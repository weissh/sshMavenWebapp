package pojos;

import java.util.Date;

/**
 * Cost entity. @author MyEclipse Persistence Tools
 */

public class Cost implements java.io.Serializable {

	// Fields

	private Integer costId;
	private Staff staff;
	private Date recordDate;
	private Date executeDate;
	private String payWay;
	private String currency;
	private Integer money;
	private String costUnitName;
	private String costCountry;
	private String costProvince;
	private String costAddress;
	private String costContactName;
	private String costContactPosition;
	private String costContactPhone;
	private String costContactEmail;
	private String useDescription;
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
	public Cost(Staff staff, Date recordDate, Date executeDate, String payWay,
			String currency, Integer money, String costUnitName,
			String costCountry, String costProvince, String costAddress,
			String costContactName, String costContactPosition,
			String costContactPhone, String costContactEmail,
			String useDescription, String prop1, String prop2, String prop3,
			String prop4, String prop5) {
		this.staff = staff;
		this.recordDate = recordDate;
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
		this.useDescription = useDescription;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.prop5 = prop5;
	}

	// Property accessors

	public Integer getCostId() {
		return this.costId;
	}

	public void setCostId(Integer costId) {
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

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
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

	public String getUseDescription() {
		return this.useDescription;
	}

	public void setUseDescription(String useDescription) {
		this.useDescription = useDescription;
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