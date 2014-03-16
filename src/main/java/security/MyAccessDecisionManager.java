/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.MyAccessDecisionManager
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
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager{

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		System.out.println("MyAccessDecisionManager-----------------------------------");
		if(configAttributes==null){
			return;
		}
		Iterator<ConfigAttribute> iterator=configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = (ConfigAttribute) iterator.next();
			String needRole=((SecurityConfig) configAttribute).getAttribute();
			System.out.println("请求资源所需要的权限---"+needRole);
			for(GrantedAuthority grantedAuthority:authentication.getAuthorities()){
				System.out.println("用户所具有的权限----------"+grantedAuthority.getAuthority());
				if(needRole.equals(grantedAuthority.getAuthority())){
					return;
				}
			}
		}
		throw new AccessDeniedException("没有权限访问！");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
