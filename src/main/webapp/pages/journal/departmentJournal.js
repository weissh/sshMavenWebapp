
Ext.require([
    'Ext.form.*',
    'Ext.tip.QuickTipManager',
	'Ext.data.*',
	'Ext.grid.*',
    'Ext.panel.Panel',
    'Ext.view.View',
    'Ext.layout.container.Fit',
    'Ext.toolbar.Paging',
    'Ext.window.MessageBox',
    'Ext.tip.*'

]);

Ext.onReady(function() {
	
	Ext.tip.QuickTipManager.init();
	
//  ================================================================================
    var add=true;
	var update=true;
    var drop=true;
    var importExcel=true;
    var exportExcel=true; 

    if(roleName=='部门经理'||roleName=='人力部经理'||roleName=='人力部员工'){
	    exportExcel=false;
    }else{
    	add=false;
	    update=false;
	    drop=false;
	    importExcel=false;
	    exportExcel=false;
    }
	   
	// 定义部门数据类型，用于下拉列表
	Ext.define('deptForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'departmentId'},
        	{name:'departmentName'}
    	]
	})

    // 定义部门数据源，作为下拉列表的数据源
    var deptStore=new Ext.data.Store({
    	model:deptForSelector,  	
    	proxy:{
        	type:'ajax',
        	url:'dept_getForSelector.action',
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

    // 定义员工数据类型，作为下拉列表框
    Ext.define('staffForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'staffId'},
        	{name:'staffName'}
    	]
	});
	
	// 定义员工数据源，作为下拉列表的数据源
    var staffStore=new Ext.data.Store({
    	model:staffForSelector,
    	proxy:{
        	type:'ajax',
        	url:'staff_getForSelector.action',
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
    
    var country=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'CN','name':'中国'},
        {'id':'US','name':'美国'},
        {'id':'KOR','name':'韩国'},
        {'id':'GER','name':'德国'},
        {'id':'BRA','name':'巴西'},
        {'id':'IND','name':'印度'},
        {'id':'CAN','name':'加拿大'},
        {'id':'AU','name':'澳大利亚'},
        {'id':'JP','name':'日本'}
        ]
    });
    
    var province=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'CNBJ','name':'北京'},
        {'id':'CNTJ','name':'天津'},
        {'id':'CNSH','name':'上海'},
        {'id':'CNCQ','name':'重庆'},
        {'id':'CNHB','name':'河北'},
        {'id':'CNHN','name':'河南'},
        {'id':'CNYN','name':'云南'},
        {'id':'CNLN','name':'辽宁'},
        {'id':'CNHLJ','name':'黑龙江'},
        {'id':'CNHN2','name':'湖南'},
        {'id':'CNAH','name':'安徽'},
        {'id':'CNSD','name':'山东'},
        {'id':'CNXJ','name':'新疆'},
        {'id':'CNJS','name':'江苏'},
        {'id':'CNZJ','name':'浙江'},
        {'id':'CNJX','name':'江西'},
        {'id':'CNHB','name':'湖北'},
        {'id':'CNGX','name':'广西'},
        {'id':'CNGS','name':'甘肃'},
        {'id':'CNSX','name':'山西'},
        {'id':'CNNM','name':'内蒙'},
        {'id':'CNSX2','name':'陕西'},
        {'id':'CNJL','name':'吉林'},
        {'id':'CNFJ','name':'福建'},
        {'id':'CNGZ','name':'贵州'},
        {'id':'CNGD','name':'广东'},
        {'id':'CNQH','name':'青海'},
        {'id':'CNXZ','name':'西藏'},
        {'id':'CNSC','name':'四川'},
        {'id':'CNNX','name':'宁夏'},
        {'id':'CNHN','name':'海南'},
        {'id':'CNTW','name':'台湾'},
        {'id':'CNXG','name':'香港'},
        {'id':'CNAM','name':'澳门'}
        ]
    });
    var newProvince = new Ext.data.Store({fields: ['id', 'name']});
    
    var operatemode=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'电话'},
        {'id':'2','name':'会议'},
        {'id':'3','name':'走访'},
        {'id':'4','name':'加工'},
        {'id':'5','name':'安装'},
        {'id':'6','name':'修理'},
        {'id':'7','name':'其他'}
        ]
    });
    
    var contactobject=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'客户'},
        {'id':'2','name':'经销商'}
        ]
    });
    
    var level=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'1级'},
        {'id':'2','name':'2级'},
        {'id':'3','name':'3级'}
        ]
    });
    
    var contactway=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'打电话'},
        {'id':'2','name':'接电话'},
        {'id':'3','name':'发邮件'},
        {'id':'4','name':'回复邮件'},
        {'id':'5','name':'会议协商'}
        ]
    });
    
    // 定义表格的填充模型journal
	Ext.define('journal',{
		extend: 'Ext.data.Model',
		fields: [
		    {name:'workId',type:'string'},
            {name:'recordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'staffId',type:'int'},
            {name:'staffName',type:'string'},
	        {name:'executeDate',type:'date',dateFormat:'Y-m-d'},
	        {name:'operateMode',type:'string'},
	        {name:'unitName',type:'string'},
	        {name:'country',type:'string'},
	        {name:'province',type:'string'},
	        {name:'address',type:'string'},
	        {name:'contactObject',type:'string'},
	        {name:'level',type:'string'},
	        {name:'contactWay',type:'string'},
	        {name:'contactName',type:'string'},
	        {name:'contactPosition',type:'string'},
	        {name:'contactPhone',type:'string'},
	        {name:'contactEmail',type:'string'},
	        {name:'startTime',type:'string'},
	        {name:'endTime',type:'string'},
	        {name:'workContent',type:'string'}		
         ]
	})
	// 定义表格的填充数据
	 var journalStore = Ext.create('Ext.data.Store', {
        model: 'journal',
        pageSize:20,
        remoteSort:true,
        proxy:{
        	type:'ajax',
        	url:'jour_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'workId',
        		totalProperty:'totalProperty'
        	}
        }
    });
    // 日志表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
    journalStore.load({url:'jour_getAll.action',params:{start:0,limit:20}});
    //deptStore.load();
    //staffStore.load();

    // 添加日期控件的验证，保证结束日期在开始时期之后
    Ext.apply(Ext.form.field.VTypes, {
        daterange: function(val, field) {
            var date = field.parseDate(val);

            if (!date) {
                return false;
            }
            if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
                var start = field.up('form').down('#' + field.startDateField);
                start.setMaxValue(date);
                start.validate();
                this.dateRangeMax = date;
            }
            else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = field.up('form').down('#' + field.endDateField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }

            return true;
        },

        daterangeText: 'Start date must be less than end date'

        
    });    
    

	// 定义表单，用作表格的工具栏
    var dr = Ext.create('Ext.form.Panel', {
        border:false,
        width:'100%',    
		tbar:[{
				xtype: 'datefield',
				fieldLabel: '<b>开始时间</b>',
				width:170,
				labelWidth:60,
				allowBlank: false,
				maxValue: new Date(),
				name: 'startdt',
				itemId: 'startdt',
				vtype: 'daterange',
				endDateField: 'enddt', // id of the end date field
				format : 'Y-m-d',
				margins:'0 10 0 0',
				mode:'local'
			},{
				xtype: 'datefield',
				fieldLabel: '<b>结束时间</b>',
				width:170,
				labelWidth:60,
				allowBlank: false,
				maxValue: new Date(),
				name: 'enddt',
				itemId: 'enddt',
				vtype: 'daterange',
				startDateField: 'startdt', // id of the start date field
				format : 'Y-m-d',
				margins:'0 10 0 0',
				mode:'local'
			}, {
				xtype : 'combo',
				fieldLabel : '<b>部门</b>',
				valueField:'departmentId',
				displayField : 'departmentName',
			    width : 140,
				labelWidth : 30,
				store : deptStore,
				typeAhead : true,
				margins : '0 10 0 0',
				name : 'deptCombo',
				listeners:{
                    select : function(combo,record,index){
                    Ext.getCmp('staffCombo').reset();	
                    staffStore.load({
            			url:'staff_getForSelector.action',
            			params:{departmentId:combo.value}
            		});
                    }   
                }
			}, {
				xtype : 'combo',
				fieldLabel : '<b>员工</b>',
				valueField:'staffId',
				displayField : 'staffName',
				width : 140,
				labelWidth : 30,
				store : staffStore,
				typeAhead : true,
				margins : '0 10 0 0',
				name : 'staffCombo',
				id:'staffCombo'
			},
			{xtype:'button',text:'查询',iconCls: 'search', listeners:{
			    click:function(){
					var startDate=dr.getForm().findField('startdt').getValue();
					var endDate=dr.getForm().findField('enddt').getValue();
            		var departmentId=dr.getForm().findField('deptCombo').getValue();
            		var staffId=dr.getForm().findField('staffCombo').getValue();
            		journalStore.on('beforeload',function(store,options){
            			var new_params={startDate:startDate,endDate:endDate,departmentId:departmentId,staffId:staffId,query:'true'};
            			Ext.apply(journalStore.proxy.extraParams,new_params);
            		});
            		journalStore.reload();
            	}
			
			}},'-','->',
			{xtype:'button',text:'新建',iconCls: 'journal_add',handler : addJournal,hidden:add},
			{xtype:'button',text:'修改',iconCls: 'journal_edit',handler : editJournal,hidden:update},
			{xtype:'button',text:'删除',iconCls:'journal_delete',handler:deleteJournal,hidden:drop},
			{xtype:'filefield',buttonOnly: true,buttonText:'导入',buttonConfig:{iconCls:'file_in'},hidden:importExcel},
			{xtype:'button',text:'导出',iconCls:'file_export',handler:exportJournal,hidden:exportExcel}]
    
    });
   

    // 定义表格
	var grid=Ext.create('Ext.grid.Panel',{
		width:document.body.clientWidth,
		height:'100%',
		border:false,
		layout:'fit',
		store:journalStore,
		selModel:{selType:'checkboxmodel'},
		viewConfig:{
			forceFit:true
		},
		multiSelect:true,
		tbar:[dr],
		columns:[
		    Ext.create('Ext.grid.RowNumberer'),
		    {text: "日志编号", width: 70, sortable: true, align:'center', dataIndex: 'workId',hidden:true},
			{text: "记录时间", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'recordDate'},
            {text: "员工编号", width: 70, sortable: true, align:'center', dataIndex: 'staffId'},
            {text: "员工姓名", width: 70, sortable: true, align:'center', dataIndex: 'staffName'},
            {text: "工作日期", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'executeDate'},
            {text: "工作方式", width: 90, sortable: true, align:'center', dataIndex: 'operateMode'},
            {text: "单位名称", width: 120, sortable: true, align:'center', dataIndex: 'unitName'},
            {text: "国家", width: 80, sortable: true, align:'center', dataIndex: 'country'},
            {text: "省市", width: 70, sortable: true, align:'center', dataIndex: 'province'},
            {text: "详细地址", width: 120, sortable: true, align:'center', dataIndex: 'address'},
            {text: "客户/经销商", width: 120, sortable: true, align:'center', dataIndex: 'contactObject'},
            {text: "重要级别", width: 60, sortable: true, align:'center', dataIndex: 'level'},
            {text: "联系途径", width: 60, sortable: true, align:'center', dataIndex: 'contactWay'},
            {text: "联系人姓名", width: 70, sortable: true, align:'center', dataIndex: 'contactName'},
            {text: "联系人职务", width: 70, sortable: true, align:'center', dataIndex: 'contactPosition'},
            {text: "联系人电话", width: 85, sortable: true, align:'center', dataIndex: 'contactPhone'},
            {text: "联系人邮箱", width: 80, sortable: true, align:'center', dataIndex: 'contactEmail'},
            {text: "开始时间", width: 70, sortable: true, dataIndex: 'startTime'},
            {text: "结束时间", width: 70, sortable: true, dataIndex: 'endTime'},
            {text: "商谈主要内容及结果", width: 120, sortable: true, dataIndex: 'workContent'}],
		bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: journalStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	}) 
	
    
	var form = new top.Ext.FormPanel({
				width : '100%',
				height : '100%',
				overflowY:'auto',
				bodyStyle: 'background:#dfe9f5',
				bodyPadding : 10,
				border : false,
				defaults : {
					anchor: '100%',
					allowBlank : false,
					blankText : '不允许为空',
					msgTarget : 'qtip',
					labelWidth : 80
				},
				items : [{
					xtype:'textfield',
					name:'workId',
					allowBlank : true,
					hidden:'true'
				},{
					xtype : 'textfield',
					fieldLabel : '员工编号',
					name:'staffId',
					listeners:{ 
                        blur:function(tf) {
                        	var Id=tf.getRawValue();
                            Ext.Ajax.request({
                            	params:{staffId:Id},
                                url:'staff_getNameById',
                                method:'POST',
                                success:function(response,options){
                                	var responseArray = Ext.JSON.decode(response.responseText);
                                	if(responseArray.success==true){
                                        form.getForm().findField('staffName').setValue(responseArray.msg);
                                	}else{
                                		form.getForm().findField('staffName').reset();
                                		top.Ext.Msg.show({title:'提示', msg:responseArray.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
                                	}
                                },
                                failure:function(response,options){
                                	top.Ext.Msg.show({title:'提示', msg:'错误！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
                                }
                            })
					}}
				}, {
					xtype : 'textfield',
					fieldLabel : '员工姓名',
					name:'staffName',
					readOnly : true
				}, {
					xtype : 'datefield',
					fieldLabel : '工作日期',
					maxValue : new Date(),
					mode : 'local',
					format : 'Y-m-d',
					name:'executeDate',
					value:'2014-02-01'
				}, {
					xtype : 'combo',
					fieldLabel : '工作方式',
					valueField:'name',
					displayField : 'name',
					store : operatemode,
					typeAhead : true,
					name:'operateMode',
					value:1
				}, {
					xtype : 'textfield',
					fieldLabel : '单位名称',
					name:'unitName',
					value:'一个单位'
				}, {
					xtype : 'combo',
					fieldLabel : '国家',
					valueField:'name',
					displayField : 'name',
					store : country,
					typeAhead : true,
					name:'country',
					listeners:{        
                        select : function(combo, record, index){   
                        // 清除省市下拉框的现存值
                        var provinceField = form.getForm().findField('province');   
                        provinceField.setValue('');
                        newProvince.removeAll();
                        // 获取当前选择的国家代码，然后在province过滤出所有属于这个国家的省
                        var codeHead = record[0].get('id'); 
                        if(codeHead=='CN'){                      	
                            newProvince.add(province.getRange()); 
                        }
                    }}
				}, {
					xtype : 'combo',
					fieldLabel : '省市',
					valueField:'name',
					displayField : 'name',
					store : newProvince,
					typeAhead : true,
					blankText : '国外省份请自行填写',
					name:'province',
					mode:'local'
				}, {
					xtype : 'textfield',
					fieldLabel : '详细地址',
					name:'address',
					value:'朝阳区'
				}, {
					xtype : 'combo',
					fieldLabel : '客户/经销商',
					valueField:'name',
					displayField : 'name',
					store : contactobject,
					typeAhead : true,
					name:'contactObject',
					value:1
				}, {
					xtype : 'combo',
					fieldLabel : '重要级别',
					valueField:'name',
					displayField : 'name',
					store : level,
					typeAhead : true,
					name:'level',
					value:1
				}, {
					xtype : 'combo',
					fieldLabel : '联系途径',
					valueField:'name',
					displayField : 'name',
					store : contactway,
					typeAhead : true,
					name:'contactWay',
					value:1
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人姓名',
					name:'contactName',
					value:'吴大大'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人职务',
					name:'contactPosition',
					value:'副总经理'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人电话',
					name:'contactPhone',
					value:'13098319911'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人邮箱',
					// vtype : 'email',
					name:'contactEmail',
					value:'33@123.com'
				}, {
					xtype : 'timefield',
					fieldLabel : '开始时间',
					pickerMaxBeight : 80,
					increment : 30,
					format : 'G:i:s',
					name:'startTime',
					value:'7:00:00'
				}, {
					xtype : 'timefield',
					fieldLabel : '结束时间',
					pickerMaxBeight : 80,
					increment : 30,
					format : 'G:i:s',
					name:'endTime',
					value:'9:00:00'
				}, {
					xtype : 'textarea',
					fieldLabel : '工作（商谈）主要内容及结果（200字以内）',
					preventScrollbars : true,
					width : 250,
					name:'workContent',
					value:'A big deal!'
				}],
				buttons : [{
					text : '提交',
					handler:submitForm
				}, {
					text : '取消',
					handler : function() {
						win.close();
					}
				}]
			});
			
	var win = new top.Ext.Window({
    	layout : 'fit',
		width :380,
		height : 480,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
    
    // 增加日志
    function addJournal(){   	
    	form.form.reset();
    	form.isAdd=true;
    	form.getForm().findField('staffId').setReadOnly(false);
    	win.setTitle('新建日志');
    	win.show();
    };
    
    // 修改日志
    function editJournal(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			var recorddate=record[0].get("recordDate");
			form.form.reset();
	    	form.isAdd=false;
	    	form.getForm().findField('staffId').setReadOnly(true);
	    	win.setTitle('修改日志');
	    	win.show();
			form.getForm().loadRecord(record[0]);						
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    // 双击进行修改
    grid.addListener('itemdblclick', editJournal, this);
    
    // 删除日志
    function deleteJournal(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	Ext.Msg.confirm('提示','您确定要删除所选日志吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteJRecords(records)
    		}
    	});
    };
    
    // 执行删除操作
    function deleteJRecords(records){
    	var workIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('workId');
    		if(i==0){
    			workIds+=id;
    		}else{
    			workIds=workIds+','+id;
    		}
    	}
    	var msgTip =top.Ext.Msg.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除部门信息，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'jour_delete.action',
    		params:{workIds:workIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
			    	for(var i=0;i<records.length;i++){
			    		var index=journalStore.find('workId',records[i].get('workId'));
			    		if(index!=-1){
			    			var rec=journalStore.getAt(index);
			    			journalStore.remove(rec);
			    			grid.getView().refresh();
			    		}
			    	}
    				top.Ext.Msg.show({title:'提示', msg:'删除日志信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'删除日志信息失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
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
				url:'jour_add',
				method:'POST',
    			success:function(form,action){
    				win.hide();
					updateGrid(action.result.msg);
    				top.Ext.Msg.show({title:'提示', msg:'新增日志成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			},
    			failure:function(form,action){
    				top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		});
    	}else{
    		form.form.submit({
	    		waitMsg:'正在提交数据，请稍后...',
				waitTitle:'提示',
				url:'jour_update.action',
				method:'POST',
				success:function(form,action){
					win.hide();
					updateGrid(action.result.msg);
					top.Ext.Msg.show({title:'提示', msg:'修改日志成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
				},
				failure:function(form,action){
					top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
				}
    		});
    	}
    	}else{alert('验证不通过');}
    };
    
    function updateGrid(workId){
    	var values=form.form.getValues();
    	var index=journalStore.find('workId',values['workId']);
    	if(index!=-1){
    		var item = journalStore.getAt(index);
    		for(var fieldName=journalStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			workId:workId,
    			recordDate:new Date(),
                staffId:values['staffId'],
                staffName:values['staffName'],
	            executeDate:values['executeDate'],
	            operateMode:values['operateMode'],
	            unitName:values['unitName'],
	            country:values['country'],
	            province:values['province'],
	            address:values['address'],
	            contactObject:values['contactObject'],
	            level:values['level'],
	            contactWay:values['contactWay'],
	            contactName:values['contactName'],
	            contactPosition:values['contactPosition'],
	            contactPhone:values['contactPhone'],
	            contactEmail:values['contactEmail'],
	            startTime:values['startTime'],
	            endTime:values['endTime'],
	            workContent:values['workContent']		
    		},'journal');
    		journalStore.add(rec);
    	}
    	journalStore.reload();
    }
    
    // 用于渲染grid中的与form中下拉列表框对应的值，使其显示的是name字段而不是id字段
    function getText(record){
    	var text="";
		if(record==null){
			text=value;
		}else{
			text=record.data['name'];
		}
		return text;
    };
    
    //导出日志到excel
    function exportJournal(){
    	var records=grid.getSelectionModel().getSelection();
    	var msg;
    	var workIds="";
    	if(records.length==0){
    		msg="您确定要导出所有工作日志吗？";
    	}else{
    		msg="您确定要导出所选的工作日志吗？";
	    	for(var i=0;i<records.length;i++){
	    		var id=records[i].get('workId');
	    		if(i==0){
	    			workIds+=id;
	    		}else{
	    			workIds=workIds+','+id;
	    		}
			}
    	}
    	top.Ext.Msg.confirm('提示',msg,function(btnID){
    		if(btnID=='yes'){
    			window.location.href='byjx/jour_exportDept.action?workIds='+workIds;
    		}
		});
    }
   
});