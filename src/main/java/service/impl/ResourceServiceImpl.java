/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:service.impl.ResourceServiceImpl
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
package service.impl;

import dao.ResourceDao;
import pojos.Resource;
import service.ResourceService;

public class ResourceServiceImpl extends GenericServiceImpl<Resource> implements ResourceService{

	private ResourceDao resourceDao;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
}
