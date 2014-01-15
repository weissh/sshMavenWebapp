Ext.require([
    'Ext.grid.*',
    'Ext.form.*',
    'Ext.data.*',
    'Ext.tip.QuickTipManager',
    'Ext.selection.CheckboxModel'
]);

Ext.onReady(function() {
    Ext.QuickTips.init();

    var getLocalStore = function() {
        return Ext.create('Ext.data.JsonStore', {
            pageSize:2,
            model: 'Company',
            data: Ext.grid.dummyData
        });
    };


    var user=new Ext.data.JsonStore({
        fields:['id','name'],
        data:[
            [1,'小闫'],
            [2,'小祖'],
            [3,'小蔡'],
            [4,'小宋'],
            [5,'小吴']
        ]
    });


    Ext.define('Company', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'RecordDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'ExecDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'PayWay'},
            {name: 'Currency'},
            {name: 'Money',type: 'float'},
			{name: 'CostCountry'},
			{name: 'CostProvince'},
			{name: 'CostAddress'},
			{name: 'CostUnitName'},
			{name: 'CostContactName'},
			{name: 'CostContactPosition'},
			{name: 'CostContactPhone'},
			{name: 'CostContactEmail'},
			{name: 'Use'},
			{name: 'Description'}
			
         ]
    });

    
    Ext.grid.dummyData = [
	['2006-01-01','2006-01-01','1','2','3','4','5','6','7','8','9','10','111@qq.com','12','13'],
	['2006-01-11','2006-01-11','1','2','3','4','5','6','7','8','9','10','111@qq.com','12','13']
    ];
    
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
			}, {xtype:'button',text:'查询',iconCls:'search'},
            
            '-', {text:'统计'},{xtype:'textfield',width:100,readOnly:true},'-','->',
            {xtype:'button',text:'新建',iconCls: 'cost_add',handler : addCostInfo},
            {xtype:'button',text:'修改',iconCls: 'cost_edit',handler :editCostInfo},
            {xtype:'button',text:'导入',iconCls: 'file_in'},
			{xtype:'button',text:'导出',iconCls: 'file_export'}]			        
    
    });
    
    var CostpersonInfo = Ext.create('Ext.grid.Panel', {
        width: document.body.clientWidth,
        height: document.body.clientHeight,
        disableSelection: false,
        renderTo:Ext.getBody(),
        store: getLocalStore(),

        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "记录时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'RecordDate'},
            {text: "支出日期", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'ExecDate'},
            {text: "支出方式", width: 120, sortable: true, dataIndex: 'PayWay'},
            {text: "币种", width: 120, sortable: true, dataIndex: 'Currency'},
            {text: "支出金额", width: 120, sortable: true,format: '$0,0', dataIndex: 'Money'},
			{text: "国家", width: 120, sortable: true, dataIndex: 'CostCountry'},
			{text: "省市", width: 120, sortable: true, dataIndex: 'CostProvince'},
			{text: "详细地址", width: 120, sortable: true, dataIndex: 'CostAddress'},
			{text: "相关单位名称", width: 120, sortable: true, dataIndex: 'CostUnitName'},
			{text: "联系人姓名", width: 120, sortable: true, dataIndex: 'CostContactName'},
			{text: "联系人职务", width: 120, sortable: true, dataIndex: 'CostContactPosition'},
			{text: "联系人电话", width: 120, sortable: true, dataIndex: 'CostContactPhone'},
			{text: "联系人邮箱", width: 120, sortable: true, dataIndex: 'CostContactEmail'},
			{text: "用途", width: 120, sortable: true, dataIndex: 'Use'},
			{text: "描述", width: 120, sortable: true, dataIndex: 'Description'}
        ],
        border:false,
        columnLines: true,
        tbar:[dr],
        dockedItems: [Ext.create('Ext.toolbar.Paging', {
            dock: 'bottom',
            store: getLocalStore()
        })]
        
    });
  
	CostpersonInfo.addListener('itemdblclick', click, this);

 	function click() {  //双击GRID的方法	
		var sm = CostpersonInfo.getSelectionModel();
		var record = sm.getSelection();
		//top.Ext.Msg.alert('错误', '请先选择要编辑的数据行！');
		form.form.reset();
    	form.isAdd=false;
    	win.setTitle('修改费用支出');
    	win.show();
		form.getForm().loadRecord(record[0]);
	 }

    
	var form = top.Ext.create('Ext.form.Panel', {
		labelWidth : 100,
		//frame : true,
		border:false,
		bodyStyle : 'background:#dfe9f5;padding:5px 5px 0',
		defaultType : 'textfield',
		defaults :{ //anchor : '95%',
			allowBlank : false
		},
		//fileUpload : true,
		items : [{
			xtype : 'datefield',
			fieldLabel : '支出日期 ',
			name : 'ExecDate',
			format : 'Y-m-d',
			emptyText : '支出日期...',
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'PayWay',
			name : 'PayWay',
			fieldLabel : '支出方式',
			emptyText : '支出方式...',
			forceSelection : true,
			store : new top.Ext.data.SimpleStore({
				fields : ['value', 'PayWay'],
				data : [['1', '1'],['高中', '高中'],['大专', '大专'],['大本', '大本'],['硕士', '硕士'],['博士', '博士']]
			}),
			valueField : 'value',
			displayField : 'PayWay',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'Currency',
			name : 'Currency',
			fieldLabel : '币种',
			emptyText : '币种...',
			forceSelection : true,
			store : new top.Ext.data.SimpleStore({
				fields : ['value', 'Currency'],
				data : [['2', '2'],['高中', '高中'],['大专', '大专'],['大本', '大本'],['硕士', '硕士'],['博士', '博士']]
			}),
			valueField : 'value',
			displayField : 'Currency',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			fieldLabel : '支出金额',
			emptyText : '支出金额...',
			name : 'Money',
			id :'Money',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'CostCountry',
			name : 'CostCountry',
			fieldLabel : '国家',
			emptyText : '国家...',
			forceSelection : true,
			store : new top.Ext.data.SimpleStore({
				fields : ['value', 'CostCountry'],
				data : [['4', '4'],['高中', '高中'],['大专', '大专'],['大本', '大本'],['硕士', '硕士'],['博士', '博士']]
			}),
			valueField : 'value',
			displayField : 'CostCountry',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'CostProvince',
			name : 'CostProvince',
			fieldLabel : '省份',
			emptyText : '省份...',
			forceSelection : true,
			store : new top.Ext.data.SimpleStore({
				fields : ['value', 'CostProvince'],
				data : [['5', '5'],['高中', '高中'],['大专', '大专'],['大本', '大本'],['硕士', '硕士'],['博士', '博士']]
			}),
			valueField : 'value',
			displayField : 'CostProvince',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			xtype : 'textarea',
			fieldLabel : '详细地址',
			emptyText : '详细地址...',
			name : 'CostAddress',
			id :'CostAddress',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '相关单位名称',
			emptyText : '相关单位名称...',
			name : 'CostUnitName',
			id :'CostUnitName',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人姓名',
			emptyText : '联系人姓名...',
			name : 'CostContactName',
			id :'CostContactName',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人职务',
			emptyText : '联系人职务...',
			name : 'CostContactPosition',
			id :'CostContactPosition',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人电话',
			emptyText : '联系人电话...',
			name : 'CostContactPhone',
			id :'CostContactPhone',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人邮箱',
			vtype:'email',
			vtext:'不是有效的邮箱地址',
			emptyText : '联系人邮箱...',
			name : 'CostContactEmail',
			id :'CostContactEmail',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'Use',
			name : 'Use',
			fieldLabel : '用途',
			emptyText : '用途...',
			forceSelection : true,
			store : new top.Ext.data.SimpleStore({
				fields : ['value', 'Use'],
				data : [['12', '12'],['高中', '高中'],['大专', '大专'],['大本', '大本'],['硕士', '硕士'],['博士', '博士']]
			}),
			valueField : 'value',
			displayField : 'Use',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			fieldLabel : '描述',
			emptyText : '描述...',
			name : 'Description',
			id :'Description',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		}],
		buttons:[{
			text:'提交'
		},{
			text:'取消',
			handler:function(){
				win.close();
			}
		}]
	});
	
	var win = new top.Ext.Window({
    	//layout : 'fit',
		width :350,
		height :480,
		overflowY:'auto',
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });

	//增加新费用支出
    function addCostInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	win.setTitle('新增费用支出');
    	//form.getForm().findField('Money').setReadOnly(false);
    	win.show();
    };
    
	//修改费用支出 
	function editCostInfo() {
    	var record=CostpersonInfo.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	win.setTitle('修改费用支出');
	    	//form.getForm().findField('Money').setReadOnly(true);	    	
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
	}

});