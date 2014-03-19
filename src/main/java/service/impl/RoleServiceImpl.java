package service.impl;

import java.util.HashSet;
import java.util.Set;

import dao.RoleDao;
import pojos.Right;
import pojos.Role;
import service.RoleService;

public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {
	
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public boolean saveRights(String rightIds, int roleId) {
		String[] str=rightIds.split(",");
		Set<Right> rights=new HashSet<Right>();
		for(int i=0;i<str.length;i++){
			Right right=new Right();
			right.setId(Integer.parseInt(str[i]));
			rights.add(right);
		}
		Role role=this.roleDao.find(roleId);
		role.setRights(rights);
		this.roleDao.update(role);
		return true;
	}

	@Override
	public boolean updateRole(int roleId, String roleName, String roleDesc) {
		Role role=this.roleDao.find(roleId);
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		this.roleDao.update(role);
		return true;
	}

	
	
}	
