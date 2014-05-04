/**
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.job;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.dao.mycollect.MyCollectDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.mycollect.MyCollectService;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobAuthenticationVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.mycollect.MyCollectVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.user.UserVo;
/**
 * 功能描述：完成岗位详情页面显示，包括岗位介绍、认证培训、培训课程等。
 * 
 * @author 刘祚家
 * 时间：2013-1-9
 *
 */
@Controller
public class PositionDetailController {
	private static final Log logger = LogFactory.getLog(PositionDetailController.class);
	
    @Autowired
	 private JobService jobService;
    @Autowired
	 private ProfessionService professionService;
   
    @Autowired
	 private MyCollectService myCollectService;
    @Autowired
	private MyCollectDao myCollectDao;
    
    /**
     * 根据岗位ID得到岗位基本信息
     * @author 刘祚家
     * @param jobId
     * @param modelMap
     * @param request
     * @param response
     * @return 岗位对象
     * @throws LoginException
     */
    @RequestMapping("/job/positiondetail/{jobId}/")
	public String  positionDetailIndex(@PathVariable int jobId,ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
        JobVo jobVo = new JobVo();//岗位
        JobVo jobGroupVo = new JobVo();//岗位群 
        ProfessionVo professionVo=new ProfessionVo();//行业
        
        UserVo userVo = new UserVo();
		try{
			userVo = SiteUtils.getUser(request);
		}catch (Exception e) {}
		
		try {
			jobVo = jobService.getPositionDetail(userVo,jobId);
			
			if(jobVo!=null){
				List<JobAuthenticationVo> jobAuthenticationList=new ArrayList<JobAuthenticationVo>();
				List<JobAuthenticationVo> jobAuthRecommendList=new ArrayList<JobAuthenticationVo>();
				
				List<CourseJobVo> courseJobList=new ArrayList<CourseJobVo>();
				List<CourseJobVo> courseJobRecommendList=new ArrayList<CourseJobVo>();
				List<CourseJobVo> courseJobAllList=new ArrayList<CourseJobVo>();
				
				//根据岗位ID得到岗位认证信息
				jobAuthenticationList=jobService.getJobAuthenticationList(jobVo.getJobID());
				jobVo.setJobAuthenticationList(jobAuthenticationList);
				
				//根据岗位ID得到岗位推荐认证信息List
				jobAuthRecommendList=jobService.getJobAuthRecommendList(jobVo.getJobID());
				jobVo.setJobAuthRecommendList(jobAuthRecommendList);
				
				
				//根据岗位ID得到岗位培训课程最新3条信息List
				courseJobList=jobService.getCourseJobList(jobVo.getJobID());
				jobVo.setCourseJobList(courseJobList);
				
				//根据岗位ID得到岗位推荐课程信息List
				courseJobRecommendList=jobService.getCourseJobRecommendList(jobVo.getJobID());
				jobVo.setCourseJobRecommendList(courseJobRecommendList);
				
				//根据岗位ID得到岗位所有的培训课程
				courseJobAllList=jobService.getCourseJobAllList(jobVo.getJobID());
				jobVo.setCourseJobAllList(courseJobAllList);
				
				//根据岗位群ID取得,岗位群
				if(jobVo.getJobPID()!=0){
					List<JobVo> job = null;
					jobGroupVo=jobService.getPositionDetail(userVo,jobVo.getJobPID());
					job = jobService.getJobByJobGroupId(jobId);
					String jobname = jobGroupVo.getJobName();
					int jobDetailId = jobGroupVo.getJobID();
					modelMap.put("jobName", jobname);
					modelMap.put("jobId", jobDetailId);
				}
				//根据行业ID取得行业
				if(jobVo.getProID()!=0){
					professionVo =professionService.getProfessionByProId(jobVo.getProID());
					if(professionVo!=null&&!"".equals(professionVo)){
						String proname = professionVo.getProName();
						int proId = professionVo.getProID();
						modelMap.put("proId", proId);
						modelMap.put("proName", proname);
					}
				}
				
			}
		} catch (BaseException e) {
			logger.error("get postition info exception.", e);
		}
       
    	modelMap.put("positionDetailObj", jobVo);
    	modelMap.put("jobGroupVo", jobGroupVo);
    	modelMap.put("professionVo", professionVo);
    	
		return "job/positionDetails/positionDetail.htm";
	}

    
    /**
     * 根据岗位ID得到岗位基本信息 (第二版本)
     * @author 刘祚家
     * @param jobId
     * @param modelMap
     * @param request
     * @param response
     * @return 岗位对象
     * @throws LoginException
     */
    @RequestMapping("/job/positiondetails/{jobId}/")
	public String  positionDetailsIndex(@PathVariable int jobId,ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
        JobVo jobVo = new JobVo();//岗位
        JobVo jobGroupVo = new JobVo();//岗位群 
        ProfessionVo professionVo=new ProfessionVo();//行业
        List<JobVo> jobList=new ArrayList<JobVo>(); //其他相关岗位
        UserVo userVo = new UserVo();
		try{
			userVo = SiteUtils.getUser(request);
		}catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		try {
			jobVo = jobService.getPositionDetail(userVo,jobId);
			
			if(jobVo!=null){
				List<JobAuthenticationVo> jobAuthenticationList=new ArrayList<JobAuthenticationVo>();
				List<JobAuthenticationVo> jobAuthRecommendList=new ArrayList<JobAuthenticationVo>();
				
				List<CourseJobVo> courseJobList=new ArrayList<CourseJobVo>();
				List<CourseJobVo> courseJobRecommendList=new ArrayList<CourseJobVo>();
				List<CourseJobVo> courseJobAllList=new ArrayList<CourseJobVo>();
				
				//根据岗位ID得到岗位认证信息
				jobAuthenticationList=jobService.getJobAuthenticationList(jobVo.getJobID());
				jobVo.setJobAuthenticationList(jobAuthenticationList);
				
				//根据岗位ID得到岗位推荐认证信息List
				jobAuthRecommendList=jobService.getJobAuthRecommendList(jobVo.getJobID());
				jobVo.setJobAuthRecommendList(jobAuthRecommendList);
				
				
				//根据岗位ID得到岗位培训课程最新3条信息List
				courseJobList=jobService.getCourseJobList(jobVo.getJobID());
				jobVo.setCourseJobList(courseJobList);
				
				//根据岗位ID得到岗位推荐课程信息List
				courseJobRecommendList=jobService.getCourseJobRecommendList(jobVo.getJobID());
				jobVo.setCourseJobRecommendList(courseJobRecommendList);
				
				//根据岗位ID得到岗位所有的培训课程
				courseJobAllList=jobService.getCourseJobAllList(jobVo.getJobID());
				jobVo.setCourseJobAllList(courseJobAllList);
				
				//根据岗位群ID取得,岗位群
				if(jobVo.getJobPID()!=0){
					List<JobVo> job = null;
					jobGroupVo=jobService.getPositionDetail(userVo,jobVo.getJobPID());
					job = jobService.getJobByJobGroupId(jobId);
					String jobname = jobGroupVo.getJobName();
					int jobDetailId = jobGroupVo.getJobID();
					modelMap.put("jobName", jobname);
					modelMap.put("jobId", jobDetailId);
				}
				//根据行业ID取得行业
				if(jobVo.getProID()!=0){
					professionVo =professionService.getProfessionByProId(jobVo.getProID());
					if(professionVo!=null&&!"".equals(professionVo)){
						String proname = professionVo.getProName();
						int proId = professionVo.getProID();
						modelMap.put("proId", proId);
						modelMap.put("proName", proname);
					}
				}
				
				//取得该岗位岗位群下其他相关岗位
				jobList=jobService.getRelateJob(userVo,jobVo);
				modelMap.put("jobList", jobList);
				
			}
		} catch (BaseException e) {
			logger.error("get postition info exception.", e);
		}
       
    	modelMap.put("positionDetailObj", jobVo);
    	modelMap.put("jobGroupVo", jobGroupVo);
    	modelMap.put("professionVo", professionVo);
    	
		return "job/positionDetails/positionDetails.htm";
	}
    
    
    
    /**
	 * 
	 * 功能描述：岗位收藏
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-12
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	@RequestMapping("/positiondetail/updateMyCollect.ajax")
	public void updateMyCollect(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		JobVo jobVo = new JobVo();
		String jobId = RequestUtils.getQueryParam(request, "jobId");
		if (jobId == null || jobId.equals("")) {
			jobId = "0";
		}
		// 取得登录用户对象
		UserVo userVo=new UserVo();
		try{
			userVo = SiteUtils.getUser(request);
		}catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		
		if(userVo.getUserId()!=null){
			MyCollectVo myCollectVo = new MyCollectVo();// 收藏明细对象
			if (userVo != null && !jobId.equals("0")) {
				List<MyCollectVo> myCollectVos = new ArrayList<MyCollectVo>();
				try {
					myCollectVos = myCollectService.getCollectListByUserId(userVo.getUserId(),Integer.parseInt(jobId), 1);
				} catch (Exception e) {
					logger.error(e); e.printStackTrace();
				}
				if (myCollectVos.size() == 0) {
					myCollectVo.setCollectType(1);
					myCollectVo.setRefId(Integer.parseInt(jobId));
					myCollectVo.setUserId(userVo.getUserId());
					myCollectService.saveMyCollect(myCollectVo,Integer.parseInt(jobId));
					jobVo = jobService.getPositionDetail(userVo,Integer.parseInt(jobId));
					JSONObject json = new JSONObject();
					json.put("flag", 0);
					json.put("jobVo", jobVo);
					ResponseUtils.renderJson(response, json.toString());
				}else{
					myCollectService.delMyCollect(userVo.getUserId(), Integer.parseInt(jobId), 1);
					jobVo = jobService.getPositionDetail(userVo,Integer.parseInt(jobId));
					JSONObject json = new JSONObject();
					json.put("flag", 2);
					json.put("jobVo", jobVo);
					ResponseUtils.renderJson(response, json.toString());
				}
			}
		}else{
			JSONObject json = new JSONObject();
			json.put("flag", 1);
			ResponseUtils.renderJson(response, json.toString());
		}
		
	}
   
}
