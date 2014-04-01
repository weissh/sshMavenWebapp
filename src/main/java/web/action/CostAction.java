/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.StaffAction
 * @description:实现对员工的增、删、改、查等操作
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      description
 * -----------------------------------------------------------------
 * 2014-2-9     caiwenming       v1.0.0         create
 *
 */
package web.action;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Cost;
import pojos.Department;
import pojos.Journal;
import pojos.Staff;
import service.CostService;
import service.DepartmentService;
import service.StaffService;
import web.ui.excel.CostDepUI;
import web.ui.excel.DepartmentUI;
import web.ui.model.CostModel;

import common.ExcelUtil;
import common.ObjectJsonValueProcessor;

public class CostAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	/** 获取对费用进行增、改、查操作所需要的服务 */
	private DepartmentService departmentService;
	private StaffService staffService;
	private CostService costService;
	
	/**前端表单的所有字段 */
	private int departmentId;
	private int costId;
	private String costIds;
	private Integer staffId;
	private int start;
	private int limit;
	private String query;
	private Date startDate;
	private Date endDate;
//	private Date recordDate;
	private Date executeDate;
	private String payWay;
	private String currency;
	private float money;
	private String costCountry;	
	private String costProvince;	
	private String costAddress;	
	private String costUnitName;	
	private String costContactName;
	private String costContactPosition;	
	private String costContactPhone;	
	private String costContactEmail;	
	private String usage1;	
	private String description1;	
	
	/** 各种服务相对应的get和set方法 */
	public CostService getCostService() {
		return costService;
	}
	public void setCostService(CostService costService) {
		this.costService =costService;
	}
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
	
	
	/** 导出excel表时的输入流、文件名称;以及get和set方法  */
	private InputStream excelStream;
	private String fileName;

	public InputStream getExcelStream() {
		return ServletActionContext.getServletContext().getResourceAsStream("excel/"+this.fileName);
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**前端表单所有字段的get和set方法 */

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getCostIds() {
		return costIds;
	}
	public int getCostId() {
		return costId;
	}
	public void setCostId(int costId) {
		this.costId = costId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getCostProvince() {
		return costProvince;
	}
	public void setCostProvince(String costProvince) {
		this.costProvince = costProvince;
	}
	public void setCostIds(String costIds) {
		this.costIds = costIds;
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
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
//	public Date getRecordDate() {
//		return recordDate;
//	}
//	public void setRecordDate(Date recordDate) {
//		this.recordDate = recordDate;
//	}
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
	public String getCostCountry() {
		return costCountry;
	}
	public void setCostCountry(String costCountry) {
		this.costCountry = costCountry;
	}

	public String getCostAddress() {
		return costAddress;
	}
	public void setCostAddress(String costAddress) {
		this.costAddress = costAddress;
	}
	public String getCostUnitName() {
		return costUnitName;
	}
	public void setCostUnitName(String costUnitName) {
		this.costUnitName = costUnitName;
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
	/**
	 *
	 * @description:新增费用
	 *
	 * @return Json字符串
	 * @throws Exception
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-3-2 下午4:42:00
	 *
	 * Modification History:
	 * Date         Author        Version      description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	//新增部门费用
	public String addCost() {
		Staff staff = this.staffService.find(staffId);
		if (staff == null) {
			this.printString(false, "员工不存在");
		} 
		else{
		Cost cost=new Cost(staff,executeDate, payWay, currency, money, costUnitName, costCountry, costProvince, costAddress, costContactName, costContactPosition, costContactPhone, costContactEmail, usage1, description1);
		int costId=this.costService.save(cost);
		this.printString(true, costId+"");
		}
		return null;
	}
	//新增个人费用
	public String addCostPer() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Cost cost=new Cost(staff,executeDate, payWay, currency, money, costUnitName, costCountry, costProvince, costAddress, costContactName, costContactPosition, costContactPhone, costContactEmail, usage1, description1);
		int costId=this.costService.save(cost);
		this.printString(true, costId+"");
		return null;
	}
	/**
	 *
	 * @description:更新部门费用信息（目前有两种方法）：
	 *
	 * 
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午8:57:05
	 *
	 * Modification History:
	 * Date         Author        Version      description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	//更新费用
	public String updateCost(){
		if(costId==0){
			this.printString(false, "获取参数错误！");
			return null;
		}
		Cost cost=this.costService.find(costId);
		cost.setCostId(costId);
		//cost.setRecordDate(recordDate);
		cost.setExecuteDate(executeDate);
		cost.setPayWay(payWay);
		cost.setCurrency(currency);
		cost.setMoney(money);
		cost.setCostUnitName(costUnitName);
		cost.setCostCountry(costCountry);
		cost.setCostProvince(costProvince);
		cost.setCostAddress(costAddress);
		cost.setCostContactName(costContactName);
		cost.setCostContactPosition(costContactPosition);
		cost.setCostContactPhone(costContactPhone);
		cost.setCostContactEmail(costContactEmail);
		cost.setUsage1(usage1);
		cost.setDescription1(description1);
		
		/**将对象从瞬时状态改成脱管状态（merge），之后再更新（update）*/
		this.costService.update(cost);
		this.printString(true, costId+"");
		return null;
	}
	
	/**
	 *
	 * @description:删除一条或多条员工记录 
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:09:03
	 *
	 * Modification History:
	 * Date         Author        Version      description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	//删除费用
	public String deleteCost(){
		String[] ids=this.costIds.split(",");
		ArrayList<Cost> costs=new ArrayList<Cost>();
		/**遍历id数组，查找相应记录并add到ArrayList中 */
		for(int i=0;i<ids.length;i++){
			Cost cost = this.costService.find(Integer.parseInt(ids[i]));
			costs.add(cost);
		}
		this.costService.removeAll(costs);
		this.printString(true, "");
		return null;
	}
	
	/**
	 *
	 * @description:获取所有的员工记录，充当前端grid的数据源，同时具有分页功能
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
	 * Date         Author        Version      description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	//获取所有费用列表
	public String getAllCost(){
		int page=start/limit+1;
		List<Cost> costs = new ArrayList<Cost>();
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		String roleName=staff.getRole().getRoleName();
		StringBuffer sql=null;
		
		//如果是部门经理
		if(roleName.equals("部门经理")){
			Integer departmentid=staff.getDepartment().getDepartmentId();	
			String sqll = new String("from Staff where Department_DepartmentID="+ departmentid);
			List<Staff> staff2 = this.staffService.findBysql(sqll);
			String staffid=null;
			for(int i=0;i<staff2.size();i++){
				if(staffid==null)
				{
					staffid=staff2.get(i).getStaffId()+"";
				}else{
					staffid=staffid+","+staff2.get(i).getStaffId()+"";
				}			
			}
			sql=new StringBuffer("from Cost where Staff_StaffID in (" +staffid+")");
			//如果存在其他查询条件
			if(query!=null){
				if(startDate!=null  && endDate != null){							
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String start=df.format(startDate);
					String end=df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
					+end+"'");
				}
				if(staffId!=0){
					sql.append(" and Staff_StaffID="+staffId);
				}
			}
			
			
		}
		//如果是管理员或者财务部员工或者财务部经理
		if(roleName.equals("管理员")||roleName.equals("总经理")||roleName.equals("财务部员工")||roleName.equals("财务部经理"))
		{
			sql=new StringBuffer("from Cost where 1=1");
			if(query!=null)
			{
				if(startDate!=null  && endDate != null){							
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String start=df.format(startDate);
					String end=df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
					+end+"'");
				}
				if(departmentId!=0)
				{
					String sqll = new String("from Staff where Department_DepartmentID="+ departmentId);
					List<Staff> staff2 = this.staffService.findBysql(sqll);
					String staffid=null;
					for(int i=0;i<staff2.size();i++)
					{
						if(staffid==null)
						{
							staffid=staff2.get(i).getStaffId()+"";
						}else{
							staffid=staffid+","+staff2.get(i).getStaffId()+"";
						}			
					}
					sql.append("and Staff_StaffID in (" +staffid+")");
				}
				if(staffId!=0){
					sql.append(" and Staff_StaffID="+staffId);
				}
			}
		}
		sql.append("order by recordDate desc");
		costs=this.costService.findByPage(page,limit, sql.toString());
		total=this.costService.getTotalRows(sql.toString());
		List<CostModel> costModels;
		if(costs.size()>0){
			costModels=CostModel.toCostModels(costs);
		}
		else{
			costModels=null;
		}	
		JsonConfig jsonConfig =new JsonConfig();
		this.printList(start, limit, total, costModels,jsonConfig);
		return null;
	}
	//获取个人费用列表
	public String getAllCostPer(){
		int page=start/limit+1;
		List<Cost> costs = null;
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Integer staf = staff.getStaffId();
		StringBuffer sql=new StringBuffer("from Cost where Staff_StaffID="+staf);
		if(query!=null){
			if(startDate!=null  && endDate != null){							
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String start=df.format(startDate);
				String end=df.format(endDate);
				System.out.println(start);
				sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
				+end+"'");
			}
			sql.append("order by recordDate desc");
			costs=this.costService.findByPage(page, limit, sql.toString());			
			System.out.println(sql.toString());
			total=this.costService.getTotalRows(sql.toString());
		}else{
			/**
			 * findByPage方法的参数是（当前页码,每页记录数），所以需先通过start和limit计算得出请求的当前页码
			 */
			sql.append("order by recordDate desc");
			costs=this.costService.findByPage(page,limit, sql.toString());
			total=this.costService.getTotalRows(sql.toString());
		}
		List<CostModel> costModels;
		if(costs.size()>0){
			costModels=CostModel.toCostModels(costs);
		}
		else{
			costModels=null;
		}
		JsonConfig jsonConfig =new JsonConfig();
		this.printList(start, limit, total, costModels,jsonConfig);
		return null;
	}
	//导出个人费用为excel
		public String exportCostPer() throws Exception{
			HttpSession session = ServletActionContext.getRequest().getSession();
			Staff staff = (Staff) session.getAttribute("staff");
			Integer staf = staff.getStaffId();
			List<Cost> costs= new ArrayList<Cost>();
			if(costIds.equals("")){
				StringBuffer sql=null;
				sql=new StringBuffer("from Cost where Staff_StaffID="+staf);
				costs=this.costService.findBysql(sql.toString());
			}
			else{
				/**如果有多个id，则获取到的costIds格式是：id1,id2,id3,id4.... */
				String[] str=this.costIds.split(",");
				/**遍历id，并实例化类型，在add到List */
				for(int i=0;i<str.length;i++){
					Cost cost = this.costService.find(Integer.parseInt(str[i]));
					costs.add(cost);
				}
			}
			Vector<String> head=CostDepUI.getHead();
			List<Vector<String>> dataList=CostDepUI.getDataList(costs);
			String downLoadPath =ServletActionContext.getServletContext().getRealPath("/")+"excel\\";
			String fileName=ExcelUtil.createFileName("Cost")+".xls";
			if(ExcelUtil.printExcel(head, dataList, downLoadPath+fileName)){
				download(fileName);
				//System.out.println(ServletActionContext.getServletContext().getRealPath("excel/Department201402131756458884286.xls"));
				return "success";
			}else {
				this.printString(false, "");
			}
			
			return null;
		}
	//导出部门费用为excel
	public String exportCostDept() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Integer staf = staff.getStaffId();
		String roleName=staff.getRole().getRoleName();
		List<Cost> costs= new ArrayList<Cost>();
		
		if(costIds.equals("")){
			StringBuffer sql=null;
			if(roleName.equals("管理员")||roleName.equals("总经理")||roleName.equals("财务部员工")||roleName.equals("财务部经理"))
			{costs = this.costService.findAll();}
			else if(roleName.equals("部门经理")){
				Integer departmentid=staff.getDepartment().getDepartmentId();	
				String sqll = new String("from Staff where Department_DepartmentID="+ departmentid);
				List<Staff> staff2 = this.staffService.findBysql(sqll);
				String staffid=null;
				for(int i=0;i<staff2.size();i++)
				{
					if(staffid==null)
					{
						staffid=staff2.get(i).getStaffId()+"";
					}else{
						staffid=staffid+","+staff2.get(i).getStaffId()+"";
					}			
				}
				sql=new StringBuffer("from Cost where Staff_StaffID in (" +staffid+")");
				costs=this.costService.findBysql(sql.toString());
			}}
		else{
			/**如果有多个id，则获取到的costIds格式是：id1,id2,id3,id4.... */
			String[] str=this.costIds.split(",");
			
			/**遍历id，并实例化类型，在add到List */
			for(int i=0;i<str.length;i++){
				Cost cost = this.costService.find(Integer.parseInt(str[i]));
				costs.add(cost);
			}
		}
		Vector<String> head=CostDepUI.getHead();
		List<Vector<String>> dataList=CostDepUI.getDataList(costs);
		String downLoadPath =ServletActionContext.getServletContext().getRealPath("/")+"excel\\";
		String fileName=ExcelUtil.createFileName("Cost")+".xls";
		if(ExcelUtil.printExcel(head, dataList, downLoadPath+fileName)){
			download(fileName);
			//System.out.println(ServletActionContext.getServletContext().getRealPath("excel/Department201402131756458884286.xls"));
			return "success";
		}else {
			this.printString(false, "");
		}
		
		return null;
	}
	
	public void download(String fileName) throws Exception{
		this.fileName=fileName;
		this.excelStream=ServletActionContext.getServletContext().getResourceAsStream("excel/"+fileName);
	}
	
	//计算个人费用
	public String CountPer(){
		List<Cost> costs = null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Integer staf = staff.getStaffId();
		StringBuffer sql=new StringBuffer("from Cost where Staff_StaffID="+staf);
		
		if(query!=null){
			if(startDate!=null  && endDate != null){							
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String start=df.format(startDate);
				String end=df.format(endDate);
				System.out.println(start);
				sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
				+end+"'");
			}
		}		
		costs=this.costService.findBysql(sql.toString());
		float count=0;
		String currency=null;
		for(int i=0;i<costs.size();i++)
		{
			currency=costs.get(i).getCurrency();
			if(currency.equals("人民币")){count=costs.get(i).getMoney()+count;}
			}
		this.printString(count+"");
		return null;
	}
	//计算部门费用
	public String CountDep(){
		List<Cost> costs = new ArrayList<Cost>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		String roleName=staff.getRole().getRoleName();
		StringBuffer sql=null;
		//如果是部门经理
		if(roleName.equals("部门经理")){
			Integer departmentid=staff.getDepartment().getDepartmentId();	
			String sqll = new String("from Staff where Department_DepartmentID="+ departmentid);
			List<Staff> staff2 = this.staffService.findBysql(sqll);
			String staffid=null;
			for(int i=0;i<staff2.size();i++)
			{
				if(staffid==null)
				{
					staffid=staff2.get(i).getStaffId()+"";
				}else{
					staffid=staffid+","+staff2.get(i).getStaffId()+"";
				}			
			}
			sql=new StringBuffer("from Cost where Staff_StaffID in (" +staffid+")");
			//如果存在其他查询条件
			if(query!=null)
			{
				if(startDate!=null  && endDate != null){							
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String start=df.format(startDate);
					String end=df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
					+end+"'");
				}
				if(staffId!=0){
					sql.append(" and Staff_StaffID="+staffId);
				}
			}
		}
		//如果是管理员或者财务部员工或者财务部经理
		if(roleName.equals("管理员")||roleName.equals("总经理")||roleName.equals("财务部员工")||roleName.equals("财务部经理")){
			sql=new StringBuffer("from Cost where 1=1");
			if(query!=null)
			{
				if(startDate!=null  && endDate != null){							
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String start=df.format(startDate);
					String end=df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='"+start+"'"+" and executeDate<='"
					+end+"'");
				}
				if(departmentId!=0)
				{
					String sqll = new String("from Staff where Department_DepartmentID="+ departmentId);
					List<Staff> staff2 = this.staffService.findBysql(sqll);
					String staffid=null;
					for(int i=0;i<staff2.size();i++)
					{
						if(staffid==null)
						{
							staffid=staff2.get(i).getStaffId()+"";
						}else{
							staffid=staffid+","+staff2.get(i).getStaffId()+"";
						}			
					}
					sql.append("and Staff_StaffID in (" +staffid+")");
				}
				if(staffId!=0){
					sql.append(" and Staff_StaffID="+staffId);
				}
			}
		}
		costs=this.costService.findBysql(sql.toString());
		float count=0;
		for(int i=0;i<costs.size();i++){
			currency=costs.get(i).getCurrency();
			if(currency.equals("人民币")){count=costs.get(i).getMoney()+count;}
		}
		this.printString(count+"");
		return null;
	}
}
