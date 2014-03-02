/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:dao.hibernate.ResourceDaoHib
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
package dao.hibernate;

import pojos.Resource;
import dao.ResourceDao;

public class ResourceDaoHib extends GenericDaoHib<Resource> implements ResourceDao{

	public ResourceDaoHib() {
		super(Resource.class);
	}

}
