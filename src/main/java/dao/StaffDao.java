package dao;

import pojos.Staff;

public interface StaffDao extends GenericDao<Staff>{

	public Staff getStaffByUserNamePassword(String userName,String password);
	
}
