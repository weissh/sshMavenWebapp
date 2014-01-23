package dao.hibernate;

import dao.StaffDao;
import pojos.Staff;

public class StaffDaoHib extends GenericDaoHib<Staff> implements StaffDao{

	public StaffDaoHib() {
		super(Staff.class);
	}

}
