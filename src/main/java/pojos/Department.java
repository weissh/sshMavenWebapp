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
	private Integer totalStaff;
	private String description;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
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
			Integer totalStaff, String description, String prop1, String prop2,
			String prop3, String prop4, String prop5, Set<Staff> staffs) {
		this.departmentName = departmentName;
		this.createTime=createTime;
		this.managerId = managerId;
		this.totalStaff = totalStaff;
		this.description = description;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
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

	public String getProp1() {
		return this.prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return this.prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return this.prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getProp4() {
		return this.prop4;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public String getProp5() {
		return this.prop5;
	}

	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}

	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

}