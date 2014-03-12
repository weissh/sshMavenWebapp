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
	
//	private Set<Right> rights = new HashSet<Right>(0);
	private Right right;

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** full constructor */
	public Resource(String resourceName, String resourceDesc,
			String resourceType, String url, Right right) {
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
		this.resourceType = resourceType;
		this.url = url;
		this.right = right;
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

//
//	public Set<Right> getRights() {
//		return this.rights;
//	}
//
//	public void setRights(Set<Right> rights) {
//		this.rights = rights;
//	}

	public Right getRight() {
		return right;
	}

	public void setRight(Right right) {
		this.right = right;
	}
}
