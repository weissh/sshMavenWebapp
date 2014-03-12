package dao.hibernate;

import java.util.List;

import pojos.Staff;
import dao.StaffDao;

public class StaffDaoHib extends GenericDaoHib<Staff> implements StaffDao{


	public StaffDaoHib(){
		super(Staff.class);
	}

//	public Staff getStaffByUserNamePassword(String userName, String password) {
//		System.out.println(userName);
//		List<Staff> staff=this.findByProperty("userName", userName);
//		System.out.println(staff.size());
//		if(staff!=null&&staff.get(0).getPassword().equals(password)){
//			return staff.get(0);
//		}
//		return null;
//	}

	@Override
	public Staff loginStaff(String userName) {
		System.out.println(userName);
		List<Staff> staffs=this.findByProperty("userName", userName);
		System.out.println(staffs.size());
		if(staffs.size()>0){
			return staffs.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Staff> findByUserName(String userName) {
		List<Staff> staffs=this.findByProperty("userName", userName);
		if(staffs.size()>0){
			return staffs;
		}
		return null;
	}
}
