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
    

	//定义部门数据源，作为下拉列表的数据源
    var dept=new Ext.data.Store({
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
    
	//定义员工数据源，作为下拉列表的数据源
    var staff=new Ext.data.Store({
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
    	
    //定义角色数据源，作为下拉列表的数据源
    var role=new Ext.data.Store({
        model:roleForSelector,
        proxy:{
        	type:'ajax',
        	url:'role_getForSelector.action',
        	method:'POST',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'roleId'
        	}
        },
        listeners:{
        	load:function(store,records,options){
        		var rs=Ext.ModelMgr.create({
        			roleId:0,
        			roleName:"所有角色"
        		},'roleForSelector');
        		store.insert(0,rs);
        	}
        }
    });

    //定义数据源，作为下拉列表的数据源
    var position=new Ext.data.Store({
    	autoLoad: true,
        fields:['id','name'],
        data:[
            {'id':'普通员工','name':'普通员工'},
	        {'id':'部门经理','name':'部门经理'}
        ]
    });
    
    //定义婚姻情况数据源
    var maritalStatus=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'未婚','name':'未婚'},
	        {'id':'已婚','name':'已婚'},
	        {'id':'离异','name':'离异'},
	        {'id':'丧偶','name':'丧偶'}
        ]
    });
    
    //定义最高学历数据源
    var highestEdu=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'专科','name':'专科'},
	        {'id':'本科','name':'本科'},
	        {'id':'硕士研究生','name':'硕士研究生'},
	        {'id':'博士研究生','name':'博士研究生'}
        ]
    });
    
    //定义最高学位数据源
    var highestDegree=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'学士学位','name':'学士学位'},
	        {'id':'硕士学位','name':'硕士学位'},
	        {'id':'博士学位','name':'博士学位'}
        ]
    });
    
    //定义学制数据源
    var schoolSystem=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
//	        {'id':'单轨制','name':'单轨制'},
//	        {'id':'双轨制','name':'双轨制'},
//	        {'id':'分支制学制','name':'分支制学制'}
	        {'id':'三年','name':'三年'},
	        {'id':'四年','name':'四年'}
        ]
    });
    
    //定义民族据源
    var nationalities=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'汉族','name':'汉族'},
	        {'id':'满族','name':'满族'},
	        {'id':'阿昌族','name':'阿昌族'},
	        {'id':'白族','name':'白族'},
	        {'id':'保安族 ','name':'保安族 '},
	        {'id':'布朗族','name':'布朗族'},
	        {'id':'布依族','name':'布依族'},
	        {'id':'朝鲜族','name':'朝鲜族'},
	        {'id':'达斡尔族','name':'达斡尔族'},
	        {'id':'傣族','name':'傣族'},
	        {'id':'德昂族','name':'德昂族'},
	        {'id':'东乡族','name':'东乡族'},
	        {'id':'侗族','name':'侗族'},
	        {'id':'独龙族','name':'独龙族'},
	        {'id':'俄罗斯族','name':'俄罗斯族'},
	        {'id':'鄂伦春族','name':'鄂伦春族'},
	        {'id':'鄂温克族','name':'鄂温克族'},
	        {'id':'高山族 ','name':'高山族 '},
	        {'id':'仡佬族 ','name':'仡佬族 '},
	        {'id':'哈尼族 ','name':'哈尼族 '},
	        {'id':'哈萨克族','name':'哈萨克族'},
	        {'id':'赫哲族','name':'赫哲族'},
	        {'id':'回族 ','name':'回族 '},
	        {'id':'基诺族 ','name':'基诺族 '},
	        {'id':'京族 ','name':'京族 '},
	        {'id':'景颇族','name':'景颇族'},
	        {'id':'柯尔克孜族 ','name':'柯尔克孜族 '},
	        {'id':'拉祜族 ','name':'拉祜族 '},
	        {'id':'黎族 ','name':'黎族 '},
	        {'id':'傈僳族 ','name':'傈僳族 '},
	        {'id':'珞巴族 ','name':'珞巴族 '},
	        {'id':'毛南族','name':'毛南族'},
	        {'id':'门巴族','name':'门巴族'},
	        {'id':'蒙古族 ','name':'蒙古族 '},
	        {'id':'苗族','name':'苗族'},
	        {'id':'仫佬族 ','name':'仫佬族 '},
	        {'id':'仫佬族 ','name':'仫佬族 '},
	        {'id':'怒族','name':'怒族'},
	        {'id':'普米族 ','name':'普米族 '},
	        {'id':'羌族','name':'羌族'},
	        {'id':'撒拉族','name':'撒拉族'},
	        {'id':'畲族 ','name':'畲族 '},
	        {'id':'水族','name':'水族'},
	        {'id':'塔吉克族','name':'塔吉克族'},
	        {'id':'塔塔尔族','name':'塔塔尔族'},
	        {'id':'土家族','name':'土家族'},
	        {'id':'土族','name':'土族'},
	        {'id':'佤族 ','name':'佤族 '},
	        {'id':'维吾尔族 ','name':'维吾尔族 '},
	        {'id':'乌孜别克族','name':'乌孜别克族'},
	        {'id':'锡伯族 ','name':'锡伯族 '},
	        {'id':'瑶族','name':'瑶族'},
	        {'id':'彝族','name':'彝族'},
	        {'id':'裕固族','name':'裕固族'},
	        {'id':'藏族 ','name':'藏族 '},
	        {'id':'壮族 ','name':'壮族 '}
        ]
    });
    
    //定义政治面貌数据源
    var politicalStatus=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'中共党员','name':'中共党员'},
	        {'id':'预备党员','name':'预备党员'},
	        {'id':'共青团员','name':'共青团员'},
	        {'id':'群众','name':'群众'}
        ]
    });
    
    //定义性别数据源
    var gender=new Ext.data.Store({
        fieldLabel:30,
        autoLoad: true,
        fields:['id','name'],
        data:[
	        {'id':'男','name':'男'},
	        {'id':'女','name':'女'}
        ]
    });

    //定义员工数据源，作为表格数据源
    var staffStore = Ext.create('Ext.data.Store', {
        model: 'staff',
        pageSize:20,
      	remoteSort:true,
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
	staffStore.load({url:'staff_getAll.action',params:{start:0,limit:20}});
    
	staff.load();
	dept.load();
	role.load();
	//创建工具栏表单，作为grid的上工具栏
	var formForTbar=new Ext.form.FormPanel({
		border:false,
		width:'100%',
		tbar:[{
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
            	}
            }
        },{
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
            xtype:'combo',
            name:'roleCombo',
            store:role,
            fieldLabel:'<b>角色</b>',
            valueField:'roleId',
            displayField:'roleName',
            forceSelection:true,
            value:0,
            typeAhead:true,
            width:150,
            labelWidth:30,
            margins:'0 10 0 0',
            mode:'local'
        },
            {xtype:'button',text:'查询',iconCls:'search',listeners:{
            	click:function(){
            		var departmentId=formForTbar.getForm().findField('deptCombo').getValue();
            		var staffId=formForTbar.getForm().findField('staffCombo').getValue();
            		var roleId=formForTbar.getForm().findField('roleCombo').getValue();
            		staffStore.on('beforeload',function(store,options){
            			var new_params={departmentId:departmentId,staffId:staffId,roleId:roleId,query:'true'};
            			Ext.apply(staffStore.proxy.extraParams,new_params);
            		});
            		staffStore.reload();
            	}
            }},
            '-','->',
        	{xtype:'button',text:'新增',iconCls: 'user_add',handler:addStaffInfo},
            {xtype:'button',text:'修改',iconCls: 'user_edit',handler:editStaffInfo},
            {xtype:'button',text:'删除',iconCls: 'user_delete',handler:deleteStaffInfo},
            {xtype:'button',text:'导出',iconCls:'file_export',handler:exportStaffInfo}
		]
	});
	
	//创建员工表格
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
        tbar:[formForTbar],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
        	{text: "员工编号", width: 60, sortable: true,dataIndex: 'staffId'},
            {text: "用户名", width: 80, sortable: true,dataIndex: 'userName'},
            {text: "姓名", width: 100, sortable: true, dataIndex: 'staffName'},
            {text: "照片路径", width: 120, sortable: true,dataIndex: 'photo',hidden:true},
            {text: "部门编号", width: 120, sortable: true,dataIndex: 'departmentId',hidden:true},
            {
            	text: "部门", 
            	width: 100, 
            	sortable: true, 
            	dataIndex: 'departmentName'
        	},
            {
            	text: "职务",
            	width: 100, 
            	sortable: true, 
            	dataIndex: 'position'
        	},
            {text: "入职时间", width: 100, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'entryTime'},
            {text: "联系电话", width: 100, sortable: true, dataIndex: 'phone'},
            {text: "角色编号", width: 120, sortable: true, dataIndex: 'roleId',hidden:true},
            {
            	text: "角色", 
            	width: 100, 
            	sortable: true, 
            	dataIndex: 'roleName'
        	},
            {text: "密码", flex:2, sortable: true, dataIndex: 'password'},
            {
            	text: "性别", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'gender',
            	hidden:true
        	},
            {text: "年龄", width: 120, sortable: true, dataIndex: 'age',hidden:true},
            {text: "出生日期", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'birthday',hidden:true},
            {text: "民族", 
            width: 120, 
            sortable: true, 
            dataIndex: 'nationality',
            hidden:true},
            {
            	text: "政治面貌", 
            	width: 120, 
            	sortable: true, 
            	dataIndex: 'politicalStatus',
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
        bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: staffStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
    });
    grid.addListener('itemdblclick', editStaffInfo, this);
    
    //新增、修改员工表单
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
        	name:'staffId',
        	inputType:'int',
        	value:0,
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
                },
                {width:'33%',fieldLabel: '照片路径',name: 'photoPath',hidden:true}]
	            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '用户名',name: 'userName',allowBlank: false}, 
                    {
                    	width:'33%',
                    	fieldLabel: '部门',
                    	xtype: 'combo',
                    	name: 'departmentId',
                    	store:dept,
                    	valueField:'departmentId',
                    	displayField:'departmentName',
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
                		value:'普通员工',
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
                    {xtype: 'datefield',width:'33%',fieldLabel: '入职时间',name: 'entryTime',format : 'Y-m-d',value:'2011-12-12',allowBlank: true},
                    {
                    	width:'33%',
                    	fieldLabel: '角色',
                    	xtype: 'combo',
                    	name: 'roleId',
                    	store:role,
                    	valueField:'roleId',
                    	displayField:'roleName',
                    	allowBlank: false
                	},
                    {width:'33%',fieldLabel: '密码',name: 'password',margins:'0 4 0 0',value:'000000',allowBlank: false}
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
                    {
                    	width:'33%',
                    	fieldLabel: '性别',
                    	xtype: 'combo',
                    	name: 'gender',
                    	store:gender,
                    	valueField:'id',
                    	displayField:'name',
                    	mode:'local',
                    	value:'男',
                    	allowBlank: false
                	},{
                		xtype: 'datefield',
                		width:'33%',
                		fieldLabel: '出生日期',
                		name: 'birthday',
                		format:'Y-m-d',
                		margins:'0 4 0 0',
                		value:'2012-12-12',
                		allowBlank: false,
                		listeners:{
                			select:function(m,d){
                				var date=new Date();
                				var age=date.getYear()-d.getYear();
                				form.getForm().findField('age').setValue(age);
                			}
                		}
            		}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
	                    {width:'33%',fieldLabel: '年龄',name: 'age',readOnly:true,allowBlank: true},
	                    {width:'33%',
	                    fieldLabel: '民族',
	                    xtype: 'combo',
	                    name: 'nationality',
	                    store:nationalities,
                    	valueField:'id',
                    	displayField:'name',
	                    value:'汉族',
	                    allowBlank: true
	                    }, 
	                    {
                    	width:'33%',
                    	fieldLabel: '政治面貌',
                    	xtype: 'combo',
                    	name: 'politicalStatus',
                    	store:politicalStatus,
                    	valueField:'id',
                    	displayField:'name',
                    	margins:'0 4 0 0',
                    	value:'群众',
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
                    	value:'已婚',
                    	allowBlank: false
                	},
                    {width:'33%',fieldLabel: '籍贯',name: 'nativePlace',value:'123',allowBlank: false},
                    {width:'33%',fieldLabel: '身份证号',name: 'idNo',regex:/(^\d{17}([0-9]|[x,X])$)/,regexText:'请输入正确的身份证号码,18位最后一位可能是数字也可能是x',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'49%',fieldLabel: '护照号',name: 'passportNo',value:'123',allowBlank: true},
                    {width:'50%',fieldLabel: '户口地址',name: 'domicilePlace',margins:'0 4 5 0',value:'123',allowBlank: true}
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
                    {xtype: 'datefield',width:'33%',fieldLabel: '参加工作时间',name: 'dateOfRecruitment',format:'Y-m-d',value:'2012-12-12',allowBlank: true}, 
                    {
                    	width:'33%',
                    	fieldLabel: '最高学历',
                    	xtype: 'combo',
                    	hiddenName:'hightestEdu',
                    	name: 'hightestEdu',
                    	store:highestEdu,
                    	valueField:'id',
                    	displayField: 'name',
                    	value:'本科',
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
                    	value:'学士学位',
                    	allowBlank: true
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
                    {width:'33%',fieldLabel: '专业',name: 'major',value:'123',allowBlank: true}, 
                    {
                    	width:'33%',
                    	fieldLabel: '学制',
                    	xtype: 'combo',
                    	name: 'schoolSystem',
                    	store:schoolSystem,
                    	valueField:'id',
                    	displayField: 'name',
                    	margins:'0 3 0 0',
                    	value:'三年制',
                    	allowBlank: true
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
                    {width:'33%',fieldLabel: '手机号',name: 'phone',regex:/^1[\d]{10}$/,regexText:'手机号码必须是1开头的,后面跟10位数字结尾',value:'123',allowBlank: false}, 
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
                    {width:'33%',fieldLabel: '邮箱',name: 'email',value:'123',allowBlank: true},
                    {width:'33%',fieldLabel: '邮编',name: 'zipCode',regex:/^[1-9]{1}(\d){5}$/,regexText:'请输入正确的邮政编码',value:'123',allowBlank: true},
                    {width:'33%',fieldLabel: '紧急电话',name: 'ucPhone',regex:/^1[\d]{10}$/,regexText:'手机号码必须是1开头的,后面跟10位数字结尾',margins:'0 4 0 0',value:'123',allowBlank: false}
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
//			handler:function(){
//				win.close();
//			}
		}]
    });
    
    //新增、修改员工弹出框
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
    	var records=grid.getSelectionModel().getSelection();
		if (records.length==1) {
			form.form.reset();
			form.getForm().findField('password').setReadOnly (true);
	    	form.isAdd=false;
	    	win.setTitle('修改用户');
	    	win.show();
			form.getForm().loadRecord(records[0]);
			var photoPath=records[0].get('photo');
			form.getForm().findField('photoImg').setRawValue(photoPath);
			form.getForm().findField('photoPath').setValue(photoPath);
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
    	top.Ext.Msg.confirm('提示','您确定要删除所选用户吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteStaffs(records);
    		}
    	});
    };
    
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
    				top.Ext.Msg.show({title:'提示', msg:result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		}
    	});
    };
    
    //新增、修改员工时，向后台发送请求
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
	    				top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});	
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
    			photo:values['photo'],
    			staffName:values['staffName'],
    			departmentId:values['departmentId'],
    			department:values['department'],
    			position:values['position'],
    			entryTime:values['entryTime'],
    			phone:values['phone'],
    			roleId:values['roleId'],
    			role:values['role'],
    			userName:values['userName'],
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
    	staffStore.reload();
    };
    
    //导出部门信息到excel
    function exportStaffInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	var msg;
    	var staffIds="";
    	if(records.length==0){
    		msg="您确定要导出所有的员工信息吗？";
    	}else{
    		msg="您确定要导出所选的员工信息吗？";
    		for(var i=0;i<records.length;i++){
    			var id=records[i].get('staffId');
    			if(i==0){
    				staffIds+=id;
    			}else{
    				staffIds=staffIds+","+id;
    			}
    		}
    	}
    	top.Ext.Msg.confirm('提示',msg,function(btnID){
    		if(btnID='yes'){
    			window.location.href='byjx/staff_export.action?staffIds='+staffIds;
    		}
    	});
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
})