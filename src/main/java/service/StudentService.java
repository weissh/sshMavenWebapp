package service;

import java.util.List;

import common.PageBean;

import dao.daoImpl.StudentDaoImpl;


public class StudentService {
	private StudentDaoImpl studentDAO;


	public StudentDaoImpl getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDaoImpl studentDAO) {
		this.studentDAO = studentDAO;
	}

	public StudentService() {
	}

	/**
	 * @param categoryDAO
	 */
	public StudentService(StudentDaoImpl studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public PageBean getPages(int pagenum){
		return studentDAO.listpage(pagenum);
	}
	
	public List<?> getUser(String cname){
		return studentDAO.findByCname(cname);
	}
	
}
