package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import entity.TblPermission;
import entity.TblRole;
import entity.TblUser;

public class SampleRealm extends AuthorizingRealm {
	protected UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    @SuppressWarnings("deprecation")
	public SampleRealm() {
        setName("SampleRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
    }
	 protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
	        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        TblUser user = userDao.findUser(token.getUsername());
	        if( user != null ) {
	            return new SimpleAuthenticationInfo(user.getUserId(), user.getUserPassword(), getName());
	        } else {
	            return null;
	        }
	    }


	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
	        TblUser user = userDao.getUser(userId);
	        if( user != null ) {
	            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	            for( TblRole role : user.getTblRoles() ) {
	                info.addRole(role.getRoleName());
	                info.addStringPermissions(role.getTblPermissions());
	            }
	            return info;
	        } else {
	            return null;
	        }
	    	
	    }
	
	

}
