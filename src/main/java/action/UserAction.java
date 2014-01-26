package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	private int page=0;
	private int rows=10;
	
	private List<TblUser> list;
	public List<TblUser> getList() {
		return list;
	}
	public void setList(List<TblUser> list) {
		this.list = list;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@JSON(serialize=false)
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
		list=userService.findByPage(page, rows);
		return "SUCCESS";
	}
	
	
	public String save(){
		TblUser tblUsers=new TblUser();
		tblUsers.setUserUsername("test");
		tblUsers.setUserPassword("aaaa");
		userService.save(tblUsers);
		return "SUCCESS";
	}
}
