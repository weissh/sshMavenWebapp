package service.impl;

import java.util.List;

import dao.StaffDao;
import freemarker.core.ReturnInstruction.Return;
import pojos.Staff;
import service.StaffService;

public class StaffServiceImpl extends GenericServiceImpl<Staff> implements StaffService{

//	private StaffService staffService;
//	
//	public StaffService getStaffService() {
//		return staffService;
//	}
//
//	public void setStaffService(StaffService staffService) {
//		this.staffService = staffService;
//	}

	private StaffDao staffDao;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public Staff getStaffByUserNamePwd(String userName, String password) {
		List<Staff> staffs=this.findByProperty("userName", userName);
		if(staffs.size()>0&&staffs.get(0).getPassword().equals(password)){
			return staffs.get(0);
		}
		return null;
	}
	
	public Staff find(Integer id){
		return this.staffDao.find(id);
	}
}
