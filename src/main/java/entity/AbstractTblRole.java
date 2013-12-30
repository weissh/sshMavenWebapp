package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

/**
 * AbstractTblRole entity provides the base persistence definition of the
 * TblRole entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTblRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Set<TblPermission> tblPermissions = new HashSet<TblPermission>(0);
	//private Set<TblUser> tblUsers = new HashSet<TblUser>(0);

	// Constructors

	/** default constructor */
	public AbstractTblRole() {
	}

	/** minimal constructor */
	public AbstractTblRole(Integer roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public AbstractTblRole(Integer roleId, String roleName,Set<TblPermission> tblPermissions) {//,Set<TblPermission> tblPermissions, Set<TblUser> tblUsers
		this.roleId = roleId;
		this.roleName = roleName;
		this.tblPermissions = tblPermissions;
		//this.tblUsers = tblUsers;
	}

	// Property accessors
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", unique = true, length = 45)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_permission_role", catalog = "test1", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) })
	public Set<TblPermission> getTblPermissions() {
		return this.tblPermissions;
	}

	public void setTblPermissions(Set<TblPermission> tblPermissions) {
		this.tblPermissions = tblPermissions;
	}

	/*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tblRoles")
	public Set<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(Set<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}*/

}