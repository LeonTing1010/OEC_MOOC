/**
 * ApplicationInterceptor.java	  V1.0   2014-4-16 下午7:30:29
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.common.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gta.oec.cms.util.ThreadLocalUtils;

/** 
 * @description ApplicationInterceptor 应用拦截器  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-04-16 11:25:34 
 * @file ApplicationInterceptor.java 
 * @package com.gta.oec.cms.common.interceptors 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = Logger.getLogger(ApplicationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug("enter the ApplicationInterceptor ! ");
		ThreadLocalUtils.setReques(request);
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
