package pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Integer departmentId;
	private String departmentName;
	private Date createTime;
	private Integer managerId;
	private String managerName;
	private Integer totalStaff;
	private String description;
	private Set<Staff> staffs = new HashSet<Staff>(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	//前端部门下拉列表数据源
	public Department(int departmentId,String departmentName){
		this.departmentId=departmentId;
		this.departmentName=departmentName;
	}
	
	//前端表单构造函数
	public Department(String departmentName,Date createTime,String description){
		this.departmentName=departmentName;
		this.createTime=createTime;
		this.description=description;
	}
	
	/** full constructor */
	public Department(String departmentName, Date createTime, Integer managerId,
			Integer totalStaff, String description, Set<Staff> staffs) {
		this.departmentName = departmentName;
		this.createTime=createTime;
		this.managerId = managerId;
		this.totalStaff = totalStaff;
		this.description = description;
		this.staffs = staffs;
	}

	// Property accessors

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
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

	public Integer getManagerId() {
		return this.managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getTotalStaff() {
		return this.totalStaff;
	}

	public void setTotalStaff(Integer totalStaff) {
		this.totalStaff = totalStaff;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

}