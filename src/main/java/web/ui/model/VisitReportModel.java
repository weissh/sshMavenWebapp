package web.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Department;
import pojos.Staff;
import pojos.VisitReport;

public class VisitReportModel {

	private Integer reportId;
	private Integer staffId;
	private String staffName;
	private String departmentName;
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
	
	
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
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
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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
	public VisitReportModel(){}
	
	public VisitReportModel(VisitReport vr) {
		this.reportId  = vr.getReportId();
		Staff staff = vr.getStaff();
		if(staff != null){
			this.staffId = staff.getStaffId();
			this.staffName = staff.getStaffName();
			Department department = staff.getDepartment();
			if(department != null){
				this.departmentName = department.getDepartmentName();
			}
		}
		this.visitDate = vr.getVisitDate();
		this.visitPlace = vr.getVisitPlace();
		this.visitAim = vr.getVisitAim();
		this.projectNo = vr.getProjectNo();
		this.recordDate = vr.getRecordDate();
		this.customer = vr.getCustomer();
		this.phone = vr.getPhone();
		this.email = vr.getEmail();
		this.company = vr.getCompany();
		this.ticket = vr.getTicket();
		this.accommodation = vr.getAccommodation();
		this.transportation = vr.getTransportation();
		this.meals = vr.getMeals();
		this.subsidy = vr.getSubsidy();
		this.sum =vr.getSum();
		this.number = vr.getNumber();
		this.desc1 = vr.getDesc1();
		this.desc2 = vr.getDesc2();
		this.desc3 = vr.getAbstract3();
		this.desc4 = vr.getAbstract4();
		this.abstract1 = vr.getAbstract1();
		this.abstract2 = vr.getAbstract2();
		this.abstract3 = vr.getAbstract3();
		this.abstract4 = vr.getAbstract4();
	}
	
	public static List<VisitReportModel> toVisitReportModels(List<VisitReport> reports){
		List<VisitReportModel> visitReportModels=new ArrayList<VisitReportModel>();
		for(int i=0;i<reports.size();i++){
			VisitReportModel visitReportModel=new VisitReportModel(reports.get(i));
			visitReportModels.add(visitReportModel);
		}
		return visitReportModels;
	}
}
