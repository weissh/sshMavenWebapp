package web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Department;
import pojos.Staff;
import service.DepartmentService;

public class DepartmentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DepartmentService departmentService;

	private Department department;

	private String departmentIds;
	
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
	
	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}


	// 获取前端表单属性 start
	private String departmentId;
	private String departmentName;
	private Date createTime;
	private String description;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// 获取前端表单属性 end
	
	// 新增部门
	public String addDept(){
		if (departmentName == null || createTime == null || description == null) {
			this.printString(false, "参数获取错误！");
			return null;
		}
		if (this.departmentService.findByProperty("departmentName",
				departmentName).size() > 0) {
			this.printString(false, "该部门已经存在！");
			return null;
		}
		Department department = new Department(departmentName, createTime, description);
		int departmentId = this.departmentService.save(department);
		this.printString(true, departmentId + "");
		return null;
	}

	// 修改部门
	public String updateDept() {
		if(Integer.parseInt(departmentId)==0){
			this.printString(false, "获取参数错误！");
			return null;
		}
		Department department =this.departmentService.find(Department.class, Integer.parseInt(departmentId));
		department.setDepartmentName(departmentName);
		department.setCreateTime(createTime);
		department.setDescription(description);
		this.departmentService.update(department);
		this.printString(true, departmentId);
		return null;
	}

	// 删除部门
	public String deleteDept() {
		System.out.println(departmentIds);
		String[] str=this.departmentIds.split(",");
		ArrayList<Department> departments= new ArrayList<Department>();
		for(int i=0;i<str.length;i++){
			Department department = new Department();
			department.setDepartmentId(Integer.parseInt(str[i]));
			departments.add(department);
		}
		this.departmentService.removeAll(departments);
		this.printString(true, "");
		return null;
	}

	public String getAllDept() {
		List<Department> departments = this.departmentService.findAll();
		String[] properties = { "staffName" };
		this.printList(0, 0, 0, departments, properties, Staff.class);
		return null;
	}
}
