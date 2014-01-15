
Ext.onReady(function() {
	
	Ext.QuickTips.init();
	
	var dept=new Ext.data.ArrayStore({
        fieldLabel:30,
        fields:['id','name'],
        data:[
            [1,'财务部'],
            [2,'人力部'],
            [3,'销售部']
        ]
    });

    var user=new Ext.data.ArrayStore({
        fields:['id','name'],
        data:[
            [1,'小闫'],
            [2,'小祖'],
            [3,'小蔡'],
            [4,'小宋'],
            [5,'小吴']
        ]
    });
    
    Ext.define('user', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'staffName'},
            {name: 'staffNo', type: 'int'},
            {name: 'department'},
            {name: 'position'},
            {name: 'enterTime', type: 'date', dateFormat: 'n/j/Y'},
            {name: 'phone'},
            {name: 'role'},
            {name: 'password'},
            {name: 'desc'}
         ]
    });

    Ext.grid.dummyData = [
        ['菜','01','财务部','总经理','9/9/2006','123213','总经理','123123'],
        ['菜','01','销售部','总经理','9/1/2010','123213','总经理','123123']
    ];

    var user_store = Ext.create('Ext.data.Store', {
        model: 'user',
        autoLoad: true,
        data : Ext.grid.dummyData
        //proxy:new Ext.ux.data.PagingMemoryProxy(Ext.grid.dummyData)
    });
	
    Ext.define('department',{
    	extend:'Ext.data.Model',
    	fields:[
    		{name:'departmentNo'},
			{name:'departmentName'},
			{name:'totalStaff'},
			{name:'manager'}]
    });
    
	var dept_store=Ext.create('Ext.data.Store',{
		model:'department',
		autoLoad:true,
		data:[
	    	[001,'财务部',24,'小菜'],
	    	[002,'销售部',24,'小召']
		]
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
		dockedItems:[{
			xtype:'toolbar',
			dock:'top',
			margin:'-1 0 0 -1',
			items:[{
				xtype:'combo',
				store:dept,
				fieldLabel:'<b>部门</b>',
				displayField:'name',
				forceSelection:true,
				typeAhead:true,
				width:150,
				labelWidth:30,
				margins:'0 10 0 0',
				mode:'local'
			},{
				xtype:'button',
				text:'查询',
				iconCls:'search'
			}]
		},{
			xtype: 'pagingtoolbar',
	        margin:'0 0 -1 -1',
	        store: dept_store, 
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
            {text: "部门编号", flex: 1,sortable: true, dataIndex: 'departmentNo'},
            {text: "部门名称", width: 80, sortable: true,dataIndex: 'departmentName'},
            {text: "部门人数", width: 80, sortable: true, dataIndex: 'totalStaff'},
            {text: "部门经理", width: 80, sortable: true, dataIndex: 'manager'}
		],
		listener:{
			itemclick:function(view,rec,item,index,e,eOpts){
				
			}
		}
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
		dockedItems:[{
			xtype:'toolbar',
			dock:'top',
			margin:'-1 -1 0 0',
			items:[{
				xtype:'combo',
					store:user,
					fieldLabel:'<b>员工</b>',
					displayField:'name',
					forceSelection:true,
					typeAhead:true,
					width:150,
					labelWidth:30,
					margins:'0 10 0 0',
					mode:'local'
			},{
				xtype:'button',
				text:'查询',
				iconCls:'search'
			}]
		},{
			xtype: 'pagingtoolbar',
	        margin:'0 -1 -1 0',
	        store: user_store, 
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
            {text: "姓名", flex: 1, sortable: true, dataIndex: 'staffName'},
            {text: "工号", width: 120, sortable: true,dataIndex: 'staffNo'},
            {text: "部门", width: 120, sortable: true, dataIndex: 'department'},
            {text: "职务", width: 120, sortable: true, dataIndex: 'position'},
            {
        		text: "入职时间", 
        		width: 120, 
        		sortable: true, 
        		renderer: Ext.util.Format.dateRenderer('m/d/Y'), 
        		dataIndex: 'enterTime'
    		},
            {text: "联系电话", width: 120, sortable: true, dataIndex: 'phone'}
		]
	});
	
//	new Ext.Panel({
//		title:'11',
//		width:'100%',
//		height:'100%',
//		layout:'hbox',
//		items:[grid_dept,grid_emp],
//		renderTo:Ext.getBody()
//	});
	
	//定义界面视图
    var viewPort = new Ext.container.Viewport({
    	layout:'hbox',
		items:[dept_grid,emp_grid],
		renderTo:Ext.getBody()
    });
});