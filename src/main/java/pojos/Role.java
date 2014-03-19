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
	private String roleDesc;
	//不需要通过角色查找相应的员工，故可不需要 private Set staffs = new HashSet(0);
	private Set<Staff> staffs = new HashSet<Staff>(0);
	
	private Set<Right> rights = new HashSet<Right>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String roleName, String roleDesc, Set<Staff> staffs,
			Set<Right> rights) {
		this.roleName = roleName;
		this.roleDesc=roleDesc;
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

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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