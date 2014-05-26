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

public class VisitReport  implements  Serializable{

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
	private Integer phone;
	private String email;
	private String company;
	private Integer ticket;
	private Integer accommodation;
	private Integer transportation;
	private Integer meals;
	private Integer subsidy;
	private Integer sum;
	private Integer number;
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
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
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
	
	
}
