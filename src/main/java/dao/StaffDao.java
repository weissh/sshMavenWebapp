package dao;

import java.util.List;

import pojos.Staff;

public interface StaffDao extends GenericDao<Staff>{

	public Staff getStaffByUserNamePassword(String userName,String password);
	
	public Staff loginStaff(String userName);
	
	public List<Staff> findByUserName(String userName);
}
