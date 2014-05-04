/**
 * QACenter.java	  V1.0   2014年3月24日 下午4:28:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.controller.qacenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.service.profession.IProfessionService;
import com.gta.oec.cms.service.professionjob.ProfessionJobService;
import com.gta.oec.cms.service.qacenter.QACenterService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;
import com.gta.oec.cms.vo.qacenter.QuestionVo;
import com.gta.oec.cms.vo.qacenter.SearchParamtersOfQA;

/**
 * 功能描述：答疑中心管理控制层.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Controller
@RequestMapping("/qacenter")
public class QACenterController extends BaseController<QuestionVo> {

	private static Logger log = Logger.getLogger(QuestionVo.class);

	/**
	 * 功能描述：进入答疑管理页面.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午9:38:09
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/main")
	public String enterMain(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		log.info("enter QACenterController!");
		request.setAttribute("page", this.gson.toJson(new PageModel<QuestionVo>()));
		return "qacenter/qacenter_main.jsp";
	}

	/**
	 * 功能描述：答疑管理页面搜索及分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年3月30日 下午2:52:12
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/findMainPaginationJson")
	public void findMainPaginationJson(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String courseName = request.getParameter("courseName");
		String professionId = request.getParameter("professionId");
		String jobGroupId = request.getParameter("jobGroupId");
		String chosenQuestionOrNot = request.getParameter("chosenQuestionOrNot");
		String questionContent = request.getParameter("questionContent");
		String visibleQuestionOrNot = request.getParameter("visibleQuestionOrNot");
		String questionType = request.getParameter("questionType");
		String timeFrom = request.getParameter("timeFrom");
		String timeTo = request.getParameter("timeTo");
		SearchParamtersOfQA parameter = new SearchParamtersOfQA();
		if (!StringUtils.isBlank(userName)) {
			parameter.setUserName(userName.trim());
		}
		if (!StringUtils.isBlank(courseName)) {
			parameter.setCourseName(courseName.trim());
		}
		if (!StringUtils.isBlank(questionContent)) {
			parameter.setQuestionContent(questionContent.trim());
		}
		if (!StringUtils.isBlank(professionId) && StringUtils.isNumeric(professionId)) {
			parameter.setProfessionId(Integer.parseInt(professionId));
		}
		if (!StringUtils.isBlank(jobGroupId) && StringUtils.isNumeric(jobGroupId)) {
			parameter.setJobGroupId(Integer.parseInt(jobGroupId));
		}
		if (!StringUtils.isBlank(chosenQuestionOrNot) && StringUtils.isNumeric(chosenQuestionOrNot)) {
			parameter.setChosenQuestionOrNot(Integer.parseInt(chosenQuestionOrNot));
		} else {
			// 因为int型的默认值是0,设置一个不存在的值,以防误判"未传值"为"传值为0".
			parameter.setChosenQuestionOrNot(5);
		}
		if (!StringUtils.isBlank(visibleQuestionOrNot)
				&& StringUtils.isNumeric(visibleQuestionOrNot)) {
			parameter.setVisibleQuestionOrNot(Integer.parseInt(visibleQuestionOrNot));
		} else {
			// 因为int型的默认值是0,设置一个不存在的值,以防误判"未传值"为"传值为0".
			parameter.setVisibleQuestionOrNot(5);
		}
		if (!StringUtils.isBlank(questionType) && StringUtils.isNumeric(questionType)) {
			parameter.setQuestionType(Integer.parseInt(questionType));
		} else {
			// 因为int型的默认值是0,设置一个不存在的值,以防误判"未传值"为"传值为0".
			parameter.setQuestionType(5);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (!StringUtils.isBlank(timeFrom)) {
				Date timeFromDate = simpleDateFormat.parse(timeFrom);
				parameter.setTimeFrom(timeFromDate);
			}
			if (!StringUtils.isBlank(timeTo)) {
				Date timeToDate = simpleDateFormat.parse(timeTo);
				parameter.setTimeTo(timeToDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		PaginationContext<QuestionVo> pc = new PaginationContext<QuestionVo>();
		pc.setParameter(parameter);
		// 初始化分页对象.
		// 不带查询参数.
		// PageModel<QuestionVo> pm=new PageModel<QuestionVo>();
		// pm = initPaginationInfo(pm, request);
		// 带查询参数.

		pc = initPaginationInfo(pc, request);
		try {
			pc = qaService.findQuestionsPagination(pc);
		} catch (ServiceException e) {
			log.debug(e);
			e.printStackTrace();
		}
		log.debug(this.gson.toJson(pc));
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// total键 存放总记录数，必须的
		jsonMap.put("total", pc.getTotalSize());
		// rows键 存放每页记录 list
		jsonMap.put("rows", pc.getResult());
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}

	/**
	 * 功能描述：级联根据获取一级的行业下拉列表json.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年3月30日 上午11:30:42
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/getProfessionListJson")
	public void getProfessionListJson(HttpServletRequest request, HttpServletResponse response) {
		// 查询所有行业信息
		List<Profession> professionList = professionService.getAllProfessions();
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject1st = new JsonObject();
		jsonObject1st.addProperty("id", "0");
		jsonObject1st.addProperty("text", "请选择");
		jsonObject1st.addProperty("selected", true);
		jsonArray.add(jsonObject1st);
		for (Profession profession : professionList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", profession.getProID());
			jsonObject.addProperty("text", profession.getProName());
			jsonArray.add(jsonObject);
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonArray));

	}

	/**
	 * 功能描述：级联根据一级所选的行业获取二级的岗位群下拉列表json.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年3月30日 上午11:29:51
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/getJobGroupListJson")
	public void getJobGroupListJson(HttpServletRequest request, HttpServletResponse response) {
		String proId = request.getParameter("id");
		if (StringUtils.isBlank(proId) || !StringUtils.isNumeric(proId)) {

			// 异常处理.

		}
		// 查询所有岗位群信息
		try {
			List<Job> jobGroupList = professionJobService.findJobGroupListByProId(Integer
					.parseInt(proId));
			JsonArray jsonArray = new JsonArray();
			JsonObject jsonObject1st = new JsonObject();
			jsonObject1st.addProperty("id", "0");
			jsonObject1st.addProperty("text", "请选择");
			jsonObject1st.addProperty("selected", true);
			jsonArray.add(jsonObject1st);
			for (Job job : jobGroupList) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("id", job.getJobID());
				jsonObject.addProperty("text", job.getJobName());
				jsonArray.add(jsonObject);
			}
			System.out.println(jsonArray.toString());
			ResponseUtils.renderJson(response, jsonArray.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：异步修改一个问题的可见与否.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午9:39:17
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/changevisibleQuestionOrNot/", method = RequestMethod.POST)
	public void changevisibleQuestionOrNot(HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		boolean flag = true;
		String questionId = request.getParameter("questionId");
		if (StringUtils.isBlank(questionId) || !StringUtils.isNumeric(questionId)) {
			// 异常处理.
			msg = "参数不是一个正常的问题id";
			flag = false;
		}
		if (flag) {
			try {
				qaService.updateVisibleQuestionOrNot(Integer.parseInt(questionId));
				flag = true;
				msg = "修改成功!";
			} catch (ServiceException e) {
				e.printStackTrace();
				msg = "updateVisibleQuestionOrNot出现错误!";
				flag = false;
			}
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("flag", flag);
		jsonObject.addProperty("msg", msg);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 功能描述：异步修改一个问题的推荐与否.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午9:40:25
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/changeChosenQuestionOrNot/", method = RequestMethod.POST)
	public void changeChosenQuestionOrNot(HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		boolean flag = true;
		String questionId = request.getParameter("questionId");
		if (!StringUtils.isNumeric(questionId)) {
			// 异常处理.
			msg = "参数不是一个正常的问题id";
			flag = false;
		}
		if (flag) {
			try {
				//前台现在2条精选问题.
				qaService.updateChosenQuestionOrNot(Integer.parseInt(questionId), 2);
				log.info("成功修改一个问题是否精选.");
				flag = true;
				msg = "修改成功!";
			} catch (ServiceException e) {
				e.printStackTrace();
				msg = e.getMessage();
				log.error(e.getAllMessage());
				flag = false;
			}
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("flag", flag);
		jsonObject.addProperty("msg", msg);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 功能描述：异步修改多个选定的问题的可见与否.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午9:40:53
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/batchUpdateVisibleQuestionOrNot/", method = RequestMethod.POST)
	public void batchUpdateVisibleQuestionOrNot(HttpServletRequest request,
			HttpServletResponse response) {
		String[] questionIds = request.getParameterValues("questionIds[]");
		String updateType = request.getParameter("updateType");
		String msg = "";
		boolean flag = true;
		if (questionIds == null || questionIds.length <= 0) {
			msg = "没有选择任何项目.";
			flag = false;
		}
		if (StringUtils.isBlank(updateType) || !StringUtils.isNumeric(updateType)) {
			msg = "参数修改类型有误.";
			flag = false;
		}
		int[] quesIds = new int[questionIds.length];
		if (flag) {
			for (int i = 0; i < questionIds.length; i++) {
				if (StringUtils.isBlank(questionIds[i]) || !StringUtils.isNumeric(questionIds[i])) {
					// 异常处理.
					msg = "参数不是一个正常的问题id";
					flag = false;
					break;
				}
				quesIds[i] = Integer.parseInt(questionIds[i]);
			}
		}
		if (flag) {
			try {
				qaService.batchUpdateVisibleQuestionOrNot(quesIds, Integer.parseInt(updateType));
				flag = true;
				msg = "修改成功!";
			} catch (ServiceException e) {
				e.printStackTrace();
				msg = "updateChosenQuestionOrNot出现错误!";
				flag = false;
			}
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("flag", flag);
		jsonObject.addProperty("msg", msg);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * 功能描述：查看问题详情的弹出层.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午9:41:26
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping(value = "/showQuestionDital/")
	public String showQuestionDital(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String questionId = request.getParameter("questionId");
		if (StringUtils.isBlank(questionId) || !StringUtils.isNumeric(questionId)) {
			// 添加参数异常提示.
		}
		QuestionVo question = null;
		try {
			question = qaService.getQuestionDetailByQuestionId(Integer.parseInt(questionId));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		modelMap.put("question", question);
		return "qacenter/question_detail.jsp";

	}

	/**
	 * 功能描述：删除一条已经屏蔽的问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月15日 下午5:56:34
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
	@RequestMapping(value = "/deleteInvisibleQuestion/", method = RequestMethod.POST)
	public void deleteInvisibleQuestion(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String msg = "";
		boolean flag = true;
		String questionId = request.getParameter("questionId");
		if (StringUtils.isBlank(questionId) || !StringUtils.isNumeric(questionId)) {
			msg = "参数错误";
			log.error("questionId:" + questionId + msg);
			flag = false;
		}
		if (flag) {
			try {
				qaService.deleteQuestionWithAllData(Integer.parseInt(questionId));
			} catch (NumberFormatException e) {
				msg = "参数类型错误";
				flag = false;
				log.error("questionId:" + questionId + msg);
				e.printStackTrace();
			} catch (ServiceException e) {
				msg = e.getMessage();
				flag = false;
				log.error("questionId:" + questionId + msg);
				e.printStackTrace();
			}
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("flag", flag);
		jsonObject.addProperty("msg", msg);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	@Autowired
	private QACenterService qaService;
	@Autowired
	private IProfessionService professionService;
	@Autowired
	private ProfessionJobService professionJobService;
}
