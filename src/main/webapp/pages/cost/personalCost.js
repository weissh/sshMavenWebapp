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

    //定义员工数据类型，作为下拉列表框
    Ext.define('staffForSelector', {
        extend: 'Ext.data.Model',
        fields:[
        	{name:'staffId'},
        	{name:'staffName'}
    	]
	});
	
 	//定义支出方式数据源
    var payWay=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'现金'},
	        {'id':'2','name':'银行转账'},
	        {'id':'3','name':'汇款'},
	        {'id':'4','name':'支票'},
	        {'id':'5','name':'本票'},
	        {'id':'6','name':'汇票'},
	        {'id':'7','name':'汇兑'}
        ]
    });
    
    //币种数据源
    var currency=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'人民币'},
	        {'id':'2','name':'美元'},
	        {'id':'3','name':'英镑'},
	        {'id':'4','name':'欧元'},
	        {'id':'5','name':'港元'},
	        {'id':'6','name':'加元'},
	        {'id':'7','name':'日元'},
	        {'id':'8','name':'澳大利亚元'},
	        {'id':'9','name':'瑞士法郎'},
	        {'id':'10','name':'法国法郎'},
	        {'id':'11','name':'德国马克'},
	        {'id':'12','name':'新西兰元'},
	        {'id':'13','name':'越南盾'},
	        {'id':'14','name':'泰铢'},
	        {'id':'15','name':'韩国元'},
	        {'id':'16','name':'卢森堡法郎'},
	        {'id':'17','name':'俄罗斯卢布'},
	        {'id':'18','name':'卢森堡法郎'},
	        {'id':'19','name':'马来西亚林吉特 '},
	        {'id':'20','name':'奥地利先令 '},
	        {'id':'21','name':'芬兰马克 '},
	        {'id':'22','name':'比利时法郎 '},
	        {'id':'23','name':'新加坡元'},
	        {'id':'24','name':'西班牙比塞塔'},
	        {'id':'25','name':'菲律宾比索'},
	        {'id':'26','name':'印尼盾 	'},
	        {'id':'27','name':'爱尔兰镑'},
	        {'id':'28','name':'印度卢比'}
        ]
    });
    
    //国家数据源
    var costCountry=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'中国'},
	        {'id':'2','name':'韩国'},
	        {'id':'3','name':'日本'},
	        {'id':'4','name':'朝鲜'},
	        {'id':'5','name':'蒙古'},
	        {'id':'6','name':'越南'},
	        {'id':'7','name':'老挝'},
	        {'id':'8','name':'柬埔寨'},
	        {'id':'9','name':'缅甸'},
	        {'id':'10','name':'泰国'},
	        {'id':'11','name':'马来西亚'},
	        {'id':'12','name':'新加坡'},
	        {'id':'13','name':'印度尼西亚'},
	        {'id':'14','name':'菲律宾'},
	        {'id':'15','name':'文莱'},
	        {'id':'16','name':'印度'}
        ]
    });
    
    //省份数据源
    var costProvince=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'安徽省'},
	        {'id':'2','name':'河北省'},
	        {'id':'3','name':'河南省'},
	        {'id':'4','name':'湖北省'},
	        {'id':'5','name':'湖南省'},
	        {'id':'6','name':'内蒙古'},
	        {'id':'7','name':'广东省'}
        ]
    });
    //用途数据源
    var usage1=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	        {'id':'1','name':'住宿'},
	        {'id':'2','name':'吃饭'},
	        {'id':'3','name':'坐车'},
	        {'id':'4','name':'其它'}
        ]
    });

    Ext.define('personalCost', {
        extend: 'Ext.data.Model',
        fields: [
       	    {name: 'costId', type: 'int'},
            {name: 'recordDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'staffId', type: 'int',mapping:'staff.staffId'},
            {name: 'staffName',type:'string',mapping:'staff.staffName'},
            {name: 'executeDate', type: 'date', dateFormat: 'Y-m-d'},
            {name: 'payWay',type:'string'},
            {name: 'currency',type:'string'},
            {name: 'money',type: 'float'},
			{name: 'costCountry',type:'string'},
			{name: 'costProvince',type:'string'},
			{name: 'costAddress',type:'string'},
			{name: 'costUnitName',type:'string'},
			{name: 'costContactName',type:'string'},
			{name: 'costContactPosition',type:'string'},
			{name: 'costContactPhone',type:'string'},
			{name: 'costContactEmail',type:'string'},
			{name: 'usage1',type:'string'},
			{name: 'description1',type:'string'}
			
         ]
    });

     //定义费用数据源，作为表格数据源
    var personalCostStore = Ext.create('Ext.data.Store', {
        model: 'personalCost',
        pageSize:20,
        proxy:{
        	type:'ajax',
        	url:'cost_getAllper.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'costId',
        		totalProperty:'totalProperty'
        	}
        }
    });
    
    //费用表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
	personalCostStore.load({url:'cost_getAllper.action',params:{start:0,limit:20}});
    
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
            else if (field.enddtField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = field.up('form').down('#' + field.enddtField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }

            return true;
        },

        daterangeText: 'Start date must be less than end date'

        
    });    
    
    //创建工具栏表单，作为grid的上工具栏
    var dr = Ext.create('Ext.form.Panel', {
        border:false,
        width:'100%',    
		tbar:[{
				xtype: 'datefield',
				name: 'startdt',
				itemId: 'startdt',
				fieldLabel: '<b>开始时间</b>',
				forceSelection:true,
				width:165,
				labelWidth:60,
				allowBlank: false,
				maxValue: new Date(),
				vtype: 'daterange',
				enddtField: 'enddt', // id of the end date field
				format : 'Y-m-d',
				margins:'0 10 0 0',
				mode:'local'
			},{
				xtype: 'datefield',
				fieldLabel: '<b>结束时间</b>',
				width:165,
				labelWidth:60,
				allowBlank: false,
				maxValue: new Date(),
				forceSelection:true,
				name: 'enddt',
				itemId: 'enddt',
				vtype: 'daterange',
				startDateField: 'startdt', // id of the start date field
				margins:'0 10 0 0',
				format : 'Y-m-d',
				mode:'local'
			}, {xtype:'button',text:'查询',iconCls:'search',listeners:{
				click:function(){
					var startDate=dr.getForm().findField('startdt').getValue();
					var endDate=dr.getForm().findField('enddt').getValue();
            		personalCostStore.on('beforeload',function(store,options){
            			var new_params={startDate:startDate,endDate:endDate,query:'true'};
            			Ext.apply(personalCostStore.proxy.extraParams,new_params);
            		});
            		personalCostStore.reload();
            	}
            }},
            
            '-', {
            	text:'统计',
            	handler:function(){
            		//console.info(grid.getStore().getAt(0).getData());
            		var cout=0;
            		for(var i=0;i<grid.getStore().getCount();i++)
            		{
            			if(parseInt(grid.getStore().getAt(i).getData().currency)==1)
						cout+=parseInt(grid.getStore().getAt(i).getData().money);
            		}
            		Ext.getCmp("allCout").setValue(cout);
            	}
            },{xtype:'textfield',readOnly:true,width:100,id:"allCout"},'-','->',
            {xtype:'button',text:'新建',iconCls: 'cost_add',handler : addCostInfo},
            {xtype:'button',text:'修改',iconCls: 'cost_edit',handler :editCostInfo},
            {xtype:'filefield',buttonOnly: true,buttonText:'导入',buttonConfig:{iconCls:'file_in'}},
			{xtype:'button',text:'导出',iconCls: 'file_export'}]			        
    
    });
    //创建个人费用表格
    var grid = Ext.create('Ext.grid.Panel', {
        width: document.body.clientWidth,
        height: document.body.clientHeight,
        border:false,
        autoScroll:false,
        multiSelect: true,
//        columnLines: true,
        selModel:{selType:'checkboxmodel'},
        store: personalCostStore,
        viewConfig:{
            forceFit:true
        },
        tbar:[dr],
//        disableSelection: false,

        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "记录编号", width: 120, sortable: true, dataIndex: 'costId',hidden:true},
            {text: "记录时间", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'),dataIndex: 'recordDate'},
            {text: "支出日期", width: 120, sortable: true, renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'executeDate'},
            {text: "支出方式", width: 120, sortable: true, dataIndex: 'payWay',
            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=payWay.find('id',value);
            		var record=payWay.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
            {text: "币种", width: 120, sortable: true, dataIndex: 'currency',
            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=currency.find('id',value);
            		var record=currency.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
            {text: "支出金额", width: 120, sortable: true,format: '$0,0', dataIndex: 'money'},
			{text: "国家", width: 120, sortable: true, dataIndex: 'costCountry',
            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=costCountry.find('id',value);
            		var record=costCountry.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
			{text: "省市", width: 120, sortable: true, dataIndex: 'costProvince',hidden:true,
            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
            		var index=costProvince.find('id',value);
            		var record=costProvince.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
			{text: "详细地址", width: 120, sortable: true, dataIndex: 'costAddress',hidden:true},
			{text: "相关单位名称", width: 120, sortable: true, dataIndex: 'costUnitName'},
			{text: "联系人姓名", width: 120, sortable: true, dataIndex: 'costContactName'},
			{text: "联系人职务", width: 120, sortable: true, dataIndex: 'costContactPosition',hidden:true},
			{text: "联系人电话", width: 120, sortable: true, dataIndex: 'costContactPhone',hidden:true},
			{text: "联系人邮箱", width: 120, sortable: true, dataIndex: 'costContactEmail',hidden:true},
			{text: "用途", width: 120, sortable: true, dataIndex: 'usage1',hidden:true,
            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
                   		var index=usage1.find('id',value);
            		var record=usage1.getAt(index);
            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
            	}},
			{text: "描述", width: 120, sortable: true, dataIndex: 'description1',hidden:true}
        ], 
        bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: personalCostStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
    });
	grid.addListener('itemdblclick', editCostInfo, this);
	
	 //双击GRID的方法
 	function click() {  	
		var sm = grid.getSelectionModel();
		var record = sm.getSelection();
		//top.Ext.Msg.alert('错误', '请先选择要编辑的数据行！');
		form.form.reset();
    	form.isAdd=false;
    	win.setTitle('修改费用支出');
    	win.show();
		form.getForm().loadRecord(record[0]);
	 }

	//新增、修改个人费用表单
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
					xtype:'textfield',
					name:'costId',
					allowBlank : true,
					hidden:'true'
				},{
			xtype : 'datefield',
			fieldLabel : '支出日期 ',
			name : 'executeDate',
			format : 'Y-m-d',
			emptyText : '支出日期...',
			maxValue: new Date(),
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{width:'33%',
		 xtype : 'combo',
		 hiddenName : 'payWay',
		 fieldLabel : '支出方式',
		 emptyText : '支出方式...',
		 name : 'payWay',
		 forceSelection : true,
		 store:payWay,
		 valueField : 'id',
		 displayField : 'name',
		 mode : 'local',
		 value:'1',
		 allowBlank: false
		 },{
			xtype : 'combo',
			hiddenName : 'currency',
			name : 'currency',
			fieldLabel : '币种',
			emptyText : '币种...',
			forceSelection : true,
			store:currency,
			valueField : 'id',
			displayField : 'name',
			value:'1',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			fieldLabel : '支出金额',
			emptyText : '支出金额...',
			name : 'money',
			id :'money',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'costCountry',
			name : 'costCountry',
			fieldLabel : '国家',
			emptyText : '国家...',
			forceSelection : true,
			store :costCountry,
			valueField : 'id',
			displayField : 'name',
			value:'1',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'costProvince',
			name : 'costProvince',
			fieldLabel : '省份',
			emptyText : '省份...',
			forceSelection : true,
			store : costProvince,
			valueField : 'id',
			displayField : 'name',
			value:'1',
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
			name : 'costAddress',
			id :'costAddress',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '相关单位名称',
			emptyText : '相关单位名称...',
			name : 'costUnitName',
			id :'costUnitName',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人姓名',
			emptyText : '联系人姓名...',
			name : 'costContactName',
			id :'costContactName',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人职务',
			emptyText : '联系人职务...',
			name : 'costContactPosition',
			id :'costContactPosition',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			fieldLabel : '联系人电话',
			emptyText : '联系人电话...',
			name : 'costContactPhone',
			id :'costContactPhone',
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
			name : 'costContactEmail',
			id :'costContactEmail',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		},{
			xtype : 'combo',
			hiddenName : 'usage1',
			name : 'usage1',
			fieldLabel : '用途',
			emptyText : '用途...',
			forceSelection : true,
			store :usage1,
			valueField : 'id',
			displayField : 'name',
			value:'1',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
			//anchor : '95%'
		},{
			fieldLabel : '描述',
			emptyText : '描述...',
			name : 'description1',
			id :'description1',
			allowBlank : false,
			style : {
				color : 'blue'
			}
			//anchor : '95%'
		}],
		buttons:[{
				text:'提交',
				handler:submitForm
				},{
				text:'取消',
				handler:function(){
				win.close();
				var form1   = this.up('form').getForm(),
				encode = Ext.String.htmlEncode,
				 s  = '';
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
//    	form.getForm().findField('staffId').setReadOnly(false);
    	win.setTitle('新增费用支出');
    	//form.getForm().findField('Money').setReadOnly(false);
    	win.show();
    };
    
	//修改费用支出 
	function editCostInfo() {
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
//	    	form.getForm().findField('staffId').setReadOnly(true);
	    	win.setTitle('修改费用支出');
	    	//form.getForm().findField('Money').setReadOnly(true);	    	
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
	};
	
    //新增、修改费用时，向后台发送请求
    function submitForm(){
    	if(form.form.isValid()){
	    	if(form.isAdd){
	    		form.form.submit({
	    			waitMsg:'正在提交数据，请稍后...',
	    			waitTitle:'提示',
//	    			url:'cost_add.action',
	    			method:'POST',
	    			success:function(form,action){
	    				win.hide();
	    				updateGrid(action.result.msg);
	    				top.Ext.Msg.show({title:'提示', msg:'新增费用成功！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
	    			},
	    			failure:function(form,action){
	    				top.Ext.Msg.show({title:'提示', msg:'新增费用失败！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});	
	    			}
	    		});
	    	}else{
	    		form.form.submit({
		    		waitMsg:'正在提交数据，请稍后...',
					waitTitle:'提示',
//					url:'cost_update.action',
					method:'POST',
					success:function(form,action){
						win.hide();
						updateGrid(action.result.msg);
						top.Ext.Msg.show({title:'提示', msg:'修改费用成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
					},
					failure:function(form,action){
						top.Ext.Msg.show({title:'提示', msg:action.result.msg,icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
					}
	    		});
	    	}
    	}else{alert('验证不通过');}
    };
    
    //更新grid
    function updateGrid(costId){
    	var values=form.form.getValues();
    	var index=personalCostStore.find('costId',values['costId']);
    	if(index!=-1){
    		var item = personalCostStore.getAt(index);
    		for(var fieldName=personalCostStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			costId:costId,
    			recordDate:new Date(),
    			staffId:values['staffId'],
    			executeDate:values['executeDate'],
    			payWay:values['payWay'],
    			currency:values['currency'],
    			money:values['money'],
    			costCountry:values['costCountry'],
    			costProvince:values['costProvince'],
    			costAddress:values['costAddress'],
    			costUnitName:values['costUnitName'],
    			costContactPosition:values['costContactName'],
    			costContactPosition:values['costContactPosition'],
    			costContactPhone:values['costContactPhone'],
    			costContactEmail:values['costContactEmail'],
    			usage1:values['usage1'],
    			description1:values['description1']
    			
    		},'personalCost');
    		personalCostStore.add(rec);
    	}
    	personalCostStore.reload();
    };

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
});