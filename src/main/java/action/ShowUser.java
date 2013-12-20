package action;

import java.util.List;

import service.StudentService;


import com.opensymphony.xwork2.ActionSupport;
import common.PageBean;
import entity.Student;


@SuppressWarnings("serial")
public class ShowUser extends ActionSupport{
	private int pagenum=0;
	private StudentService studentService;
	private String cname;
	private String cteacher;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCteacher() {
		return cteacher;
	}
	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}
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
	
	public String findUser(){
		Student student;
		List<?> list=studentService.getUser(this.getCname().trim());
		if(list.size()!=0){
		student=(Student)list.get(0);
		if (student.getCteacher().toString()==this.getCteacher().trim().toString() || this.getCteacher().trim().equals(student.getCteacher()) ) {
			return "SUCCESS"; 
		}else {
			return "FAIL";
		}
		}else {
			return "FAIL";
		}
	}
}
