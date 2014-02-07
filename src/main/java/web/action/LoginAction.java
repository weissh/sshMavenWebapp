package web.action;

import com.opensymphony.xwork2.ActionContext;

import pojos.Staff;
import service.RightService;
import service.RoleService;
import service.StaffService;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StaffService staffService;
	
	private RoleService roleService;
	
	private RightService rightService;
	
	private String userName;
	
	private String password;
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RightService getRightService() {
		return rightService;
	}

	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login(){
		//Staff staff=this.staffService.getStaffByUserNamePwd(this.userName,this.password);
		Staff staff=this.staffService.find(Staff.class, 1);
		if(staff!=null){
			ActionContext context=ActionContext.getContext();
			context.getSession().put("staff",new Staff(userName, password));
			this.printString(true, "index.jsp");
		}else{
			this.printString(false, "用户或密码错误，请重新登录！");
		}
		return null;
	}
	
}
