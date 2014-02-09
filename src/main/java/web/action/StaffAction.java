package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Department;
import pojos.Role;
import pojos.Staff;
import service.DepartmentService;
import service.RoleService;
import service.StaffService;

import common.ObjectJsonValueProcessor;

public class StaffAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StaffService staffService;
	private DepartmentService departmentService;
	private RoleService roleService;
	
	//前端表单的所有字段
	private String staffIds;
	private int staffId;
	private String photo;
	private File photoImg;
	private String photoImgFileName;
	private String staffName;
	private int departmentId;
	private Date entryTime;
	private String position;
	private String phone;
	private int roleId;
	private String password;
	private String gender;
	private int age;
	private Date birthday;
	private String nationality;
	private String politicalStatus;
	private String maritalStatus;
	private String nativePlace;
	private String idNo;
	private String passportNo;
	private String domicilePlace;
	private Date dateOfRecruitment;
	private String hightestEdu;
	private String hightestDegree;
	private String graduateSchool;
	private String major;
	private String schoolSystem;
	private String currentAddress;
	private String urgentContact;
	private String email;
	private String ucPhone;
	private String zipCode;
	
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
	//前端表单所有字段的get和set方法
	public String getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(String staffIds) {
		this.staffIds = staffIds;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public File getPhotoImg() {
		return photoImg;
	}
	public void setPhotoImg(File photoImg) {
		this.photoImg = photoImg;
	}
	public String getPhotoImgFileName() {
		return photoImgFileName;
	}
	public void setPhotoImgFileName(String photoImgFileName) {
		this.photoImgFileName = photoImgFileName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
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
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
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
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getUrgentContact() {
		return urgentContact;
	}
	public void setUrgentContact(String urgentContact) {
		this.urgentContact = urgentContact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUcPhone() {
		return ucPhone;
	}
	public void setUcPhone(String ucPhone) {
		this.ucPhone = ucPhone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	//得到文件保存的路径
	public String getPhotoSavePath(){
		return ServletActionContext.getServletContext().getRealPath("/")+"images/photo/";
	}
	
	//新增新员工
	public String addStaff() throws Exception{
		Department department =this.departmentService.find(Department.class, departmentId);
//		Role role =this.roleService.find(Role.class, roleId);
		Staff staff=new Staff(department, null, staffName, entryTime, position, phone, email, urgentContact, ucPhone, gender, nationality, politicalStatus, age, birthday, maritalStatus, idNo, passportNo, nativePlace, domicilePlace, dateOfRecruitment, currentAddress, zipCode, graduateSchool, hightestEdu, hightestDegree, major, schoolSystem, null, password, null, null, null, null, null, null, null, null);
		savePhoto(staff);
		int staffId=this.staffService.save(staff);
		this.printString(true, staffId+"");
		return null;
	}
	
	//保存照片
	public void savePhoto(Staff staff) throws Exception{
		if(photoImg==null){
			return ;
		}
		//文件重命名为"staff_+staffId+后缀"的形式
		photo="staff_"+staffId+photoImgFileName.substring(photoImgFileName.lastIndexOf("."));
		FileOutputStream fos=new FileOutputStream(getPhotoSavePath()+photo);
		FileInputStream fis=new FileInputStream(photoImg);
		byte[] b =new byte[512];
		int length=0;
		while((length=fis.read(b))>0){
			fos.write(b, 0, length);
		}
		fis.close();
		fos.close();
		System.out.println(photo);
		staff.setPhoto(photo);
	}
	public String updateStaff(){
//		Staff staff=this.staffService.find(Staff.class, staffId);
		Department department =this.departmentService.find(Department.class, departmentId);
//		Role role =this.roleService.find(Role.class, roleId);
		Staff staff=new Staff(department, photo, staffName, entryTime, position, phone, email, urgentContact, ucPhone, gender, nationality, politicalStatus, age, birthday, maritalStatus, idNo, passportNo, nativePlace, domicilePlace, dateOfRecruitment, currentAddress, zipCode, graduateSchool, hightestEdu, hightestDegree, major, schoolSystem, password);
		staff.setStaffId(staffId);
		this.staffService.update(this.staffService.merge(staff));
		this.printString(true, staffId+"");
		return null;
	}
	
	public String deleteStaff(){
		String[] ids=this.staffIds.split(",");
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		for(int i=0;i<ids.length;i++){
			Staff staff = this.staffService.find(Staff.class, Integer.parseInt(ids[i]));
			staffs.add(staff);
		}
		this.staffService.removeAll(staffs);
		this.printString(true, "");
		return null;
	}
	
	public String getAllStaff(){
		List<Staff> staffs=this.staffService.findAll();
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{"departmentId"}, Department.class));
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("costs")||arg1.equals("roles")||arg1.equals("journals")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, staffs, jsonConfig);
		return null;
	}
}
