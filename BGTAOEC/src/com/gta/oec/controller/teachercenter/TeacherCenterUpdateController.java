/**
 * TeacherCenterUpdateController.java	  V1.0   2014-1-22 上午9:52:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.teachercenter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.upload.FileRepository;
import com.gta.oec.common.web.upload.UploadUtils;
import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.util.CourseUtils;
import com.gta.oec.util.DateUtils;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.resource.ResourceVo;
import com.gta.oec.vo.school.SchoolVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：教师中心-修改课程控制层
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
public class TeacherCenterUpdateController {
	private static final Log logger = LogFactory.getLog(TeacherCenterUpdateController.class);
	/**
	 * 
	 * 功能描述：加载修改页面页面
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-22 上午10:22:50</p>
	 *
	 * @returny
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws LoginException 
	 */
   @RequestMapping("/teacherCenter/update/")
   public String teacherCenterUpdate(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws LoginException {
	   String courseId = RequestUtils.getQueryParam(request, "courseId");
	   SiteUtils.checkTeacher(request);
	   if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
	      try {
			CourseVo courseVo = courseService.getCourseInfoById(Integer.parseInt(courseId));
			modelMap.put("course", courseVo);
		  } catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		  } catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		   }
	   }
	   modelMap.put("courseId", courseId);
	   modelMap.put("code", System.currentTimeMillis());
	   return "teacenter/update/update.htm";
    }
   //第一页
   @RequestMapping("/teacherCenter/update/page1/")
   public String teacherCenterUpdatePage1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws LoginException {
	   String courseId = RequestUtils.getQueryParam(request, "courseId");
	   SiteUtils.checkTeacher(request);	 
	   CourseVo courseVo = new CourseVo();
	   if (!StringUtils.isBlank(courseId)&&StrUtils.isNum(courseId)) {
		   try {
			courseVo = courseService.getCourseInfoById(Integer.parseInt(courseId));
			List<CourseJobVo> jobList = jobService.getAssociatedJobByCourseId(Integer.parseInt(courseId));
			modelMap.put("jobCount", jobList==null?0:jobList.size());
			//组装job数组
	        if (jobList!=null) {
	        	 StringBuffer str = new StringBuffer("[");
				 Iterator<CourseJobVo> iterator = jobList.iterator();
				 while (iterator.hasNext()) {
					 str.append( iterator.next().getJobID()).append(",");
				}
				str.append("]");
				  modelMap.put("jobStr", str.toString());
			}
	      
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error("get course info error.");
		}
	    }
	   modelMap.put("course", courseVo);
	   modelMap.put("courseId", courseId);
	   return "teacenter/update/page1.htm";
    }
   //第二页
   @RequestMapping("/teacherCenter/update/page2/")
   public String teacherCenterUpdatePage2(HttpServletRequest request,ModelMap modelMap) throws LoginException {
	   SiteUtils.checkTeacher(request);	 
	   String courseId = RequestUtils.getQueryParam(request, "courseId");
	   try {
		CourseVo courseVo = courseService.getCourseById(Integer.parseInt(courseId));
		modelMap.put("course", courseVo);
	   } catch (NumberFormatException e) {
		logger.error(e); e.printStackTrace();
	   } catch (BaseException e) {
	   	logger.error(e); e.printStackTrace();
	   }
	   return "teacenter/update/page2.htm";
    }
   
   //第三页
   @RequestMapping("/teacherCenter/update/page3/")
   public String teacherCenterUpdatePage3(HttpServletRequest request,ModelMap modelMap) throws LoginException{
	   SiteUtils.checkTeacher(request);	
	   String courseId = RequestUtils.getQueryParam(request, "courseId");
	   try {
		CourseVo courseVo = courseService.getCourseById(Integer.parseInt(courseId));
		modelMap.put("course", courseVo);
	   } catch (NumberFormatException e) {
		logger.error(e); e.printStackTrace();
	   } catch (BaseException e) {
	   	logger.error(e); e.printStackTrace();
	   }
	   return "teacenter/update/page3.htm";
    }
 
   /**
    * 
    * 功能描述：修改第二步，保存操作
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-2-11 下午1:11:18</p>
    *
    * @param request
    * @param response
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   @RequestMapping("/teacherCenter/update/page2/save/")
   public void teacherCenterUpdatePage2Save(HttpServletRequest request,HttpServletResponse response){
	   String courseId = RequestUtils.getQueryParam(request, "updateCourseId"); //更新章名称
	   String[] updateSection = RequestUtils.getQueryParamValues(request, "changeSection"); //更新章名称
	   String[] updatePart = RequestUtils.getQueryParamValues(request, "changePart"); //更新节名称
	   String[] deleteSection  = RequestUtils.getQueryParamValues(request, "deleteSection"); //删除的章
	   String[] deletePart  = RequestUtils.getQueryParamValues(request, "deletePart"); //删除的章
	   String[] addPart = RequestUtils.getQueryParamValues(request, "addPart"); //在原有章的基础上增加节
	   String[]	addSection =    RequestUtils.getQueryParamValues(request, "addSection"); //新增的章
	   String[]	addNewPart =    RequestUtils.getQueryParamValues(request, "addNewPart"); //新增的章的基础上新增节

	   Boolean result = true;
	   /***********************章节删除*******************************************/
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

		   if (deleteSectionList.size()>0) {
			  try {
				 courseService.betachDeleteSectionById(deleteSectionList,Integer.parseInt(courseId));
		     	} catch (BaseException e) {
		     	logger.info("update page2 deleteSection error."+deleteSectionList);
			   }
		   }
	 
	  
	   /***********************章节修改*******************************************/
	   List<SectionVO> updateSectionList = new ArrayList<SectionVO>();
	   //解析修改的章
	   if (null!=updateSection) {
		   for (String str: updateSection) {
			     if (!StringUtils.isBlank(str)) {
					String[] uStr = str.split("\\:");
					if (uStr.length==2) {
						SectionVO sectionVO = new SectionVO();
						sectionVO.setId(Integer.parseInt(uStr[0]));
						sectionVO.setName(uStr[1]);
						updateSectionList.add(sectionVO);
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
  					SectionVO sectionVO = new SectionVO();
  					sectionVO.setId(Integer.parseInt(uStr[0]));
  					sectionVO.setName(uStr[1]);
  					updateSectionList.add(sectionVO);
  				}
  			}
  	    }
		}
	   if (updateSectionList.size()>0) {
		  try {
			courseService.betachUpdateSection(updateSectionList);
	     	} catch (BaseException e) {
	     		logger.info("update page2 UpdateSection error."+updateSectionList);
		   }
	   }
	   /**********************在原有章的基础上增加节*************************/
	   List<SectionVO> addPartList = new ArrayList<SectionVO>();
		CourseVo courseVo = new CourseVo();
		try {
			courseVo = courseService.getCourseInfoById(Integer.parseInt(courseId));
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			courseVo = new CourseVo();
			logger.error(e); e.printStackTrace();
		}
		if (null != addPart) {
			for (String str : addPart) {
				if (!StringUtils.isBlank(str)) {
					String[] uStr = str.split("\\:");
					if (uStr.length == 2) {
						SectionVO sectionVO = new SectionVO();
						sectionVO.setCourseId(Integer.parseInt(courseId));
						sectionVO.setPid(Integer.parseInt(uStr[0]));
						sectionVO.setName(uStr[1]);
						sectionVO.setType(String.valueOf(courseVo.getLiveType()));
						try {
							sectionVO.setcTime(DateUtils
									.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
							sectionVO.setuTime(DateUtils
									.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
						} catch (ParseException e) {
							logger.error(e); e.printStackTrace();
						}

						addPartList.add(sectionVO);
					}
				}
			}

		}
	   if (addPartList.size()>0) {
		try {
			courseService.saveBatchSection(addPartList);
		} catch (BaseException e) {
	     	logger.info("update page2 UpdateSection error."+updateSectionList);
		   }
	  }
	  /********************新增新的章、新的节***********************************/
	    List<SectionVO> sectionList = new ArrayList<SectionVO>();
		if (null != addSection) {
			for (String string : addSection) {
			   if (!StringUtils.isBlank(string)) {
				   SectionVO sectionVo = new SectionVO();
					sectionVo.setList(new ArrayList<SectionVO>());
					sectionVo.setName(string);
					sectionVo.setCourseId(Integer.parseInt(courseId));
					sectionVo.setPid(Integer.parseInt(courseId));
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
		
		//将节信息组装至章
		if (null!=addNewPart) {
			for (String string:addNewPart) {
				 //校验数据的合法性
				if (string.contains(":")) {
					String str[] = string.split("\\:");
					//校验数据的合法性
					if (str.length==3) {
						SectionVO sectionVo = new SectionVO();
						sectionVo.setName(str[2]);
						sectionVo.setCourseId(Integer.parseInt(courseId));
						sectionVo.setType(String.valueOf(courseVo.getLiveType()));
						try {
							sectionVo.setcTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
							sectionVo.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
						} catch (ParseException e) {
							logger.error(e); e.printStackTrace();
						}
						sectionList.get(Integer.parseInt(str[0])-1).getList().add(sectionVo);
					}
				}

			}			
		}

		
	   if (sectionList.size()>0) {
			try {
			courseService.saveBatchSection(sectionList);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
		 }
	   }
		
	  JSONObject jsonObject = new JSONObject();
	  jsonObject.put("result", result);
	  ResponseUtils.renderJson(response, jsonObject.toString());
   }
   
   @Autowired 
   private CourseService courseService;
   @Autowired 
   private JobService jobService;  
   @Autowired
   private FileRepository fileRepository;
}
