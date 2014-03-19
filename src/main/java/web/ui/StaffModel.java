/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.StaffModel
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-19     caiwenming       v1.0.0         create
 *
 */
package web.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Staff;

public class StaffModel {
	private int staffId;
	private int departmentId;
	private String departmentName;
	private int roleId;
	private String roleName;
	private String photo;
	private String staffName;
	private Date entryTime;
	private String position;
	private String phone;
	private String email;
	private String urgentContact;
	private String ucPhone;
	private String gender;
	private String nationality;
	private String politicalStatus;
	private int age;
	private Date birthday;
	private String maritalStatus;
	private String idNo;
	private String passportNo;
	private String nativePlace;
	private String domicilePlace;
	private Date dateOfRecruitment;
	private String currentAddress;
	private String zipCode;
	private String graduateSchool;
	private String hightestEdu;
	private String hightestDegree;
	private String major;
	private String schoolSystem;
	private String userName;
	private String password;
	private String staffDesc;
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getUrgentContact() {
		return urgentContact;
	}
	public void setUrgentContact(String urgentContact) {
		this.urgentContact = urgentContact;
	}
	public String getUcPhone() {
		return ucPhone;
	}
	public void setUcPhone(String ucPhone) {
		this.ucPhone = ucPhone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getDomicilePlace() {
		return domicilePlace;
	}
	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}
	public Date getDateOfRecruitment() {
		return dateOfRecruitment;
	}
	public void setDateOfRecruitment(Date dateOfRecruitment) {
		this.dateOfRecruitment = dateOfRecruitment;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getHightestEdu() {
		return hightestEdu;
	}
	public void setHightestEdu(String hightestEdu) {
		this.hightestEdu = hightestEdu;
	}
	public String getHightestDegree() {
		return hightestDegree;
	}
	public void setHightestDegree(String hightestDegree) {
		this.hightestDegree = hightestDegree;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSchoolSystem() {
		return schoolSystem;
	}
	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStaffDesc() {
		return staffDesc;
	}
	public void setStaffDesc(String staffDesc) {
		this.staffDesc = staffDesc;
	}
	
	public void toStaffModel(Staff staff){
		this.staffId=staff.getStaffId();
		this.departmentId = staff.getDepartment().getDepartmentId();
		this.departmentName=staff.getDepartment().getDepartmentName();
		this.roleId=staff.getRole().getRoleId();
		this.roleName=staff.getRole().getRoleName();
		this.photo=staff.getPhoto();
		this.staffName = staff.getStaffName();
		this.entryTime = staff.getEntryTime();
		this.position = staff.getPosition();
		this.phone = staff.getPhone();
		this.email = staff.getEmail();
		this.urgentContact = staff.getUrgentContact();
		this.ucPhone = staff.getUcPhone();
		this.gender = staff.getGender();
		this.nationality = staff.getNationality();
		this.politicalStatus = staff.getPoliticalStatus();
		this.age=staff.getAge();
		this.birthday = staff.getBirthday();
		this.maritalStatus = staff.getMaritalStatus();
		this.idNo = staff.getIdNo();
		this.passportNo = staff.getPassportNo();
		this.nativePlace = staff.getNativePlace();
		this.domicilePlace = staff.getDomicilePlace();
		this.dateOfRecruitment=staff.getDateOfRecruitment();
		this.currentAddress = staff.getCurrentAddress();
		this.zipCode = staff.getZipCode();
		this.graduateSchool = staff.getGraduateSchool();
		this.hightestEdu = staff.getHightestEdu();
		this.hightestDegree = staff.getHightestDegree();
		this.major = staff.getMajor();
		this.schoolSystem = staff.getSchoolSystem();
		this.userName=staff.getUserName();
		this.password = staff.getPassword();
		this.staffDesc=staff.getStaffDesc();
	}
	
	public static List<StaffModel> toStaffModels(List<Staff> staffs){
		List<StaffModel> staffModels=new ArrayList<StaffModel>();
		StaffModel staffModel;
		for(int i=0;i<staffs.size();i++){
			staffModel=new StaffModel();
			staffModel.toStaffModel(staffs.get(i));
			staffModels.add(staffModel);
		}
		return staffModels;
	}
}
