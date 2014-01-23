package service.impl;

import dao.StaffDao;
import pojos.Staff;
import service.StaffService;

public class StaffServiceImpl extends GenericServiceImpl<Staff> implements StaffService{

	private StaffDao staffDao;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
}
