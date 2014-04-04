package web.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojos.Cost;
import pojos.Department;
import pojos.Staff;

public class CostModel {
	private int costId;
	private Date recordDate;
	private Date executeDate;
	private String payWay;
	private String currency;
	private float money;
	private String costUnitName;
	private String costCountry;
	private String costProvince;
	private String costAddress;
	private String costContactName;
	private String costContactPosition;
	private String costContactPhone;
	private String costContactEmail;
	private String usage1;	
	private String description1;	
//	private String prop1;
//	private String prop2;
//	private String prop3;
//	private String prop4;
//	private String prop5;
	private Integer staffId;
	private String staffName;
	private Integer departmentId;
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public int getCostId() {
		return costId;
	}
	public void setCostId(int costId) {
		this.costId = costId;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getCostUnitName() {
		return costUnitName;
	}
	public void setCostUnitName(String costUnitName) {
		this.costUnitName = costUnitName;
	}
	public String getCostCountry() {
		return costCountry;
	}
	public void setCostCountry(String costCountry) {
		this.costCountry = costCountry;
	}
	public String getCostProvince() {
		return costProvince;
	}
	public void setCostProvince(String costProvince) {
		this.costProvince = costProvince;
	}
	public String getCostAddress() {
		return costAddress;
	}
	public void setCostAddress(String costAddress) {
		this.costAddress = costAddress;
	}
	public String getCostContactName() {
		return costContactName;
	}
	public void setCostContactName(String costContactName) {
		this.costContactName = costContactName;
	}
	public String getCostContactPosition() {
		return costContactPosition;
	}
	public void setCostContactPosition(String costContactPosition) {
		this.costContactPosition = costContactPosition;
	}
	public String getCostContactPhone() {
		return costContactPhone;
	}
	public void setCostContactPhone(String costContactPhone) {
		this.costContactPhone = costContactPhone;
	}
	public String getCostContactEmail() {
		return costContactEmail;
	}
	public void setCostContactEmail(String costContactEmail) {
		this.costContactEmail = costContactEmail;
	}
	public String getUsage1() {
		return usage1;
	}
	public void setUsage1(String usage1) {
		this.usage1 = usage1;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public CostModel(){
		
	}
	
	public CostModel(Cost cost){
		this.costId=cost.getCostId();
		Staff staff=cost.getStaff();
		if(staff!=null){
			this.staffId=staff.getStaffId();	
			this.staffName=staff.getStaffName();
			Department department=staff.getDepartment();
			if(department!=null){
				this.departmentId=department.getDepartmentId();
			}
		}
		this.recordDate=cost.getRecordDate();
		this.executeDate=cost.getExecuteDate();
		this.payWay=cost.getPayWay();
		this.currency=cost.getCurrency();
		this.money=cost.getMoney();
		this.costUnitName=cost.getCostUnitName();
		this.costCountry=cost.getCostCountry();
		this.costProvince=cost.getCostProvince();
		this.costAddress=cost.getCostAddress();
		this.costContactName=cost.getCostContactName();
		this.costContactPosition=cost.getCostContactPosition();
		this.costContactPhone=cost.getCostContactPhone();
		this.costContactEmail=cost.getCostContactEmail();
		this.usage1=cost.getUsage1();
		this.description1=cost.getDescription1();
	}
	
//	public void toCostModel(Cost cost)
//	{
//		this.costId=cost.getCostId();
//		this.staffId=cost.getStaff().getStaffId();
//		this.recordDate=cost.getRecordDate();
//		this.executeDate=cost.getExecuteDate();
//		this.payWay=cost.getPayWay();
//		this.currency=cost.getCurrency();
//		this.money=cost.getMoney();
//		this.costUnitName=cost.getCostUnitName();
//		this.costCountry=cost.getCostCountry();
//		this.costProvince=cost.getCostProvince();
//		this.costAddress=cost.getCostAddress();
//		this.costContactName=cost.getCostContactName();
//		this.costContactPosition=cost.getCostContactPosition();
//		this.costContactPhone=cost.getCostContactPhone();
//		this.costContactEmail=cost.getCostContactEmail();
//		this.usage1=cost.getUsage1();
//		this.description1=cost.getDescription1();
//		this.staffId=cost.getStaff().getStaffId();		
//		this.departmentId=cost.getStaff().getDepartment().getDepartmentId();
//		this.staffName=cost.getStaff().getStaffName();
//	}
	
	public static List<CostModel> toCostModels(List<Cost> costs)
	{
		List<CostModel> costModels=new ArrayList<CostModel>();
//		CostModel costModel;
		for(int i=0;i<costs.size();i++)
		{
			CostModel costModel=new CostModel(costs.get(i));
//			costModel=new CostModel();
//			costModel.toCostModel(costs.get(i));
			costModels.add(costModel);
		}
		return costModels;
	}
	
}
