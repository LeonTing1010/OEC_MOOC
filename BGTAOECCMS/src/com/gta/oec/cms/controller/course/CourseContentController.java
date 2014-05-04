/**
 * CourseContentController.java	  V1.0   2014-3-18 上午10:25:59
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.controller.course;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.enums.course.CourseStateEnum;
import com.gta.oec.cms.exception.AppExceiption;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.service.coursemanage.CourseService;
import com.gta.oec.cms.service.professionjob.ProfessionJobService;
import com.gta.oec.cms.util.CourseUtils;
import com.gta.oec.cms.util.FileRepository;
import com.gta.oec.cms.util.FileUtils;
import com.gta.oec.cms.util.RequestUtils;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.StrUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.UploadUtils;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.course.Course;
import com.gta.oec.cms.vo.course.CourseDetail;
import com.gta.oec.cms.vo.course.CourseJob;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.resource.ResourceVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/course")
public class CourseContentController extends BaseController<Course> {
	//图片服务器路径
	private String applicationUrl=ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
	//视频服务器路径
	private String videoRootUrl=ApplicationPropertiesUtil.getStringValue("videoRoot");
	//辅助资源服务器路径
	private String aidRoot=ApplicationPropertiesUtil.getStringValue("aidRoot");
	//图片路径
	private String imgUrl=SystemConstant.USERPATH;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private ProfessionJobService professionService;
	@Autowired 
	private FileRepository fileRepository; 
	
	private static Logger log = Logger.getLogger(CourseContentController.class);

	@RequestMapping(value = "/findCourseManage")
	public String findCourse(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("enter the CourseController and the execute the findCourse method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<Course>()));
		return "course/courseList.jsp";
	}

	@RequestMapping(value = "/coursePageList")
	public void queryCourseInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PaginationContext<Course> page = initPaginationInfo(
				new PaginationContext<Course>(), request);
		List<Course> courses = this.courseService.allCourseCtxPageQuery(page);
		page.setResult(courses);
		// 填充total和rows的数据给前台
		Map<String, Object> jsonMap = this.buildRetPageInfo(page);
		log.debug(this.gson.toJson(page));
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}

	@RequestMapping(value = "/selectProfession")
	public void selectProfession(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询所有行业信息
		try {
			List<Course> courseProList = courseService.getAllTrade();
			Course course = new Course();
			course.setProId(0);
			course.setProName("--------请选择-------");
			courseProList.add(course);
			Course firstCourse = courseProList.get(0);
			courseProList.set(courseProList.size() - 1, firstCourse);
			courseProList.set(0, course);
			ResponseUtils.renderJson(response, this.gson.toJson(courseProList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/selectPjob")
	public void selectPjob(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询所有岗位群信息
		try {
			List<Course> coursePjobList = courseService.getAllPjob(0);
			Course course = new Course();
			course.setJobId(0);
			course.setJobName("--------请选择-------");
			coursePjobList.add(course);
			Course firstCourse = coursePjobList.get(0);
			coursePjobList.set(coursePjobList.size() - 1, firstCourse);
			coursePjobList.set(0, course);
			ResponseUtils
					.renderJson(response, this.gson.toJson(coursePjobList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/selectJob")
	public void selectJob(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询岗位信息，根据行业id、父岗位群id
		try {

			Course cour = new Course();
			List<Course> courseJobList = null;
			String pId = request.getParameter("pId");
			String pjId = request.getParameter("pjId");
			if (pId != null && !"".equals(pId) && pjId == null) {
				// 根据行业id查询岗位信息
				cour.setProId(Integer.parseInt(pId));
				courseJobList = courseService.getJobByPid(cour);
			} else if (pId != null && !"".equals(pId) && pjId != null) {
				cour.setProId(Integer.parseInt(pId));
				cour.setJobPid(Integer.parseInt(pjId));
				courseJobList = courseService.getJobByPid(cour);
			} else if (pId == null && pjId != null && !"".equals(pjId)) {
				// cour.setJobPid(Integer.parseInt(pjId));
				courseJobList = courseService.getAllJob(Integer.parseInt(pjId));
			} else {
				// 获取所有的岗位信息
				courseJobList = courseService.getAllJob(0);
			}
			Course course = new Course();
			course.setJobId(0);
			course.setJobName("--------请选择-------");
			courseJobList.add(course);
			Course firstCourse = courseJobList.get(0);
			courseJobList.set(courseJobList.size() - 1, firstCourse);
			courseJobList.set(0, course);
			ResponseUtils.renderJson(response, this.gson.toJson(courseJobList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/selectPjobByPid")
	public void selectPjobByPid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 根据行业id查询父岗位群信息
		try {

			String proId = request.getParameter("id");
			List<Course> coursePjobList = courseService.getAllPjob(Integer
					.parseInt(proId));
			ResponseUtils
					.renderJson(response, this.gson.toJson(coursePjobList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/selectJobByPid")
	public void selectJobByPid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 根据父岗位群id查询岗位信息
		try {

			String pjobId = request.getParameter("id");
			List<Course> courseJobList = courseService.getAllJob(Integer
					.parseInt(pjobId));
			if (courseJobList.size() == 0 || "".equals(courseJobList)) {
				courseJobList = null;
			}
			ResponseUtils.renderJson(response, this.gson.toJson(courseJobList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/checkCourse")
	public void checkCourse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 允许发布
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Course course = new Course();
		String message = "";
		try {
			String courseId = request.getParameter("courseId");
			String courseType = request.getParameter("courseType");
			String status = request.getParameter("status");
			int courseStatus = Integer.parseInt(status);
			course.setCourseId(Integer.parseInt(courseId));
			if(courseType.equals("1")&&(courseStatus==CourseStateEnum.CHECKING.getValue()||courseStatus==CourseStateEnum.UNPASS.getValue())){
				//允许发布
				course.setStatus(CourseStateEnum.PUBLISHED.getValue());
				courseService.checkCourse(course);
				message="已审核!";
			}else if(courseType.equals("3")&&(courseStatus==CourseStateEnum.CHECKING.getValue()||courseStatus==CourseStateEnum.UNPASS.getValue())){
//				点击不通过按钮
				course.setStatus(CourseStateEnum.UNPASS.getValue());
				courseService.checkCourse(course);
				message="审核未通过！";
			}
			jsonMap.put("success", true);
			jsonMap.put("message", message);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/courseRecommend")
	public void courseRecommend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 岗位推荐课程
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		CourseJob course = new CourseJob();
		String message = "";
		try {
			String courseId = request.getParameter("courseId");
			String reType = request.getParameter("reType");
			course.setCourseID(Integer.parseInt(courseId));
			if(reType.equals("1")){
				course.setCourseJobRecommend(1);
				courseService.courseRecommend(course);
				message = "已推荐！";
			}else if(reType.equals("2")){
				course.setCourseJobRecommend(0);
				courseService.courseRecommend(course);
				message = "已取消推荐！";
			}else if(reType.equals("3")){
				message = "操作已取消！";
			}
			
			jsonMap.put("success", true);
			jsonMap.put("message", message);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}
	
	@RequestMapping(value = "/courseDrop")
	public void courseDrop(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//课程下架(一门课程)
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String courseId = request.getParameter("courseId");
			Course course = new Course();
			course.setCourseId(Integer.parseInt(courseId));
			//课程审核状态(1.未发布 2.审核中（待审核） 3.已发布 4.审核未通过 5.已结束)
			course.setStatus(CourseStateEnum.FINISHED.getValue());
			courseService.checkCourse(course);
			jsonMap.put("success", true);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		}catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}
	
	@RequestMapping(value = "/courseAgainAudit")
	public void courseAgainAudit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//重新发布课程
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try{
			String courseId = request.getParameter("courseId");
			Course course = new Course();
			course.setCourseId(Integer.parseInt(courseId));
			course.setStatus(CourseStateEnum.PUBLISHED.getValue());
			courseService.checkCourse(course);
			jsonMap.put("success", true);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		}catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}
	
	@RequestMapping(value = "/passAudit")
	public void passAudit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 批量审核课程
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String[] courseId = request.getParameterValues("courseId[]");
			String type = request.getParameter("type");
			List<String> list = java.util.Arrays.asList(courseId);
			int status = 0;
			String message = "";
			if(type.equals("1")){
				//未发布和审核未通过状态，批量通过审核
				status = CourseStateEnum.PUBLISHED.getValue();
				courseService.passAudit(list,status);
				message = "已审核！";
			}else if(type.equals("2")){
				//未发布状态，批量审核不通过
				status = CourseStateEnum.UNPASS.getValue();
				courseService.passAudit(list,status);
				message = "审核未通过！";
			}
			
			jsonMap.put("success", true);
			jsonMap.put("message", message);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/selectCourseDrops")
	public void selectCourseDrops(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//批量下架课程
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String[] courseId = request.getParameterValues("courseId[]");
			//已发布课程id的集合
			List<String> list = java.util.Arrays.asList(courseId);
			//课程审核状态(1.未发布 2.审核中（待审核） 3.已发布 4.审核未通过 5.已结束)
			int status = CourseStateEnum.FINISHED.getValue();
			courseService.passAudit(list,status);
			jsonMap.put("success", true);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		}catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}
	
	@RequestMapping(value = "/openCourseDetail")
	public void openCourseDetail(HttpServletRequest request,
			HttpServletResponse response) {
		// 打开课程介绍页
		String cid = request.getParameter("id");
		String jobId = request.getParameter("jobId");
		String tempContextUrl = ApplicationPropertiesUtil.getStringValue("ResourcesWebSite");
		try {
			Template template = freeMarkerConfigurer.createConfiguration()
					.getTemplate("course/courseView.html");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			List<Course> courses = this.courseService
					.getCourseIntroduction(Integer.parseInt(cid),Integer.parseInt(jobId));
			jsonMap.put("courseIntroduction", courses);
			jsonMap.put("projectUrl", tempContextUrl);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, jsonMap);
			ResponseUtils.renderHtmlText(response, text);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("IOException!", e);
		} catch (TemplateException e) {
			e.printStackTrace();
			log.error("TemplateException!", e);
		}
	}

	@RequestMapping(value = "/paragraphicInfo")
	public void paragraphicInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 查看视频，章节信息展示
		try {
			String courId = request.getParameter("courseId");
			Course course = courseService.getParagraphicInfo(Integer
					.parseInt(courId));
			Template template = freeMarkerConfigurer.getConfiguration()
					.getTemplate("/course/paragraphicInfo.html");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("course", course);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, jsonMap);
			ResponseUtils.renderHtmlText(response, text);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/setLiveChannel")
	public void setLiveChannel(HttpServletRequest request,
			HttpServletResponse response) {
		// 查看视频，打开设置直播通道窗口
		try {
			String courId = request.getParameter("couseId");
			Template template = freeMarkerConfigurer.getConfiguration()
					.getTemplate("/course/liveChannelView.html");
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("courId", courId);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, jsonMap);
			ResponseUtils.renderHtmlText(response, text);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	@RequestMapping(value = "/setChannelURL")
	public void setChannelURL(HttpServletRequest request,
			HttpServletResponse response) {
		// 查看视频，设置直播通道URL
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String courId = request.getParameter("cId");
			String channelurl = request.getParameter("channel");
			CourseDetail courseDetail = new CourseDetail();
			courseDetail.setCourseId(Integer.parseInt(courId));
			courseDetail.setSecUrl(channelurl);
			courseService.setChannelURL(courseDetail);
			jsonMap.put("success", true);
			ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
			log.error("System DB ERROR!", e);
		}
	}

	/**
	 * 功能描述：查看课程辅助资源.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月8日 下午1:30:50
	 *         </p>
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/reader/")
	public String courseReader(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		String courseId = request.getParameter("courseId");
		String sectionId = request.getParameter("sectionId");
		boolean flag = false;
		if (!StringUtils.isBlank(courseId) && StringUtils.isNumeric(courseId)
				&& !StringUtils.isBlank(sectionId)
				&& StringUtils.isNumeric(sectionId)) {
			flag = true;
		} else {
			flag = false;
		}
		if (flag) {
			List<ResourceVo> resourceList = null;
			try {
				//第二个参数代表章节id,第二个参数代表资源类型(辅助资源)，第三个参数代表课程id
				resourceList = courseService.getSectionCourseResourceList(
						Integer.parseInt(sectionId), 2,0);
				modelMap.put("resourceList", resourceList);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error("NumberFormatException!", e);
			} catch (AppExceiption e) {
				e.printStackTrace();
				log.error("AppExceiption!", e);
			}
		}

		return "course/reader_popupwindow.jsp";
	}

	@RequestMapping(value = "/movieView")
	public String movieView(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) {
		// 查看视频，点击章节信息中的查看视频链接
		try {
			String secId = request.getParameter("secId");
			CourseDetail course = courseService.getCourseInfoByCid(Integer
					.parseInt(secId));
			String videoProtocol = ApplicationPropertiesUtil.getStringValue("videoProtocol");
			String vodServer = ApplicationPropertiesUtil.getStringValue("vodServer");
			course.setVideoProtocol(videoProtocol);
			modelMap.put("videoProtocol", videoProtocol);
			modelMap.put("vodServer", vodServer);
			modelMap.put("course", course);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!!", e);
		}
		return	"course/checkVideo.html"; //"course/video_popupwindow.jsp";
	}

	@RequestMapping(value = "/courseAmend")
	public String courseAmend(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) {
		//课程修改
		try {
			String courseId = request.getParameter("cid");
			Course course = courseService.courseAmend(Integer.parseInt(courseId));
			String titleImg = course.getTitlePage();
			String tempContextUrl = ApplicationPropertiesUtil.getStringValue("ResourcesWebSite");
			//已选择岗位结合
			List<CourseJob> cjobList = courseService.jobInfoByCid(Integer.parseInt(courseId));
			//已选择岗位个数
			modelMap.put("jobCount", ((cjobList==null)?0:cjobList.size()));
			if(cjobList!=null){
				StringBuffer sf = new StringBuffer("[");
				Iterator<CourseJob> itr = cjobList.iterator();
				while(itr.hasNext()){
					sf.append(itr.next().getJobID()).append(",");
				}
				sf.append("]");
				modelMap.put("jobList", sf.toString());
			}
			modelMap.put("course",course);
			modelMap.put("courseId",courseId);
			modelMap.put("ImgRoot", tempContextUrl);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!!", e);
		}
		
		return "course/courseAmend/courseAmend.html";
	}
	
	@RequestMapping(value = "/step1_job")
	public String courseofPost(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) {
		// 修改课程信息，课程所属岗位列表查询
		try {
			//查询所有岗位群信息（第一个参数为行业id,第二个参数为父岗位群id）
			List<Job> list = professionService.findChildJob(-1, -1);
			if(list!=null){
				for(int i=0;i<list.size();i++){
//					根据父岗位群id查询该岗位群下的岗位信息
					List<Job> jobList = professionService.findChildJob(-1, list.get(i).getJobID());
					list.get(i).setList(jobList);
				}
			}
			modelMap.put("jobList", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!!", e);
		}
		return	"course/courseAmend/step1_job.html";
	}
	
	@RequestMapping(value = "/uploadImg")
	public String courseImgUpload(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) {
		// 修改课程信息，上传课程图片
		String errorMsg = "";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgMultipartFile = multipartRequest.getFile("titlePageFile");
			log.info("img upload  start..." + imgMultipartFile.getOriginalFilename());
			if (StringUtils.isBlank(imgMultipartFile.getOriginalFilename())) {
				errorMsg = "图片为空!";
			} else if (!StringUtils.isBlank(imgMultipartFile.getOriginalFilename()) && imgMultipartFile.getSize() > 0
					&& !FileUtils.checkFile(imgMultipartFile, "jpg", 500)) {
				errorMsg = "课程图片大小不超过500KB";
			} else {
				String resourceChildDir = ApplicationPropertiesUtil.getStringValue("imgRoot");
				try {
					//here is generate the file name by date ,you can change the generate rule,here the file name like : ***.jpg..
					//here is mulit upload the file need to delete the old file
					//FileUtils.deleteRemoteFileByName(fileRepository, resourceChildDir, "20140421102435788.jpg");
					//so ,here need to delete file
					String fileName = UploadUtils.generateFileName("", FileUtils.getFileType(imgMultipartFile.getOriginalFilename()));
					//here is upload the file to remote server ,the server need to share the file root dir 
					//because the upload use the smb protocol, so the need the dir to share
					String fileName1 = FileUtils.storeRemoteFileByAuth(fileRepository,resourceChildDir,fileName,imgMultipartFile);
					
					modelMap.put("dataUrl", resourceChildDir+fileName1);
				} catch (Exception e) {
					errorMsg = "图片上传失败，请重新上传";
					e.printStackTrace();
					log.error(errorMsg, e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!!", e);
		}
		log.info("img upload  end..." + modelMap.get("dataUrl"));
		modelMap.put("type", "Img");
		modelMap.put("errorMsg", errorMsg);
		return	"course/courseAmend/uploadok.html";
	}
	
	@RequestMapping(value = "/uploadVideo")
	public void courseVideoUpload(HttpServletRequest request,
			HttpServletResponse response){
		// 修改课程信息，上传课程视频
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile previewVideo = multipartRequest.getFile("previewFile");
		String errorMsg = "";
//		try {
			log.info("preview video upload start..");
//		} catch (ParseException e) {
//			log.error(e);
//		}
		if (previewVideo != null && previewVideo.getSize() > 0 && !StringUtils.isBlank(previewVideo.getOriginalFilename())) {
			//String courseRoot = ApplicationPropertiesUtil.getStringValue("courseRoot");
			String videoRoot = ApplicationPropertiesUtil.getStringValue("videoRoot");
			String videoServerIp = ApplicationPropertiesUtil.getStringValue("videoServerIp");
			String videoServerName = ApplicationPropertiesUtil.getStringValue("videoServerName");
			String vidoeServerPassword = ApplicationPropertiesUtil.getStringValue("vidoeServerPassword");
			String filePath = "smb://" + videoServerIp + videoRoot;
			String filename = UploadUtils.generateFilename("", FileUtils.getFileType(previewVideo.getOriginalFilename()));
			String fileName2 = "";
			try {
				jsonMap.put("resourceName", previewVideo.getOriginalFilename());
				fileName2 = fileRepository.storeByRmoeteFilename(videoServerIp, videoServerName, vidoeServerPassword, filePath.trim(),
						filename, previewVideo);
				String fileNamed = request.getParameter("fileNamed");
				if(null !=fileNamed && !fileNamed.equals("")){
					WebUtils.delUserImg( fileNamed,videoRootUrl);
				}
				jsonMap.put("dataUrl", fileName2);
//				try {
					log.info("preview video upload end..");
//				} catch (ParseException e) {
//					log.error(e);
//				}
			} catch (IOException e) {
				errorMsg = "预览视频上传异常！";
				jsonMap.put("message", errorMsg);
				log.error(errorMsg + e);
			}
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
//		jsonMap.put("type", "Video");
//		jsonMap.put("errorMsg", errorMsg);
//		return "course/courseAmend/uploadok.html";
	}
	
	//课件资源，章节视频上传
	@RequestMapping("/upload/uploadSectionVideo")
	public void uploadSectionVideo(HttpServletRequest request, HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile videoFile = multipartRequest.getFile("mainFile");
		String errorMsg = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("section upload start..." + videoFile.getOriginalFilename());
		} catch (Exception e) {
			log.error(e); e.printStackTrace();
		}
		if (videoFile != null && videoFile.getSize() > 0 && !StringUtils.isBlank(videoFile.getOriginalFilename())) {
			String videoRoot = ApplicationPropertiesUtil.getStringValue("videoRoot");
			String videoServerIp = ApplicationPropertiesUtil.getStringValue("videoServerIp");
			String videoServerName = ApplicationPropertiesUtil.getStringValue("videoServerName");
			String vidoeServerPassword = ApplicationPropertiesUtil.getStringValue("vidoeServerPassword");
			String filePath = "smb://" + videoServerIp + videoRoot;
			String filename = UploadUtils.generateFilename("", FileUtils.getFileType(videoFile.getOriginalFilename()));
			String fileName2 = "";
			try {
				jsonMap.put("resourceName", videoFile.getOriginalFilename());
				fileName2 = fileRepository.storeByRmoeteFilename(videoServerIp, videoServerName, vidoeServerPassword, filePath.trim(),
						filename, videoFile);
				String fileNamed = request.getParameter("fileNamed");
				if(null !=fileNamed && !fileNamed.equals("")){
					WebUtils.delUserImg( fileNamed,videoRootUrl);
				}
				try {
					log.info("section upload end..." + jsonMap.get("dataUrl"));
				} catch (Exception e) {
					log.error(e); e.printStackTrace();
				}
				jsonMap.put("dataUrl", fileName2);
			} catch (IOException e) {
				errorMsg = "预览视频上传异常！";
				log.error(errorMsg + e);
			}
		}
		jsonMap.put("message", errorMsg);
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value = "/checkCourseName")
	public void checkCourseName(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//检查课程名称是否重复
		boolean result = false;
		try {
			String courseName = request.getParameter("courseName");
			Course course = new Course();
			course.setCourseName(courseName.trim());
			List<Course> courseList = courseService.courseInfo(course);
			if(courseList!=null&&courseList.size()>0){
				result = true;
			}
 		}catch (Exception e) {
			e.printStackTrace();
			log.error("System DB ERROR!!", e);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/saveCourseInfo")
	public void saveCourseInfo(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
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
		String schoolId = RequestUtils.getQueryParam(request, "schoolId");//学校id
		String titleImgRoot = RequestUtils.getQueryParam(request, "titlePageImg");//保存操作之前数据库存储的图片路径
		String recommendVideo = RequestUtils.getQueryParam(request, "recommendVideo");//保存操作之前数据库存储的视频名称
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
				Course course = new Course();
				course.setCourseName(courseName);
				course.setLiveType(Integer.parseInt(type));
				course.setIntroduction(introduction);
				course.setScoreMethod(scoreMethod);
				course.setCredit(Float.parseFloat(credit));
				course.setPrice(Float.parseFloat(price));
				course.setIsPublic("1");
				course.setTitlePage(titleImgRoot);
				course.setPreviewUrl(previewUrl);
				course.setSchoolId(Integer.parseInt(schoolId));
				course.setStatus(2);
				// 上传文件操作
				List<ResourceVo> list = new ArrayList<ResourceVo>();
				ResourceVo resourceVo = null;
				if (!StringUtils.isBlank(titlePage)) {
					resourceVo = new ResourceVo();
					resourceVo.setResourceURL(titlePage);
					resourceVo.setIsDownload("1");
					resourceVo.setResourceType("2");
					list.add(resourceVo);
				}
				if (!StringUtils.isBlank(previewUrl)) {
					resourceVo = new ResourceVo();
					resourceVo.setResourceURL(previewUrl);
					resourceVo.setResourceName(resourceName);
					resourceVo.setResourceType("1");
					resourceVo.setIsDownload("1");
					list.add(resourceVo);
				}
				List<CourseJob> list2 = new ArrayList<CourseJob>();
				if (null != job && job.length > 0) {
					// 批量绑定课程与岗位
					for (String str : job) {
						CourseJob courseJob = new CourseJob();
						courseJob.setJobID(Integer.parseInt(str));
						courseJob.setCourseID(Integer.parseInt(step1CourseId));
						list2.add(courseJob);
					}
				}
				// 如果step1CourseId为空，表示是第一次编辑，保存课程信息
				if (StringUtils.isBlank(step1CourseId) || "0".equals(step1CourseId)) {
					course = courseService.saveOrUpadteCourse(course, list, list2, true);
				} else {
					course.setCourseId(Integer.parseInt(step1CourseId));
					course = courseService.saveOrUpadteCourse(course, list, list2, false);
					
					if(!(titlePage.trim().equals(course.getTitlePage().trim())) || (titlePage.trim()!=course.getTitlePage().trim())){
						WebUtils.delUserImg(titlePage,applicationUrl);
					}
					if(!(recommendVideo.equals(course.getPreviewUrl())) || (recommendVideo!=course.getPreviewUrl())){
						WebUtils.delUserImg( recommendVideo,videoRootUrl);
					}
				}
				respJsonObject.put("courseId", course.getCourseId());
			} catch (Exception e) {
				errorMsg = "保存课程信息异常！";
				log.info(e.getMessage());
			} 
		}
		log.info("上传课件第一步：errorMsg=" + errorMsg);
		respJsonObject.put("errorMsg", errorMsg);
		ResponseUtils.renderJson(response, respJsonObject.toString());
	}

	
	@RequestMapping(value = "/courseResource")
	public String courseResource(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//跳转到课程修改，课件资源页
		String courseId = request.getParameter("courseId");
		try{
			//根据课程id，查询课程章节信息
			Course course = courseService.getParagraphicInfo(Integer.parseInt(courseId));
			modelMap.put("course", course);
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		} 
		return "course/courseAmend/courseResource.html";
	}
	
	@RequestMapping(value = "/step3/getRight")
	public String courseResourceGetRigh(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap){
		//课程资源右边数据页面
		String sectionId = request.getParameter("sectionId");
		String courseId = request.getParameter("courseId");
		if(!StringUtils.isBlank(sectionId) && StrUtils.isNum(sectionId)){
			CourseDetail section;
			try{
				section = courseService.getCourseInfoByCid(Integer.parseInt(sectionId));
				ResourceVo resourceVo = new ResourceVo();
//				根据章节id、资源类型、课程id，查询辅助资源信息
				List<ResourceVo> resList = courseService.getSectionCourseResourceList(
						Integer.parseInt(sectionId), 2,Integer.parseInt(courseId));
				modelMap.put("resList", resList);
				modelMap.put("section", section);
			} catch (NumberFormatException e) {
				log.error(e); 
				e.printStackTrace();
			} catch (Exception e) {
				log.equals("get section info:" + e.getMessage());
			}
		}
		return "course/courseAmend/step3_right.html";
	}
	
	//课程修改，课件资源，辅助资源上传
	@RequestMapping("/upload/uploadAidResource")
	public String uploadAidResource(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile aidFile = multipartRequest.getFile("aidFile");
		String errorMsg = "";
		try {
			log.info("辅助资源上传开始..." + aidFile.getOriginalFilename());
		} catch (Exception e) {
			log.error(e); e.printStackTrace();
		}
		if (aidFile != null && aidFile.getSize() > 0 && !StringUtils.isBlank(aidFile.getOriginalFilename())) {
			String resourceRoot = ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
			String resourceServerIp = ApplicationPropertiesUtil.getStringValue("videoServerIp");
    		String resourceServerName = ApplicationPropertiesUtil.getStringValue("videoServerName");
    		String resourceServerPassword = ApplicationPropertiesUtil.getStringValue("vidoeServerPassword");
    		String aidResourceRoot = ApplicationPropertiesUtil.getStringValue("aidRoot");
    		
    		String filePath = "smb://"+resourceServerIp+resourceRoot+aidResourceRoot;
    		String fileName = UploadUtils.generateFilename("", FileUtils.getFileType(aidFile.getOriginalFilename()));
			String fileStoreName = "";
    		try {
    			modelMap.put("resourceName", aidFile.getOriginalFilename());
				fileStoreName=fileRepository.storeByRmoeteFilename(resourceServerIp, resourceServerName, resourceServerPassword, filePath.trim(),
						 fileName, aidFile);
				fileName=aidResourceRoot+fileName;
			} catch (IOException e) {
				errorMsg = "文件上传异常！";
				log.error(errorMsg+e);
			}
    		modelMap.put("dataUrl", fileName);
		}
		try {
			log.info("辅助资源上传结束..." + modelMap.get("dataUrl"));
		} catch (Exception e) {
			log.error(e); e.printStackTrace();
		}
		modelMap.put("type", "AidResource");
		modelMap.put("errorMsg", errorMsg);
		return "course/courseAmend/uploadok.html";
	}
	
	@RequestMapping("/upload/saveStep3")
	public void saveStep3(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		String sectionId = RequestUtils.getQueryParam(request, "sectionId");
		String step3CourseId = RequestUtils.getQueryParam(request, "step3CourseId");
		String coreKnowledge = RequestUtils.getQueryParam(request, "coreKnowledge");
		String videoUrl = RequestUtils.getQueryParam(request, "videoUrl");
		String[] aidResource = RequestUtils.getQueryParamValues(request, "aidResource");
		String[] aidResourceName = RequestUtils.getQueryParamValues(request, "aidResourceName");
		String videoResourceName = RequestUtils.getQueryParam(request, "videoResourceName");
		String movieResource = RequestUtils.getQueryParam(request, "movieResource");
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
				CourseDetail section = new CourseDetail();
				section.setId(Integer.parseInt(sectionId));
				section.setCoreKnowledge(coreKnowledge);
				try {
					courseService.updateSection(section);
				} catch (Exception e) {
					log.info("update section info:" + e.getMessage());
				}
			}
			if (!StringUtils.isBlank(videoUrl)) {
				CourseDetail section = new CourseDetail();
				section.setId(Integer.parseInt(sectionId));
				section.setSecUrl(videoUrl);
				try {
					courseService.updateSection(section);
					ResourceVo resourceVo = new ResourceVo();
					resourceVo.setCourseId(Integer.parseInt(step3CourseId));
					resourceVo.setSectionId(Integer.parseInt(sectionId));
					resourceVo.setResourceURL(videoUrl);
					resourceVo.setResourceName(videoResourceName);
					resourceVo.setIsDownload("1");
					resourceVo.setResourceType("1");
					resourceVo = courseService.addResource(resourceVo);
					courseService.addBindCourRescource(resourceVo);
					if(!(movieResource.equals(resourceVo.getResourceURL())) || (movieResource!=resourceVo.getResourceURL())){
						WebUtils.delUserImg( movieResource,videoRootUrl);
					}
				} catch (Exception e) {
					log.error(e); 
					e.printStackTrace();
				}
			}

			// 辅助资源
			if (null != aidResource && aidResource.length > 0) {
				for (int i = 0; i < aidResource.length; i++) {
					ResourceVo resourceVo = new ResourceVo();
					resourceVo.setCourseId(Integer.parseInt(step3CourseId));
					resourceVo.setSectionId(Integer.parseInt(sectionId));
					resourceVo.setResourceURL(aidResource[i]);
					if (aidResourceName.length==aidResource.length) {
						resourceVo.setResourceName(aidResourceName[i]);
					}
					resourceVo.setIsDownload("2");
					resourceVo.setResourceType("2");
					
					// 增加资源
					try {
						resourceVo = courseService.addResource(resourceVo);
						// 绑定资源
						courseService.addBindCourRescource(resourceVo);
					} catch (Exception e) {
						log.error(e); 
						e.printStackTrace();
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
	 * 功能描述：异步获取课程未审核数量.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月24日 下午4:43:22</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/ajaxGetCourseUnauditedNum")
	public void ajaxGetCourseUnauditedNum(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		int num=0;
		boolean flag=true;
		try {
			num=courseService.getCourseUnauditedNum(CourseStateEnum.CHECKING.getValue());
			flag=true;
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error(e.getAllMessage());
			flag=false;
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("num", num);
		jsonObject.put("flag", flag);
		ResponseUtils.renderJson(response, jsonObject.toString());
		
	}

	
	/**
	 * 功能描述：异步获取课程的一节中是否有内容.(即:核心知识点,课程视频,辅助资源).
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月24日 下午4:43:22</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/ajaxGetResourceInPartOfSectionNum")
	public void ajaxGetResourceInPartOfSectionNum(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		String courseId = RequestUtils.getQueryParam(request, "courseId"); //课程id
		String partOfSectionId = RequestUtils.getQueryParam(request, "partOfSectionId"); //小节id.
		boolean flag=true;
		boolean result=false;
		String msg="";
		if (!StringUtils.isNumeric(courseId) || !StringUtils.isNumeric(partOfSectionId)) {
			msg="paramters are illegal";
			flag=false;
			log.error(msg);
		}
		if (flag) {
			
			try {
				result=courseService.hasResourceInPartOfSection(Integer.parseInt(courseId), Integer.parseInt(partOfSectionId));
				flag=true;
			} catch (ServiceException e) {
				e.printStackTrace();
				log.error(e.getAllMessage());
				msg="db opreation error";
				flag=false;
			}
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("flag", flag);
		jsonObject.put("msg", msg);
		ResponseUtils.renderJson(response, jsonObject.toString());
		
	}
	
	
	/**
	 * 功能描述：跳转到课程修改第二步.大纲修改.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月22日 下午5:15:58</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/toUpdatePublishedCourseStep2")
	public String toUpdatePublishedCourseStep2_outline(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//跳转到课程修改的大纲修改页.
		String courseId = request.getParameter("courseId");
		try{
			//根据课程id，查询课程章节信息
			Course course = courseService.getParagraphicInfo(Integer.parseInt(courseId));
			modelMap.put("course", course);
		}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		} 
		return "course/courseAmend/step2_outline.htm";
	}
	/**
	 * 功能描述：修改课程信息第二部.修改课程大纲.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月23日 下午3:10:08</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="/updatePublishedCourseStep2")
	public void updatePublishedCourseStep2_outline(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
			   String courseId = RequestUtils.getQueryParam(request, "updateCourseId"); //更新的课程id
			   String[] updateSection = RequestUtils.getQueryParamValues(request, "changeSection"); //更新章名称
			   String[] updatePart = RequestUtils.getQueryParamValues(request, "changePart"); //更新节名称
			   String[] deleteSection  = RequestUtils.getQueryParamValues(request, "deleteSection"); //删除的章
			   String[] deletePart  = RequestUtils.getQueryParamValues(request, "deletePart"); //删除的节
			   String[] addPart = RequestUtils.getQueryParamValues(request, "addPart"); //在原有章的基础上增加节
			   String[]	addSection =    RequestUtils.getQueryParamValues(request, "addSection"); //新增的章
			   String[]	addNewPart =    RequestUtils.getQueryParamValues(request, "addNewPart"); //新增的章的基础上新增节
			   String msg="";
			   Boolean result = true;
			   Course course=new Course();

			   if (StringUtils.isBlank(courseId) || !StringUtils.isNumeric(courseId)) {
				log.error("courseId is not a legal paramter.");
				msg="courseId is not a legal paramter.";
				result=false;
			   }else {
				   course.setCourseId(Integer.parseInt(courseId));
				   List<Course> tempList=courseService.courseInfo(course);
				   if (tempList.size()>0) {
					   course= tempList.get(0);
				}else {
					course=null;
					log.error("courseId is not a legal paramter.");
					msg="courseId is not a legal paramter.";
					result=false;
				}
			   }
			   if (result==true) {
				
			   /***********************组装章节删除数据*******************************************/
			   List<Integer> deleteSectionList = new ArrayList<Integer>();
				if (null != deleteSection) {

					for (String str : deleteSection) {
						if (!StringUtils.isBlank(str) && StrUtils.isNum(str)) {
							deleteSectionList.add(Integer.parseInt(str));
						}
					}
				}
				if (null != deletePart) {
					for (String str : deletePart) {
						if (!StringUtils.isBlank(str) && StrUtils.isNum(str)) {
							deleteSectionList.add(Integer.parseInt(str));
						}
					}
				}
			   /***********************组装章节修改数据*******************************************/
			   List<CourseDetail> updateSectionList = new ArrayList<CourseDetail>();
			   //解析修改的章
			   if (null!=updateSection) {
				   for (String str: updateSection) {
					     if (!StringUtils.isBlank(str)) {
							String[] uStr = str.split("\\:");
							if (uStr.length==2) {
								CourseDetail section = new CourseDetail();
								section.setId(Integer.parseInt(uStr[0]));
								section.setName(uStr[1]);
								updateSectionList.add(section);
							}
						}
				    }
			  }
			  
			   //解析修改的节
		      if (null!=updatePart) {
		      	   for (String str: updatePart) {
		  		     if (!StringUtils.isBlank(str)) {
		  				String[] uStr = str.split("\\:");
		  				if (uStr.length==2) {
		  					CourseDetail section= new CourseDetail();
		  					section.setId(Integer.parseInt(uStr[0]));
		  					section.setName(uStr[1]);
		  					updateSectionList.add(section);
		  				}
		  			}
		  	    }
				}
			   /**********************在原有章的基础上增加节*************************/
			   List<CourseDetail> addPartList = new ArrayList<CourseDetail>();
				if (null != addPart) {
					for (String str : addPart) {
						if (!StringUtils.isBlank(str)) {
							String[] uStr = str.split("\\:");
							if (uStr.length == 2) {
								CourseDetail sectionVO = new CourseDetail();
								sectionVO.setCourseId(course.getCourseId());
								sectionVO.setPid(Integer.parseInt(uStr[0]));
								sectionVO.setName(uStr[1]);
								sectionVO.setType(String.valueOf(course.getLiveType()));
								sectionVO.setcTime(VeDate
										.getNowDate());
								sectionVO.setuTime(VeDate
										.getNowDate());
								addPartList.add(sectionVO);
							}
						}
					}
				}
			  /********************新增新的章、新的节***********************************/
			    List<CourseDetail> sectionList = new ArrayList<CourseDetail>();
				if (null != addSection) {
					for (String string : addSection) {
					   if (!StringUtils.isBlank(string)) {
						   CourseDetail sectionVo = new CourseDetail();
							sectionVo.setList(new ArrayList<CourseDetail>());
							sectionVo.setName(string);
							sectionVo.setCourseId(course.getCourseId());
							sectionVo.setType(String.valueOf(course.getLiveType()));				
							sectionVo.setPid(Integer.parseInt(courseId));
								sectionVo.setcTime(VeDate.getNowDate());
								sectionVo.setuTime(VeDate.getNowDate());
							sectionList.add(sectionVo);
						}
					}
						
				}
				
				//将节信息组装至章
				if (null!=addNewPart) {
					for (String string:addNewPart) {
						 //校验数据的合法性
						if (string.contains(":")) {
							String str[] = string.split("\\:");
							//校验数据的合法性
							if (str.length==3) {
								CourseDetail sectionVo = new CourseDetail();
								sectionVo.setName(str[2]);
								sectionVo.setCourseId(course.getCourseId());
								sectionVo.setType(String.valueOf(course.getLiveType()));	
									sectionVo.setcTime(VeDate.getNowDate());
									sectionVo.setuTime(VeDate.getNowDate());
								sectionList.get(Integer.parseInt(str[0])-1).getList().add(sectionVo);
							}
						}

					}			
				}
				sectionList.addAll(addPartList);
				
				
				try {
					courseService.updatePublishedCourseStep2_outlineService(course.getCourseId(), deleteSectionList, updateSectionList,sectionList);
				} catch (ServiceException e) {
					e.printStackTrace();
					
				}
				
				
			   }
			  JSONObject jsonObject = new JSONObject();
			  jsonObject.put("result", result);
			  jsonObject.put("msg", msg);
			  ResponseUtils.renderJson(response, jsonObject.toString());
		   }

	
	@RequestMapping("/uploadCourseImg")
	public void uploadUserImg(HttpServletRequest  request,HttpServletResponse response) throws Exception{
//		上传课程图片
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		 try {
			 	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile userFile=multipartRequest.getFile("titlePageFile");
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
			jsonMap.put("courseImgFileName",null);
			log.error(e);
			e.printStackTrace();
			throw e;
		}	   
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping("/uploadfzFileDelete")
	public void uploadfzFileDelete(HttpServletRequest  request,HttpServletResponse response) throws Exception{
		try{
			String url = request.getParameter("uploadurl");
			if((null !=url) || (!url.equals("")) || (!url.equals(null))){
				int index = url.lastIndexOf("/")+1;
				String resourceUrl = url.substring(0, index);
				String resourceName = url.substring(index);
				WebUtils.delUserImg( resourceName,applicationUrl+resourceUrl);
			}else{
				log.info("辅助资源不存在");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}	 
	}
}













