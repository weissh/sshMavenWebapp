/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.MyInvocationSecurityMetadataSourceService
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import pojos.Resource;
import security.util.AntUrlPathMatcher;
import security.util.UrlMatcher;

import dao.ResourceDao;

public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{

	private ResourceDao resourceDao;
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	private UrlMatcher urlMatcher=new AntUrlPathMatcher();
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap;
	
	public MyInvocationSecurityMetadataSourceService(){
		this.loadResourceDefine();
	}
	
	public MyInvocationSecurityMetadataSourceService(ResourceDao resourceDao){
		this.resourceDao=resourceDao;
		this.loadResourceDefine();
	}
	
	private void loadResourceDefine(){
		System.out.println("-MySecurityMetadataSource---加载所有资源与权限的关系------");
		List<Resource> resources=this.resourceDao.findAll();
		resourceMap=new HashMap<String,Collection<ConfigAttribute>>();
		for(int i=0;i<resources.size();i++){
			System.out.println(resources.get(i).getResourceName());
			ConfigAttribute configAttribute=new SecurityConfig(resources.get(i).getResourceName());
			String url=resources.get(i).getUrl();
			if(resourceMap.containsKey(url)){
				Collection<ConfigAttribute> configAttributes= resourceMap.get(url);
				configAttributes.add(configAttribute);
				resourceMap.put(url, configAttributes);
			}else{
				Collection<ConfigAttribute> configAttributes=new ArrayList<ConfigAttribute>();
				configAttributes.add(configAttribute);
				resourceMap.put(url, configAttributes);
			}
			
		}
//		if(resourceMap==null){
//			
//			List<Resource> resources =this.resourceDao.findAll();
//			for(Resource resource:resources){
//				ConfigAttribute configAttribute=new SecurityConfig(resource.getResourceName());
//				if(resourceMap.containsKey(resource.getUrl())){
//					Collection<ConfigAttribute> configAttributes=resourceMap.get(resource.getUrl());
//					configAttributes.add(configAttribute);
//					resourceMap.put(key, value)
//				}
//				Collection<ConfigAttribute> configAttributes=new ArrayList<ConfigAttribute>();
//				//以权限名称封装为spring的security Object
//				System.out.println("resource.getResourceName()---"+resource.getResourceName());
//				configAttributes.add(configAttribute);
//				resourceMap.put(resource.getUrl(), configAttributes);
//			}
//		}
//		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet=resourceMap.entrySet();
//		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator=resourceSet.iterator();
	}
	
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl=((FilterInvocation)object).getRequestUrl();
		System.out.println("请求URL为----------------"+requestUrl);
		String string=requestUrl.split("\\?")[0];
		 System.out.println("string--------------"+string);
		requestUrl=string;
		Iterator<String> iterator=resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String url = iterator.next();
			if(urlMatcher.pathMatchesUrl(requestUrl, url)){
				return resourceMap.get(url);
			}
		}
		return null;
//		if(resourceMap==null){
//			System.out.println("resourceMap==null------------------");
//			this.loadResourceDefine();
//		}
//		System.out.println("resourceMap------"+resourceMap.size());
//		Collection<ConfigAttribute> collection=resourceMap.get(requestUrl);
//		System.out.println(resourceMap.get(requestUrl));
////		if(collection.size()>0){
////			for(ConfigAttribute configAttribute:collection){
////				System.out.println("configAttribute.getAttribute()-----------------"+configAttribute.getAttribute());
////			}
////		}
//		return resourceMap.get(requestUrl);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	
}
