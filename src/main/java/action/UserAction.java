package action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import service.GenericService;
import service.UserService;
import service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

import entity.TblUser;

public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService  userService;
	
	private TblUser tblUsers;
	
	

	public TblUser getTblUsers() {
		return tblUsers;
	}
	public void setTblUsers(TblUser tblUsers) {
		this.tblUsers = tblUsers;
	}
	@JSON(serialize=false)
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String test(){
		tblUsers=(userService.findUser("yingzhuo"));
		return "SUCCESS";
	}
	
	
	public String save(){
		TblUser tblUsers=new TblUser();
		tblUsers.setUserUsername("zhangtao");
		tblUsers.setUserPassword("aaaa");
		userService.save(tblUsers);
		return "SUCCESS";
	}
}
