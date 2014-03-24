/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.RightAction
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-7     caiwenming       v1.0.0         create
 *
 */
package web.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Right;
import service.RightService;
import web.ui.TreeStore;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;


public class RightAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private RightService rightService;
	private int roleId;
	public RightService getRightService() {
		return rightService;
	}


	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getAllRight(){
//		List<TreeNode> nodes=this.rightService.getCheckedTree(roleId);
		TreeStore treeStore =this.rightService.getCheckedTree(roleId);
		JsonConfig jsonConfig =new JsonConfig();
//		jsonConfig.registerJsonValueProcessor(Resource.class, new ObjectJsonValueProcessor(new String[]{"url"}, Resource.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("roles")||arg1.equals("resources")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printString(JSONObject.toJSONString(treeStore));
		return null;
	}
	
	public String getRightByRole(){
		TreeStore treeStore=this.rightService.getRightByRole();
//		List<Right> rights=this.rightService.getRightByRole();
		JsonConfig jsonConfig =new JsonConfig();
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("roles")||arg1.equals("resources")){
					return true;
				}else{
					return false;
				}
			}
		});
		Map<String, Object> sessionMap=ActionContext.getContext().getSession();
//		HttpSession session =ServletActionContext.getRequest().getSession();
//		session.setAttribute("1", JSONObject.toJSONString(treeStore));
		sessionMap.put("treeStore", JSONObject.toJSONString(treeStore));
		this.printString(JSONObject.toJSONString(treeStore));
		return null;
	}
	
	public String getRightForSelector(){
		List<Right> rights=this.rightService.findBysql("from Right where isMenu=0");
		JsonConfig jsonConfig =new JsonConfig();
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("roles")||arg1.equals("children")||arg1.equals("resources")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, rights, jsonConfig);
		return null;
	}
}
