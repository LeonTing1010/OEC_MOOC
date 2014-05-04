package com.gta.oec.cms.controller.professionjob;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.service.professionjob.ProfessionJobService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

@Controller
@RequestMapping(value="profession")
public class ProfessionJobController extends BaseController<Profession> {
	
	private static Logger log = Logger.getLogger(ProfessionJobController.class);
	
	@Autowired
	private ProfessionJobService professionJobService;
	//图片服务器路径  /res_dev/
	private String applicationUrl=ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
	//图片路径  /res/jobimage/
	private String imgUrl=SystemConstant.proJobPATH;
	//服务器路径  http://192.168.101.163:8090/res_dev/
	private String resourcesWebSite=ApplicationPropertiesUtil.getStringValue("ResourcesWebSite");
	
	/**
	 * 功能描述：初始化行业分类的页面信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-03-24 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="listAllProfessionInit")
	public String listAllProfessionInit(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("page", this.gson.toJson(new PageModel<Profession>()));
		log.debug(ApplicationPropertiesUtil.getStringValue("ResourcesRootDir"));
		log.debug(ApplicationPropertiesUtil.getStringValue("ResourcesWebSite"));
		return "professionjob/listAllProfessionJob.jsp";
	}
	
	/**
	 * 功能描述：初始化行业分类的增加信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-02 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="addInitProfession")
	public String addInitProfession(HttpServletRequest req, HttpServletResponse resp){
		return "professionjob/addProfessionJob.html";
	}
	
	/**
	 * 功能描述：推荐岗位信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-08 下午8:00:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)
	 * dongs,2014-4-28 14:04:44,增加取消推荐岗位功能.
	 * </p>
	 */
	@RequestMapping(value="recommendJob")
	public void recommendJob(String jobID,String jobRecommend,HttpServletRequest req, HttpServletResponse resp){
		//String jobID = req.getParameter("jobID");
		//String chosenOrNot = req.getParameter("chosenOrNot");
		boolean flag=false;
		String msg="";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (!StringUtils.isNumeric(jobRecommend) || !StringUtils.isNumeric(jobID)) {   //把这里的||换成了&&
			flag=false;
			msg="paramters error:jobID or chosenOrNot is not a integer";
			log.error(msg);
		}else {
			flag=true;
			msg="";
		}
		if(flag){
			Job job = new Job();
			job.setJobRecommend(Integer.parseInt(jobRecommend));
			job.setJobUtime(new Date());
			/*if (Integer.parseInt(chosenOrNot)==0) {
				job.setJobRecommend(1);
			}else if (Integer.parseInt(chosenOrNot)==1) {
				job.setJobRecommend(0);
			}
			job.setJobUtime(VeDate.getNowDate());*/
			try {
				job.setJobID(Integer.parseInt(jobID));
				professionJobService.updateJob(job);
				jsonMap.put("success", true);
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
				jsonMap.put("success", false);
				jsonMap.put("errorMsg", e.getMessage());
			}
			log.debug(gson.toJson(job));
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(jsonMap));
	}
	
	/**
	 * 功能描述：查看岗位群，岗位信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-08 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="viewJob")
	public String viewJob(HttpServletRequest req, HttpServletResponse resp){
		String jobID = req.getParameter("jobID");
		if(StringUtils.isNotEmpty(jobID)){
			int _jobId = 0;
			try{
				_jobId = Integer.parseInt(jobID);
			}catch(Exception e){
				_jobId = -1;
			}
			Job job = this.professionJobService.findJobByJobId(_jobId);
			log.debug(gson.toJson(job));
			req.setAttribute("Job", job);
			req.setAttribute("resourcesWebSite", resourcesWebSite);
			if(job.getJobPID()<=0){
				return "professionjob/viewJobGroup.html";
			}else{				
				return "professionjob/viewJob.html";
			}
		}else{
			return "professionjob/viewJob.html";
		}
	}
	
	/**
	 * 功能描述：初始化岗位群页面信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-14 上午11:37:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="addInitJobGroup")
	public String addInitJobGroup(HttpServletRequest req, HttpServletResponse resp){
		String proID = req.getParameter("proID");
		Job job = new Job();
		if(StringUtils.isNotEmpty(proID)){
			int _proId = 0;
			try{
				_proId = Integer.parseInt(proID);
			}catch(Exception e){
				_proId = -1;
			}
			
			job.setProID(_proId);
		}
		req.setAttribute("Job", job);
		return "professionjob/addJobGroup.html";
	}
	
	/**
	 * 功能描述：编辑岗位群页面信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-14 下午16:40:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="editJobGroup")
	public String editJobGroup(HttpServletRequest req, HttpServletResponse resp){
		String jobID = req.getParameter("jobID");
		int _jobId = 0;
		try{
			_jobId = Integer.parseInt(jobID);
		}catch(Exception e){
			_jobId = -1;
		}
		Job job = this.professionJobService.findJobByJobId(_jobId);
		req.setAttribute("Job", job);
		return "professionjob/addJobGroup.html";
	}
	
	/**
	 * 功能描述：初始化岗位页面信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-03 下午1:37:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="addInitJob")
	public String addInitJob(HttpServletRequest req, HttpServletResponse resp){
		String proID = req.getParameter("proID");
		String jobID = req.getParameter("jobID");
		String jobPID = req.getParameter("jobPID");
		Job job = null;
		if(StringUtils.isNotEmpty(proID)&&StringUtils.isEmpty(jobPID)){
			int _proId = 0;
			try{
				_proId = Integer.parseInt(proID);
			}catch(Exception e){
				_proId = -1;
			}
			job = new Job();
			job.setProID(_proId);
		}else if(StringUtils.isNotEmpty(jobID)){
			int _jobId = 0;
			try{
				_jobId = Integer.parseInt(jobID);
			}catch(Exception e){
				_jobId = -1;
			}
			job = this.professionJobService.findJobByJobId(_jobId);
		}else if(StringUtils.isNotEmpty(jobPID)&&StringUtils.isNotEmpty(proID)){
			job = new Job();
			int _jobPID = 0;
			int _proId = 0;
			try{
				_jobPID = Integer.parseInt(jobPID);
			}catch(Exception e){
				_jobPID = -1;
			}
			job.setJobPID(_jobPID);
			try{
				_proId = Integer.parseInt(proID);
			}catch(Exception e){
				_proId = -1;
			}
			job.setProID(_proId);
		}
		req.setAttribute("Job", job);
		req.setAttribute("resourcesWebSite", resourcesWebSite);
		return "professionjob/addJobProfession.html";
	}
	
	/**
	 * 
	 * 功能描述：查看行业信息
	 *
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-01 下午9:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="viewProfession")
	public String viewProfession(HttpServletRequest req, HttpServletResponse resp){
		String proID = req.getParameter("proID");
		String modifyFlag = req.getParameter("modify");
		int _proId = -1;
		try{
			_proId = Integer.parseInt(proID);
		}catch(Exception e){
			_proId = -1;
		}
		List<Profession> result = professionJobService.findChildPrfession(_proId);
		req.setAttribute("Profession", result.size() > 0 ? result.get(0) : null);
		req.setAttribute("ModifyFlag", modifyFlag);
		if(!StringUtils.isEmpty(modifyFlag) && Boolean.valueOf(modifyFlag)==true){
			return "professionjob/modifyProfessionJob.html";
		}else{
			return "professionjob/viewProfessionJob.html";
		}
		
	} 
	
	@RequestMapping(value="setJobIsVisable")
	public void setJobIsVisable(HttpServletRequest req, HttpServletResponse resp){
		String jobID = req.getParameter("jobID");
		String jobPID = req.getParameter("jobPID");
		String jobIsVisible = req.getParameter("jobIsVisible");
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(jobID)&&StringUtils.isNotEmpty(jobPID)){
			int courseNum = professionJobService.getJobCourseNum(Collections.singletonMap("jobID", (Object)jobID));
			if(courseNum>0){
				ret.put("success", Boolean.FALSE);
				ret.put("errorMsg", "您要屏蔽的岗位或者行业下的岗位中包含课程，请先修改课程所属岗位后再进行屏蔽");
			}else{
				Job job = new Job();
				try {
					job.setJobID((Integer.parseInt(jobID)));
					job.setJobIsVisible(Short.parseShort(jobIsVisible));
					job.setJobUtime(VeDate.getNowDate());
					job.setJobCollectCount(-1);
					job.setJobRecommend(-1);
					job.setJobLevel(-1);
					professionJobService.updateJob(job);
					ret.put("success", Boolean.TRUE);
					ret.put("retMsg", "操作成功!");
				} catch (ServiceException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
					ret.put("success", Boolean.FALSE);
					ret.put("errorMsg", e.getMessage());
				}
			}
		}else if(StringUtils.isNotEmpty(jobID)&&StringUtils.isEmpty(jobPID)){
			int courseNum = professionJobService.getJobCourseNum(Collections.singletonMap("jobPID", (Object)jobID));
			if(courseNum>0){
				ret.put("success", Boolean.FALSE);
				ret.put("errorMsg", "您要屏蔽的岗位或者行业下的岗位中包含课程，请先修改课程所属岗位后再进行屏蔽");
			}else{
				try {
					Map<String,Object> parameters = new HashMap<String,Object>();
					parameters.put("jobIsVisible", jobIsVisible);
					parameters.put("jobPID", jobID);
					professionJobService.updateJobVisibleWithParameters(parameters);
					ret.put("success", Boolean.TRUE);
					ret.put("retMsg", "操作成功!");
				} catch (ServiceException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
					ret.put("success", Boolean.FALSE);
					ret.put("errorMsg", e.getMessage());
				}
			}
		}else{
			ret.put("success", Boolean.FALSE);
			ret.put("errorMsg", "ID不能为空!");
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(ret));
	}
	
	@RequestMapping(value="setProfessionIsVisable")
	public void setProfessionIsVisable(HttpServletRequest req, HttpServletResponse resp){
		String proID = req.getParameter("proID");
		String proIsVisible = req.getParameter("proIsVisible");
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(proID)){
			int courseNum = professionJobService.getJobCourseNum(Collections.singletonMap("proID", (Object)proID));
			if(courseNum>0){
				ret.put("success", Boolean.FALSE);
				ret.put("errorMsg", "您要屏蔽的岗位或者行业下的岗位中包含课程，请先修改课程所属岗位后再进行屏蔽");
			}else{
				Profession p = new Profession();
				try {
					p.setProID(Integer.parseInt(proID));
					p.setProIsVisible(Short.parseShort(proIsVisible));
					p.setProRecommend(-1);
					p.setProUtime(VeDate.getNowDate());
					professionJobService.updateProfessionVisable(p);
					ret.put("success", Boolean.TRUE);
					ret.put("retMsg", "操作成功!");
				} catch (ServiceException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
					ret.put("success", Boolean.FALSE);
					ret.put("errorMsg", e.getMessage());
				}
			}
		}else{
			ret.put("success", Boolean.FALSE);
			ret.put("errorMsg", "ID不能为空!");
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(ret));
	}
	
	/**
	 * 功能描述：保存和更新岗位信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-03 下午9:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="saveOrUpdateJob")
	public void saveOrUpdateJob(HttpServletRequest req, HttpServletResponse resp){
		String modifyFlag = req.getParameter("modifyFlag");
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters = this.buildParametersToMap(parameters, req);
		Job job = gson.fromJson(gson.toJson(parameters), Job.class);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		log.debug(modifyFlag);
		if(!StringUtils.isEmpty(modifyFlag) && Boolean.valueOf(modifyFlag)==false){
			job.setJobCtime(new Date());
			//add by:dongs .start
			job.setJobUtime(VeDate.getNowDate());
			job.setJobRecommend(0);
			 
			//end.
			try {
				this.professionJobService.saveJob(job);
				jsonMap.put("success", true);
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
				jsonMap.put("success", false);
				jsonMap.put("errorMsg", e.getMessage());
			}
		}else{
			job.setJobUtime(new Date());
			try {
				professionJobService.updateJob(job);
				jsonMap.put("success", true);
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
				jsonMap.put("success", false);
				jsonMap.put("errorMsg", e.getMessage());
			}
		}
		log.debug(gson.toJson(job));
		ResponseUtils.renderHtmlText(resp, gson.toJson(jsonMap));
	}
	
	/**
	 * 功能描述：删除行业，岗位群，岗位信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-11 上午11:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="deleteProfession")
	public void removeProfession(HttpServletRequest req, HttpServletResponse resp){
		String proID = req.getParameter("proID");
		String deleteFlag = req.getParameter("deleteFlag");
		Map<String,Object> ret = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(proID)){
			int _proID = -1;
			try{
				_proID = Integer.parseInt(proID);
			}catch(Exception e){
				_proID = 0;
			}
			List<Job> jobs = professionJobService.findChildJobByProId(_proID);
			if(StringUtils.isNotEmpty(deleteFlag) && Boolean.valueOf(deleteFlag)){
				List<Integer> ids = new ArrayList<Integer>();
				for(Job job : jobs){
					ids.add(job.getJobID());
				}
				try {
					professionJobService.removeProfessionAndChildJob(_proID, ids);
					ret.put("success", "true");
				} catch (ServiceException e) {
					log.error(e);
					e.printStackTrace();
					ret.put("success", "false");
					ret.put("errorMsg", "删除信息失败!");
				}
			}else{
				ret.put("success", "true");
				ret.put("children", ""+jobs.size());
			}
		}else{
			ret.put("success", "false");
			ret.put("errorMsg", "您选择删除的信息!");
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(ret));
	}
	
	/**
	 * 功能描述：删除岗位群或者岗位信息
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-09 下午1:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="deleteJob")
	public void removeJob(HttpServletRequest req, HttpServletResponse resp){
		String jobID = req.getParameter("jobID");
		String deleteFlag = req.getParameter("deleteFlag");
		Map<String,Object> ret = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(jobID)){
			int _jobID = -1;
			try{
				_jobID = Integer.parseInt(jobID);
			}catch(Exception e){
				_jobID = 0;
			}
			List<Job> jobs = this.professionJobService.findChildJobByJobPID(_jobID);
			if(StringUtils.isNotEmpty(deleteFlag) && Boolean.valueOf(deleteFlag)){
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(_jobID);
				for(Job job : jobs){
					ids.add(job.getJobID());
				}
				try {
					this.professionJobService.remove(ids);
					ret.put("success", "true");
				} catch (ServiceException e) {
					log.error(e);
					e.printStackTrace();
					ret.put("success", "false");
					ret.put("errorMsg", "删除信息失败!");
				}
			}else{
				ret.put("success", "true");
				ret.put("children", ""+jobs.size());
			}
		}else{
			ret.put("success", "false");
			ret.put("errorMsg", "您选择删除的信息!");
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(ret));
	}
	
	/**
	 * 
	 * 功能描述：保存和更新行业信息
	 *
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-01 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="saveOrUpdateProfession")
	public void saveOrUpdateProfession(Profession profession,HttpServletRequest req, HttpServletResponse resp){
		String modifyFlag = req.getParameter("ModifyFlag");
		if(!StringUtils.isEmpty(modifyFlag) && Boolean.valueOf(modifyFlag)==true){
			profession.setProUtime(VeDate.getNow());
		}else{
			profession.setProCtime(VeDate.getNow());
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try{
			profession.setProDescription("-");//必填字段，页面上面没有让用户输入
			if(!StringUtils.isEmpty(modifyFlag) && Boolean.valueOf(modifyFlag)==true){
				professionJobService.updateProfession(profession);
			}else{
				professionJobService.saveProfession(profession);
			}
			jsonMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(resp, gson.toJson(jsonMap));
	}
	
	/**
	 * 
	 * 功能描述：分页查询行业信息
	 *
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-03-24 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="listAllProfession")
	public void listAllProfession(HttpServletRequest req, HttpServletResponse resp){
		//初始化pagination context 
		PaginationContext<Profession> page = this.initPaginationInfo(new PaginationContext<Profession>(), req);
		String action = String.valueOf(page.getParameters().get("action"));
		if(StringUtils.isNotEmpty(action)&&action.equalsIgnoreCase("children")){
			String proID = String.valueOf(page.getParameters().get("proID"));
			String jobPID = String.valueOf(page.getParameters().get("jobPID"));
			int _proId = -1,_jobPID = -1;
			try{
				_proId = Integer.parseInt(proID);
			}catch(Exception e){
				_proId = -1;
			}
			try{
				_jobPID = Integer.parseInt(jobPID);
			}catch(Exception e){
				_jobPID = -1;
			}
			List<Job> result = professionJobService.findChildJob(_proId, _jobPID);
			if(_jobPID>-1){
				for(Job job : result){
					job.setState(Job.OPEN);
				}
			}
			ResponseUtils.renderHtmlText(resp, gson.toJson(result));
		}else{
			//根据pagination context 查询数据
			page = professionJobService.findProfessionPageQuery(page);
			//填充total和rows的数据给前台
			Map<String, Object> jsonMap = this.buildRetPageInfo(page);
			//返回给前台json数据
			ResponseUtils.renderHtmlText(resp, gson.toJson(jsonMap));
		}
	}
	
	@RequestMapping(value="uploadjobProspectImg")
	public void uploadjobProspectImg(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			MultipartFile jobFile=multipartRequest.getFile("editjobFile");
			//上传图片
			jsonMap=WebUtils.uploadImg(jobFile, applicationUrl+imgUrl);
			//如果已经上传了一张，删除前一张
			String fileNamed=req.getParameter("fileNamed");
		 	if(null !=fileNamed && !fileNamed.equals("")){
		 		WebUtils.delUserImg( fileNamed,applicationUrl);
		 	}
		    //图片相对路径用于页面显示
		    jsonMap.put("urlPath", imgUrl);
		    log.info("图片上传路径"+applicationUrl+imgUrl);
		}catch (Exception e) {
			jsonMap.put("userFileName",null);
			jsonMap.put("message", "上传失败");
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		ResponseUtils.renderHtmlText(resp, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value="uploadJobImg")
	public void uploadJobImg(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			MultipartFile jobFile=multipartRequest.getFile("jobImgFiles");
			//上传图片
			jsonMap=WebUtils.uploadImg(jobFile, applicationUrl+imgUrl);
			//如果已经上传了一张，删除前一张
			String fileNamed=req.getParameter("fileNamed");
		 	if(null !=fileNamed && !fileNamed.equals("")){
		 		WebUtils.delUserImg( fileNamed,applicationUrl);
		 	}
		    //图片相对路径用于页面显示
		    jsonMap.put("urlPath", imgUrl);
		    log.info("图片上传路径"+applicationUrl+imgUrl);
		}catch (Exception e) {
			jsonMap.put("userFileName",null);
			jsonMap.put("message", "上传失败");
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		ResponseUtils.renderHtmlText(resp, this.gson.toJson(jsonMap));
	}
}
