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

import java.util.Date;

import service.StaffService;
import service.VisitReportService;

public class VisitReportAction extends BaseAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
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
	/**
	 * 销售员
	 *
	 */
	private Integer reportId;
	private Date visitDate;
	private String visitPlace;
	private String visitAim;
	private Integer projectNo;
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

	
	
	public String addReport(){
		return null;
	}
	
	public String addReportPer(){
		return null;
	}
	
	public String updateReport(){
		return null;
	}
	
	public String deleteReport(){
		return null;
	}
	
	public String getAllReport(){
		return null;
	}
	
	public String getAllReportPer(){
		return null;
	}
}
