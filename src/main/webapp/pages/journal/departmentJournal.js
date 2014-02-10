
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
	
	Ext.define('journal',{
		extend: 'Ext.data.Model',
		fields: [
		    {name:'workId',type:'string'},
            {name:'recordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'staffId',type:'int',mapping:'staff.staffId'},
            {name:'staffName',type:'string',mapping:'staff.staffName'},
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
	
	 var journalStore = Ext.create('Ext.data.Store', {
        model: 'journal',
        autoLoad: true,
        proxy:{
        	type:'ajax',
        	url:'jour_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'workId'
        	}
        }
    });
    
	
	//定义下拉框中的字段变量
    
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
        fields:['id','name'],
        data:[
        {'id':'1','name':'小闫'},
        {'id':'2','name':'大祖'},
        {'id':'3','name':'大蔡'},
        {'id':'4','name':'小宋'},
        {'id':'5','name':'小吴'}
        ]
    });
    
    var operatemode=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'出差'},
        {'id':'2','name':'汇报'},
        {'id':'3','name':'签合同'},
        {'id':'4','name':'询价'}
        ]
    });
    
    var contactobject=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'安全食品有限公司'},
        {'id':'2','name':'泰格码有限公司'},
        {'id':'3','name':'天天钢材市场'}
        ]
    });
    
    var level=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'A级'},
        {'id':'2','name':'B级'},
        {'id':'3','name':'C级'},
        {'id':'4','name':'D级'},
        {'id':'5','name':'E级'}
        ]
    });
    
    var contactway=new Ext.data.Store({
        fields:['id','name'],
        data:[
        {'id':'1','name':'电话联系'},
        {'id':'2','name':'电邮联系'},
        {'id':'3','name':'信件联系'},
        {'id':'4','name':'见面会谈'},
        {'id':'5','name':'网络视频'}
        ]
    });
    

    //添加日期控件的验证，保证结束日期在开始时期之后
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
    

	//定义表单，用作表格的工具栏
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
				margins:'0 10 0 0',
				mode:'local'
			}, {
				xtype : 'combo',
				fieldLabel : '<b>部门</b>',
				valueField:'departmentId',
				displayField : 'departmentName',
			    width : 140,
				labelWidth : 30,
				store : dept,
				typeAhead : true,
				margins : '0 10 0 0'
			}, {
				xtype : 'combo',
				fieldLabel : '<b>员工</b>',
				valueField:'id',
				displayField : 'name',
				width : 140,
				labelWidth : 30,
				store : user,
				typeAhead : true,
				margins : '0 10 0 0'
			},
			{xtype:'button',text:'查询',iconCls:'search'},'-','->',
			{xtype:'button',text:'新建',iconCls: 'journal_add',handler : addJournal},
			{xtype:'button',text:'修改',iconCls: 'journal_edit',handler : editJournal},
			{xtype:'filefield',buttonOnly: true,buttonText:'导入',buttonConfig:{iconCls:'file_in'}},
			{xtype:'button',text:'导出',iconCls:'file_export'},
			{xtype:'button',text:'删除',iconCls:'journal_delete',handler:deleteJournal}]
    
    });
   

    //定义表格
	var grid=Ext.create('Ext.grid.Panel',{
		width:document.body.clientWidth,
		height:'100%',
		border:false,
		layout:'fit',
		store:journalStore,
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
            {text: "国家", width: 50, sortable: true, align:'center', dataIndex: 'country'},
            {text: "省市", width: 50, sortable: true, align:'center', dataIndex: 'province'},
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

		}),
		renderTo:Ext.getBody()
	}) 
	

	var form = new top.Ext.FormPanel({
				width : '100%',
				height : '100%',
				autoScroll : true,
				bodyStyle: 'background:#dfe9f5',
				bodyPadding : 10,
				border : false,
				defaults : {
					// anchor: '100%',
					//allowBlank : false,
					//blankText : '不允许为空',
					//msgTarget : 'qtip',
					labelWidth : 80
				},
				//frame : true,

				items : [{
					xtype:'textfield',
					name:'workId',
					hidden:'true'
				},{
					xtype : 'textfield',
					fieldLabel : '员工编号',
					name:'staffId'
					
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
					valueField:'id',
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
					valueField:'departmentId',
					displayField : 'departmentName',
					store : dept,
					typeAhead : true,
					name:'country',
					value:1
				}, {
					xtype : 'combo',
					fieldLabel : '省市',
					valueField:'departmentId',
					displayField : 'departmentName',
					store : dept,
					typeAhead : true,
					name:'province',
					value:1
				}, {
					xtype : 'textfield',
					fieldLabel : '详细地址',
					name:'address',
					value:'朝阳区'
				}, {
					xtype : 'combo',
					fieldLabel : '客户/经销商',
					valueField:'id',
					displayField : 'name',
					store : contactobject,
					typeAhead : true,
					name:'contactObject',
					value:1
				}, {
					xtype : 'combo',
					fieldLabel : '重要级别',
					valueField:'id',
					displayField : 'name',
					store : level,
					typeAhead : true,
					name:'level',
					value:1
				}, {
					xtype : 'combo',
					fieldLabel : '联系途径',
					valueField:'id',
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
					vtype : 'email',
					name:'contactEmail',
					value:'33@123.com'
				}, {
					xtype : 'timefield',
					fieldLabel : '开始时间',
					pickerMaxBeight : 80,
					increment : 60,
					format : 'G:i:s',
					name:'startTime',
					value:'7:00:00'
				}, {
					xtype : 'timefield',
					fieldLabel : '结束时间',
					pickerMaxBeight : 80,
					increment : 60,
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
		width :300,
		height : 500,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
	     
     
    //增加日志
    function addJournal(){   	
    	form.form.reset();
    	form.isAdd=true;
    	form.getForm().findField('staffId').setReadOnly(false);
    	win.setTitle('新建日志');
    	win.show();
    };
    
    //修改日志
    function editJournal(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
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
    
    //双击进行修改
    grid.addListener('itemdblclick', editJournal, this);
    
    //删除日志
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
    
    //执行删除操作
    function deleteJRecords(records){
    	var journalIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('workId');
    		if(i==0){
    			journalIds+=id;
    		}else{
    			journalIds=journalIds+','+id;
    		}
    	}
    	var msgTip =top.Ext.Msg.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除部门信息，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'jour_delete.action',
    		params:{journalIds:journalIds},
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
    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			}else{
    				top.Ext.Msg.show({title:'提示', msg:'删除用户信息失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		}
    	});
    };
    
    function submitForm(){
    	//if(form.form.isValid()){
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
    	//}
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
    }

    
});
