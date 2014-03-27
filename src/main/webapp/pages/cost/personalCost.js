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
	        {'id':'1','name':'支票'},
	        {'id':'2','name':'信用卡'},
	        {'id':'3','name':'现金'},
	        {'id':'4','name':'其他'}
        ]
    });
    
     
    //币种数据源
    var currency=new Ext.data.Store({
    	autoLoad: true,
        fieldLabel:30,
        fields:['id','name'],
        data:[
	         {'id':'CN','name':'人民币'},
        {'id':'US','name':'美元'},
        {'id':'KOR','name':'韩元'},
        {'id':'GER','name':'欧元'},
        {'id':'BRA','name':'雷亚尔'},
        {'id':'IND','name':'卢比'},
        {'id':'CAN','name':'加元'},
        {'id':'AU','name':'澳元'},
        {'id':'JP','name':'日元'}
        ]
    });
    
    //国家数据源
       var costCountry=new Ext.data.Store({
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
    
    //省份数据源
       var costProvince=new Ext.data.Store({
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
            {name: 'staffId', type: 'int'},
            {name: 'staffName',type:'string'},
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
			{name: 'description1',type:'string'},
			{name: 'departmentId',type:'int'}
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
            
            '-', {xtype:'button',
            	text:'统计',
            	listeners:{
            	click:function(){
            		var startDate=dr.getForm().findField('startdt').getValue();
					var endDate=dr.getForm().findField('enddt').getValue();
            		var new_params={startDate:startDate,endDate:endDate,query:'true'};
            		Ext.Ajax.request({
					url : 'cost_countper.action',
					method : 'POST',
					customer:'自定义属性',
					params : new_params,	
					success : function(response,o) {
						// 处理返回信息
						var result=Ext.JSON.decode(response.responseText);
//						if (result.success) {
//							window.top.location = result.msg;
//						} else {
//							top.Ext.Msg.show({title:'提示', msg:'用户名或密码错误！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});							
//						}
						
						Ext.getCmp("allCout").setValue(result);
					},
					failure : function() {
						//显示错误信息
						Ext.example.msg('登录失败','网络故障');
					}
				});
            		//console.info(grid.getStore().getAt(0).getData());
            		//alert(personalCostStore.getCount());
//            		var cout=0;
//            		for(var i=0;i<grid.getStore().getCount();i++)
//            		{
//            			if(parseInt(grid.getStore().getAt(i).getData().currency)==1)
//						cout+=parseInt(grid.getStore().getAt(i).getData().money);
//            		}
//            		Ext.getCmp("allCout").setValue(cout);
            	}}
            },{xtype:'textfield',readOnly:true,width:100,id:"allCout"},'-','->',
            {xtype:'button',text:'新建',iconCls: 'cost_add',handler : addCostInfo},
            {xtype:'button',text:'修改',iconCls: 'cost_edit',handler :editCostInfo},
            {xtype:'filefield',buttonOnly: true,buttonText:'导入',buttonConfig:{iconCls:'file_in'}},
			{xtype:'button',text:'导出',iconCls: 'file_export',handler:exportCostInfo}]			        
    
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
            {text: "支出方式", width: 120, sortable: true, dataIndex: 'payWay'
//            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//            		var index=payWay.find('id',value);
//            		var record=payWay.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
            	},
            {text: "币种", width: 120, sortable: true, dataIndex: 'currency'
//            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//            		var index=currency.find('id',value);
//            		var record=currency.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
            	},
            {text: "支出金额", width: 120, sortable: true,format: '$0,0', dataIndex: 'money'},
			{text: "国家", width: 120, sortable: true, dataIndex: 'costCountry'
//            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//            		var index=costCountry.find('id',value);
//            		var record=costCountry.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
            	},
			{text: "省市", width: 120, sortable: true, dataIndex: 'costProvince',hidden:true
//            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//            		var index=costProvince.find('id',value);
//            		var record=costProvince.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
            	},
			{text: "详细地址", width: 120, sortable: true, dataIndex: 'costAddress',hidden:true},
			{text: "相关单位名称", width: 120, sortable: true, dataIndex: 'costUnitName'},
			{text: "联系人姓名", width: 120, sortable: true, dataIndex: 'costContactName'},
			{text: "联系人职务", width: 120, sortable: true, dataIndex: 'costContactPosition',hidden:true},
			{text: "联系人电话", width: 120, sortable: true, dataIndex: 'costContactPhone',hidden:true},
			{text: "联系人邮箱", width: 120, sortable: true, dataIndex: 'costContactEmail',hidden:true},
			{text: "用途", width: 120, sortable: true, dataIndex: 'usage1',hidden:true
//            		renderer:function(value){//根据当前单元格的值，调用相应的store，并显示displayField；
//                   		var index=usage1.find('id',value);
//            		var record=usage1.getAt(index);
//            		return getText(record);//当combo的数据源为本地时，才能调用getText方法，并且数据源store只能有两个字段（id、name）
//            	}
            	},
			{text: "描述", flex:1, sortable: true, dataIndex: 'description1'}
        ], 
        bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: personalCostStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
    });
	grid.addListener('itemdblclick', editCostInfo, this);
	
//	 //双击GRID的方法
// 	function click() {  	
//		var sm = grid.getSelectionModel();
//		var record = sm.getSelection();
//		//top.Ext.Msg.alert('错误', '请先选择要编辑的数据行！');
//		form.form.reset();
//    	form.isAdd=false;
//    	win.setTitle('修改费用支出');
//    	win.show();
//		form.getForm().loadRecord(record[0]);
//	 }

	//新增、修改个人费用表单
	var form = top.Ext.create('Ext.form.Panel', {
		labelWidth : 100,
		overflowY:'auto',
		border:false,
		bodyPadding:10,
		bodyStyle : 'background:#dfe9f5',
		defaultType : 'textfield',
		defaults: {
			xtype:'textfield',
        	labelWidth:80,
            anchor: '100%',
            msgTarget:'qtip'
        },
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
			maxValue: new Date(),
			allowBlank : false
		},{
			xtype : 'combo',
			hiddenName : 'payWay',
			fieldLabel : '支出方式',
			name : 'payWay',
			store:payWay,
			valueField : 'name',
			displayField : 'name',
			mode : 'local',
			allowBlank: false
		 },{
			xtype : 'combo',
			hiddenName : 'currency',
			name : 'currency',
			fieldLabel : '币种',
			store:currency,
			valueField : 'name',
			displayField : 'name',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
		},{
			fieldLabel : '支出金额',
			name : 'money',
			allowBlank : false
		},{
			xtype : 'combo',
			fieldLabel : '国家',
			valueField:'name',
			displayField : 'name',
			store : costCountry,
			typeAhead : true,
			name : 'costCountry',
			listeners:{        
		        select : function(combo, record, index){   
		        // 清除省市下拉框的现存值
			        var provinceField = form.getForm().findField('costProvince');   
			        provinceField.setValue('');
			        newProvince.removeAll();
			        // 获取当前选择的国家代码，然后在province过滤出所有属于这个国家的省
			        var codeHead = record[0].get('id'); 
			        if(codeHead=='CN'){                      	
			            newProvince.add(costProvince.getRange()); 
			        }
    			}		
        	}
        },{
			xtype : 'combo',
			fieldLabel : '省市',
			valueField:'name',
			displayField : 'name',
			store : newProvince,
			typeAhead : true,
			name:'costProvince',
			mode:'local'
		},{
			xtype : 'textarea',
			fieldLabel : '详细地址',
			name : 'costAddress',
			allowBlank : false
		},{
			fieldLabel : '相关单位名称',
			name : 'costUnitName',
			allowBlank : false
		},{
			fieldLabel : '联系人姓名',
			name : 'costContactName',
			allowBlank : false
		},{
			fieldLabel : '联系人职务',
			name : 'costContactPosition',
			allowBlank : false
		},{
			fieldLabel : '联系人电话',
			name : 'costContactPhone',
			allowBlank : false
		},{
			fieldLabel : '联系人邮箱',
			vtype:'email',
			vtext:'不是有效的邮箱地址',
			name : 'costContactEmail',
			allowBlank : false
		},{
			xtype : 'combo',
			hiddenName : 'usage1',
			name : 'usage1',
			fieldLabel : '用途',
			store :usage1,
			valueField : 'name',
			displayField : 'name',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			selectOnFocus : true,
			allowBlank : false
		},{
			xtype : 'textarea',
			fieldLabel : '描述',
			name : 'description1',
			allowBlank : false
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
		width :380,
		height :480,
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
    	win.show();
    };
    
	//修改费用支出 
	function editCostInfo() {
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	win.setTitle('修改费用支出');
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
	    			url:'cost_addper.action',
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
					url:'cost_update.action',
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
	
    //导出部门费用信息到excel
    function exportCostInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	var msg;
    	var costIds="";
    	if(records.length==0){
    		msg="您确定要导出所有个人费用信息吗？";
    	}else{
    		msg="您确定要导出所选的个人费用信息吗？";
	    	for(var i=0;i<records.length;i++){
	    		var id=records[i].get('costId');
	    		if(i==0){
	    			costIds+=id;
	    		}else{
	    			costIds=costIds+','+id;
	    		}
			}
    	}
    	top.Ext.Msg.confirm('提示',msg,function(btnID){
    		if(btnID=='yes'){
    			window.location.href='byjx/costper_export.action?costIds='+costIds;
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
    
    
});