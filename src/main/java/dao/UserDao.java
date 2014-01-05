package dao;

import entity.TblUser;

public interface UserDao  extends GenericDao <TblUser>{
	public TblUser findUser(String userName);
	public TblUser findById(java.lang.String id);
}
