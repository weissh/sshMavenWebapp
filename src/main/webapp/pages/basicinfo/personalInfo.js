Ext.require([
    'Ext.form.*',
    'Ext.data.*',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function() {
    Ext.QuickTips.init();
    //定义修改密码的类
    Ext.define('Password', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'oldPwd',type: 'string'},
            {name: 'newPwd_1',type: 'string'},
            {name: 'newPwd_2',type: 'string'}
        ]
    });
    
    //修改密码
    var modifyPwd={
        xtype: 'fieldset',
        title: '修改密码',
        border: false,
        defaults: {
        	bodyStyle:'background:#dfe9f5',
            border: false,
            layout: {
                type: 'hbox',
                defaultMargins: {top: 0, right: 25, bottom: 5, left: 0}
            }
        },
        items: [{
            defaults: {
            	bodyStyle:'background:#dfe9f5',
                labelWidth:69,
                xtype:'textfield'
            },
            items: [
                {width:'33%',fieldLabel: '旧密码',name: 'oldPwd',readOnly:true},
                {width:'33%',fieldLabel: '新密码',id:'newPwd_1',name: 'newPwd_1'},
                {width:'33%',fieldLabel: '确认密码',id:'newPwd_2',name: 'newPwd_2',margins:'0 4 5 0'}
            ],
            fbar:[{
            	xtype:'button',
        		text:'测试',
        		handler: function() {
                    this.up('form').getForm().loadRecord(Ext.create('Password', {
                        'oldPwd'    : '12345678'
                    }));
                }
            },{
            	xtype:'button',
            	text:'保存',
            	handler: function() {
                    var form   = this.up('form').getForm(),
                        encode = Ext.String.htmlEncode,
                        s      = '';

                    if (form.isValid()) {
                        Ext.iterate(form.getValues(), function(key, value) {
                            value = encode(value);
                            
                            s += Ext.util.Format.format("{0} = {1}<br />", key, value);
                        }, this);

                        Ext.Msg.alert('Form Values', s);
                    }
                }
            },{
            	xtype:'button',
            	text:'重置',
            	handler: function() {
                    this.up('form').getForm().reset();
                }
            }]
        }]
    }
    
    //定义用户信息，包括照片和员工系统信息
    var userInfo=new Ext.Panel({
    	layout:'column',
    	anchor:'100%',
    	bodyStyle:'background:#dfe9f5',
        bodyPadding: '5px',
    	border:false,
    	items:[{
    		border:false,
    		bodyStyle:'background:#dfe9f5',
    		width:145,
            autoHeight:true,
            bodyPadding: '5px',
            html:'<img src="../../images/photo.jpg" width="130" height="160">'
    	},{
    		bodyStyle:'background:#dfe9f5',
    		border:false,
    		columnWidth:1,
    		items:[{
    			xtype: 'fieldset',
                title: '员工信息',
                border: false,
                collapsible: false,
                defaults: {
                	bodyStyle:'background:#dfe9f5',
                    border: false,
                    layout: {
                        type: 'hbox',
                        defaultMargins: {top: 0, right: 25, bottom: 5, left: 0}
                    }
                },
                items: [{
                    defaults: {
                    	readOnly:true,
                        labelWidth:69,
                        xtype:'textfield'
                    },
                    items: [
                        {width:'33%',fieldLabel: '姓名',name: 'staffName'}, 
                        {width:'33%',fieldLabel: '工号',name: 'staffNo'},
                        {width:'33%',fieldLabel: '部门',name: 'department',margins:'0 4 0 0'}
                    ]
                },{
                    defaults: {
                    	readOnly:true,
                        labelWidth:69,
                        xtype:'textfield'
                    },
                    items: [
                        {width:'33%',fieldLabel: '职务',name: 'position'}, 
                        {width:'33%',fieldLabel: '入职时间',name: 'enterTime'},
                        {width:'33%',fieldLabel: '角色',name: 'role',margins:'0 4 0 0'}
                    ]
                }]
    		},{
    			xtype:'form',
    			bodyStyle:'background:#dfe9f5',
    			items:[modifyPwd]
    		}]
    	}],
    	renderTo:Ext.getBody()
    });
    
    //定义基本信息、履历信息、联系方式信息
    var form = Ext.create('Ext.form.Panel', {
    	renderTo:Ext.getBody(),
    	height:'100%',
        border:false,
        bodyStyle:'background:#dfe9f5',
        bodyPadding:'10 5 15 5',
        autoSrcoll:false,
        items: [{
        	xtype: 'fieldset',
        	flex:1,
            title: '基本信息',
            collapsible: true,
            defaults: {
            	bodyStyle:'background:#dfe9f5',
                border: false,
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 25, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'25%',fieldLabel: '姓名',name: 'staffName'}, 
                    {width:'25%',fieldLabel: '性别',name: 'gender'},
                    {width:'25%',fieldLabel: '年龄',name: 'age'},
                    {width:'25%',fieldLabel: '出生日期',name: 'birthday',margins:'0 4 0 0'}
                ]
            },{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'25%',fieldLabel: '民族',name: 'nationality'}, 
                    {width:'25%',fieldLabel: '政治面貌',name: 'politicalStatus'},
                    {width:'25%',fieldLabel: '婚姻状况',name: 'maritalStatus'},
                    {width:'25%',fieldLabel: '籍贯',name: 'nativePlace',margins:'0 4 0 0'}
                ]
            },{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'50%',fieldLabel: '身份证号',name: 'IDNo'}, 
                    {width:'50%',fieldLabel: '护照号',name: 'passportNo',margins:'0 4 0 0'}
                ]
            },{
				defaults: {
					readOnly:true,
                    labelWidth:79,
                    anchor: '100%',
                    xtype:'textfield'
                },
                items: [
                    {width:'100%',fieldLabel: '户口所在地',margins:'0 4 5 0',name: 'domicilePlace'}
                ]
			}]
        },{
            xtype: 'fieldset',
            title: '履历信息',
            flex:1,
            collapsible: true,
            defaults: {
            	bodyStyle:'background:#dfe9f5',
                border: false,
                //labelWidth: 100,
                anchor: '100%',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 25, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'25%',fieldLabel: '参加工作时间',name: 'dateOfRecruitment'}, 
                    {width:'25%',fieldLabel: '最高学历',name: 'hightestEdu'},
                    {width:'25%',fieldLabel: '最高学位',name: 'hightestDegree'},
                    {width:'25%',fieldLabel: '毕业院校',name: 'graduateSchool',margins:'0 4 0 0'}
                ]
            },{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'50%',fieldLabel: '专业',name: 'major'}, 
                    {width:'50%',fieldLabel: '学制',name: 'schoolSystem',margins:'0 3 0 0'}
                ]
            }]
        },{
            xtype: 'fieldset',
            title: '联系信息',
            flex:1,
            collapsible: true,
            defaults: {
            	bodyStyle:'background:#dfe9f5',
                border: false,
                //labelWidth: 100,
                anchor: '100%',
                layout: {
                    type: 'hbox',
                    defaultMargins: {top: 0, right: 25, bottom: 5, left: 0}
                }
            },
            items: [{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'25%',fieldLabel: '手机号',name: 'phone'}, 
                    {width:'25%',fieldLabel: '邮箱',name: 'email'},
                    {width:'25%',fieldLabel: '紧急联系人',name: 'urgentContact'},
                    {width:'25%',fieldLabel: '紧急电话',name: 'UCPhone',margins:'0 4 0 0'}
                ]
            },{
                defaults: {
                	readOnly:true,
                    labelWidth:79,
                    xtype:'textfield',
                    anchor: '100%'
                },
                items: [
                    {width:'50%',fieldLabel: '现居住地',name: 'currentAddress'}, 
                    {width:'50%',fieldLabel: '邮编',name: 'zipCode',margins:'0 3 0 0'}
                ]
            }]
        }]
    });
    
    //定义界面视图
    var viewPort = new Ext.container.Viewport({
    	items:[userInfo,form]
    });
});
