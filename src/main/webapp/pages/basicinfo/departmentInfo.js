Ext.onReady(function() {
	
	Ext.QuickTips.init();
	
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
    var dept = Ext.create('Ext.data.Store', {
        model: 'department',
        autoLoad: true,
        proxy:{
        	type:'ajax',
        	url:'dept_getAllByRole.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		totalProperty:'totalProperty',
        		idProperty:'departmentId'
        	}
        }
    });
//	dept.load({url:'dept_getAllByRole.action',params:{start:0,limit:20}});
    
	//定义员工数据类型
    Ext.define('staff', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'staffId', type: 'int'},
            {name: 'photo'},
            {name: 'photoImg'},
            {name: 'staffName'},
            //mapping 用于获取嵌套json中的摸个属性
            {name: 'departmentId', type: 'int'},
            {name: 'departmentName'},
            {name: 'position'},
            {name: 'entryTime', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'phone'},
            {name: 'roleId', type: 'int'},
            {name: 'roleName'},
            {name: 'password'},
            {name: 'gender'},
            {name: 'age', type: 'int'},
            {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'nationality'},
            {name: 'politicalStatus'},
            {name: 'maritalStatus'},
            {name: 'nativePlace'},
            {name: 'idNo'},
            {name: 'passportNo'},
            {name: 'domicilePlace'},
            {name: 'dateOfRecruitment', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'hightestEdu'},
            {name: 'hightestDegree'},
            {name: 'graduateSchool'},
            {name: 'major'},
            {name: 'schoolSystem'},
            {name: 'currentAddress'},
            {name: 'urgentContact'},
            {name: 'email'},
            {name: 'zipCode'},
            {name: 'ucPhone'}
         ]
    });
    
	//定义员工数据源，作为表格数据源
    var staffStore = Ext.create('Ext.data.Store', {
        model: 'staff',
        pageSize:20,
        proxy:{
        	type:'ajax',
        	url:'staff_getAllByRole.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'staffId',
        		totalProperty:'totalProperty'
        	}
        }
    });
    
    
	//员工表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
	staffStore.load({url:'staff_getAllByRole.action',params:{start:0,limit:20}});
    
   
	
	dept.load();
	//staff.load();
	
	
//            listeners:{
//            	select:function(combo,record,index){
//            		Ext.getCmp('staffCombo').reset();
//            		staff.load({
//            			url:'staff_getForSelector.action',
//            			params:{departmentId:combo.value}
//            		});
////            		staffStore.load({url:'staff_getAll.action',params:{start:0,limit:20}});
//            	}
//            }
//			},{
//				xtype:'button',
//				text:'查询',
//				iconCls:'search',listeners:{
//            	click:function(){
////            		var departmentId=pe.getForm().findField('deptCombo').getValue();
//            		var departmentId=dr.getForm().findField('deptCombo').getValue();
//            		dept_store.on('beforeload',function(store,options){
//            			var new_params={departmentId:departmentId,query:'true'};
//            			Ext.apply(dept_store.proxy.extraParams,new_params);
//            		});
//            		dept_store.reload();
//            	}
//            }
////				,listeners:{
////            	click:function(){
////            		var departmentId=dr.getForm().findField('deptCombo').getValue();
////            		dept_store.on('beforeload',function(store,options){
////            			var new_params={departmentId:departmentId,query:'true'};
////            			Ext.apply(dept_store.proxy.extraParams,new_params);
////            		});
////            		dept_store.reload();
////            	}
////            }
//			}]
//		}]
    
//    });
    //定义部门信息表
     var deptGrid=Ext.create('Ext.grid.Panel',{
     	title:'部门列表',
		width:'40%',
		height:'100%',
//		border:false,
		bodyStyle:'border-width:1 1 1 0',
		layout:'fit',
		margins:'-1 0 -1 0',
		store:dept,
		viewConfig:{
			forceFit:true
		},
		listeners:{
			itemclick:function(view,record, item, index, e, eOpts){
				Ext.getCmp("stafftext").setValue('');
				var records=deptGrid.getSelectionModel().getSelection();
				var departmentId=records[0].get('departmentId');
//				alert(departmentId);
				staffStore.on('beforeload',function(store,options){
	    			var new_params={departmentId:departmentId,staffName:'',query:'true'};
	    			Ext.apply(staffStore.proxy.extraParams,new_params);
	    		});
    			staffStore.reload();
			}
		},
		columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "部门编号", flex: 0.2, sortable: true, dataIndex: 'departmentId'},
            {text: "部门名称", flex: 0.2, sortable: true,dataIndex: 'departmentName'},
            {text: "部门经理", flex: 0.2, sortable: true, dataIndex: 'managerNo'},
            {text: "总员工数",flex: 0.2, sortable: true, dataIndex: 'totalStaff'},
            {
        		text: "成立时间", 
        		flex: 0.3,
        		sortable: true, 
        		renderer: Ext.util.Format.dateRenderer('Y-m-d'), 
        		dataIndex: 'createTime'
    		},
    		{text: "部门描述",flex: 0.5, sortable: true, dataIndex: 'description'}
        ],
        bbar:new Ext.PagingToolbar({
//        	border:false,
//        	bodyStyle:'border-width:1 1 0 0',
        	pageSize:20,
            store: dept,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	
     //创建工具栏表单，作为grid的上工具栏
    var pe = Ext.create('Ext.form.Panel', {
        border:false,
        width:'100%',  
		tbar:[{
            xtype:'textfield',
            id:'stafftext',
            name:'stafftext',
            //store:staff,
            fieldLabel:'<b>员工</b>',
            border:false,
            width:150,
            labelWidth:40,
            margins:'0 10 0 0',
            mode:'remote'
			},{
				xtype:'button',
				text:'查询',
				iconCls:'search',listeners:{
            	click:function(){
            		var staffName=Ext.getCmp("stafftext").getValue();
            		staffStore.on('beforeload',function(store,options){
            			var new_params={staffName:staffName,query:'true'};
            			Ext.apply(staffStore.proxy.extraParams,new_params);
            		});
            		staffStore.reload();
            	}
            }
			}]
		
    
    });
    //定义员工信息表
    var staffGrid=Ext.create('Ext.grid.Panel',{
		width:'59.9%',
		height:document.body.clientHeight,
		border:false,
		layout:'fit',
		store:staffStore,
		viewConfig:{
			forceFit:true
		},
		tbar:[pe],
		columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "工号", width: 120, sortable: true,dataIndex: 'staffId'},
            {text: "姓名", flex: 1, sortable: true, dataIndex: 'staffName'},
            {text: "照片路径", width: 120, sortable: true,dataIndex: 'photo',hidden:true},
            {text: "部门编号", width: 120, sortable: true,dataIndex: 'departmentId',hidden:true},
            {text: "部门", width: 120, sortable: true, dataIndex: 'departmentName',hidden:true},
            {text: "职务",width: 120, sortable: true, dataIndex: 'position'},
            {text: "入职时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'entryTime'},
            {text: "联系电话", width: 120, sortable: true, dataIndex: 'phone'},
            {text: "角色编号", width: 120, sortable: true, dataIndex: 'roleId',hidden:true},
            {text: "角色", width: 120, sortable: true, dataIndex: 'roleName',hidden:true},
            {text: "密码", width: 120, sortable: true, dataIndex: 'password',hidden:true},
            {text: "姓名", width: 120, sortable: true, dataIndex: '',hidden:true},
            {text: "性别", width: 120, sortable: true, dataIndex: 'gender', hidden:true},
            {text: "年龄", width: 120, sortable: true, dataIndex: 'age',hidden:true},
            {text: "出生日期", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'birthday',hidden:true},
            {text: "民族", width: 120, sortable: true, dataIndex: 'nationality',hidden:true},
            {text: "政治面貌", width: 120, sortable: true, dataIndex: 'politicalStatus', hidden:true},
            {text: "婚姻状况", width: 120, sortable: true, dataIndex: 'maritalStatus',hidden:true},
            {text: "籍贯", width: 120, sortable: true, dataIndex: 'nativePlace',hidden:true},
            {text: "身份证号", width: 120, sortable: true, dataIndex: 'idNo',hidden:true},
            {text: "护照号", width: 120, sortable: true, dataIndex: 'passportNo',hidden:true},
            {text: "户口地址", width: 120, sortable: true, dataIndex: 'domicilePlace',hidden:true},
            {text: "参加工作时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'dateOfRecruitment',hidden:true},
            {text: "最高学历", width: 120, sortable: true, dataIndex: 'hightestEdu',hidden:true},
            {text: "最高学位", width: 120, sortable: true, dataIndex: 'hightestDegree',hidden:true},
            {text: "毕业院校", width: 120, sortable: true, dataIndex: 'graduateSchool',hidden:true},
            {text: "专业", width: 120, sortable: true, dataIndex: 'major',hidden:true},
            {text: "学制", width: 120, sortable: true, dataIndex: 'schoolSystem',hidden:true},
            {text: "手机号", width: 120, sortable: true, dataIndex: 'phone',hidden:true},
            {text: "现居住地", width: 120, sortable: true, dataIndex: 'currentAddress',hidden:true},
            {text: "紧急联系人", width: 120, sortable: true, dataIndex: 'urgentContact',hidden:true},
            {text: "邮箱", width: 120, sortable: true, dataIndex: 'email',hidden:true},
            {text: "邮编", width: 120, sortable: true, dataIndex: 'zipCode',hidden:true},
            {text: "紧急电话", width: 120, sortable: true, dataIndex: 'ucPhone',hidden:true}
        ],
		 bbar:new Ext.PagingToolbar({
		 	border:true,
        	pageSize:20,
            store: staffStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	staffGrid.addListener('itemdblclick', showStaffInfo, this);
	
	//定义界面视图
    var viewPort = new Ext.container.Viewport({
    	layout:'hbox',
    	border:false,
		items:[deptGrid,staffGrid],
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
    
    function showStaffInfo(){
    	var form = top.Ext.create('Ext.form.Panel', {
	        width:'100%',
	        height:'100%',
	        autoScroll:true,
	        bodyPadding: 10,
	        border:false,
	        bodyStyle: 'background:#dfe9f5',
	        defaults: {
	            anchor: '100%',
	            msgTarget:'qtip'
	        },
	        items: [{
	        	xtype:'textfield',
	        	name:'userId',
	        	hidden:true
	        },{
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
	            items: [
	            	{
	                defaults: {
	                    labelWidth:64,
	                    anchor: '100%',
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '工号',name: 'staffId'}, 
	                    {width:'33%',fieldLabel: '部门',name: 'departmentName'},
	                    {width:'33%',fieldLabel: '职务',name: 'position',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    anchor: '100%',
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {xtype: 'datefield',width:'33%',fieldLabel: '入职时间',name: 'entryTime',format : 'Y-m-d'},
	                    {width:'33%',fieldLabel: '角色',name: 'roleName'},
	                    {width:'33%',fieldLabel: '密码',name: 'password',margins:'0 4 0 0'}
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
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '姓名',name: 'staffName'}, 
	                    {width:'33%',fieldLabel: '性别',name: 'gender'},
	                    {xtype: 'datefield',width:'33%',fieldLabel: '出生日期',name: 'birthday',format:'Y-m-d',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    anchor: '100%',
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '年龄',name: 'age'},
	                    {width:'33%',fieldLabel: '民族',name: 'nationality'}, 
	                    {width:'33%',fieldLabel: '政治面貌',name: 'politicalStatus',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    anchor: '100%',
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '婚姻状况',name: 'maritalStatus'},
	                    {width:'33%',fieldLabel: '籍贯',name: 'nativePlace'},
	                    {width:'33%',fieldLabel: '身份证号',name: 'idNo',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    anchor: '100%',
	                    readOnly:true,
	                    xtype:'textfield'
	                },
	                items: [
	                    {width:'49%',fieldLabel: '护照号',name: 'passportNo'},
	                    {width:'50%',fieldLabel: '户口地址',name: 'domicilePlace',margins:'0 4 5 0'}
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
	                    readOnly:true,
	                    anchor: '100%'
	                },
	                items: [
	                    {xtype: 'datefield',width:'33%',fieldLabel: '参加工作时间',name: 'dateOfRecruitment',format:'Y-m-d'}, 
	                    {width:'33%',fieldLabel: '最高学历',name: 'hightestEdu'},
	                    {width:'33%',fieldLabel: '最高学位',name: 'hightestDegree',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    xtype:'textfield',
	                    readOnly:true,
	                    anchor: '100%'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '毕业院校',name: 'graduateSchool'},
	                    {width:'33%',fieldLabel: '专业',name: 'major'}, 
	                    {width:'33%',fieldLabel: '学制',name: 'schoolSystem',margins:'0 3 0 0'}
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
	                    readOnly:true,
	                    anchor: '100%'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '手机号',name: 'phone'}, 
	                    {width:'33%',fieldLabel: '现居住地',name: 'currentAddress'}, 
	                    {width:'33%',fieldLabel: '紧急联系人',name: 'urgentContact',margins:'0 4 0 0'}
	                ]
	            },{
	                defaults: {
	                    labelWidth:64,
	                    xtype:'textfield',
	                    readOnly:true,
	                    anchor: '100%'
	                },
	                items: [
	                    {width:'33%',fieldLabel: '邮箱',name: 'email'},
	                    {width:'33%',fieldLabel: '邮编',name: 'zipCode'},
	                    {width:'33%',fieldLabel: '紧急电话',name: 'ucPhone',margins:'0 4 0 0'}
	                ]
	            }]
	        }],
	        buttons:[{
				text:'取消',
				handler:function(){
					win.close();
				}
			}]
	    });
	    
	    //新增、修改员工弹出框
	    var win = new top.Ext.Window({
	    	layout : 'fit',
			width :750,
			height : 475,
			closeAction:'hide',
			constrainHeader:true,
			plain : true,
			modal : true,
			items : form
	    });
	    
	    var record=staffGrid.getSelectionModel().getSelection();
	    if (record.length==1) {
			form.form.reset();
//			form.getForm().findField('password').setReadOnly (true);
	    	form.isAdd=false;
	    	win.setTitle('查询用户');
	    	win.show();
			form.getForm().loadRecord(record[0]);
		}
    }
});