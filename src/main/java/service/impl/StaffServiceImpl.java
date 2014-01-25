package service.impl;

import dao.StaffDao;
import pojos.Staff;
import service.StaffService;

public class StaffServiceImpl extends GenericServiceImpl<Staff> implements StaffService{

	private StaffService staffService;
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	private StaffDao staffDao;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public Staff getStaffByUserNamePwd(String userName, String password) {
		return this.staffDao.getStaffByUserNamePassword(userName, password);
	}
	
}
