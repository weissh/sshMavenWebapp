package service;

import pojos.Staff;

public interface StaffService extends GenericService<Staff>{

	public Staff getStaffByUserNamePwd(String userName,String password);
}
