package service;

import java.util.List;

import common.PageBean;

import dao.StudentDAO;


public class StudentService {
	private StudentDAO studentDAO;


	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public StudentService() {
	}

	/**
	 * @param categoryDAO
	 */
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public PageBean getPages(int pagenum){
		return studentDAO.listpage(pagenum);
	}
	
	public List<?> getUser(String cname){
		return studentDAO.findByCname(cname);
	}
	
}
