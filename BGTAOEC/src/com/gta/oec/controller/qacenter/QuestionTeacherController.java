/**
 * QuestionTeacherController.java	  V1.0   2014-1-20 上午10:21:54
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.qacenter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.springmvc.RichFreeMarkerView;
import com.gta.oec.common.web.utils.PathUtils;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.controller.directive.TradeTopTeacherDirective;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.teacher.TeacherService;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.user.UserVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 功能描述：答疑中心名师页控制层.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */

@Controller
public class QuestionTeacherController {
	
	private static final Log logger = LogFactory.getLog(QuestionTeacherController.class);
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private JobService jobService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private QuestionCenterService questionCenterService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private CourseService courseService;

	/**
	 * 功能描述：名师列表页
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/qacenter/teacherlist/")
	public String teacherListmain(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {
		String jumpProId = RequestUtils.getQueryParam(request, "jumpProId");
		String jumpJobId = RequestUtils.getQueryParam(request, "jumpJobId");
		List<ProfessionVo> professionList = null;
		
		UserVo userVo = new UserVo();
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		
		try {
			professionList = professionService.getProfessionList();
			if (professionList != null) {
				for (ProfessionVo professionVo : professionList) {
					int proId = professionVo.getProID();
					List<JobVo> jobGroupList = jobService.getJobGroupByProid(proId);
					if (jobGroupList != null) {
						professionVo.setJobGroupList(jobGroupList);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}

		PageModel pageModel = null;

		try {
			pageModel = teacherService.getTeacherPage(1, 6);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		
		modelMap.put("userVo", userVo);
		modelMap.put("pageModel", pageModel);
		modelMap.put("professionList", professionList);
		modelMap.put("jumpProId", jumpProId);
		modelMap.put("jumpJobId", jumpJobId);

		return "qacenter/teachermainpage.htm";
	}

	/**
	 * 功能描述：解疑模块,名师列表.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-23 上午9:40:56
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/qacenter/teachercontentlist/")
	public void teacherListDiv(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String profIdString = RequestUtils.getQueryParam(request, "profId");
		String jobgIdString = RequestUtils.getQueryParam(request, "jobgId");
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		String text = "";
		PageModel pageModel = null;
		UserVo userVo = new UserVo();
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
		}
		
		
		if (!StringUtils.isBlank(jobgIdString) && !jobgIdString.equals("0")) {
			try {
				pageModel = teacherService.getTeacherListByjobGroupId(Integer.valueOf(jobgIdString), Integer.valueOf(pageNo), 6);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e); e.printStackTrace();
			}
		} else if (!StringUtils.isBlank(profIdString)) {
			try {
				pageModel = teacherService.getTeacherListByProfessionId(Integer.valueOf(profIdString), Integer.valueOf(pageNo), 6);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		if (StringUtils.isBlank(jobgIdString) && StringUtils.isBlank(profIdString)) {
			try {
				pageModel = teacherService.getTeacherPage(Integer.valueOf(pageNo) + 1, 6);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

		}	
			try {
				Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/qacenter/teachercontentlist.htm");
				ModelMap map = new ModelMap();
				map.put("pageModel", pageModel);
				map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
				map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));			
				map.put("userVo", userVo);
				text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			} catch (IOException e) {
				logger.error(e); e.printStackTrace();
			} catch (TemplateException e) {
				logger.error(e); e.printStackTrace();
			}
		
		ResponseUtils.renderText(response, text);

	}

	/**
	 * 功能描述：名师详情.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-23 上午9:41:38
	 *         </p>
	 * 
	 * @param teacherId
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/qacenter/teacherdetail/{teacherId}/")
	public String teacherDetail(@PathVariable int teacherId, HttpServletResponse response, HttpServletRequest request, ModelMap modelMap)
			throws LoginException {

		TeacherVo teacherVo = null;
		UserVo userVo = new UserVo();
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		
		if (teacherId != 0) {
			try {
				String userId = RequestUtils.getQueryParam(request, "userId");
				if (!StringUtils.isBlank(userId)) {
					teacherVo = teacherService.getTeacherByUserId(Integer.parseInt(userId));
				}else {
					teacherVo = teacherService.getTeacherById(teacherId);
				}
				
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

		}
		if (teacherVo!=null) {
		modelMap.put("userVo", userVo);
		modelMap.put("teacherVo", teacherVo);
		return "qacenter/teacherdetails.htm";
			
		}else {
			return "redirect:/index/?headTab=1";
		}
	}

	/**
	 * 功能描述：名师详情页,获取教师课程.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-23 上午9:42:31
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping("/qacenter/teacherdetail/courselist/")
	public void teacherCourseList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String teacherId = RequestUtils.getQueryParam(request, "teacherId");
		String toPage = RequestUtils.getQueryParam(request, "toPage");
		TeacherVo teacherVo = new TeacherVo();
		String text = "";
		PageModel pageModel = null;
		try {
			teacherVo = teacherService.getTeacherById(Integer.valueOf(teacherId));
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		try {
			pageModel = courseService.getCoursePageModelByUserId(teacherVo.getUserId(),10, Integer.valueOf(toPage));

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

	
			try {
				Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/qacenter/teachercourselist.htm");
				modelMap.put("pageModel", pageModel);
				modelMap.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
				modelMap.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
				text = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelMap);
			} catch (IOException e) {
				logger.error(e); e.printStackTrace();
			} catch (TemplateException e) {
				logger.error(e); e.printStackTrace();
			}
		

		ResponseUtils.renderText(response, text);
	}

	/**
	 * 功能描述：名师详情页,获取教师答疑.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-23 上午9:41:59
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 * @throws LoginException
	 */
	@RequestMapping("/qacenter/teacherdetail/questionlist/")
	public void teacherQuestionList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws LoginException {
		String teacherId = RequestUtils.getQueryParam(request, "teacherId");
		String toPage = RequestUtils.getQueryParam(request, "toPage");
		String text = "";
		TeacherVo teacherVo = null;
		try {
			teacherVo = teacherService.getTeacherById(Integer.valueOf(teacherId));
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		PageModel pageModel = new PageModel();
		try {
			pageModel = questionCenterService.getQuestionPageByTeacherId(Integer.valueOf(teacherId), 3, Integer.valueOf(toPage));
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		List<QuestionVo> list = pageModel.getList();
		// 带最新老师回答的问题列表.
		for (QuestionVo questionVo : list) {
			try {
				questionVo.setAnswerVo(questionCenterService.getAnswerVoByQuesIdUserId(questionVo.getQuesID(), teacherVo.getUserId()));
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

		}
		pageModel.setList(list);
		if (null != pageModel && pageModel.getList().size() > 0) {
			try {
				Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/qacenter/teacherquestionlist.htm");
				ModelMap map = new ModelMap();
				map.put("pageModel", pageModel);
				map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
				map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
				text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			} catch (IOException e) {
				logger.error(e); e.printStackTrace();
			} catch (TemplateException e) {
				logger.error(e); e.printStackTrace();
			}
		}

		ResponseUtils.renderText(response, text);

	}
}