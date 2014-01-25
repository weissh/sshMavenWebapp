
Ext.onReady(function() {
    Ext.tip.QuickTipManager.init();
    var loginMask=new Ext.LoadMask(Ext.getBody(),{msg:'正在登录，请稍后...'});
    
    //用户名文本框
    var userName=new Ext.form.TextField({
    	fieldLabel:'用户名',
		name:'userName',
		allowBlank:false,
		value:'admin',
		listeners:{
			specialkey:function(field,e){
				if(e.getKey()==13){
					loginBtn.fireEvent('click',loginBtn);
				}
			}
		}
    });
    
    //密码文本框
    var pwd=new Ext.form.TextField({
		fieldLabel:'密码',
		name:'password',
		inputType:'password',
		allowBlank:false,
		value:'admin',
		listeners:{
			specialkey:function(field,e){
				if(e.getKey()==13){
					loginBtn.fireEvent('click',loginBtn);
				}
			}
		}
    });
    
    //登录按钮，包括监听事件
	var loginBtn=new Ext.Button({
    	text:'登录',
    	listeners:{
    		click:function(){
    			var params={
    				userName:userName.getValue(),
    				password:pwd.getValue()
				};
				
				if(params.username==''){
					//MsgTip.msg('请输入用户名','');
					userName.focus();
					return;
				}
				
				if(params.password==''){
					//MsgTip.msg('请输入用户名','');
					pwd.focus();
					return;
				}
				loginMask.show();
				
				Ext.Ajax.request({
					url : 'login.action',
					method : 'POST',
					customer:'自定义属性',
					params : params,
//					callback:function(options,success,response){
//						var msg=['请求是否成功：',success,"\n",
//						'服务器返回值：',response.responseText,
//						'本地自定义属性：',options.customer];
//						alert(msg.join(''));
//					},
					success : function(response,o) {
						// 处理返回信息
						var responseObj = Ext.decode(response.responseText);
						if (responseObj.success) {
							window.top.location = responseObj.msg;
						} else {
							Ext.example.msg('登录失败',responseObj.msg);
							//隐藏mask
							loginMask.hide();
						}
					},
					failure : function() {
						//隐藏mask
						loginMask.hide();
						//显示错误信息
						Ext.example.msg('登录失败','网络故障');
					}
				});
    		}
    	}
    
    });
    
    //登录表单
    var loginForm=new Ext.form.Panel({
    	border:false,
    	bodyStyle: 'background:#dfe9f5',
    	width:'100%',
    	height:'100%',
    	bodyPadding:'20px 30px 20px 30px',
    	buttonAlign:'center',
        fieldDefaults: {
            labelWidth: 60,
            msgTarget: 'side',
            autoFitErrors: false
        },
    	items:[userName,pwd],
    	buttons:[loginBtn,{
    		text:'重置',
    		handler:function(){
    			this.up('form').getForm().reset();
    		}
    	}],
    	renderTo:Ext.getBody()
    });
    
    //窗口，用于放置登录表单
    var win =new Ext.Window({
    	title:'用户登录',
    	layout:'fit',
    	width:300,
    	height:145,
    	draggable:false,
    	closable:false,
    	items:loginForm
    }).show();
    
    

    
    userName.focus();
    
    
    function login(){
    	
    }
});