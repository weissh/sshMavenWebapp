package dao.impl.entity;

import java.util.List;

import org.hibernate.classic.Session;
import org.springframework.util.Assert;

import dao.UserDao;
import dao.impl.GenericDaoImpl;
import entity.Student;
import entity.TblUser;

public class UserDaoImpl extends GenericDaoImpl<TblUser> implements UserDao {
	public UserDaoImpl() {
		super(TblUser.class);
	}
	/*public List<TblUser>  findAll() {
		String hql = "from TblUser";
		org.hibernate.Session session =super.getSession();
		return (List <TblUser>) session.createQuery(hql).list();
	}*/
	
	public TblUser findUser(String username) {
        Assert.hasText(username);
        String query = "from TblUser u where u.userUsername = :username";
        return (TblUser) getSession().createQuery(query).setString("username", username).uniqueResult();
    }
	
	
	public TblUser findById(long userId) {
		try {
			TblUser instance = (TblUser) getSession().get("entity.TblUser", userId);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	
}
