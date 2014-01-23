package web.action;

import pojos.Staff;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userName;
	private String password;
	
	public int getUserName() {
		return userName;
	}
	
	public void setUserName(int userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login(){
		if("admin".equals(userName)&&"123456".equals(password)){
			ActionContext context=ActionContext.getContext();
			context.getSession().put("staff",new Staff(userName,password));
			return "SUCCESS";
		}
		return "INPUT";
	}
	
	public String logout(){
		ActionContext context=ActionContext.getContext();
		context.getSession().remove("staff");
		return "INPUT";
	}
}
