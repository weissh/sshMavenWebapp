package entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TblUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_user", catalog = "test1")
public class TblUser extends AbstractTblUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblUser() {
	}

	/** minimal constructor */
	public TblUser(String userUsername, String userPassword) {
		super(userUsername, userPassword);
	}

	/** full constructor */
	public TblUser(String userUsername, String userPassword,
			Set<TblRole> tblRoles) {
		super(userUsername, userPassword, tblRoles);
	}

}
