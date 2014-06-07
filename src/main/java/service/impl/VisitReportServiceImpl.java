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

import dao.StaffDao;
import dao.VisitReportDao;
import pojos.Staff;
import pojos.VisitReport;
import service.VisitReportService;

public class VisitReportServiceImpl extends GenericServiceImpl<VisitReport> implements VisitReportService{

	private VisitReportDao visitReportDao;
	private StaffDao staffDao;

	public VisitReportDao getVisitReportDao() {
		return visitReportDao;
	}

	public void setVisitReportDao(VisitReportDao visitReportDao) {
		this.visitReportDao = visitReportDao;
	}

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public int saveByManager(Integer staffId, VisitReport report) {
		Staff staff = this.staffDao.find(staffId);
		report.setStaff(staff);
		int reportId = visitReportDao.save(report);
		return reportId;
	}

}
