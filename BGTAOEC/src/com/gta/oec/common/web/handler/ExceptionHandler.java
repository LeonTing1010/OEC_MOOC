/**
 * ExceptionHandler.java	  V1.0   2013-12-30 ����9:00:04
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.common.web.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.exception.LoginException;

public class ExceptionHandler implements HandlerExceptionResolver{
	private static final Log logger = LogFactory.getLog(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
	
		ModelAndView mView = new ModelAndView("error/error.htm");
		
	
		if (arg3 instanceof LoginException && null!=arg0.getHeader("x-requested-with")&&"XMLHttpRequest".equalsIgnoreCase(arg0.getHeader("x-requested-with"))) {
				ResponseUtils.renderText(arg1, arg3.getMessage());
				ModelMap map = new ModelMap();
				try {
					map.put("errorMsg", URLEncoder.encode("您的登录状态异常!", "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					logger.error(e); e.printStackTrace();
				}
				map.put("errorTag", "1");
				return new ModelAndView("error/error.htm",map);		
		}else {
			if (arg3 instanceof LoginException) {
				ModelMap map = new ModelMap();
				String returnURI = arg0.getRequestURI();
				if (!StringUtils.isBlank(arg0.getQueryString())) {
					returnURI = returnURI.concat("?").concat(arg0.getQueryString());
				}
				map.put("returnUri",returnURI);
				if (!StringUtils.isBlank(arg3.getMessage())) {
					map.put("errorMsg",arg3.getMessage());
				}
				mView = new ModelAndView("login/login.htm",map);
			}
		}
	    logger.error(arg3);
		return mView;
	}

}
