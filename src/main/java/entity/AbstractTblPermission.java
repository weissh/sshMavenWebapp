package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractTblPermission entity provides the base persistence definition of the
 * TblPermission entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTblPermission implements java.io.Serializable {

	// Fields

	private Integer permissionId;
	private String permissionName;

	// Constructors

	/** default constructor */
	public AbstractTblPermission() {
	}

	/** minimal constructor */
	public AbstractTblPermission(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/** full constructor */
	public AbstractTblPermission(Integer permissionId, String permissionName) {
		this.permissionId = permissionId;
		this.permissionName = permissionName;
	}

	// Property accessors
	@Id
	@Column(name = "PERMISSION_ID", unique = true, nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "PERMISSION_NAME", unique = true, length = 45)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}