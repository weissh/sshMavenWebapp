package entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TblPermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_permission", catalog = "test1")
public class TblPermission extends AbstractTblPermission implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblPermission() {
	}

	/** minimal constructor */
	public TblPermission(String permissionId) {
		super(permissionId);
	}

	/** full constructor */
	public TblPermission(String permissionId, String permissionName) {
		super(permissionId, permissionName);
	}

}
