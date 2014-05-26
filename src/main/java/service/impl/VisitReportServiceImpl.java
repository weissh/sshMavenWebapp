/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:service.impl.VisitReportServiceImpl
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
package service.impl;

import dao.VisitReportDao;
import pojos.VisitReport;
import service.VisitReportService;

public class VisitReportServiceImpl extends GenericServiceImpl<VisitReport> implements VisitReportService{

	private VisitReportDao visitReportDao;

	public VisitReportDao getVisitReportDao() {
		return visitReportDao;
	}

	public void setVisitReportDao(VisitReportDao visitReportDao) {
		this.visitReportDao = visitReportDao;
	}

}
