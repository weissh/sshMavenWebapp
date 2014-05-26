
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

	// 定义表格的填充数据
	 var reportStore = Ext.create('Ext.data.Store', {
        model: 'report',
        pageSize:20,
        proxy:{
        	type:'ajax',
        	url:'jour_getAllReport.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'reportId',
        		totalProperty:'totalProperty'
        	}
        }
    });
    // 日志表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
    // reportStore.load({url:'jour_getAllReport.action',params:{start:0,limit:20}});

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
			},
			{xtype:'button',text:'查询',iconCls: 'search', listeners:{
			    click:function(){
					var startDate=dr.getForm().findField('startdt').getValue();
					var endDate=dr.getForm().findField('enddt').getValue();
            		reportStore.on('beforeload',function(store,options){
            			var new_params={startDate:startDate,endDate:endDate,query:'true'};
            			Ext.apply(reportStore.proxy.extraParams,new_params);
            		});
            		reportStore.reload();
            	}
			
			}},'-','->',
			{xtype:'button',text:'新建',iconCls: 'journal_add',handler : addreport},
			{xtype:'button',text:'修改',iconCls: 'journal_edit',id:"editt",handler : editreport},
			{xtype:'filefield',buttonOnly: true,buttonText:'导入',buttonConfig:{iconCls:'file_in'},hidden:true},
			{xtype:'button',text:'导出',iconCls:'file_export',handler:exportreport}]
    
    });
   

    // 定义表格
	var grid=Ext.create('Ext.grid.Panel',{
		width:document.body.clientWidth,
		height:'100%',
		border:false,
		layout:'fit',
		store:reportStore,
		selModel:{selType:'checkboxmodel'},
		viewConfig:{
			forceFit:true
		},
		multiSelect:true,
		tbar:[dr],
		columns:[
		    Ext.create('Ext.grid.RowNumberer'),
		    {text: "报告编号", width: 70, sortable: true, align:'center', dataIndex: 'reportId',hidden:true},
		    {text: "项目编号", width: 120, sortable: true, align:'center', dataIndex: 'projectNo'},
		    {text: "记录时间", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'recordDate'},
            {text: "出差日期", width: 80, sortable: true, align:'center', renderer: Ext.util.Format.dateRenderer('Y-m-d'), dataIndex: 'visitDate'},
            {text: "出差地点", width: 120, sortable: true, align:'center', dataIndex: 'visitPlace'},
            {text: "出差目的", width: 120, sortable: true, align:'center', dataIndex: 'visitAim'},
            {text: "对方联系", width: 120, sortable: true, align:'center', dataIndex: 'customer'},
            {text: "电话", width: 120, sortable: true, align:'center', dataIndex: 'phone'},
            {text: "邮箱", width: 120, sortable: true, align:'center', dataIndex: 'email'},
            {text: "员工编号", width: 70, sortable: true, align:'center', dataIndex: 'staffId',hidden:true},
            {text: "报告人", width: 70, sortable: true, align:'center', dataIndex: 'staffName'},
            {text: "部门", width: 70, sortable: true, align:'center', dataIndex: 'departmentName'},
            {text: "同行人", width: 70, sortable: true, align:'center', dataIndex: 'company'},
            {text: "车/机票", width: 70, sortable: true, align:'center', dataIndex: 'ticket'},
            {text: "住宿", width: 70, sortable: true, align:'center', dataIndex: 'accommodation'},
            {text: "当地交通", width: 70, sortable: true, align:'center', dataIndex: 'transportation'},
            {text: "餐费", width: 70, sortable: true, align:'center', dataIndex: 'meals'},
            {text: "差补", width: 70, sortable: true, align:'center', dataIndex: 'subsidy'},
            {text: "总计", width: 70, sortable: true, align:'center', dataIndex: 'sum'},
            {text: "人数", width: 70, sortable: true, align:'center', dataIndex: 'number'}
        ],
		bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: reportStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	// 双击进行修改
    grid.addListener('itemdblclick', editreport, this);
    // 单击进行判断是否可以修改
    grid.addListener('itemclick', enableupdate, this);
    function enableupdate(){
    	var judge=judgereport();
		if (judge){
			Ext.getCmp("editt").setDisabled(false);} 
		else{Ext.getCmp("editt").setDisabled(true);} 
	}	

    
	var form = new top.Ext.FormPanel({
				width : '100%',
				height : '100%',
				overflowY:'auto',
				bodyStyle: 'background:#dfe9f5',
				bodyPadding : 10,
				border : false,
				defaults : {
					anchor: '100%',
					allowBlank : true,
					blankText : '不允许为空',
					msgTarget : 'qtip',
					labelWidth : 80
				},
				items : [{
					xtype:'textfield',
					name:'reportId',
					allowBlank : true,
					hidden:'true'
				},{
		            xtype: 'fieldset',
		            title: '基本信息',
		            collapsible: false,
		            defaults: {
		                border: false,
		                bodyStyle: 'background:#dfe9f5',
		                layout: {
		                    type: 'hbox',
		                    defaultMargins: {top: 0, right: 5, bottom: 5, left: 0}
		                }
		            },
		            items: [{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'50%',
							xtype : 'textfield',
							fieldLabel : '项目编号',
							name:'projectNo'
						}, {
		                	width:'50%',
							xtype : 'datefield',
							fieldLabel : '填表日期',
							maxValue : new Date(),
							mode : 'local',
							format : 'Y-m-d',
							name:'recordDate'
						}]
			            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'32%',
							xtype : 'datefield',
							fieldLabel : '出差日期',
							maxValue : new Date(),
							mode : 'local',
							format : 'Y-m-d',
							name:'visitDate'
						}, {
		                	width:'68%',
							xtype : 'textfield',
							fieldLabel : '出差地点',
							name:'visitPlace'
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'100%',
							xtype : 'textfield',
							fieldLabel : '出差目的',
							name:'visitAim'
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [ {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '对方联系',
							name:'customer'
						}, {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '电话',
							name:'phone'
						}, {
		                	width:'34%',
							xtype : 'textfield',
							fieldLabel : '邮箱',
							name:'email'
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [ {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '报告人',
							name:'staffName'
						}, {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '部门',
							name:'departmentName'
						}, {
		                	width:'34%',
							xtype : 'textfield',
							fieldLabel : '同行人',
							name:'company'
						}]
		            }]
		        }, {
		            xtype: 'fieldset',
		            title: '出访内容',
		            collapsible: false,
		            defaults: {
		                border: false,
		                bodyStyle: 'background:#dfe9f5',
		                layout: {
		                    type: 'hbox',
		                    defaultMargins: {top: 0, right: 5, bottom: 5, left: 0}
		                }
		            },
		            items: [{
		                items: [{
		                	width:'5%',
							xtype : 'text',
							text : '编号',
							height : 18,
							style: {
								align :'center'
							}
						},{
		                	width:'30%',
							xtype : 'text',
							text : '处理事项简述',
							height : 18,
							style: {
								align :'center'
							}
						},{
		                	width:'65%',
							xtype : 'text',
							text : '总结摘要',
							height : 18
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                items: [{
		                	width:'5%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'30%',
							xtype : 'textarea',
							height :50
						},{
		                	width:'65%',
							xtype : 'textarea',
							height :50
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%'
		                },
		                items: [{
		                	width:'100%',
							xtype : 'htmleditor',
							fieldLabel : '项目编号',
							name:'projectNo'
						}]
		            }]
		        }, {
		            xtype: 'fieldset',
		            title: '出差费用',
		            collapsible: false,
		            defaults: {
		                border: false,
		                bodyStyle: 'background:#dfe9f5',
		                layout: {
		                    type: 'hbox',
		                    defaultMargins: {top: 0, right: 5, bottom: 5, left: 0}
		                }
		            },
		            items: [{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '车\机票',
							name:'ticket'
						}, {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '住宿',
							name:'accommodation'
						}, {
		                	width:'34%',
							xtype : 'textfield',
							fieldLabel : '当地交通',
							name:'transportation'
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '餐费',
							name:'meals'
						}, {
		                	width:'33%',
							xtype : 'textfield',
							fieldLabel : '补贴',
							name:'subsidy'
						}, {
		                	width:'34%',
							xtype : 'textfield',
							fieldLabel : '人数',
							name:'number'
						}]
		            },{
		                defaults: {
		                    labelWidth:64,
		                    anchor: '100%',
		                    xtype:'textfield'
		                },
		                items: [{
		                	width:'100%',
							xtype : 'textfield',
							fieldLabel : '总计',
							name:'sum'
						}]
		            }]
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
		width :680,
		height : 480,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
    
    // 增加日志
    function addreport(){   	
    	form.form.reset();
    	form.isAdd=true;
    	win.setTitle('新建访问报告');
    	win.show();
    };
    // 判断所选日志是否是当天提交的日志
    function judgereport(){
    	var record=grid.getSelectionModel().getSelection();
    	var recorddate=record[0].get("recordDate");
		var today=Ext.Date.clearTime(new Date());
    	return recorddate-today==0;
    }
    // 修改日志
    function editreport(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			var judge=judgereport();
			form.form.reset();
	    	form.isAdd=false;	    	
			form.getForm().loadRecord(record[0]);	
			if(judge){//当天的日志，设置只读为false
				win.setTitle('修改日志');
				form.getForm().getFields().each(function(field) {	        
		        field.setReadOnly(false);  
	            })
			}else{//非当天的日志，设置只读为true
				win.setTitle('查看日志');
				form.getForm().getFields().each(function(field) {		        
		        field.setReadOnly(true);  
	            })
			}		
	    	win.show();
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    // 删除日志
    function deletereport(){
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
    	var reportIds="";
    	for(var i=0;i<records.length;i++){
    		var id=records[i].get('reportId');
    		if(i==0){
    			reportIds+=id;
    		}else{
    			reportIds=reportIds+','+id;
    		}
    	}
    	var msgTip =top.Ext.Msg.show({
    		title:'提示',
    		width:250,
    		msg:'正在删除部门信息，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'jour_delete.action',
    		params:{reportIds:reportIds},
    		method:'POST',
    		success:function(response,options){
    			msgTip.hide();
    			var result=Ext.JSON.decode(response.responseText);
    			if(result.success){
			    	for(var i=0;i<records.length;i++){
			    		var index=reportStore.find('reportId',records[i].get('reportId'));
			    		if(index!=-1){
			    			var rec=reportStore.getAt(index);
			    			reportStore.remove(rec);
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
				url:'jour_addPer.action',
				method:'POST',
    			success:function(form,action){
    				win.hide();
					updateGrid(action.result.msg);
    				top.Ext.Msg.show({title:'提示', msg:'新增日志成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			},
    			failure:function(form,action){
    				top.Ext.Msg.show({title:'提示', msg:'新增日志失败，所填内容有误。',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		});
    	}else{			
    		var judge=judgereport();	
			if (judge){
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
					top.Ext.Msg.show({title:'提示', msg:'修改日志失败，所填内容有误。',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
				}})							
				}else{
				top.Ext.Msg.show({title:'提示', msg:'不可以修改非当天提交的日志！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
				}    		
    	}
    	}else{alert('验证不通过。请按提示正确填写表单，不可出现红色下划线。');}
    };
    
    function updateGrid(reportId){
    	var values=form.form.getValues();
    	var index=reportStore.find('reportId',values['reportId']);
    	if(index!=-1){
    		var item = reportStore.getAt(index);
    		for(var fieldName=reportStore in values){
    			item.set(fieldName,values[fieldName]);
    		}
    		item.commit();
    	}else{
    		var rec =Ext.ModelMgr.create({
    			reportId:reportId,
    			recordDate:new Date(),
                staffId:values['staffId'],
                staffName:values['staffName'],
                departmentId:values['departmentId'],
                departmentName:values['departmentName'],
	            visitDate:values['visitDate'],
	            visitPlace:values['visitPlace'],
	            visitAim:values['visitAim'],
	            projectNo:values['projectNo'],
	            recordDate:['recordDate'],
	            customer:values['customer'],
	            phone:values['phone'],
	            email:values['email'],
	            company:values['company'],
	            ticket:values['ticket'],
	            accommodation:values['accommodation'],
	            transportation:values['transportation'],
	            meals:values['meals'],
	            subsidy:values['subsidy'],
	            sum:values['sum'],
	            number:values['number']
    		},'report');
    		reportStore.add(rec);
    	}
    	reportStore.reload();
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
    function exportreport(){
    	var records=grid.getSelectionModel().getSelection();
    	var msg;
    	var reportIds="";
    	if(records.length==0){
    		msg="您确定要导出所有工作日志吗？";
    	}else{
    		msg="您确定要导出所选的工作日志吗？";
	    	for(var i=0;i<records.length;i++){
	    		var id=records[i].get('reportId');
	    		if(i==0){
	    			reportIds+=id;
	    		}else{
	    			reportIds=reportIds+','+id;
	    		}
			}
    	}
    	top.Ext.Msg.confirm('提示',msg,function(btnID){
    		if(btnID=='yes'){
    			window.location.href='byjx/jour_exportPer.action?reportIds='+reportIds;
    		}
		});
    }
   
});