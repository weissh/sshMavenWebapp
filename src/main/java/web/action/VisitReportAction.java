/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.VisitReportAction
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
package web.action;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import pojos.Journal;
import pojos.Staff;
import pojos.VisitReport;
import service.StaffService;
import service.VisitReportService;
import web.ui.excel.VisitReportUI;
import web.ui.model.JournalModel;
import web.ui.model.VisitReportModel;

import common.ExcelUtil;

public class VisitReportAction extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private VisitReportService visitReportService;
	private StaffService staffService;
	
	
	public VisitReportService getVisitReportService() {
		return visitReportService;
	}
	public void setVisitReportService(VisitReportService visitReportService) {
		this.visitReportService = visitReportService;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	/** 导出excel表时的输入流、文件名称;以及get和set方法 */
	private InputStream excelStream;
	private String fileName;
	
	
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 销售员
	 *
	 */
	private Integer reportId;
	private String reportIds;
	private Date visitDate;
	private String visitPlace;
	private String visitAim;
	private Integer projectNo;
	private Date recordDate;
	private String customer;
	private String phone;
	private String email;
	private String company;
	private Integer ticket;
	private Integer accommodation;
	private Integer transportation;
	private Integer meals;
	private Integer subsidy;
	private Integer sum;
	private Integer number;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;
	private String abstract1;
	private String abstract2;
	private String abstract3;
	private String abstract4;
	
	private Integer departmentId;
	private Integer staffId;
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public String getReportIds() {
		return reportIds;
	}
	public void setReportIds(String reportIds) {
		this.reportIds = reportIds;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) throws ParseException {
		this.visitDate = df.parse(visitDate);
	}
	public String getVisitPlace() {
		return visitPlace;
	}
	public void setVisitPlace(String visitPlace) {
		this.visitPlace = visitPlace;
	}
	public String getVisitAim() {
		return visitAim;
	}
	public void setVisitAim(String visitAim) {
		this.visitAim = visitAim;
	}
	public Integer getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) throws ParseException {
		this.recordDate = df.parse(recordDate);
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getTicket() {
		return ticket;
	}
	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}
	public Integer getAccommodation() {
		return accommodation;
	}
	public void setAccommodation(Integer accommodation) {
		this.accommodation = accommodation;
	}
	public Integer getTransportation() {
		return transportation;
	}
	public void setTransportation(Integer transportation) {
		this.transportation = transportation;
	}
	public Integer getMeals() {
		return meals;
	}
	public void setMeals(Integer meals) {
		this.meals = meals;
	}
	public Integer getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(Integer subsidy) {
		this.subsidy = subsidy;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public String getAbstract1() {
		return abstract1;
	}
	public void setAbstract1(String abstract1) {
		this.abstract1 = abstract1;
	}
	public String getAbstract2() {
		return abstract2;
	}
	public void setAbstract2(String abstract2) {
		this.abstract2 = abstract2;
	}
	public String getAbstract3() {
		return abstract3;
	}
	public void setAbstract3(String abstract3) {
		this.abstract3 = abstract3;
	}
	public String getAbstract4() {
		return abstract4;
	}
	public void setAbstract4(String abstract4) {
		this.abstract4 = abstract4;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	private Integer start;
	private Integer limit;
	private String query;
	private Date startDate;
	private Date endDate;
	
	public int getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public int getLimit() {
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) throws ParseException {
		this.startDate = df.parse(startDate);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) throws ParseException {
		this.endDate = df.parse(endDate);
	}
	public String addReportPer(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		VisitReport report = new VisitReport(visitDate, visitPlace, visitAim, projectNo, recordDate, customer, phone, email, company, ticket, accommodation, transportation, meals, subsidy, sum, number, desc1, desc2, desc3, desc4, abstract1, abstract2, abstract3, abstract4);
		report.setStaff(staff);
		int id = this.visitReportService.save(report);
		this.printString(true, id+"");
		return null;
	}
	
	public String addReport(){
		if(staffId == null){
			this.printString(false, "参数错误");
			return null;
		}
		VisitReport report = new VisitReport(visitDate, visitPlace, visitAim, projectNo, recordDate, customer, phone, email, company, ticket, accommodation, transportation, meals, subsidy, sum, number, desc1, desc2, desc3, desc4, abstract1, abstract2, abstract3, abstract4);
		int id = this.visitReportService.saveByManager(staffId, report);
		this.printString(true, id + "");
		return null;
	}
	
	public String updateReport(){
		if(reportId == null || reportId == 0){
			this.printString(false, "获取参数错误！");
		}
		VisitReport report = new VisitReport(visitDate, visitPlace, visitAim, projectNo, recordDate, customer, phone, email, company, ticket, accommodation, transportation, meals, subsidy, sum, number, desc1, desc2, desc3, desc4, abstract1, abstract2, abstract3, abstract4);
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		report.setStaff(staff);
		report.setReportId(reportId);
		this.visitReportService.update(report);
		this.printString(true, report + "");
		return null;
	}
	
	public String deleteReport(){
		String[] str = this.reportIds.split(",");
		ArrayList<VisitReport> reports = new ArrayList<VisitReport>();
		for(int i = 0; i < str.length; i++) {
			VisitReport report = this.visitReportService.find(Integer.parseInt(str[i]));
			reports.add(report);
		}
		this.visitReportService.removeAll(reports);
		this.printString(true, "");
		return null;
	}
	
	public String getAllReport(){
		int page = start / limit + 1;
		List<VisitReport> reports = new ArrayList<VisitReport>();
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		String roleName = tempStaff.getRole().getRoleName();
		StringBuffer sql = null;

		// 如果是部门经理
		if (roleName.equals("部门经理")) {
			Integer departmentid = tempStaff.getDepartment().getDepartmentId();
			String sqll = new String(
					"from Staff where Department_DepartmentID=" + departmentid);
			List<Staff> staffs = this.staffService.findBysql(sqll);
			String staffid = null;
			for (int i = 0; i < staffs.size(); i++) {
				if (staffid == null) {
					staffid = staffs.get(i).getStaffId() + "";
				} else {
					staffid = staffid + "," + staffs.get(i).getStaffId() + "";
				}
			}
			sql = new StringBuffer("from VisitReport where Staff_StaffID in ("
					+ staffid + ")");
			// 如果存在其他查询条件
			if (query != null) {
				if (startDate != null && endDate != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String start = df.format(startDate);
					String end = df.format(endDate);
					sql.append(" and executeDate>='" + start + "'"
							+ " and executeDate<='" + end + "'");
				}
				if (staffId != 0) {
					sql.append(" and Staff_StaffID=" + staffId);
				}
			}

		}
		// 如果是管理员或者财务部员工或者财务部经理
		if (roleName.equals("管理员") || roleName.equals("行政人事员工")
				|| roleName.equals("行政人事部经理")||roleName.equals("总经理")) {
			sql = new StringBuffer("from VisitReport where 1=1");
			if (query != null) {
				if (startDate != null && endDate != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String start = df.format(startDate);
					String end = df.format(endDate);
					sql.append(" and VisitDate>='" + start + "'"
							+ " and VisitDate<='" + end + "'");
				}
				if (departmentId != 0) {
					String sqll = new String(
							"from Staff where Department_DepartmentID="
									+ departmentId);
					List<Staff> staffs = this.staffService.findBysql(sqll);
					String staffid = null;
					for (int i = 0; i < staffs.size(); i++) {
						if (staffid == null) {
							staffid = staffs.get(i).getStaffId() + "";
						} else {
							staffid = staffid + ","
									+ staffs.get(i).getStaffId() + "";
						}
					}
					sql.append("and Staff_StaffID in (" + staffid + ")");
				}
				if (staffId != 0) {
					sql.append(" and Staff_StaffID=" + staffId);
				}
			}
		}
		sql.append(" order by RecordDate desc");
		reports = this.visitReportService.findByPage(page, limit, sql.toString());
		total = this.visitReportService.getTotalRows(sql.toString());
		List<VisitReportModel> reportModels = new ArrayList<VisitReportModel>();
		if (reports.size() > 0) {
			reportModels = VisitReportModel.toVisitReportModels(reports);
		} else {
			reportModels = null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		this.printList(start, limit, total, reportModels, jsonConfig);
		return null;
	}
	
	public String getAllReportPer(){
		int page = start / limit + 1;
		List<VisitReport> reports = new ArrayList<VisitReport>();
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		StringBuffer sql = new StringBuffer("from VisitReport   where Staff_StaffID="
				+ tempStaff.getStaffId());
		if(query != null){
			if(startDate != null && endDate != null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String start = df.format(startDate);
				String end = df.format(endDate);
				sql.append(" and VisitDate>='" + start + "'"
						+ " and VisitDate<='" + end + "'");
			}
		}
		sql.append(" order by RecordDate desc");
		reports = this.visitReportService.findByPage(page, limit, sql.toString());
		total = this.visitReportService.getTotalRows(sql.toString());
		List<VisitReportModel> reportModels = new ArrayList<VisitReportModel>();
		if(reports.size() > 0){
			reportModels = VisitReportModel.toVisitReportModels(reports);
		}else{
			reportModels = null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		this.printList(start, limit, total, reportModels, jsonConfig);
		return null;
	}
	
	public String exportReportPer() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		List<VisitReport> reports = new ArrayList<VisitReport>();
		if(reportIds.equals("")){
			StringBuffer sql = new StringBuffer("from VisitReport where Staff_StaffID =" + tempStaff.getStaffId());
			reports = this.visitReportService.findBysql(sql.toString());
		}else{
			reports = this.visitReportService.findBysql("from VisitReport where ReportID in (" + reportIds +")");
		}
		List<VisitReportModel> models = new ArrayList<VisitReportModel>();
		models = VisitReportModel.toVisitReportModels(reports);
		Vector<String> head = VisitReportUI.getHead();
		List<Vector<String>> dataList = VisitReportUI.getDataList(models);
		String downLoadPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "excel\\";
		String fileName = ExcelUtil.createFileName("VisitReport") + ".xls";
		if (ExcelUtil.printExcel(head, dataList, downLoadPath + fileName)) {
			download(fileName);
			return "success";
		} else {
			this.printString(false, "");
		}
		return null;
	}
	
	public String exportReport() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		String roleName = tempStaff.getRole().getRoleName();
		List<VisitReport> reports = new ArrayList<VisitReport>();
		if(reportIds.equals("")){
			StringBuffer sql = null;
			if (roleName.equals("管理员") || roleName.equals("行政人事员工")
					|| roleName.equals("行政人事部经理")) {
				reports = this.visitReportService.findAll();
			} else if (roleName.equals("部门经理")) {
				int departmentid = tempStaff.getDepartment().getDepartmentId();
				String sqll = new String(
						"from Staff where Department_DepartmentID="
								+ departmentid);
				List<Staff> staff2 = this.staffService.findBysql(sqll);
				String staffid = null;
				for (int i = 0; i < staff2.size(); i++) {
					if (staffid == null) {
						staffid = staff2.get(i).getStaffId() + "";
					} else {
						staffid = staffid + "," + staff2.get(i).getStaffId()
								+ "";
					}
				}
				sql = new StringBuffer("from VisitReport where Staff_StaffID in ("
						+ staffid + ")");
				reports = this.visitReportService.findBysql(sql.toString());
			}
		}else{
			reports = this.visitReportService.findBysql("from VisitReport where ReportID in (" + reportIds +")");
		}
		List<VisitReportModel> models = new ArrayList<VisitReportModel>();
		models = VisitReportModel.toVisitReportModels(reports);
		Vector<String> head = VisitReportUI.getHead();
		List<Vector<String>> dataList = VisitReportUI.getDataList(models);
		String downLoadPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "excel\\";
		String fileName = ExcelUtil.createFileName("VisitReport") + ".xls";
		if (ExcelUtil.printExcel(head, dataList, downLoadPath + fileName)) {
			download(fileName);
			return "success";
		} else {
			this.printString(false, "");
		}
		return null;
	}
	
	public void download(String fileName) throws Exception {
		this.fileName = fileName;
		this.excelStream = ServletActionContext.getServletContext()
				.getResourceAsStream("excel/" + fileName);
	}
}
