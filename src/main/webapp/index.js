Ext.require([
    'Ext.grid.*',
    'Ext.form.*',
    'Ext.data.*',
    'Ext.toolbar.Paging',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager',
    'Ext.selection.CheckboxModel'
]);
Ext.onReady(function() {

    Ext.QuickTips.init();
    
    Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
    
    var forTree;
    Ext.Ajax.request({
		url:'right_getByRole.action',
		method:'POST',
//		params:{roleId:roleId},
		dataType:'json', 
		async:false,
		success:function(response,options){
			forTree=response.responseText;
//			alert(forTree);
		}
	});
    
    var treeStore=new Ext.data.TreeStore(eval('('+forTree+')'));
          treeStore.sort('id','ASC');
	var tree=Ext.create('Ext.tree.Panel', {
	    width: '59.9%',
	    height: 150,
	    margins : '-1 0 0 0',
	    border:false,
	    store: treeStore,
	    rootVisible: false,
	    listeners:{
            itemclick:function(view,rec,item,index,e,eOpts){
                if(rec.isLeaf())
                    loadPage(rec.data.hrefTarget,rec.data.text);
            }
        }
	});
    //左侧菜单面板
    var menuPanel = new Ext.Panel({
        split : true,
        width : 200,
        minSize : 100,
        maxSize : 400,
        //collapsible : true,
        margins : '0 0 5 5',
        items:tree,
        bodyboder : true,
        title : '功能导航',
        layout : 'fit',
        region : 'west'
    });
    
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
            margins: '0 15 5 0',
            contentEl:'north'
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
//    treeStore.load();
    var curNode=treeStore.tree.root;
    curNode=curNode.childNodes[0];
    curNode=curNode.childNodes[0]
    loadPage(curNode.data.hrefTarget,curNode.data.text);
    
});

function loadPage(url,text) {
    Ext.getDom('iframeContent').src=url;
    Ext.getCmp('mainContent').setTitle(text);
}