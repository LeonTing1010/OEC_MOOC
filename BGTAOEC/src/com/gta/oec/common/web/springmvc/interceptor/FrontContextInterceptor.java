package com.gta.oec.common.web.springmvc.interceptor;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.common.web.utils.ThreadLocalUtils;
import com.gta.oec.controller.course.CourseController;
import com.gta.oec.exception.LoginException;



/**
 * 上下文信息拦截器
 * 
 * 
 * @author 
 * 
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {
	private static final Log logger = LogFactory.getLog(FrontContextInterceptor.class);;
	public static final String HEADTAB = "headTab";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		String headTab = (String) request.getSession().getAttribute(HEADTAB);
		String rHeadTab =  RequestUtils.getQueryParam(request, HEADTAB);
		if (StringUtils.isBlank(rHeadTab)&&StringUtils.isBlank(headTab)) {		
			request.getSession().setAttribute(HEADTAB, "1");
	 	}else if (!StringUtils.isBlank(rHeadTab)) {
	 		request.getSession().setAttribute(HEADTAB, rHeadTab);
		}else if (StringUtils.isBlank(rHeadTab)&&!StringUtils.isBlank(headTab)) {
			request.getSession().setAttribute(HEADTAB, headTab);
		}
		//课程
		if (request.getRequestURI().contains("/course/")) {
			request.getSession().setAttribute(HEADTAB, "2");
		}
		//职业
		if (request.getRequestURI().contains("/job/")) {
			request.getSession().setAttribute(HEADTAB, "3");
		}
		ThreadLocalUtils.setReques(request);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	
	}       
}