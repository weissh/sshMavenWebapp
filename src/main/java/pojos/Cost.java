package pojos;


import java.util.Date;

/**
 * Cost entity. @author MyEclipse Persistence Tools
 */

public class Cost implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private int costId;
	private Staff staff;
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
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private String prop5;

	// Constructors

	/** default constructor */
	public Cost() {
	}

	/** minimal constructor */
	public Cost(Staff staff) {
		this.staff = staff;
	}

	/** full constructor */
	public Cost(Staff staff,Date executeDate, String payWay,
			String currency, float money, String costUnitName,
			String costCountry, String costProvince, String costAddress,
			String costContactName, String costContactPosition,
			String costContactPhone, String costContactEmail,
			String usage1,String description1, String prop1, String prop2, String prop3,
			String prop4, String prop5) {
		this.staff = staff;
		this.recordDate = new Date();
		this.executeDate = executeDate;
		this.payWay = payWay;
		this.currency = currency;
		this.money = money;
		this.costUnitName = costUnitName;
		this.costCountry = costCountry;
		this.costProvince = costProvince;
		this.costAddress = costAddress;
		this.costContactName = costContactName;
		this.costContactPosition = costContactPosition;
		this.costContactPhone = costContactPhone;
		this.costContactEmail = costContactEmail;
		this.usage1 = usage1;
		this.description1 = description1;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
	}

	public Cost(Staff staff, Date executeDate, String payWay,
			String currency, float money, String costUnitName,
			String costCountry, String costProvince, String costAddress,
			String costContactName, String costContactPosition,
			String costContactPhone, String costContactEmail,
			String usage1,String description1) {
		this.staff = staff;
		this.recordDate = new Date();
		this.executeDate = executeDate;
		this.payWay = payWay;
		this.currency = currency;
		this.money = money;
		this.costUnitName = costUnitName;
		this.costCountry = costCountry;
		this.costProvince = costProvince;
		this.costAddress = costAddress;
		this.costContactName = costContactName;
		this.costContactPosition = costContactPosition;
		this.costContactPhone = costContactPhone;
		this.costContactEmail = costContactEmail;
		this.usage1 = usage1;
		this.description1 = description1;
	}
	// Property accessors

	public int getCostId() {
		return this.costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
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

	public String getPayWay() {
		return this.payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getCostUnitName() {
		return this.costUnitName;
	}

	public void setCostUnitName(String costUnitName) {
		this.costUnitName = costUnitName;
	}

	public String getCostCountry() {
		return this.costCountry;
	}

	public void setCostCountry(String costCountry) {
		this.costCountry = costCountry;
	}

	public String getCostProvince() {
		return this.costProvince;
	}

	public void setCostProvince(String costProvince) {
		this.costProvince = costProvince;
	}

	public String getCostAddress() {
		return this.costAddress;
	}

	public void setCostAddress(String costAddress) {
		this.costAddress = costAddress;
	}

	public String getCostContactName() {
		return this.costContactName;
	}

	public void setCostContactName(String costContactName) {
		this.costContactName = costContactName;
	}

	public String getCostContactPosition() {
		return this.costContactPosition;
	}

	public void setCostContactPosition(String costContactPosition) {
		this.costContactPosition = costContactPosition;
	}

	public String getCostContactPhone() {
		return this.costContactPhone;
	}

	public void setCostContactPhone(String costContactPhone) {
		this.costContactPhone = costContactPhone;
	}

	public String getCostContactEmail() {
		return this.costContactEmail;
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