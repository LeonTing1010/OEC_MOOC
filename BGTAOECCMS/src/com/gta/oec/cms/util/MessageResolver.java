package com.gta.oec.cms.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 国际化信息帮助类
 * 
 * @author 
 * 
 */
public final class MessageResolver {
	/**
	 * 获得国际化信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param code
	 *            国际化代码
	 * @param args
	 *            替换参数
	 * @return
	 * @see org.springframework.context.MessageSource#getMessage(String,
	 *      Object[], Locale)
	 */
	public static String getMessage(HttpServletRequest request, String code,
			Object... args) {
		WebApplicationContext webApplicationContext = RequestContextUtils
				.getWebApplicationContext(request);
	
		if (webApplicationContext == null) {
			throw new IllegalStateException("WebApplicationContext not found!");
		}
		Locale locale = Locale.getDefault();
		return webApplicationContext.getMessage(code, args, locale);
	}
}
