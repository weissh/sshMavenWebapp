
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.PageBean;
import service.StudentService;
import dao.StudentDAO;
import entity.Student;
 


public class test {
	public static void main(String args[]){
	BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	Student  test1=new Student(11,"再找找","张韬");
	Student  test2=new Student(12,"cc","dd");
	StudentDAO  cDao=(StudentDAO)factory.getBean("studentDAO");
	StudentService cService=(StudentService)factory.getBean("studentService");
	PageBean pageBean=cService.getPages(10);
	System.out.print(pageBean.getCurrentPage());
	}
}
