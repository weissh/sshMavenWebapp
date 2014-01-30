package web.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;

import common.DateJsonValueProcessor;
import common.ObjectJsonValueProcessor;

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

	/**
	 * <p>
	 * 使action动作返回一个json类型，例：{'success':false,msg:'参数获取错误！'}，具体可在浏览器试一下
	 * </p>
	 * 
	 * @param boolean flag, String msg
	 */
	public void printString(boolean flag, String msg) {
		String isSuccess = flag ? "true" : "false";
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		try{
			this.getResponse().getWriter().write("{'success':"+isSuccess+",msg:'"+msg+"'}");
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>
	 * 使action动作返回一个json类型，例：{msg:'参数获取错误！'}，具体可在浏览器试一下
	 * </p>
	 * 
	 * @param String msg
	 */
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
	
	/**
	 * <p>
	 * 使action动作返回一个json类型
	 * </p>
	 * 
	 * @param int start,int limit,int total,List<?> list
	 */
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
	
	/**
	 * <p>
	 * 使action动作返回一个json类型
	 * </p>
	 * 
	 * @param int start,int limit,int total,List<?> list,String[] properties,Class<?> clazz
	 */
	public void printList(int start,int limit,int total,List<?> list,String[] properties,Class<?> clazz){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		JSONArray jsonArray=new JSONArray();
		JsonConfig jsonConfig =new JsonConfig();
		//转换时间格式
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		//避免hibernate查询出现死循环
		jsonConfig.registerJsonValueProcessor(clazz, new ObjectJsonValueProcessor(properties, clazz));
		if(list!=null&&list.size()>0){
			jsonArray=JSONArray.fromObject(list,jsonConfig);
			System.out.println(jsonArray.toString());
		}
		String jsonString ="{start:"+start+",limit:"+limit+",totalProperty:"+total+",infoList:"+jsonArray.toString()+"}";
		try{
			this.getResponse().getWriter().write(jsonString);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
