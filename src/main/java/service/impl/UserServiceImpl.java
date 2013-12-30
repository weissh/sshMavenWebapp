package service.impl;

import dao.GenericDao;
import dao.impl.entity.UserDaoImpl;
import entity.TblUser;

public class UserServiceImpl extends GenericServiceImpl<TblUser>implements service.UserService {
	
	//private UserDaoImpl userDaoImpl;

	@Override
	public TblUser findUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	/*public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}
	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}*/
	
}
