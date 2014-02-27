/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.StaffAction
 * @description:实现对员工的增、删、改、查等操作
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-9     caiwenming       v1.0.0         create
 *
 */
package web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

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

	private static final long serialVersionUID = 1L;

	/** 获取对员工进行增、改、查操作所需要的服务 */
	private StaffService staffService;
	private DepartmentService departmentService;
	private RoleService roleService;
	
	/**前端表单的所有字段 */
	private String staffIds;
	private int staffId;
	private int start;
	private int limit;
	private String query;
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
	
	/** 各种服务相对应的get和set方法 */
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
	
	
	/**前端表单所有字段的get和set方法 */
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
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String Query() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
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
	
	/**
	 *
	 * @Description:上传员工照片时，获取保存照片的路径
	 *
	 * @return String类型，照片保存路径
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午8:51:24
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public String getPhotoSavePath(){
		return ServletActionContext.getServletContext().getRealPath("/")+"images/photo/";
	}
	
	/**
	 *
	 * @Description:新增员工，同时保存员工的照片到服务器上
	 *
	 * @return Json字符串
	 * @throws Exception
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午8:53:55
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public String addStaff() throws Exception{
		Department department =this.departmentService.find(departmentId);
//		Role role =this.roleService.find(Role.class, roleId);
		Staff staff=new Staff(department, null, staffName, entryTime, position, phone, email, urgentContact, ucPhone, gender, nationality, politicalStatus, age, birthday, maritalStatus, idNo, passportNo, nativePlace, domicilePlace, dateOfRecruitment, currentAddress, zipCode, graduateSchool, hightestEdu, hightestDegree, major, schoolSystem, null, password, null, null, null, null, null, null, null, null);
		savePhoto(staff);
		int staffId=this.staffService.save(staff);
		this.printString(true, staffId+"");
		return null;
	}
	
	/**
	 * 
	 * @Description:保存员工照片
	 *
	 * @param staff 具体的staff对象
	 * @throws Exception
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午8:55:31
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public void savePhoto(Staff staff) throws Exception{
		if(photoImg==null){
			return ;
		}
		/** 文件重命名为"staff_+staffId+后缀"的形式 */
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
		staff.setPhoto(photo);
	}
	
	/**
	 *
	 * @Description:更新员工信息（目前有两种方法）：
	 * 1.如下，直接用实体类的构造函数new一个新的对象，再用set
	 * 方法设置其id，之后再把该对象用merge方法将其由瞬时状态（Transient）转换成脱管状态（Detached），
	 * 最后用update方法将该对象转换成持久化状态（Persistent）即存入数据库中。
	 * 2.使用find的方法，从数据库查找出要修改的记录，使用实体类的set方法重新设置对象的所有属性值，最后update
	 * 
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午8:57:05
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public String updateStaff(){
		Department department =this.departmentService.find(departmentId);
//		Role role =this.roleService.find(Role.class, roleId);
		Staff staff=new Staff(department, photo, staffName, entryTime, position, phone, email, urgentContact, ucPhone, gender, nationality, politicalStatus, age, birthday, maritalStatus, idNo, passportNo, nativePlace, domicilePlace, dateOfRecruitment, currentAddress, zipCode, graduateSchool, hightestEdu, hightestDegree, major, schoolSystem, password);
		staff.setStaffId(staffId);
		/**将对象从瞬时状态改成脱管状态（merge），之后再更新（update）*/
		this.staffService.update(this.staffService.merge(staff));
		this.printString(true, staffId+"");
		return null;
	}
	
	/**
	 *
	 * @Description:删除一条或多条员工记录
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:09:03
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public String deleteStaff(){
		String[] ids=this.staffIds.split(",");
		ArrayList<Staff> staffs=new ArrayList<Staff>();
		/**遍历id数组，查找相应记录并add到ArrayList中 */
		for(int i=0;i<ids.length;i++){
			Staff staff = this.staffService.find(Integer.parseInt(ids[i]));
			staffs.add(staff);
		}
		this.staffService.removeAll(staffs);
		this.printString(true, "");
		return null;
	}
	
	/**
	 *
	 * @Description:获取所有的员工记录，充当前端grid的数据源，同时具有分页功能
	 * 分页：action类有两个属性：start和limit。
	 * start：表示起始的记录位置，即数据表的第几条记录
	 * limit：表示从start开始之后的几条记录
	 * 例子：（0,20）表示提取数据表的前20条，（20,20）表示数据表中的第21条至40条的记录
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:10:24
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public String getAllStaff(){
		/**
		 *  page表示分页查询时的当前页码，前端一页显示记录数（limit）已经设置为20，
		 * 所以每一次点击“下一页”是时，传到后台的start都+20
		 */
		int page=start/limit+1;
		List<Staff> staffs=null;
		int total;
		System.out.println(departmentId!=0);
		if(query!=null){
			StringBuffer sql=new StringBuffer("from Staff where 1=1");
			if(departmentId!=0){
				sql.append(" and Department_DepartmentID="+departmentId);
			}
			if(staffId!=0){
				sql.append(" and staffId="+staffId);
			}
//			if(roleId!=0){
//				sql.append(" and roleId="+roleId);
//			}
			staffs=this.staffService.findByPage(page, limit, sql.toString());
			System.out.println(sql.toString());
			total=staffs.size();
		}else{
			/**
			 * findByPage方法的参数是（当前页码,每页记录数），所以需先通过start和limit计算得出请求的当前页码
			 */
			staffs=this.staffService.findByPage(page,limit);
			total=this.staffService.getTotalRows();
		}
		JsonConfig jsonConfig =new JsonConfig();
		/** 结果转换成json对象是避免出现hibernate死循环，过滤掉引起死循环的字段，保留有用字段 */
		jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{"departmentId"}, Department.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
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
		this.printList(start, limit, total, staffs, jsonConfig);
		return null;
	}
	
	public String getStaffForSelector(){
		List<Staff> staffs;
		if (departmentId==0) {
			staffs=this.staffService.findAll();
		}else{
			Department department =this.departmentService.find(departmentId);
			staffs =this.staffService.findByProperty("department", department);
		}
		JsonConfig jsonConfig =new JsonConfig();
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("department")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, staffs,jsonConfig);
		return null;
	}
	
}
