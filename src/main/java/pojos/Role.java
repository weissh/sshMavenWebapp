package pojos;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Integer roleId;
	private String roleName;
	private String description;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
	//不需要通过角色查找相应的员工，故可不需要 private Set staffs = new HashSet(0);
	private Set<Staff> staffs = new HashSet<Staff>(0);
	
	private Set<Right> rights = new HashSet<Right>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String roleName, String description, String prop1,
			String prop2, String prop3, String prop4, String prop5, Set<Staff> staffs,
			Set<Right> rights) {
		this.roleName = roleName;
		this.description = description;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
		this.staffs = staffs;
		this.rights = rights;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Set<Right> getRights() {
		return this.rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}