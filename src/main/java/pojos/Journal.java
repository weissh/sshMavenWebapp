package pojos;

import java.util.Date;

/**
 * Journal entity. @author MyEclipse Persistence Tools
 */

public class Journal implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Integer workId;
	private Staff staff;
	private Date recordDate;
	private Date executeDate;
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
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;

	// Constructors

	/** default constructor */
	public Journal() {
	}

	/** minimal constructor */
	public Journal(Staff staff) {
		this.staff = staff;
	}

	/** full constructor */
	public Journal(Staff staff, Date recordDate, Date executeDate,
			String operateMode, String unitName, String country,
			String province, String address, String contactObject,
			String level, String contactWay, String contactName,
			String contactPosition, String contactPhone, String contactEmail,
			String startTime, String endTime, String workContent, String prop1,
			String prop2, String prop3, String prop4, String prop5) {
		this.staff = staff;
		this.recordDate = recordDate;
		this.executeDate = executeDate;
		this.operateMode = operateMode;
		this.unitName = unitName;
		this.country = country;
		this.province = province;
		this.address = address;
		this.contactObject = contactObject;
		this.level = level;
		this.contactWay = contactWay;
		this.contactName = contactName;
		this.contactPosition = contactPosition;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workContent = workContent;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
	}

	// Property accessors

	public Journal(Staff staff, Date executeDate, String operateMode,
			String unitName, String country, String province,
			String address, String contactObject, String level,
			String contactWay, String contactName, String contactPosition,
			String contactPhone, String contactEmail, String startTime,
			String endTime, String workContent) {
		this.staff = staff;
		this.recordDate = new Date();
		this.executeDate = executeDate;
		this.operateMode = operateMode;
		this.unitName = unitName;
		this.country = country;
		this.province = province;
		this.address = address;
		this.contactObject = contactObject;
		this.level = level;
		this.contactWay = contactWay;
		this.contactName = contactName;
		this.contactPosition = contactPosition;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workContent = workContent;	
	}

	public Integer getWorkId() {
		return this.workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getExecuteDate() {
		return this.executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public String getOperateMode() {
		return this.operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactObject() {
		return this.contactObject;
	}

	public void setContactObject(String contactObject) {
		this.contactObject = contactObject;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getContactWay() {
		return this.contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPosition() {
		return this.contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return this.contactEmail;
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
		return this.workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
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

}