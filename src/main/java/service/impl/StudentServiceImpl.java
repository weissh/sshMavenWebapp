package service.impl;


import dao.GenericDao;
import dao.StudentDao;
import dao.impl.entity.StudentDaoImpl;
import service.impl.GenericServiceImpl;
import entity.*;
import service.StudentService;

public class StudentServiceImpl  extends GenericServiceImpl<Student> implements StudentService {
	private StudentDaoImpl studentDaoImpl;

	public StudentDaoImpl getStudentDaoImpl() {
		return studentDaoImpl;
	}

	public void setStudentDaoImpl(StudentDaoImpl studentDaoImpl) {
		this.studentDaoImpl = studentDaoImpl;
	}
}