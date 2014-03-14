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

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Right;
import service.RightService;
import web.ui.TreeNode;

public class RightAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private RightService rightService;
	
	
	public RightService getRightService() {
		return rightService;
	}


	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}


	public String getAllRight(){
		List<TreeNode> list=this.rightService.getCheckedTree();
		List<Right> rights=(ArrayList<Right>) this.rightService.findBysql("from Right where menu=1");
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
		this.printList(rights,jsonConfig);
		
		return null;
	}
	
	public String getRightByRole(){
		List<Right> rights=this.rightService.getRightByRole();
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
		this.printList(rights,jsonConfig);
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
