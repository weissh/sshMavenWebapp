package service;

import pojos.Role;

public interface RoleService extends GenericService<Role> {

	public boolean saveRights(String rightIds,int roleId);
	
	public boolean updateRole(int roleId, String roleName,String roleDesc);
}
