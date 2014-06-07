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

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import pojos.Role;
import service.RoleService;

public class RoleAction extends BaseAction{
	private RoleService roleService;
	private Integer roleId;
	private String roleIds;
	private String roleName;
	private String roleDesc;
	private String rightIds;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRightIds() {
		return rightIds;
	}

	public void setRightIds(String rightIds) {
		this.rightIds = rightIds;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public String addRole(){
		Role role=new Role(roleName,roleDesc,null,null);
		int id=this.roleService.save(role);
		this.printString(true, id+"");
		return null;
	}

	public String updateRole(){
		if(this.roleService.updateRole(roleId, roleName, roleDesc)){
			this.printString(true, roleId+"");
		}
		return null;
	}
	
	public String deleteRole(){
		String[] str=roleIds.split(",");
		ArrayList<Role> roles=new ArrayList<Role>();
		for(int i=0;i<str.length;i++){
			Role role=new Role();
			role.setRoleId(Integer.parseInt(str[i]));
			roles.add(role);
		}
		this.roleService.removeAll(roles);
		this.printString(true, "");
		return null;
	}
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
	
	public String saveRights(){
		if(this.roleService.saveRights(rightIds, roleId)){
			this.printString(true, "");
		}
		return null;
	}
	
	public String getRoleForSelector(){
		List<Role> roles=this.roleService.findAll();
		JsonConfig jsonConfig =new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("staffs")||arg1.equals("rights")){
					return true;
				}else{
					return false;
				}
			}
		});
		this.printList(0, 0, 0, roles,jsonConfig);
		return null;
	}
}
