package web.action;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import pojos.Department;
import pojos.Role;
import pojos.Staff;
import service.DepartmentService;
import web.ui.excel.DepartmentUI;

import common.ExcelUtil;

public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 获取department服务、以及get和set方法  */
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/** 导出excel表时的输入流、文件名称;以及get和set方法  */
	@SuppressWarnings("unused")
	private InputStream excelStream;
	private String fileName;

	public InputStream getExcelStream() {
		return ServletActionContext.getServletContext().getResourceAsStream("excel/"+this.fileName);
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() throws UnsupportedEncodingException {
		return new String(fileName.getBytes(),"ISO8859-1");
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/** 获取前端表单所有字段  */
	private String departmentIds;
	private int departmentId;
	private String departmentName;
	private Date createTime;
	private String description;
	private String query;
	private int start;
	private int limit;

	/** 前端表单所有字段的get和set方法  */
	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	/**
	 *
	 * @Description:新增部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:11:44
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String addDept(){
		if (departmentName == null || createTime == null || description == null) {
			this.printString(false, "参数获取错误！");
			return null;
		}
		if (this.departmentService.findByProperty("departmentName",
				departmentName).size() > 0) {
			this.printString(false, "该部门已经存在！");
			return null;
		}
		Department department = new Department(departmentName, createTime, description);
		department.setTotalStaff(0);
		int departmentId = this.departmentService.save(department);
		this.printString(true, departmentId + "");
		return null;
	}

	/**
	 *
	 * @Description:修改部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:12:18
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String updateDept() {
		if(departmentId==0){
			this.printString(false, "获取参数错误！");
			return null;
		}
		Department department=new Department();
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		department.setCreateTime(createTime);
		department.setDescription(description);
		this.departmentService.update(department);
		this.printString(true, departmentId+"");
		return null;
	}

	/**
	 *
	 * @Description:删除部门
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:12:33
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String deleteDept() {
		String name=this.departmentService.deleteDept(departmentIds);
		if (name=="") {
			this.printString(true, "");
			return null;
		}else {
			this.printString(false, name+"还有员工，请删除员工信息后在进行删除操作...");
			return null;
		}
	}

	/**
	 *
	 * @Description:获取所有的部门信息
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:15:38
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
	public String getAllDept() {
		int page=start/limit+1;
		List<Department> departments = null;
		int total;
		if(query!=null){
			StringBuffer sql=new StringBuffer("from Department where 1=1");
			
			if(departmentId!=0){
				sql.append(" and DepartmentID="+departmentId);
			}
			departments=this.departmentService.findByPage(page, limit, sql.toString());
			System.out.println(sql.toString());
			total=departments.size();

		}else{
			/**
			 * findByPage方法的参数是（当前页码,每页记录数），所以需先通过start和limit计算得出请求的当前页码
			 */
			departments = this.departmentService.findByPage(page,limit);
			total=this.departmentService.getTotalRows();
		}
		JsonConfig jsonConfig =new JsonConfig();
		/** 结果转换成json对象是避免出现hibernate死循环，过滤掉引起死循环的字段，保留有用字段 */
		//jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{"departmentId"}, Department.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staffs")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(start, limit, total, departments, jsonConfig);
		return null;
	}
	
	public String getAllDeptByRole(){
		List<Department> departments=new ArrayList<Department>();
		int page=start/limit+1;
		int total = 0;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff =(Staff) session.getAttribute("staff");
		Role role=staff.getRole();
		String roleName=role.getRoleName().trim();
		if(roleName.equals("部门经理")){
			Department department=this.departmentService.find(staff.getDepartment().getDepartmentId());
			departments.add(department);
			total=1;
		}else if (roleName.equals("管理员")||roleName.equals("人事经理")||roleName.equals("总经理")) {
			departments=this.departmentService.findByPage(page, limit);
			total=this.departmentService.getTotalRows();
		}
		JsonConfig jsonConfig =new JsonConfig();
		/** 结果转换成json对象是避免出现hibernate死循环，过滤掉引起死循环的字段，保留有用字段 */
		//jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{"departmentId"}, Department.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staffs")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(start, limit, total, departments, jsonConfig);
		return null;
	}
	
	/**
	 *
	 * @Description:仅获得部门的departmentId和departmentName属性，并以json返回:作为前端部门下拉列表的值
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-11 上午9:16:13
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-11    caiwenming      v1.0.0         create
	 */
//	public String getDeptForSelector(){
//		String sql="select new Department(dept.departmentId,dept.departmentName) from Department dept";
//		List<Department> departments=this.departmentService.findBysql(sql);
//		this.printList(0, 0, 0, departments);
//		return null;
//	}
public String getDeptForSelector(){
		
		List<Department> departments=null;
		String sql=null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Department depart=staff.getDepartment();
		int departId=depart.getDepartmentId();
		pojos.Role role= staff.getRole();
		String rol = role.getRoleName();
		if(rol.equals("管理员")){
		 sql="from Department dept";
		}
		else if(rol.equals("部门经理")){
		 sql="select new Department(dept.departmentId,dept.departmentName) from Department dept where DepartmentID ="+departId;
		}
		departments=this.departmentService.findBysql(sql);
		JsonConfig jsonConfig =new JsonConfig();
		/** 结果转换成json对象是避免出现hibernate死循环，过滤掉引起死循环的字段，保留有用字段 */
		//jsonConfig.registerJsonValueProcessor(Department.class, new ObjectJsonValueProcessor(new String[]{"departmentId"}, Department.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staffs")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, departments,jsonConfig);
		return null;
	}
	
	/**
	 *
	 * @Description:将部门数据表中记录全部或根据id导出到Excel表
	 *
	 * @return
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @throws Exception 
	 * @date:2014-2-12 上午9:58:08
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-12    caiwenming      v1.0.0         create
	 */
	public String exportDept() throws Exception{
		System.out.println(departmentIds);
		List<Department> departments= new ArrayList<Department>();
		if(departmentIds.equals("")){
			departments = this.departmentService.findAll();
		}else{
			/**如果有多个id，则获取到的departmentIds格式是：id1,id2,id3,id4.... */
			String[] str=this.departmentIds.split(",");
			
			/**遍历id，并实例化类型，在add到List */
			for(int i=0;i<str.length;i++){
				Department department = this.departmentService.find(Integer.parseInt(str[i]));
				departments.add(department);
			}
		}
		Vector<String> head=DepartmentUI.getHead();
		List<Vector<String>> dataList=DepartmentUI.getDataList(departments);
		String downLoadPath =ServletActionContext.getServletContext().getRealPath("/")+"excel\\";
		String fileName=ExcelUtil.createFileName("部门信息")+".xls";
		if(ExcelUtil.printExcel(head, dataList, downLoadPath+fileName)){
			download(fileName);
			//System.out.println(ServletActionContext.getServletContext().getRealPath("excel/Department201402131756458884286.xls"));
			return "success";
		}else {
			this.printString(false, "");
		}
		return null;
	}
	
	/**
	 *
	 * @Description:设置action类中的fileName和excelStream，从而使当前端点击导出时，浏览器可以识别为文件下载
	 *
	 * @param fileName 文件名称
	 * @throws Exception
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-15 上午8:34:53
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-15    caiwenming      v1.0.0         create
	 */
	public void download(String fileName) throws Exception{
		this.fileName=fileName;
		this.excelStream=ServletActionContext.getServletContext().getResourceAsStream("excel/"+fileName);
	}
}
