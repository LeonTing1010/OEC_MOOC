/**
 * TeacherCenterconTroller.java	  V1.0   2014-1-15 上午11:28:46
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.teachercenter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.gta.oec.enums.CourseStateEnum;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.school.SchoolService;
import com.gta.oec.service.studentcenter.StudentcenterService;
import com.gta.oec.service.teacher.TeacherService;
import com.gta.oec.service.teacher.TeacherShineService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.CourseUtils;
import com.gta.oec.util.DateUtils;
import com.gta.oec.util.ObjectUtils;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.gta.oec.vo.exam.ExamQuestionVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.resource.ResourceVo;
import com.gta.oec.vo.school.SchoolVo;
import com.gta.oec.vo.teacher.TeacherShineVo;
import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.user.UserVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 功能描述：教师中心控制层
 * 
 * @author bingzhong.qin
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */

@Controller
public class TeacherCenterconTroller {
	private static final Log logger = LogFactory.getLog(TeacherCenterconTroller.class);
	
	/**
	 * 
	 * 功能描述：教师中心
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 上午11:30:22
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws BaseException 
	 */
	@RequestMapping("/teacherCenter/index/")
	public String teacherCenterIndex(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws BaseException {
		String tab = RequestUtils.getQueryParam(request, "tab");
		UserVo userVo = SiteUtils.checkTeacher(request);
		TeacherVo teacherVo = null;
		int unsolvedQuesNum = 0;
		int unCorrectOpNum = 0;
		int unCorrectExamNum = 0;
		ExamVo examVo = new ExamVo();
		ExamStudentVo examStudentVo = new ExamStudentVo();
		CourseVo courseVo = new CourseVo();
		courseVo.setUserId(userVo.getUserId());
		courseVo.setStatus(CourseStateEnum.PUBLISHED.getValue()); // 课程为已发布课程
		try {
			userVo = SiteUtils.getUser(request);
		} catch (BaseException e) {
			logger.error(e); 
			throw e;
		}
		
		int quesAddCout = 0;
		try {
			teacherVo = teacherService.getTeacherByUserId(userVo.getUserId());
			modelMap.put("teacher", ObjectUtils.prse(teacherVo, TeacherVo.class));

			if (teacherVo != null) {
				QuestionUserVo questionUser = new QuestionUserVo();
				questionUser.setStatus(0);
				// questionUser.setTeacherID(teacherVo.getId());
				questionUser.setTeacherID(userVo.getUserId());
				// 未解答数.不包括追问.
				unsolvedQuesNum = teacherService.countQuestionPageModel(questionUser, 5);// 除1,2以外全部表示查询全部未解答.
				// 追问数量
				quesAddCout = teacherService.quesAddCout(questionUser);
				unsolvedQuesNum = unsolvedQuesNum + quesAddCout;

				modelMap.put("unsolvedQuesNum", unsolvedQuesNum);
				// modelMap.put("teacher", teacherVo);
			}

			// 待评作业
			examVo.setExamType(3); // 设置考试类型为作业
			List<ExamVo> optionList = examService.getCourExamList(examVo, courseVo, 0, 0);
			for (ExamVo exam : optionList) {
				examStudentVo.setExamId(exam.getExamId());
				examStudentVo.setExamState(2); // 未批阅作业
				int count = examService.getTakeExamCount(examStudentVo);
				unCorrectOpNum += count;
			}

			// 待评考试
			examVo.setExamType(1); // 设置考试类型为考试
			List<ExamVo> ExamList = examService.getCourExamList(examVo, courseVo, 0, 0);
			for (ExamVo exam : ExamList) {
				examStudentVo.setExamId(exam.getExamId());
				examStudentVo.setExamState(2); // 未批阅作业
				int count = examService.getTakeExamCount(examStudentVo);
				unCorrectExamNum += count;
			}
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		if (!StrUtils.isNum(tab)) {
			tab = "12";
		}
		userVo.setUserType(2);
		SiteUtils.setUser(request, userVo);
		modelMap.put("tab", tab);
		modelMap.put("unCorrectOpNum", unCorrectOpNum);
		modelMap.put("unCorrectExamNum", unCorrectExamNum);
		return "teacenter/main.htm";
	}

	/**
	 * 
	 * 功能描述: 跳转教师个人中心页
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月11日 上午11:02:35
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/teacenterIndex/")
	public String teacenterIndex(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo userVo = SiteUtils.checkTeacher(request);
		CourseVo courseVo = new CourseVo();
		CourseVo courseVo2 = new CourseVo();
		QuestionUserVo questionUserVo = new QuestionUserVo();
		ExamVo examVo = new ExamVo();
		ExamStudentVo examStudentVo = new ExamStudentVo();
		List<ExamVo> optionList = new ArrayList<ExamVo>(); // 作业列表
		List<ExamVo> examList = new ArrayList<ExamVo>(); // 考试列表
		List<QuestionVo> questionList = new ArrayList<QuestionVo>(); // 问题列表
		int count = 0; // 记录数
		courseVo.setUserId(userVo.getUserId());
		courseVo.setStatus(CourseStateEnum.PUBLISHED.getValue()); // 课程为已发布课程

		// 作业批阅模块
		examVo.setExamType(3); // 设置考试类型为作业
		examVo.setPaperState(2); // 设置批阅状态为未批阅
		try {
			optionList = examService.getCourExamList(examVo, courseVo, 0, 4); // 只查询前4条
			for (ExamVo exam : optionList) {
				examStudentVo.setExamId(exam.getExamId());
				examStudentVo.setExamState(2); // 未批阅作业
				count = examService.getTakeExamCount(examStudentVo); // 得到该作业的未批阅数
				exam.setNoCorrect(count); // 设置该作业的未批阅数

				examStudentVo.setExamState(1); // 已批阅作业
				count = examService.getTakeExamCount(examStudentVo); // 得到该作业的已批阅数
				exam.setHasCorrect(count); // 设置该作业的已批阅数

				courseVo2.setCourseId(exam.getCourId());
				count = courseService.getCourStudyCount(courseVo2); // 得到学习该作业课程的人数
				exam.setLearnCount(count);
			}
			modelMap.put("optionList", optionList);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		// 考试批阅模块
		examVo.setExamType(1); // 设置考试类型为考试
		examVo.setPaperState(2);// 设置批阅状态为未批阅
		try {
			examList = examService.getCourExamList(examVo, courseVo, 0, 4); // 只查询前4条
			for (ExamVo exam : examList) {
				examStudentVo.setExamId(exam.getExamId());
				examStudentVo.setExamState(2); // 未批阅考试
				count = examService.getTakeExamCount(examStudentVo); // 得到该考试的未批阅数
				exam.setNoCorrect(count); // 设置该考试的未批阅数

				examStudentVo.setExamState(1); // 已批阅考试
				count = examService.getTakeExamCount(examStudentVo); // 得到该考试的已批阅数
				exam.setHasCorrect(count); // 设置该考试的已批阅数

				courseVo2.setCourseId(exam.getCourId());
				count = courseService.getCourStudyCount(courseVo2); // 得到学习该考试课程的人数
				exam.setLearnCount(count);
			}
			modelMap.put("examList", examList);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		// 问题模块
		questionUserVo.setTeacherID(userVo.getUserId()); // 设置回答问题的老师为当前登录用户
		questionUserVo.setStatus(0); // 设置回答状态为未解答
		try {
			questionList = teacherService.getTeaQuestionList(questionUserVo, 3, 1, 6);
			modelMap.put("questionList", questionList);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		return "teacenter/index/teacherindex.htm";
	}

	/**
	 * 
	 * 功能描述：上传课件第一步页面
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午1:17:42
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step1/")
	public String teacherCenterStep1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		CourseVo courseVo = new CourseVo();

		// 校验是否是老师登录
		SiteUtils.checkTeacher(request);
		if (!StringUtils.isBlank(courseId) && StrUtils.isNum(courseId)) {
			try {
				courseVo = courseService.getCourseInfoById(Integer.parseInt(courseId));
				List<CourseJobVo> jobList = jobService.getAssociatedJobByCourseId(Integer.parseInt(courseId));
				modelMap.put("jobCount", jobList == null ? 0 : jobList.size());
				// 组装job数组
				if (jobList != null) {
					StringBuffer str = new StringBuffer("[");
					Iterator<CourseJobVo> iterator = jobList.iterator();
					while (iterator.hasNext()) {
						str.append(iterator.next().getJobID()).append(",");
					}
					str.append("]");
					modelMap.put("jobStr", str.toString());
				}
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		modelMap.put("courseId", courseId);
		modelMap.put("course", courseVo);
		return "teacenter/upload/step1.htm";
	}

	/**
	 * 
	 * 功能描述：上传课件第二步页面
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午1:17:42
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step2/")
	public String teacherCenterStep2(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		modelMap.put("courseId", courseId);
		return "teacenter/upload/step2.htm";
	}

	/**
	 * 
	 * 功能描述：上传课件第三步页面
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-17 上午8:43:50
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step3/")
	public String teacherCenterStep3(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		SiteUtils.checkTeacher(request);

		if (!StringUtils.isBlank(courseId) && StrUtils.isNum(courseId)) {
			try {
				CourseVo course = courseService.getCourseById(Integer.parseInt(courseId));
				modelMap.put("course", course);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.info(e.getMessage());
			}
		}
		modelMap.put("courseId", courseId);
		return "teacenter/upload/step3.htm";
	}

	/**
	 * 
	 * 功能描述：上传课程第四步
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-20 上午10:42:26
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step4/")
	public String teacherCenterStep4(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		modelMap.put("courseId", courseId);
		return "teacenter/upload/step4.htm";
	}

	/**
	 * 
	 * 功能描述：加载岗位选择页面
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午1:17:42
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step1_job/")
	public String teacherCenterStep1Job(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		try {
			List<JobVo> list = jobService.getJobGroupDetailList(null);
			modelMap.put("jobList", list);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		return "teacenter/upload/step1_job.htm";
	}

	/**
	 * 
	 * 功能描述：上传课程封面图片
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-3-5 下午5:17:59
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/upload/uploadImg/")
	public String teacherCenterStep1UploadImg(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgMultipartFile = multipartRequest.getFile("titlePageFile");
		String errorMsg = "";
		logger.info("img upload  start..." + imgMultipartFile.getOriginalFilename());
		if (StringUtils.isBlank(imgMultipartFile.getOriginalFilename())) {
			errorMsg = "图片为空!";
		} else if (!StringUtils.isBlank(imgMultipartFile.getOriginalFilename()) && imgMultipartFile.getSize() > 0
				&& !FileUtils.checkFile(imgMultipartFile, "jpg", 500)) {
			errorMsg = "课程图片大小不超过500KB";
		} else {
			String courseImgRoot = MessageResolver.getMessage(multipartRequest, "courseImgRoot", null);
			String imageRoot = MessageResolver.getMessage(multipartRequest, "imageRoot", null);
    		String imageServerIp = MessageResolver.getMessage(multipartRequest, "imageServerIp", null);
    		String imageServerName = MessageResolver.getMessage(multipartRequest, "imageServerName", null);
    		String imageServerPassword = MessageResolver.getMessage(multipartRequest, "imageServerPassword", null);
    		String filePath = "smb://"+imageServerIp+imageRoot+courseImgRoot;
    		String fileName = UploadUtils.generateFilename("", FileUtils.getFileType(imgMultipartFile.getOriginalFilename()));
			try {
				fileRepository.storeByRmoeteFilename(imageServerIp, imageServerName, imageServerPassword, filePath.trim(),
						 fileName, imgMultipartFile);
				fileName=courseImgRoot+fileName;
				modelMap.put("dataUrl", fileName);
			} catch (IOException e) {
				errorMsg = "图片上传失败，请重新上传";
				logger.error(e); 
				e.printStackTrace();
			}

		}

		logger.info("img upload  end..." + modelMap.get("dataUrl"));
		modelMap.put("type", "Img");
		modelMap.put("errorMsg", errorMsg);
		return "teacenter/upload/uploadok.htm";
	}

	/**
	 * 
	 * 功能描述：预览视频上传
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-3-7 上午9:59:00
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/upload/uploadVideo/")
	public String teacherCenterStep1uploadVideo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile previewVideo = multipartRequest.getFile("previewFile");
		String errorMsg = "";
		try {
			logger.info(" preview video upload start..." + previewVideo.getOriginalFilename() + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		if (previewVideo != null && previewVideo.getSize() > 0 && !StringUtils.isBlank(previewVideo.getOriginalFilename())) {
			String courseRoot = MessageResolver.getMessage(multipartRequest, "courseRoot", null);
			String videoRoot = MessageResolver.getMessage(multipartRequest, "videoRoot", null);
			String videoServerIp = MessageResolver.getMessage(multipartRequest, "videoServerIp", null);
			String videoServerName = MessageResolver.getMessage(multipartRequest, "videoServerName", null);
			String vidoeServerPassword = MessageResolver.getMessage(multipartRequest, "vidoeServerPassword", null);
			String filePath = "smb://" + videoServerIp + videoRoot;
			String filename = UploadUtils.generateFilename("", FileUtils.getFileType(previewVideo.getOriginalFilename()));
			String fileName2 = "";
			try {
				modelMap.put("resourceName", previewVideo.getOriginalFilename());
				fileName2 = fileRepository.storeByRmoeteFilename(videoServerIp, videoServerName, vidoeServerPassword, filePath.trim(),
						filename, previewVideo);
				modelMap.put("dataUrl", fileName2);
				try {
					logger.info("preview video upload end.." + modelMap.get("dataUrl") + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					logger.error(e); e.printStackTrace();
				}
			} catch (IOException e) {
				errorMsg = "预览视频上传异常！";
                logger.error("uploadVideo IOException:" + e);			
			}
	
		}

		modelMap.put("type", "Video");
		modelMap.put("errorMsg", errorMsg);
		return "teacenter/upload/uploadok.htm";
	}

	/**
	 * 
	 * 功能描述：章节视频上传
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-3-7 上午9:57:24
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/upload/uploadSectionVideo/")
	public String teacherCenteruploadSectionVideo(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile videoFile = multipartRequest.getFile("mainFile");
		String errorMsg = "";
		try {
			logger.info("section upload start..." + videoFile.getOriginalFilename() + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		if (videoFile != null && videoFile.getSize() > 0 && !StringUtils.isBlank(videoFile.getOriginalFilename())) {
			String videoRoot = MessageResolver.getMessage(multipartRequest, "videoRoot", null);
			String videoServerIp = MessageResolver.getMessage(multipartRequest, "videoServerIp", null);
			String videoServerName = MessageResolver.getMessage(multipartRequest, "videoServerName", null);
			String vidoeServerPassword = MessageResolver.getMessage(multipartRequest, "vidoeServerPassword", null);
			String filePath = "smb://" + videoServerIp + videoRoot;
			String filename = UploadUtils.generateFilename("", FileUtils.getFileType(videoFile.getOriginalFilename()));
			String fileName2 = "";
			try {
				modelMap.put("resourceName", videoFile.getOriginalFilename());
				fileName2 = fileRepository.storeByRmoeteFilename(videoServerIp, videoServerName, vidoeServerPassword, filePath.trim(),
						filename, videoFile);
				try {
					logger.info("section upload start..." + modelMap.get("dataUrl") + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					logger.error(e); e.printStackTrace();
				}
				modelMap.put("dataUrl", fileName2);
			} catch (IOException e) {
				errorMsg = "预览视频上传异常！";
				logger.error(errorMsg + e);
			}

		}

		modelMap.put("type", "Section");
		modelMap.put("errorMsg", errorMsg);
		return "teacenter/upload/uploadok.htm";
	}

	@RequestMapping("/teacherCenter/upload/uploadAidResource/")
	public String teacherCenteruploadAidResource(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile aidFile = multipartRequest.getFile("aidFile");
		String errorMsg = "";
		try {
			logger.info("辅助资源上传开始..." + aidFile.getOriginalFilename() + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		if (aidFile != null && aidFile.getSize() > 0 && !StringUtils.isBlank(aidFile.getOriginalFilename())) {
			String resourceRoot = MessageResolver.getMessage(multipartRequest, "resourceRoot", null);
			String resourceServerIp = MessageResolver.getMessage(multipartRequest, "resourceServerIp", null);
    		String resourceServerName = MessageResolver.getMessage(multipartRequest, "resourceServerName", null);
    		String resourceServerPassword = MessageResolver.getMessage(multipartRequest, "resourceServerPassword", null);
    		String aidResourceRoot = MessageResolver.getMessage(multipartRequest, "aidResourceRoot", null);
    		
    		String filePath = "smb://"+resourceServerIp+resourceRoot+aidResourceRoot;
    		String fileName = UploadUtils.generateFilename("", FileUtils.getFileType(aidFile.getOriginalFilename()));
			try {
				modelMap.put("resourceName", aidFile.getOriginalFilename());
				fileRepository.storeByRmoeteFilename(resourceServerIp, resourceServerName, resourceServerPassword, filePath.trim(),
						 fileName, aidFile);
				fileName=aidResourceRoot+fileName;
			} catch (IOException e) {
				errorMsg = "文件上传异常！";
				logger.error(errorMsg+e);
			}
			modelMap.put("dataUrl", fileName);
		}
		try {
			logger.info("辅助资源上传结束..." + modelMap.get("dataUrl") + ":" + DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("type", "AidResource");
		modelMap.put("errorMsg", errorMsg);
		return "teacenter/upload/uploadok.htm";
	}

	/**
	 * 
	 * 功能描述：上传第一步，提交
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午3:46:09
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/saveStep1/")
	public void teacherCenterSaveStep1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {

		String titlePage = RequestUtils.getQueryParam(request, "titlePage");// 图片封面URL
		String previewUrl = RequestUtils.getQueryParam(request, "previewUrl");// 预览视频URL
		String resourceName = RequestUtils.getQueryParam(request, "resourceName");
		String type = RequestUtils.getQueryParam(request, "type");// 课程类型
		String[] job = RequestUtils.getQueryParamValues(request, "job");// 关联的岗位
		String courseName = RequestUtils.getQueryParam(request, "courseName");// 课程名称
		String introduction = RequestUtils.getQueryParam(request, "introduction");// 课程描述
		String scoreMethod = RequestUtils.getQueryParam(request, "scoreMethod");// 课程评分方式
		String credit = RequestUtils.getQueryParam(request, "credit");// 课程学分
		String price = RequestUtils.getQueryParam(request, "price");// 课程价格
		String step1CourseId = RequestUtils.getQueryParam(request, "step1CourseId");// 课程id标识，当第二步点击上一步的时候值存在，不进行保存操作

		// 校验结果标识
		boolean save = true;
		String errorMsg = null;
		JSONObject respJsonObject = new JSONObject();
		respJsonObject.put("courseId", step1CourseId);
		// 校验参数
		if (StringUtils.isBlank(type) || !CourseUtils.checkCredit(credit) || !CourseUtils.checkIntroduction(introduction)
				|| !CourseUtils.checkScoreMethod(scoreMethod) || !CourseUtils.checkCourseName(courseName) || !CourseUtils.checkPrice(price)) {
			save = false;
		}

		// 保存操作
		if (save && StringUtils.isBlank(errorMsg)) {
			try {
				// 获取用户的学校信息
				UserVo user = null;
				try {
					user = SiteUtils.checkTeacher(request);
				} catch (LoginException e) {
					errorMsg = "未登录！不允许此";
					logger.error(e); e.printStackTrace();
				}
				CourseVo courseVo = new CourseVo();
				courseVo.setCourseName(courseName);
				courseVo.setLiveType(Integer.parseInt(type));
				courseVo.setIntroduction(introduction);
				courseVo.setScoreMethod(scoreMethod);
				courseVo.setCredit(Float.parseFloat(credit));
				courseVo.setPrice(Float.parseFloat(price));
				courseVo.setIsPublic("1");
				courseVo.setUserId(user.getUserId());
				courseVo.setTitlePage(titlePage);
				courseVo.setPreviewUrl(previewUrl);
				SchoolVo schoolVo = schoolService.getSchoolByUserId(user.getUserId());
				courseVo.setSchoolID(schoolVo.getSchId());
				courseVo.setStatus(1);
				// 上传文件操作
				List<ResourceVo> list = new ArrayList<ResourceVo>();
				ResourceVo resourceVo = null;
				if (!StringUtils.isBlank(titlePage)) {
					resourceVo = new ResourceVo();
					resourceVo.setDataUrl(titlePage);
					resourceVo.setDataIsdownload("1");
					resourceVo.setSourceType("2");
					list.add(resourceVo);
				}
				if (!StringUtils.isBlank(previewUrl)) {
					resourceVo = new ResourceVo();
					resourceVo.setDataUrl(previewUrl);
					resourceVo.setResourseName(resourceName);
					resourceVo.setSourceType("1");
					resourceVo.setDataIsdownload("1");
					list.add(resourceVo);
				}
				List<CourseJobVo> list2 = new ArrayList<CourseJobVo>();
				if (null != job && job.length > 0) {
					// 批量绑定课程与岗位
					for (String str : job) {
						CourseJobVo courseJobVo = new CourseJobVo();
						courseJobVo.setJobID(Integer.parseInt(str));
						list2.add(courseJobVo);
					}
				}
				// 如果step1CourseId为空，表示是第一次编辑，保存课程信息
				if (StringUtils.isBlank(step1CourseId) || "0".equals(step1CourseId)) {
					courseVo = courseService.createOrUpadteCourse(courseVo, list, list2, true);
				} else {
					courseVo.setCourseId(Integer.parseInt(step1CourseId));
					courseVo = courseService.createOrUpadteCourse(courseVo, list, list2, false);
				}
				respJsonObject.put("courseId", courseVo.getCourseId());
			} catch (BaseException e) {
				if (e instanceof SystemDBException) {
					errorMsg = "保存课程信息异常！";
				} else {
					errorMsg = e.getMessage();
				}
				logger.info(e.getMessage());
			} catch (Exception e) {
				errorMsg = "保存课程信息异常！";
				logger.error(e); e.printStackTrace();
			}

		}
		logger.info("上传课件第一步：errorMsg=" + errorMsg);

		respJsonObject.put("errorMsg", errorMsg);
		ResponseUtils.renderJson(response, respJsonObject.toString());

	}

	/**
	 * 
	 * 功能描述：校验课程是否重复
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午5:15:21
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/checkCourse.ajax")
	public void teacherCenterCheckCourse(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String courseName = RequestUtils.getQueryParam(request, "courseName");
		SiteUtils.checkTeacher(request);
		boolean result = false;
		if (!StringUtils.isBlank(courseName)) {
			CourseVo courseVo = new CourseVo();
			courseVo.setCourseName(courseName.trim());
			try {
				List<CourseVo> list = courseService.getCourseList(courseVo);
				if (null != list && list.size() > 0) {
					result = true;
				}
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：上传第一步，提交
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 下午3:46:09
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/saveStep2/")
	public void teacherCenterSaveStep2(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {

		String[] section = RequestUtils.getQueryParamValues(request, "section");// 章信息列表
																				// ，值的结构(章顺序：节名称名称)
		String[] part = RequestUtils.getQueryParamValues(request, "part");// 节信息列表，值的结构
																			// (章顺序：节顺序：节名称名称)
		String step2CourseId = RequestUtils.getQueryParam(request, "step2CourseId");// 课程Id
		SiteUtils.checkTeacher(request);
		boolean result = false;
		if (!(StringUtils.isBlank(step2CourseId) || !StrUtils.isNum(step2CourseId) || null == section || null == part || section.length < 1 || part.length < 1)) {

			int courseId = Integer.parseInt(step2CourseId);
			// 章的list
			List<SectionVO> sectionList = new ArrayList<SectionVO>();

			CourseVo courseVo = new CourseVo();
			try {
				courseVo = courseService.getCourseInfoById(Integer.parseInt(step2CourseId));
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				courseVo = new CourseVo();
				logger.error(e); e.printStackTrace();
			}
			// 组装章信息
			for (String string : section) {
				// 校验数据的合法性
				if (string.contains(":")) {
					String str[] = string.split("\\:");
					// 校验数据的合法性
					if (str.length == 2) {
						SectionVO sectionVo = new SectionVO();
						sectionVo.setList(new ArrayList<SectionVO>());
						sectionVo.setName(str[1]);
						sectionVo.setCourseId(courseId);
						sectionVo.setPid(courseId);
						sectionVo.setSequence(Integer.parseInt(str[0]));
						sectionVo.setType(String.valueOf(courseVo.getLiveType()));
						try {
							sectionVo.setcTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
							sectionVo.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
						} catch (ParseException e) {
							logger.error(e); e.printStackTrace();
						}
						sectionList.add(sectionVo);
					}

				}

			}

			// 将节信息组装至章
			for (String string : part) {
				// 校验数据的合法性
				if (string.contains(":")) {
					String str[] = string.split("\\:");
					// 校验数据的合法性
					if (str.length == 3) {
						SectionVO sectionVo = new SectionVO();
						sectionVo.setName(str[2]);
						sectionVo.setCourseId(courseId);
						sectionVo.setSequence(Integer.parseInt(str[1]));
						sectionVo.setType(String.valueOf(courseVo.getLiveType()));
						try {
							sectionVo.setcTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
							sectionVo.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
						} catch (ParseException e) {
							logger.error(e); e.printStackTrace();
						}
						sectionList.get(Integer.parseInt(str[0]) - 1).getList().add(sectionVo);
					}

				}

			}
			if (sectionList.size() > 0) {
				try {
					courseService.saveBatchSection(sectionList);
					result = true;
				} catch (BaseException e) {
					logger.error("保存章节信息失败:"+e);
				}
			}

		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：上传第三步右边页面
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-17 下午1:10:32
	 *         </p>
	 * 
	 * @param request
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/upload/step3/getRight/")
	public String saveStep3GetRigh(HttpServletRequest request, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		if (!StringUtils.isBlank(sectionId) && StrUtils.isNum(sectionId)) {
			SectionVO sectionVO;
			try {
				sectionVO = courseService.getSectionById(Integer.parseInt(sectionId));
				ResourceVo resourceVo = new ResourceVo();
				resourceVo.setCourseID(Integer.parseInt(courseId));
				resourceVo.setSecID(Integer.parseInt(sectionId));
				resourceVo.setSourceType("2");
				List<ResourceVo> resList = courseService.getResourceList(resourceVo);
				modelMap.put("resList", resList);
				modelMap.put("section", sectionVO);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.equals("get section info:" + e.getMessage());
			}

		}

		return "teacenter/upload/step3_right.htm";
	}

	/**
	 * 
	 * 功能描述：上传课件第三步，保存操作
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-2-12 下午4:08:41
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/upload/saveStep3/")
	public void teacherCenterSaveStep3(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
		String step3CourseId = RequestUtils.getQueryParam(request, "step3CourseId");
		String coreKnowledge = RequestUtils.getQueryParam(request, "coreKnowledge");
		String videoUrl = RequestUtils.getQueryParam(request, "videoUrl");
		String[] aidResource = RequestUtils.getQueryParamValues(request, "aidResource");
		String[] aidResourceName = RequestUtils.getQueryParamValues(request, "aidResourceName");
		String videoResourceName = RequestUtils.getQueryParam(request, "videoResourceName");
		
		SiteUtils.checkTeacher(request);
		boolean result = true;
		String errorMsg = "";
		if (StringUtils.isBlank(coreKnowledge)) {
			result = false;
			errorMsg = "核心知识点不允许为空！";
		}else if (coreKnowledge.length()>500) {
			result = false;
			errorMsg = "核心知识点最大为500字！";
		} 
		
		if (result&&!StringUtils.isBlank(sectionId) && StrUtils.isNum(sectionId) && !StringUtils.isBlank(step3CourseId)
				&& StrUtils.isNum(step3CourseId)) {
			if (!StringUtils.isBlank(coreKnowledge) && coreKnowledge.length() <= 500) {
				SectionVO sectionVO = new SectionVO();
				sectionVO.setId(Integer.parseInt(sectionId));
				sectionVO.setCoreKnowledge(coreKnowledge);
				try {
					courseService.updateSection(sectionVO);
				} catch (BaseException e) {
					logger.info("update section info:" + e.getMessage());
				}

			}
			if (!StringUtils.isBlank(videoUrl)) {
				SectionVO sectionVO = new SectionVO();
				sectionVO.setId(Integer.parseInt(sectionId));
				sectionVO.setSecUrl(videoUrl);
				try {
					courseService.updateSection(sectionVO);
					ResourceVo resourceVo = new ResourceVo();
					resourceVo.setCourseID(Integer.parseInt(step3CourseId));
					resourceVo.setSecID(Integer.parseInt(sectionId));
					resourceVo.setDataUrl(videoUrl);
					resourceVo.setResourseName(videoResourceName);
					resourceVo.setDataIsdownload("1");
					resourceVo.setSourceType("1");
					resourceVo = courseService.addResource(resourceVo);
					courseService.addBindCourRescource(resourceVo);

				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
			}

			// 辅助资源
			if (null != aidResource && aidResource.length > 0) {
				for (int i = 0; i < aidResource.length; i++) {
					ResourceVo resourceVo = new ResourceVo();
					resourceVo.setCourseID(Integer.parseInt(step3CourseId));
					resourceVo.setSecID(Integer.parseInt(sectionId));
					resourceVo.setDataUrl(aidResource[i]);
					if (aidResourceName.length==aidResource.length) {
						resourceVo.setResourseName(aidResourceName[i]);
					}
					resourceVo.setDataIsdownload("2");
					resourceVo.setSourceType("2");
					
					// 增加资源
					try {
						resourceVo = courseService.addResource(resourceVo);
						// 绑定资源
						courseService.addBindCourRescource(resourceVo);
					} catch (BaseException e) {
						logger.error(e); e.printStackTrace();
					}

				}
			}
		}
		JSONObject jObject = new JSONObject();
		jObject.put("result", result);
		jObject.put("errorMsg", errorMsg);
		ResponseUtils.renderJson(response, jObject.toString());

	}

	/**
	 * 
	 * 功能描述：校验第三步(检查所有的节是否已编辑核心知识点)
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-2-12 上午11:28:32
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping("/teacherCenter/upload/checkStep3/")
	public void checkStep3(HttpServletRequest request, HttpServletResponse response) {
		String courseId = RequestUtils.getQueryParam(request, "courseId");

		boolean result = true;
		try {
			int count = courseService.getSectionNotCompleteCount(Integer.parseInt(courseId));
			if (count > 0) {
				result = false;
			}
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：资源删除
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-18 上午9:56:21
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 * @throws LoginException
	 */
	@RequestMapping(value = "/teacherCenter/upload/step3/deleteResource/", method = RequestMethod.POST)
	public void deleteResource(HttpServletRequest request, HttpServletResponse response) throws LoginException {
		String resourceId = RequestUtils.getQueryParam(request, "resourceId");
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		boolean result = false;
		SiteUtils.checkTeacher(request);
		if (!StringUtils.isBlank(resourceId) && !StringUtils.isBlank(sectionId) && !StringUtils.isBlank(courseId)
				&& StrUtils.isNum(resourceId) && StrUtils.isNum(sectionId)) {
			ResourceVo resourceVo = new ResourceVo();
			resourceVo.setCourseID(Integer.parseInt(courseId));
			resourceVo.setSecID(Integer.parseInt(sectionId));
			resourceVo.setReSourceID(Integer.parseInt(resourceId));
			try {
				courseService.deleteBindCourRescource(resourceVo, true);
				result = true;
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：修改课程
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-20 上午10:40:14
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 * @throws LoginException
	 */
	@RequestMapping(value = "/teacherCenter/upload/step4/updateCourseStatus/", method = RequestMethod.POST)
	public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response) throws LoginException {
		String courseId = RequestUtils.getQueryParam(request, "courseId");
		String status = RequestUtils.getQueryParam(request, "status");
		boolean result = false;
		SiteUtils.checkTeacher(request);
		if (!StringUtils.isBlank(courseId) && !StringUtils.isBlank(status) && StrUtils.isNum(courseId) && StrUtils.isNum(status)) {

			CourseVo courseVo = new CourseVo();
			courseVo.setCourseId(Integer.parseInt(courseId));
			courseVo.setStatus(Integer.parseInt(status));
			try {
				courseService.updateCourse(courseVo);
				result = true;
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：教师中心直播课程页面
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年1月20日 上午11:25:00
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/tolivebroad/")
	public String toListBroad(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo userVo = SiteUtils.checkTeacher(request);
		SiteUtils.checkTeacher(request);
		CourseVo courseVo = new CourseVo();
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		PageModel pageModel = null;
		try {
			courseVo.setUserId(userVo.getUserId());
			courseVo.setLiveType(new Integer(1));
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			pageModel = courseService.getCourseTab(courseVo, Integer.parseInt(pageNo), 5);
			// modelMap.put("courList", courList);
		} catch (LoginException e) {
			logger.error(e); e.printStackTrace();
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("pageModel", pageModel == null ? new PageModel() : pageModel);
		return "teacenter/livebroad/livebroad.htm";
	}

	/**
	 * 
	 * 功能描述：教师中心点播课程页面
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年1月21日 下午1:46:01
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/todemandbroad/")
	public String toDemandBroad(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo userVo = SiteUtils.checkTeacher(request);
		CourseVo courseVo = new CourseVo();
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		PageModel pageModel = null;
		try {
			courseVo.setUserId(userVo.getUserId());
			courseVo.setLiveType(new Integer(2));
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			pageModel = courseService.getCourseTab(courseVo, Integer.parseInt(pageNo), 5);
			// modelMap.put("courList", courList);
		} catch (LoginException e) {
			logger.error(e); e.printStackTrace();
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("pageModel", pageModel == null ? new PageModel() : pageModel);
		return "teacenter/demandbroad/demandbroad.htm";
	}

	/**
	 * 
	 * 功能描述：教师中心练习管理
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年1月22日 下午1:55:51
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
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/practiceManagement/")
	public String toPracticeManagement(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo userVo = SiteUtils.checkTeacher(request);
		CourseVo courseVo = new CourseVo();
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		PageModel pageModel = null;
		try {
			courseVo.setUserId(userVo.getUserId());
			courseVo.setStatus(CourseStateEnum.PUBLISHED.getValue()); // 设置课程为已发布课程
			if (StringUtils.isBlank(pageNo)) {
				pageNo = "1";
			}
			// 设置examType=2 即为考试类型为练习
			// 设置paperState=0 即练习不需要批阅
			pageModel = courseService.getCourseTab(courseVo, Integer.parseInt(pageNo), 3, 2, 0);
		} catch (LoginException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("pageModel", pageModel == null ? new PageModel() : pageModel);
		return "teacenter/practice/practicemanagement.htm";
	}

	/**
	 * 
	 * 功能描述：跳转教师中心 (作业/考试) 管理页面
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月10日 下午2:22:05
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/toManagement/")
	public String toManagement(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		// 检验是否登陆且为教师身份
		SiteUtils.checkTeacher(request);
		int examType = Integer.parseInt(RequestUtils.getQueryParam(request, "examType"));

		if (examType < 0) {
			throw new BaseException("parameter error!");
		}

		if (examType == 1) {
			return "teacenter/exam/exammanagement.htm";
		} else if (examType == 3) {
			return "teacenter/operation/operationmanagement.htm";
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 功能描述：教师中心 (作业/考试) 管理
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年1月24日 上午10:48:01
	 *         </p>
	 * 
	 * @param examType
	 *            考试类型
	 * @param paperState
	 *            批阅
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/Management/")
	public String management(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		// 检验是否登陆且为教师身份
		UserVo userVo = SiteUtils.checkTeacher(request);
		CourseVo courseVo = new CourseVo();
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		int examType = Integer.parseInt(RequestUtils.getQueryParam(request, "examType"));
		int paperState = Integer.parseInt(RequestUtils.getQueryParam(request, "paperState"));
		PageModel pageModel = null;

		if (examType > 0 && paperState > 0) {
			try {
				courseVo.setUserId(userVo.getUserId());
				courseVo.setStatus(CourseStateEnum.PUBLISHED.getValue()); // 设置课程为已发布课程
				if (StringUtils.isBlank(pageNo)) {
					pageNo = "1";
				}
				pageModel = courseService.getCourseTab(courseVo, Integer.parseInt(pageNo), 3, examType, paperState);
			} catch (LoginException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		modelMap.put("pageModel", pageModel == null ? new PageModel() : pageModel);
		modelMap.put("paperState", paperState);
		if (examType == 1) {
			return "teacenter/exam/examcontent.htm";
		} else if (examType == 3) {
			return "teacenter/operation/operationcontent.htm";
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 功能描述：顯示教師編輯頁面
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-1-21 上午11:04:14
	 *         </p>
	 * 
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teachercenter/teacherinforedit/")
	public String teacherEditPageShow(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo userVo = SiteUtils.checkTeacher(request);
		// 獲取傳過來的ID值

		// 通過ID找到相關的數據并將數據返回到界面中
		TeacherVo teacherVo = new TeacherVo();
		try {
			teacherVo = teacherService.getTeacherByUserId(userVo.getUserId());
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		// 获取用户表信息

		try {
			userVo = userService.getUserById(userVo.getUserId());
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		// 获取老师的行业信息
		StringBuffer teachershineStr=new StringBuffer("已选择领域: ");
		StringBuffer teachershineIdStr=new StringBuffer("");
		
			List<TeacherShineVo> list=new ArrayList<TeacherShineVo>();
			list=teacherShineService.getTeacherShineByUserId(userVo.getUserId());
			for (TeacherShineVo vo : list) {
				if (StringUtils.isBlank(vo.getProfessionName())) {
					vo.setProfessionName("");
				}
				if (StringUtils.isBlank(vo.getJobName())) {
					vo.setJobName("");
				}
				teachershineIdStr.append(vo.getProid()+"|"+vo.getJobid()+",");
				teachershineStr.append(vo.getJobName()+", ");
			}
		if (teacherVo != null && userVo != null) {
			modelMap.put("teachershineStr", teachershineStr.toString());
			modelMap.put("teachershineIdStr", teachershineIdStr.toString());
			modelMap.put("userModel", userVo);
			modelMap.put("teacherModel", teacherVo);
		}
		return "teacenter/teacheredit/teacherinfo.htm";
	}

	/**
	 * 
	 * 功能描述：更新保存教师信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-1-21 下午6:47:16
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(dongs，2014-2-21 09:32:48，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping(value = "/teachercenter/saveteacherInfor/")
	public void saveTeacherInfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		// 获取界面上显示的数据
		String hduserId = request.getParameter("hduserId");
		String hdteacherId = request.getParameter("hdteacherId");
		String teacherShineStr=request.getParameter("jobGroupIdsInput");
		String userName = request.getParameter("uname");
		String introduce = request.getParameter("introduce");
		String userMobile = request.getParameter("userMobile");
		String userEmail = request.getParameter("userEmail");
		String school = request.getParameter("school");
		String teacherLevel = request.getParameter("teacherLevel");
		String major = request.getParameter("major");
		// 数据验证
		String errorMsg = null;
		boolean result = true;
		JSONObject json = new JSONObject();
		UserVo userMode = null;
		TeacherVo teacherMode = null;
		if (StringUtils.isBlank(userName)) {
			result = false;
			errorMsg = "用户名不能为空!";
		}
		if (!StringUtils.isBlank(userMobile) && !StringUtils.isNumeric(userMobile)) {
			result = false;
			errorMsg = "手机号码格式不正确!";
		}
		if (StringUtils.isBlank(userEmail)) {
			result = false;
			errorMsg = "用户邮箱地址不能为空!";
		}
		if (StringUtils.isBlank(school)) {
			result = false;
			errorMsg = "老师的就职学校不能为空!";
		}
		if (StringUtils.isBlank(teacherLevel)) {
			result = false;
			errorMsg = "老师的职称不能为空!";
		}
		if (StringUtils.isBlank(major)) {
			result = false;
			errorMsg = "专业不能为空!";
		}
		if (introduce.length() > 500) {
			result = false;
			errorMsg = "个人介绍长度不能大于500!";
		}
		if (StringUtils.isBlank(teacherShineStr)) {
			errorMsg = "擅长领域不能为空";
			result = false;
		}
		String[] teacherShines=teacherShineStr.split(","); 
		if (teacherShines.length>3 || teacherShines.length<=0) {
			errorMsg = "请选择1到3个擅长领域";
			result = false;
		}
		if (StringUtils.isBlank(errorMsg)) {
			// 写入到数据库中
			// 更新用户表

			try {
				userMode = userService.getUserById(Integer.parseInt(hduserId));
			} catch (NumberFormatException e) {
				result = false;
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				result = false;
				logger.error(e); e.printStackTrace();
			}
			if (userMode != null) {
				userMode.setUserName(userName);
				userMode.setUserMobile(userMobile);
				userMode.setUserEmail(userEmail);
				try {
					int intFlag = userService.updateUser(userMode);
					if (intFlag > 0) {
						result = true;
						json.put("userMode", userMode);
					}
				} catch (BaseException e) {
					result = false;
					logger.error(e); e.printStackTrace();
				}
			} else {
				result = false;
			}
			// 更新老师表

			try {
				teacherMode = teacherService.getTeacherById(Integer.parseInt(hdteacherId));
			} catch (NumberFormatException e) {
				result = false;
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				result = false;
				logger.error(e); e.printStackTrace();
			}
			if (teacherMode != null) {

				teacherMode.setUserId(Integer.parseInt(hduserId));
				teacherMode.setMajor(major);
				teacherMode.setTeacherLevel(teacherLevel);
				teacherMode.setSchool(school);
				teacherMode.setIntroduce(introduce);
				try {
					int intFlagT = teacherService.updateTeacher(teacherMode);
					if (intFlagT > 0) {
						result = true;
					}
				} catch (BaseException e) {
					result = false;
					logger.error(e); e.printStackTrace();
				}

			} else {
				result = false;
			}
			
			// 更新擅长领域表
			try {
				// 删除所有的教师擅长领域
				teacherShineService.deleteTeacherShineInfor(Integer.parseInt(hduserId));
			} catch (NumberFormatException e) {
				result = false;
				errorMsg = "删除擅长领域出错";
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				result = false;
				errorMsg = "删除擅长领域出错";
				logger.error(e); e.printStackTrace();
			}
			// 保存教师的擅长领域
			TeacherShineVo teacherShineVo =null;
			List<TeacherShineVo> teacherShineList =new ArrayList<TeacherShineVo>();
			for (int i = 0; i < teacherShines.length; i++) {
				teacherShineVo = new TeacherShineVo();
				String[] strpAndj = teacherShines[i].split("\\|");
				teacherShineVo.setProid(Integer.parseInt(strpAndj[0]));
				teacherShineVo.setJobid(Integer.parseInt(strpAndj[1]));
				teacherShineVo.setUserid(Integer.parseInt(hduserId));
				teacherShineList.add(i, teacherShineVo);
			}
			try {	
				int intFlag = teacherShineService.addTeacherShine(teacherShineList);
				if (intFlag > 0) {
					result = true;
				} else {
					result = false;
					errorMsg = "保存擅长领域出错";
				}
			} catch (Exception e) {
				// TODO: handle exception
				result=false;
				logger.error(e); e.printStackTrace();
			}
		}
	
		json.put("errorMsg", errorMsg);
		json.put("result", result);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	/**
	 * 
	 * 功能描述：上传教师头像
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月27日 下午1:35:04</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/teachercenter/uploadTeacherImage/")
	public String uploadTeacherImage(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)throws LoginException{
		UserVo userVo=SiteUtils.getUser(request);
		int teacherId=userVo.getUserId();
    	int result=0;
    	MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
    	MultipartFile multipartFile=multipartRequest.getFile("imagefile");

    	if (!StringUtils.isBlank(multipartFile.getOriginalFilename())) {
    		String teacherImgRoot = MessageResolver.getMessage(multipartRequest, "teacherImgRoot", null);
    		String imageRoot = MessageResolver.getMessage(multipartRequest, "imageRoot", null);
    		String imageServerIp = MessageResolver.getMessage(multipartRequest, "imageServerIp", null);
    		String imageServerName = MessageResolver.getMessage(multipartRequest, "imageServerName", null);
    		String imageServerPassword = MessageResolver.getMessage(multipartRequest, "imageServerPassword", null);
    		String filePath = "smb://"+imageServerIp+imageRoot+teacherImgRoot+ teacherId+"/";
    		String fileName = UploadUtils.generateFilename("", FileUtils.getFileType(multipartFile.getOriginalFilename()));
			try {
				 fileRepository.storeByRmoeteFilename(imageServerIp, imageServerName, imageServerPassword, filePath.trim(),
						 fileName, multipartFile);
				 fileName=teacherImgRoot+ teacherId+"/"+fileName;
				modelMap.put("userHeadPic", fileName);
				result=1;  //上传成功
			} catch (IOException e) {
				logger.error(e); e.printStackTrace();
			}
		}
    	modelMap.put("result", result);
    	return "teacenter/teacheredit/uOk.htm";
	}

	/**
	 * 
	 * 功能描述：更新教师头像信息
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月27日 下午2:04:59</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/teachercenter/updateteacherimage/")
	public void upLoadTeacherInfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String messageString="";
  	  	boolean result=false;
  	  	String image = request.getParameter("imageUrl");
  	  	UserVo userVo = SiteUtils.getUser(request);;

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
	 * 功能描述：
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 上午10:04:43
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping(value = "/teachercenter/getteacherimage/", method = RequestMethod.POST)
	public void getTeacherImage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo user = new UserVo();
		// 获取用户图片信息
		try {
			// user= userService.getUserById(Integer.parseInt("1"));
			UserVo uservo = SiteUtils.getUser(request);
			user = userService.getUserById(uservo.getUserId());
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		String flag = "error";
		if (user != null) {
			flag = "right";
		}
		// modelMap.put("flag", flag);
		// modelMap.put("userHeadPic", user.getUserHeadPic());
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		json.put("userHeadPic", user.getUserHeadPic());
		ResponseUtils.renderJson(response, json.toString());
	}

	/**
	 * 
	 * 功能描述：教师密码修改
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-1-26 上午10:15:23
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws BaseException
	 * @throws IOException
	 */
	@RequestMapping("/teacherCenter/updatepwd/")
	public void resetpwd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws NoSuchAlgorithmException,
			BaseException, IOException {
		String oldpwd = RequestUtils.getQueryParam(request, "oldpwd");
		String newpwd = RequestUtils.getQueryParam(request, "newpwd");
		String flag = null;
		String oldpwdMd5 = new Md5PwdEncoder().encodePassword(oldpwd);
		String newpwdMd5 = new Md5PwdEncoder().encodePassword(newpwd);
		UserVo user = SiteUtils.getUser(request);
		try {
			user = userService.getUserById(user.getUserId());
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		String userpwd = user.getPassword();
		// String userpwdMd5 = PwdEncoderUtils.EncoderPwdByMd5(userpwd);
		if (oldpwdMd5 != null && !oldpwdMd5.equals("")) {
			if (!oldpwdMd5.equals(userpwd)) {
				flag = "false";
			} else {
				if (newpwdMd5 != null && !newpwdMd5.equals("")) {
					int userId = user.getUserId();
					user.setPassword(newpwdMd5);
					user.setUserId(userId);
					studentcenterService.updateUser(user);
					flag = "true";
				}
			}
		}

		response.getWriter().append(flag);
	}

	/**
	 * 
	 * 功能描述：安装要求跳转到首页
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-1-23 下午3:57:38
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teachercenter/loadmain/{strFlag}")
	public String teacherCenterloadmain(@PathVariable String strFlag, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		if (!StringUtils.isBlank(strFlag) && StrUtils.isNum(strFlag)) {
			modelMap.put("OPtionType", strFlag);
		}
		// 读取图片信息显示到页面上
		return "teacenter/main.htm";
	}

	// -------------------老师答疑管理------------------------------------------------
	/**
	 * 
	 * 功能描述：老师答疑管理未解答
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-24
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/teacherCenter/unanswered/")
	public String unanswered(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo user = SiteUtils.checkTeacher(request);
		// TeacherVo teacher = null;
		// try {
		// teacher = teacherService.getTeacherByUserId(user.getUserId());
		// } catch (BaseException e) {
		// logger.error(e); e.printStackTrace();
		// }
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		PageModel pageModel = new PageModel();
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		int qaQuestionNum = 0;
		int courseQuestionNum = 0;
		int quesAddCout = 0;
		QuestionUserVo questionUserVo = new QuestionUserVo();
		// questionUserVo.setTeacherID(teacher.getId());
		questionUserVo.setTeacherID(user.getUserId());
		questionUserVo.setStatus(0);
		try {
			qaQuestionNum = teacherService.countQuestionPageModel(questionUserVo, 0);
			courseQuestionNum = teacherService.countQuestionPageModel(questionUserVo, 1);
			// 追问数量
			quesAddCout = teacherService.quesAddCout(questionUserVo);
			pageModel = teacherService.getQuestionPageModel(questionUserVo, 0, Integer.valueOf(pageNo), 5);

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("qaQuestionNum", qaQuestionNum);
		modelMap.put("courseQuestionNum", courseQuestionNum);
		modelMap.put("quesAddCout", quesAddCout);
		modelMap.put("type", 0);
		modelMap.put("pageModel", pageModel);
		return "teacenter/question/questionList.htm";
	}

	/**
	 * 功能描述：教师中心未解答答疑问题和课程问题的分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-2-14 上午10:04:57
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param type
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/unanswered/getPageOfQuestion/{type}/")
	public String toPageOfUnansweredQuestion(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@PathVariable int type) throws LoginException {
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		UserVo user = SiteUtils.checkTeacher(request);
		// TeacherVo teacher = null;
		// try {
		// teacher = teacherService.getTeacherByUserId(user.getUserId());
		// } catch (BaseException e) {
		// logger.error(e); e.printStackTrace();
		// }
		PageModel pageModel = new PageModel();
		QuestionUserVo questionUserVo = new QuestionUserVo();
		// questionUserVo.setTeacherID(teacher.getId());
		questionUserVo.setTeacherID(user.getUserId());
		questionUserVo.setStatus(0);
		if (type != 0 && type != 1) {
			type = 2;
		}
		try {
			if (type == 2) {
				pageModel = teacherService.getQuestionAddPageModel(questionUserVo, Integer.valueOf(pageNo), 5);
			} else {
				pageModel = teacherService.getQuestionPageModel(questionUserVo, type, Integer.valueOf(pageNo), 5);
			}
			modelMap.put("pageModel", pageModel);
			modelMap.put("type", type);
			return "teacenter/question/unsolvedques.htm";
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * 功能描述：老师答疑管理已解答
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-24
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/teacherCenter/answered/")
	public String answered(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {

		UserVo user = SiteUtils.checkTeacher(request);
		// TeacherVo teacher = null;
		// try {
		// teacher = teacherService.getTeacherByUserId(user.getUserId());
		// } catch (BaseException e) {
		// logger.error(e); e.printStackTrace();
		// }
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		PageModel pageModel = new PageModel();
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		QuestionUserVo questionUserVo = new QuestionUserVo();
		// questionUserVo.setTeacherID(teacher.getId());
		questionUserVo.setTeacherID(user.getUserId());
		questionUserVo.setStatus(1);
		try {
			pageModel = teacherService.getQuestionPageModel(questionUserVo, 0, Integer.valueOf(pageNo), 5);

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("type", 0);
		modelMap.put("pageModel", pageModel);
		return "teacenter/question/answerList.htm";
	}

	/**
	 * 功能描述：获取教师中心已解答各类问题的分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-2-14 上午10:03:58
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param type
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/answered/getPageOfQuestion/{type}/")
	public String toPageOfAnsweredQuestion(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@PathVariable int type) throws LoginException {
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		UserVo user = SiteUtils.checkTeacher(request);
		// TeacherVo teacher = null;
		// try {
		// teacher = teacherService.getTeacherByUserId(user.getUserId());
		// } catch (BaseException e) {
		// logger.error(e); e.printStackTrace();
		// }
		PageModel pageModel = new PageModel();
		QuestionUserVo questionUserVo = new QuestionUserVo();
		// questionUserVo.setTeacherID(teacher.getId());
		questionUserVo.setTeacherID(user.getUserId());
		questionUserVo.setStatus(1);
		if (type != 0 && type != 1) {
			type = 2;
		}
		try {
			pageModel = teacherService.getQuestionPageModel(questionUserVo, type, Integer.valueOf(pageNo), 5);
			modelMap.put("pageModel", pageModel);
			modelMap.put("type", type);
			return "teacenter/question/solvedques.htm";

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
			return null;
		}

	}

	/**
	 * 功能描述：教师中心已解答问题,展开问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年2月19日 下午7:37:50
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/answered/asyncGetAnswer/")
	public void asyncGetAnswerOfTeacherOfQuestion(HttpServletRequest request, HttpServletResponse response, ModelMap map)
			throws LoginException {
		String quesId = RequestUtils.getQueryParam(request, "quesId");
		UserVo user = SiteUtils.checkTeacher(request);
		AnswerVo answerVo = null;
		if (StringUtils.isBlank(quesId)) {
			quesId = "0";
		}
		try {
			answerVo = questionCenterService.getAnswerVoByQuesIdUserId(Integer.valueOf(quesId), user.getUserId());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("answContent", answerVo.getAnswContent());
		jsonObject.put("answCtime", DateUtils.format(answerVo.getAnswCtime(), "yyyy-MM-dd HH:mm"));
		ResponseUtils.renderJson(response, jsonObject.toString());

	}

	/**
	 * 
	 * 功能描述：老师答疑管理--老师解答入口
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-24
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/teacherCenter/answer/")
	public String answer(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String quesId = RequestUtils.getQueryParam(request, "quesId");
		UserVo teacherUser = SiteUtils.checkTeacher(request);
		UserVo askUser = null;
		QuestionVo questionVo = null;
		if (StringUtils.isBlank(quesId)) {
			quesId = "0";
		}
		try {
			questionVo = questionCenterService.getQuestionVo(Integer.valueOf(quesId));
			if (questionVo != null) {
				askUser = userService.getUserById(questionVo.getUserID());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}

		modelMap.put("question", questionVo);
		modelMap.put("askUser", askUser);
		// 读取图片信息显示到页面上
		return "teacenter/question/answer.htm";
	}

	/**
	 * 
	 * 功能描述：老师答疑管理--老师追问解答入口
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/teacherCenter/answerAdd/")
	public String answerAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String quesAddId = RequestUtils.getQueryParam(request, "quesAddId");
		UserVo teacherUser = SiteUtils.checkTeacher(request);
		UserVo askUser = new UserVo();
		QuestionAddVo questionAddVo = new QuestionAddVo();
		if (StringUtils.isBlank(quesAddId)) {
			quesAddId = "0";
		}
		try {
			questionAddVo = teacherService.getQuestionAddVo(Integer.valueOf(quesAddId));
			if (questionAddVo != null) {
				askUser = userService.getUserById(questionAddVo.getUserID());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}

		modelMap.put("question", questionAddVo);
		modelMap.put("askUser", askUser);
		// 读取图片信息显示到页面上
		return "teacenter/question/answer.htm";
	}

	/**
	 * 
	 * 功能描述：获取没有被选择的领域信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 下午3:28:15
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teachercenter/getnochooseinfor/")
	public void GetNoChooseInfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		// 获取选择的领域信息ID
		List<TeacherShineVo> shineList = null;
		UserVo loginUserVo = SiteUtils.checkTeacher(request);
		shineList = teacherShineService.getTeacherShineByUserId(loginUserVo.getUserId());
		// 获取没有选择的领域信息
		List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
		try {
			professionList = professionService.getProfessionList();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		int proID = 0;
		StringBuilder st = new StringBuilder();
		st.append("{\"result\":[");
		for (ProfessionVo val : professionList) {
			proID = val.getProID();
			List<JobVo> jobGroupList = null;
			try {
				jobGroupList = jobService.getJobGroupByProid(proID);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

			for (JobVo valjob : jobGroupList) {
				int intFlag = 0;
				for (TeacherShineVo shineVal : shineList) {
					if ((shineVal.getProid() + "&" + shineVal.getJobid()).equals(String.valueOf(val.getProID()) + "&"
							+ String.valueOf(valjob.getJobID()))) {
						intFlag = 1;
						break;
					}
				}
				if (intFlag == 1) {
					continue;
				}
				st.append("{\"id\":\"" + String.valueOf(val.getProID()) + "&" + String.valueOf(valjob.getJobID()) + "\"");
				st.append(",\"name\":\"" + val.getProName() + "/" + valjob.getJobName() + "\"},");
				resultMap.put(String.valueOf(val.getProID()) + "&" + String.valueOf(valjob.getJobID()),
						val.getProName() + "/" + valjob.getJobName());
			}
		}
		st.append("]}");
		// 去除最后一个，
		if (st.toString().equals("{\"result\":[]}")) {
			ResponseUtils.renderJson(response, "{\"result\":\"null\"}");
		} else {

			String stResult = st.toString().replace("},]}", "}]}");
			ResponseUtils.renderJson(response, stResult);
		}
	}

	/**
	 * 
	 * 功能描述：获取被选择的领域信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 下午3:28:15
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teachercenter/getchooseinfor/")
	public void GetChooseInfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		// 获取选择的领域信息ID
		List<TeacherShineVo> shineList = null;
		UserVo loginUserVo = SiteUtils.checkTeacher(request);
		shineList = teacherShineService.getTeacherShineByUserId(loginUserVo.getUserId());
		// 获取没有选择的领域信息
		List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
		try {
			professionList = professionService.getProfessionList();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		int proID = 0;
		StringBuilder st = new StringBuilder();
		st.append("{\"result\":[");
		for (ProfessionVo val : professionList) {
			proID = val.getProID();
			List<JobVo> jobGroupList = null;
			try {
				jobGroupList = jobService.getJobGroupByProid(proID);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			for (JobVo valjob : jobGroupList) {
				int intFlag = 0;
				for (TeacherShineVo shineVal : shineList) {
					if ((shineVal.getProid() + "&" + shineVal.getJobid()).equals(String.valueOf(val.getProID()) + "&"
							+ String.valueOf(valjob.getJobID()))) {
						intFlag = 1;
						st.append("{\"id\":\"" + String.valueOf(val.getProID()) + "&" + String.valueOf(valjob.getJobID()) + "\"");
						st.append(",\"name\":\"" + val.getProName() + "/" + valjob.getJobName() + "\"},");
						break;
					}
				}
				if (intFlag == 1) {
					continue;
				}
			}
		}
		st.append("]}");
		// 去除最后一个，
		if (st.toString().equals("{\"result\":[]}")) {
			ResponseUtils.renderJson(response, "{\"result\":\"null\"}");
		} else {

			String stResult = st.toString().replace("},]}", "}]}");
			ResponseUtils.renderJson(response, stResult);
		}

	}

	/**
	 * 
	 * 功能描述：保存选择的领域到数据库中
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-11 下午3:32:56
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teachercenter/savechooseinfor/")
	public void SaveChooseInfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		UserVo loginUser = SiteUtils.checkTeacher(request);
		String pjid = RequestUtils.getQueryParam(request, "pjid");
		String errorMsg = null;
		boolean result = false;
		if (StringUtils.isBlank(pjid)) {
			errorMsg = "请至少选择一个擅长领域";
			result = false;
		}

		if (errorMsg == null) {

			// 删除所有的教师擅长领域
			int intFlag;
			try {
				intFlag = teacherShineService.deleteTeacherShineInfor(loginUser.getUserId());
				if (intFlag > 0) {
					result = true;
				} else {
					result = false;
					errorMsg = "删除擅长领域出错";
				}
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			

			// 保存教师的擅长领域
			TeacherShineVo teacherShineVo = new TeacherShineVo();
			List<TeacherShineVo> teacherShineList = new ArrayList();

			String[] strPjid = pjid.split(",");
			for (int i = 0; i < strPjid.length; i++) {
				teacherShineVo = new TeacherShineVo();
				String[] strPj = strPjid[i].split("&");
				teacherShineVo.setProid(Integer.parseInt(strPj[0]));
				teacherShineVo.setJobid(Integer.parseInt(strPj[1]));
				teacherShineVo.setUserid(loginUser.getUserId());
				teacherShineList.add(i, teacherShineVo);
			}
			int intFlagTwo = teacherShineService.addTeacherShine(teacherShineList);
			if (intFlagTwo > 0) {
				result = true;
			} else {
				result = false;
				errorMsg = "删除擅长领域出错";
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errorMsg", errorMsg);
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：教师中心批阅 作业/考试
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月16日 下午4:18:34
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/correctIndex/")
	public String correct(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		// 得到考试ID
		int examId = Integer.parseInt(RequestUtils.getQueryParam(request, "examId"));
		ExamVo examVo = new ExamVo();
		List<ExamStudentVo> examStudentList = null;
		if (examId > 0) {
			examVo.setExamId(examId);
			try {
				examVo = examService.getExamByExamId(examId);
				// 得到学生考试集合 取得第一位学生考试信息
				examStudentList = examService.getStuExamListByExamId(examId, 2, 1, 1);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}

		modelMap.put("examVo", examVo);
		modelMap.put("examStudentList", examStudentList);
		if (examVo.getExamType() == 1) {
			return "teacenter/correct/correctExamIndex.htm";
		} else {
			return "teacenter/correct/correctOperaIndex.htm";
		}
	}

	/**
	 * 
	 * 功能描述：教师中心 考试/作业批阅 得到学生考试信息
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月20日 上午9:53:43
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/correctIndex/getExamStu/")
	public void getMoreExamStu(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		// 得到考试ID
		int examId = Integer.parseInt(RequestUtils.getQueryParam(request, "examId"));
		int pageNo = Integer.parseInt(RequestUtils.getQueryParam(request, "pageNo"));
		int correctStatus = Integer.parseInt(RequestUtils.getQueryParam(request, "correctStatus"));
		boolean result = false;
		ExamStudentVo examStudentVo = null;
		JSONObject jsonObject = new JSONObject();
		if (pageNo > 0) {
			try {
				// 得到学生考试集合 每次只得到一个学生考试信息 pageSize=1
				List<ExamStudentVo> examStudentVos = examService.getStuExamListByExamId(examId, correctStatus, pageNo, 1);
				if (null != examStudentVos && examStudentVos.size() > 0) {
					examStudentVo = examStudentVos.get(0);
					jsonObject.put("studentName", examStudentVo.getStudentName());
					jsonObject.put("examStuId", examStudentVo.getId());
				}
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			if (null != examStudentVo)
				result = true;
		}
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述：得到学生考试试题回答
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月20日 下午1:37:58
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/correctIndex/getAnswer/")
	public String getStuExamAnswer(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		int examId = 0;
		int examStuId = 0;
		String examIds = RequestUtils.getQueryParam(request, "examId"); // 得到考试Id
		String examStuIds = RequestUtils.getQueryParam(request, "examStuId"); // 得到学生考试Id
		String correctStatus = RequestUtils.getQueryParam(request, "correctStatus");
		if (!StringUtils.isBlank(examIds) && !StringUtils.isBlank(examStuIds)) {
			examId = Integer.parseInt(examIds);
			examStuId = Integer.parseInt(examStuIds);
		}

		ExamVo examVo = new ExamVo();
		List<ExamQuestionVo> examQuestionList = null;
		ExamAnswerDetailVo examAnswerDetailVo = null;
		ExamQuestionVo examQuestionVo = new ExamQuestionVo();

		int totleScore = 0; // 试卷主观题总分
		double subAnswerScore = 0; // 学生主观题得分
		double objAnswerScore = 0; // 学生客观题得分
		double totalAnsScore = 0; // 学生试卷总得分
		if (examId > 0 && examStuId > 0) {
			examVo.setExamId(examId);
			try {
				examVo = examService.getExamByExamId(examId);
				// 根据paperId 和 试题类型=4 得到试卷主观题
				examQuestionVo.setPaperId(examVo.getPaperId());
				examQuestionVo.setExamQuesType(4);
				examQuestionList = examService.getExamQuesList(examQuestionVo);
				if (null != examQuestionList && examQuestionList.size() > 0) {
					for (ExamQuestionVo ques : examQuestionList) {
						examAnswerDetailVo = examService.getStuExamAnswer(examStuId, ques.getExamQuesId());
						// 若为已批阅，则得到该学生的得分
						if (correctStatus.equals("1")) {
							double score = examService.getStuAnsdetailScore(examAnswerDetailVo.getExamAnswerId()); // 学生该试题得分
							subAnswerScore += score; // 学生主观题得分
						}
						ques.setExamAnswerDetailVo(examAnswerDetailVo);
					}
					totleScore = examService.countExamQuesScore(examQuestionVo); // 得到主观题总分
				}
				if (correctStatus.equals("1")) {
					totalAnsScore = examService.getExamSumScore(examStuId); // 学生总得分
					objAnswerScore = totalAnsScore - subAnswerScore;
					modelMap.put("totalAnsScore", totalAnsScore);
					modelMap.put("objAnswerScore", objAnswerScore);
				}

			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}

		modelMap.put("examQuestionList", examQuestionList);
		modelMap.put("totleScore", totleScore);
		modelMap.put("subAnswerScore", subAnswerScore);
		// 若为1 则返回批阅结果查看页面 2：则返回批阅页面
		if (correctStatus.equals("1")) {
			return "teacenter/correct/correctResult.htm";
		} else {
			return "teacenter/correct/correctAnswer.htm";
		}

	}

	/**
	 * 
	 * 功能描述：保存学生作答明细分数
	 * 
	 * @author biyun.huang
	 *         <p>
	 *         创建日期 ：2014年2月20日 下午4:46:36
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 * @throws LoginException
	 */
	@RequestMapping("/teacherCenter/correctIndex/inputScore/")
	public void inputAnsDetailScore(HttpServletRequest request, HttpServletResponse response) throws LoginException {
		SiteUtils.checkTeacher(request);
		String[] score = RequestUtils.getQueryParamValues(request, "score");  
		String[] examAnsId = RequestUtils.getQueryParamValues(request, "examAnswerId");
		int examStuId = Integer.parseInt(RequestUtils.getQueryParam(request, "examStuId"));
		double totalScore = 0; // 学生作答得到的主观题总分
		double Score = 0; // 考试总分
		boolean result = false;
		if (null != score && null != examAnsId && score.length > 0 && examAnsId.length > 0) {
			try {
				for (int i = 0; i < examAnsId.length; i++) {
					int s = Integer.parseInt(score[i]);
					totalScore += s;
					examService.inputAnswerDetailScore(s, Integer.parseInt(examAnsId[i])); // 保存学生试题分数
				}
				Score = examService.getExamSumScore(examStuId); // 得到学生考试总分
				examService.correctExamStu(1, Score, examStuId); // 保存该学生考试总分
				result = true;
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("totalScore", totalScore);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 
	 * 功能描述: 查看批阅结果
	 * 
	 * @author biyun.huang
	 * <p> 创建日期 ：2014年3月7日 下午2:49:40</p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws LoginException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/teacherCenter/correctResultIndex/")
	public String showCorrect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		SiteUtils.checkTeacher(request);
		// 得到考试ID
		int examId = Integer.parseInt(RequestUtils.getQueryParam(request, "examId"));
		ExamVo examVo = new ExamVo();
		List<ExamStudentVo> examStudentList = null;
		if (examId > 0) {
			examVo.setExamId(examId);
			try {
				examVo = examService.getExamByExamId(examId);
				// 得到学生考试集合 取得第一位学生考试信息
				examStudentList = examService.getStuExamListByExamId(examId, 1, 1, 1);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}

		modelMap.put("examVo", examVo);
		modelMap.put("examStudentList", examStudentList);
		if (examVo.getExamType() == 1) {
			return "teacenter/correct/examCorrResult.htm";
		} else {
			return "teacenter/correct/operaCorrResult.htm";
		}
	}

	
	/**
	 * 
	 * 功能描述：得到未批阅数量
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月10日 下午4:51:10</p>
	 *
	 * @param request
	 * @param response
	 * @throws LoginException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/teacherCenter/correctIndex/countUnCorrect/")
	public void count(HttpServletRequest request,HttpServletResponse response)throws LoginException{
		int examId=Integer.parseInt(RequestUtils.getQueryParam(request, "examId"));
		int unCorrectCount=0;
		if(examId>0){
			try {
				unCorrectCount=examService.getStuExamCount(examId, 2);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("unCorrectCount", unCorrectCount);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}


	/**
	 * 
	 * 功能描述：教师中心擅长领域
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年4月25日 上午10:47:40</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/teachercenter/teacherinforedit/initteachershine.ajax")
	public void initTeacherShineDataOfPopwindow(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		UserVo teacherUser = SiteUtils.checkTeacher(request);
		List<TeacherShineVo> teacherShines = teacherShineService.getTeacherShineByUserId(teacherUser.getUserId());
		String text = "";
		Map<String, List<ProfessionVo>> myHashMap = new HashMap<String, List<ProfessionVo>>();
		List<ProfessionVo> unselectedList = null;
		List<ProfessionVo> selectedList = null;
		try {
			myHashMap = teacherShineService.initDataOfTeacherShine(teacherShines);

			unselectedList = myHashMap.get("unselected");
			selectedList = myHashMap.get("selected");
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		try {
			Template template = freeMarkerConfigurer.getConfiguration()
					.getTemplate("/webpage/teacenter/teacheredit/teachershinepopdiv.htm");
			ModelMap map = new ModelMap();
			map.put("unselected", unselectedList);
			map.put("selected", selectedList);
			map.put("selectedNum", teacherShines.size());
			map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
			map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		} catch (IOException e) {
			logger.error(e); e.printStackTrace();
		} catch (TemplateException e) {
			logger.error(e); e.printStackTrace();
		}

		ResponseUtils.renderText(response, text);

	}


	@Autowired
	private JobService jobService;
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private TeacherShineService teacherShineService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	SchoolService schoolService;
	// Add by:缪佳 2014-01-21
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionCenterService questionCenterService;

	@Autowired
	private StudentcenterService studentcenterService;
	@Autowired
	private ExamService examService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
}