package com.gta.oec.controller.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.poi.xssf.eventusermodel.examples.FromHowTo;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.util.ObjectUtils;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.JobAuthenticationVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.mycollect.MyCollectVo;
import com.gta.oec.vo.trade.TradeVo;
import com.gta.oec.vo.user.UserVo;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.mycollect.MyCollectService;
import com.lowagie.text.pdf.codec.JBIG2Image;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
public class JobController {
	private static final Log logger = LogFactory.getLog(JobController.class);
      /**
       * 
       * 功能描述：职业中心
       *
       * @author  bingzhong.qin
       * <p>创建日期 ：2014-4-1 下午3:42:11</p>
       *
       * @param modelMap
       * @param request
       * @param response
       * @return
       * @throws LoginException
       *
       * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
       */
	 @RequestMapping("/job/jobCenter/")
		public String  jobInfoPage(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
		 String jobGroupId = RequestUtils.getQueryParam(request, "jobGroupId");//岗位群id
		 String tradeId = RequestUtils.getQueryParam(request,"tradeId");//行业id
		 List<TradeVo> tradeList = null;
		 List<JobVo> hotJobList = null;
		 JobVo jobVo = new JobVo();
		 jobVo.setJobRecommend(1);
			 try{
				 tradeList = jobService.getTradeList(null,0,0);				
				 hotJobList = jobService.getJobList(jobVo, 0, 0);
			 }catch (BaseException e) {
					logger.error(e); e.printStackTrace();
					logger.error("get course info exception.", e);
			}
		 modelMap.put("jobGroupId", jobGroupId);
		 modelMap.put("tradeId", tradeId);
		 modelMap.put("tradeList", tradeList);
		 modelMap.put("hotJob", hotJobList);
		 return "job/jobcenter.htm";
	 }
	 /**
	  * 
	  * 功能描述：获取岗位列表
	  *
	  * @author  bingzhong.qin
	  * <p>创建日期 ：2014-4-1 下午3:48:01</p>
	  *
	  * @param request
	  * @param response
	  * @param modelMap
	  * @return
	  *
	  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	  */
	 @RequestMapping("/job/getJobList/")
     public String  getJobList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
  		 String jobGroupId = RequestUtils.getQueryParam(request, "jobGroupId");//岗位群id
  		 String tradeId = RequestUtils.getQueryParam(request,"tradeId");//行业id  
  		 String pageNo = RequestUtils.getQueryParam(request, "pageNo");
  		 if (StringUtils.isBlank(pageNo)) {
  			pageNo = "1";
		 }
  		 PageModel pageModel = null;
  		 try {
  	  		 if (!StringUtils.isBlank(jobGroupId)&&StrUtils.isNum(jobGroupId)) {
  	  			pageModel = jobService.getJobByJobGroupId(Integer.parseInt(jobGroupId), Integer.parseInt(pageNo), 10);
  			 }
  	  		 if (!StringUtils.isBlank(tradeId)&&StrUtils.isNum(tradeId)) {
  	  			pageModel = jobService.getJobByTradeId(Integer.parseInt(tradeId), Integer.parseInt(pageNo), 10);
  	  		 }
  	  		 if (StringUtils.isBlank(tradeId)&&StringUtils.isBlank(jobGroupId)) {
			    pageModel = jobService.queryJobList(null,  Integer.parseInt(pageNo), 10);	
			}
		} catch (Exception e) {
		   logger.error(e); e.printStackTrace();
		}
		 modelMap.put("pageModel", pageModel);
		 return "job/joblist.htm";
	   }
	    /**
	     * 
	     * 功能描述：根据行业id获取岗位群 页
	     *
	     * @author  bingzhong.qin
	     * <p>创建日期 ：2014-4-1 下午3:31:58</p>
	     *
	     * @param modelMap
	     * @param request
	     * @param response
	     * @return
	     * @throws LoginException
	     *
	     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	     */
	    @RequestMapping("/job/getJobGroupPage/")
		public String  JobPage(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	

		String proId = RequestUtils.getQueryParam(request, "proId");
		List<JobVo> jobGroupList = null;
		try {
			jobGroupList = jobService.getJobGroupByProid(Integer.parseInt(proId));
		} catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		modelMap.put("jobGroup", jobGroupList);
		return "job/jobpage.htm";
		}
	
	 /**
	  * 
	  * 功能描述：收藏岗位
	  *
	  * @author  bingzhong.qin
	  * <p>创建日期 ：2014-4-2 上午11:10:41</p>
	  *
	  * @param modelMap
	  * @param request
	  * @param response
	  * @throws LoginException
	  *
	  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	  */
	 @RequestMapping(value="/job/jobCollect/")
	 public void collectJob(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) {
		 boolean flag = false;
		 boolean loginFlag = true;
		 String jobId = RequestUtils.getQueryParam(request,"jobId");
		 String type = RequestUtils.getQueryParam(request,"type");
	     try {
			UserVo userVo = SiteUtils.getUser(request);
			if ("1".equals(type)) {
				jobService.collJob(Integer.parseInt(jobId),userVo.getUserId(),1);
			}
			if ("2".equals(type)) {
				myCollectService.delMyCollect(userVo.getUserId(), Integer.parseInt(jobId), 1);
			}
			flag = true;
		  } catch (LoginException e) {
			loginFlag = false;
			logger.error(e); e.printStackTrace();
		   } catch (NumberFormatException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		 JSONObject json  = new JSONObject();
	     json.put("flag", flag);
	     json.put("loginFlag", loginFlag);
	     ResponseUtils.renderJson(response, json.toString());
	 }
	 /**
	  * 
	  * 功能描述：获取收藏的岗位
	  *
	  * @author  bingzhong.qin
	  * <p>创建日期 ：2014-4-2 下午4:51:00</p>
	  *
	  * @param modelMap
	  * @param request
	  * @param response
	  *
	  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	  */
	 @RequestMapping(value="/job/hasJobCollect/")
	 public void hasJobCollect(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) {
         try {
			UserVo userVo = SiteUtils.getUser(request);
			List<MyCollectVo> list = myCollectService.getCollectListByUserId(userVo.getUserId(), 0, 1);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = null;
			for (MyCollectVo myCollectVo : list) {
				 jsonObject = new JSONObject();
				 jsonObject.put("jobId", myCollectVo.getRefId());
				 jsonArray.add(jsonObject);				
			}
			ResponseUtils.renderJson(response, jsonArray.toJSONString());
		} catch (LoginException e) {
			logger.error(e); e.printStackTrace();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
	 }	 
	 @Autowired
	 private JobService jobService;
	 @Autowired
	 private MyCollectService myCollectService;
	 @Autowired
	 private FreeMarkerConfigurer freeMarkerConfigurer;
}
