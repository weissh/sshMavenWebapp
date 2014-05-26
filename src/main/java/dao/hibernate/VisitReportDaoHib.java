/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:dao.hibernate.VisitReportDaoHib
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-5-25     caiwenming       v1.0.0         create
 *
 */
package dao.hibernate;

import pojos.VisitReport;
import dao.VisitReportDao;

public class VisitReportDaoHib extends GenericDaoHib<VisitReport> implements VisitReportDao{

	public VisitReportDaoHib() {
		super(VisitReport.class);
	}

}
