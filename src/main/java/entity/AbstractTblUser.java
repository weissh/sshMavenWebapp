package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractTblUser entity provides the base persistence definition of the
 * TblUser entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractTblUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userUsername;
	private String userPassword;
	private Set<TblRole> tblRoles = new HashSet<TblRole>(0);

	// Constructors

	/** default constructor */
	public AbstractTblUser() {
	}

	/** minimal constructor */
	public AbstractTblUser(String userUsername, String userPassword) {
		this.userUsername = userUsername;
		this.userPassword = userPassword;
	}

	/** full constructor */
	public AbstractTblUser(String userUsername, String userPassword,
			Set<TblRole> tblRoles) {
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.tblRoles = tblRoles;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_USERNAME", unique = true, nullable = false, length = 45)
	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	@Column(name = "USER_PASSWORD", nullable = false, length = 32)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_role", catalog = "test1", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
	public Set<TblRole> getTblRoles() {
		return this.tblRoles;
	}

	public void setTblRoles(Set<TblRole> tblRoles) {
		this.tblRoles = tblRoles;
	}

}