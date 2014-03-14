/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.action.RoleAction
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

import com.alibaba.fastjson.JSON;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import pojos.Role;
import service.RoleService;

public class RoleAction extends BaseAction{
	private RoleService roleService;
	

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String getAllRole(){
		List<Role> roles =this.roleService.findAll();
		JsonConfig jsonConfig =new JsonConfig();
//		jsonConfig.registerJsonValueProcessor(Resource.class, new ObjectJsonValueProcessor(new String[]{"url"}, Resource.class));
		/** 同样是为了避免出现hibernate死循环，过滤掉引起死循环的整个对象，不需要任何字段 */
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("rights")||arg1.equals("staffs")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(roles,jsonConfig);
		return null;
	}
}
