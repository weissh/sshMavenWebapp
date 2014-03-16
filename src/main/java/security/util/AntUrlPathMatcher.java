/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.Util.AntUrlPathMatcher
 * @description:TODO
 * @version:v1.0.0
 * @author:caiwenming
 *
 * Modification History:
 * Date         Author         Version      Description
 * -----------------------------------------------------------------
 * 2014-3-4     caiwenming       v1.0.0         create
 *
 */
package security.util;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntUrlPathMatcher implements UrlMatcher{

	private boolean requiresLowerCaseUrl;
	
	private PathMatcher pathMatcher;
	
	public AntUrlPathMatcher(){
		this(true);
	}
	
	public AntUrlPathMatcher(boolean requiresLowerCaseUrl){
		this.requiresLowerCaseUrl=true;
		this.pathMatcher=new AntPathMatcher();
		this.requiresLowerCaseUrl=requiresLowerCaseUrl;
	}
	
	public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl){
		this.requiresLowerCaseUrl=requiresLowerCaseUrl;
	}
	
	@Override
	public Object compile(String path) {
		if(this.requiresLowerCaseUrl){
			return path.toLowerCase();
		}
		return path;
	}

	@Override
	public boolean pathMatchesUrl(Object path, String url) {
		if(("/**").equals(path)||("*".equals(path))){
			return true;
		}
		return this.pathMatcher.match((String) path, url);
	}

	@Override
	public String getUniversalMatchPattern() {
		return "/**";
	}

	@Override
	public boolean requiresLowerCaseUrl() {
		return this.requiresLowerCaseUrl;
	}

	public String toString(){
		return super.getClass().getName()+"[requiresLowerCase='"+this.requiresLowerCaseUrl+"']";
	}
}
