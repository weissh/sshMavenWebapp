import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CDAO;
import entity.C;


public class test {
	public static void main(String args[]){
	BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	C  test1=new C(11,"再找找","张韬");
	C  test2=new C(12,"cc","dd");
	CDAO  tiger=(CDAO)factory.getBean("CDAO");
	tiger.save(test1);
	}
}
