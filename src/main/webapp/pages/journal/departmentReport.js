Ext.require(['Ext.form.*', 'Ext.tip.QuickTipManager', 'Ext.data.*',
		'Ext.grid.*', 'Ext.panel.Panel', 'Ext.view.View',
		'Ext.layout.container.Fit', 'Ext.toolbar.Paging',
		'Ext.window.MessageBox', 'Ext.tip.*'

]);

Ext.onReady(function() {

	Ext.tip.QuickTipManager.init();

	// ================================================================================
	var add = true;
	var update = true;
	var drop = true;
	var importExcel = true;
	var exportExcel = true;
	var commit = true;
	var formtitle = '查看日志';

	if (roleName == '部门经理' || roleName == '行政人事部经理' || roleName == '行政人事员工'|| roleName == '总经理') {
		exportExcel = false;
	} else if(roleName == '管理员') {
		add = false;
		update = false;
		drop = false;
		importExcel = false;
		exportExcel = false;
		commit = false;
		formtitle = '修改日志';
	}

	// 定义部门数据源，作为下拉列表的数据源
	var deptStore = new Ext.data.Store({
		model : deptForSelector,
		proxy : {
			type : 'ajax',
			url : 'dept_getForSelector.action',
			reader : {
				type : 'json',
				root : 'infoList',
				idProperty : 'departmentId'
			}
		},
		listeners : {
			load : function(store, records, options) {
				var rs = Ext.ModelMgr.create({
							departmentId : 0,
							departmentName : "所有部门"
						}, 'deptForSelector');
				store.insert(0, rs);
			}
		}
	});

	// 定义员工数据源，作为下拉列表的数据源
	var staffStore = new Ext.data.Store({
		model : staffForSelector,
		proxy : {
			type : 'ajax',
			url : 'staff_getForSelector.action',
			reader : {
				type : 'json',
				root : 'infoList',
				idProperty : 'staffId'
			}
		},
		listeners : {
			load : function(store, records, options) {
				var rs = Ext.ModelMgr.create({
							staffId : 0,
							staffName : "所有员工"
						}, 'staffForSelector');
				store.insert(0, rs);
			}
		}
	});

	// 定义表格的填充数据
	var reportStore = Ext.create('Ext.data.Store', {
        model: 'report',
        pageSize:20,
        proxy:{
        	type:'ajax',
        	url:'report_getAll.action',
        	reader:{
        		type:'json',
        		root:'infoList',
        		idProperty:'reportId',
        		totalProperty:'totalProperty'
        	}
        }
    });
	// 日志表格数据源载入，默认为第一页前20条记录，当点击下一页（第二页）时参数自动改变为{start:20,limit:20}，store的pagesize为20时
    reportStore.load({url:'report_getAll.action',params:{start:0,limit:20}});
	deptStore.load();
	staffStore.load();

	// 添加日期控件的验证，保证结束日期在开始时期之后
	Ext.apply(Ext.form.field.VTypes, {
		daterange : function(val, field) {
			var date = field.parseDate(val);
			if (!date) {
				return false;
			}
			if (field.startDateField
					&& (!this.dateRangeMax || (date.getTime() != this.dateRangeMax
							.getTime()))) {
				var start = field.up('form').down('#' + field.startDateField);
				start.setMaxValue(date);
				start.validate();
				this.dateRangeMax = date;
			} else if (field.endDateField
					&& (!this.dateRangeMin || (date.getTime() != this.dateRangeMin
							.getTime()))) {
				var end = field.up('form').down('#' + field.endDateField);
				end.setMinValue(date);
				end.validate();
				this.dateRangeMin = date;
			}
			return true;
		},
		daterangeText : 'Start date must be less than end date'
	});

	// 定义表单，用作表格的工具栏
	var dr = Ext.create('Ext.form.Panel', {
		border : false,
		width : '100%',
		tbar : [{
			xtype : 'datefield',
			fieldLabel : '<b>开始时间</b>',
			width : 170,
			labelWidth : 60,
			allowBlank : false,
			maxValue : new Date(),
			name : 'startdt',
			itemId : 'startdt',
			vtype : 'daterange',
			endDateField : 'enddt', // id of the end date field
			format : 'Y-m-d',
			margins : '0 10 0 0',
			mode : 'local'
		}, {
			xtype : 'datefield',
			fieldLabel : '<b>结束时间</b>',
			width : 170,
			labelWidth : 60,
			allowBlank : false,
			maxValue : new Date(),
			name : 'enddt',
			itemId : 'enddt',
			vtype : 'daterange',
			startDateField : 'startdt', // id of the start date
										// field
			format : 'Y-m-d',
			margins : '0 10 0 0',
			mode : 'local'
		}, {
			xtype : 'combo',
			fieldLabel : '<b>部门</b>',
			valueField : 'departmentId',
			displayField : 'departmentName',
			width : 140,
			labelWidth : 30,
			store : deptStore,
			typeAhead : true,
			margins : '0 10 0 0',
			name : 'deptCombo',
			value : 0,
			listeners : {
				select : function(combo, record, index) {
					Ext.getCmp('staffCombo').reset();
					staffStore.load({
								url : 'staff_getForSelector.action',
								params : {
									departmentId : combo.value
								}
							});
				}
			}
		}, {
			xtype : 'combo',
			fieldLabel : '<b>员工</b>',
			valueField : 'staffId',
			displayField : 'staffName',
			width : 140,
			labelWidth : 30,
			store : staffStore,
			typeAhead : true,
			margins : '0 10 0 0',
			name : 'staffCombo',
			id : 'staffCombo',
			value : 0
		}, {
			xtype : 'button',
			text : '查询',
			iconCls : 'search',
			listeners : {
				click : function() {
					var startDate = dr.getForm()
							.findField('startdt').getValue();
					var endDate = dr.getForm()
							.findField('enddt').getValue();
					var departmentId = dr.getForm()
							.findField('deptCombo').getValue();
					var staffId = dr.getForm()
							.findField('staffCombo').getValue();
					reportStore.on('beforeload', function(store, options) {
						var new_params = {
							startDate : startDate,
							endDate : endDate,
							departmentId : departmentId,
							staffId : staffId,
							query : 'true'
						};
						Ext.apply(
							journalStore.proxy.extraParams,
							new_params);
						});
					journalStore.reload();
				}
			}
		}, '-', '->', {
			xtype : 'button',
			text : '新建',
			iconCls : 'journal_add',
			handler : addReport,
			hidden : add
		}, {
			xtype : 'button',
			text : '修改',
			iconCls : 'journal_edit',
			handler : editReport,
			hidden : update
		}, {
			xtype : 'button',
			text : '删除',
			iconCls : 'journal_delete',
			handler : deleteReport,
			hidden : drop
		}, {
			xtype : 'filefield',
			buttonOnly : true,
			buttonText : '导入',
			buttonConfig : {
				iconCls : 'file_in'
			},
			hidden:true
		}, {
			xtype : 'button',
			text : '导出',
			iconCls : 'file_export',
			handler : exportReport,
			hidden : exportExcel
		}]

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
            {text: "人数", width: 70, sortable: true, align:'center', dataIndex: 'number'},
            {width: 70, sortable: true, align:'center', dataIndex: 'desc1', hidden: true},
            {width: 70, sortable: true, align:'center', dataIndex: 'abstract1', hidden :true},
            {width: 70, sortable: true, align:'center', dataIndex: 'desc2', hidden: true},
            {width: 70, sortable: true, align:'center', dataIndex: 'abstract2', hidden :true},
            {width: 70, sortable: true, align:'center', dataIndex: 'desc3', hidden: true},
            {width: 70, sortable: true, align:'center', dataIndex: 'abstract3', hidden :true},
            {width: 70, sortable: true, align:'center', dataIndex: 'desc4', hidden: true},
            {width: 70, sortable: true, align:'center', dataIndex: 'abstract4', hidden :true},
        ],
		bbar:new Ext.PagingToolbar({
        	pageSize:20,
            store: reportStore,
            displayInfo: true
        }),
        renderTo: Ext.getBody()
	});
	// 双击进行修改
    grid.addListener('itemdblclick', editReport, this);

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
			value: 0,
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
                    anchor: '100%'
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
					MaxValue : new Date(),
					// value :new Date(),
					mode : 'local',
					format : 'Y-m-d',
					name:'recordDate'
				}]
	            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%'
                },
                items: [{
                	width:'32%',
					xtype : 'datefield',
					fieldLabel : '出差日期',
					MaxValue : new Date(),
					mode : 'local',
					// allowBlank : false,
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
                    anchor: '100%'
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
                    anchor: '100%'
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
                    anchor: '100%'
                },
                items: [{
                	width:'33%',
					xtype : 'textfield',
					fieldLabel : '报告人编号',
					name:'staffId',
					listeners : {
						blur : function(tf) {
							var Id = tf.getRawValue();
							Ext.Ajax.request({
								params : {
									staffId : Id
								},
								url : 'staff_getNameById',
								method : 'POST',
								success : function(response, options) {
									var responseArray = Ext.JSON.decode(response.responseText);
									if (responseArray.success == true) {
										form.getForm().findField('staffName').setValue(responseArray.msg);
									} else {
										form.getForm().findField('staffName').reset();
										top.Ext.Msg.show({
											title : '提示',
											msg : responseArray.msg,
											icon : Ext.Msg.ERROR,
											buttons : Ext.Msg.OK
										});
									}
								},
								failure : function(response, options) {
									top.Ext.Msg.show({
										title : '提示',
										msg : '错误！',
										icon : Ext.Msg.ERROR,
										buttons : Ext.Msg.OK
									});
								}
							})
						}
					}
				}, {
                	width:'33%',
					xtype : 'textfield',
					fieldLabel : '报告人名称',
					name:'staffName'
				}, {
                	width:'100%',
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
					height : 18
				},{
                	width:'30%',
					xtype : 'text',
					text : '处理事项简述',
					height : 18
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
					value :"\n  1",
					height :50
				},{
                	width:'30%',
					xtype : 'textarea',
					name : 'desc1',
					height :50
				},{
                	width:'65%',
					xtype : 'textarea',
					name : 'abstract1',
					height :50
				}]
            },{
                items: [{
                	width:'5%',
					xtype : 'textarea',
					value :"\n  2",
					height :50
				},{
                	width:'30%',
					xtype : 'textarea',
					name : 'desc2',
					height :50
				},{
                	width:'65%',
					xtype : 'textarea',
					name : 'abstract2',
					height :50
				}]
            },{
                items: [{
                	width:'5%',
					xtype : 'textarea',
					value :"\n  3",
					height :50
				},{
                	width:'30%',
					xtype : 'textarea',
					name :'desc3',
					height :50
				},{
                	width:'65%',
					xtype : 'textarea',
					name : 'abstract3',
					height :50
				}]
            },{
                items: [{
                	width:'5%',
					xtype : 'textarea',
					value :"\n  4",
					height :50
				},{
                	width:'30%',
					xtype : 'textarea',
					name : 'desc4',
					height :50
				},{
                	width:'65%',
					xtype : 'textarea',
					name : 'abstract4',
					height :50
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
                    anchor: '100%'
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
                    anchor: '100%'
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
					name:'sum',
                    listeners : {
                        focus : function(tf) {
                            var total = 0;
                            var tic = form.getForm().findField('ticket').getValue();
                            var acc = form.getForm().findField('accommodation').getValue();
                            var tra = form.getForm().findField('transportation').getValue();
                            var mea = form.getForm().findField('meals').getValue();
                            var sub = form.getForm().findField('subsidy').getValue();
                            var num = form.getForm().findField('number').getValue();
                            var t1 = (tic=="")?0:parseInt(tic);
                            var t2 = (acc=="")?0:parseInt(acc);
                            var t3 = (tra=="")?0:parseInt(tra);
                            var t4 = (mea=="")?0:parseInt(mea);
                            var t5 = (sub=="")?0:parseInt(sub);
                            var i = (num=="")?0:parseInt(num);
                            total = t1 + t2 + t3 + t4 + t5;
                            tf.setValue((total * i));
                        }
                    }
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
    function addReport(){   	
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
    function editReport(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			var judge=judgereport();
			form.form.reset();
	    	form.isAdd=false;	    	
			form.getForm().loadRecord(record[0]);	
			if(judge){//当天的日志，设置只读为false
				win.setTitle('修改访问报告');
				form.getForm().getFields().each(function(field) {	        
		        field.setReadOnly(false);  
	            })
			}else{//非当天的日志，设置只读为true
				win.setTitle('查看访问报告');
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
    function deleteReport(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	top.Ext.Msg.confirm('提示','您确定要删除所选日志吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteRecords(records)
    		}
    	});
    };
    
    // 执行删除操作
    function deleteRecords(records){
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
    		msg:'正在删除记录，请稍等...'
    	});
    	Ext.Ajax.request({
    		url:'report_delete.action',
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
				url:'report_add.action',
				method:'POST',
    			success:function(form,action){
    				win.hide();
					updateGrid(action.result.msg);
    				top.Ext.Msg.show({title:'提示', msg:'新增访问报告成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
    			},
    			failure:function(form,action){
    				top.Ext.Msg.show({title:'提示', msg:'新增访问报告失败，所填内容有误。',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    			}
    		});
    	}else{			
    		var judge=judgereport();	
			if (judge){
			    form.form.submit({
	    		waitMsg:'正在提交数据，请稍后...',
				waitTitle:'提示',
				url:'report_update.action',
				method:'POST',
				success:function(form,action){
					win.hide();
					updateGrid(action.result.msg);
					top.Ext.Msg.show({title:'提示', msg:'修改访问报告成功',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
				},
				failure:function(form,action){
					top.Ext.Msg.show({title:'提示', msg:'修改访问报告失败，所填内容有误。',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
				}})							
			}else{
				top.Ext.Msg.show({title:'提示', msg:'不可以修改非当天提交的访问报告！',icon:Ext.Msg.INFO,buttons:Ext.Msg.OK});
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
	            number:values['number'],
	            desc1:values['desc1'],
	            abstract1:values['abstract1'],
	            abstract1:values['abstract1'],
	            abstract1:values['abstract1'],
	            desc2:values['desc2'],
	            abstract2:values['abstract2'],
	            desc3:values['desc3'],
	            abstract3:values['abstract3'],
	            desc4:values['desc4'],
	            abstract4:values['abstract4']
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
    		msg="您确定要导出所有访问报告吗？";
    	}else{
    		msg="您确定要导出所选的访问报告吗？";
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
    			window.location.href='byjx/report_export.action?reportIds='+reportIds;
    		}
		});
    }

});