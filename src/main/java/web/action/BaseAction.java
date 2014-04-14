/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.BaseAction
 * @description:继承ActionSupport，封装所有*Action类会用到方法；处理所有Action返回的结果，主要是json处理
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-2-9     caiwenming       v1.0.0         create
 *
 */
package web.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

import common.DateJsonValueProcessor;
import common.ObjectJsonValueProcessor;

@SuppressWarnings("unused")
public class BaseAction extends ActionSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	// 记录系统日志常数
	public static int CREATE_LOG = 1;
	public static int READ_LOG = 2;
	public static int UPDATE_LOG = 3;
	public static int DELETE_LOG = 4;

	//获取请求、响应以及会话
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	//请求、响应、会话的get和set方法
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
	 *
	 * @Description:json化Action返回的结果，并写入response
	 *
	 * @param flag
	 * @param msg
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:21:19
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
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
	 *
	 * @Description:仅写入response一个字符串
	 *
	 * @param msg
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:22:48
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
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
	 *
	 * @Description:json化Action返回的结果，并写入response中，带有分页功能
	 *
	 * @param start
	 * @param limit
	 * @param total
	 * @param list
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:23:55
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public void printList(int start,int limit,int total,List<?> list){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		JSONArray jsonArray=new JSONArray();
		//改变所有Date字段的形式为"yyyy--MM--dd"
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		if(list!=null&&list.size()>0){
			jsonArray=JSONArray.fromObject(list,jsonConfig);
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
	 *
	 * @Description:json化Action返回的结果，并写入response中，带有分页功能
	 *
	 * @param start
	 * @param limit
	 * @param total
	 * @param list
	 * @param jsonConfig
	 *
	 * @version:v1.0
	 * @author:caiwenming
	 * @date:2014-2-10 上午9:24:33
	 *
	 * Modification History:
	 * Date         Author        Version      Description
	 * -----------------------------------------------------------------
	 * 2014-2-10    caiwenming      v1.0.0         create
	 */
	public void printList(int start,int limit,int total,List<?> list,JsonConfig jsonConfig){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		JSONArray jsonArray=new JSONArray();
		//改变所有Date字段的形式为"yyyy--MM--dd"
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		if(list!=null&&list.size()>0){
			jsonArray=JSONArray.fromObject(list,jsonConfig);
		}
		String jsonString ="{start:"+start+",limit:"+limit+",totalProperty:"+total+",infoList:"+jsonArray.toString()+"}";
		try{
			this.getResponse().getWriter().write(jsonString);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printList(List<?> list,JsonConfig jsonConfig){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		JSONArray jsonArray=new JSONArray();
		//改变所有Date字段的形式为"yyyy--MM--dd"
		jsonConfig.registerJsonValueProcessor(Date.class, new ObjectJsonValueProcessor("yyyy-MM-dd"));
		if(list!=null&&list.size()>0){
			jsonArray=JSONArray.fromObject(list,jsonConfig);
		}
		String jsonString =jsonArray.toString();
		try{
			this.getResponse().getWriter().write(jsonString);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printString(List<?> list){
		this.getResponse().setContentType("text/html;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		
		String jsonText = JSON.toJSONString(list, true);
		try{
			this.getResponse().getWriter().write(jsonText);
			this.getResponse().flushBuffer();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
