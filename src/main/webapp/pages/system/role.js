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
   
	//定义角色表格
	var grid=Ext.create('Ext.grid.Panel', {
		width:'100%',
		height:document.body.clientHeight,
//		bodyStyle:'border-width:0 1px 1px 0',
		border:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
		store:roleStore,
		dockedItems: [{
	        xtype: 'toolbar',
	        margin:'-1 0 0 -1',
	        dock: 'top',
	        items:['->',
				{xtype:'button',id:'role_add',text:'新增角色',iconCls: 'role_add',handler:addRoleInfo},
				{xtype:'button',id:'role_update',text:'修改角色',iconCls: 'role_edit',handler:editRoleInfo},
				{xtype:'button',id:'role_del',text:'删除角色',iconCls: 'role_delete',handler:deleteRoleInfo},
				{xtype:'button',id:'allot_rights',text:'设置权限',iconCls: 'role_delete',handler:allotRights}
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
		renderTo:Ext.getBody()
	});
	 grid.addListener('itemdblclick', allotRights, this);
    
	//定义另一个表单，用于新增、修改角色信息；定义角色信息表单：包括角色基本信息以及其权限信息,
	var form=top.Ext.create('Ext.form.Panel',{
		border:false,
		bodyStyle: 'background:#dfe9f5',
		bodyPadding:15,
		width:'100%',
		height:'100%',
		defaults: {
        	labelWidth:60,
            anchor: '100%',
            msgTarget:'qtip'
        },
		items:[{
			xtype:'textfield',
			fieldLabel:'角色编号',
			name:'roleId',
//			readOnly:true,
			hidden:true
		},{
			xtype:'textfield',
			fieldLabel:'角色名称',
			name:'roleName',
			allowBlank : false
		},{
			xtype:'textarea',
			fieldLabel:'角色描述',
			name:'roleDesc',
			allowBlank : false
		}],
		fbar:[{
			xtype:'button',
			text:'提交',
			handler:submitForm
		},{
			xtype:'button',
			text:'取消',
			handler:function(){
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
		width :350,
		height : 230,
		closeAction:'hide',
		constrainHeader:true,
		plain:true,
		modal:true,
		items:[form]
	});
	
	

	//增加角色
	function addRoleInfo(){
		form.form.reset();
//		form.getForm().findField('roleId').setReadOnly (false);
//		form.getForm().findField('roleName').setReadOnly (false);
//		form.getForm().findField('roleDesc').setReadOnly (false);
		form.isAdd=true;
		win.setTitle('新增角色');
		win.show();
	};
	
//	//编辑角色
	function editRoleInfo(){
		var records=grid.getSelectionModel().getSelection();
		if(records.length==1){
			form.form.reset();
			form.isAdd=false;
			win.setTitle('修改角色');
			win.show();
			form.getForm().loadRecord(records[0]);
		}else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
	};
	
	//删除角色
	function deleteRoleInfo(){
		var records=grid.getSelectionModel().getSelection();
		if(records.length==0){
			top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
		}
		top.Ext.Msg.confirm('提示','您确定要删除所选的角色吗？',function(btnID){
			if(btnID=='yes'){
				deleteRoles(records);
			}
		});
	};
	//执行删除操作
	function deleteRoles(records){
		var roleIds="";
		for(var i=0;i<records.length;i++){
			var id=records[i].get('roleId');
			if(i==0){
				roleIds+=id;
			}else{
				roleIds=roleIds+','+id;
			}
		}
    	var msgTip=top.Ext.MessageBox.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除角色信息，请稍后...'
    	});
		Ext.Ajax.request({
    		url:'role_delete.action',
    		params:{roleIds:roleIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
    				for(var i=0;i<records.length;i++){
    					var index=roleStore.find('roleId',records[i].get('roleId'));
    					if(index!=-1){
    						var rec=roleStore.getAt(index);
    						roleStore.remove(rec);
    						grid.getView().refresh();
    					}
    				}
    				top.Ext.Msg.show({title:'提示', msg:'删除角色信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'删除角色信息失败！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}
    		}
    	});
	};
	var tree;
	var winForRight;
	//分配权限
	function allotRights(){
		var records=grid.getSelectionModel().getSelection();
		if(records.length==1){
			var roleId=records[0].get('roleId');
			var forTree;
			Ext.Ajax.request({
				url:'right_getAll.action',
				method:'POST',
				params:{roleId:roleId},
				dataType:'json', 
				async:false,
				success:function(response,options){
					forTree=response.responseText;
				}
			});
			var treeStore=new Ext.data.TreeStore(eval('('+forTree+')'));
			treeStore.sort('id','ASC');

			tree=Ext.create('Ext.tree.Panel', {
			    width: '100%',
			    height: '100%',
			    border:false,
			    margins : '-1 0 0 0',
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
			
			var form1=top.Ext.create('Ext.form.Panel',{
				border:false,
				bodyStyle: 'background:#dfe9f5',
				bodyPadding:15,
				width:'100%',
				height:'10%',
				defaults: {
		        	labelWidth:60,
		            anchor: '100%',
		            msgTarget:'qtip'
		        },
				items:[{
					xtype:'textfield',
					fieldLabel:'角色编号',
					name:'roleId',
					readOnly:true,
					allowBlank : false
				}]
			});
			winForRight = new top.Ext.Window({
				layout:'fit',
				title:'设置角色权限',
				width :350,
				height : 430,
				closeAction:'hide',
				constrainHeader:true,
				plain:true,
				modal:true,
				items:[tree]
			});
			winForRight.show();
			form.getForm().loadRecord(records[0]);
		}else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
	}
	//弹出框中，提交表单时的动作
	function submitForm(){
		if(form.form.isValid()){
	    	if(form.isAdd){//新增
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
					url:'role_add.action',
					method:'POST',
	    			success:function(form,action){
	    				win.hide();
						updateGrid(action.result.msg);
	    				top.Ext.Msg.show({title:'提示', msg:'新增角色成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
	    			},
	    			failure:function(form,action){
	    				top.Ext.Msg.show({title:'提示', msg:'新增角色失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
	    			}
	    		});
	    	}else{//修改
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
					url:'role_update.action',
					method:'POST',
					success:function(form,action){
						win.hide();
						updateGrid(action.result.msg);
						top.Ext.Msg.show({title:'提示', msg:'修改角色成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
					},
					failure:function(form,action){
						top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
					}
	    		});
	    	}
    	}
	};
	
	//新增、修改后更新前端的数据
    function updateGrid(roleId){
    	var values=form.form.getValues();
    	var index=roleStore.find('roleId',values['roleId']);
    	if(index!=-1){
    		var item = roleStore.getAt(index);
    		for(var fieldName=roleStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			roleId:roleId,
    			roleName:values['roleName'],
    			roleDesc:values['roleDesc']
    		},'role');
    		roleStore.add(rec);
    	}
    }
	Ext.create('Ext.container.Viewport',{
		width:document.body.clientWidth,
		height:document.body.clientHeight,
		bodyBorder:false,
		layout:'hbox',
		border:false,
		bodyStyle: 'background:#f0f0f0',
		items:[grid]
	});
	
	function saveRight(){
		var records=grid.getSelectionModel().getSelection();
		var roleId=records[0].get('roleId');
		var check=tree.getChecked();
		var ids=new Array();
		var rightIds;
		Ext.each(check,function(node){
			if(node.get('id')!=0){
				ids.push(node.get('id'));
			}
		});
		rightIds=ids.join(',');
		Ext.Ajax.request({
			url:'role_saveRight.action',
			method:'POST',
			params:{rightIds:rightIds,roleId:roleId},
			success:function(response,options){
				var result=Ext.decode(response.responseText);
    			if(result.success){
    				top.Ext.Msg.show({title:'提示', msg:'保存角色权限成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK,
    				fn: function(btnID){
	                    	if(btnID == 'ok'){
	                    		winForRight.close();
	                		}
                		}
    				});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'保存角色权限失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		}
		});
	}
});