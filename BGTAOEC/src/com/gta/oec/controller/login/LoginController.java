/**
 * LoginController.java	  V1.0   2014-4-21 下午2:25:46
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.gta.oec.common.security.encoder.Md5PwdEncoder;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.controller.user.UserController;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.CustomerDisableException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.user.UserVo;

@Controller
public class LoginController {
	private  final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 * 功能描述：验证用户是否登录
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：20142014-4-21 下午4:54:50</p>
	 *
	 * @param request
	 * @param response
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
    @RequestMapping("/login/checkUserLogin.ajax")
	public void checkUserLogin(HttpServletRequest request,HttpServletResponse response) {
		boolean result = true;
		try {
			SiteUtils.getUser(request);
		} catch (LoginException e) {
			result = false;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}
    /**
     * 
     * 功能描述：获取登录弹出框
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：20142014-4-21 下午5:05:48</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/login/getLoginPoup.ajax")
	public String getLoginPoup(HttpServletRequest request,HttpServletResponse response) {
	   return "login/poup.htm";
	}
    /**
     * 
     * 功能描述：
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：20142014-4-22 上午9:10:29</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/login/loginAjax.ajax",method=RequestMethod.POST)
    public void loginAjax(HttpServletRequest request,HttpServletResponse response) {
    	String account =  RequestUtils.getQueryParam(request, "account");//登录账号
    	String passWord = RequestUtils.getQueryParam(request, "passWord");//登录密码
    	
    	String errorMsg = null;
    	boolean result = true;
    	if (StringUtils.isBlank(account)) {
			errorMsg = "登录账号不能为空！";
			result = false;
		}
    	if (result&&!StrUtils.isEmail(account)) {
			errorMsg = "登录账号必须为邮箱！";
			result = false;
		}
    	if (result&&StringUtils.isBlank(passWord)) {
			errorMsg = "密码不能为空！";
			result = false;
		}
    	if (result) {
        	try {
        		UserVo	userVo = new UserVo();
        		userVo.setUserEmail(account);
        		userVo.setPassword(new Md5PwdEncoder().encodePassword(passWord));
        		userVo = userService.userLogin(userVo);
        		SiteUtils.setUser(request, userVo);
        		result = true;
    		} catch (CustomerDisableException e) {
    			errorMsg = "您的账号已被禁用！";
    			result = false;
    		}catch (BaseException e) {
    			errorMsg = "账号/密码不正确！";
    			result = false;
    		}catch (Exception e) {
    			errorMsg = "系统正忙，请稍后！";
    			result = false;
    		}
		}
    	logger.info("customer login :account"+account+" resutl-"+result+" errorMsg-"+errorMsg);
    	JSONObject json = new JSONObject();
    	json.put("errorMsg", errorMsg);
    	json.put("result", result);
    	ResponseUtils.renderJson(response, json.toString());	
	}
    @RequestMapping("/login/loadTop.ajax")
    public String loadTop(HttpServletRequest request,HttpServletResponse response) throws LoginException{
    		return "common/top.htm"; 
    }
    @Autowired
    UserService userService;
}
