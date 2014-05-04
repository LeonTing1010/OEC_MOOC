/**
 * AuthorizeFilter.java	  V1.0   2014-4-16 下午1:27:15
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.common.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.WebUtils;

/** 
 * @description AuthorizeFilter 认证拦截器  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-04-16 11:25:34 
 * @file ApplicationInterceptor.java 
 * @package com.gta.oec.cms.common.filters 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class AuthorizeFilter extends OncePerRequestFilter {

	private static Logger log = Logger.getLogger(AuthorizeFilter.class);
	
	/** 不用拦截的url */
	private List<String> excludedUrls;
	
	/** 不用拦截的目录 */
	private List<String> excludedDirs;
	
	private void initExcludedUrl(){
		if(excludedUrls!=null && excludedUrls.size()>0){
			return ;
		}
		ServletContext sc = this.getServletContext();
		String excludedUrlStr = sc.getInitParameter(SystemConstant.EXCLUDEDURLS);
		if(StringUtils.isNotEmpty(excludedUrlStr)){
			this.excludedUrls = WebUtils.separateStrByMark(excludedUrlStr, ";");
			if (log.isDebugEnabled()) {
				log.debug(" the init excludedUrl size is :'"+this.excludedUrls.size());
			}
		}else{
			if (log.isDebugEnabled()) {
				log.debug(" the configre excludedUrl is null '");
			}
		}
	}
	
	private void initExcludedDir(){
		if(excludedDirs!=null && excludedDirs.size()>0){
			return ;
		}
		ServletContext sc = this.getServletContext();
		String excludedDirs = sc.getInitParameter(SystemConstant.EXCLUDEDDIRS);
		if(StringUtils.isNotEmpty(excludedDirs)){
			this.excludedDirs = WebUtils.separateStrByMark(excludedDirs, ";");
			if (log.isDebugEnabled()) {
				log.debug(" the init excludedDirs size is :'"+this.excludedDirs.size());
			}
		}else{
			if (log.isDebugEnabled()) {
				log.debug(" the configre excludedDirs is null '");
			}
		}
	}
	
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		initExcludedUrl();
		initExcludedDir();
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (log.isDebugEnabled()) {  
			log.debug("enter the AuthorizeFilter doFilterInternal request uri:  "+requestUri);
        } 
		// excluded URLs: see# http://stackoverflow.com/questions/9908124/spring-mvc-3-interceptor-on-all-excluding-some-defined-paths
		//filter is not login the url
		if(excludedUrls!=null && excludedUrls.size()>=0){
			for (String url : excludedUrls) {
				if (requestUri.endsWith(url)) {
					filterChain.doFilter(request, response);
					return ;
				}
			}
		}
		//here is filter the is not login request the dir
		if(excludedDirs!=null && excludedDirs.size()>=0){
			for (String url : excludedDirs) {
				if (requestUri.indexOf(url)!=-1) {
					filterChain.doFilter(request, response);
					return ;
				}
			}
		}
		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute(SystemConstant.LOGINUSERMARKER) == null) {
			String str = "user not login this system, pls you login our system first !";
			//see# http://stackoverflow.com/questions/12713873/spring-3-1-how-do-you-send-all-exception-to-one-page
			if (log.isDebugEnabled()) {  
				log.debug(str);  
	        }
			StringBuffer path = new StringBuffer("/login.jsp");
			request.getRequestDispatcher(path.toString()).forward(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
