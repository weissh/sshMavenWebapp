package web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Department;
import service.DepartmentService;

public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 获取department服务、以及get和set方法  */
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/** 获取前端表单所有字段  */
	private String departmentIds;
	private int departmentId;
	private String departmentName;
	private Date createTime;
	private String description;

	/** 前端表单所有字段的get和set方法  */
	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	
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

	/**
	 *
	 * @Description:新增部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:11:44
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
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

	/**
	 *
	 * @Description:修改部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:12:18
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
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

	/**
	 *
	 * @Description:删除部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:12:33
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String deleteDept() {
		/**如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4.... */
		String[] str=this.departmentIds.split(",");
		ArrayList<Department> departments= new ArrayList<Department>();
		/**遍历id，并实例化类型，在add到List */
		for(int i=0;i<str.length;i++){
			Department department = new Department();
			/** 因为Department与其他实体类不存在多对一的关系，即数据表中没有外键，所有新建对象只许设置id即可 */
			department.setDepartmentId(Integer.parseInt(str[i]));
			departments.add(department);
		}
		this.departmentService.removeAll(departments);
		this.printString(true, "");
		return null;
	}

	/**
	 *
	 * @Description:获取所有的部门信息
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:15:38
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
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
	
	/**
	 *
	 * @Description:仅获得部门的departmentId和departmentName属性，并以json返回:作为前端部门下拉列表的值
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:16:13
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String getDeptForSelector(){
		String sql="select new Department(dept.departmentId,dept.departmentName) from Department dept";
		List<Department> departments=this.departmentService.findBysql(sql);
		this.printList(0, 0, 0, departments);
		return null;
	}
}
