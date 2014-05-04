/**
 * LearnController.java	  V1.0   2014-4-3 上午9:21:45
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.course;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.course.LearnService;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.DateUtils;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.exam.ExamPaperVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.learn.LearnVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.user.UserVo;

@Controller
public class LearnController extends BaseCourseController{
	private static final Log logger = LogFactory.getLog(LearnController.class);

	/**
	 * 
	 * 功能描述：保存笔记
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-14 下午4:28:15</p>
	 *
	 * @param request
	 * @param response
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/course/saveNote/",method=RequestMethod.POST)
	public void saveNote(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, BaseException{
		String courseId = RequestUtils.getQueryParam(request, "noteCourseId");
		String sectionId = RequestUtils.getQueryParam(request, "noteSectionId");
		String videoTime = RequestUtils.getQueryParam(request, "videoTime");
		String noteContent  = RequestUtils.getQueryParam(request, "noteContent");
		
		checkCourse(Integer.parseInt(courseId), request);
		
		boolean result = false;
		//校验参数的合法性
		if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)
			&&!StringUtils.isBlank(videoTime)&&!StringUtils.isBlank(noteContent)&&noteContent.length()<=500) {
			try {
				UserVo user = SiteUtils.getUser(request);
				
				NoteVo noteVo = new NoteVo();
				noteVo.setUserId(user.getUserId());
				noteVo.setCourseId(Integer.parseInt(courseId));
			    noteVo.setSecId(Integer.parseInt(sectionId));
			    videoTime = StrUtils.prasVideoTime(videoTime);
			    noteVo.setVideoTime(videoTime);
			    noteVo.setNoteContent(noteContent);
			    try {
					noteVo.setNoteSubTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					noteVo.setNoteCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					noteVo.setNoteUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					logger.error(e); e.printStackTrace();
				}
			    //保存笔记
			    courseService.insertNote(noteVo);
			    result = true;
			} catch (LoginException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("videoTime", videoTime);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}
	/**
	 * 
	 * 功能描述：获取已提交笔记列表
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-3-12 上午10:25:15</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/course/getNote.ajax")
	public String getNote(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws NumberFormatException, BaseException{
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
	    checkCourse(Integer.parseInt(courseId), request);
		//校验参数的合法性
		if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)) {
			try {
				UserVo user = SiteUtils.getUser(request);	
			    //保存笔记
			    List<NoteVo> list = userService.getNote(Integer.parseInt(courseId), Integer.parseInt(sectionId), user.getUserId());
			    modelMap.put("noteList", list);
			} catch (LoginException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
		}
        return "course/learn/notelist.htm";
	}
	/**
	 * 
	 * 功能描述：获取已提交课程问题列表
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-3-12 上午10:25:32</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/course/getAsk.ajax")
	public String getAsk(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws NumberFormatException, BaseException{
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
		checkCourse(Integer.parseInt(courseId), request);
		//校验参数的合法性
		if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)) {
			try {
				UserVo user = SiteUtils.getUser(request);	
			    //保存笔记
				QuestionVo queryVo = new QuestionVo();
				queryVo.setCourseID(Integer.parseInt(courseId));
				queryVo.setSecID(Integer.parseInt(sectionId));
				queryVo.setUserID(user.getUserId());
			    List<QuestionVo> list =  questionCenterService.getQestionList(queryVo);
			    modelMap.put("askList", list);
			} catch (LoginException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
		}
        return "course/learn/asklist.htm";
	}
	/**
	 * 
	 * 功能描述：保存课程提问
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-14 下午6:43:28</p>
	 *
	 * @param request
	 * @param response
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/course/saveAsk/",method=RequestMethod.POST)
	public void saveAsk(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, BaseException{
		String courseId = RequestUtils.getQueryParam(request, "askCourseId");
		String sectionId = RequestUtils.getQueryParam(request, "askSectionId");
		String askContent  = RequestUtils.getQueryParam(request, "askContent");
		 checkCourse(Integer.parseInt(courseId), request);
		boolean result = false;
		String createTime = null;
		String errorMsg = null;
		//校验参数的合法性
		if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)
			&&!StringUtils.isBlank(askContent)&&askContent.length()<200) {
			try {
				UserVo user = SiteUtils.getUser(request);
	            QuestionVo questionVo = new QuestionVo();
	            questionVo.setCourseID(Integer.parseInt(courseId));
	            questionVo.setSecID(Integer.parseInt(sectionId));
	            questionVo.setQuesContent(askContent);
	            questionVo.setUserID(user.getUserId());
	            questionVo.setQuesType(1);
	            try {	            
					questionVo.setQuesCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					questionVo.setQuesUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					logger.error(e); e.printStackTrace();
				}
	        //   savedQuestion= questionCenterService.saveQuestion(questionVo);
	           	questionCenterService.saveCourseQuestion(questionVo);
	            createTime = DateUtils.format(questionVo.getQuesCtime(), "yyyy-MM-dd HH:mm:ss");
			    result = true;
			} catch (LoginException e) {
				errorMsg ="您的账号未登录/已在别处登录！";
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}

			
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("createTime", createTime);
		jsonObject.put("errorMsg", errorMsg);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}	
	/**
	 * 
	 * 功能描述：课程视频学习
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-14 下午4:27:27</p>
	 *
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
    @RequestMapping("/course/learn/")
	public String  courseLearn(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, BaseException {  	
         String courseId = RequestUtils.getQueryParam(request, "courseId");
         String sectionId = RequestUtils.getQueryParam(request, "sectionId");
         checkCourse(Integer.parseInt(courseId), request);
     	 UserVo userVo = SiteUtils.getUser(request);

         if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
			try {
			
				//更新学习进度
				if (!StringUtils.isBlank(sectionId)) {
					LearnVo learnVo = new LearnVo();
					learnVo.setCourseId(Integer.parseInt(courseId));
					learnVo.setUserId(userVo.getUserId());
					learnVo.setSectionId(Integer.parseInt(sectionId));
					learnVo.setLearnState(1);
					learnService.saveLearn(learnVo);		
				}
	
				//获取课程详细信息
				CourseVo courseVo = courseService.getUserCourseLearnDetail(Integer.parseInt(courseId), userVo.getUserId());
				
				//查询该课程是否有考试				
				List<ExamVo> list = examService.getExamListByCourId(Integer.valueOf(courseId), 0, 1);
				modelMap.put("isExam", list==null?0:list.size());
				modelMap.put("course", courseVo);
				modelMap.put("sectionId", sectionId);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
		 }
     	 String vodServer = MessageResolver.getMessage(request, "vodServer", null);
     	 String liveServer = MessageResolver.getMessage(request, "liveServer", null);
     	 String videoProtocol = MessageResolver.getMessage(request, "videoProtocol", null);
     	 modelMap.put("videoProtocol", videoProtocol);
     	 modelMap.put("vodServer", vodServer);
     	 modelMap.put("liveServer", liveServer);
         return "course/learn/learn.htm";
	} 
    /**
     * 
     * 功能描述：获取课程核心知识点
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-2-20 下午6:52:07</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/learn/getCoreKnowledge.ajax")
  	public String  courseIndex(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) {  	

           String sectionId = RequestUtils.getQueryParam(request, "sectionId");
       	   try {
		    SiteUtils.getUser(request);
		    if (!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)) {
		    	try {
					SectionVO sectionVO = courseService.getSectionById(Integer.parseInt(sectionId));
					modelMap.put("section", sectionVO);
				} catch (NumberFormatException e) {
					logger.error(e); e.printStackTrace();
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
	        }
		   } catch (LoginException e) {
			logger.error(e); e.printStackTrace();
		   }    
           return "course/learn/learn_knowledge.htm";
  	} 
    /**
     * 
     * 功能描述：获取练习
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-2-20 下午6:52:50</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws BaseException 
     * @throws NumberFormatException 
     */
    @RequestMapping("/course/learn/getCourseTest.ajax")
  	public String  courseTest(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, BaseException {  	

           String sectionId = RequestUtils.getQueryParam(request, "sectionId");
           String courseId = RequestUtils.getQueryParam(request, "courseId");
           checkCourse(Integer.parseInt(courseId), request);
           if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(sectionId)) {
        	   try {
        		   
       			ExamPaperVo examPaperVo = examService.getSectionPaper(Integer.parseInt(courseId), Integer.parseInt(sectionId), 2);
       			
       			//查询用户是否已经提交过练习,如果已提交，则展示答案
       			ExamStudentVo examStudentVo = examService.getExamStudentByExamIdAndStuId(SiteUtils.getUser(request).getUserId(), examPaperVo.getExamId());
       		    
       			modelMap.put("isExam", examStudentVo==null?0:1);
       			
       			if (null!=examStudentVo) {
       				//获取答案
					Map<String, Object> map = examService.getStuExamQuesAnswer(examStudentVo.getExamId(), examStudentVo.getId(), 1);
					modelMap.put("paperAnswerdetails", map.get("examList"));
				}	
       			modelMap.put("examPaper", examPaperVo);
       			modelMap.put("courseId", courseId);
       		   } catch (NumberFormatException e) {
       			logger.error(e); e.printStackTrace();
       		   } catch (BaseException e) {
       		  	logger.error(e); e.printStackTrace();
       		   }
		}
          
           modelMap.put("courseId", courseId);
         return "course/learn/learn_test.htm";
  	} 
    
    /**
     * 
     * 功能描述：继续学习
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-22 下午4:09:55</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws LoginException 
     */
    @RequestMapping("/course/learn/keepOnStudy/")
	public String  keepOnStudy(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws BaseException {  	
        String courseId = RequestUtils.getQueryParam(request, "courseId");
        checkCourse(Integer.parseInt(courseId), request);
        Integer sectionId = null;
        boolean flag = true;
    	UserVo userVo = SiteUtils.getUser(request);
        if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
			try {
			
				CourseVo courseVo = courseService.getUserCourseLearnDetail(Integer.parseInt(courseId), userVo.getUserId());
				if (null!=courseVo) {
					if (null!=courseVo.getList()) {
						//章
						for (SectionVO sectionVO:courseVo.getList()) {
							 List<SectionVO> list = sectionVO.getList();
							if (null!=list&&list.size()>0) {
								//节
								for (SectionVO section:list) {
									if (null==section.getLearn()) {
										sectionId = section.getId();
										flag = false;
										break;
									}
								}
							}
							if (!flag) {
								break;
							}
						}
					}
				}
				
				
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}
		 }
        String redirect = "redirect:/course/learn/?courseId="+courseId+"&";
        if (null!=sectionId&&sectionId.intValue()>0) {
        	redirect += "sectionId="+sectionId;
		}
		return redirect;
	} 
    /**
     * 
     * 功能描述：保存学习信息
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-21 下午6:28:37</p>
     *
     * @param modelMap
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/course/learn/saveLearn/",method=RequestMethod.POST)
	public void  saveLearn(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws BaseException{  	
         String courseId = RequestUtils.getQueryParam(request, "courseId");
         checkCourse(Integer.parseInt(courseId), request);
         String sectionId = RequestUtils.getQueryParam(request, "sectionId");
         if (!StringUtils.isBlank(courseId)&&!StringUtils.isBlank(sectionId)&&StrUtils.isNum(courseId)&&StrUtils.isNum(sectionId)) {
			try {
				UserVo userVo = SiteUtils.getUser(request);
				//更新学习进度
				LearnVo learnVo = new LearnVo();
				learnVo.setCourseId(Integer.parseInt(courseId));
				learnVo.setUserId(userVo.getUserId());
				learnVo.setSectionId(Integer.parseInt(sectionId));
				learnVo.setLearnState(1);
				learnService.saveLearn(learnVo);	
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e.getMessage());e.printStackTrace();
			}catch (Exception e) {
				logger.error("save learn exception.");
			}
		 }
		
	}
    @Autowired
    LearnService learnService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionCenterService questionCenterService;
	@Autowired
	private ExamService examService;
}
