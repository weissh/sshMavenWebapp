package common;

import pojos.Department;
import pojos.Role;
import service.DepartmentService;
import service.RoleService;

public class Test 
{
	

	RoleService roleService;
	DepartmentService departmentService;
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void find(){
		Role role =this.roleService.find(Role.class, 1);
		System.out.println(role.getRoleId());
		System.out.println(role.getRoleName());
	}

	public void search(){
		Department d=this.departmentService.find(Department.class, 17);
		System.out.println(d.getDepartmentName());
	}
	
	public Test(){
		
	};
	
	
	
    public static void main(String[] args)
    {
        Test t = new Test();
        t.search();
        //t.find();
    }
}
