Ext.Loader.setConfig({enabled: true});
Ext.require([
    'Ext.grid.*',
    'Ext.form.*',
    'Ext.data.*',
    'Ext.toolbar.Paging',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager',
    'Ext.selection.CheckboxModel'
]);

Ext.onReady(function(){
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

    var role=new Ext.data.ArrayStore({
        fields:['id','name'],
        data:[
            [1,'管理员'],
            [2,'员工'],
            [3,'部门经理']
        ]
    });

    Ext.define('user', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'staffName'},
            {name: 'staffNo', type: 'int'},
            {name: 'department'},
            {name: 'position'},
            {name: 'enterTime', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'phone'},
            {name: 'role'},
            {name: 'password'},
            {name: 'desc'}
         ]
    });

    Ext.grid.dummyData = [
        ['菜',1,'财务','总经理','2006-06-04','123213','总经理','123123'],
        ['菜',2,'财务','总经理','2001-04-03','123213','总经理','123123']
    ];

    var user_store = Ext.create('Ext.data.Store', {
        model: 'user',
        autoLoad: true,
        data : Ext.grid.dummyData
        //proxy:new Ext.ux.data.PagingMemoryProxy(Ext.grid.dummyData)
    });

    //创建表格
    var grid = Ext.create('Ext.grid.Panel', {
        width: document.body.clientWidth,
        height: document.body.clientHeight,
        border:false,
        autoScroll:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
        store: user_store,
        viewConfig:{
            forceFit:true
        },
        tbar:[{
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
            xtype:'combo',
            store:role,
            fieldLabel:'<b>角色</b>',
            displayField:'name',
            forceSelection:true,
            typeAhead:true,
            width:150,
            labelWidth:30,
            margins:'0 10 0 0',
            mode:'local'
        },
            {xtype:'button',text:'查询',iconCls:'search'},
            '-','->',
        	{xtype:'button',text:'新增用户',iconCls: 'user_add',handler:addUserInfo},
            {xtype:'button',text:'修改',iconCls: 'user_edit',handler:editUserInfo},
            {xtype:'button',text:'删除',iconCls: 'user_delete',handler:deleteUserInfo},
            {xtype:'button',text:'导出',iconCls:'file_export'}
        ],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "姓名", flex: 1, sortable: true, dataIndex: 'staffName'},
            {text: "工号", width: 120, sortable: true,dataIndex: 'staffNo'},
            {text: "部门", width: 120, sortable: true, dataIndex: 'department'},
            {text: "职务", width: 120, sortable: true, dataIndex: 'position'},
            {
        		text: "入职时间", 
        		width: 120, 
        		sortable: true, 
        		renderer: Ext.util.Format.dateRenderer('Y-m-d'), 
        		dataIndex: 'enterTime'
    		},
            {text: "联系电话", width: 120, sortable: true, dataIndex: 'phone'},
            {text: "角色", width: 120, sortable: true, dataIndex: 'role'},
            {text: "密码", width: 120, sortable: true, dataIndex: 'password'}
        ],
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        autoScroll:false,
	        store: user_store,   // same user_store GridPanel is using
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
//        listeners: {
//            'selectionchange': function(view, records) {
//                grid.down('#removeEmployee').setDisabled(!records.length);
//                alert(1);
//            }
//        },
        renderTo: Ext.getBody()
    });
    
    grid.addListener('itemdblclick', editUserInfo, this);
    
    //user_store.load({ params: { start: 0, limit: 2} })
    //创建表单
	var form = top.Ext.create('Ext.form.Panel', {
        width:'100%',
        height:'100%',
        autoScroll:true,
        bodyPadding: 10,
        border:false,
        bodyStyle: 'background:#dfe9f5',
        defaults: {
            anchor: '100%',
//            allowBlank:false,
//            blankText:'不允许为空',
            msgTarget:'qtip'
        },
        items: [{
            xtype: 'fieldset',
            title: '部门信息',
            collapsible: false,
            defaults: {
                border: false,
                bodyStyle: 'background:#dfe9f5',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 15, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [{
                    xtype: 'filefield',
                    emptyText: '请选择上传图片',
                    fieldLabel: '请上传图片',
                    name: 'photo-path',
                    buttonText: '浏览  ',
                    labelAlign:'left'
                }]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '工号',xtype: 'combo',name: 'staffNo',allowBlank: false}, 
                    {width:'33%',fieldLabel: '部门',xtype: 'combo',name: 'department',allowBlank: false},
                    {width:'33%',fieldLabel: '职务',xtype: 'combo',name: 'position',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {xtype: 'datefield',width:'33%',fieldLabel: '入职时间',name: 'enterTime',format : 'Y-m-d',allowBlank: false},
                    {width:'33%',fieldLabel: '角色',xtype: 'combo',name: 'role',allowBlank: false},
                    {width:'33%',fieldLabel: '密码',name: 'password',margins:'0 4 0 0',allowBlank: false}
                ]
            }]
        },{
            xtype: 'fieldset',
            title: '基本信息',
            collapsible: false,
            defaults: {
                border: false,
                bodyStyle: 'background:#dfe9f5',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 15, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '姓名',name: 'staffName',allowBlank: false}, 
                    {width:'33%',fieldLabel: '性别',xtype: 'combo',name: 'gender',allowBlank: false},
                    {width:'33%',fieldLabel: '年龄',xtype: 'combo',name: 'age',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {xtype: 'datefield',width:'33%',fieldLabel: '出生日期',name: 'birthday',format:'Y-m-d',allowBlank: false},
                    {width:'33%',fieldLabel: '民族',xtype: 'combo',name: 'nationality',allowBlank: false}, 
                    {width:'33%',fieldLabel: '政治面貌',xtype: 'combo',name: 'politicalStatus',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '婚姻状况',xtype: 'combo',name: 'maritalStatus',allowBlank: false},
                    {width:'33%',fieldLabel: '籍贯',xtype: 'combo',name: 'nativePlace',allowBlank: false},
                    {width:'33%',fieldLabel: '身份证号',name: 'IDNo',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'49%',fieldLabel: '护照号',name: 'passportNo',allowBlank: false},
                    {width:'50%',fieldLabel: '户口地址',name: 'domicilePlace',margins:'0 4 5 0',allowBlank: false}
                ]
            }]
        },{
            xtype: 'fieldset',
            title: '履历信息',
            collapsible: false,
            defaults: {
                border: false,
                bodyStyle: 'background:#dfe9f5',
                anchor: '100%',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 15, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '<fon size="10">参加工作时间</fon>',name: 'dateOfRecruitment',allowBlank: false}, 
                    {width:'33%',fieldLabel: '最高学历',xtype: 'combo',name: 'hightestEdu',allowBlank: false},
                    {width:'33%',fieldLabel: '最高学位',xtype: 'combo',name: 'hightestDegree',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '毕业院校',name: 'graduateSchool',allowBlank: false},
                    {width:'33%',fieldLabel: '专业',name: 'major',allowBlank: false}, 
                    {width:'33%',fieldLabel: '学制',name: 'schoolSystem',margins:'0 3 0 0',allowBlank: false}
                ]
            }]
        },{
            xtype: 'fieldset',
            title: '联系信息',
            collapsible: false,
            defaults: {
                border: false,
                bodyStyle: 'background:#dfe9f5',
                anchor: '100%',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 15, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '手机号',name: 'phone',allowBlank: false}, 
                    {width:'33%',fieldLabel: '现居住地',name: 'currentAddress',allowBlank: false}, 
                    {width:'33%',fieldLabel: '紧急联系人',name: 'urgentContact',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '邮箱',name: 'email',allowBlank: false},
                    {width:'33%',fieldLabel: '邮编',name: 'zipCode',allowBlank: false},
                    {width:'33%',fieldLabel: '紧急电话',name: 'UCPhone',margins:'0 4 0 0',allowBlank: false}
                ]
            }]
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
    
    var win = new top.Ext.Window({
    	layout : 'fit',
		width :750,
		height : 500,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
	    
    //增加新用户
    function addUserInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	//form.getForm().findField('email').setReadOnly (false);
    	win.setTitle('新增用户');
    	win.show();
    };
    
    //修改用户
    function editUserInfo(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
			//form.getForm().findField('email').setReadOnly (true);
	    	form.isAdd=false;
	    	win.setTitle('修改用户');
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    //删除用户
    function deleteUserInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	Ext.Msg.confirm('提示','您确定要删除所选用户吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteUsers(records)
    		}
    	});
    };
    //执行删除操作
    function deleteUsers(records){
    	var userID=records.join(',');
//    	var msgTip=Ext.MessageBox.show({
//    		title:'提示',
//    		width:250,
//    		msg:'正在删除用户信息，请稍后...'
//    	});
    	for(var i=0;i<records.length;i++){
    		var index=user_store.find('staffNo',records[i].get('staffNo'));
    		if(index!=-1){
    			var rec=user_store.getAt(index);
    			user_store.remove(rec);
    			//grid.getStore().reload();
    		}
    	}
//	    	Ext.Ajax.request({
//	    		url:'',
//	    		params:{userID:userID},
//	    		method:'POST',
//	    		success:function(response,options){
//	    			msgTip.hide();
//	    			var result=Ext.JSON.decode(response.responseText);
//	    			if(result.success){
//	    				for(var i=0;i<userList.lenght;i++){
//	    					var index=user_store.find('id',userList[i]);
//	    					if(index!=-1){
//	    						var rec=userList.getAt(index);
//	    						user_store.remove(rec);
//	    					}
//	    				}
//	    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
//	    			}else{
//	    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息失败！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
//	    			}
//	    		}
//	    	});
    };
    
    function submitForm(){
    	if(form.isAdd){
    		form.form.submit({
    			waitMsg:'正在提交数据，请稍后...',
    			waitTitle:'提示',
    			url:'staff_add.action',
    			method:'POST',
    			success:function(form,action){
    				win.hide();
    				Ext.Msg.alert('提示','新增员工成功！');
    			},
    			failure:function(form,action){
    				Ext.Msg.alert('提示','新增员工失败！')	
    			}
    		});
    	}else{
    		form.form.submit({
	    		waitMsg:'正在提交数据，请稍后...',
				waitTitle:'提示',
				url:'staff_update.action',
				method:'POST',
				success:function(form,action){
					win.hide();
					Ext.Msg.alert('提示','修改员工成功！');
				},
				failure:function(form,action){
					Ext.Msg.alert('提示','修改员工失败！');	
				}
    		});
    	}
    };
});