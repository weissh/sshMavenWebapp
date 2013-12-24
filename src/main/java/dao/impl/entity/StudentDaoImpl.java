package dao.impl.entity;

import java.util.List;

import dao.impl.GenericDaoImpl;

import entity.Student;
import dao.StudentDao;

public class StudentDaoImpl extends GenericDaoImpl<Student> implements
		StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

	@SuppressWarnings("unchecked")
	public Student findByNameExact(String uname) {

		List<Student> list = (List<Student>) this.getHibernateTemplate()
				.find("from Student u where u.cname = ?", uname).get(0);
		return (!list.isEmpty() && list.size() == 1) ? null : list.get(0);
	}
	public static void main(String args[]){
		 
	}

}