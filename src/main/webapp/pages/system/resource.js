Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux','extjs/ux');
Ext.require([
    'Ext.form.Panel',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function () {
	
	Ext.QuickTips.init();
    
    Ext.tip.QuickTipManager.init();
    
    //定义部门数据类型，用于下拉列表
	Ext.define('rightForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'id',type:'int'},
        	{name:'text'}
    	]
	});
	
	//定义部门数据源，作为下拉列表的数据源
    var right=new Ext.data.Store({
    	autoLoad:true,
    	model:rightForSelector,
    	proxy:{
        	type:'ajax',
        	url:'right_getForSelector.action',
        	method:'POST',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'id'
        	}
        }
    });
    
    //定义资源数据类型
    Ext.define('resource',{
    	extend: 'Ext.data.Model',
    	fields:[
    		{name:'resourceId',type:'int'},
    		{name:'resourceName'},
    		{name:'resourceType'},
    		{name:'resourceDesc'},
    		{name:'url'}
		]
    });
    
    //定义资源数据源，充当页面表格的数据来源
    var resourceStore = Ext.create('Ext.data.Store', {
        model: 'resource',
        autoLoad: true,
        proxy:{
        	type:'ajax',
        	url:'resource_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		totalProperty:'totalProperty',
        		idProperty:'resourceId'
        	}
        }
    });

    //定义资源类型数据源，作为下拉列表的数据源
    var resourceType=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'url','name':'链接'},
	        {'id':'action','name':'操作'}
        ]
    });
    
    //定义资源优先级数据源，作为下拉列表的数据源
    var priority=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'1','name':'一级'},
	        {'id':'2','name':'二级'},
	        {'id':'3','name':'三级'},
	        {'id':'4','name':'四级'}
        ]
    });
    
    //定义资源是否禁用数据源，作为下拉列表的数据源
    var enabled=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'1','name':'可用'},
	        {'id':'0','name':'禁用'}
        ]
    });
    
    //定义资源是否为超级权限数据源，作为下拉列表的数据源
    var isSuper=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'1','name':'是'},
	        {'id':'0','name':'否'}
        ]
    });
    
    //用表单制作资源表格的工具栏
    var formForTbar=Ext.create('Ext.form.Panel',{
    	border:false,
    	width:'100%',
    	tbar:[
        	{xtype:'button',text:'新增资源',iconCls: 'user_add',handler:addResourceInfo},
            {xtype:'button',text:'修改资源',iconCls: 'user_edit',handler:editResourceInfo},
            {xtype:'button',text:'删除资源',iconCls: 'user_delete',handler:deleteResourceInfo}
        ]
    });
    
    //创建资源表格
    var grid = Ext.create('Ext.grid.Panel', {
        height: '100%',
        border:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
        store: resourceStore,
//        margin: '0 0 2 0',	
        viewConfig:{
            forceFit:false
        },
        tbar:[formForTbar],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "资源编号", sortable: true, dataIndex: 'resourceId'},
            {text: "资源名称", sortable: true,dataIndex: 'resourceName'},
            {
            	text: "资源类型",
            	sortable: true,
            	dataIndex: 'resourceType',
            	renderer:function(value){
            		var index=resourceType.find('id',value);
            		var record=resourceType.getAt(index);
            		return getText(record);
            	}
        	},
            {text: "资源路径", flex: 0.1, sortable: true, dataIndex: 'url'},
    		{text: "资源描述",flex: 0.1, sortable: true, dataIndex: 'resourceDesc'}
        ],
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: resourceStore,
	        dock: 'bottom',
	        displayInfo: true
	    }],
        renderTo: Ext.getBody()
    });
    
    //创建新增、修改资源表单
	var form = top.Ext.create('Ext.form.Panel', {
        width:'100%',
        height:'100%',
        bodyPadding: 10,
        border:false,
        bodyStyle: 'background:#dfe9f5',
        defaults: {
        	labelWidth:60,
            anchor: '100%',
            msgTarget:'qtip'
        },
        items: [{
        	xtype:'textfield',
        	hidden:true,
        	name:'resourceId'
        },{
        	xtype:'combo',
        	fieldLabel : '权限',
			name : 'rightId',
        	store:right,
        	valueField:'id',
        	displayField:'text',
            allowBlank:false,
            blankText:'不允许为空'
        },{
        	xtype:'textfield',
        	fieldLabel : '资源名称',
			name : 'resourceName',
            allowBlank:false,
            blankText:'不允许为空'
        },{
        	xtype:'combo',
        	fieldLabel : '资源类型',
			name : 'resourceType',
        	store:resourceType,
        	valueField:'id',
        	displayField:'name',
            allowBlank:false,
            blankText:'不允许为空'
        },{
        	xtype:'textfield',
        	fieldLabel : '资源路径',
			name : 'url',
            allowBlank:false,
            blankText:'不允许为空'
        },
        {
        	xtype : 'textarea',
			fieldLabel : '资源描述',
			name : 'resourceDesc',
            allowBlank:false,
            blankText:'不允许为空'
        }],
        buttons:[{
			text:'提交',
			handler:submitForm
		},{
			text:'取消',
			handler:function(){
				win.close();
			}
		}]
    });
    
    //新增、修改资源的弹出框
    var win = new top.Ext.Window({
    	layout : 'fit',
		width :350,
		height : 330,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
    
	//增加新资源
    function addResourceInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	win.setTitle('新增资源');
    	win.show();
    };
    
    //修改资源
    function editResourceInfo(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	win.setTitle('修改资源');
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    //删除资源
    function deleteResourceInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	top.Ext.Msg.confirm('提示','您确定要删除所选资源吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteResource(records);
    		}
    	});
    };
    //执行删除操作
    function deleteResource(records){
    	var resourceIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('resourceId');
    		if(i==0){
    			resourceIds+=id;
    		}else{
    			resourceIds=resourceIds+','+id;
    		}
    	}
    	var msgTip =top.Ext.Msg.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除资源信息，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'resource_delete.action',
    		params:{resourceIds:resourceIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
			    	for(var i=0;i<records.length;i++){
			    		var index=resourceStore.find('resourceId',records[i].get('resourceId'));
			    		if(index!=-1){
			    			var rec=resourceStore.getAt(index);
			    			resourceStore.remove(rec);
			    			grid.getView().refresh();
			    		}
			    	}
    				top.Ext.Msg.show({title:'提示', msg:'删除资源信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:result.msg,icon:Ext.Msg.ERROR,maxWidth:250,buttons:Ext.Msg.OK});
    			}
    		}
    	});
    };
    
    //新增、修改表单提交后想后台发送请求
    function submitForm(){
    	if(form.form.isValid()){
	    	if(form.isAdd){//新增
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
					url:basePath+'resource_add.action',
					method:'POST',
	    			success:function(form,action){
	    				win.hide();
						updateGrid(action.result.msg);
	    				top.Ext.Msg.show({title:'提示', msg:'新增资源成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
	    			},
	    			failure:function(form,action){
	    				top.Ext.Msg.show({title:'提示', msg:'新增资源失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
	    			}
	    		});
	    	}else{//修改
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
					url:'resource_update.action',
					method:'POST',
					success:function(form,action){
						win.hide();
						updateGrid(action.result.msg);
						top.Ext.Msg.show({title:'提示', msg:'修改资源成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
					},
					failure:function(form,action){
						top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
					}
	    		});
	    	}
    	}
    };
    
    //新增、修改后更新前端的数据
    function updateGrid(resourceId){
    	var values=form.form.getValues();
    	var index=resourceStore.find('resourceId',values['resourceId']);
    	if(index!=-1){
    		var item = resourceStore.getAt(index);
    		for(var fieldName=resourceStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			resourceId:resourceId,
    			resourceName:values['resourceName'],
    			resourceType:values['resourceType'],
    			url:values['url'],
    			priority:values['priority'],
    			enabled:values['enabled'],
    			isSuper:values['isSuper'],
    			resourceDesc:values['resourceDesc']
    		},'resource');
    		resourceStore.add(rec);
    	}
    }
    
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
