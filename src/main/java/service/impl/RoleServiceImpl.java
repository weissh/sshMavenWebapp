package service.impl;

import dao.RoleDao;
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
	
}	
