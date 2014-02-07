Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	Ext.define('role',{
		extend:'Ext.data.Model',
		fields:[
            {name: 'roleNo', type: 'int'},
			{name: 'roleName'},
            {name: 'roleDescription'}
		]
	});
	
	Ext.grid.dummyData = [
        [001,'普通员工','限制操作'],
        [002,'管理员','最大权限']
    ];
	
	var roleStore=Ext.create('Ext.data.Store',{
		model:'role',
		autoLoad:true,
		data:Ext.grid.dummyData
	});
	
	//定义角色表格
	var grid=Ext.create('Ext.grid.Panel', {
		width:'40%',
		margins:'0 20 0 -1',
		height:document.body.clientHeight,
		bodyStyle:'border-width:0 1px 1px 0',
		store:roleStore,
		dockedItems: [{
	        xtype: 'toolbar',
	        margin:'-1 0 0 -1',
	        dock: 'top',
	        items:[
				{xtype:'button',id:'role_add',text:'新增角色',iconCls: 'role_add',handler:addRoleInfo},
				//{xtype:'button',id:'role_update',text:'修改角色',iconCls: 'role_edit',handler:editRoleInfo},
				{xtype:'button',id:'role_del',text:'删除角色',iconCls: 'role_delete',handler:deleteRoleInfo}
			]
	    },{
	        xtype: 'pagingtoolbar',
	        border:true,
	        margin:'0 0 -1 -1',
	        store: role_store, 
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
			{text:'角色编号',width:80,sortable:true,dataIndex:'roleNo'},
			{text:'角色名称',width:80,sortable:true,dataIndex:'roleName'},
			{text:'角色描述',flex:1,sortable:true,dataIndex:'roleDescription'}
		],
		listeners:{
			itemclick:function(view,rec,item,index,e,eOpts){
                    role_form.getForm().loadRecord(rec);
            }
		},
		renderTo:Ext.getBody()
	});
	
	//定义角色权限列表：复选框
	var authorityList={
		layout:'form',
		width:'100%',
		frame:false,
		border:false,
		bodyStyle: 'background:#dfe9f5',
		height:'100%',
		defaults:{
			columns:4
		},
		items:[{
			xtype:'checkboxgroup',
			fieldLabel:'基本信息 -个人',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'personalInfo',inputValue:'1',checked:true},
				{boxLabel:'修改密码',name:'personalInfo',inputValue:'2'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'基本信息 -部门',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'departmentInfo',inputValue:'1',checked:true},
				{boxLabel:'导出',name:'departmentInfo',inputValue:'2'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'工作日志 -个人',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'personalJournal',inputValue:'1',checked:true},
				{boxLabel:'新建',name:'personalJournal',inputValue:'2'},
				{boxLabel:'导入',name:'personalJournal',inputValue:'3'},
				{boxLabel:'导出',name:'personalJournal',inputValue:'4'},
				{boxLabel:'修改',name:'personalJournal',inputValue:'5'},
				{boxLabel:'查找',name:'personalJournal',inputValue:'6'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'工作日志 -部门',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'departmentJournal',inputValue:'1',checked:true},
				{boxLabel:'新建',name:'departmentJournal',inputValue:'2'},
				{boxLabel:'导入',name:'departmentJournal',inputValue:'3'},
				{boxLabel:'导出',name:'departmentJournal',inputValue:'4'},
				{boxLabel:'修改',name:'departmentJournal',inputValue:'5'},
				{boxLabel:'删除',name:'departmentJournal',inputValue:'6'},
				{boxLabel:'查找',name:'departmentJournal',inputValue:'7'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'费用支出 -个人',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'personalCost',inputValue:'1',checked:true},
				{boxLabel:'新建',name:'personalCost',inputValue:'2'},
				{boxLabel:'导入',name:'personalCost',inputValue:'3'},
				{boxLabel:'导出',name:'personalCost',inputValue:'4'},
				{boxLabel:'修改',name:'personalCost',inputValue:'5'},
				{boxLabel:'查找',name:'personalCost',inputValue:'6'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'费用支出 -部门',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'departmentCost',inputValue:'1',checked:true},
				{boxLabel:'新建',name:'departmentCost',inputValue:'2'},
				{boxLabel:'导入',name:'departmentCost',inputValue:'3'},
				{boxLabel:'导出',name:'departmentCost',inputValue:'4'},
				{boxLabel:'修改',name:'departmentCost',inputValue:'5'},
				{boxLabel:'删除',name:'departmentCost',inputValue:'6'},
				{boxLabel:'查找',name:'departmentCost',inputValue:'7'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'系统管理 -用户',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'userManage',inputValue:'1',checked:true},
				{boxLabel:'新增',name:'userManage',inputValue:'2'},
				{boxLabel:'导出',name:'userManage',inputValue:'3'},
				{boxLabel:'修改',name:'userManage',inputValue:'4'},
				{boxLabel:'删除',name:'userManage',inputValue:'5'},
				{boxLabel:'查找',name:'userManage',inputValue:'6'}
			]
		},{
			xtype:'checkboxgroup',
			fieldLabel:'系统管理 -角色',
			vertical:true,
			items:[
				{boxLabel:'查看',name:'roleManage',inputValue:'1',checked:true},
				{boxLabel:'新增',name:'roleManage',inputValue:'2'},
				{boxLabel:'修改',name:'roleManage',inputValue:'3'},
				{boxLabel:'删除',name:'roleManage',inputValue:'4'}
			]
		}]
	};
	
	//定义角色基本信息
	var role_info={
		border:false,
		bodyStyle: 'background:#dfe9f5',
		items:[{
			layout:'hbox',
			bodyStyle: 'background:#dfe9f5',
			border:false,
			defaults:{
				width:'50%',
				labelWidth:55
			},
			items:[{
				xtype:'textfield',
				fieldLabel:'角色编号',
				name:'roleNo',
				margin:'0 25 0 0',
				readOnly:true,
				allowBlank : false
			},{
				border:true,
				xtype:'textfield',
				fieldLabel:'角色名称',
				margin:'0 1 0 0',
				name:'roleName',
				readOnly:true,
				allowBlank : false
			}]
		},{
			xtype:'textarea',
			fieldLabel:'角色描述',
			width:575,
			labelWidth:55,
			margin:'5 0 0 0',
			name:'roleDescription',
			readOnly:true,
			allowBlank : false
		}]
	}
	
	//定义角色信息表单：包括角色基本信息以及其权限信息
	var role_form=Ext.create('Ext.form.Panel', {
		frame:false,
		border:true,
		bodyStyle: 'background:#dfe9f5;border-width:1px 0 0 1px',
		width:'59.9%',
		bodyPadding:'5px 40px 0 40px',
		height:'100%',
		items:[role_info,authorityList],
		dockedItems: [{
	        xtype: 'toolbar',
	        margin:'-1 -1 0 0',
	        dock: 'top',
	        items:[
				{xtype:'button',text:'保存',iconCls: 'save',handler:saveRoleInfo}
			]
	    }]
	});
	
	var record=grid.getStore().getAt(0);
	role_form.getForm().loadRecord(record);
	
	//定义另一个表单，用于新增、修改角色信息；定义角色信息表单：包括角色基本信息以及其权限信息,
	var form=top.Ext.create('Ext.form.Panel',{
		border:false,
		bodyStyle: 'background:#dfe9f5',
		bodyPadding:15,
		width:'100%',
		height:'100%',
		items:[role_info,authorityList],
		fbar:[{
			xtype:'button',
			text:'提交',
			handler:submitForm
		},{
			xtype:'button',
			text:'取消',
			handler:function(){
				//win.close();
				var form1   = this.up('form').getForm(),
                    encode = Ext.String.htmlEncode,
                    s      = '';

                if (form1.isValid()) {
                    Ext.iterate(form1.getValues(), function(key, value) {
                        value = encode(value);
                        
                        s += Ext.util.Format.format("{0} = {1}<br />", key, value);
                    }, this);

                    top.Ext.Msg.alert('Form Values', s);
                }
			}
		}]
	});
	
	//定义弹出框，用于新建、修改角色信息
	var win = new top.Ext.Window({
		layout:'fit',
		width:620,
		height:550,
		closeAction:'hide',
		constrainHeader:true,
		plain:true,
		modal:true,
		items:[form]
	});

	//增加角色
	function addRoleInfo(){
		form.form.reset();
		form.getForm().findField('roleNo').setReadOnly (false);
		form.getForm().findField('roleName').setReadOnly (false);
		form.getForm().findField('roleDescription').setReadOnly (false);
		form.isAdd=true;
		win.setTitle('新增角色');
		win.show();
	};
	
	//保存修改的角色信息
	function saveRoleInfo(){
		role_form.getForm().reset();
	};
	
//	//编辑角色
//	function editRoleInfo(){
//		var records=role_grid.getSelectionModel().getSelection();
//		if(records.length==1){
//			form.form.reset();
//			form.isAdd=false;
//			win.setTitle('修改角色');
//			win.show();
//			form.getForm().loadRecord(records[0]);
//		}else {
//			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
//		}
//	};
	
	//删除角色
	function deleteRoleInfo(){
		var records=grid.getSelectionModel().getSelection();
		if(records.length==0){
			top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
		}
		Ext.Msg.confirm('提示','您确定要删除所选的角色吗？',function(btnID){
			if(btnID=='yes'){
				deleteRoles(records);
			}
		});
	};
	//执行删除操作
	function deleteRoles(records){
		var roleID=records.join(',');
//    	var msgTip=Ext.MessageBox.show({
//    		title:'提示',
//    		width:250,
//    		msg:'正在删除角色信息，请稍后...'
//    	});
		for(var i=0;i<records.length;i++){
			var index=roleStore.find('roleNo',records[i].get('roleNo'));
			if(index!=-1){
				var rec=roleStore.getAt(index);
				roleStore.remove(rec);
				role_form.getForm().reset();
			}
		}
//		Ext.Ajax.request({
//    		url:'',
//    		params:{userID:userID},
//    		method:'POST',
//    		success:function(response,options){
//    			msgTip.hide();
//    			var result=Ext.JSON.decode(response.responseText);
//    			if(result.success){
//    				for(var i=0;i<userList.lenght;i++){
//    					var index=user_store.find('id',userList[i]);
//    					if(index!=-1){
//    						var rec=userList.getAt(index);
//    						user_store.remove(rec);
//    					}
//    				}
//    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
//    			}else{
//    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息失败！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
//    			}
//    		}
//    	});
	};
	//弹出框中，提交表单时的动作
	function submitForm(){
		
	};
	Ext.create('Ext.container.Viewport',{
		width:document.body.clientWidth,
		height:document.body.clientHeight,
		bodyBorder:false,
		layout:'hbox',
		border:false,
		bodyStyle: 'background:#f0f0f0',
		items:[grid,role_form]
	});
});