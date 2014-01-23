package service.impl;

import pojos.Department;
import service.DepartmentService;
import dao.DepartmentDao;

public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService{
	//必须要有该属性，并且与applicationContext.xml中的配置相对应
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
}
