import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import action.UserAction;
import dao.StudentDao;
import dao.impl.entity.StudentDaoImpl;
import dao.impl.entity.UserDaoImpl;
import entity.Student;
import entity.TblUser;


public class test {
	public static void main(String args[]){
	BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	Student  test1=new Student(11,"再找找","张韬");
	Student  test2=new Student(12,"abc","abcd");
	UserDaoImpl   ss=(UserDaoImpl)factory.getBean("userDAO");
	TblUser tblUser= ss.findUser("yingzhuo");
	System.out.println(11);
	
	}
	
}
