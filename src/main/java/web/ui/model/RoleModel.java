/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:web.ui.model.RoleModel
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-26     caiwenming       v1.0.0         create
 *
 */
package web.ui.model;

import java.util.ArrayList;
import java.util.List;

import pojos.Role;

public class RoleModel {
	private Integer roleId;
	private String roleName;
	private String roleDesc;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
	
	public void toRoleModel(Role role){
		this.roleId=role.getRoleId();
		this.roleName=role.getRoleName();
		this.roleDesc=role.getRoleDesc();
	}
	
	public static List<RoleModel> toRoleModels(List<Role> roles){
		List<RoleModel> roleModels=new ArrayList<RoleModel>();
		RoleModel roleModel;
		for(int i=0;i<roles.size();i++){
			roleModel=new RoleModel();
			roleModel.toRoleModel(roles.get(i));
			roleModels.add(roleModel);
		}
		return roleModels;
	}
}
