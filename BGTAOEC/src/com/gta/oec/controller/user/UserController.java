/**
 * LoginController.java	  V1.0   2013-12-30 ����9:02:52
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import sun.misc.BASE64Encoder;

import com.gta.oec.common.security.encoder.Md5PwdEncoder;
import com.gta.oec.common.web.email.SendEmailTemplate;
import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.student.StudentService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.ImageUtils;
import com.gta.oec.vo.common.CaptchaVO;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：用户信息验证
 *
 * @author  Miaoj
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
public class UserController {
	private  final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService; 
    /**
     * 功能描述：注销登录
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-24 下午1:08:19</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/logout/")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
		SiteUtils.removeUser(request);
    	return "redirect:/user/loginIndex/";
	}
    /**
     * 
     * 功能描述：更换角色
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年4月24日 下午4:56:41</p>
     *
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/changeUser/")
    public String changeUser(HttpServletRequest request,HttpServletResponse response) throws LoginException {
		UserVo userVo = SiteUtils.getUser(request);
		String type = RequestUtils.getQueryParam(request, "type");  /**1.学生  2.老师**/
	    if ("1".equals(type)) {
	    	userVo.setUserType(1);    	
		}else {
			userVo.setUserType(2);
		}
	    SiteUtils.setUser(request, userVo);
    	return "common/personalcentertop.htm";
	}
    @RequestMapping("/user/loginIndex/")
	public String loginIndex(HttpServletRequest request,UserVo user,ModelMap modelMap) { 
    	
      String returnUri = RequestUtils.getQueryParam(request, "returnUri");
      modelMap.put("returnUri", returnUri);
	  return "login/login.htm";
	}
     /**
      * 
      * 功能描述：检查登录用户信息是否正确，正确则跳转到首页，否则停留在登录页面
      *
      * @author  Miaoj
      * <p>创建日期 ：2014-1-9 下午5:44:12</p>
      *
      * @param request
      * @param model
      * @return
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws BaseException 
     * @throws UnsupportedEncodingException 
      */
    @RequestMapping("/user/login/")
	public String checkLoginUser(HttpServletRequest request,UserVo user,ModelMap modelMap) throws BaseException, UnsupportedEncodingException{ 
       request.setCharacterEncoding("UTF-8");
       String strLoinType= request.getParameter("login");
       String returnUri = RequestUtils.getQueryParam(request, "returnUri");
       if(strLoinType!=null&&strLoinType.equals("登录"))
       {
    	   UserVo userModel= new UserVo();
    	   userModel=user;
    	   userModel.setPassword(new Md5PwdEncoder().encodePassword(userModel.getPassword()));
			
	       UserVo userResult= userService.getUserByVo(userModel);
	       if(userResult != null && userResult.getUserId()>0)
	       {
	    	  //将用户信息存入到Session中
	    	  SiteUtils.setUser(request, userResult);
	    	
	    	  if (!StringUtils.isBlank(returnUri)) {
				return "redirect:"+returnUri;
			  }
	    	  return "redirect:/index/?headTab=1";
	       }
      }
      modelMap.put("errorMsg", "请输入正确的用户名或密码!");
	  return "login/login.htm";
	}
    
    /**
     * 
     * 功能描述：ajax检查登录用户信息是否正确，正确则跳转到首页，否则停留在登录页面
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-02-21 下午5:44:12</p>
     *
     * @param request
     * @param model
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    * @throws BaseException 
    * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/user/loginUser/")
    public void checkUserLogin(HttpServletRequest request,UserVo user,ModelMap modelMap,HttpServletResponse response) throws BaseException, UnsupportedEncodingException {
    	request.setCharacterEncoding("UTF-8");
        String strLoinType= request.getParameter("login");
        String returnUri = RequestUtils.getQueryParam(request, "returnUri");
        String userEmail = RequestUtils.getQueryParam(request, "userEmail");
        String randomCode = RequestUtils.getQueryParam(request, "randomCode");
        //randomCode is null; will continue;
        if(!StringUtils.isEmpty(randomCode)){
        	String checkCode = SiteUtils.getCheckCode(request);
        	JSONObject json = new JSONObject();
        	if(!StringUtils.isEmpty(checkCode)){
        		if(!randomCode.equals(checkCode)){
            		json.put("errorMsg", "验证码不正确!");
            		json.put("success", false);
            		ResponseUtils.renderJson(response, json.toString());
            		return ;
        		}
        	}else{
        		json.put("errorMsg", "验证码不正确!");
        		json.put("success", false);
        		ResponseUtils.renderJson(response, json.toString());
        		return ;
        	}
        }
        if(!StringUtils.isBlank(strLoinType))
        {
     	   UserVo userModel= new UserVo();
     	   UserVo userResult = new UserVo();
     	   String UserCode = null;
     	   String uemail = null;
     	   userModel=user;
 		   userModel.setPassword(new Md5PwdEncoder().encodePassword(userModel.getPassword()));
 		   userResult = userService.getUserByVo(userModel);
 		   
 		   if(userResult == null){
 			   JSONObject json = new JSONObject();
 			   json.put("errorMsg", "帐号或密码不正确!");
			   json.put("success", false);
			   ResponseUtils.renderJson(response, json.toString());
			   return ;
 		   } 		  
 		   
 		  /** add by liuli 
    	    * 如果用户在后台已被禁用，则不能登陆
    	    * */
    	   if(userResult.getUserState() == 0){
    		   JSONObject json = new JSONObject();
 			   json.put("errorMsg", "该用户已被禁用，请联系后台取消禁用后再登陆!");
 			   json.put("success", false);
 			   ResponseUtils.renderJson(response, json.toString());
    		 return ;
    	   }
 		   
 		   //判断从提问进入时，是否是老师登陆，如果是，则不请允许登录    -刘祚家
 		   if((returnUri.contains("createQuestion") ||returnUri.contains("helpme"))  && userResult!=null && userResult.getUserType()==2){
 			   JSONObject json = new JSONObject();
 			   json.put("errorMsg", "教师账号不允许提交问题!");
 			   json.put("success", false);
 			   ResponseUtils.renderJson(response, json.toString());
 			   return ;
 		   }
 		   
 		   if(userResult!=null){
 			  uemail = userResult.getUserEmail(); 
 		   }else{
 			   uemail = userModel.getUserEmail();
 		   }
 		   
 		   if(!uemail.equals(userModel.getUserEmail())){
 			  userResult = null;
 		   }else{
 			  if(userResult!=null){
 	 			  UserCode=userModel.getUserEmail()+"|"+userModel.getPassword()+"|"+SiteUtils.getCode();
 	 		   }else if(userResult==null){
 	 			  UserCode=null;
 	 		   }
 			   userModel.setUserLoginInfo(UserCode);
 	 	       userResult= userService.getUserByVo(userModel);
 		   }
 	       if(userResult != null && userResult.getUserId()>0)
 	       {
 	    	  //将用户信息存入到Session中
  	    	  SiteUtils.setUser(request, userResult);
 	    	  //TODO 单点登录未完成，此处需要跳到登录成功的页面，后期需要完善  add by:缪佳
 	    	 JSONObject json = new JSONObject();
 	    	  if (!StringUtils.isBlank(returnUri)) {
 	    		json.put("returnUri", returnUri);
 	    		json.put("success", true);
 	    		ResponseUtils.renderJson(response, json.toString());
 	    		return ;
 			  }
 	    	  
 	    	  json.put("returnUri", request.getContextPath()+"/index/?headTab=1");
	    	  json.put("success", true);
	    	  ResponseUtils.renderJson(response, json.toString());
	    	  return ;
 	       }
       }
	    JSONObject json = new JSONObject();
	    if(userEmail!=null&&!"".equals(userEmail)){
	    	   json.put("userEmail", userEmail);
	       }
		json.put("errorMsg", "请输入正确的用户名或密码!");
		json.put("success", false);
		ResponseUtils.renderJson(response, json.toString());
    }
    
    
    /**
     * 功能描述：打开注册页面
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-16 下午4:48:49</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/loadreg/")
    public String loadReg(HttpServletRequest request,
			HttpServletResponse response)
    {
		try {
			SiteUtils.setCheckCode(request, "");
		} catch (LoginException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}
		return "register/reg.htm";	
    }
    
    
    /**
     * 
     * 功能描述：打开注册页面
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-16 下午4:48:49</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/loadlogin/")
    public String loadLogin(HttpServletRequest request,
			HttpServletResponse response)
    {
		return "login/login.htm";	
    }
    
    /**
     * 
     * 功能描述：保存注册用户信息到数据库
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-9 下午8:09:26</p>
     *
     * @param user
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws UnsupportedEncodingException 
     * @throws BaseException 
     */
	@RequestMapping("/user/reg/")
	public String registerUser(UserVo user, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws BaseException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}
		//用户信息
        int    roleID =1;
		String userName = request.getParameter("username");
		String userEmail = request.getParameter("useremail");
		String password = request.getParameter("password");
		String pwdOk = request.getParameter("password2");
		String education = request.getParameter("education");
		String randomCode = request.getParameter("randomCode");
		String errorMsg ="";
		if(SiteUtils.getCheckCode(request) != null && !SiteUtils.getCheckCode(request).equals(""))
		{
			if(!randomCode.equals(SiteUtils.getCheckCode(request)))
			{	
				errorMsg = "验证码不正确！";
				modelMap.put("errorMsg", errorMsg);
				modelMap.put("uname", (userName!=null&&!"".equals(userName))?userName:"");
				modelMap.put("uemail", (userEmail!=null&&!"".equals(userEmail))?userEmail:"");
				modelMap.put("pwd", (password!=null&&!"".equals(password))?password:"");
				modelMap.put("pwdOk", (pwdOk!=null&&!"".equals(pwdOk))?pwdOk:"");
				modelMap.put("edu", (education!=null&&!"".equals(education))?education:"");
				return "register/reg.htm";	
			}
			else
			{
				SiteUtils.setCheckCode(request, "");
			}
		}
		else
		{
			errorMsg = "验证码不正确！";
			modelMap.put("errorMsg", errorMsg);
			return "register/reg.htm";	
		}
		UserVo userModel= new UserVo();
		if(userName!= null && !userName.equals(""))
		{
			userModel.setUserName(userName);		
		}
        if(userEmail != null && !userEmail.equals(""))
        {
    		userModel.setUserEmail(userEmail);
        }       
        if(password != null && !password.equals(""))
        {       	    		
			userModel.setPassword(new Md5PwdEncoder().encodePassword(password));
        }
		userModel.setRoleID(roleID);	
		
		//学生信息
		String strEducation = request.getParameter("education");
		//验证用户名
		List<UserVo> listInfor=userService.getUserByName(userName);
		if(listInfor.size()>0)
		{
			//TODO 后台验证用户名信息是否存在 看是否需要提示
			errorMsg = "用户名已存在！";
			modelMap.put("errorMsg", errorMsg);
			return "register/reg.htm";		
		}
		
		UserVo userTemp= new UserVo();
		if(userEmail!= null && !userEmail.equals(""))
		{
			userTemp.setUserEmail(userEmail);		
		}
		
		//验证邮箱
		List<UserVo> listInforTemp=userService.getUserByUserVo(userTemp);
		if(listInforTemp.size()>0)
		{
			//TODO 后台验证邮箱信息是否存在 看是否需要提示
			errorMsg = "邮箱已存在！";
			modelMap.put("errorMsg", errorMsg);
			return "register/reg.htm";		
		}
        if(strEducation == null || strEducation.equals("") ||userModel == null)
        {
        	 return "register/reg.htm";
        }
        else
        {  
        	userModel.setUserType(1);
        	userModel.setCreateDate(new Date());
        	userModel.setUserState(1);
        	UserVo userVo = userService.savaUser(userModel);
        	if(userVo!=null)
        	{
        		if(strEducation != null && !strEducation.equals(""))
				 {
					 if(userModel != null)
					 {
						 userVo = userService.getUserById(userVo.getUserId());
						 StudentVO studentModel= new StudentVO();
						 studentModel.setUserID(userVo.getUserId());		
						 studentModel.setEducation(strEducation);
						 StudentVO studenVo= studentService.saveStudent(studentModel);
						 if(studenVo!= null )
						 {      
						        modelMap.put("UserNameAll", userEmail);
						        
						        // 注册完之后要把用户相关信息放到session中去 
						        SiteUtils.setUser(request, userVo);
						        // 注册成功后给新用户的邮箱发送成功提示邮件
						        try {
						        	Map<String,Object> root = new HashMap<String, Object>();
						        	String emailImgAddress = MessageResolver.getMessage(request,"email.img.address", null);
							        root.put("regUserName",userVo.getUserName());
							        root.put("emailImgAddress",emailImgAddress);
							        sendEmailTemplate.sendTemplateMail(root, userVo.getUserEmail(),"国泰安网络教育注册成功通知","regSuccessSendEmail.ftl");  
								} catch (Exception e) {
									e.printStackTrace();
									logger.error("发送邮件时报错："+e.getMessage());
								}
						        
							 return "register/regsuccess.htm";
						 }
						 else
						 {
				            	return "login/login.htm";
						 }
					 }
				 }
            	return "login/login.htm";
        	}
        }
    	return "login/login.htm";
	}
	/**
	 * 
	 * 功能描述：检查用户是否存在
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-13 下午4:55:09</p>
	 *
	 * @param user
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/user/checkusername.ajax")
	public void CheckUserInfor(HttpServletRequest request,HttpServletResponse response) throws BaseException {
		String userName = RequestUtils.getQueryParam(request, "username"); 
		UserVo loginUserVo=null;
		List<UserVo> listInfor=new ArrayList<UserVo>();
		boolean result = false;
		try {
			loginUserVo=SiteUtils.getUser(request);
		} catch (Exception e) {
			
		}
		//未登录用户.
		if (loginUserVo==null) {
			try{
				listInfor=userService.getUserByName(userName);
			}catch(Exception e){
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			if(listInfor.size()>0){
				if(!userName.trim().equals(listInfor.get(0).getUserName())){
					result = false;
				}else{
					result = true;
				}
			}
		}//获取的用户名和自己的用户名不相同.
		else if (!userName.trim().equals(loginUserVo.getUserName())) {
			listInfor=userService.getUserByName(userName);
			if(listInfor.size()>0){
				result = true;
			}
		}
		
           
	    JSONObject json  = new JSONObject();
        json.put("result", result);
	    ResponseUtils.renderJson(response, json.toString());	
	}
	
	/**
	 * 
	 * 功能描述：获取检查的验证码
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-15 下午4:29:13</p>
	 *
	 * @param request
	 * @param response
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/user/CheckCode/")
	public void productCheckCode(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws BaseException
	{
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
		CaptchaVO captchaVo = new CaptchaVO();
        try {
        	captchaVo=ImageUtils.creatImage(true);
		} catch (IOException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}
        try {
        	if(captchaVo!= null)
        	{
        	  SiteUtils.setCheckCode(request, captchaVo.getCaptchaValue());
        	  modelMap.put("checkcode", captchaVo.getCaptchaValue());
        	  ImageIO.write(captchaVo.getImage(), "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        	}
        } catch (Exception e) {
            logger.error(e.getMessage());e.printStackTrace();
        }
	}
    
	/**
	 * 
	 * 功能描述：Md5加密
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-13 下午1:48:57</p>
	 *
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public String EncoderPwdByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
   }
	
	/**
	 * 
	 * 功能描述：检查用户邮箱是否已存在
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-14 下午2:26:56</p>
	 *
	 * @param request
	 * @param response
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/user/checkusereamil.ajax")
	public void CheckUserEmail(HttpServletRequest request,HttpServletResponse response) throws BaseException {
		UserVo loginUserVo=null;
		List<UserVo> listInfor=new ArrayList<UserVo>();
		boolean result = false;
		UserVo userTemp=new UserVo();
		try {
			loginUserVo=SiteUtils.getUser(request);
		} catch (Exception e) {
		}
		String userEmail = RequestUtils.getQueryParam(request, "userEamil");
		if (!StringUtils.isBlank(userEmail)) {
			userTemp.setUserEmail(userEmail);
		}
		//未登录用户.
		if (loginUserVo==null) {
			listInfor = userService.getUserByUserVo(userTemp);
			if(listInfor.size()>0){
				if(userEmail.trim().equals(listInfor.get(0).getUserEmail())){
					result = false;
				}else{
					result = true;
				}
			}else{
				result = true;
			}
		}//获取的邮箱和自己的邮箱相同.
		else if (!userEmail.trim().equals(loginUserVo.getUserEmail())) {
				listInfor = userService.getUserByUserVo(userTemp);
				if(listInfor.size()>0){
					result = false;
				}else {
					result=true;
				}
		}else {
			result=true;
		}
		
	    JSONObject json  = new JSONObject();
	    json.put("result", result);
	    ResponseUtils.renderJson(response, json.toString());		
	}
	
	/**
	 * 
	 * 功能描述：检查用户邮箱是否已存在
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-14 下午2:26:56</p>
	 *
	 * @param request
	 * @param response
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/user/checkrandomcode.ajax")
	public void CheckRandomCode(HttpServletRequest request,HttpServletResponse response) throws BaseException {
		String RandomCode = RequestUtils.getQueryParam(request, "RandomCode");
		boolean result = false;
		if(RandomCode!= null && !RandomCode.equals(""))
		{
			if(SiteUtils.getCheckCode(request).equals(RandomCode)){
				result = true;
			}
		}
	    JSONObject json  = new JSONObject();
	    json.put("result", result);
	    ResponseUtils.renderJson(response, json.toString());		
	}
	
	/**
	 * 
	 * 功能描述：通过邮箱获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午2:38:52</p>
	 *
	 * @param userEmail
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public  List<UserVo> getUseByEmail(String userEmail)
	{
		UserVo userMode= new UserVo();
		userMode.setUserEmail(userEmail);
		List<UserVo> listInfor = null;
		try {
			listInfor = userService.getUserByUserVo(userMode);
		} catch (BaseException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}		
        return listInfor;
	}
	/**
	 * 
	 * 功能描述：跳转到找回密码的地方
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-15 下午6:47:02</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/user/forgetpassword/")
	public String loadForgetPassword(HttpServletRequest request,HttpServletResponse response)
	{
		return "login/forgetpassword.htm";
	}
	
	
    /**
     * 
     * 功能描述：发送修改密码的邮件
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-16 上午9:43:23</p>
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/sendemail/")
    public void sendPasswordEmail(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws BaseException {
    	String strUserEmail = request.getParameter("userEmail");
		List<UserVo> listInfor = getUseByEmail(strUserEmail);

    	try {
	    	String strCodeMd5=null;
	    	String strCode= SiteUtils.generateWord().toUpperCase();
	    	strCodeMd5=new Md5PwdEncoder().encodePassword(strCode);
	    	if(strCodeMd5 == null || strCodeMd5.equals(""))
	    	{
	    	}
			int intNumber=userService.updateUserPwdCode(strCodeMd5,strUserEmail);
			if(intNumber == 0)
			{
			}
			
			JSONObject json  = new JSONObject();
			try {
				// 注册成功后给新用户的邮箱发送成功提示邮件
			       Map<String,Object> root = new HashMap<String, Object>();
			       String emailImgAddress = MessageResolver.getMessage(request,"email.img.address", null);
			       root.put("emailImgAddress",emailImgAddress);
			       root.put("memberUserName",listInfor.get(0).getUserName());
			       root.put("emailCode",strCode);
			       sendEmailTemplate.sendTemplateMail(root, listInfor.get(0).getUserEmail(),"国泰安网络教育重设密码通知","forPwdSendEmailGetCode.ftl"); 
					
			       json.put("result", 1);
	         		ResponseUtils.renderJson(response, json.toString());
			} catch (Exception e) {
				json.put("result", 2);
         		ResponseUtils.renderJson(response, json.toString());
				e.printStackTrace();
				logger.error("发送邮件时失败："+e.getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());e.printStackTrace();
		} 
	}
    
	/**
	 * 
	 * 功能描述：检查重设密码的验证码
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-17 上午9:27:27</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
    @RequestMapping("/user/passwordtwo/")	
    public void CheckResetPassword(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
    {
    	JSONObject json  = new JSONObject();
    	json.put("resutl", 0);
    	//检查完后更新验证码 将其设置为空
    	String strUserEmail= request.getParameter("hduserEmail");
    	String strUserCode = request.getParameter("emailCode").toUpperCase();
    	String strCodeMd5=null;
		strCodeMd5=new Md5PwdEncoder().encodePassword(strUserCode);
		
		List<UserVo> listInfor = getUseByEmail(strUserEmail);
		if(listInfor.size()==1){
	    	//检查邮箱Code是否正确
			if(!listInfor.get(0).getUserResetPwdCode().equals(strCodeMd5))
			{
				json.put("result", 2);
			}
		}
		
		ResponseUtils.renderJson(response, json.toString());
    }
    
	/**
	 * 
	 * 功能描述：跳转到输入密码页面
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-17 上午9:27:27</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
    @RequestMapping("/user/skipToForgetPassword2/")	
    public String skipToForgetPassword2(){
    	
    	return "login/forgetpassword2.htm";
    }
    /**
     * 
     * 功能描述：更新数据库密码
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-17 上午10:09:34</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/user/repasswordthree/")	
    public String  updatePassword(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap)
    {
    	String strUserEmail= request.getParameter("userEmail");	
    	//获取用户密码
    	String strPassword= request.getParameter("password");	
    	if(strUserEmail== null ||strUserEmail.equals("") ||strPassword== null ||strPassword.equals(""))
    	{
    		return "login/forgetpassword.htm";   		
    	}
    	String PassWordMd5=null;
    	PassWordMd5=new Md5PwdEncoder().encodePassword(strPassword);    		
	
    	int intFalg=0;
    	if(PassWordMd5 != null && !PassWordMd5.equals(""))
    	{
    		try {
    			intFalg=userService.updateUserPwd(PassWordMd5, strUserEmail);
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
    	}
    	if(intFalg==1)
    	{
    		return "login/forgetpassword3.htm";
    	}
    	return "login/forgetpassword.htm"; 
    }
    
    /**
   	 * 功能描述：发送邮件
   	 *
   	 * @author  li.liu
   	 * <p>创建日期 ：2014-4-25 下午3:47:35</p>
   	 *
   	 *
   	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   	 */
   	public void endEmail(String receiveEmail,Map<String,Object> root,String emailModeName){  
   	        try {
				sendEmailTemplate.sendTemplateMail(root, "liulixajh@sina.cn","主题标题","regSuccessSendEmail.ftl");
			} catch (Exception e) {
				e.printStackTrace();
			}  
   	 }  

	@Autowired
	private FreeMarkerConfig freemarkerConfig;
	@Autowired
	private SendEmailTemplate sendEmailTemplate;  

	  @Resource(name="sendEmailTemplate")  
	  public void setTemplateEmail(SendEmailTemplate sendEmailTemplate) {  
	      this.sendEmailTemplate = sendEmailTemplate;  
	  }   
	  
	  
}
	


