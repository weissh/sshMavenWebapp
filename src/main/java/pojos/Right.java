package pojos;

import java.util.HashSet;
import java.util.Set;

import javassist.expr.NewArray;

/**
 * Right entity. @author MyEclipse Persistence Tools
 */

public class Right implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Integer rightId;
	private String rightName;
	private String rightDesc;
	private Integer enabled;
	private Integer isSuper;
	private String mark;
	private String description;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
	
	//不需要通过权限查找相应的角色，故可不需要 private Set roles = new HashSet(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Resource> resources =new HashSet<Resource>(0);

	// Constructors

	/** default constructor */
	public Right() {
	}

	/** full constructor */
	public Right(String rightName, String mark, String description,
			String prop1, String prop2, String prop3, String prop4,
			String prop5, Set<Role> roles, Set<Resource>resources) {
		this.rightName = rightName;
		this.mark = mark;
		this.description = description;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
		this.roles = roles;
		this.resources=resources;
	}

	// Property accessors

	public Integer getRightId() {
		return this.rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}