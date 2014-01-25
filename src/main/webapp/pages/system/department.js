Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux','../../extjs/ux');
Ext.require([
    'Ext.form.Panel',
    'Ext.ux.form.MultiSelect',
    'Ext.ux.form.ItemSelector',
    'Ext.tip.QuickTipManager',
    'Ext.ux.ajax.JsonSimlet',
    'Ext.ux.ajax.SimManager'
]);

Ext.onReady(function () {
	Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
    function createDockedItems(fieldId) {
        return [{
            xtype: 'toolbar',
            dock: 'top',
            items: {
                text: 'Options',
                menu: [{
                    text: 'Get value',
                    handler: function () {
                        var value = Ext.getCmp(fieldId).getValue();
                        Ext.Msg.alert('Value is a split array', value.join(', '));
                    }
                }, {
                    text: 'Set value (2,3)',
                    handler: function () {
                        Ext.getCmp(fieldId).setValue(['2', '3']);
                    }
                }, {
                    text: 'Toggle enabled',
                    checked: true,
                    checkHandler: function (item, checked) {
                        Ext.getCmp(fieldId).setDisabled(!checked);
                    }
                }, {
                    text: 'Toggle delimiter',
                    checked: true,
                    checkHandler: function (item, checked) {
                        var field = Ext.getCmp(fieldId);
                        if (checked) {
                            field.delimiter = ',';
                            Ext.Msg.alert('Delimiter Changed', 'The delimiter is now set to <b>","</b>. Click Save to ' +
                                          'see that values are now submitted as a single parameter separated by the delimiter.');
                        } else {
                            field.delimiter = null;
                            Ext.Msg.alert('Delimiter Changed', 'The delimiter is now set to <b>null</b>. Click Save to ' +
                                          'see that values are now submitted as separate parameters.');
                        }
                    }
                }]
            }
        }, {
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            defaults: {
                minWidth: 75
            },
            items: ['->', {
                text: '清除',
                handler: function () {
                    var field = Ext.getCmp(fieldId);
                    if (!field.disabled) {
                        field.clearValue();
                    }
                }
            }, {
                text: '重置',
                handler: function () {
                    Ext.getCmp(fieldId).up('form').getForm().reset();
                }
            }, {
                text: '保存',
                handler: function () {
                    var form = Ext.getCmp(fieldId).up('form').getForm();
                    form.getValues(true);
                    if (form.isValid()) {
                        Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />' +
                            form.getValues(true));
                    }
                }
            }]
        }];
    }

   
    Ext.define('department', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'DepartmentNo'},
            {name: 'Department'},
            {name: 'ManagerNo'},
            {name: 'TotalStaff', type: 'int'},
            {name: 'createTime', type: 'date', dateFormat: 'Y-m-d'}
         ]
    });

    Ext.grid.dummyData = [
        ['1','人力部','小蔡','10','2006-06-04'],
        ['2','财务部','小召','12','2001-04-03']
    ];

    var user_store = Ext.create('Ext.data.Store', {
        model: 'department',
        autoLoad: true,
        data : Ext.grid.dummyData
        });

    //创建表格
    var grid = Ext.create('Ext.grid.Panel', {
        width: document.body.clientWidth,
        height: document.body.clientHeight,
        border:false,
        autoScroll:false,
        multiSelect: true,
        selModel:{selType:'checkboxmodel'},
        store: user_store,
        viewConfig:{
            forceFit:true
        },
        tbar:[
        	{xtype:'button',text:'新增部门',iconCls: 'user_add',handler:addUserInfo},
            {xtype:'button',text:'修改部门',iconCls: 'user_edit',handler:editUserInfo},
            {xtype:'button',text:'删除部门',iconCls: 'user_delete',handler:deleteUserInfo},
            {xtype:'button',text:'员工管理',iconCls: 'user_delete',handler:userManagement},
            {xtype:'button',text:'导出',iconCls:'file_export'}
        ],
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "部门编号", flex: 0.2, sortable: true, dataIndex: 'DepartmentNo'},
            {text: "部门名称", flex: 0.2, sortable: true,dataIndex: 'Department'},
            {text: "部门经理", flex: 0.2, sortable: true, dataIndex: 'ManagerNo'},
            {text: "总员工数",flex: 0.2, sortable: true, dataIndex: 'TotalStaff'},
            {
        		text: "成立时间", 
        		width: 120, 
        		sortable: true, 
        		renderer: Ext.util.Format.dateRenderer('Y-m-d'), 
        		dataIndex: 'createTime'
    		}
        ],
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        autoScroll:false,
	        store: user_store,   // same user_store GridPanel is using
	        dock: 'bottom',
	        pageSize:5,
	        displayInfo: true,
	        autoScroll:false
	    }],
        renderTo: Ext.getBody()
    });
    var ds = Ext.create('Ext.data.ArrayStore', {
        fields: ['value', 'text'],
        proxy: {
            type: 'ajax',
            url: 'Numbers',
            reader: 'array'
        },
        autoLoad: true,
        sortInfo: {
            field: 'value',
            direction: 'ASC'
        }
    });
    Ext.ux.ajax.SimManager.init({
        delay: 300,
        defaultSimlet: null
    }).register({
        'Numbers': {
            data: [ ['1', '小闫'], ['2', '小祖'], ['3', '小蔡'], ['4', '小宋'], ['5', '小吴'],
                    ['6', '小袁'], ['7', '小王'], ['8', '小李'], ['9', '小刘']],
            stype: 'json'
        }
    });
    var isForm = Ext.widget('form', {
        width: 600,
		bodyStyle: 'background:#dfe9f5',
        border: false,
        height: 350,
        //renderTo: Ext.getBody(),
        layout: 'fit',
        items: [{
            xtype: 'itemselector',
            name: 'itemselector',
            margin:'-1 -1 0 0',
            id: 'itemselector-field',
            anchor: '100%',
            border:0,
            fieldLabel: 'ItemSelector',
            imagePath: 'ux/images/',
            store: ds,
            displayField: 'text',
            valueField: 'value',
            value: ['3', '4', '6'],
            allowBlank: false,
            msgTarget: 'side',
            fromTitle: '源部门',
            toTitle: '目标部门'
        }],
        dockedItems: createDockedItems('itemselector-field')
    });

    var wind =new Ext.create('Ext.Window', {
        width: 610,
        height: 400,
        layout:'fit',
        closeable:true,
        closeAction:'hide',
        constrain:true,
        title: 'multiselect',
        items: isForm,
        modal:true

    });
    //创建表单
	var form = top.Ext.create('Ext.form.Panel', {
        width:'100%',
        height:'100%',
        autoScroll:true,
        bodyPadding: 10,
        border:false,
        bodyStyle: 'background:#dfe9f5',
        defaults: {
            anchor: '100%',
            allowBlank:false,
            blankText:'不允许为空',
            msgTarget:'qtip'
        },
        items: [{
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
            items: [{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '部门编号',xtype: 'combo',name: 'DepartmentNo',allowBlank: false}, 
                    {width:'33%',fieldLabel: '部门名称',name: 'Department',margins:'0 4 0 0',allowBlank: false}
                ]
            },{
                defaults: {
                    labelWidth:64,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'33%',fieldLabel: '部门经理',xtype: 'combo',name: 'ManagerNo',allowBlank: false}, 
                    {width:'33%',fieldLabel: '员工总数',name: 'TotalStaff',margins:'0 4 0 0',allowBlank: false}
                ]
            }]
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
    	layout : 'fit',
		width :750,
		height : 500,
		closeAction:'hide',
		constrainHeader:true,
		plain : true,
		modal : true,
		items : form
    });
  //员工管理
    function userManagement(){
    	isForm.form.reset();
    	isForm.isAdd=true;
    	wind.setTitle('员工管理');
    	wind.show();
    };
  //增加新用户
    function addUserInfo(){
    	form.form.reset();
    	form.isAdd=true;
    	win.setTitle('新增部门');
    	win.show();
    };
    
    //修改用户
    function editUserInfo(){
    	var record=grid.getSelectionModel().getSelection();
		if (record.length==1) {
			form.form.reset();
	    	form.isAdd=false;
	    	win.setTitle('修改部门');
	    	win.show();
			form.getForm().loadRecord(record[0]);
		} else {
			top.Ext.Msg.show({title:'错误', msg:'请仅选择一条记录进行编辑！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
		}
    };
    
    //删除用户
    function deleteUserInfo(){
    	var records=grid.getSelectionModel().getSelection();
    	if(records.length==0){
    		top.Ext.Msg.show({title:'错误', msg:'请至少选择一条记录进行删除！',icon:Ext.Msg.ERROR,buttons:Ext.Msg.OK});
    		return;
    	}
    	Ext.Msg.confirm('提示','您确定要删除所选用户吗？',function(btnID){
    		if(btnID=='yes'){
    			deleteUsers(records)
    		}
    	});
    };
    //执行删除操作
    function deleteUsers(records){
    	var userID=records.join(',');
    	for(var i=0;i<records.length;i++){
    		var index=user_store.find('staffNo',records[i].get('staffNo'));
    		if(index!=-1){
    			var rec=user_store.getAt(index);
    			user_store.remove(rec);
    		}
    	}
    };
    
    function submitForm(){
    	
    };
});
