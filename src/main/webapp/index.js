//Ext.require(['*']);

    Ext.onReady(function() {

        Ext.QuickTips.init();
        
        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
        
        //左侧菜单面板
        var menuPanel = new Ext.Panel({
            split : true,
            width : 200,
            minSize : 100,
            maxSize : 400,
            //collapsible : true,
            margins : '0 0 5 5',
            bodyboder : true,
            title : '功能导航',
            layout : 'accordion',
            region : 'west'
        });
        
        var info = new Ext.tree.TreePanel({
            border : false,
            title : '基本信息',
            autoScroll : true,
            rootVisible : false,
            lines : false,
            singleExpand : false,
            useArrows : true,
            root : {
                expanded : true,
                children : [{
                    text : '个人信息',
                    hrefTarget : 'pages/basicinfo/personalInfo.jsp',
                    leaf : true
                }, {
                    text : '部门信息',
                    hrefTarget : 'pages/basicinfo/departmentInfo.jsp',
                    leaf : true
                }]
            },
            listeners:{
                itemclick:function(view,rec,item,index,e,eOpts){
                    if(rec.isLeaf())
                        loadPage(rec.data.hrefTarget,rec.data.text);
                }
            }
        });
        menuPanel.add(info);
        
        var journal = new Ext.tree.TreePanel({
            border : false,
            title : '工作日志',
            autoScroll : true,
            rootVisible : false,
            lines : false,
            singleExpand : false,
            useArrows : true,
            root : {
                expanded : true,
                children : [{
                    text : '个人日志',
                    hrefTarget : 'pages/journal/personalJournal.jsp',
                    leaf : true
                }, {
                    text : '部门日志',
                    hrefTarget : 'pages/journal/departmentJournal.jsp',
                    leaf : true
                }]
            },
            listeners:{
                itemclick:function(view,rec,item,index,e,eOpts){
                    if(rec.isLeaf())
                        loadPage(rec.data.hrefTarget,rec.data.text);
                }
            }
        });
        
        menuPanel.add(journal);
        
        var cost = new Ext.tree.TreePanel({
            border : false,
            title : '费用支出',
            autoScroll : true,
            rootVisible : false,
            lines : false,
            singleExpand : false,
            useArrows : true,
            root : {
                expanded : true,
                children : [{
                    text : '个人支出',
                    hrefTarget : 'pages/cost/personalCost.jsp',
                    leaf : true
                }, {
                    text : '部门支出',
                    hrefTarget : 'pages/cost/departmentCost.jsp',
                    leaf : true
                }]
            },
            listeners:{
                itemclick:function(view,rec,item,index,e,eOpts){
                    if(rec.isLeaf())
                        loadPage(rec.data.hrefTarget,rec.data.text);
                }
            }
        });
        
        menuPanel.add(cost);
        
        var system = new Ext.tree.TreePanel({
            border : false,
            title : '系统管理',
            autoScroll : true,
            rootVisible : false,
            lines : false,
            singleExpand : false,
            useArrows : true,
            root : {
                expanded : true,
                children : [{
                    text : '部门管理',
                    hrefTarget : 'pages/system/department.jsp',
                    leaf : true
                },{
                    text : '用户管理',
                    hrefTarget : 'pages/system/user.jsp',
                    leaf : true
                }, {
                    text : '角色管理',
                    hrefTarget : 'pages/system/role.jsp',
                    leaf : true
                }]
            },
            listeners:{
                itemclick:function(view,rec,item,index,e,eOpts){
                    if(rec.isLeaf())
                        loadPage(rec.data.hrefTarget,rec.data.text);
                }
            }
        });
        
        menuPanel.add(system);
        //中部显示面板
        var contentPanel = new Ext.Panel({
            layout:'fit',
            autoScroll:false,
            region : 'center',
            contentEl:'center',
            id:'mainContent',
            margins: '0 5 5 0',
            bodyStyle: 'background:#f0f0f0'
        });
        var viewport = Ext.create('Ext.Viewport', {
            id: 'border-example',
            layout: 'border',
            items: [
            // 定义界面上方
            Ext.create('Ext.Component', {
                region: 'north',
                height: 32,
                autoEl: {
                    tag: 'div',
                    html:'<p>north - generally for menus, toolbars and/or advertisements</p>'
                }
            }),
//            //定义界面下方
//            Ext.create('Ext.Component', {
//                region: 'south',
//                height: 32,
//                contentEl: 'south'
//            }),
//            {
//                //定义界面右方
//                xtype: 'tabpanel',
//                region: 'east',
//                title: '提示',
//                dockedItems: [{
//                    dock: 'top',
//                    xtype: 'toolbar',
//                    items: [ '->', {
//                       xtype: 'button',
//                       text: 'test',
//                       tooltip: 'Test Button'
//                    }]
//                }],
//                animCollapse: true,
//                collapsible: true,
//                split: true,
//                width: 225, // give east and west regions a width
//                minSize: 175,
//                maxSize: 400,
//                margins: '0 5 3 0',
//                activeTab: 1,
//                tabPosition: 'bottom',
//                items: []
//            }
                menuPanel
                ,contentPanel
//            ,{
//                // 定义界面下方
//                region: 'south',
//                contentEl: 'south',
//                split: true,
//                height: 30
//            }
                ]
        });
        var curNode=info.root;
        if(curNode.children.length>0){
            curNode=curNode.children[0];
        };
        loadPage(curNode.hrefTarget,curNode.text);
        
    });
    
    function loadPage(url,text) {
        Ext.getDom('iframeContent').src=url;
        Ext.getCmp('mainContent').setTitle(text);
    }