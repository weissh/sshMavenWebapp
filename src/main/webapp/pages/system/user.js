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
	
    var dept=new Ext.data.Store({
    	autoLoad: true,
    	fields:[
        	{name:'departmentId'},
        	{name:'departmentName'}
    	],
    	proxy:{
        	type:'ajax',
        	url:'dept_getForSelector.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'departmentId'
        	}
        }
    });

    var user=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            ['1','小闫'],
            ['2','小祖'],
            ['3','小蔡'],
            ['4','小宋'],
            ['5','小吴']
        ]
    });

    var role=new Ext.data.Store({
        fields:['id','name'],
        data:[
            {'id':'1','name':'员工'},
	        {'id':'2','name':'部门经理'},
	        {'id':'3','name':'总经理'},
	        {'id':'4','name':'管理员'}
        ]
    });

    var position=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'1','name':'员工'},
	        {'id':'2','name':'部门经理'},
	        {'id':'3','name':'总经理'}
        ]
    });
    
    var maritalStatus=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'未婚'},
	        {'id':'2','name':'已婚'},
	        {'id':'3','name':'离异'},
	        {'id':'4','name':'丧偶'}
        ]
    });
    
    var highestEdu=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'专科'},
	        {'id':'2','name':'本科'},
	        {'id':'3','name':'硕士研究生'},
	        {'id':'4','name':'博士研究生'}
        ]
    });
    
    var highestDegree=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'学士学位'},
	        {'id':'2','name':'硕士学位'},
	        {'id':'3','name':'博士学位'}
        ]
    });
    
    var schoolSystem=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'单轨制'},
	        {'id':'2','name':'双轨制'},
	        {'id':'3','name':'分支制学制'}
        ]
    });
    
    var politicalStatus=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'中共党员'},
	        {'id':'2','name':'预备党员'},
	        {'id':'3','name':'共青团员'},
	        {'id':'4','name':'群众'}
        ]
    });
    
    var gender=new Ext.data.Store({
        fieldLabel:30,
        autoLoad: true,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'男'},
	        {'id':'2','name':'女'}
        ]
    });    
    Ext.define('staff', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'staffId', type: 'int'},
            {name: 'photoImg'},
            {name: 'staffName'},
            //mapping 用于获取嵌套json中的摸个属性
            {name: 'departmentId', type: 'int',mapping:'department.departmentId'},
            {name: 'position'},
            {name: 'entryTime', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'phone'},
            {name: 'roleId', type: 'int'},
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

    var staffStore = Ext.create('Ext.data.Store', {
        model: 'staff',
        autoLoad: true,
        proxy:{
        	type:'ajax',
        	url:'staff_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'userId'
        	}
        }
    });

    //创建用户表格
    var grid = Ext.create('Ext.grid.Panel', {
        width: document.body.clientWidth,
        height: document.body.clientHeight,
        border:false,
        autoScroll:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
        store: staffStore,
        viewConfig:{
            forceFit:true
        },
        tbar:[{
            xtype:'combo',
            store:dept,
            fieldLabel:'<b>部门</b>',
            displayField:'name',
            forceSelection:true,
        	valueField:'departmentId',
        	displayField:'departmentName',
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
        	{xtype:'button',text:'新增',iconCls: 'user_add',handler:addStaffInfo},
            {xtype:'button',text:'修改',iconCls: 'user_edit',handler:editStaffInfo},
            {xtype:'button',text:'删除',iconCls: 'user_delete',handler:deleteStaffInfo},
            {xtype:'button',text:'导出',iconCls:'file_export'}
        ],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "工号", width: 120, sortable: true,dataIndex: 'staffId'},
            {text: "姓名", flex: 1, sortable: true, dataIndex: 'staffName'},
            {text: "照片路径", width: 120, sortable: true,dataIndex: 'photoImg',hidden:true},
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
            {
            	text: "职务",
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'position',
            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=position.find('id',value);
            		var record=position.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}
        	},
            {text: "入职时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'entryTime'},
            {text: "联系电话", width: 120, sortable: true, dataIndex: 'phone'},
            {
            	text: "角色", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'roleId'
//            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//            		var index=role.find('id',value);
//            		var record=role.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
        	},
            {text: "密码", width: 120, sortable: true, dataIndex: 'password'},
            {text: "姓名", width: 120, sortable: true, dataIndex: '',hidden:true},
            {
            	text: "性别", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'gender',
            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=gender.find('id',value);
            		var record=gender.getAt(index);
            		return getText(record);
            	},
            	hidden:true
        	},
            {text: "年龄", width: 120, sortable: true, dataIndex: 'age',hidden:true},
            {text: "出生日期", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'birthday',hidden:true},
            {text: "民族", width: 120, sortable: true, dataIndex: 'nationality',hidden:true},
            {
            	text: "政治面貌", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'politicalStatus',
            	renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=politicalStatus.find('id',value);
            		var record=politicalStatus.getAt(index);
            		return getText(record);
            	},
            	hidden:true},
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
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        autoScroll:false,
	        store: staffStore,   // same user_store GridPanel is using
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
    
    grid.addListener('itemdblclick', editStaffInfo, this);
    
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
                    xtype:'textfield'
                },
                items: [{
                    xtype: 'filefield',
                    width:200,
                    emptyText: '请选择上传图片',
                    fieldLabel: '请上传图片',
                    name: 'photoImg',
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
                    {width:'33%',fieldLabel: '工号',xtype: 'combo',name: 'staffId',value:123,allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '部门',
                    	xtype: 'combo',
                    	name: 'departmentId',
                    	store:dept,
                    	valueField:'departmentId',
                    	displayField:'departmentName',
                    	value:17,
                    	mode:'local',
                    	allowBlank: false
                	},{
                		width:'33%',
                		fieldLabel: '职务',
                		xtype: 'combo',
                		name: 'position',
                		store:position,
                		valueField:'id',
                		displayField:'name',
                		margins:'0 4 0 0',
                		value:'1',
                		allowBlank: false
            		}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {xtype: 'datefield',width:'33%',fieldLabel: '入职时间',name: 'entryTime',format : 'Y-m-d',value:'2011-12-12',allowBlank: false},
                    {
                    	width:'33%',
                    	fieldLabel: '角色',
                    	xtype: 'combo',
                    	name: 'roleId',
                    	store:role,
                    	valueField:'id',
                    	displayField:'name',
                    	value:'1',
                    	allowBlank: false
                	},
                    {width:'33%',fieldLabel: '密码',name: 'password',margins:'0 4 0 0',value:'123',allowBlank: false}
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
                    {width:'33%',fieldLabel: '姓名',name: 'staffName',value:'123',allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '性别',
                    	xtype: 'combo',
                    	name: 'gender',
                    	store:gender,
                    	valueField:'id',
                    	displayField:'name',
                    	mode:'local',
                    	value:'1',
                    	allowBlank: false
                	},{
                		xtype: 'datefield',
                		width:'33%',
                		fieldLabel: '出生日期',
                		name: 'birthday',
                		format:'Y-m-d',
                		margins:'0 4 0 0',
                		value:'2012-12-12',
                		allowBlank: false
            		}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '年龄',name: 'age',value:12,allowBlank: false},
                    {width:'33%',fieldLabel: '民族',name: 'nationality',value:'123',allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '政治面貌',
                    	xtype: 'combo',
                    	name: 'politicalStatus',
                    	store:politicalStatus,
                    	valueField:'id',
                    	displayField:'name',
                    	margins:'0 4 0 0',
                    	value:'1',
                    	allowBlank: false
                	}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {
                    	width:'33%',
                    	fieldLabel: '婚姻状况',
                    	xtype: 'combo',
                    	name: 'maritalStatus',
                    	store:maritalStatus,
                    	valueField:'id',
                    	displayField: 'name',
                    	value:'1',
                    	allowBlank: false
                	},
                    {width:'33%',fieldLabel: '籍贯',name: 'nativePlace',value:'123',allowBlank: false},
                    {width:'33%',fieldLabel: '身份证号',name: 'idNo',margins:'0 4 0 0',value:'123',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'49%',fieldLabel: '护照号',name: 'passportNo',value:'123',allowBlank: false},
                    {width:'50%',fieldLabel: '户口地址',name: 'domicilePlace',margins:'0 4 5 0',value:'123',allowBlank: false}
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
                    {xtype: 'datefield',width:'33%',fieldLabel: '参加工作时间',name: 'dateOfRecruitment',format:'Y-m-d',value:'2012-12-12',allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '最高学历',
                    	xtype: 'combo',
                    	hiddenName:'hightestEdu',
                    	name: 'hightestEdu',
                    	store:highestEdu,
                    	valueField:'id',
                    	displayField: 'name',
                    	value:'1',
                    	allowBlank: false
                	},{	
                    	width:'33%',
                    	fieldLabel: '最高学位',
                    	xtype: 'combo',
                    	name: 'hightestDegree',
                    	store:highestDegree,
                    	valueField:'id',
                    	displayField: 'name',
                    	margins:'0 4 0 0',
                    	value:'1',
                    	allowBlank: false
                	}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '毕业院校',name: 'graduateSchool',value:'123',allowBlank: false},
                    {width:'33%',fieldLabel: '专业',name: 'major',value:'123',allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '学制',
                    	xtype: 'combo',
                    	name: 'schoolSystem',
                    	store:schoolSystem,
                    	valueField:'id',
                    	displayField: 'name',
                    	margins:'0 3 0 0',
                    	value:'1',
                    	allowBlank: false
                	}
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
                    {width:'33%',fieldLabel: '手机号',name: 'phone',value:'123',allowBlank: false}, 
                    {width:'33%',fieldLabel: '现居住地',name: 'currentAddress',value:'123',allowBlank: false}, 
                    {width:'33%',fieldLabel: '紧急联系人',name: 'urgentContact',margins:'0 4 0 0',value:'123',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'33%',fieldLabel: '邮箱',name: 'email',value:'123',allowBlank: false},
                    {width:'33%',fieldLabel: '邮编',name: 'zipCode',value:'123',allowBlank: false},
                    {width:'33%',fieldLabel: '紧急电话',name: 'ucPhone',margins:'0 4 0 0',value:'123',allowBlank: false}
                ]
            }]
        }],
        buttons:[{
			text:'提交',
			handler:submitForm
		},{
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
//			handler:function(){
//				win.close();
//			}
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
    function addStaffInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	//form.getForm().findField('email').setReadOnly (false);
    	win.setTitle('新增用户');
    	win.show();
    };
    
    //修改用户
    function editStaffInfo(){
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
    function deleteStaffInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	Ext.Msg.confirm('提示','您确定要删除所选用户吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteStaffs(records);
    		}
    	});
    }
    //执行删除操作
    function deleteStaffs(records){
    	var staffIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('staffId');
    		if(i==0){
    			staffIds+=id;
    		}else{
    			staffIds=staffIds+','+id;
    		}
    	}
    	var msgTip=top.Ext.MessageBox.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除员工信息，请稍后...'
    	});
    	Ext.Ajax.request({
    		url:'staff_delete.action',
    		params:{staffIds:staffIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
    				for(var i=0;i<records.length;i++){
    					var index=staffStore.find('staffId',records[i].get('staffId'));
    					if(index!=-1){
    						var rec=staffStore.getAt(index);
    						staffStore.remove(rec);
    						grid.getView().refresh();
    					}
    				}
    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		}
    	});
    };
    
    function submitForm(){
    	if(form.form.isValid()){
	    	if(form.isAdd){
	    		form.form.submit({
	    			waitMsg:'正在提交数据，请稍后...',
	    			waitTitle:'提示',
	    			url:'staff_add.action',
	    			method:'POST',
	    			success:function(form,action){
	    				win.hide();
	    				updateGrid(action.result.msg);
	    				top.Ext.Msg.show({title:'提示', msg:'新增员工成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
	    			},
	    			failure:function(form,action){
	    				top.Ext.Msg.show({title:'提示', msg:'新增员工失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});	
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
						updateGrid(action.result.msg);
						top.Ext.Msg.show({title:'提示', msg:'修改员工成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
					},
					failure:function(form,action){
						top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
					}
	    		});
	    	}
    	}
    };
    
    //更新grid
    function updateGrid(staffId){
    	var values=form.form.getValues();
    	var index=staffStore.find('staffId',values['staffId']);
    	if(index!=-1){
    		var item = staffStore.getAt(index);
    		for(var fieldName=staffStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			staffId:staffId,
    			photoImg:values['photoImg'],
    			staffName:values['staffName'],
    			departmentId:values['departmentId'],
    			position:values['position'],
    			entryTime:values['entryTime'],
    			phone:values['phone'],
    			roleId:values['roleId'],
    			password:values['password'],
    			gender:values['gender'],
    			age:values['age'],
    			birthday:values['birthday'],
    			nationality:values['nationality'],
    			politicalStatus:values['politicalStatus'],
    			maritalStatus:values['maritalStatus'],
    			nativePlace:values['nativePlace'],
    			idNo:values['idNo'],
    			passportNo:values['passportNo'],
    			domicilePlace:values['domicilePlace'],
    			dateOfRecruitment:values['dateOfRecruitment'],
    			hightestEdu:values['hightestEdu'],
    			hightestDegree:values['hightestDegree'],
    			graduateSchool:values['graduateSchool'],
    			major:values['major'],
    			schoolSystem:values['schoolSystem'],
    			currentAddress:values['currentAddress'],
    			urgentContact:values['urgentContact'],
    			email:values['email'],
    			zipCode:values['zipCode'],
    			ucPhone:values['ucPhone']
    		},'staff');
    		staffStore.add(rec);
    	}
    };
    
    //
    function getText(record){
    	var text="";
		if(record==null){
			text=value;
		}else{
			text=record.data['name'];
		}
		return text;
    }
});