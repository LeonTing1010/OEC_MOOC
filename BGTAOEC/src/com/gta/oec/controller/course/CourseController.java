/**
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.course;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.common.web.utils.TimeUtils;
import com.gta.oec.enums.CourseStateEnum;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseCommentService;
import com.gta.oec.service.course.LearnService;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.mycollect.MyCollectService;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.school.SchoolService;
import com.gta.oec.service.teacher.TeacherService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.gta.oec.vo.exam.ExamQuestionVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.learn.LearnVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.resource.ResourceVo;
import com.gta.oec.vo.school.SchoolVo;
import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.trade.TradeVo;
import com.gta.oec.vo.user.UserVo;

@Controller
public class CourseController extends BaseCourseController {
	private static final Log logger = LogFactory.getLog(CourseController.class);

	/**
	 * 
	 * 功能描述：课程主页（包含课程相关列表）
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-4 下午2:11:30</p>
	 *
	 * @param courseId
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
    @RequestMapping("/course/main/{courseId}/")
	public String  courseIndex(@PathVariable String courseId,ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, BaseException {  	
    	checkCourse(Integer.parseInt(courseId), request);
        UserVo userVo = SiteUtils.getUser(request);
        String tab = RequestUtils.getQueryParam(request, "tab");
        String examId = RequestUtils.getQueryParam(request, "examId");
        String examType=RequestUtils.getQueryParam(request, "examType");
         if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
        	 try {
        	  //给用户与课程的绑定
        	  courseService.addUserCourse(Integer.parseInt(courseId), userVo.getUserId());
        		
        	  //获取课程信息，包含学习信息
			  CourseVo courseVo = courseService.getUserCourseLearnDetail(Integer.parseInt(courseId), userVo.getUserId());
			  modelMap.put("course", courseVo);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
        if(!StringUtils.isBlank(examId)){
        	ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(userVo.getUserId(), Integer.valueOf(examId));
        	if(examStudentVo!=null){
        		 modelMap.put("status", 1);
        	}else{
        		 modelMap.put("status", 2);
        	}
        }
        try {
           LearnVo learnVo = new LearnVo();
           learnVo.setCourseId(Integer.parseInt(courseId));
           learnVo.setUserId(userVo.getUserId());
     	   modelMap.put("isLearn", learnService.getUserCourseLearnCount(learnVo));
		 
	    	}catch ( BaseException e) {
	    		logger.error(e); e.printStackTrace();
			}
        modelMap.put("tab", tab);
        modelMap.put("examId", examId);
        modelMap.put("examType", examType);
		return "course/coursemain.htm";
	} 
    /**
     * 
     * 功能描述：获取课程信息
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-13 下午2:31:11</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws LoginException 
     */
    @RequestMapping("/course/courseInfo/")
    public String courseInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException {
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	checkCourse(Integer.parseInt(courseId), request);
    	try {
    		UserVo userVo =SiteUtils.getUser(request);
		
	    	 if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
				  CourseVo courseVo;
				try {
					courseVo = courseService.getUserCourseLearnDetail(Integer.parseInt(courseId), userVo.getUserId());
					modelMap.put("course", courseVo);
				} catch (NumberFormatException e) {
					logger.error(e); e.printStackTrace();
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
			}
		} catch (LoginException e) {
			logger.error(e);
			throw e;
		}
    	 	
    	return "course/courseinfo.htm";
    }
    /**
     * 
     * 功能描述：获取课程大纲
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-13 下午2:31:11</p>
     *
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws LoginException 
     */
    @RequestMapping("/course/courseOutline/")
    public String courseOutline(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException{
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	checkCourse(Integer.parseInt(courseId), request);
    	try {
			SiteUtils.getUser(request);
	    	 if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
				  CourseVo courseVo;
				try {
					courseVo = courseService.getCourseById(Integer.parseInt(courseId));
					modelMap.put("course", courseVo);
				} catch (NumberFormatException e) {
					logger.error(e); e.printStackTrace();
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
				
			}
		} catch (LoginException e1) {
			logger.error(e1);
			throw e1;
		}
    	return "course/courseoutline.htm";
    }
   
    /**
     * 
     * 功能描述：获取课程评分标准
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年1月13日 下午3:40:20</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws LoginException 
     */
    @RequestMapping("/course/courseCriterion/")
    public String courseCriterion(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException{
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	checkCourse(Integer.parseInt(courseId), request);
    	try {
			SiteUtils.getUser(request);
			
	    	 if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
				  CourseVo courseVo;
				try {
					courseVo = courseService.getCourseInfoById(Integer.parseInt(courseId));
					modelMap.put("course", courseVo);
				} catch (NumberFormatException e) {				
					logger.error(e); e.printStackTrace();
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}

			}
		} catch (LoginException e1) {
			throw e1;
		}
    	return "course/criterion.htm";
    }
    
    /**
     * 
     * 功能描述：获取课程笔记
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年1月13日 下午4:22:55</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws LoginException 
     */
    @RequestMapping("/course/courseNotes/")
    public String courseNotes(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
    {	
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	checkCourse(Integer.parseInt(courseId), request);
    	try {
    		UserVo userVo=SiteUtils.getUser(request);	
		
	    	 if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
				  CourseVo courseVo;
				try {
					courseVo = courseService.getUserCourseNotes(Integer.parseInt(courseId),userVo.getUserId());
					modelMap.put("course", courseVo);
				} catch (NumberFormatException e) {
					logger.error(e); e.printStackTrace();
				} catch (LoginException e) {
					logger.error(e); e.printStackTrace();
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}

			}
		} catch (LoginException e1) {
			logger.error(e1);
			throw e1;
		}
    	return "course/coursenotes.htm";
    }
    
    /**
     * 
     * 功能描述：课程详情页面
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-1-9 下午6:04:25</p>
     *
     * @param courseId
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/info/{courseId}/")
  	public String  courseinfo(@PathVariable String courseId,ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws BaseException {  	
    
    	Integer courId = Integer.parseInt(courseId);
    	// Integer 
    	checkCourse(Integer.parseInt(courseId), request);
    	
    	if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
    		 try {
    			 
    	        	//根据课程id查询课程
    	        	CourseVo course = courseService.getCourseById(courId);
    	        	if (null!= course) {
    	        		// 课程被浏览时   更改课程浏览次数
    	        		courseService.updateByBrowseTimes(courId,course.getCourseAttention());   
    	        		// 课程浏览次数 (包含当前这次)
    	            	modelMap.put("courseAttention", course.getCourseAttention() + 1);
    	            	
    	        		modelMap.put("course", course);
    	            	//查询该门课程的人数
    	            //	int userCourseCount =courseService.getUserCourseCount(courId);
    	            	//查询学习该门课程的所有用户id集合
    	            	List<UserVo> userList = courseService.getCourseUser(courId,1,11);
    	            	//根据用户的id集合查对于的用户对象集合
    	            
    	            	//对于课程的老师ID
    	            	int userId = course.getUserId();
    	            	//UserVo user = userService.getUserById(userId);
    	          
    	            	TeacherVo teacher = teacherService.getTeacherByUserId(userId);
    	            	SchoolVo school = schoolService.getSchoolByUserId(userId);
    	            	
    	            	//精选问答
    	            	List<QuestionVo> queslist = questionCenterService.getChosenQuestions(3);
    	            	//该课程所属岗位下的  其他相关推荐课程
    	            	List<CourseJobVo> courseJobList = new ArrayList<CourseJobVo>();
    	            	// 得到课程相关行业名称
    	            	List<CourseJobVo> courseJobVoList=jobService.getCourseJobBycourseId(courId);
    	            	String proName=null;
    	            	if(null!=courseJobVoList && courseJobVoList.size()>0){
    	            		CourseJobVo courseJobVo=courseJobVoList.get(0);
    	            		proName=courseJobVo.getProName();
        	            	courseJobList = jobService.getRelativityCourseListByJobId(courseJobVo.getJobID(),courseJobVo.getCourseID());  
    	            	}
    	            	
    	            	
    	            //	modelMap.put("userVo", user);
    	            	if(teacher==null){
    	            		teacher = new TeacherVo();
    	            	}
    	            	modelMap.put("teacher", teacher);
    	            	modelMap.put("school", school);
    	            	modelMap.put("userList", userList);
    	            	modelMap.put("userCourseCount", userList.size());
    	            	modelMap.put("courseJobList", courseJobList);
    	            	modelMap.put("queslist", queslist);
    	            	modelMap.put("courseId", courId);
    	            	modelMap.put("proName", proName);
    				}
    	        	//1、岗位收藏 2、课程收藏
    	        	int collType = 2;
    	        	int courCount = courseService.checkCollCourse(Integer.parseInt(courseId), collType, 0);
    	        	boolean flag;
    	        	//UserVo userVo=SiteUtils.getUser(request);
    	        	
    	        	 UserVo userVo = (UserVo)SiteUtils.getUser(request);
    	        	if(userVo!=null){
    	        		int check = courseService.checkCollCourse(Integer.parseInt(courseId),collType,userVo.getUserId());
    	        		if(check!=0){
    	        			flag=true;
    	        		}else{
    	        			flag=false;
    	        		}
    	        		modelMap.put("flag", flag);
    	        	}else{
    	        		modelMap.put("flag", false);
    	        	}
    	        
    	           try {
    	        	   modelMap.put("isBuy", courseService.checkUserCourse(Integer.parseInt(courseId), SiteUtils.getUser(request).getUserId())?1:0);
				     } catch (LoginException e) {
				       logger.error(e); e.printStackTrace();
				       modelMap.put("isBuy",0);
			    	}catch ( BaseException e) {
						logger.error(e); e.printStackTrace();
					}
    	        	modelMap.put("courseCount", courCount);
    	        	
    			} catch (BaseException e) {
    				logger.error(e);
    			}

		}
    	
    	String vodServer = MessageResolver.getMessage(request, "vodServer", null);
    	String videoProtocol = MessageResolver.getMessage(request, "videoProtocol", null);
    	modelMap.put("videoProtocol", videoProtocol);
    	modelMap.put("vodServer", vodServer);
    	
    	//  首次查询时 该课程相关的评论 默认最近8条
    	PageModel courseCommentPageModel = courseCommentService.findCourseCommentCoursePageListByCourseId(courId,1,8); 
    	// 该课程下共有多少条评论
    	Integer courseCommentCount = courseCommentPageModel.getTotalItem();
    	
    	modelMap.put("courseId", courId);
    	modelMap.put("courseCommentCount",courseCommentCount);
    	modelMap.put("courseCommentPageModel", courseCommentPageModel);
    	
  		return "course/coursedetail.htm";
  	}
 
    /**
     * 
     * 功能描述：课程收藏
     *
     * @author  yangyang.ou
     * <p>创建日期 ：2014-2-18 下午5:01:43</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/course/collect/")
    public void collectCourse(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	String flag = "tourist";
    	int check = 0;
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	String type = RequestUtils.getQueryParam(request, "type");
    	int courCount = 0;
    	try{
			UserVo user = SiteUtils.getUser(request);
			
    		int collType=2;
        	
        	if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
        		try{
        				if(type!=null&&"1".equals(type)&&user!=null && !"0".equals(courseId)){
    		    			check = courseService.checkCollCourse(Integer.parseInt(courseId),collType,user.getUserId());
    		    			if(check==0){
    			    			courseService.collectCourse(Integer.parseInt(courseId),collType,user.getUserId());
    			    			courCount = courseService.checkCollCourse(Integer.parseInt(courseId), collType, 0);
    			    			flag = "right";
    		    			}else{
    		    				flag = "error";
    		    			}
        				}
        				if(type!=null&&"2".equals(type)&&user!=null && !"0".equals(courseId)){
        					myCollectService.delMyCollect(user.getUserId(),Integer.parseInt(courseId),collType);
        					courCount = courseService.checkCollCourse(Integer.parseInt(courseId), collType, 0);
       					 	flag="sure";
        				}
        		
        		} catch (BaseException e) {
    				logger.error(e); e.printStackTrace();
    			}
        	}
		}catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
    	//课程收藏
    
    	JSONObject json = new JSONObject();
    	json.put("flag", flag);
    	json.put("collCount", courCount);
    	ResponseUtils.renderJson(response,json.toString());
    }
    
    /**
     * 
     * 功能描述：跳转课程分类页面
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-9 下午4:39:29</p>
     *
     * @param map
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/courselist/")
    public String toCourselist(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
    	try {
			List<TradeVo> tradeList = jobService.getTradeList(null,1,20);
			
	    	String jobId=RequestUtils.getQueryParam(request, "jobId");
	    	String proId =RequestUtils.getQueryParam(request, "tradeId");
	    	modelMap.put("proId", proId);
	    	modelMap.put("jobId", jobId);
			modelMap.put("tradeList", tradeList);
		} catch (BaseException e) {
			logger.error("get course info exception.", e);
		}
    	return "course/courselist.htm";
    }
    
    /**
     * 
     * 功能描述：根据岗位查找课程
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年1月8日 上午9:47:51</p>
     *
     * @param courseVo
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>	
     */
    @RequestMapping("/course/getCourseList/")
    public String getCourseVoList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){

    	String jobID=RequestUtils.getQueryParam(request, "jobId");//岗位id
    	String proId=RequestUtils.getQueryParam(request, "proId");//行业id
    	String jobGroupId = RequestUtils.getQueryParam(request, "jobGroupId");//岗位群id
    	String pageNo = RequestUtils.getQueryParam(request, "pageNo");//页数
    	if (StringUtils.isBlank(pageNo)) {
    		pageNo = "1";
		}
    	CourseVo queryVo = new CourseVo();
		queryVo.setStatus(CourseStateEnum.PUBLISHED.getValue());//设置课程状态为已发布课程
	    PageModel pageModel = null;
	    	try {
	    		//根据岗位id查询课程信息
	    		if(!StringUtils.isBlank(jobID)&&StrUtils.isNum(jobID))
	        	{
	    			pageModel = courseService.getCourseListByJob(queryVo, Integer.parseInt(jobID),Integer.parseInt(pageNo), 10);
	    			
	        	}
	    		//根据行业id查询课程信息
	    		if(!StringUtils.isBlank(proId)&&StrUtils.isNum(proId)){
	    			pageModel = courseService.queryCourseListByProId(queryVo, Integer.parseInt(proId), Integer.parseInt(pageNo), 10);
	    		}
	    		//根据岗位群id查询课程信息
	    		if(!StringUtils.isBlank(jobGroupId)&&StrUtils.isNum(jobGroupId)){

	    			pageModel = courseService.getCourListByJobGroupId(queryVo,Integer.parseInt(jobGroupId),Integer.parseInt(pageNo),10);
	    		}
	    		if (StringUtils.isBlank(jobID)&&StringUtils.isBlank(jobGroupId)&&StringUtils.isBlank(proId)) {
	    			pageModel = courseService.getCourseList(queryVo, Integer.parseInt(pageNo), 10);
				}
			} catch (BaseException e) {
				logger.error("get course info exception.", e);
			}
    	
     	modelMap.put("pageModel",  pageModel);	
		return "course/coursePage.htm";                              
    }

    /**
     * 
     * 功能描述：获取更多岗位信息
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-10 上午10:53:17</p>
     *
     * @param request
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/getJobPage/")
	public String getJobPage(HttpServletRequest request,ModelMap modelMap) {
		String proId = RequestUtils.getQueryParam(request, "proId");
		String proName = RequestUtils.getQueryParam(request, "proName");
		List<JobVo> list = null;
		if (!StringUtils.isBlank(proId)&&StrUtils.isNum(proId)) {
			try {
			 list = jobService.getJobGroupDetailByProid(Integer.parseInt(proId));
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		modelMap.put("jobList", list);
		modelMap.put("proName", proName);
		modelMap.put("proId", proId);
		return "course/jobpage.htm";
	}
    
    /**
     * 
     * 功能描述：获取课程章节辅助资源
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年1月15日 下午12:30:53</p>
     *
     * @param request
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/getResourceList/{courseID}/{secID}/")
    public String getResourceList(@PathVariable String courseID,@PathVariable String secID,HttpServletRequest request,ModelMap modelMap){
    	
//    	String courseID=RequestUtils.getQueryParam(request, "courseID");
//    	String secID=RequestUtils.getQueryParam(request, "secID");

    	 if (!StringUtils.isBlank(courseID)&&StrUtils.isNum(courseID)
    			 &&!StringUtils.isBlank(secID)&&StrUtils.isNum(secID)) {
    		 List<ResourceVo> resList=null;
			try {
				ResourceVo resourceVo = new ResourceVo();
				resourceVo.setCourseID(Integer.parseInt(courseID));
				resourceVo.setSecID(Integer.parseInt(secID));
				resList = courseService.getResourceList(resourceVo);
				modelMap.put("resList", resList);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
    	 }
    	 
    	 return "course/resource.htm";
    }
    
    /**
     * 
     * 功能描述：我的课程表页面
     *
     * @author  biyun.huang
     * <p>创建日期 ：2014年1月21日 上午10:44:41</p>
     *
     * @param request
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/myCourse/")
    public String toMyClass(HttpServletRequest request,ModelMap modelMap)throws LoginException{
    	
    	List<CourseVo> courList;   //直播课程 
    	List<CourseVo> courRecommend;   //推荐课程
    	CourseVo courseVo=new CourseVo();
    	UserVo user = SiteUtils.getUser(request);
    	try{
    		courseVo.setUserId(user.getUserId());
    		courList=courseService.getUserCourList(courseVo);
    		
    		/**计算距离课程开课时间**/
    		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String startTime,endTime;
    		long day,hour,min,diffTime;
    		Map<String, Long> map =new HashMap<String, Long>();
			for (CourseVo cour : courList) {
				if(cour.getProgress()>10){
					cour.setProgress(10);
				}else if(cour.getProgress()<0){
					cour.setProgress(0);
				}else{};
				Date date = new Date();
				startTime = simpleDateFormat.format(date);
				endTime = simpleDateFormat.format(cour.getCourStartTime()); // 得到直播课程开课时间
				map = TimeUtils.dateDiff(startTime, endTime,"yyyy-MM-dd HH:mm:ss");
				day = map.get("diffDays"); // 得到相差的天数
				hour = map.get("diffHours"); // 得到相差的小时数
				min = map.get("diffMins"); // 得到相差的分钟数
				diffTime = map.get("diffTime"); // 得到相差的秒
				cour.setDiffTime(diffTime);
				if (diffTime > 0) {
					if (day > 0) {
						int diffDay = new Long(day).intValue();
						if(new Long(hour).intValue()>12) diffDay=diffDay+1;
						cour.setDiffDay(diffDay);
					} else {
						int diffHour = new Long(hour).intValue();
						int diffMin = new Long(min).intValue();
						cour.setDiffHour(diffHour);
						cour.setDiffMin(diffMin);
					}
				} 
			}
			courRecommend = courseService.getCourseRecommendList();
			modelMap.put("courRecommList", courRecommend);
			modelMap.put("courList", courList);
    	} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}    	
    	return "course/myclass.htm";
    }
    
    /**
     * 
     * 功能描述：跳转辅助资源页面
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-4-3 下午3:35:22</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/resource/")
    public String courseResource(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException {
    	String courseId = RequestUtils.getQueryParam(request, "courseId");
    	String sectionId = RequestUtils.getQueryParam(request, "sectionId");
    	String courseName =  RequestUtils.getQueryParam(request, "courseName");
		modelMap.put("courseName",courseName);	
    	checkCourse(Integer.parseInt(courseId), request);
    	SiteUtils.getUser(request);
    	 if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)
    			 &&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)) {
    		 List<ResourceVo> resList=null;
			try {
				ResourceVo resourceVo = new ResourceVo();
				resourceVo.setCourseID(Integer.parseInt(courseId));
				resourceVo.setSecID(Integer.parseInt(sectionId));
				resourceVo.setSourceType("2");
				resList = courseService.getResourceList(resourceVo);
				modelMap.put("resList", resList);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
    	 }
		return "course/resource/resource.htm";
	}
    
    /**
     * 
     * 功能描述：课程搜索
     *
     * @author  刘祚家
     * <p>创建日期 ：2014-02-14</p>
     *
     * @param request
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/search/")
    public String searchCourse(@ModelAttribute("form") SearchVo searchVo,HttpServletRequest request,ModelMap modelMap){
    	List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
    	List<TradeVo> tradeList =new ArrayList<TradeVo>();
    	List<CourseVo> courseVoList =new ArrayList<CourseVo>();
		
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");//页数
		if(pageNo==null){
			pageNo="1";
		}else{
			if(pageNo.equals("")){
				pageNo="1";
			}
		}
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");//每页条数
		if(pageSize==null){
			pageSize="10";
		}else{
			if(pageSize.equals("")){
				pageSize="10";
			}
		}
		  PageModel pageModel = null;
		//获取行业
		try {
			professionList=professionService.getProfessionList();
			tradeList = jobService.getTradeList(null,1,20);	
			
			pageModel = courseService.getSearchCourseList(searchVo,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
			logger.error("get QuestionList info exception.", e);
		}
		
		modelMap.put("pageModel", pageModel);
		modelMap.put("tradeList", tradeList);
		modelMap.put("professionList", professionList);
    	modelMap.put("searchVo", searchVo);
    	modelMap.put("searchText", searchVo.getSearchtext());
    	
    	modelMap.put("pageNo", pageNo);
    	modelMap.put("pageSize", pageSize);
    	
    	return "course/searchClass.htm";
    }
    
    /**
     * 
     * 功能描述：课程搜索-获取更多课程
     *
     * @author  刘祚家
     * <p>创建日期 ：2014-02-14</p>
     *
     * @param request
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/getSearchMoreCourse.ajax")
    public String getSearchMoreCourse(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
    	
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		String proId = RequestUtils.getQueryParam(request, "proId"); //0-表示全部 ，其他表示相应行业ID
		
		String searchtext = RequestUtils.getQueryParam(request, "searchtext");
		SearchVo searchVo =new SearchVo();
		searchVo.setSearchtext(searchtext);
		
//		String text = "";
//		List<CourseVo> courseVoList =new ArrayList<CourseVo>();
		PageModel pageModel = null;
		if (!StringUtils.isBlank(pageNo)) {
			//根据搜索条件，查询全部课程
			if ("0".equals(proId)) {
				try {
					pageModel = courseService.getSearchCourseList(searchVo,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
			}else{//根据根据搜索条件、行业，查询课程
		    	try {
		    		pageModel = courseService.getCourseListByProId(searchVo,Integer.parseInt(proId), Integer.parseInt(pageNo), Integer.parseInt(pageSize));
				} catch (BaseException e) {
					logger.error("get course info exception.", e);
				}
			}
			
//			if (null!=pageModel && pageModel.getList().size()>0) {
//				try {
//					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/course/searchClassPage.htm");
//					ModelMap map = new ModelMap();
//					map.put("pageModel", pageModel);
//					map.put("base", request.getContextPath());
//					map.put("searchText", searchVo.getSearchtext());
//					
//				    text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
//				} catch (IOException e) {
//					logger.error(e); e.printStackTrace();
//				} catch (TemplateException e) {
//					logger.error(e); e.printStackTrace();
//				}
//			}
			modelMap.put("pageModel", pageModel);
			modelMap.put("base", request.getContextPath());
			modelMap.put("searchText", searchVo.getSearchtext());
			
		}
//		ResponseUtils.renderText(response, text);
		
		return "course/searchClassPage.htm";
	}
    
    
    /**
     * 
     * 功能描述：课程作业列表页面
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-2-25 下午5:59:32</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/courseExamList/")
    public String courseExamList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
    {
    	UserVo user = SiteUtils.getUser(request);
    	String courseId = RequestUtils.getQueryParam(request, "courseId"); //课程id
    
    	checkCourse(Integer.parseInt(courseId), request);
    	List<ExamVo> examlist = new ArrayList<ExamVo>();
    	if (!StringUtils.isBlank(courseId)) {
    	   List<ExamVo> list = examService.getExamListByCourId(Integer.valueOf(courseId), 0, 3);
    	   
    	   for(ExamVo examVo:list){
    		   ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), examVo.getExamId());
    		   if(examStudentVo!=null){
    			   examVo.setPaperState(1);  //未批阅或者批阅（查看答案）
    		   }else{
    			   examVo.setPaperState(0); //未作答
    		   }
    		   examlist.add(examVo);
    	   }
    	 
    	   modelMap.put("examlist", examlist);
    	   modelMap.put("courseId", courseId);
    	   
    	}
    	
    	return "course/exam/courseExamList.htm";
    }
    
    
    /**
     * 
     * 功能描述：课程练习列表页面
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-3-11 上午11:04:41</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/coursePracticeList/")
    public String coursePracticeList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
    {
    	UserVo user = SiteUtils.getUser(request);
    	String courseId = RequestUtils.getQueryParam(request, "courseId"); //课程id
    	
    	checkCourse(Integer.parseInt(courseId), request);
    	List<ExamVo> examlist = new ArrayList<ExamVo>();
    	if (!StringUtils.isBlank(courseId)) {
    	   List<ExamVo> list = examService.getExamListByCourId(Integer.valueOf(courseId), 0, 2);
    	   
    	   for(ExamVo examVo:list){
    		   ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), examVo.getExamId());
    		   if(examStudentVo!=null){
    			   examVo.setPaperState(1);  //未批阅或者批阅（查看答案）
    		   }else{
    			   examVo.setPaperState(0); //未作答
    		   }
    		   examlist.add(examVo);
    	   }
    	 
    	   modelMap.put("examlist", examlist);
    	   modelMap.put("courseId", courseId);
    	   
    	}
    	
    	return "course/exam/coursePracticeList.htm";
    }
    
    
    /**
     * 
     * 功能描述：【-课程试题页面-】
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-2-19 下午6:01:36</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  * @throws BaseException 
     */
     @RequestMapping("/course/courseExamPage/")
     public String courseExamPage(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
     {
    	 SiteUtils.getUser(request);
    	 try {
 			String examIdStr = RequestUtils.getQueryParam(request, "examId"); //考试id
 			
 			List<ExamQuestionVo> examSelectQueslist = new ArrayList<ExamQuestionVo>();
 			List<ExamQuestionVo> examAskQuestionlist = new ArrayList<ExamQuestionVo>();
 			ExamVo exam = new ExamVo();
 			
 			int examId = 0;
 			if (!StringUtils.isBlank(examIdStr)&&!examIdStr.equals("undefined")&&!examIdStr.equals("0")) {
 				examId = Integer.valueOf(examIdStr);
 				//选择题集合
 				examSelectQueslist = examService.getExamQuestionByExamId(examId, 1);
 				//问答题集合
 				examAskQuestionlist = examService.getExamQuestionByExamId(examId, 4);
 			    
 			    exam = examService.getExamByExamId(examId);
 			}
 			
		    modelMap.put("type", exam.getExamType());
			modelMap.put("examId", examId);
			modelMap.put("examSelectQueslist", examSelectQueslist);
			modelMap.put("examAskQuestionlist", examAskQuestionlist);
			modelMap.put("examName", exam.getExamName());
 			
    		} catch (BaseException e) {
    			logger.error(e); e.printStackTrace();
    		}
    	   

     	return "course/exam/courseExamPage.htm";
     }
     
     
     /**
      * 
      * 功能描述：检查考试是否作答
      *
      * @author  jin.zhang
      * <p>创建日期 ：2014-3-4 下午3:00:01</p>
      *
      * @param request
      * @param response
      * @param modelMap
      * @throws BaseException
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
     @RequestMapping("/course/checkExamIsSubmit/")
     public void checkExamIsSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
     {
    	
    	 UserVo user = SiteUtils.getUser(request);
    	 String examId = RequestUtils.getQueryParam(request, "examId");
    	 String courseId = RequestUtils.getQueryParam(request, "courseId"); //课程id  
    	 
    	 //如果传入课程Id，则查询该课程下的考试是否提交
    	 if(!StringUtils.isBlank(courseId)){
    		 checkCourse(Integer.parseInt(courseId), request);
    		 List<ExamVo> list = examService.getExamListByCourId(Integer.valueOf(courseId), 0, 1);  //根据课程找到examId
    		 if(list!=null&&list.size()>0){
    			 ExamVo examVo = list.get(0);
        		 int examIds = examVo.getExamId();
        		 if(examIds>0){
        			 ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), Integer.valueOf(examIds));
            		 int result = 0;
            		 //已经提交过
            		 if(examStudentVo!=null){
            			 result = 1;
            		 }else{ //还未提交
            			 result = 2;
            		 }
            		JSONObject json  = new JSONObject();
             		json.put("examId", examIds);
            	    json.put("result", result);
            	    ResponseUtils.renderJson(response, json.toString());
            	    return;
        		 }
    		 }
    	 }
        //直接根据examId查询某考试是否提交
    	 if (!StringUtils.isBlank(examId)){ 
    		 if(Integer.parseInt(examId)>0){
	    		 ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), Integer.valueOf(examId));
	    		 int result = 0;
	    		 //已经提交过
	    		 if(examStudentVo!=null){
	    			 result = 1;
	    		 }else{ //还未提交
	    			 result = 2;
	    		 }
	    		JSONObject json  = new JSONObject();
	    		json.put("examId", examId);
	   	        json.put("result", result);
	   	        ResponseUtils.renderJson(response, json.toString());	
    		 }
    	 }
    	 
     }
     
     
     
     /**
      * 
      * 功能描述：检查是否已经提交考试
      *
      * @author  jin.zhang
      * <p>创建日期 ：2014-3-4 下午3:00:01</p>
      *
      * @param request
      * @param response
      * @param modelMap
      * @throws BaseException
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
     @RequestMapping("/course/checkIsSubmit/")
     public void checkIsSubmit(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
     {
    	
    	 UserVo user = SiteUtils.getUser(request);
    	 String examId = RequestUtils.getQueryParam(request, "examId"); //考试id
         //选择题集合
	     List<ExamQuestionVo> examSelectQueslist = examService.getExamQuestionByExamId(Integer.valueOf(examId), 1);
         
	     //判断选择题是否全部选填
	     if(examSelectQueslist!=null&&examSelectQueslist.size()>0){
        	for(ExamQuestionVo examQuestion:examSelectQueslist){
        		int quesId = examQuestion.getExamQuesId();
        		String answerOpt = RequestUtils.getQueryParam(request, "quesId"+quesId);  //课程id
	 		 if(answerOpt==null||answerOpt==""){
	 			JSONObject json  = new JSONObject();
	   	        json.put("result", "1"); //选择题未选填完！
	   	        ResponseUtils.renderJson(response, json.toString());	
	   	        return;
	 		 }
        	}
          }
    	 
    	 if (!StringUtils.isBlank(examId)) {
    		 ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), Integer.valueOf(examId));
    		 int result = 0;
    		 //已经提交过
    		 if(examStudentVo!=null){
    			 result = 2;
    		 }else{ //还未提交
    			 result = 3;
    		 }
    		JSONObject json  = new JSONObject();
   	        json.put("result", result);
   	        ResponseUtils.renderJson(response, json.toString());	
    	 }
    	 
     }
     
     
     /**
      * 
      * 功能描述：保存学生提交的试卷
      *
      * @author  jin.zhang
      * <p>创建日期 ：2014-2-20 下午2:40:28</p>
      *
      * @param request
      * @param response
      * @param modelMap
      * @return
      * @throws BaseException
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
      @RequestMapping("/course/submitPaper/")
      public void submitPaper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException
      {
    	 try {
     	    UserVo user = SiteUtils.getUser(request);
     	    ExamAnswerDetailVo examAnswerDetailVo = new ExamAnswerDetailVo();
     	    ExamStudentVo examStudentVo = new ExamStudentVo();
     	    //试题提交时间
     	    Date date = new Date();
     	    
  		    //考试id
  			String examId = RequestUtils.getQueryParam(request, "examId");   
  			//考试类型
  			String examType =RequestUtils.getQueryParam(request, "examType");
  			
 			if (!StringUtils.isBlank(examId)&&!StringUtils.isBlank(examType)) {
 			
 				 //选择题集合
			     List<ExamQuestionVo> selectQueslist = examService.getExamQuestionByExamId(Integer.valueOf(examId), 1);
				//判断选择题是否全部选填
			     if(selectQueslist!=null&&selectQueslist.size()>0){
		        	for(ExamQuestionVo examQuestion:selectQueslist){
		        		int quesId = examQuestion.getExamQuesId();
		        		String answerOpt = RequestUtils.getQueryParam(request, "quesId"+quesId);  //课程id
			 		 if(answerOpt==null||answerOpt==""){
			 			JSONObject json  = new JSONObject();
			   	        json.put("result", "1"); //选择题未选填完！
			   	        ResponseUtils.renderJson(response, json.toString());	
			   	        return;
			 		 }
		          }
			     }
 	  				ExamStudentVo examStudent = examService.getExamStudentByExamIdAndStuId(user.getUserId(), Integer.valueOf(examId));
 	  				if(examStudent!=null){  //练习已提交过
 	  					JSONObject json  = new JSONObject();
 	  		  	        json.put("result", 2);
 	  		  	        ResponseUtils.renderJson(response, json.toString());	
 	  		  	        return;
 	  		   }
 			
 	 			examStudentVo.setExamId(Integer.valueOf(examId)); //考试id
 				examStudentVo.setStudentId(user.getUserId());  //学生id
 				examStudentVo.setExamCtime(date);
 				//当考试类型不为练习，则有批阅状态
 				if(Integer.parseInt(examType)!=2){
 					examStudentVo.setExamState(2);  //未批阅作业
 				}
 				examStudentVo = examService.insertExamStudent(examStudentVo);

 				//选择题集合
		        List<ExamQuestionVo> examSelectQueslist = examService.getExamQuestionByExamId(Integer.valueOf(examId), 1);
		        int selectScore=0;
		        /****************--插入学生选择题作答的详情-- ***************************/
		        if(examSelectQueslist!=null&&examSelectQueslist.size()>0){
		        	for(ExamQuestionVo examQuestion:examSelectQueslist){
		        		int quesId = examQuestion.getExamQuesId();
		        		String answerOpt = RequestUtils.getQueryParam(request, "quesId"+quesId);  //课程id
		        		examAnswerDetailVo.setExamStuId(examStudentVo.getId());
	  	  				examAnswerDetailVo.setExamQuesId(Integer.valueOf(quesId));
	  	  				examAnswerDetailVo.setExamAnswer(answerOpt);
	  	  				//选择题回答正确得分没有则为0分
	  	  				if(examAnswerDetailVo.getExamAnswer().equals(examQuestion.getExamQuesAnswer())){
	  	  				   examAnswerDetailVo.setExamAnswerScore(examQuestion.getExamQuesScore());
	  	  				   if(Integer.parseInt(examType)==2){
	  	  					   selectScore+=examQuestion.getExamQuesScore();
	  	  				   }
	  	  				}else{
		  	  			   examAnswerDetailVo.setExamAnswerScore(0);
	  	  				}
	  	  				examAnswerDetailVo.setExamAnswerCtime(date);
	  	  			    examService.insertExamAnswerDetail(examAnswerDetailVo);
		        	 }
		        	//如果考试类型为练习，插入选择题总得分
		        	if(Integer.parseInt(examType)==2){
		        		examService.correctExamStu(0, selectScore, examStudentVo.getId());
		        	}
		          }
 			   }
  			
 			    //问答题集合
	 			String[] quesAnswers = RequestUtils.getQueryParamValues(request, "quesAnswer"); 			
	  			String[] quesIds = RequestUtils.getQueryParamValues(request, "quesId");
		        /*****************--插入学生问答题作答的详情-- ***************************/
	 			if(quesAnswers!=null&&quesAnswers.length>0){
		  			for(int i=0;i<quesAnswers.length;i++){	
		  				String quesAnswer = quesAnswers[i];
		  				String quesId = quesIds[i];
		  				
		  					examAnswerDetailVo.setExamStuId(examStudentVo.getId());
		  	  				examAnswerDetailVo.setExamQuesId(Integer.valueOf(quesId));
		  	  				examAnswerDetailVo.setExamAnswer(quesAnswer);
		  	  				examAnswerDetailVo.setExamAnswerScore(0);
		  	  				examAnswerDetailVo.setExamAnswerCtime(date);
		  	  			    examService.insertExamAnswerDetail(examAnswerDetailVo);
		  				
		  			}
	 			}

  			JSONObject json  = new JSONObject();
  	        json.put("result", 3);
  	        ResponseUtils.renderJson(response, json.toString());	
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
      }
      
      /**
       * 
       * 功能描述：课程搜索-自动补全
       *
       * @author  dongs
       * <p>创建日期 ：2014-2-20 </p>
       *
       * @param request
       * @param response
       * @param modelMap
       * @return
       * @throws BaseException
       *
       * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
       */
  	@RequestMapping("/course/search/autocomplete.ajax")
  	public void autocompleteCourse(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)
  	{	
  		String term=RequestUtils.getQueryParam(request, "term");
  		List<CourseVo> list=new ArrayList<CourseVo>();
  		if (!StringUtils.isBlank(term)) {
  			try {
  				list=courseService.getCourseListByKeyword(term, 20);
  			} catch (BaseException e) {
  				logger.error(e); e.printStackTrace();
  			}
  		}
  		StringBuffer stringBuffer=new StringBuffer("[");
  		for (CourseVo courseVo : list) {
  			stringBuffer.append("\"")
  			.append(courseVo.getCourseName()).append("\",");
  		}
  		stringBuffer.append("]");
  		String arrString=stringBuffer.toString().replace(",]", "]");
  		JSONObject jsonObject=new JSONObject();
  		jsonObject.put("arrString", arrString);
  		ResponseUtils.renderJson(response, jsonObject.toString());
  		
  	}

  	/**
  	 * 
  	 * 功能描述：查看作业、考试答案
  	 *
  	 * @author  biyun.huang
  	 * <p>创建日期 ：2014年3月3日 下午5:32:05</p>
  	 *
  	 * @param request
  	 * @param response
  	 * @param modelMap
  	 * @return
  	 * @throws BaseException 
  	 * @throws NumberFormatException 
  	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/course/showExamResult/")
	public String showExamResult(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)throws NumberFormatException, BaseException{
	
		UserVo user =SiteUtils.getUser(request);	
		//得到考试Id
	    String examId = RequestUtils.getQueryParam(request, "examId");   
		if (!StringUtils.isBlank(examId)) {
		{
			ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(user.getUserId(), Integer.valueOf(examId));
			List<ExamQuestionVo> examQuesSelectList=null;  //单项选择题得分
			List<ExamQuestionVo> examQuesMulSelList=null;  //多选题
			List<ExamQuestionVo> examQuesJudgeList=null;   //判断题
			List<ExamQuestionVo> examQuesShortAnsList=null; //简答题
			 //单选题、多选题、判断题、简答题、试卷总分(学生得分)
			double  singSelScore=0,mulSelScore=0,judSelScore=0,shortAnsScore=0,examTotleScore=0; 
			Map<String, Object> map=null;
			int examType=0;
			String examName=null;
			try {			
				/** 试题类型=1 得到单选题 **/
				map=examService.getStuExamQuesAnswer(Integer.valueOf(examId), examStudentVo.getId(),1);
				if(!map.isEmpty()){
					examQuesSelectList=(List<ExamQuestionVo>)map.get("examList");
					singSelScore=(Double)map.get("examScore");
				}
				
				/** 试题类型=2 得到多选题 **/
				map=examService.getStuExamQuesAnswer(Integer.valueOf(examId), examStudentVo.getId(),2);
				if(!map.isEmpty()){
					examQuesMulSelList=(List<ExamQuestionVo>)map.get("examList");
					mulSelScore=(Double)map.get("examScore");
				}
				
				/** 试题类型=3 得到判断题 **/
				map=examService.getStuExamQuesAnswer(Integer.valueOf(examId), examStudentVo.getId(),3);
				if(!map.isEmpty()){
					examQuesJudgeList=(List<ExamQuestionVo>)map.get("examList");
					judSelScore=(Double)map.get("examScore");
				}
				
				/** 试题类型=4 得到问答题 **/
				map=examService.getStuExamQuesAnswer(Integer.valueOf(examId), examStudentVo.getId(),4);
				if(!map.isEmpty()){
					examQuesShortAnsList=(List<ExamQuestionVo>)map.get("examList");
					shortAnsScore=(Double)map.get("examScore");
				}
				/** 得到试卷总分 **/
				examTotleScore=singSelScore+mulSelScore+judSelScore+shortAnsScore;
				examType=(Integer)map.get("examType");
				examName=(String)map.get("examName");
			}catch(Exception e){
				logger.error(e); e.printStackTrace();
			}
			modelMap.put("examQuesSelectList", examQuesSelectList);
			modelMap.put("examQuesMulSelList", examQuesMulSelList);
			modelMap.put("examQuesJudgeList", examQuesJudgeList);
			modelMap.put("examQuesShortAnsList", examQuesShortAnsList);
			modelMap.put("singSelScore", singSelScore);
			modelMap.put("mulSelScore", singSelScore);
			modelMap.put("judSelScore", shortAnsScore);
			modelMap.put("shortAnsScore", shortAnsScore);
			modelMap.put("examTotleScore", examTotleScore);
			modelMap.put("status", examStudentVo.getExamState());
			modelMap.put("examType", examType);
			modelMap.put("examName", examName);
		}
		}
		return "course/exam/showExamResult.htm";
	}

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private QuestionCenterService questionCenterService;
    @Autowired
    LearnService learnService;
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private ExamService examService;
	@Autowired
	private MyCollectService myCollectService;
	@Autowired
    private CourseCommentService courseCommentService;
}
