package action;

import java.util.List;

import service.GenericService;
import service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

import entity.TblUser;

public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserServiceImpl genericService;
	
	private List<TblUser> tblUser;
	
	public UserServiceImpl getGenericService() {
		return genericService;
	}

	public void setGenericService(UserServiceImpl genericService) {
		this.genericService = genericService;
	}


	public List<TblUser> getTblUser() {
		return tblUser;
	}

	public void setTblUser(List<TblUser> tblUser) {
		this.tblUser = tblUser;
	}

	public String test(){
		tblUser=(genericService.findByProperty("userId", 1));
		return "SUCCESS";
	}
}
