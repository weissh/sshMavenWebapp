package service;

import pojos.Department;

public interface DepartmentService extends GenericService<Department>{
	public String deleteDept(String departmentIds);
	public void manageStaff(int source,int target);
}
