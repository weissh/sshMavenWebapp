Ext.onReady(function() {
	
	Ext.QuickTips.init();
	
	//定义部门数据类型，用于下拉列表
	Ext.define('deptForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'departmentId'},
        	{name:'departmentName'}
    	]
	});
	//定义部门数据源，作为下拉列表的数据源
    var dept=new Ext.data.Store({
    	autoLoad: true,
    	model:deptForSelector,
    	proxy:{
        	type:'ajax',
        	url:'dept_getForSelector.action',
        	method:'POST',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'departmentId'
        	}
        },
        listeners:{
        	load:function(store,records,options){
        		var rs=Ext.ModelMgr.create({
        			departmentId:0,
        			departmentName:"所有部门"
        		},'deptForSelector');
        		store.insert(0,rs);
        	}
        }
    });

    //定义员工数据类型，作为下拉列表框
    Ext.define('staffForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'staffId'},
        	{name:'staffName'}
    	]
	});
	//定义员工数据源，作为下拉列表的数据源
    var staff=new Ext.data.Store({
//    	autoLoad: true,
        model:staffForSelector,
        proxy:{
        	type:'ajax',
        	url:'staff_getForSelector.action',
        	method:'POST',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'staffId'
        	}
        },
        listeners:{
        	load:function(store,records,options){
        		var rs=Ext.ModelMgr.create({
        			staffId:0,
        			staffName:"所有员工"
        		},'staffForSelector');
        		store.insert(0,rs);
        	}
        }
    });
    
     //定义职务数据源，作为下拉列表的数据源
    var position=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'1','name':'员工'},
	        {'id':'2','name':'部门经理'},
	        {'id':'3','name':'总经理'}
        ]
    });
     //定义部门数据类型
    Ext.define('department',{
    	extend: 'Ext.data.Model',
    	fields:[
    		{name: 'departmentId', type: 'int'},
    		{name:'departmentName'},
    		{name:'createTime',type: 'date', dateFormat:'Y-m-d'},
    		{name:'managerId'},
    		{name:'totalStaff'},
    		{name:'description'}
		]
    });
    //定义部门数据源，充当页面表格的数据来源
    var dept_store = Ext.create('Ext.data.Store', {
        model: 'department',
        autoLoad: true,
        proxy:{
        	type:'ajax',
        	url:'dept_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		totalProperty:'totalProperty',
        		idProperty:'departmentId'
        	}
        }
    });
	dept_store.load({url:'dept_getAll.action',params:{start:0,limit:20}});
     //定义员工数据类型
    Ext.define('user', {
        extend: 'Ext.data.Model',
        fields: [
         {name: 'departmentId', type: 'int',mapping:'department.departmentId'},
            {name: 'staffName'},
            {name: 'staffId', type: 'int'},
            {name: 'department'},
            {name: 'position'},
            {name: 'entryTime', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'phone'},
            {name: 'role'},
            {name: 'password'},
            {name: 'desc'}
         ]
    });
    
	//定义员工数据源，作为表格数据源
     var user_store = Ext.create('Ext.data.Store', {
        model: 'user',
        pageSize:20,
        proxy:{
        	type:'ajax',
        	url:'staff_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'staffId',
        		totalProperty:'totalProperty'
        	}
        }
    });
    
    
   //员工表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
	user_store.load({url:'staff_getAll.action',params:{start:0,limit:20}});
	
   
	
	dept.load();
	staff.load();
	
	 //创建工具栏表单，作为grid的上工具栏
    var dr = Ext.create('Ext.form.Panel', {
        border:false,
        width:'100%',  
		tbar:[{
			xtype:'toolbar',
			dock:'top',
			margin:'-1 0 0 -1',
			items:[{
            xtype:'combo',
            name:'deptCombo',
            store:dept,
            fieldLabel:'<b>部门</b>',
            forceSelection:true,
        	valueField:'departmentId',
        	displayField:'departmentName',
        	triggerAction : 'all',
        	value:0,
            typeAhead:true,
            width:150,
            labelWidth:30,
            margins:'0 10 0 0',
            mode:'local',
            listeners:{
            	select:function(combo,record,index){
            		Ext.getCmp('staffCombo').reset();
            		staff.load({
            			url:'staff_getForSelector.action',
            			params:{departmentId:combo.value}
            		});
//            		user_store.load({url:'staff_getAll.action',params:{start:0,limit:20}});
            	}
            }
			},{
				xtype:'button',
				text:'查询',
				iconCls:'search',listeners:{
            	click:function(){
//            		var departmentId=pe.getForm().findField('deptCombo').getValue();
            		var departmentId=dr.getForm().findField('deptCombo').getValue();
            		dept_store.on('beforeload',function(store,options){
            			var new_params={departmentId:departmentId,query:'true'};
            			Ext.apply(dept_store.proxy.extraParams,new_params);
            		});
            		dept_store.reload();
            	}
            }
//				,listeners:{
//            	click:function(){
//            		var departmentId=dr.getForm().findField('deptCombo').getValue();
//            		dept_store.on('beforeload',function(store,options){
//            			var new_params={departmentId:departmentId,query:'true'};
//            			Ext.apply(dept_store.proxy.extraParams,new_params);
//            		});
//            		dept_store.reload();
//            	}
//            }
			}]
		}]
    
    });
    //定义部门信息表
     var dept_grid=Ext.create('Ext.grid.Panel',{
		width:'35%',
		bodyPadding:0,
		height:document.body.clientHeight,
		bodyStyle:'border-width:0 1 1 0',
		layout:'fit',
		margins:'0 20 0 0',
		store:dept_store,
		viewConfig:{
			forceFit:true
		},
		tbar:[dr],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
            {text: "部门编号", width: 80,sortable: true, dataIndex: 'departmentId'},
            {text: "部门名称", width: 80, sortable: true,dataIndex: 'departmentName'},  
			{text: "部门经理", width: 80, sortable: true, dataIndex: 'managerNo'},
            {text: "总员工数",width: 80,sortable: true, dataIndex: 'totalStaff'}],
        bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: user_store,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	
     //创建工具栏表单，作为grid的上工具栏
    var pe = Ext.create('Ext.form.Panel', {
        border:false,
        width:'100%',  
		tbar:[{
             xtype:'combo',
            id:'staffCombo',
            name:'staffCombo',
            store:staff,
            fieldLabel:'<b>员工</b>',
            forceSelection:true,
            valueField:'staffId',
        	displayField:'staffName',
        	triggerAction : 'all',
        	value:0,
            typeAhead:true,
            width:150,
            labelWidth:30,
            margins:'0 10 0 0',
            mode:'remote'
			},{
				xtype:'button',
				text:'查询',
				iconCls:'search',listeners:{
            	click:function(){
//            		var departmentId=pe.getForm().findField('deptCombo').getValue();
            		var staffId=pe.getForm().findField('staffCombo').getValue();
            		user_store.on('beforeload',function(store,options){
            			var new_params={staffId:staffId,query:'true'};
            			Ext.apply(user_store.proxy.extraParams,new_params);
            		});
            		user_store.reload();
            	}
            }
			}]
		
    
    });
    //定义员工信息表
    var emp_grid=Ext.create('Ext.grid.Panel',{
		width:'64.9%',
		height:document.body.clientHeight,
		bodyStyle:'border-width:0 0 1 1',
		layout:'fit',
		store:user_store,
		viewConfig:{
			forceFit:true
		},
		tbar:[pe],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
            {text: "姓名", flex: 1, sortable: true, dataIndex: 'staffName'},
            {text: "工号", width: 120, sortable: true,dataIndex: 'staffId'},
            {
            	text: "部门", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'departmentId',
            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=dept.find('departmentId',value);
            		var record=dept.getAt(index);
            		var text="";
            		if(record==null){
            			text=value;
            		}else{
            			text=record.data['departmentName'];
            		}
            		return text;
            	}
        	},
            {text: "职务", width: 120, sortable: true, dataIndex: 'position',
            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=position.find('id',value);
            		var record=position.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
            {text: "入职时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'entryTime'},
            {text: "联系电话", width: 120, sortable: true, dataIndex: 'phone'}
		],
		 bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: user_store,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	
	//定义界面视图
    var viewPort = new Ext.container.Viewport({
    	layout:'hbox',
		items:[dept_grid,emp_grid],
		renderTo:Ext.getBody()
    });
    //用于渲染grid中的与form中下拉列表框对应的值，使其显示的是name字段而不是id字段
    function getText(record){
    	var text="";
		if(record==null){
			text=value;
		}else{
			text=record.data['name'];
		}
		return text;
    };
});