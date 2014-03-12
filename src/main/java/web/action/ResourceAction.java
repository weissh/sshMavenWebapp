/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.ResourceAction
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-2     caiwenming       v1.0.0         create
 *
 */
package web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import pojos.Resource;
import pojos.Right;
import service.ResourceService;
import service.RightService;

public class ResourceAction extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//前端表单字段
	private Integer resourceId;
	private Integer rightId;
	private String resourceIds;
	private String resourceName;
	private String resourceType;
	private String resourceDesc;
	private String url;
	private Integer start;
	private Integer limit;
	private String query;
	
	//所需要的服务
	private ResourceService resourceService;
	private RightService rightService;
	
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public RightService getRightService() {
		return rightService;
	}

	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	//实现增删改查功能的具体方法
	public String addResource() {
		Right right=this.rightService.find(rightId);
		Resource resource=new Resource(resourceName, resourceDesc, resourceType, url, right);
		int resourceId=this.resourceService.save(resource);
		this.printString(true, resourceId+"");
		return null;
	}
	
	public String updateResource() {
		Resource resource=new Resource(resourceName, resourceDesc, resourceType, url, null);
		resource.setResourceId(resourceId);
		this.resourceService.update(this.resourceService.merge(resource));
		this.printString(true, resourceId+"");
		return null;
	}
	
	public String deleteResource() {
		String[] ids=this.resourceIds.split(",");
		ArrayList<Resource> resources=new ArrayList<Resource>();
		for(int i=0;i<ids.length;i++){
			Resource resource=this.resourceService.find(Integer.parseInt(ids[i]));
			resources.add(resource);
		}
		this.resourceService.removeAll(resources);
		this.printString(true, "");
		return null;
	}
	
	public String getAllResource() {
		int page =start/limit+1;
		List<Resource> resources=null;
		int total;
		resources=this.resourceService.findByPage(page, limit);
		total=this.resourceService.getTotalRows();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("right")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(start, limit, total, resources, jsonConfig);
		return null;
	}
	
	public String getResourceForSelector(){
		
		return null;
	}
}
