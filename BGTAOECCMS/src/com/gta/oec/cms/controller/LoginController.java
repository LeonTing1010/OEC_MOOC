/**
 * LoginController.java	  V1.0   2014-4-16 下午3:22:51
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.cms.exception.AuthorizationException;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.vo.user.User;

@Controller
public class LoginController extends BaseController<User> {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws AuthorizationException{
		log.debug("login controller ..........");
		Map<String,Object> map = new HashMap<String,Object>();
		map = this.buildParametersToMap(map, request);
		String username = (String)map.get("username"),password = (String)map.get("userpassword");
		if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
			if(username.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("123456")){
				HttpSession session = request.getSession();
				session.setAttribute(SystemConstant.LOGINUSERMARKER,new User());
				map.put("success", Boolean.TRUE);
			}else{
				map.put("success", Boolean.FALSE);
				map.put("errorMsg", "登录失败,用户名或者密码错误!");
			}
		}else{
			map.put("success", Boolean.FALSE);
			map.put("errorMsg", "登录失败,用户名或者密码不能为空!");
		}
		ResponseUtils.renderHtmlText(response, gson.toJson(map));
		//return "index.jsp";
	}
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws AuthorizationException{
		HttpSession session = request.getSession();
		session.removeAttribute(SystemConstant.LOGINUSERMARKER);
		String path = "/login.jsp";
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new AuthorizationException(e.getMessage());
		}
	}
	
	@RequestMapping(value="/loginPage")
	public void loginPage(HttpServletRequest request,HttpServletResponse response) throws AuthorizationException{
		String path = "/login.jsp";
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new AuthorizationException(e.getMessage());
		}
	}
	
}
