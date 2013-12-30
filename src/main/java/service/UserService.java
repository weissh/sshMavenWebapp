package service;

import entity.TblUser;

public interface UserService extends GenericService<TblUser>{
	public TblUser findUser(String userName);
}
