/**
 * Copyright (C) 2014 Asiainfo-Linkage
 *
 * @className:security.Util.UrlMatcher
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

public abstract interface UrlMatcher {
	public abstract Object compile(String paramString);

	public abstract boolean pathMatchesUrl(Object paramObject, String paramString);

	public abstract String getUniversalMatchPattern();

	public abstract boolean requiresLowerCaseUrl();

}
