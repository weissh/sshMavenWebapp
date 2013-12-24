import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.StudentDao;
import dao.impl.entity.StudentDaoImpl;
import entity.Student;


public class test {
	public static void main(String args[]){
	BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	Student  test1=new Student(11,"再找找","张韬");
	Student  test2=new Student(12,"abc","abcd");
	StudentDaoImpl  ss=(StudentDaoImpl)factory.getBean("studentDAO");
	List<Student>a=ss.findByProperty("cteacher", "张韬");
	
	System.out.println(a.get(0).getUnicode()+","+a.get(0).getCteacher());
	
	}
	
}
