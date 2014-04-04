package web.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Department;
import pojos.Journal;
import pojos.Staff;

public class JournalModel {

	private int workId;
	private int staffId;
	private int departmentId;
	private String staffName;
	private Date executeDate;
	private Date recordDate;
	private String operateMode;
	private String unitName;
	private String country;
	private String province;
	private String address;
	private String contactObject;
	private String level;
	private String contactWay;
	private String contactName;
	private String contactPosition;
	private String contactPhone;
	private String contactEmail;
	private String startTime;
	private String endTime;
	private String workContent;
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getOperateMode() {
		return operateMode;
	}
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactObject() {
		return contactObject;
	}
	public void setContactObject(String contactObject) {
		this.contactObject = contactObject;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPosition() {
		return contactPosition;
	}
	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	public JournalModel(){
		
	}
	
	public JournalModel(Journal journal){
		this.workId = journal.getWorkId();
		Staff staff=journal.getStaff();
		if(staff!=null){
			this.staffId=staff.getStaffId();	
			this.staffName=staff.getStaffName();
			Department department=staff.getDepartment();
			if(department!=null){
				this.departmentId=department.getDepartmentId();
			}
		}
		this.executeDate = journal.getExecuteDate();
		this.recordDate = journal.getRecordDate();
		this.operateMode = journal.getOperateMode();
		this.unitName = journal.getUnitName();
		this.country = journal.getCountry();
		this.province = journal.getProvince();
		this.address = journal.getAddress();;
		this.contactObject = journal.getContactObject();
		this.level = journal.getLevel();
		this.contactWay = journal.getContactWay();
		this.contactName = journal.getContactName();
		this.contactPosition = journal.getContactPosition();
		this.contactPhone = journal.getContactPhone();
		this.contactEmail = journal.getContactEmail();
		this.startTime = journal.getStartTime();
		this.endTime = journal.getEndTime();
		this.workContent = journal.getWorkContent();
	}
	
//	public void toJournalModel(Journal journal){
//		this.workId = journal.getWorkId();
//		this.staffId = journal.getStaff().getStaffId();
//		this.departmentId = journal.getStaff().getDepartment().getDepartmentId();
//		this.staffName = journal.getStaff().getStaffName();
//		this.executeDate = journal.getExecuteDate();
//		this.recordDate = journal.getRecordDate();
//		this.operateMode = journal.getOperateMode();
//		this.unitName = journal.getUnitName();
//		this.country = journal.getCountry();
//		this.province = journal.getProvince();
//		this.address = journal.getAddress();;
//		this.contactObject = journal.getContactObject();
//		this.level = journal.getLevel();
//		this.contactWay = journal.getContactWay();
//		this.contactName = journal.getContactName();
//		this.contactPosition = journal.getContactPosition();
//		this.contactPhone = journal.getContactPhone();
//		this.contactEmail = journal.getContactEmail();
//		this.startTime = journal.getStartTime();
//		this.endTime = journal.getEndTime();
//		this.workContent = journal.getWorkContent();
//	}
	
	public static List<JournalModel> toJournalModels(List<Journal> journals)
	{
		List<JournalModel> JournalModels=new ArrayList<JournalModel>();
//		JournalModel JournalModel;
		for(int i=0;i<journals.size();i++)
		{
			JournalModel journalModel=new JournalModel(journals.get(i));
//			JournalModel=new JournalModel();
//			JournalModel.toJournalModel(Journals.get(i));
			JournalModels.add(journalModel);
		}
		return JournalModels;
	}
}
