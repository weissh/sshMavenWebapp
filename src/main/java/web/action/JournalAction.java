package web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ObjectJsonValueProcessor;

import pojos.Department;
import pojos.Journal;
import pojos.Staff;
import service.JournalService;
import service.StaffService;
import net.sf.json.JsonConfig;

public class JournalAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	private JournalService journalService;

	private Journal journal;

	private String journalIds;
	
	private StaffService staffService;
	

	public JournalService getJournalService() {
		return journalService;
	}
	public void setJournalService(JournalService journalService) {
		this.journalService = journalService;
	}
	public Journal getJournal() {
		return journal;
	}
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	public String getJournalIds() {
		return journalIds;
	}
	public void setJournalIds(String journalIds) {
		this.journalIds = journalIds;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	//获取前段表单start
	private Integer workId;
	private Integer staffId;
	private String staffName;
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
	
	// 获取前端表单属性 end
	

	
	//获取日志信息列表
	public String getAllJour() {
		List<Journal> journals = this.journalService.findAll();
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Staff.class, new ObjectJsonValueProcessor(new String[]{"staffId","staffName"}, Staff.class));
		/*jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staff")){
					return true;
				}else{
					return false;
				}
			}
		});*/
		this.printList(0, 0, 0, journals, jsonConfig);
		return null;
	}
	
	// 新增日志
	public String addJour(){
		if (this.staffService.findByProperty("staffId",staffId).size() == 0) {			
			this.printString(false, "员工不存在");
			return null;
		}
		Staff staff=this.staffService.findByProperty("staffId",staffId).get(0);	
		Journal journal = new Journal(staff, executeDate, operateMode, unitName,
				country, province, address, contactObject, level, contactWay,
				contactName, contactPosition, contactPhone, contactEmail, startTime, 
				endTime, workContent);	
		int workId = this.journalService.save(journal);
		this.printString(true, workId + "");
		return null;
	}
	
	//修改日志
	//不能修改日志对应的当事人
	public String updateJour() {
		if(workId==0){
			this.printString(false, "获取参数错误！");
			return null;
		}
		if(contactPhone.length()!=11){
			this.printString(false, "联系人电话的位数有误！");
			return null;
		}
		Journal journal =this.journalService.find(Journal.class, workId);
		journal.setAddress(address);
		journal.setContactEmail(contactEmail);
		journal.setContactName(contactName);
		journal.setContactObject(contactObject);
		journal.setContactPhone(contactPhone);
		journal.setContactPosition(contactPosition);
		journal.setContactWay(contactWay);
		journal.setCountry(country);
		journal.setEndTime(endTime);
		journal.setExecuteDate(executeDate);
		journal.setLevel(level);
		journal.setOperateMode(operateMode);
		journal.setProvince(province);
		journal.setRecordDate(new Date());//修改后记录时间改变为修改时间
		journal.setStartTime(startTime);
		journal.setUnitName(unitName);
		journal.setWorkContent(workContent);
		this.journalService.update(journal);
		this.printString(true, workId+"");
		return null;
	}
	
	//删除日志
	public String deleteJour() {
		//如果有多个id，则获取到的journalIds格式是：id1,id2,id3,id4....
		String[] str=this.journalIds.split(",");
		ArrayList<Journal> journals= new ArrayList<Journal>();
		//遍历id，并实例化类型，在add到List
		for(int i=0;i<str.length;i++){
			Journal journal = this.journalService.find(Journal.class, Integer.parseInt(str[i]));
			journals.add(journal);
		}
		this.journalService.removeAll(journals);
		this.printString(true, "");
		return null;
	}
}
