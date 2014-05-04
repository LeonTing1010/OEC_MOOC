/**
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.studentcenter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.security.encoder.Md5PwdEncoder;
import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.springmvc.RichFreeMarkerView;
import com.gta.oec.common.web.upload.FileRepository;
import com.gta.oec.common.web.upload.UploadUtils;
import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.common.web.utils.PathUtils;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.mycollect.MyCollectService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.student.StudentService;
import com.gta.oec.service.studentcenter.StudentcenterService;
import com.gta.oec.service.teacher.TeacherService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.user.UserVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;


@Controller
public class StudentcenterController {
	private static final Log logger = LogFactory.getLog(StudentcenterController.class);
	 /**
     * 
     * 功能描述：【学生中心首页】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-20 下午1:10:42</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
	 * @throws BaseException 
     */
    @RequestMapping("/studentCenter/index/") 
    public String index(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws BaseException {  	
    	String tab = RequestUtils.getQueryParam(request, "tab");
    	String type = RequestUtils.getQueryParam(request, "type"); 
    	UserVo user = SiteUtils.getUser(request);
    	
    	//如是老师，则默认跳转至个人中心
    	if (user.getIsTeacher()==1&&StringUtils.isBlank(type)&&StringUtils.isBlank(tab)) {
			return "redirect:/teacherCenter/index/";
		}
     	if (!StrUtils.isNum(tab)) {
   	    	tab = "0";
    	}
    	modelMap.put("tab",tab);
    
    	int userId = user.getUserId();
    	user=userService.getUserById(userId);
    	SiteUtils.setUser(request, user);
	    //查询答疑提问问题id集合
    	List<?> quesAnswerList = questionCenterService.getQuesIdListByUserId(userId,0);
    	//查询课程提问问题id集合
    	List<?> courAnswerList = questionCenterService.getQuesIdListByUserId(userId,1);
    	int answIsRead = 0; //0表示未读的回答
    	int quesAnswerCount = 0;
    	int courAnswerCount = 0;
    	
    	//答疑提问未读的条数
    	if(quesAnswerList!=null&&quesAnswerList.size()>0){
    		quesAnswerCount = questionCenterService.getHasAnswerQuestionCount(quesAnswerList, answIsRead);
    	}
    	
    	if(courAnswerList!=null&&courAnswerList.size()>0){
    		//课程提问未读的条数
    	    courAnswerCount = questionCenterService.getHasAnswerQuestionCount(courAnswerList, answIsRead);
    	}
    	//考试总数
    	int AllExamCount = courseService.getCourseExamCount(userId,1);
		//已考数
	    int hasExamCount = examService.getExamStudentListCount(userId, 1);
	    //作业总数
	    int AllTaskCount = courseService.getCourseExamCount(userId,3);
        //已作答作业数
		int hasTaskCount = examService.getExamStudentListCount(userId, 3);
		
		List<?> examNoticeList = courseService.getExamNoticeList(userId,1);
		
		List<?> taskNoticeList = courseService.getExamNoticeList(userId,3);
		 
    	PageModel jobcoll = null;
    	PageModel courexam =  null;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	StudentVO student = null;
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			JobVo jobVo = new JobVo();
    		jobVo.setJobPID(1); 
    		int pageSize = 4;
    		int collectType =1;
    		int examType = 1;
    		List<?> list = myCollectService.getRefIdList(userId,collectType);	
    		if(list!=null&&list.size()>0){
    			//此处无需分页
    			jobcoll =jobService.getJobNameList(list, Integer.parseInt(pageNo), 0);
    		}
		    student = studentService.getStuByUserId(userId);
			
			if(student==null){
				student = new StudentVO();
			}
			courexam = examService.getCourExamInfoList(userId, examType, Integer.parseInt(pageNo), pageSize);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		user.setUserType(1);
        SiteUtils.setUser(request,user);
		modelMap.put("user", user);
		modelMap.put("student", student==null?new StudentVO():student);
    	modelMap.put("pjobcoll", jobcoll==null?new PageModel():jobcoll);
    	modelMap.put("courexam", courexam==null?new PageModel():courexam);
    	modelMap.put("quesAnswerCount", quesAnswerCount);
    	modelMap.put("courAnswerCount", courAnswerCount);
    	modelMap.put("examCount", AllExamCount-hasExamCount);
    	modelMap.put("taskCount", AllTaskCount-hasTaskCount);
    	modelMap.put("examNoticeList", examNoticeList);
    	modelMap.put("taskNoticeList", taskNoticeList);
    	modelMap.put("type", 1);
    	return "stucenter/main.htm";
    }
      

    /**
     * 
     * 功能描述：个人资料修改后保存
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-1-22 下午4:37:10</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/student/myinfo/save/")
    public void savemyinfo(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	UserVo user = new UserVo();
    	user = SiteUtils.getUser(request);
    	StudentVO student = new StudentVO();
    	int userFlag = 0,studentFlag =0;
    	boolean result = false;
    	try {
    		String userName = request.getParameter("userName");
    		String userMobile = request.getParameter("userMobile");
    		String userEmail = request.getParameter("userEmail");
    		String studyGoal = request.getParameter("studyGoal");
    		String education = request.getParameter("education");
    		
    		user.setUserId(user.getUserId());
			user.setUserName(userName);
			user.setUserMobile(userMobile);
			user.setUserEmail(userEmail);
			
			student.setUserID(user.getUserId());
			student.setStudyGoal(studyGoal);
			student.setEducation(education);
			userFlag = studentcenterService.updateUser(user);
			studentFlag = studentService.updateStudent(student);
			result = (userFlag>0 && studentFlag >0)?true:false;
			
			modelMap.put("user", user);
	    	modelMap.put("student", student);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
        JSONObject json  = new JSONObject();
        json.put("result", result);
        ResponseUtils.renderJson(response, json.toString());	
    }
    
    /**
     * 
     * 功能描述：个人资料修改页面
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-1-17 上午10:45:55</p>
     *
     * @param userinfo
     * @param stuinfo
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/myinfo/update/")
    public String  myinfoUpdate(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	UserVo uservo =SiteUtils.getUser(request);
    	UserVo user = new UserVo();
    	StudentVO student = new StudentVO();
		// 获取用户表信息
		/*try {
			uservo = userService.getUserById(uservo.getUserId());
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}*/
    	try {
    		 user = studentcenterService.getUserById(uservo.getUserId());
    		 user.setUserId(uservo.getUserId());
    		 user.setUserName(uservo.getUserName());  //用户名
    		 user.setUserEmail(uservo.getUserEmail());  //邮箱
    		 user.setUserMobile(uservo.getUserMobile());	//手机号码
    		 student = teaService.getStuByUserId(uservo.getUserId());
    		 
    	} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	
    	modelMap.put("user", user);
    	modelMap.put("student", student);
    	return "stucenter/myinfo/updateinfo.htm";
    }
    

    
    /**
     * 
     * 功能描述：个人密码修改
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-1-17 上午10:46:16</p>
     *
     * @param userinfo
     * @param password
     * @param newpwd
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws BaseException 
     * @throws IOException 
     */
    @RequestMapping("/studentCenter/myinfo/updatepwd/")
    public void updatepwd(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, BaseException, IOException {  	

    	UserVo user = SiteUtils.getUser(request);
    	try {
    		user = studentcenterService.getUserById(user.getUserId());
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	String password = request.getParameter("oldpwd");
    	String newpwd = request.getParameter("newpwd");
    	String oldPwd = user.getPassword();
    	String pwdMd5 = null;
    	String newPwdMd5 = null;
    	String isRight = null;
    		pwdMd5 = new Md5PwdEncoder().encodePassword(password);
    		newPwdMd5 =  new Md5PwdEncoder().encodePassword(newpwd);
    		
    		if(pwdMd5!=null && !pwdMd5.equals("")){
    			if(!oldPwd.equals(pwdMd5)){
    				isRight = "error";
    			}else{
    				if(newPwdMd5!=null && !newPwdMd5.equals("")){
    	    			int userId = user.getUserId();
    	    			user.setPassword(newPwdMd5); 
    					user.setUserId(userId);
    	    			studentcenterService.updateUser(user);
    	    			isRight = "right";
    	    		}
    			}
        	}
    			
    		response.getWriter().append(isRight);
    }
    
    /**
     * 
     * 功能描述：上传头像
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年3月26日 下午2:09:41</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/uploadImg/")
    public String uploadImage(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response)throws LoginException{
    	UserVo userVo=SiteUtils.getUser(request);
    	int intStudentId = userVo.getUserId();
    	int result=0;
    	MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
    	MultipartFile multipartFile=multipartRequest.getFile("imagefile");

    	if (!StringUtils.isBlank(multipartFile.getOriginalFilename())) {
    		String stuImgRoot = MessageResolver.getMessage(multipartRequest, "stuImgRoot", null);
    		String imageRoot = MessageResolver.getMessage(multipartRequest, "imageRoot", null);
    		String imageServerIp = MessageResolver.getMessage(multipartRequest, "imageServerIp", null);
    		String imageServerName = MessageResolver.getMessage(multipartRequest, "imageServerName", null);
    		String imageServerPassword = MessageResolver.getMessage(multipartRequest, "imageServerPassword", null);
    		String filePath = "smb://"+imageServerIp+imageRoot+stuImgRoot+ intStudentId+"/";
    		String fileName = UploadUtils.generateFilename("", FileUtils.getFileType(multipartFile.getOriginalFilename()));
			try {
				fileRepository.storeByRmoeteFilename(imageServerIp, imageServerName, imageServerPassword, filePath.trim(),
						 fileName, multipartFile);
				fileName=stuImgRoot+intStudentId+"/"+fileName;
				modelMap.put("userHeadPic", fileName);
				result=1;  //上传成功
			} catch (IOException e) {
				logger.error(e); e.printStackTrace();
			}
		}
    	modelMap.put("result", result);
    	return "stucenter/myinfo/uOk.htm";
    }
    
     /**
      * 
      * 功能描述：更新用户头像信息
      *
      * @author  biyun.huang
      * <p>创建日期 ：2014年3月27日 下午1:24:46</p>
      *
      * @param modelMap
      * @param request
      * @param response
      * @throws LoginException
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
     @RequestMapping(value="/studentCenter/updateImg/",method=RequestMethod.POST)
     public void  updateImg(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException{
    	  String messageString="";
    	  boolean result=false;
    	  String image = request.getParameter("imageUrl");
    	  UserVo userVo = SiteUtils.getUser(request);
    	  
			if (!StringUtils.isBlank(image)) {
				try {
					userVo.setUserHeadPic(image);
					userService.updateUser_HeadPic(userVo);
					SiteUtils.setUser(request, userVo);
					messageString="保存成功!";
					result=true;
				} catch (BaseException e) {
					messageString="图片保存失败，请重新上传!";
					logger.error(e); e.printStackTrace();
				}
			}else{
				messageString="请选择上传图片!";
			}
		JSONObject json=new JSONObject();
		json.put("result", result);
		json.put("messageString", messageString);
		json.put("userHeadPic", userVo.getUserHeadPic());
		ResponseUtils.renderJson(response, json.toString());
     }
     
     /**
      * 
      * 功能描述：头像显示
      *
      * @author  yangyang.ou
      * <p>创建日期 ：2014-2-18 下午2:17:24</p>
      *
      * @param request
      * @param response
      * @param modelMap
      * @throws LoginException
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
     @RequestMapping(value="/studentCenter/getStuImg/",method=RequestMethod.POST)
     public void getStuImg(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws LoginException
     {
    	 UserVo user = new UserVo();
    	 try{
    		 	UserVo uservo = SiteUtils.getUser(request);
    		 	user = userService.getUserById(uservo.getUserId());
    	 	} catch (NumberFormatException e) {
   		  		logger.error(e); e.printStackTrace();
	   	  	} catch (BaseException e) {
	   	  		logger.error(e); e.printStackTrace();
	   	  	}
    	String flag = "error";
    	if(user!=null){
    		flag = "right";
    	}
    	modelMap.put("flag", flag);
    	modelMap.put("userHeadPic", user.getUserHeadPic());
    	JSONObject json = new JSONObject();
    	json.put("flag", flag);
    	json.put("userHeadPic", user.getUserHeadPic());
    	ResponseUtils.renderJson(response, json.toString());
     }
     
    /**
     * 
     * 功能描述：【--我的作业--】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-17 上午10:46:25</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/mytask/")
    public String mytask(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user =SiteUtils.getUser(request);
    	PageModel pageModel =  null;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	int pageSize = 5;
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	try {
			//获取多个课程下和对应的课程作业（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
			int examType = 3;
			pageModel = courseService.getCourseExamInfoList(user.getUserId(),examType,Integer.parseInt(pageNo), pageSize);
			 
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	modelMap.put("pageModel", pageModel==null?new PageModel():pageModel);
    	return "stucenter/mytask.htm";
    }
    
    
    /**
     * 
     * 功能描述：【--我的练习--】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-20 上午9:58:39</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/mypractice/")
    public String  mypractice(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	PageModel pageModel =  null;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	int pageSize = 5;
    	try {
			 
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			//获取多个课程下和对应的课程作业（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
			int examType = 2;
			pageModel = courseService.getCourseExamInfoList(user.getUserId(),examType,Integer.parseInt(pageNo), pageSize);
			modelMap.put("pageModel", pageModel==null?new PageModel():pageModel);
			 
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	
    	return "stucenter/mypractice.htm";
    }
    
    /**
	 * 
	 * 功能描述：【--我的笔记--】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-17 上午10:47:09</p>
	 *
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @throws LoginException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/studentCenter/mynote/")
	public String mynote(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
	
		UserVo user = SiteUtils.getUser(request);
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		int pageSize = 5;
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		PageModel pageModel =  null;
		try {
			//我的笔记
			pageModel = courseService.getCourseInfoList(user.getUserId(),Integer.parseInt(pageNo), pageSize);
			 
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		
		modelMap.put("user", user);
		modelMap.put("pageModel", pageModel);
		return "stucenter/mynote.htm";
	}


	/**
     * 
     * 功能描述：【--岗位收藏--】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-17 上午10:46:43</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/jobcollect/")
    public String  myjobcollect(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	UserVo user = SiteUtils.getUser(request);
    	PageModel pageModel = null;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	List list = new ArrayList();
    	if(StringUtils.isBlank(pageNo)){
    		pageNo = "1";
    	}
    	try {
    		JobVo jobVo = new JobVo();
    		jobVo.setJobPID(1);
    		int pageSize = 5;
    		//collectType 1代表岗位收藏
    		int collectType = 1;
    		list = myCollectService.getRefIdList(user.getUserId(),collectType);
    		if(list!=null&&list.size()>0){
    			pageModel = jobService.getJobNameList(list, Integer.parseInt(pageNo), pageSize);
    		}
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	
    	modelMap.put("jobList", pageModel==null?new PageModel():pageModel);
    	modelMap.put("user", user);
    	return "stucenter/mycollect/morejobcollect.htm";
    }
    
    
    /**
     * 
     * 功能描述：取消岗位收藏
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-3-20 下午2:02:10</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/cancelJobSelect/")
    public void cancelJobSelect(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	UserVo user = SiteUtils.getUser(request);
    	
    	String jobID = RequestUtils.getQueryParam(request, "jobID");
    	boolean result = false;
    	int collType = 1;
    	int userId = user.getUserId();
    	if(!StringUtils.isBlank(jobID)){
    		int courId = Integer.parseInt(jobID);
    		try{
    			//根据用户id，岗位id,收藏类型(1、岗位收藏 2、课程收藏)，删除收藏的课程
    			myCollectService.delMyCollect(userId,courId,collType);
    			result = true;
        	}catch(BaseException e) {
    			logger.error(e); e.printStackTrace();
    		}
    	}
    	
    	JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("jobID", jobID);
		ResponseUtils.renderJson(response, jsonObject.toString());
    	
    }
    
    

    /**
     * 
     * 功能描述：【--课程收藏--】
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-1-17 上午10:46:58</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/coursecollect/")
    public String  courseCollect(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	PageModel pageModel = null;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	if(StringUtils.isBlank(pageNo)){
    		pageNo = "1";
    	}
    	int collectType = 2;
    	int pageSize = 15;
    	try{
    	    //根据用户id查询岗位id集合，此处参数传用户id
    		pageModel = courseService.courseCollectList(user.getUserId(),collectType,Integer.parseInt(pageNo),pageSize);
    	}catch(BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	modelMap.put("courseList", pageModel==null?new PageModel():pageModel);
    	return "stucenter/mycollect/coursecollect.htm";
    }
    
    /**
     * 
     * 功能描述：课程收藏，列表数据删除
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-2-12 下午2:42:48</p>
     *
     * @param request
     * @param response
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/studentCenter/courseDelete/")
    public void deleteCourse(HttpServletRequest request,HttpServletResponse response) throws LoginException {
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	String result = "error";
    	int collType = 2;
    	UserVo user = SiteUtils.getUser(request);
    	int userId = user.getUserId();
    	if(!StringUtils.isBlank(courseId)){
    		int courId = Integer.parseInt(courseId);
    		try{
    			//根据用户id，课程id,收藏类型(1、岗位收藏 2、课程收藏)，删除收藏的课程
    			myCollectService.delMyCollect(userId,courId,collType);
    			result = "right";
        	}catch(BaseException e) {
    			logger.error(e); e.printStackTrace();
    		}
    	}
    	
    	JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("cour", courseId);
		ResponseUtils.renderJson(response, jsonObject.toString());
    }

    /**
     * 
     * 功能描述：【--我的考试--】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-20 上午10:14:35</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/myexam/")
    public String myexam(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	PageModel pageModel =  null;
    	int pageSize = 15;
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	try {
    		int examType = 1;
			pageModel = courseService.getUserCourseList(user.getUserId(), examType,Integer.parseInt(pageNo), pageSize);
			
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

    	modelMap.put("pageModel", pageModel==null?new PageModel():pageModel);
    	return "stucenter/myexam.htm";
    }
    
    
    /**
     * 
     * 功能描述：【--我的课程提问--】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-17 上午10:47:16</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/mycoursequestion/")
    public String mycoursequestion(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	int userId = user.getUserId();
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	int pageSize = 5;
    	int totleSize=0;
    	String hasNext="false";
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	//类型 1.关注的问题 2.最新动态
    	String type = RequestUtils.getQueryParam(request, "type");
		List<QuestionVo> courseQuesList = new ArrayList<QuestionVo>();
		List<?> quesIdlist = new ArrayList<Object>();
		int answerQuesAcount = 0;
    	try {
    		
	   		if(type.equals("1")){ //【-所有问题-】
	   			//根据用户id查询用户提的问题id集合
	   			quesIdlist = questionCenterService.getQuesIdListByUserId(userId,1);
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			totleSize=quesIdlist.size();
		   			courseQuesList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,2);
		   		}
	   		}else if(type.equals("2")){ //【-最新收到的回答-】
	   			List<?> idlist = questionCenterService.getQuesIdListByUserId(userId,1);
	   			if(idlist!=null&&idlist.size()>0){
	   				quesIdlist = questionCenterService.getHasAnswerQuestionIdsList(idlist);
	   			}
	   			
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			
		   			courseQuesList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,0);
		   			totleSize=courseQuesList.size();
		   		}
	   		}
	   		
	   		
	   		//判断下一页是否有数据
	   		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
	   			hasNext="true";
	   		}
	   		
	    	int answIsRead = 0; //0表示未读的回答
	    	if(quesIdlist!=null&&quesIdlist.size()>0){
	    		//课程提问未读的条数
	    		answerQuesAcount = questionCenterService.getHasAnswerQuestionCount(quesIdlist, answIsRead);
	    	}
			 
	    	if(type.equals("2")){
	    		if (quesIdlist!=null&&quesIdlist.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAnswIsReadByquesId(quesIdlist);
	    		} 
	    	}
	    	
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    
    	 modelMap.put("type", type);
    	 modelMap.put("answerQuesAcount", answerQuesAcount);
    	 modelMap.put("courseQuesList", courseQuesList);  
    	 modelMap.put("hasNext",hasNext);
    	return "stucenter/myquestion/coursequestion.htm";
    }
    
    
    
    /**
    /**
     * 
     * 功能描述：获取更多课程提问
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-22 下午1:22:34</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  * @throws BaseException 
     */
     @RequestMapping("/studentCenter/getMoreCourserQuestion.ajax")
     public void getMoreCourserQuestion(HttpServletRequest request,HttpServletResponse response) throws BaseException {
    	
 		UserVo user = SiteUtils.getUser(request);
 		
 		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
 		String tagFlag = RequestUtils.getQueryParam(request, "tagFlag");
      	int pageSize = 5;
      	int totleSize=0;
    	String hasNext="false";
    	
      	if (StringUtils.isBlank(pageNo)) {
  			pageNo = "1";
  		}
      	
      	 List<QuestionVo> courseQuesList = new ArrayList<QuestionVo>();
      	List<?> quesIdlist = new ArrayList<Object>();
  		 String text = "";
     	 
  		 
  		if(tagFlag.equals("1")){ //【-所有问题-】
   			//根据用户id查询用户提的问题id集合
   			quesIdlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),1);
   			if(quesIdlist!=null&&quesIdlist.size()>0){
	   			totleSize=quesIdlist.size();
	   			courseQuesList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
	   		}
   		}else if(tagFlag.equals("2")){ //【-最新收到的回答-】
   			List<?> idlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),1);
   			if(idlist!=null&&idlist.size()>0){
   				quesIdlist = questionCenterService.getHasAnswerQuestionIdsList(idlist);
   			}
   			
   			if(quesIdlist!=null&&quesIdlist.size()>0){
	   			
	   			courseQuesList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,0);
	   			totleSize=courseQuesList.size();
	   		}
   		}
  		
//  		//quesType 0.答疑提问 1.课程提问
//		 int quesType = 1; 
//		 //用户下的课程提问的列表
//		 List<?> quesIdlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),quesType);
//		 if(quesIdlist!=null&&quesIdlist.size()>0){
//			 totleSize=quesIdlist.size();
//		     courseQuesList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
//		 }
 		
			//判断下一页是否有数据
	   		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
	   			hasNext="true";
	   		}
	   		
	   		if(tagFlag.equals("2")){
	    		if (quesIdlist!=null&&quesIdlist.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAnswIsReadByquesId(quesIdlist);
	    		} 
	    	}
	   		
		 if (null!=courseQuesList && courseQuesList.size()>0) {
 				try {
 					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/stucenter/myquestion/coursequestionPage.htm");
 					ModelMap map = new ModelMap();
 					map.put("courseQuesList", courseQuesList);
 					map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
 					map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
 					map.put("hasNext", hasNext);
 				    text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
 				} catch (IOException e) {
 					logger.error(e); e.printStackTrace();
 				} catch (TemplateException e) {
 					logger.error(e); e.printStackTrace();
 				}
 			}
 			
 		ResponseUtils.renderText(response, text);
 	}
     
     
    /**
     * 
     * 功能描述：我的答疑提问
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-17 上午10:47:16</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/myanswerquestion/")
    public String myanswerquestion(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	int userId = user.getUserId();
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	int pageSize = 5;
    	int totleSize=0;  //记录问题总数
    	String hasNext="false";
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	//类型 1.答疑问题 2.最新动态
    	String type = RequestUtils.getQueryParam(request, "type");
		List<QuestionVo> answerQuestionnList = new ArrayList<QuestionVo>();
		List<?> quesIdlist = new ArrayList<Object>();
		int quesAnswerCount = 0;
    	try {
    		
	   		if(type.equals("1")){ //【-所有问题-】
	   			//根据用户id查询用户提的问题id集合
	   			quesIdlist = questionCenterService.getQuesIdListByUserId(userId,0);
	   			
	   			if(quesIdlist!=null&&quesIdlist.size()>0){//获取提问与提问的最新回答

		   			totleSize=quesIdlist.size();
		   			//最新回答：包括追问的回答在内的最新回答（按时间）
		   			answerQuestionnList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,2);
		   		}
	   		}else if(type.equals("2")){ //【-最新收到的回答-】
	   			List<?> idlist = questionCenterService.getQuesIdListByUserId(userId,0);//获取用户所有提问
	   			if(idlist!=null&&idlist.size()>0){
	   				quesIdlist = questionCenterService.getHasAnswerQuestionIdsList(idlist);//获取用户有回答的提问
	   			}
	   			
	   			if(quesIdlist!=null&&quesIdlist.size()>0){//获取提问与提问的最新回答
		   			
		   			//最新回答问题：包括追问的回答在内的最新的未读的回答（按时间）
	   				answerQuestionnList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,0);
	   				
		   			totleSize=answerQuestionnList.size();
		   		}
	   		}
	   		
	   		//判断下一页是否有数据
	   		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
	   			hasNext="true";
	   		}
	   		
	   		int answIsRead = 0;
	    	//答疑提问未读回答的条数
	    	if(quesIdlist!=null&&quesIdlist.size()>0){
	    		quesAnswerCount = questionCenterService.getHasAnswerQuestionCount(quesIdlist, answIsRead);
	    	}
	    	
	    	if(type.equals("2")){
	    		if (quesIdlist!=null&&quesIdlist.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAnswIsReadByquesId(quesIdlist);
	    		} 
	    	}
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
    	 modelMap.put("type", type);
    	 modelMap.put("quesAnswerCount", quesAnswerCount);
		 modelMap.put("answerQuestionnList", answerQuestionnList);
		 modelMap.put("hasNext", hasNext);
    	return "stucenter/myquestion/answerquestion.htm";
    }
    
    
    /**
    /**
     * 
     * 功能描述：获取更多答疑提问
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-22 下午1:22:34</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  * @throws BaseException 
     */
     @RequestMapping("/studentCenter/getMoreAnswerQuestion.ajax")
     public void getMoreAnswerQuestion(HttpServletRequest request,HttpServletResponse response) throws BaseException {
     	
     	UserVo user = SiteUtils.getUser(request);
     	
     	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
     	String tagFlag = RequestUtils.getQueryParam(request, "tagFlag");
     	
     	int pageSize = 5;
     	int totleSize=0;
     	String hasNext="false";
     	if (StringUtils.isBlank(pageNo)) {
 			pageNo = "1";
 		}
 		String text = "";
 		List<?> answerQuestionnList = new ArrayList<QuestionVo>();
 		List<?> quesIdlist = new ArrayList<Object>();
 		if(tagFlag.equals("1")){ //【-所有问题-】
   			//根据用户id查询用户提的问题id集合
   			quesIdlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),0);
   			
   			if(quesIdlist!=null&&quesIdlist.size()>0){//获取提问与提问的最新回答

	   			totleSize=quesIdlist.size();
	   			//最新回答：包括追问的回答在内的最新回答（按时间）
	   			answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
	   		}
   		}else if(tagFlag.equals("2")){ //【-最新收到的回答-】
   			List<?> idlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),0);//获取用户所有提问
   			if(idlist!=null&&idlist.size()>0){
   				quesIdlist = questionCenterService.getHasAnswerQuestionIdsList(idlist);//获取用户有回答的提问
   			}
   			
   			if(quesIdlist!=null&&quesIdlist.size()>0){//获取提问与提问的最新回答
	   			
	   			//最新回答问题：包括追问的回答在内的最新的未读的回答（按时间）
   				answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,0);
   				
	   			totleSize=answerQuestionnList.size();
	   		}
   		}
 		
// 		List<?> quesIdlist = questionCenterService.getQuesIdListByUserId(user.getUserId(),0);
// 		if(quesIdlist!=null&&quesIdlist.size()>0){
//	   		answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
//	   		totleSize=quesIdlist.size();
//	   	}
     	
 		//下一页是否还有数据
 		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
   			hasNext="true";
   		}
    	
    	if(tagFlag.equals("2")){
    		if (quesIdlist!=null&&quesIdlist.size()>0) {
    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
 			   questionCenterService.updateAnswIsReadByquesId(quesIdlist);
    		} 
    	}
    	
    	
 		if (null!=answerQuestionnList && answerQuestionnList.size()>0) {
 				try {
 					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/stucenter/myquestion/answerquestionPage.htm");
 					ModelMap map = new ModelMap();
 					map.put("answerQuestionnList", answerQuestionnList);
 					map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
 					map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
 					map.put("hasNext", hasNext);
 				    text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
 				} catch (IOException e) {
 					logger.error(e); e.printStackTrace();
 				} catch (TemplateException e) {
 					logger.error(e); e.printStackTrace();
 				}
 			}
 			
 		ResponseUtils.renderText(response, text);
 	}
     
    
    
    
    
    /**
     * 
     * 功能描述：收到的回答标记为已读
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-22 下午1:22:34</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  * @throws BaseException 
     * @throws IOException 
     */
     @RequestMapping("/studentCenter/hasReadAnswer.ajax")
     public void hasReadAnswer(HttpServletRequest request,HttpServletResponse response) throws BaseException, IOException {
 	
     try {
    	 UserVo user = SiteUtils.getUser(request);
    	 String quesType = RequestUtils.getQueryParam(request, "quesType");
    	 if (!StringUtils.isBlank(quesType)) {
     	     //查询答疑提问问题id集合
	     	 List<?> quesAnswerList = questionCenterService.getQuesIdListByUserId(user.getUserId(),Integer.valueOf(quesType));
	    	 if (quesAnswerList!=null&&quesAnswerList.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAnswIsReadByquesId(quesAnswerList);
	    	  } 
    	 }
        } catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
     
     }
    

    
    /**
     * 
     * 功能描述：我的关注
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-21 下午3:51:13</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/myattention/")
    public String myattention(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

    	UserVo user = SiteUtils.getUser(request);
    	int userId = user.getUserId();
    	//类型 1.关注的问题 2.最新动态
    	String type = RequestUtils.getQueryParam(request, "type");
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	int totleSize=0;
    	String hasNext="false";
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	int pageSize = 5;
   	    //答疑提问列表 
		List<QuestionVo> answerQuestionnList = new ArrayList<QuestionVo>();
		List<?> quesIdlist = new ArrayList<Object>();
		int attentionCount = 0;
	   	try {
	   		
	   		if(type.equals("1")){ //关注的问题
	   			quesIdlist = questionCenterService.getAttentionQuesIdListByUserId(userId);
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			totleSize=quesIdlist.size();
		   		  answerQuestionnList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,2);
		   		}
	   		}else if(type.equals("2")){ //最新动态
	   			List<?> idlist = questionCenterService.getAttentionQuesIdListByUserId(userId);
	   			quesIdlist = questionCenterService.getAttentionHasAnswerQuestionIdsList(userId,idlist);
	   			
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			
	   				answerQuestionnList = questionCenterService.getQuestionListByIds(userId,quesIdlist,Integer.valueOf(pageNo),pageSize,0);
	   				totleSize=answerQuestionnList.size();
		   		}
	   		}
	   		
			
	   		//下一页是否还有数据
	 		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
	   			hasNext="true";
	   		}
	 		
	    	int answIsRead = 0; //0表示未读的回答
	    	if(quesIdlist!=null&&quesIdlist.size()>0){
	    		//关注提问未读的条数
	    		attentionCount = questionCenterService.getAttentionHasAnswerQuestionIdsCount(userId,quesIdlist, answIsRead);
	    	}
	    	
	    	
	    	if(type.equals("2")){
	    		if (quesIdlist!=null&&quesIdlist.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAttentionAnswIsReadByquesId(userId,quesIdlist);
	    		} 
	    	}
	    	
	    	
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
	     modelMap.put("type", type);
		 modelMap.put("answerQuestionnList", answerQuestionnList);
		 modelMap.put("attentionCount", attentionCount);
		 modelMap.put("hasNext", hasNext);
    	return "stucenter/myattention/attention.htm";
    }
    
    
    /**
     * 
     * 功能描述：获取更多我的关注
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-21 下午3:51:13</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/studentCenter/getMoreMyattention.ajax")
    public void getMoreMyattention(HttpServletRequest request,HttpServletResponse response) throws BaseException {
    	
    	UserVo user = SiteUtils.getUser(request);
    	
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");
    	String tagFlag = RequestUtils.getQueryParam(request, "tagFlag");
    	int pageSize = 5;
    	int totleSize=0;
    	String hasNext="false";
    	
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		 String text = "";
		 List<QuestionVo> answerQuestionnList = new ArrayList<QuestionVo>();
		 List<?> quesIdlist = new ArrayList<Object>();
		 
		 if(tagFlag.equals("1")){ //关注的问题
	   			quesIdlist = questionCenterService.getAttentionQuesIdListByUserId(user.getUserId());
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			totleSize=quesIdlist.size();
		   		  answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
		   		}
	   		}else if(tagFlag.equals("2")){ //最新动态
	   			List<?> idlist = questionCenterService.getAttentionQuesIdListByUserId(user.getUserId());
	   			quesIdlist = questionCenterService.getAttentionHasAnswerQuestionIdsList(user.getUserId(),idlist);
	   			
	   			if(quesIdlist!=null&&quesIdlist.size()>0){
		   			
	   				answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,0);
	   				totleSize=answerQuestionnList.size();
		   		}
	   		}
	   		
//   		 List<?> quesIdlist = questionCenterService.getAttentionQuesIdListByUserId(user.getUserId());
//   		 totleSize=quesIdlist.size();
//		 List answerQuestionnList = questionCenterService.getQuestionListByIds(user.getUserId(),quesIdlist,Integer.valueOf(pageNo),pageSize,2);
		
		//下一页是否还有数据
	 		if((totleSize-Integer.parseInt(pageNo)*pageSize)>0){
	   			hasNext="true";
	   		}
	 		
	 		if(tagFlag.equals("2")){
	    		if (quesIdlist!=null&&quesIdlist.size()>0) {
	    		   //根据问题ID，更新答案为已读状态,返回已读的回答数
	 			   questionCenterService.updateAttentionAnswIsReadByquesId(user.getUserId(),quesIdlist);
	    		} 
	    	}
		if (null!=answerQuestionnList && answerQuestionnList.size()>0) {
				try {
					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/stucenter/myattention/attentionPage.htm");
					ModelMap map = new ModelMap();
					map.put("answerQuestionnList", answerQuestionnList);
					map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
					map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
					map.put("hasNext", hasNext);
				    text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
				} catch (IOException e) {
					logger.error(e); e.printStackTrace();
				} catch (TemplateException e) {
					logger.error(e); e.printStackTrace();
				}
			}
			
		ResponseUtils.renderText(response, text);
	}
    
    
    @Autowired
    private StudentcenterService studentcenterService;
    @Autowired
    private TeacherService teaService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private JobService jobService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MyCollectService myCollectService;
    @Autowired
    private QuestionCenterService questionCenterService;
    @Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    private  ExamService examService;
}
