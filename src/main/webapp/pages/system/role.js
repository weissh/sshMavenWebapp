Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//定义部门数据类型，用于下拉列表
	Ext.define('role', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'roleId',type:'int'},
        	{name:'roleName'},
        	{name:'roleDesc'}
    	]
	});
	
	//定义部门数据源，作为下拉列表的数据源
    var roleStore=new Ext.data.Store({
    	autoLoad:true,
    	model:role,
    	proxy:{
        	type:'ajax',
        	url:'role_getAll.action',
        	method:'POST',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'roleId'
        	}
        }
    });
//    roleStore.load();
    var roleId=1;
	//定义角色表格
	var grid=Ext.create('Ext.grid.Panel', {
		width:'50%',
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
	        store: roleStore, 
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
		columns:[
			Ext.create('Ext.grid.RowNumberer'),
			{text:'角色编号',width:80,sortable:true,dataIndex:'roleId'},
			{text:'角色名称',width:80,sortable:true,dataIndex:'roleName'},
			{text:'角色描述',flex:1,sortable:true,dataIndex:'roleDesc'}
		],
		listeners:{
			itemclick:function(view,rec,item,index,e,eOpts){
				roleId= rec.get('roleId');
				Ext.Ajax.request({
					url:'right_getAll.action',
					method:'POST',
					params:{roleId:roleId},
					dataType:'json', 
					async:false
				});
				treeStore.reload();
            }
		},
		renderTo:Ext.getBody()
	});
	
	//定义角色基本信息
	var roleInfo={
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
//	
	Ext.Ajax.request({
		url:'right_getAll.action',
		method:'POST',
		params:{roleId:roleId},
		dataType:'json', 
		async:false
	});
//		success:function(response,options){
//			var result=Ext.JSON.decode(response.responseText);
//			children=result.infoList;
//		}
//	});

	var treeStore=new Ext.data.TreeStore(eval(storeForRole));
	treeStore.sort('id','ASC');
          
	var tree=Ext.create('Ext.tree.Panel', {
		title:'设置角色权限',
	    width: '49.9%',
	    height: '100%',
	    margin:'-1 -1 -1 0',
	    store: treeStore,
	    rootVisible: false,
		viewConfig:{
		    onCheckboxChange: function(e, t) {
		         var item = e.getTarget(this.getItemSelector(), this.getTargetEl()), record;
		         if (item) {
		             record = this.getRecord(item);
		             var check = !record.get('checked');
		             record.set('checked', check);
		             if (check) {
		                 record.bubble(function(parentNode) {
		                     parentNode.set('checked', true);
		                 });
		                 record.cascadeBy(function(node) {
		                     node.set('checked', true);
		                 });
		                 record.expand();
		                 record.expandChildren();
		             } else {
//		                 record.collapse();
		                 record.collapseChildren();
		                 record.cascadeBy(function(node) {
		                     node.set('checked', false);
		                 });
		                 record.bubble(function(parentNode) {
		                    var childHasChecked=false;
		                    var childNodes = parentNode.childNodes;
		                    if(childNodes || childNodes.length>0){
		                        for(var i=0;i<childNodes.length;i++){
		                            if(childNodes[i].data.checked){
		                                childHasChecked= true;
		                                break;
		                            }
		                        }
		                    }
		                    if(!childHasChecked){
		                         parentNode.set('checked', false);
		                     }
		                 });
		                 
		             }
		         }
		     }
		},
		bbar:['->',
			{xtype:'button',text:'保存',iconCls: 'role_add',handler:saveRight}
		],
	    renderTo: Ext.getBody()
	});
//	
//	tree.on('checkchange', function(node, checked) {   
//		node.expand();   
//		node.attributes.checked = checked;   
//		node.eachChild(function(child) {   
//			child.ui.toggleCheck(checked);   
//			child.attributes.checked = checked;   
//			child.fireEvent('checkchange', child, checked);   
//		});   
//	}, tree);
    
	//定义另一个表单，用于新增、修改角色信息；定义角色信息表单：包括角色基本信息以及其权限信息,
	var form=top.Ext.create('Ext.form.Panel',{
		border:false,
		bodyStyle: 'background:#dfe9f5',
		bodyPadding:15,
		width:'100%',
		height:'100%',
		items:[roleInfo],
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
		items:[form,tree]
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
		items:[grid,tree]
	});
	
	function saveRight(){
		var check=tree.getChecked();
		var ids=new Array();
		var rightIds;
		Ext.each(check,function(node){
			ids.push(node.get('id'));
		});
		rightIds=ids.join(',');
		alert(rightIds);
		Ext.Ajax.request({
			url:'role_saveRight.action',
			method:'POST',
			params:{rightIds:rightIds},
			success:function(response,options){
    			if(result.success){
    				top.Ext.Msg.show({title:'提示', msg:'保存角色权限成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'保存角色权限失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		}
		});
	}
});