
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
	
	//定义分面参数
	//var gridUrl='../server/gridserver.jsp';//取数据的服务页
	//var start=0;//读取的起始行
	//var limit=10;//每次读取的行数
	
	var journal_store=new Ext.data.JsonStore({
		fields: [
		    {name:'WorkID',type:'string'},
            {name:'RecordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'StaffNo',type:'string'},
            {name:'StaffName',type:'string'},
	        {name:'ExecDate',type:'date',dateFormat:'Y-m-d'},
	        {name:'OperateMode',type:'string'},
	        {name:'UnitName',type:'string'},
	        {name:'Country',type:'string'},
	        {name:'Province',type:'string'},
	        {name:'Address',type:'string'},
	        {name:'ContactObject',type:'string'},
	        {name:'Level',type:'string'},
	        {name:'ContactWay',type:'string'},
	        {name:'ContactName',type:'string'},
	        {name:'ContactPosition',type:'string'},
	        {name:'ContactPhone',type:'string'},
	        {name:'ContactEmail',type:'string'},
	        {name:'StartTime',type:'date',dateFormat:'G:i:s'},
	        {name:'EndTime',type:'date',dateFormat:'G:i:s'},
	        {name:'JobContent',type:'string'}		
         ],
        data:[['500','2007-08-07','001002','小吴','2007-08-07','1','2','3','4','5','6','7','8','9','10','13110981234','111@qq.com','11:00:00','13:00:00','和客户谈地很好'],
              ['600','2008-01-24','001003','大蔡','2008-01-27','出差','天天钢材市场','中国','北京','朝阳区光明大厦B座','天天钢材市场','A级','见面会谈','严总','总经理','15510981234','222@qq.com','7:00:00','9:00:00','一笔订单']]
	
	});
	
	//定义下拉框中的字段变量
	var dept=new Ext.data.JsonStore({
        fieldLabel:30,
        fields:['id','name'],
        data:[
            [1,'财务部'],
            [2,'人力部'],
            [3,'销售部']
        ]
    });

    var user=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'小闫'],
            [2,'大祖'],
            [3,'大蔡'],
            [4,'小宋'],
            [5,'小吴']
        ]
    });
    
    var operatemode=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'出差'],
            [2,'汇报'],
            [3,'签合同'],
            [4,'询价']
        ]
    });
    
    var contactobject=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'安全食品有限公司'],
            [2,'泰格码有限公司'],
            [3,'天天钢材市场']
        ]
    });
    
    var level=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'A级'],
            [2,'B级'],
            [3,'C级'],
            [4,'D级'],
            [5,'E级']
        ]
    });
    
    var contactway=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'电话联系'],
            [2,'电邮联系'],
            [3,'信件联系'],
            [4,'见面会谈'],
            [5,'网络视频']
        ]
    });
    /*Ext.define('JournalModel', {
        extend: 'Ext.data.Model',
        fields: [       
            {name:'RecordDate',type:'date',dateFormat:'Y-m-d'},
            {name:'StaffName',type:'string'},
            {name:'StaffNo',type:'string'},
	        {name:'ExecDate',type:'date',dateFormat:'Y-m-d'},
	        {name:'OperateMode',type:'string'},
	        {name:'UnitName',type:'string'},
	        {name:'Country',type:'string'},
	        {name:'Province',type:'string'},
	        {name:'Address',type:'string'},
	        {name:'ContactObject',type:'string'},
	        {name:'Level',type:'string'},
	        {name:'ContactWay',type:'string'},
	        {name:'ContactName',type:'string'},
	        {name:'ContactPosition',type:'string'},
	        {name:'ContactPhone',type:'string'},
	        {name:'ContactEmail',type:'string'},
	        {name:'StartTime',type:'date',dateFormat:'G:i:s'},
	        {name:'EndTime',type:'date',dateFormat:'G:i:s'},
	        {name:'JobContent',type:'string'}		
         ]
    });
    	
	Ext.grid.dummyData =[['2007-08-07','001002','小吴','2007-08-07',
	    '1','2','3','4','5','6','7','8','9','10',
	    '13110981234','111@qq.com','11:00:00','13:00:00','和客户谈地很好']];
    
    var getLocalStore = function() {
        return Ext.create('Ext.data.ArrayStore', {
            pageSize:2,
            model: 'journalModel',
            data: Ext.grid.dummyData
        });
    };*/

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
				displayField : 'name',
			    width : 140,
				labelWidth : 30,
				store : dept,
				queryMode : 'local',
				typeAhead : true,
				margins : '0 10 0 0'
			}, {
				xtype : 'combo',
				fieldLabel : '<b>员工</b>',
				displayField : 'name',
				width : 140,
				labelWidth : 30,
				store : user,
				queryMode : 'local',
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
		store:journal_store,
		viewConfig:{
			forceFit:true
		},
		tbar:[dr],
		columns:[
		    Ext.create('Ext.grid.RowNumberer'),
		    {text: "日志编号", width: 70, sortable: true, align:'center', dataIndex: 'WorkID',hidden:true},
			{text: "记录时间", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'RecordDate'},
            {text: "员工编号", width: 70, sortable: true, align:'center', dataIndex: 'StaffNo'},
            {text: "员工姓名", width: 70, sortable: true, align:'center', dataIndex: 'StaffName'},
            {text: "工作日期", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'ExecDate'},
            {text: "工作方式", width: 90, sortable: true, align:'center', dataIndex: 'OperateMode'},
            {text: "单位名称", width: 120, sortable: true, align:'center', dataIndex: 'UnitName'},
            {text: "国家", width: 50, sortable: true, align:'center', dataIndex: 'Country'},
            {text: "省市", width: 50, sortable: true, align:'center', dataIndex: 'Province'},
            {text: "详细地址", width: 120, sortable: true, align:'center', dataIndex: 'Address'},
            {text: "客户/经销商", width: 120, sortable: true, align:'center', dataIndex: 'ContactObject'},
            {text: "重要级别", width: 60, sortable: true, align:'center', dataIndex: 'Level'},
            {text: "联系途径", width: 60, sortable: true, align:'center', dataIndex: 'ContactWay'},
            {text: "联系人姓名", width: 70, sortable: true, align:'center', dataIndex: 'ContactName'},
            {text: "联系人职务", width: 70, sortable: true, align:'center', dataIndex: 'ContactPosition'},
            {text: "联系人电话", width: 85, sortable: true, align:'center', dataIndex: 'ContactPhone'},
            {text: "联系人邮箱", width: 80, sortable: true, align:'center', dataIndex: 'ContactEmail'},
            {text: "开始时间", width: 70, sortable: true, renderer: Ext.util.Format.dateRenderer('G:i:s'), dataIndex: 'StartTime'},
            {text: "结束时间", width: 70, sortable: true, renderer: Ext.util.Format.dateRenderer('G:i:s'), dataIndex: 'EndTime'},
            {text: "商谈主要内容及结果", width: 120, sortable: true, dataIndex: 'JobContent'}],
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
					allowBlank : false,
					blankText : '不允许为空',
					msgTarget : 'qtip',
					labelWidth : 80
				},
				//frame : true,

				items : [{
					xtype : 'textfield',
					fieldLabel : '员工编号',
					name:'StaffNo'
					}, {
					xtype : 'textfield',
					fieldLabel : '员工姓名',
					name:'StaffName',
					readOnly : true
				}, {
					xtype : 'datefield',
					fieldLabel : '工作日期',
					maxValue : new Date(),
					vtype : 'daterange',
					mode : 'local',
					format : 'Y-m-d',
					name:'ExecDate'
				}, {
					xtype : 'combo',
					fieldLabel : '工作方式',
					displayField : 'name',
					store : operatemode,
					typeAhead : true,
					queryMode : 'local',
					name:'OperateMode'
				}, {
					xtype : 'textfield',
					fieldLabel : '单位名称',
					name:'UnitName'
				}, {
					xtype : 'combo',
					fieldLabel : '国家',
					displayField : 'name',
					store : dept,
					typeAhead : true,
					queryMode : 'local',
					name:'Country'
				}, {
					xtype : 'combo',
					fieldLabel : '省市',
					displayField : 'name',
					store : dept,
					typeAhead : true,
					queryMode : 'local',
					name:'Province'
				}, {
					xtype : 'textfield',
					fieldLabel : '详细地址',
					name:'Address'
				}, {
					xtype : 'combo',
					fieldLabel : '客户/经销商',
					displayField : 'name',
					store : contactobject,
					typeAhead : true,
					queryMode : 'local',
					name:'ContactObject'
				}, {
					xtype : 'combo',
					fieldLabel : '重要级别',
					displayField : 'name',
					store : level,
					typeAhead : true,
					queryMode : 'local',
					name:'Level'
				}, {
					xtype : 'combo',
					fieldLabel : '联系途径',
					displayField : 'name',
					store : contactway,
					typeAhead : true,
					queryMode : 'local',
					name:'ContactWay'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人姓名',
					name:'ContactName'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人职务',
					name:'ContactPosition'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人电话',
					name:'ContactPhone'
				}, {
					xtype : 'textfield',
					fieldLabel : '联系人邮箱',
					vtype : 'email',
					name:'ContactEmail'
				}, {
					xtype : 'timefield',
					fieldLabel : '开始时间',
					pickerMaxBeight : 80,
					increment : 60,
					format : 'G:i:s',
					name:'StartTime'
				}, {
					xtype : 'timefield',
					fieldLabel : '结束时间',
					pickerMaxBeight : 80,
					increment : 60,
					format : 'G:i:s',
					name:'EndTime'
				}, {
					xtype : 'textarea',
					fieldLabel : '工作（商谈）主要内容及结果（200字以内）',
					preventScrollbars : true,
					width : 250,
					name:'JobContent'
				}],
				buttons : [{
							text : '提交'
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
	    
    //增加新用户
    function addJournal(){   	
    	form.form.reset();
    	form.isAdd=true;
    	form.getForm().findField('StaffNo').setReadOnly(false);
    	win.setTitle('新增用户');
    	win.show();
    };
    
    //修改用户
    function editJournal(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	form.getForm().findField('StaffNo').setReadOnly(true);
	    	win.setTitle('修改用户');
	    	win.show();
			form.getForm().loadRecord(record[0]);			
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    //双击进行修改
    grid.addListener('itemdblclick', editJournal, this);
    
    //删除用户
    function deleteJournal(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	Ext.Msg.confirm('提示','您确定要删除所选用户吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteJRecords(records)
    		}
    	});
    };
    
    function deleteJRecords(records){
    	var userID=records.join(',');
//    	var msgTip=Ext.MessageBox.show({
//    		title:'提示',
//    		width:250,
//    		msg:'正在删除用户信息，请稍后...'
//    	});
    	for(var i=0;i<records.length;i++){
    		var index=journal_store.find('WorkID',records[i].get('WorkID'));
    		if(index!=-1){
    			var rec=journal_store.getAt(index);
    			journal_store.remove(rec);
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
    	
    };

});
