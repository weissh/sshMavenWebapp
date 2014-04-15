Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux','extjs/ux');
Ext.require([
    'Ext.form.Panel',
    'Ext.ux.form.MultiSelect',
    'Ext.ux.form.ItemSelector',
    'Ext.tip.QuickTipManager',
    'Ext.ux.ajax.JsonSimlet',
    'Ext.ux.ajax.SimManager'
]);

Ext.onReady(function () {
	
	Ext.QuickTips.init();
    
    Ext.tip.QuickTipManager.init();
    
    
    //定义部门数据源，充当页面表格的数据来源
    var departmentStore = Ext.create('Ext.data.Store', {
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

    //用表单制作部门表格的工具栏
    var formForTbar=Ext.create('Ext.form.Panel',{
    	border:false,
    	width:'100%',
    	tbar:['->',
        	{xtype:'button',text:'新增',iconCls: 'user_add',handler:addDepartmentInfo},
            {xtype:'button',text:'修改',iconCls: 'user_edit',handler:editDepartmentInfo},
            {xtype:'button',text:'删除',iconCls: 'user_delete',handler:deleteDepartmentInfo},
            {xtype:'button',text:'管理员工',iconCls: 'user_delete',handler:userManagement},
            {xtype:'button',text:'导出',iconCls:'file_export',handler:exportDeartmentInfo}
        ]
    });
    //创建部门表格
    var grid = Ext.create('Ext.grid.Panel', {
        height: '100%',
        border:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
        store: departmentStore,
//        margin: '0 0 2 0',	
        viewConfig:{
            forceFit:false
        },
        tbar:[formForTbar],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "部门编号", flex: 0.2, sortable: true, dataIndex: 'departmentId'},
            {text: "部门名称", flex: 0.2, sortable: true,dataIndex: 'departmentName'},
            {text: "部门经理工号", flex: 0.2, sortable: true, dataIndex: 'managerId',hidden:true},
            {text: "部门经理", flex: 0.2, sortable: true, dataIndex: 'managerName'},
            {text: "总员工数",flex: 0.2, sortable: true, dataIndex: 'totalStaff'},
            {
        		text: "成立时间", 
        		width: 120, 
        		sortable: true, 
        		renderer: Ext.util.Format.dateRenderer('Y-m-d'), 
        		dataIndex: 'createTime'
    		},
    		{text: "部门描述",flex: 0.2, sortable: true, dataIndex: 'description'}
        ],
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: departmentStore,
	        dock: 'bottom',
	        displayInfo: true
	    }],
        renderTo: Ext.getBody()
    });
    grid.addListener('itemdblclick', editDepartmentInfo, this);
    
    //创建新增、修改部门表单
	var form = Ext.create('Ext.form.Panel', {
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
        	name:'departmentId'
        },{
        	xtype:'textfield',
        	hidden:true,
        	name:'totalStaff'
        },{
        	xtype:'textfield',
        	hidden:true,
        	name:'managerId'
        },{
        	xtype:'textfield',
        	hidden:true,
        	name:'managerName'
        },{
        	xtype:'textfield',
        	fieldLabel : '部门名称',
			name : 'departmentName',
            allowBlank:false,
            blankText:'不允许为空'
        },{
        	xtype : 'datefield',
			fieldLabel : '成立时间',
			name : 'createTime',
			format : 'Y-m-d',
            allowBlank:false,
            blankText:'不允许为空'
        },{
        	xtype : 'textarea',
			fieldLabel : '部门描述',
			name : 'description',
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
    
    var manageStaff=Ext.create('Ext.form.Panel',{
    	width:'100%',
    	height:'100%',
    	bodyPadding:10,
    	border:false,
    	bodyStyle:'background:#dfe9f5',
    	defaults:{
    		labelWidth:60,
    		anchor:'100%',
    		msgTarget:'qtip',
    		allowBlank:false
    	},
    	items:[{
    		xtype:'combo',
    		fieldLabel:'源部门',
    		name:'sourceDept',
    		store:departmentStore,
    		displayField:'departmentName',
    		valueField:'departmentId',
    		listeners:{
    			select:function(combo,record,index){
    				var target=manageStaff.getForm().findField('targetDept').value;
//    				alert(record[0].data.departmentId);
    				var source=record[0].get('departmentId');
    				if(source!=target){
    					Ext.getCmp('btn').setDisabled(false);
    				}else{
    					Ext.getCmp('btn').setDisabled(true);
    				}
    			}
    		}
    	},{
    		xtype:'combo',
    		fieldLabel:'目标部门',
    		name:'targetDept',
    		store:departmentStore,
    		displayField:'departmentName',
    		valueField:'departmentId',
    		listeners:{
    			select:function(combo,record,index){
    				var source=manageStaff.getForm().findField('sourceDept').value;
//    				alert(record[0].data.departmentId);
    				var target=record[0].get('departmentId');
    				if(source!=target){
    					Ext.getCmp('btn').setDisabled(false);
    				}else{
    					Ext.getCmp('btn').setDisabled(true);
    				}
    			}
    		}
    	}],
    	buttons:[{
			text:'提交',
			id:'btn',
			disabled:true,
			handler:manage
		},{
			text:'取消',
			handler:function(){
				wind.close();
			}
		}]
    });
    
    var wind = new Ext.Window({
    	layout : 'fit',
    	title:'员工管理',
		width :350,
		height : 130,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : manageStaff
    });
    //新增、修改部门的弹出框
    var win = new top.Ext.Window({
    	layout : 'fit',
		width :350,
		height : 230,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
    
	//增加新部门
    function addDepartmentInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	win.setTitle('新增部门');
    	win.show();
    };
    
    //修改部门
    function editDepartmentInfo(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	win.setTitle('修改部门');
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    //删除部门
    function deleteDepartmentInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	top.Ext.Msg.confirm('提示','您确定要删除所选部门吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteDept(records);
    		}
    	});
    };
    //执行删除操作
    function deleteDept(records){
    	var departmentIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('departmentId');
    		if(i==0){
    			departmentIds+=id;
    		}else{
    			departmentIds=departmentIds+','+id;
    		}
    	}
    	var msgTip =top.Ext.Msg.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除部门信息，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'dept_delete.action',
    		params:{departmentIds:departmentIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
			    	for(var i=0;i<records.length;i++){
			    		var index=departmentStore.find('departmentId',records[i].get('departmentId'));
			    		if(index!=-1){
			    			var rec=departmentStore.getAt(index);
			    			departmentStore.remove(rec);
			    			grid.getView().refresh();
			    		}
			    	}
    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
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
					url:basePath+'dept_add.action',
					method:'POST',
	    			success:function(form,action){
	    				win.hide();
						updateGrid(action.result.msg);
	    				top.Ext.Msg.show({title:'提示', msg:'新增部门成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
	    			},
	    			failure:function(form,action){
	    				top.Ext.Msg.show({title:'提示', msg:'新增部门失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
	    			}
	    		});
	    	}else{//修改
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
					url:'dept_update.action',
					method:'POST',
					success:function(form,action){
						win.hide();
						updateGrid(action.result.msg);
						top.Ext.Msg.show({title:'提示', msg:'修改部门成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
					},
					failure:function(form,action){
						top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
					}
	    		});
	    	}
    	}
    };
    
    //新增、修改后更新前端的数据
    function updateGrid(departmentId){
    	var values=form.form.getValues();
    	var index=departmentStore.find('departmentId',values['departmentId']);
    	if(index!=-1){
    		var item = departmentStore.getAt(index);
    		for(var fieldName=departmentStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			departmentId:departmentId,
    			departmentName:values['departmentName'],
    			createTime:values['createTime'],
    			description:values['description']
    		},'department');
    		departmentStore.add(rec);
    	}
    }

    //员工管理
    function userManagement(){
    	wind.show();
    };
    
    function manage(){
    	if(manageStaff.form.isValid()){
    		manageStaff.form.submit({
    			waitMsg:'正在提交，请稍后',
    			waitTitle:'提示',
    			url:'dept_manageStaff.action',
    			method:'POST',
    			success:function(form,action){
    				wind.hide(),
    				top.Ext.Msg.show({title:'提示', msg:'移动员工成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
				},
				failure:function(form,action){
					top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
				}
    		});
    	}
    }
    
    //导出部门信息到excel
    function exportDeartmentInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	var msg;
    	var departmentIds="";
    	if(records.length==0){
    		msg="您确定要导出所有部门信息吗？";
    	}else{
    		msg="您确定要导出所选的部门信息吗？";
	    	for(var i=0;i<records.length;i++){
	    		var id=records[i].get('departmentId');
	    		if(i==0){
	    			departmentIds+=id;
	    		}else{
	    			departmentIds=departmentIds+','+id;
	    		}
			}
    	}
    	top.Ext.Msg.confirm('提示',msg,function(btnID){
    		if(btnID=='yes'){
    			window.location.href='byjx/dept_export.action?departmentIds='+departmentIds;
    		}
		});
    }
});
