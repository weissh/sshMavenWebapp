/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.MyUserDetailsService
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-1     caiwenming       v1.0.0         create
 *
 */
package security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pojos.Resource;
import pojos.Right;
import pojos.Role;
import pojos.Staff;
import dao.StaffDao;

public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private StaffDao staffDao;
	
	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		System.out.println("MyUserDetailServiceImpl----------------------------");
		System.out.println("登录账户名-------------"+userName);
		Staff staff=staffDao.loginStaff(userName);
		Collection<GrantedAuthority> grantedAuthorities=obtainGranteAuthoity(staff);
		@SuppressWarnings("unused")
		boolean enables=true;
		@SuppressWarnings("unused")
		boolean accountNonExpired=true;
		@SuppressWarnings("unused")
		boolean credentialsNonExpired=true;
		@SuppressWarnings("unused")
		boolean accountNonLoacked=true;
		System.out.println("--------封装成spring security 的user-------------------------");
		User userDetail =new User(userName, staff.getPassword(), true, true, true, true, grantedAuthorities);
		return userDetail;
	}

	private Set<GrantedAuthority> obtainGranteAuthoity(Staff staff){
		Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
		Role role =staff.getRole();
//		Set<Role> roles=staff.getRoles();
		Set<Resource> resources=new HashSet<Resource>();
//		Set<Right> rights=new HashSet<Right>();
//		for(Role role:roles){
//			Set<Right> rightsSet =role.getRights();
//			for(Right right:rightsSet){
//				rights.add(right);
//			}
//		}
		Set<Right> rights=role.getRights();
		for(Right right:rights){
			Set<Resource> resourceSet=right.getResources();
			for(Resource resource:resourceSet){
				resources.add(resource);
			}
		}
		System.out.println("用户所具有的资源个数------------"+resources.size());
		for(Resource resource:resources){
			authorities.add(new SimpleGrantedAuthority(resource.getResourceName()));
//					new GrantedAuthorityImpl(resource.getResourceName()));
		}
		return authorities;
	}
}
