package service;

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
	
}
