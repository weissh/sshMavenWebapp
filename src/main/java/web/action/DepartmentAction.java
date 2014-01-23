package web.action;

import pojos.Department;
import service.DepartmentService;

public class DepartmentAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DepartmentService departmentService;
	
	private Department department;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String save(){
		Department dept=new Department("12", 123, 12, null, null, null, null, null, null, null);
		departmentService.save(dept);
		System.out.println("存储成功！");
		return "SUCCESS";
	}

}
