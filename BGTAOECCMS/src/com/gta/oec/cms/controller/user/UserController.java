package com.gta.oec.cms.controller.user;

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.enums.user.UserStateEnum;
import com.gta.oec.cms.md5.Md5PwdEncoder;
import com.gta.oec.cms.service.profession.IProfessionService;
import com.gta.oec.cms.service.user.UserService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.JavaMailTool;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.user.User;

import freemarker.template.Template;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	
	private static Logger log = Logger.getLogger(UserController.class);


	//图片服务器路径
	private String applicationUrl=ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
	//图片路径
	private String imgUrl=SystemConstant.USERPATH;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IProfessionService professionService;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Autowired
	private IProfessionService proService;
	
	/*
	@RequestMapping(value="/findAllUser")
	public ModelAndView findAllUser(HttpServletRequest req,HttpServletResponse resp){
		List<User> users = userService.queryAllUser();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userList.ftl");
		mav.getModelMap().put("users", users);
		mav.getModelMap().put("listMessage", "enter the UserController and the execute the findAllUser method! ");
		return mav;
	}
	
	@RequestMapping(value="/findAUser")
	public ModelAndView findAUser(){
		log.info("enter the UserController and the execute the findAllUser method! ");
		List<User> users = userService.queryAllUser();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userList.jsp");
		mav.getModelMap().put("users", users);
		mav.getModelMap().put("listMessage", "enter the UserController and the execute the findAllUser method! ");
		return mav;
	}
	
	@RequestMapping(value="/findAJUser")
	public String findUser(HttpServletRequest request,HttpServletResponse response){
		log.info("enter the UserController and the execute the findAllUser method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<User>()));
		return "user/uList.jsp";
	}
	
	@RequestMapping(value="/test1")
	public String test1(HttpServletRequest request,HttpServletResponse response){
		log.info("enter the UserController and the execute the findAllUser method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<User>()));
		return "user/test.html";
	}
	
	@RequestMapping(value="/test2")
	public String test2(HttpServletRequest request,HttpServletResponse response){
		log.info("enter the UserController and the execute the findAllUser method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<User>()));
		return "user/uList.jsp";
	}
	
	@RequestMapping(value="/test3")
	public ModelAndView test3(HttpServletRequest request,HttpServletResponse response){
		log.info("enter the UserController and the execute the findAllUser method! ");
		List<User> users = userService.queryAllUser();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userList.ftl");
		mav.getModelMap().put("users", users);
		mav.getModelMap().put("listMessage", "enter the UserController and the execute the findAllUser method! ");
		
		return mav;
	}
	
	@RequestMapping(value="/findAJUserJson")
	public void findAJUser(HttpServletRequest request,HttpServletResponse response){
		PageModel<User> page = initPaginationInfo(new PageModel<User>(), request);
		
		List<User> users = this.userService.allUserPageQuery(page);
		page.setResult(users);
		log.debug(this.gson.toJson(page));
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
        jsonMap.put(SystemConstant.TOTAL, page.getTotalSize());//total键 存放总记录数，必须的  
        jsonMap.put(SystemConstant.ROWS, page.getResult());//rows键 存放每页记录 list  
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value="/saveUser")
	public void saveUser(User user,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		try{
			this.userService.saveUser(user);
			jsonMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value="/updateUser")
	public void updateUser(User user,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		try{
			this.userService.updateUser(user);
			jsonMap.put("success", true);  
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	//delUser
	@RequestMapping(value="/delUser")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		try{
			this.userService.removeUser(Integer.parseInt(userId));
			jsonMap.put("success", true);  
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping("/userPageList")
	public ModelAndView userPageList(HttpServletRequest request,HttpServletResponse response){
		PageModel<User> page = initPaginationInfo(new PageModel<User>(), request);
		List<User> users = this.userService.allUserPageQuery(page);
		page.setResult(users);
		log.debug(new GsonBuilder().create().toJson(page));
		String pageStr=String.format("<a href=\"%s\">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"%s\">下一页</a>",
						request.getRequestURI()+"?pageIndex="+page.getPrevPageIndex(),request.getRequestURI()+"?pageIndex="+page.getNextPageIndex() );
		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("user/listUser.jsp");
		mav.addObject("users",users);
		mav.addObject("pageStr",pageStr);
		return mav;
	}
	
	@RequestMapping("/userCtxPageList")
	public ModelAndView userContextPageList(HttpServletRequest request,HttpServletResponse response){
		PaginationContext<User> pc = initPaginationInfo(new PaginationContext<User>(), request);
		pc.addParameter("User_Password", "39e59f96b32675cc35d0b5d3ce5a74d7");
		pc.addParameter("User_Type", 2);
		pc = this.userService.userPageQuery(pc);
		
		log.debug(new GsonBuilder().create().toJson(pc));
		String pageStr=String.format("<a href=\"%s\">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"%s\">下一页</a>",
						request.getRequestURI()+"?pageIndex="+pc.getPrevPageIndex(),request.getRequestURI()+"?pageIndex="+pc.getNextPageIndex() );

		//制定视图，也就是list.jsp
		ModelAndView mav=new ModelAndView("user/listUser.jsp");
		mav.addObject("users",pc.getResult());
		mav.addObject("pageStr",pageStr);
		return mav;
	}*/

	/**
	 * 会员管理， 会员查询
	 * 
	 * @author can.xie
	 * @createDate 2014.3.19
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/searchAJUser")
	public String searchAJUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			log.info("enter the UserController and the execute the findAllUser method! ");
			request.setAttribute("page", this.gson.toJson(new PageModel<User>()));
			return "user/user_list.jsp";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * 查询用户列表
	 * @author can.xie
	 * @param user
	 * @param request
	 * @param response
	 * @param userstateenum
	 * @throws Exception
	 */
@RequestMapping("/searchUser")
public void findUserBySearch(User user,HttpServletRequest request,HttpServletResponse response,String userstateenum) throws Exception{
	//用于分页
	PaginationContext<User> pc = initPaginationInfo(new PaginationContext<User>(), request);
	try{
				Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map
				List<User> list = userService.findUserPageQuery(pc);
				//查询已推荐老师的个数 IsRecommended=1 为推荐
				jsonMap.put("isRecommended", 1);
				Integer count =userService.getTeacherByIsrc(jsonMap);
				
				pc.setResult(list);
				jsonMap.put("teahcerIsrcSzie", count);
		        jsonMap.put("total", pc.getTotalSize());//total键 存放总记录数，必须的  
		        jsonMap.put("rows", pc.getResult());//rows键 存放每页记录 list  
				ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
				
				 
		 }catch(Exception e){
			 e.printStackTrace();
			log.error(e);
			 throw  e;
		 }
	}
	@RequestMapping("/addUserUI")
	public ModelAndView addUserUI(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView("user/user_add.jsp");
		Map<String, Object> in =new HashMap<String, Object>();
		in.put("isRecommended", 1);
		Integer count =userService.getTeacherByIsrc(in);
		mav.getModelMap().put("teahcerIsrcSzie", count);
		return mav;
	}
	/**
	 * 添加老师
	 * @author can.xie
	 * @create data 2014.3.26
	 * @param user
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addUser")
	public void addUser(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> jsonMap=new HashMap<String, Object>();
		try {
			userService.insertUserByTeacher(user);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error(e);
			throw e;
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	/**
	 * 上传用户图片
	 * @author can.xie
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadUserImg", method = RequestMethod.POST)
	public void uploadUserImg(HttpServletRequest  request,HttpServletResponse response) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
	 try {
		 	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile userFile=multipartRequest.getFile("userFile");
			//上传图片
			jsonMap=WebUtils.uploadImg(userFile, applicationUrl+imgUrl);
			//如果已经上传了一张，删除前一张
			String fileNamed=request.getParameter("fileNamed");
		 	if(null !=fileNamed && !fileNamed.equals("")){
		 		WebUtils.delUserImg( fileNamed,applicationUrl);
		 	}
		    //图片相对路径用于页面显示
		    jsonMap.put("urlPath", imgUrl);
		    
		    log.info("图片上传路径"+applicationUrl+imgUrl);
	} catch (Exception e) {
		jsonMap.put("userFileName",null);
		log.error(e);
		e.printStackTrace();
		throw e;
	}
	   
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
/*	*//**
	 * 删除已上传图片
	 * @author can.xie
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/delUserImg")
	public void delUserImg(HttpServletRequest  request,HttpServletResponse response)throws Exception{
		Map<String,Object> jsonMap=new  HashMap<String,Object>();
		String patch=super.findCtxPath(request, SystemConstant.USERPATH);
		try{
			String userFileName = request.getParameter("fileName");
			String message=WebUtils.delUserImg(request, userFileName,patch);
			jsonMap.put("message", message);
			jsonMap.put("success", true);
		}catch(Exception e){
			 	e.printStackTrace();
				log.error(e);
				jsonMap.put("success", false);
				jsonMap.put("errorMsg", e.getMessage());
				throw e;
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}*/
	/**
	 * 动态下拉框 ，查询所有学校
	 * @author can.xie
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSchools")
	public void findSchoolAll(HttpServletRequest  request,HttpServletResponse response)throws Exception{
		try{
			List<User> schoolList=userService.getSchoolAll();
			 User user=new User();
			 user.setSchId("0");
			 user.setSchName("--------请选择-------");
			 schoolList.add(user);
			 User firstUser=schoolList.get(0);
			schoolList.set(schoolList.size()-1, firstUser);
			schoolList.set(0, user);
			ResponseUtils.renderJson(response, this.gson.toJson(schoolList));
		}catch(Exception e){
			 	e.printStackTrace();
				log.error(e);
			 	throw e;
		}
	}
	/**
	 * 改变用户状态或推荐
	 *  @author can.xie
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/userUpdate")
	public void UserUpdateById(HttpServletRequest  request,HttpServletResponse response)throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		try{
			
			String message=null;
			int upType=Integer.parseInt(request.getParameter("upType"));
			Integer userId=Integer.valueOf(request.getParameter("userId"));
			if(upType==1){
				userService.updateUserStateById(userId, UserStateEnum.DISABLED.getValue());
				message="注销成功!";
			}else if(upType==2){
				userService.updateUserStateById(userId, UserStateEnum.ABLE.getValue());
				message="启用成功!";
			}else if(upType==3){
				userService.updateTeacherIsRec(userId, 1);
				message="推荐成功!";
			}else{
				userService.updateTeacherIsRec(userId, 0);
				message="取消推荐成功!";
			}
			jsonMap.put("success", true);
			jsonMap.put("message", message);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		}catch(Exception e){
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error(e);
			 throw e;
		}
	}
	/**
	 * 行业岗位群初始化
	 * @author can.xie
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/findTeacherProfession")
	public void findTeacherProfession(HttpServletRequest request, HttpServletResponse response)
			throws Exception {  
		String jobGroupIdsInput=request.getParameter("jobGroupIdsInput");
		Map<String,Object> params=new HashMap<String,Object>();
		try {
			//页面模板
			Template template = freeMarkerConfigurer.getConfiguration()
				.getTemplate("/user/teachershinepopdiv.htm");
			params.put("jobGroupIdsInput", jobGroupIdsInput);
			professionService.getProfessionAndJobs(params);
		 
		 
			params.put("base", request.getContextPath());
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
			ResponseUtils.renderHtmlText(response, text);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}  
	}
	/**
	 * 验证
	 * email，用户名
	 * 是否存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUserFlag")
	public void checkUserFlag(HttpServletRequest  request,HttpServletResponse response)throws Exception{
		Map<String,Object> jsonMap=new  HashMap<String,Object>();
		boolean flag=false;
		try{
			String userEmail = request.getParameter("userEmail");
			String userName=request.getParameter("userName");
			String userId=request.getParameter("userId");
			Map<String, Object> params=new HashMap<String, Object>();
			 Integer count=0;
			// 验证邮箱是否存在
			if(null !=userEmail  && !"".equals(userEmail.trim())){
				params.put("userEmail", userEmail.trim());
			}//编辑的时候验证用户名是否存在
			else if (null !=userName && !"".equals(userName.trim()) && null!=userId && !"".equals(userId)){
				params.put("userName", userName.trim());
				params.put("userId",userId);
				
			} //验证用户名是否存在
			else if(null !=userName && !"".equals(userName.trim())) {
				params.put("userName", userName.trim());
			}
			count =userService.findUserCount(params);
			 if(null !=count && count >0){
				 flag=true;
			 }
			jsonMap.put("success", flag);
		}catch(Exception e){
				jsonMap.put("success", flag);
			 	e.printStackTrace();
				log.error(e);
				throw e;
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}
	/**
	 * 编辑老师页面
	 * @author can.xie
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUserTeacherUI")
	public ModelAndView editUserTeacherUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		try {
			ModelAndView modelView=new ModelAndView("user/user_edit.jsp");
			String userId=req.getParameter("userId");
			if(userId==null || "".equals(userId)){
				throw new NullPointerException();
			}
			Map<String,Object> in=new HashMap<String, Object>();
			in.put("userId",userId);
			User user =userService.findUserTearchObj(in);
			
			modelView.getModelMap().put("user", user);
			//查询岗位信息
			
			List<Job> jobList=proService.getJobByParams(in);
			StringBuffer proJobIds=new StringBuffer("");
			StringBuffer jobName=new StringBuffer("");
			for(Job job:jobList){
				proJobIds.append(job.getProID()).append("|").append(job.getJobID()).append(",");
				jobName.append(job.getJobName()).append(",");
			}
		
			String jobNames=(jobName==null || "".equals(jobName.toString())? "": jobName.deleteCharAt(jobName.length()-1).toString());
			String projobIdStr=(proJobIds==null || "".equals(proJobIds.toString())? "": proJobIds.deleteCharAt(proJobIds.length()-1).toString());
			//显示岗位群
			
			//查询已推荐老师的个数 IsRecommended=1 为推荐
			in.put("isRecommended", 1);
			Integer count =userService.getTeacherByIsrc(in);
			modelView.getModelMap().put("teahcerIsrcSzie", count);
			modelView.getModelMap().put("selectedShines",jobNames);
			modelView.getModelMap().put("jobGroupIdsInput",projobIdStr);
			return modelView;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/editUserTeacher")
	public void editUserTeacher(User user ,HttpServletRequest req,HttpServletResponse resp) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		 
		try{
			//修改user
				userService.updateUserByTeacher(user);
				//如果用户有更改图片则删除
				if(! (user.getUserImgUrl().equals(user.getUserHeadPic())) ){
					WebUtils.delUserImg(user.getUserHeadPic(),applicationUrl);
			}
			jsonMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(resp, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value="/magEmail")
	public void magEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		try{

			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/user/emailrepassword.htm");
			Map<String, String> map = new HashMap<String, String>();
			
	    	String passwordToEmail=request.getParameter("passwordToEmail");
	    	String userName=request.getParameter("userName");
	    	String userId=request.getParameter("userId");
			
			String host = ApplicationPropertiesUtil.getStringValue("mail.host");
			String sendEmail = ApplicationPropertiesUtil.getStringValue("mail.email");
			String password = ApplicationPropertiesUtil.getStringValue("mail.password");
			String userPwd = WebUtils.randomString(5)+WebUtils.randomInt(1);
			
			map.put("userName", userName);
			map.put("userPwd", userPwd);
			String messageContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			JavaMailTool JavaMailTool = new JavaMailTool(host,sendEmail, password,
					passwordToEmail,sendEmail, "发送邮件", messageContent);
			JavaMailTool.start();
			
			//发送邮件成功之后再修改数据库密码
			User user =new User();
			user.setUserId(Integer.parseInt(userId));
			user.setPassword(new Md5PwdEncoder().encodePassword(userPwd));
			userService.updateTeahcerByUser(user);
			
			
			jsonMap.put("success", true);  
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
			throw e;
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
}
