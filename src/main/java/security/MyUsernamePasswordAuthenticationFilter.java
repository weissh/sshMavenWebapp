/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.MyUsernamePasswordAuthenticationFilter
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dao.StaffDao;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private StaffDao staffDao;
	
	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	public static final String USERNAME="userName";
	public static final String PASSWORD="password";
	
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response){
		if(!request.getMethod().equals("POST")){
			throw new AuthenticationServiceException("权限方法不支持"+request.getMethod());
		}
		String userName=obtainUsername(request);
		String password=obtainPassword(request);
		userName=userName.trim();
		User user=(User)this.staffDao.findByUserName(userName);
		if(user==null||!user.getPassword().equals(password)){
			throw new AuthenticationServiceException("用户名或密码错误！");
		}
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, password);
		setDetails(request, authenticationToken);
		return this.getAuthenticationManager().authenticate(authenticationToken);
	}
	
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}
}
