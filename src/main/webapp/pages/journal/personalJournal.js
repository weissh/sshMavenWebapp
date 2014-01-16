
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
	
	var store=new Ext.data.JsonStore({
		fields: [       
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
        data:[['2007-08-07','001002','小吴','2007-08-07','1','2','3','4','5','6','7','8','9','10','13110981234','111@qq.com','11:00:00','13:00:00','和客户谈地很好']]
	
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
				startDateField: 'startdt', 
				margins:'0 10 0 0',
				mode:'local'
			},
			{xtype:'button',text:'查询',iconCls:'search'},'-','->',
			{xtype:'button',id:'btn_1',text:'新建',iconCls:'journal_add',handler : addJournal},
			{xtype:'button',text:'修改',iconCls:'journal_edit',handler : editJournal},
			{xtype:'filefield',buttonOnly: true,iconCls:'journal_delete',buttonText:'导入'},
			{xtype:'button',text:'导出',iconCls:'file_export'}]
    
    });
   

    //定义表格
	var grid=Ext.create('Ext.grid.Panel',{
		width:document.body.clientWidth,
		height:'100%',
		border:false,
		layout:'fit',
		store:store,
		viewConfig:{
			forceFit:true
		},
		tbar:[dr],
		columns:[
		    Ext.create('Ext.grid.RowNumberer'),		    
			{text: "记录时间", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'RecordDate'},
            
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
	
	function addJournal() {
		var form = new top.Ext.FormPanel({
			labelWidth : 100,
			frame : true,
			bodyStyle : 'padding:5px 5px 0',
			//fileUpload : true,
			defaults:{
					allowBlank:false
					//width : '55%',
					//labelWidth : '40%'
					//anchor : '95%'
				},
		    items : [{
							xtype : 'datefield',
							fieldLabel : '工作日期',
							maxValue : new Date(),
							vtype : 'daterange',
							mode : 'local',
							format : 'Y-m-d'
							
						}, {

							xtype : 'combo',
							fieldLabel : '工作方式',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						}, {
							xtype:'textfield',
							fieldLabel: '单位名称'
						},{
							xtype : 'combo',
							fieldLabel : '国家',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						},{
							xtype : 'combo',
							fieldLabel : '省市',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						},{
							xtype:'textfield',
							fieldLabel: '详细地址'
						},{
							xtype : 'combo',
							fieldLabel : '客户/经销商',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						},{
							xtype : 'combo',
							fieldLabel : '重要级别',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						},{
							xtype : 'combo',
							fieldLabel : '联系途径',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local'
						},{
							xtype:'textfield',
							fieldLabel: '联系人姓名'
						},{
							xtype:'textfield',
							fieldLabel: '联系人职务'
						},{
							xtype:'textfield',
							fieldLabel: '联系人电话'
						},{
							xtype:'textfield',
							fieldLabel: '联系人邮箱',
							vtype:'email'
						},{
							xtype : 'timefield',
							fieldLabel : '开始时间',
							pickerMaxBeight:80,
							increment:60,
							format:'G:i:s'							
						},{
							xtype : 'timefield',
							fieldLabel : '结束时间',
							pickerMaxBeight:80,
							increment:60,
							format:'G:i:s'							
						},{
						    xtype:'textarea',
						    fieldLabel:'工作（商谈）主要内容及结果（200字以内）',
						    preventScrollbars:true,
						    width : 300,
							labelWidth : 80						
						},{
							xtype:'button',
							text:'保存',
							width:50,
							align:'center',
							margins:'0 10 0 50'
						},{
							xtype:'button',
							text:'取消',
							width:50,
							align:'center',
							handler : function() {
							win.close();
						    }
						}]					 
				});
		var win = new top.Ext.Window({
					title : '新建工作日志',
					//layout : 'fit',
					width :350,
					height : 450,
					//closeAction : 'hide',
					plain : true,
					modal : true,
					items : [form],
					overflowY:'auto'					
				});
		win.show();
	}
	function editJournal() {
		var sm = grid.getSelectionModel();
		if (sm.getCount() > 0) {
		var rows=sm.getSelection();
		//top.Ext.Msg.alert('错误', '选中了');
			showJournalInfoWin(rows);
		} else {
			top.Ext.Msg.alert('错误', '请先选择要编辑的数据行！');
		}
	}
	
	function showJournalInfoWin(rows) {
		//var dirtyColor = '#f60600';	
		var formPanel = new top.Ext.FormPanel({
			labelWidth : 100,
			frame : true,
			defaultType : 'textfield',
			bodyStyle : 'padding:5px 5px 0',
			defaults:{
					allowBlank:false
					//width : '55%',
					//labelWidth : '40%'
					//anchor : '95%'
				},
		    items : [{
							xtype : 'datefield',
							fieldLabel : '工作日期',
							maxValue : new Date(),
							vtype : 'daterange',
							mode : 'local',
							format : 'Y-m-d',
							value : rows[0].get('ExecDate')
						}, {
							xtype : 'combo',
							fieldLabel : '工作方式',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('OperateMode')
						}, {
							xtype:'textfield',
							fieldLabel: '单位名称',
							value : rows[0].get('UnitName')
						},{
							xtype : 'combo',
							fieldLabel : '国家',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('Country')
						},{
							xtype : 'combo',
							fieldLabel : '省市',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('Province')
						},{
							xtype:'textfield',
							fieldLabel: '详细地址',
							value : rows[0].get('Address')
						},{
							xtype : 'combo',
							fieldLabel : '客户/经销商',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('ContactObject')
						},{
							xtype : 'combo',
							fieldLabel : '重要级别',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('Level')
						},{
							xtype : 'combo',
							fieldLabel : '联系途径',
							displayField : 'name',
							store : dept,
							typeAhead : true,
							queryMode : 'local',
							value : rows[0].get('ContactWay')
						},{
							xtype:'textfield',
							fieldLabel: '联系人姓名',
							value : rows[0].get('ContactName')
						},{
							xtype:'textfield',
							fieldLabel: '联系人职务',
							value : rows[0].get('ContactPosition')
						},{
							xtype:'textfield',
							fieldLabel: '联系人电话',
							value : rows[0].get('ContactPhone')
						},{
							xtype:'textfield',
							fieldLabel: '联系人邮箱',
							vtype:'email',
							value : rows[0].get('ContactEmail')
						},{
							xtype : 'timefield',
							fieldLabel : '开始时间',
							pickerMaxBeight:80,
							increment:60,
							format:'G:i:s',
							value : rows[0].get('StartTime')
						},{
							xtype : 'timefield',
							fieldLabel : '结束时间',
							pickerMaxBeight:80,
							increment:60,
							format:'G:i:s',
							value : rows[0].get('EndTime')
						},{
						    xtype:'textarea',
						    fieldLabel:'工作（商谈）主要内容及结果（200字以内）',
						    preventScrollbars:true,
						    width : 300,
							labelWidth : 80,
							value : rows[0].get('JobContent')
						},{
							xtype:'button',
							text:'保存',
							width:50,
							align:'center',
							margins:'0 10 0 50'
						},{
							xtype:'button',
							text:'取消',
							width:50,
							align:'center',
							handler : function() {
							win.close();
						    }
						}]	
				});

		var win = new top.Ext.Window({
					title : '详细信息',
					//layout : 'fit',
					width : 350,
					height : 450,
					// closeAction : 'hide',
					overflowY:'auto',
					plain : true,
					modal : true,
					items : [formPanel]
					
				});
		win.show();
	}
	
    

});
