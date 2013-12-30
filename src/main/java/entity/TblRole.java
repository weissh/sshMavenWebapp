package entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TblRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_role", catalog = "test1")
public class TblRole extends AbstractTblRole implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblRole() {
	}

	/** minimal constructor */
	public TblRole(Integer roleId) {
		super(roleId);
	}

	/** full constructor */
	public TblRole(Integer roleId, String roleName,Set<TblPermission> tblPermissions) {
		super(roleId, roleName,tblPermissions);
	}

}
