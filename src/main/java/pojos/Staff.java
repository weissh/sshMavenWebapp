package pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer staffId;
	private Department department;
	private String staffName;
	private Date entryTime;
	private String position;
	private String phone;
	private String email;
	private String urgentContact;
	private String ucphone;
	private String gender;
	private String nationality;
	private String politicalStatus;
	private String staffinfocol;
	private Date birthday;
	private String maritalStatus;
	private String idno;
	private String passportNo;
	private String nativePlace;
	private String domicilePlace;
	private String currentAddress;
	private String zipCode;
	private String graduateSchool;
	private String hightestEdu;
	private String hightestDegree;
	private String major;
	private String schoolSystem;
	private String userName;
	private String password;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;
	private Set costs = new HashSet(0);
	private Set roles = new HashSet(0);
	private Set journals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	public Staff(int userName,String password){
		this.staffId=userName;
		this.password=password;
	}
	
	/** minimal constructor */
	public Staff(Department department, String staffName) {
		this.department = department;
		this.staffName = staffName;
	}

	/** full constructor */
	public Staff(Department department, String staffName, Date entryTime,
			String position, String phone, String email, String urgentContact,
			String ucphone, String gender, String nationality,
			String politicalStatus, String staffinfocol, Date birthday,
			String maritalStatus, String idno, String passportNo,
			String nativePlace, String domicilePlace, String currentAddress,
			String zipCode, String graduateSchool, String hightestEdu,
			String hightestDegree, String major, String schoolSystem,String userName,
			String password, String prop1, String prop2, String prop3,
			String prop4, String prop5, Set costs, Set roles, Set journals) {
		this.department = department;
		this.staffName = staffName;
		this.entryTime = entryTime;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.urgentContact = urgentContact;
		this.ucphone = ucphone;
		this.gender = gender;
		this.nationality = nationality;
		this.politicalStatus = politicalStatus;
		this.staffinfocol = staffinfocol;
		this.birthday = birthday;
		this.maritalStatus = maritalStatus;
		this.idno = idno;
		this.passportNo = passportNo;
		this.nativePlace = nativePlace;
		this.domicilePlace = domicilePlace;
		this.currentAddress = currentAddress;
		this.zipCode = zipCode;
		this.graduateSchool = graduateSchool;
		this.hightestEdu = hightestEdu;
		this.hightestDegree = hightestDegree;
		this.major = major;
		this.schoolSystem = schoolSystem;
		this.userName=userName;
		this.password = password;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
		this.costs = costs;
		this.roles = roles;
		this.journals = journals;
	}

	// Property accessors

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getEntryTime() {
		return this.entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrgentContact() {
		return this.urgentContact;
	}

	public void setUrgentContact(String urgentContact) {
		this.urgentContact = urgentContact;
	}

	public String getUcphone() {
		return this.ucphone;
	}

	public void setUcphone(String ucphone) {
		this.ucphone = ucphone;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPoliticalStatus() {
		return this.politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getStaffinfocol() {
		return this.staffinfocol;
	}

	public void setStaffinfocol(String staffinfocol) {
		this.staffinfocol = staffinfocol;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getPassportNo() {
		return this.passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getDomicilePlace() {
		return this.domicilePlace;
	}

	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}

	public String getCurrentAddress() {
		return this.currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getHightestEdu() {
		return this.hightestEdu;
	}

	public void setHightestEdu(String hightestEdu) {
		this.hightestEdu = hightestEdu;
	}

	public String getHightestDegree() {
		return this.hightestDegree;
	}

	public void setHightestDegree(String hightestDegree) {
		this.hightestDegree = hightestDegree;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchoolSystem() {
		return this.schoolSystem;
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
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProp1() {
		return this.prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return this.prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return this.prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getProp4() {
		return this.prop4;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public String getProp5() {
		return this.prop5;
	}

	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}

	public Set getCosts() {
		return this.costs;
	}

	public void setCosts(Set costs) {
		this.costs = costs;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public Set getJournals() {
		return this.journals;
	}

	public void setJournals(Set journals) {
		this.journals = journals;
	}

}