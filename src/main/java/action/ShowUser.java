package action;

import service.StudentService;

import com.opensymphony.xwork2.ActionSupport;
import common.PageBean;

@SuppressWarnings("serial")
public class ShowUser extends ActionSupport{
	private int pagenum=0;
	private StudentService studentService;
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private PageBean pageBean;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
 
	public String showUser(){
		pageBean=studentService.getPages(pagenum);
		return "SUCCESS";
	}
}
