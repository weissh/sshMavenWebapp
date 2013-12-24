package action;   
  
import entity.*;  
import service.StudentService;   
  
import com.opensymphony.xwork2.ActionSupport;   
//  




@SuppressWarnings("serial")
public class StudentAction extends ActionSupport {   
	String propertyName;
	String value;
	
    public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	private StudentService service;   
 
    public StudentService getService() {   
        return service;   
    }   
    public void setService(StudentService service) {   
        this.service = service;   
    }   
       
    @SuppressWarnings("static-access")
	public String execute() throws Exception {   
    	this.service.findByProperty(propertyName,value);   
        return this.SUCCESS;   
    }   
       
    public void validate() {   
        if(getPropertyName()==null||"".equals(getPropertyName())){   
            this.addFieldError("p.name", "name is not null");   
        }   
    }   
       
}  
