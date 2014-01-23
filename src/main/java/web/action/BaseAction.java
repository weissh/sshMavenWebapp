package web.action;


import java.lang.reflect.Method;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//基础操作
	private String action="index";
	
	//获取action的属性值
	public String getAction(){
		return action;
	}
	
	public void setAction(String action){
		this.action=action;
	}
	
	public String execute(){
		try{
			return this.executeMethod(this.getAction());
		}catch(Exception e){
			e.printStackTrace();
			return INPUT;
		}
	}

	private String executeMethod(String method) throws Exception {
		Class[] c=null;
		Method m=this.getClass().getMethod(method, c);
		Object[] object = null;
		String result =(String) m.invoke(this, object);
		return result;
	}
}
