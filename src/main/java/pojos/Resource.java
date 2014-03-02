/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:pojos.Resource
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-1     caiwenming       v1.0.0         create
 *
 */
package pojos;

import java.util.HashSet;
import java.util.Set;


public class Resource implements java.io.Serializable{

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer resourceId;
	private String resourceName;
	private String resourceDesc;
	private String resourceType;
	private String url;
	private String proiority;
	private Integer enabled;
	private Integer isSuper;
	private String module;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
	private Set<Right> rights = new HashSet<Right>(0);

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** full constructor */
	public Resource(String resourceName, String resourceDesc,
			String resourceType, String url, String proiority, Integer enabled,
			Integer isSuper, String module, String prop1, String prop2,
			String prop3, String prop4, String prop5, Set<Right> rights) {
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
		this.resourceType = resourceType;
		this.url = url;
		this.proiority = proiority;
		this.enabled = enabled;
		this.isSuper = isSuper;
		this.module = module;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
		this.rights = rights;
	}

	// Property accessors

	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProiority() {
		return this.proiority;
	}

	public void setProiority(String proiority) {
		this.proiority = proiority;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getIsSuper() {
		return this.isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
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

	public Set<Right> getRights() {
		return this.rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}
}
