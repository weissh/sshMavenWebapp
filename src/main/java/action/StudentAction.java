package action;   
  
import entity.*;  
import service.StudentService;   
  
import com.opensymphony.xwork2.ActionSupport;   
//  




@SuppressWarnings("serial")
public class StudentAction extends ActionSupport {   
    private Student p;   
    private StudentService service;   
    public Student getP() {   
        return p;   
    }   
    public void setP(Student p) {   
        this.p = p;   
    }   
    public StudentService getService() {   
        return service;   
    }   
    public void setService(StudentService service) {   
        this.service = service;   
    }   
       
    @SuppressWarnings("static-access")
	public String execute() throws Exception {   
    	this.service.findById(34);   
        return this.SUCCESS;   
    }   
       
    public void validate() {   
        if(p.getCname()==null||"".equals(p.getCname())){   
            this.addFieldError("p.name", "name is not null");   
        }   
    }   
       
}  
