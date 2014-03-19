package service;

import pojos.Staff;

public interface StaffService extends GenericService<Staff>{

	public Staff getStaffByUserNamePwd(String userName,String password);
	
	public Staff find(Integer id);
	
	public boolean login(String userName,String password);
	
	public boolean deleteStaff(String staffIds);
}
