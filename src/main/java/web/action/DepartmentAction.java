package web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Department;
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
	private int departmentId;
	private String departmentName;
	private Date createTime;
	private String description;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
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
		if(departmentId==0){
			this.printString(false, "获取参数错误！");
			return null;
		}
		Department department =this.departmentService.find(Department.class, departmentId);
		department.setDepartmentName(departmentName);
		department.setCreateTime(createTime);
		department.setDescription(description);
		this.departmentService.update(department);
		this.printString(true, departmentId+"");
		return null;
	}

	// 删除部门
	public String deleteDept() {
		//如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4....
		String[] str=this.departmentIds.split(",");
		ArrayList<Department> departments= new ArrayList<Department>();
		//遍历id，并实例化类型，在add到List
		for(int i=0;i<str.length;i++){
			Department department = new Department();
			department.setDepartmentId(Integer.parseInt(str[i]));
			departments.add(department);
		}
		this.departmentService.removeAll(departments);
		this.printString(true, "");
		return null;
	}

	//获取部门信息列表
	public String getAllDept() {
		List<Department> departments = this.departmentService.findAll();
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staffs")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, departments, jsonConfig);
		return null;
	}
	
	//仅获得部门的departmentId和departmentName属性，并以json返回:作为前端部门下拉列表的值
	public String getDeptForSelector(){
		String sql="select new Department(dept.departmentId,dept.departmentName) from Department dept";
		List<Department> departments=this.departmentService.findBysql(sql);
		this.printList(0, 0, 0, departments);
		return null;
	}
}
