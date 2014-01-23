package dao.hibernate;

import pojos.Role;
import dao.RoleDao;

public class RoleDaoHib extends GenericDaoHib<Role> implements RoleDao{

	public RoleDaoHib() {
		super(Role.class);
	}

}
