package web.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
public class BaseAction extends ActionSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 记录系统日志常数
	public static int CREATE_LOG = 1;
	public static int READ_LOG = 2;
	public static int UPDATE_LOG = 3;
	public static int DELETE_LOG = 4;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return this.getRequest().getSession();
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void printString(boolean flag, String msg) {
		String isSuucess = flag ? "true" : "false";
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		try{
			this.getResponse().getWriter().write("{'success':"+isSuucess+",msg:'"+msg+"'}");
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printString(String msg){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		try{
			this.getResponse().getWriter().write(msg);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printList(int start,int limit,int total,List<?> list){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		JSONArray jsonArray=new JSONArray();
		if(list!=null&&list.size()>0){
			jsonArray=JSONArray.fromObject(list);
		}
		String jsonString ="{start:"+start+",limit:"+limit+",totalProperty:"+total+",infoList:"+jsonArray.toString()+"}";
		try{
			this.getResponse().getWriter().write(jsonString);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// //基础操作
	// private String action="index";
	//
	// //获取action的属性值
	// public String getAction(){
	// return action;
	// }
	//
	// public void setAction(String action){
	// this.action=action;
	// }
	//
	// public String execute(){
	// try{
	// return this.executeMethod(this.getAction());
	// }catch(Exception e){
	// e.printStackTrace();
	// return INPUT;
	// }
	// }
	//
	// private String executeMethod(String method) throws Exception {
	// Class[] c=null;
	// Method m=this.getClass().getMethod(method, c);
	// Object[] object = null;
	// String result =(String) m.invoke(this, object);
	// return result;
	// }
}
