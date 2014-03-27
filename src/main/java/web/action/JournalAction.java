package web.action;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import common.ExcelUtil;
import common.ObjectJsonValueProcessor;

import pojos.Department;
import pojos.Journal;
import pojos.Staff;
import service.JournalService;
import service.StaffService;
import web.ui.excel.JournalUI;
import web.ui.model.CostModel;
import web.ui.model.JournalModel;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JournalAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/** 获取对日志进行增、改、查操作所需要的服务,以及get和set方法 */
	private JournalService journalService;
	private StaffService staffService;

	public JournalService getJournalService() {
		return journalService;
	}

	public void setJournalService(JournalService journalService) {
		this.journalService = journalService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	/** 导出excel表时的输入流、文件名称;以及get和set方法 */
	private InputStream excelStream;
	private String fileName;

	public InputStream getExcelStream() {
		return ServletActionContext.getServletContext().getResourceAsStream(
				"excel/" + this.fileName);
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

	/** 前端表单的所有字段，以及get和set方法 */
	private int workId;
	private String workIds;
	private int staffId;
	private int departmentId;
	private int start;
	private int limit;
	private String query;
	private Date startDate;
	private Date endDate;
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

	public String getWorkIds() {
		return workIds;
	}

	public void setWorkIds(String workIds) {
		this.workIds = workIds;
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

	// 获取日志信息列表
	public String getAllJour() {
		int page = start / limit + 1;
		List<Journal> journals = new ArrayList<Journal>();
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		int tempStaffId = tempStaff.getStaffId();
		String roleName = tempStaff.getRole().getRoleName();
		StringBuffer sql = null;

		// 如果是部门经理
		if (roleName.equals("部门经理")) {
			Integer departmentid = tempStaff.getDepartment().getDepartmentId();
			String sqll = new String(
					"from Staff where Department_DepartmentID=" + departmentid);
			List<Staff> staffs = this.staffService.findBysql(sqll);
			String staffid = null;
			for (int i = 0; i < staffs.size(); i++) {
				if (staffid == null) {
					staffid = staffs.get(i).getStaffId() + "";
				} else {
					staffid = staffid + "," + staffs.get(i).getStaffId() + "";
				}
			}
			sql = new StringBuffer("from Journal where Staff_StaffID in ("
					+ staffid + ")");
			// 如果存在其他查询条件
			if (query != null) {
				if (startDate != null && endDate != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String start = df.format(startDate);
					String end = df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='" + start + "'"
							+ " and executeDate<='" + end + "'");
				}
				if (staffId != 0) {
					sql.append(" and Staff_StaffID=" + staffId);
				}
			}

		}
		// 如果是管理员或者财务部员工或者财务部经理
		if (roleName.equals("管理员") || roleName.equals("人力部员工")
				|| roleName.equals("人力部经理")) {
			sql = new StringBuffer("from Journal where 1=1");
			if (query != null) {
				if (startDate != null && endDate != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String start = df.format(startDate);
					String end = df.format(endDate);
					System.out.println(start);
					sql.append(" and executeDate>='" + start + "'"
							+ " and executeDate<='" + end + "'");
				}
				if (departmentId != 0) {
					String sqll = new String(
							"from Staff where Department_DepartmentID="
									+ departmentId);
					List<Staff> staffs = this.staffService.findBysql(sqll);
					String staffid = null;
					for (int i = 0; i < staffs.size(); i++) {
						if (staffid == null) {
							staffid = staffs.get(i).getStaffId() + "";
						} else {
							staffid = staffid + ","
									+ staffs.get(i).getStaffId() + "";
						}
					}
					sql.append("and Staff_StaffID in (" + staffid + ")");
				}
				if (staffId != 0) {
					sql.append(" and Staff_StaffID=" + staffId);
				}
			}
		}
		journals = this.journalService.findByPage(page, limit,
				sql.toString());
		total = this.journalService.getTotalRows(sql.toString());
		List<JournalModel> journalModels;
		if (journals.size() > 0) {
			journalModels = JournalModel.toJournalModels(journals);
		} else {
			journalModels = null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		this.printList(start, limit, total, journalModels, jsonConfig);
		return null;
	}

	// 获取个人日志信息列表
	public String getAllJourPer() {
		int page = start / limit + 1;
		List<Journal> journals = new ArrayList<Journal>();
		int total;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		StringBuffer sql = new StringBuffer("from Journal where Staff_StaffID="
				+ tempStaff.getStaffId());
		if (query != null) {
			if (startDate != null && endDate != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String start = df.format(startDate);
				String end = df.format(endDate);
				sql.append(" and executeDate>='" + start + "'"
						+ " and executeDate<='" + end + "'");
			}
			journals = this.journalService.findByPage(page, limit,
					sql.toString());
			total = this.journalService.getTotalRows(sql.toString());
		} else {
			/**
			 * findByPage方法的参数是（当前页码,每页记录数），所以需先通过start和limit计算得出请求的当前页码
			 */
			journals = this.journalService.findByPage(page, limit,
					sql.toString());
			total = this.journalService.getTotalRows(sql.toString());
		}
		List<JournalModel> journalModels;
		if (journals.size() > 0) {
			journalModels = JournalModel.toJournalModels(journals);
		} else {
			journalModels = null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		this.printList(start, limit, total, journalModels, jsonConfig);
		return null;
	}

	// 新增日志
	public String addJour() {
		Staff staff = this.staffService.find(staffId);
		if (staff == null) {
			this.printString(false, "员工不存在");
		} else {
			Journal journal = new Journal(staff, executeDate, operateMode,
					unitName, country, province, address, contactObject, level,
					contactWay, contactName, contactPosition, contactPhone,
					contactEmail, startTime, endTime, workContent);
			int workId = this.journalService.save(journal);
			this.printString(true, workId + "");
		}
		return null;
	}

	// 新增个人日志
	public String addJourPer() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		Staff staff = this.staffService.find(tempStaff.getStaffId());
		Journal journal = new Journal(staff, executeDate, operateMode,
				unitName, country, province, address, contactObject, level,
				contactWay, contactName, contactPosition, contactPhone,
				contactEmail, startTime, endTime, workContent);
		int workId = this.journalService.save(journal);
		this.printString(true, workId + "");
		return null;
	}

	// 修改日志
	// 不能修改日志对应的当事人
	public String updateJour() {
		if (workId == 0) {
			this.printString(false, "获取参数错误！");
			return null;
		}
		Journal journal = this.journalService.find(workId);
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
		journal.setRecordDate(new Date());// 修改后记录时间改变为修改时间
		journal.setStartTime(startTime);
		journal.setUnitName(unitName);
		journal.setWorkContent(workContent);
		this.journalService.update(journal);
		this.printString(true, workId + "");
		return null;
	}

	// 删除日志
	public String deleteJour() {
		// 如果有多个id，则获取到的workIds格式是：id1,id2,id3,id4....
		// this.printString(true, workIds+"get it!");
		String[] str = this.workIds.split(",");
		ArrayList<Journal> journals = new ArrayList<Journal>();
		// 遍历id，并实例化类型，在add到List
		for (int i = 0; i < str.length; i++) {
			Journal journal = this.journalService
					.find(Integer.parseInt(str[i]));
			journals.add(journal);
		}
		this.journalService.removeAll(journals);
		this.printString(true, "");
		return null;
	}

	// 导出个人日志
	public String exportJourPer() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		List<Journal> journals = new ArrayList<Journal>();
		if (workIds.equals("")) {
			StringBuffer sql = null;
			sql = new StringBuffer("from Journal where Staff_StaffID="
					+ tempStaff.getStaffId());
			journals = this.journalService.findBysql(sql.toString());
		} else {
			/** 如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4.... */
			String[] str = this.workIds.split(",");

			/** 遍历id，并实例化类型，在add到List */
			for (int i = 0; i < str.length; i++) {
				Journal journal = this.journalService.find(Integer
						.parseInt(str[i]));
				journals.add(journal);
			}
		}
		Vector<String> head = JournalUI.getHead();
		List<Vector<String>> dataList = JournalUI.getDataList(journals);
		String downLoadPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "excel\\";
		String fileName = ExcelUtil.createFileName("Journal") + ".xls";
		if (ExcelUtil.printExcel(head, dataList, downLoadPath + fileName)) {
			download(fileName);
			// System.out.println(ServletActionContext.getServletContext().getRealPath("excel/Department201402131756458884286.xls"));
			return "success";
		} else {
			this.printString(false, "");
		}

		return null;
	}

	// 导出部门日志
	public String exportJourDept() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff tempStaff = (Staff) session.getAttribute("staff");
		int tempStaffId = tempStaff.getStaffId();
		String roleName = tempStaff.getRole().getRoleName();
		List<Journal> journals = new ArrayList<Journal>();

		if (workIds.equals("")) {
			StringBuffer sql = null;
			if (roleName.equals("管理员") || roleName.equals("人力部员工")
					|| roleName.equals("人力部经理")) {
				journals = this.journalService.findAll();
			} else if (roleName.equals("部门经理")) {
				int departmentid = tempStaff.getDepartment().getDepartmentId();
				String sqll = new String(
						"from Staff where Department_DepartmentID="
								+ departmentid);
				List<Staff> staff2 = this.staffService.findBysql(sqll);
				String staffid = null;
				for (int i = 0; i < staff2.size(); i++) {
					if (staffid == null) {
						staffid = staff2.get(i).getStaffId() + "";
					} else {
						staffid = staffid + "," + staff2.get(i).getStaffId()
								+ "";
					}
				}
				sql = new StringBuffer("from Journal where Staff_StaffID in ("
						+ staffid + ")");
				journals = this.journalService.findBysql(sql.toString());
			}
		} else {
			/** 如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4.... */
			String[] str = this.workIds.split(",");

			/** 遍历id，并实例化类型，在add到List */
			for (int i = 0; i < str.length; i++) {
				Journal journal = this.journalService.find(Integer
						.parseInt(str[i]));
				journals.add(journal);
			}
		}
		Vector<String> head = JournalUI.getHead();
		List<Vector<String>> dataList = JournalUI.getDataList(journals);
		String downLoadPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "excel\\";
		String fileName = ExcelUtil.createFileName("Journal") + ".xls";
		if (ExcelUtil.printExcel(head, dataList, downLoadPath + fileName)) {
			download(fileName);
			// System.out.println(ServletActionContext.getServletContext().getRealPath("excel/Department201402131756458884286.xls"));
			return "success";
		} else {
			this.printString(false, "");
		}

		return null;
	}

	public void download(String fileName) throws Exception {
		this.fileName = fileName;
		this.excelStream = ServletActionContext.getServletContext()
				.getResourceAsStream("excel/" + fileName);
	}
}
