/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:pojos.VisitReport
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-5-24     caiwenming       v1.0.0         create
 *
 */
package pojos;

import java.io.Serializable;
import java.util.Date;

public class VisitReport implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer reportId;
	private Staff staff;
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

	public VisitReport() {
	}

	public VisitReport(Date visitDate, String visitPlace, String visitAim,
			Integer projectNo, Date recordDate, String customer, String phone,
			String email, String company, Integer ticket,
			Integer accommodation, Integer transportation, Integer meals,
			Integer subsidy, Integer sum, Integer number, String desc1,
			String desc2, String desc3, String desc4, String abstract1,
			String abstract2, String abstract3, String abstract4) {
		this.visitDate = visitDate;
		this.visitPlace = visitPlace;
		this.visitAim = visitAim;
		this.projectNo = projectNo;
		this.recordDate = recordDate;
		this.customer = customer;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.ticket = ticket;
		this.accommodation = accommodation;
		this.transportation = transportation;
		this.meals = meals;
		this.subsidy = subsidy;
		this.sum =sum;
		this.number = number;
		this.desc1 = desc1;
		this.desc2 = desc2;
		this.desc3 = desc3;
		this.desc4 = desc4;
		this.abstract1 = abstract1;
		this.abstract2 = abstract2;
		this.abstract3 = abstract3;
		this.abstract4 = abstract4;
		
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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

}