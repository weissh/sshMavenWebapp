package dao.hibernate;

import pojos.Department;
import dao.DepartmentDao;

public class DepartmentDaoHib extends GenericDaoHib<Department> implements DepartmentDao {

	public DepartmentDaoHib() {
		super(Department.class);
	}

}
