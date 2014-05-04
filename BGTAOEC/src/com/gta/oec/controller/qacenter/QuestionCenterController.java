/**
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.qacenter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.springmvc.RichFreeMarkerView;
import com.gta.oec.common.web.utils.PathUtils;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.job.JobService;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.service.teacher.TeacherService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.user.UserVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 功能描述：完成答疑中心首页功能。
 * @author 刘祚家 时间：2013-1-9
 * 
 */
@Controller
public class QuestionCenterController {
	private static final Log logger = LogFactory.getLog(QuestionCenterController.class);

	@Autowired
	private QuestionCenterService questionCenterService;
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private JobService jobService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;

	/**
	 * 答疑中心首页功能
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/questionCenter/")
	public String questionCenterIndex(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {
//		System.out.println("---------------aaaaa------------");
//		long pre=System.currentTimeMillis();
		
		List<QuestionVo> newQuestionList = new ArrayList<QuestionVo>();
		List<QuestionVo> hotQuestionList = new ArrayList<QuestionVo>();
		List<UserVo> teacherList = new ArrayList<UserVo>();
		List<QuestionVo> chosenQuestionList = new ArrayList<QuestionVo>();
		List<UserVo> teacherAnswerActiveList = new ArrayList<UserVo>();
		List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
		ProfessionVo professionVo =new ProfessionVo();
		UserVo userVo = new UserVo();// SiteUtils.getUser(request);
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		// 行业ID
		// 后期可把行业ID作为参数传入-----@PathVariable int proId,
		// int proId = 1;
//		System.out.println("---------------bbbbbbb------------");
		try {
			// 获取行业 -前3个行业
			professionList = professionService.getTopProfessionList(3);//professionService.getProfessionList();
			if (professionList.size() > 0) {
				professionVo =professionList.get(0);
//				for (ProfessionVo professionVo : professionList) {
					// 根据行业ID取得最新提问10条列表信息,包括课程提问、答疑提问
					newQuestionList = questionCenterService.getNewQuestionList(professionVo.getProID(), userVo);

					// 计算创建时间与当前时间段-用于页面显示
					if (newQuestionList.size() != 0) {
						for (QuestionVo questionVo : newQuestionList) {
							questionVo.setCompareTime(this.compareTime(questionVo.getQuesCtime()));
						}
					}

					// 根据行业ID取得最热门问题10条列表信息
					hotQuestionList = questionCenterService.getHotQuestionList(professionVo.getProID(), userVo, 1, 10);

					professionVo.setNewQuestionList(newQuestionList);
					professionVo.setHotQuestionList(hotQuestionList);
//				}
			}
			// 首页名师推荐--10表示取推荐10条
			teacherList = questionCenterService.getRecommendTeacherList(10);
			// 回答活跃排名
			teacherAnswerActiveList = questionCenterService.getTeacherAnswerActiveList();
			// 首页显示精选问题
			chosenQuestionList = questionCenterService.getChosenQuestions(2);
//			System.out.println("---------------ccccccc------------");
		} catch (BaseException e) {
			logger.error("get QuestionList info exception.", e);
		}

		// modelMap.put("newQuestionList", newQuestionList);
		modelMap.put("userVo", userVo);
		modelMap.put("teacherList", teacherList);
		modelMap.put("chosenQuestionList", chosenQuestionList);
		modelMap.put("teacherAnswerActiveList", teacherAnswerActiveList);
		
		modelMap.put("professionList", professionList);
		modelMap.put("professionVo", professionVo);
		
//		long post=System.currentTimeMillis();
//		
//		System.out.println(post-pre);
		
		return "qacenter/question.htm";
	}
	
	/**
	 * 
	 * 功能描述：答疑中心首页内容
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-3
	 *         </p>
	 * 
	 * @param request
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping("/qacenter/getProVo.ajax")
	public void getProVo(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws BaseException {

		String proId = RequestUtils.getQueryParam(request, "proId");// 行业ID
		
		List<QuestionVo> newQuestionList = new ArrayList<QuestionVo>();
		List<QuestionVo> hotQuestionList = new ArrayList<QuestionVo>();
		ProfessionVo professionVo =new ProfessionVo();
		String text = "";
		UserVo userVo = new UserVo();// SiteUtils.getUser(request);
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		
		try {
			professionVo =professionService.getProfessionByProId(Integer.parseInt(proId));
			
			if (proId!=null) {
					// 根据行业ID取得最新提问10条列表信息,包括课程提问、答疑提问
					newQuestionList = questionCenterService.getNewQuestionList(Integer.parseInt(proId), userVo);

					// 计算创建时间与当前时间段-用于页面显示
					if (newQuestionList.size() != 0) {
						for (QuestionVo questionVo : newQuestionList) {
							questionVo.setCompareTime(this.compareTime(questionVo.getQuesCtime()));
						}
					}

					// 根据行业ID取得最热门问题10条列表信息
					hotQuestionList = questionCenterService.getHotQuestionList(Integer.parseInt(proId), userVo, 1, 10);

					professionVo.setNewQuestionList(newQuestionList);
					professionVo.setHotQuestionList(hotQuestionList);
					
					if (professionVo!=null ) {
						Template template = freeMarkerConfigurer.getConfiguration().getTemplate(
								"/webpage/qacenter/question_content.htm");

						modelMap.put("professionVo", professionVo);
						modelMap.put("base", request.getContextPath());
						text = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelMap);
					}
			}
			
		} catch (IOException e) {
			logger.error(e); e.printStackTrace();
		} catch (TemplateException e) {
			logger.error(e); e.printStackTrace();
		}

		ResponseUtils.renderText(response, text);
	}
	

	/**
	 * 答疑中心分类功能
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/qacenter/qsort/")
	public String questionCenterSort(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {

		UserVo loginUserVo = new UserVo();// SiteUtils.getUser(request);
		try {
			loginUserVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		PageModel pageModel = new PageModel();
		List<ProfessionVo> professionList = null;
		List<JobVo> jobGroupList = null;
		try {
			professionList = professionService.getProfessionList();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		if (professionList != null) {

			for (ProfessionVo professionVo : professionList) {
				try {
					jobGroupList = jobService.getJobGroupByProid(professionVo.getProID());
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
				if (jobGroupList != null) {
					professionVo.setJobGroupList(jobGroupList);
				}
			}
		}
		try {
			// 全部问题,第一页,1条.
			pageModel = questionCenterService.getSortQuestion(loginUserVo, 0, 0, 0, 1, 5);
		} catch (BaseException e) {
			logger.error("get QuestionList info exception.", e);
		}

		modelMap.put("pageModel", pageModel);
		modelMap.put("professionList", professionList);

		return "qacenter/questionsortmainpage.htm";
	}

	/**
	 * 
	 * 功能描述：获取分类问题的问题列表元素,根据分类列出问题.
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-1-17 下午3:54:28
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(dongs，2014-1-18 上午11:59:25，完善)
	 *         </p>
	 * @throws LoginException
	 */
	@RequestMapping("/qacenter/tosortquestionlist/")
	public void getNewQuestionList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String type = RequestUtils.getQueryParam(request, "type");
		String id = RequestUtils.getQueryParam(request, "id");
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		String flag = RequestUtils.getQueryParam(request, "flag"); // 0-表示点击加载更多
																	// 1-表示从首页最新更多进入
																	// 2-表示从首页最热更多进入
		PageModel pageModel = new PageModel();
		String text = "";
		UserVo loginUserVo = new UserVo();// SiteUtils.getUser(request);
		try {
			loginUserVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		if (StringUtils.isBlank(type)) {
			type = "0";
		}
		if (StringUtils.isBlank(id)) {
			id = "0";
		}

		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		if (StringUtils.isBlank(pageSize)) {
			pageSize = "5";
		}
		if (loginUserVo == null) {

		} else {

			try {
				pageModel = questionCenterService.getSortQuestion(loginUserVo, Integer.valueOf(flag), Integer.valueOf(type),
						Integer.valueOf(id), Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			if (null != pageModel) {
				try {
					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/qacenter/questionsortlist.htm");
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

	/**
	 * 
	 * 功能描述：更新赞数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16
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
	@RequestMapping("/questionCenter/updateAnswPraiseCount.ajax")
	public void updateAnswPraiseCount(HttpServletRequest request, HttpServletResponse response) throws LoginException {

		AnswerVo answerVo = new AnswerVo();
		String answID = RequestUtils.getQueryParam(request, "answerId");
		if (answID == null || answID.equals("")) {
			answID = "0";
		}
		// 取得登录用户对象
		UserVo userVo = new UserVo();
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		if (userVo.getUserId() != null) {
			PraiseDetailVo praiseDetailVo = new PraiseDetailVo();// 赞明细对象
			if (userVo != null && !answID.equals("0")) {
				List<PraiseDetailVo> praiseDetailVos = null;
				try {
					praiseDetailVos = questionCenterService.getPraiseDetailByUserIdByAnsId(userVo.getUserId(), Integer.parseInt(answID));

					if (praiseDetailVos.size() == 0) {
						praiseDetailVo.setAnswID(Integer.parseInt(answID));
						praiseDetailVo.setUserID(userVo.getUserId());
						praiseDetailVo.setPraiCtime(new Date());
						praiseDetailVo.setPraiUtime(new Date());

						questionCenterService.updateAnswPraiseCount(answID, praiseDetailVo,true);
						
						answerVo = questionCenterService.getAnswerVoByAnswId(Integer.parseInt(answID));
						answerVo.setFlag(0);//标识：0-已赞
					}else{//取消赞
						questionCenterService.updateAnswPraiseCount(answID, praiseDetailVos.get(0),false);
						
						answerVo = questionCenterService.getAnswerVoByAnswId(Integer.parseInt(answID));
						answerVo.setFlag(1);//标识：0-已赞
					}
				} catch (Exception e) {
					logger.error(e); e.printStackTrace();
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("flag", 0);
			json.put("answerVo", answerVo);
			ResponseUtils.renderJson(response, json.toString());
		} else {
			JSONObject json = new JSONObject();
			json.put("flag", 1);
			ResponseUtils.renderJson(response, json.toString());
		}

	}

	/**
	 * 
	 * 功能描述：更新关注问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
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
	@RequestMapping("/questionCenter/updateAttentionQuestion.ajax")
	public void updateAttentionQuestion(HttpServletRequest request, HttpServletResponse response) throws LoginException {
		String quesID = RequestUtils.getQueryParam(request, "quesID");
		if (quesID == null || quesID.equals("")) {
			quesID = "0";
		}

		UserVo userVo = new UserVo();
		try {
			userVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		if (userVo.getUserId() != null) {
			AttentionVo attentionVo = new AttentionVo();// 关注问题明细对象
			QuestionVo questionVo =new QuestionVo();
			if (userVo != null && !quesID.equals("0")) {

				List<AttentionVo> attentionList = new ArrayList<AttentionVo>();
				try {
					attentionList = questionCenterService.getAttentionQuestionByUserId(userVo.getUserId(), Integer.parseInt(quesID));

					if (attentionList.size() == 0) {
						attentionVo.setQuesID(Integer.parseInt(quesID));
						attentionVo.setUserID(userVo.getUserId());
						attentionVo.setStatus(0);// 0-表示未读
						
						attentionVo = questionCenterService.updateAttentionQuestion(quesID, attentionVo,true);// 保存关注明细，并更新关注数
						
						attentionVo.setFlag(0);
					}else{//取消关注
						attentionVo = questionCenterService.updateAttentionQuestion(quesID, attentionList.get(0),false);// 删除关注明细，并更新关注数
						attentionVo.setFlag(1);
					}
					//查询关注数
					questionVo=questionCenterService.getQuestionVo(Integer.parseInt(quesID));
					attentionVo.setAttentionNum(questionVo.getQuesAttentionCount());
					
				} catch (Exception e) {
					logger.error(e); e.printStackTrace();
				}
				JSONObject json = new JSONObject();
				json.put("flag", 0);
				json.put("attentionVo", attentionVo);
				ResponseUtils.renderJson(response, json.toString());
			}
		} else {
			JSONObject json = new JSONObject();
			json.put("flag", 1);
			ResponseUtils.renderJson(response, json.toString());
		}

	}

	/**
	 * 
	 * 功能描述：问题详情
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
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
	@RequestMapping("/questionCenter/questiondetail/{quesId}/")
	public String questiondetail(@PathVariable int quesId, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response)
			throws LoginException {

		QuestionVo questionVo = new QuestionVo();
		UserVo loginUserVo = new UserVo();// SiteUtils.getUser(request);//
											// 登录用户信息
		loginUserVo = (UserVo) request.getSession().getAttribute("_user");

		List<QuestionVo> questionList = new ArrayList<QuestionVo>();
		List<UserVo> teacherList = new ArrayList<UserVo>();
		List<QuestionUserVo> teacherListed = new ArrayList<QuestionUserVo>(); // 已邀请老师列表

		List<AnswerVo> answerList = new ArrayList<AnswerVo>();// 根据邀请老师排好序的所有回答
		Map<Integer, Integer> totalQuesAddNum = new HashMap<Integer, Integer>();// 记录对同一个老师所有的回答追问是否达到了三次

		int page = 1;// 页数
		int pageSize = 5;// 每页显示数量

		try {
			// 根据问题ID取得信息，问题答案列表信息
			questionVo = questionCenterService.getQuestionDetail(quesId, loginUserVo);

			PageModel pageModel = questionCenterService.getAllQuestionDetailAnswer(quesId, loginUserVo, page, pageSize);// 根据问题ID，取得所有回答
			answerList = pageModel.getList();

			// 相关问题
			// 取得问题的相关岗位群ID
			int jobGroupId = questionVo.getJobID();
			// 根据相关岗位群ID取5条问题
			questionList = questionCenterService.getQuestionListByJobGroupId(jobGroupId,questionVo.getQuesID(), 5);

			// 搜索推荐老师-20表示20条
			teacherList = questionCenterService.getRecommendTeacherList(20);
			StringBuffer stringBuffer = new StringBuffer("[");
			if (teacherList.size() > 0) {
				for (UserVo userVo : teacherList) {
					stringBuffer.append("{teacherId:\"" + userVo.getUserId() + "\",teacherName:\"" + userVo.getUserName() + "\"},");
				}
			}
			stringBuffer.append("]");
			modelMap.put("teacherArray", stringBuffer.toString().replace(",]", "]"));

			// 已邀请老师列表
			teacherListed = questionCenterService.getAllQuestionUserListByQuesId(quesId);

			StringBuffer stringBuffer2 = new StringBuffer("[");
			if (teacherListed.size() > 0) {
				for (QuestionUserVo questionUserVo : teacherListed) {
					stringBuffer2.append("{teacherId:\"" + questionUserVo.getTeacherID() + "\",teacherName:\""
							+ questionUserVo.getUserName() + "\"},");
				}
			}
			stringBuffer2.append("]");
			modelMap.put("teacheredArray", stringBuffer2.toString().replace(",]", "]"));

			// 查询所有回答的追加问题列表
			if (answerList.size() > 0) {
				for (AnswerVo answerVo : answerList) {
					List<QuestionAddVo> quesAddList = questionCenterService.getAllQuestionAddListByAnswId(answerVo.getAnswID());
					answerVo.setQuesAddLists(quesAddList);
				}
			}
			// 统计、检查对同一个老师所有的回答追问是否达到了三次
			List<AnswerVo> allAnswerList = questionCenterService.getAllAnswer(quesId);
			if (allAnswerList.size() > 0) {
				for (AnswerVo answerVo : allAnswerList) {
					List<QuestionAddVo> quesAddList = questionCenterService.getAllQuestionAddListByAnswId(answerVo.getAnswID());
					boolean flag = false;
					if (totalQuesAddNum.size() > 0) {
						Iterator<Map.Entry<Integer, Integer>> it = totalQuesAddNum.entrySet().iterator();
						
						while (it.hasNext()) {
							Map.Entry<Integer, Integer> entry = it.next();
							if (entry.getKey() == answerVo.getUserID()) {
								flag = true;
							}
						}
						if (flag) {
							int num = totalQuesAddNum.get(answerVo.getUserID()) + quesAddList.size();
							totalQuesAddNum.put(answerVo.getUserID(), num);
						} else {
							totalQuesAddNum.put(answerVo.getUserID(), quesAddList.size());
						}

					} else {
						totalQuesAddNum.put(answerVo.getUserID(), quesAddList.size());
					}

				}
			}

			if (totalQuesAddNum.size() > 0) {
				for (Integer key : totalQuesAddNum.keySet()) {
					for (AnswerVo answerVo : answerList) {
						if (key == answerVo.getUserID()) {
							answerVo.setTotalQuesAddNum(totalQuesAddNum.get(key));
						}
					}
				}
			}
			pageModel.setList(answerList);
			modelMap.put("pageModel", pageModel);
		} catch (BaseException e) {
			logger.error("get QuestionList info exception.", e);
		}
		modelMap.put("answerList", answerList);
		modelMap.put("questionVo", questionVo);
		modelMap.put("questionList", questionList);
		modelMap.put("teacherListed", teacherListed);

		modelMap.put("page", page);
		modelMap.put("pageSize", pageSize);
		modelMap.put("loginUserVo", loginUserVo);
		return "qacenter/questiondetails.htm";
	}

	/**
	 * 
	 * 功能描述：更多问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-19
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
	@RequestMapping("/questionCenter/morequestion/{proId}/{flag}/")
	public String morequestion(@PathVariable int proId, @PathVariable int flag, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
		List<JobVo> jobGroupList = new ArrayList<JobVo>();
		try {
			professionList = professionService.getProfessionList();
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
		if (professionList != null) {
			for (ProfessionVo professionVo : professionList) {
				try {
					jobGroupList = jobService.getJobGroupByProid(professionVo.getProID());
				} catch (BaseException e) {
					logger.error(e); e.printStackTrace();
				}
				if (jobGroupList != null) {
					professionVo.setJobGroupList(jobGroupList);
				}
			}
		}
		modelMap.put("proId", proId);
		modelMap.put("flag", flag);
		modelMap.put("professionList", professionList);

		return "qacenter/questionsortmainpage.htm";
	}

	/**
	 * 
	 * 功能描述：获取更多回答
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-23
	 *         </p>
	 * 
	 * @param request
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	@RequestMapping("/qacenter/getMoreAnswer.ajax")
	public void getMoreAnswer(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws BaseException {

		String quesId = RequestUtils.getQueryParam(request, "quesID");// 问题ID
		String page = RequestUtils.getQueryParam(request, "page");// 页数
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");// 显示条数

		UserVo loginUserVo = new UserVo();// SiteUtils.getUser(request);//
											// 登录用户信息
		loginUserVo = (UserVo) request.getSession().getAttribute("_user");

		String text = "";
		QuestionVo questionVo = new QuestionVo();
		List<QuestionUserVo> teacherListed = new ArrayList<QuestionUserVo>(); // 已邀请老师列表

		List<AnswerVo> answerList = new ArrayList<AnswerVo>();// 根据邀请老师排好序的所有回答

		try {
			if (!StringUtils.isBlank(quesId) && !StringUtils.isBlank(page) && !StringUtils.isBlank(pageSize)) {
				// 根据问题ID取得信息，问题答案列表信息
				questionVo = questionCenterService.getQuestionDetail(Integer.parseInt(quesId), loginUserVo);
				// 根据问题ID，取得分页回答
				PageModel pageModel = questionCenterService.getAllQuestionDetailAnswer(Integer.parseInt(quesId), loginUserVo,
						Integer.parseInt(page), Integer.parseInt(pageSize));
				// 已邀请老师列表
				teacherListed = questionCenterService.getAllQuestionUserListByQuesId(Integer.parseInt(quesId));
				
			
					Template template = freeMarkerConfigurer.getConfiguration().getTemplate(
							"/webpage/qacenter/questiondetails_moreanswer.htm");
					modelMap.put("pageModel", pageModel);
					modelMap.put("questionVo", questionVo);
					modelMap.put("answerList", answerList);
					modelMap.put("teacherListed", teacherListed);
					modelMap.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
					modelMap.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));	

					text = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelMap);
			
			}
		} catch (IOException e) {
			logger.error(e); e.printStackTrace();
		} catch (TemplateException e) {
			logger.error(e); e.printStackTrace();
		}

		ResponseUtils.renderText(response, text);
	}

	/**
	 * 提交问题-进入问题提交表单页面
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/questionCenter/createQuestion/")
	public String createQuestion(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {
		// 先判断用户是否登录
		UserVo isUserVo = SiteUtils.getUser(request);
		
		if (isUserVo != null && isUserVo.getUserId() != 0) {// 如果用户已登录
			
			List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
			List<UserVo> teacherList = new ArrayList<UserVo>();

			try {
				// 得到行业
				professionList = professionService.getProfessionList();
				// 搜索推荐老师-20表示20条
				teacherList = questionCenterService.getRecommendTeacherList(20);
				StringBuffer stringBuffer = new StringBuffer("[");
				if (teacherList.size() > 0) {
					for (UserVo userVo : teacherList) {
						stringBuffer.append("{teacherId:\"" + userVo.getUserId() + "\",teacherName:\"" + userVo.getUserName() + "\"},");
					}
				}
				stringBuffer.append("]");
				modelMap.put("teacherArray", stringBuffer.toString().replace(",]", "]"));
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

			// 设置标识，是0-从提问入口，1-求助入口
			modelMap.put("flag", 0);

			modelMap.put("professionList", professionList);
			return "qacenter/createQuestion.htm";
		} else {// 用户未登录
			System.out.println("bbbbbb");
			return "redirect:/user/login/";
		}

	}

	/**
	 * 提交问题-进入问题提交表单页面(点击求助)
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/questionCenter/helpme/{teacherId}")
	public String helpme(@PathVariable int teacherId, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response)
			throws LoginException {
		// 先判断用户是否登录
		UserVo isUserVo = SiteUtils.getUser(request);

		if (isUserVo != null && isUserVo.getUserId() != 0) {// 如果用户已登录
			List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
			List<JobVo> jobGroupList = new ArrayList<JobVo>();
			JobVo jobVo = new JobVo();
			UserVo teacherUserVo = new UserVo();
			List<UserVo> teacherList = new ArrayList<UserVo>();

			try {
				// 老师基本信息
				teacherUserVo = userService.getUserById(teacherId);
				// 选择分类
				// 根据老师ID,得到老师所属行业、岗位群
				jobVo = jobService.getTeacherProfessionAddJob(teacherId);
				// 所有行业
				professionList = professionService.getProfessionList();
				// 老师所属行业下的所有岗位群
				jobGroupList = jobService.getJobGroupByProid(jobVo.getProID());

				// 搜索推荐老师-20表示20条
				teacherList = questionCenterService.getRecommendTeacherList(20);
				StringBuffer stringBuffer = new StringBuffer("[");
				if (teacherList.size() > 0) {
					for (UserVo userVo : teacherList) {
						stringBuffer.append("{teacherId:\"" + userVo.getUserId() + "\",teacherName:\"" + userVo.getUserName() + "\"},");
					}
				}
				stringBuffer.append("]");
				modelMap.put("teacherArray", stringBuffer.toString().replace(",]", "]"));
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

			// 设置标识，是0-从提问入口，1-求助入口
			modelMap.put("flag", 1);

			modelMap.put("teacherUserVo", teacherUserVo);
			modelMap.put("jobVo", jobVo);
			modelMap.put("professionList", professionList);
			modelMap.put("jobGroupList", jobGroupList);
			return "qacenter/createQuestion.htm";
		} else {// 用户未登录
			return "redirect:/user/login/";
		}
	}

	/**
	 * 提交问题-保存问题
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping(value = "/questionCenter/saveQuestion/", method = RequestMethod.POST)
	public String saveQuestion(@ModelAttribute("form") QuestionVo questionVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			try {
				questionVo=questionCenterService.saveQuestionByUser(questionVo, user);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
			}
			return "redirect:/questionCenter/questiondetail/" + questionVo.getQuesID() + "/";
		} else {// 未登录
			return "redirect:/user/login/";
		}

	}

	/**
	 * 
	 * 功能描述：追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
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
	@RequestMapping("/questionCenter/questionaddquestion/{quesId}/{answId}/")
	public String questionaddquestion(@PathVariable int quesId, @PathVariable int answId, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		QuestionVo questionVo = new QuestionVo();
		AnswerVo answerVo = new AnswerVo();
		List<UserVo> teacherList = new ArrayList<UserVo>();
		List<QuestionAddVo> questionAddList = new ArrayList<QuestionAddVo>();// 追加问题列表

		UserVo loginUserVo = SiteUtils.getUser(request);// 登录用户信息
		try {
			// 根据问题ID取得信息，追问回答信息
			questionVo = questionCenterService.getQuestionDetail(quesId, loginUserVo);
			answerVo = questionCenterService.getAnswerVoByAnswId(answId);// 根据回答ID，取得追问回答信息

			// 首页名师推荐--10表示取推荐10条
			teacherList = questionCenterService.getRecommendTeacherList(10);

			// 根据回答ID,取得追加问题，并取得所有追加问题回答
			questionAddList = questionCenterService.getAllQuestionAddListByAnswId(answId);

		} catch (BaseException e) {
			logger.error("get QuestionList info exception.", e);
		}

		modelMap.put("questionVo", questionVo);
		modelMap.put("answerVo", answerVo);
		modelMap.put("teacherList", teacherList);
		modelMap.put("questionAddList", questionAddList);
		return "qacenter/questionaddquestion.htm";
	}

	/**
	 * 提交问题-保存追加问题
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping(value = "/questionCenter/saveQuestionAdd/", method = RequestMethod.POST)
	public String saveQuestionAdd(@ModelAttribute("form") QuestionAddVo questionAddVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			try {
				questionCenterService.saveQuestionAddByUser(questionAddVo, user);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			return "redirect:/questionCenter/questiondetail/" + questionAddVo.getQuesID() + "/";
		} else {// 未登录
			return "redirect:/user/login/";
		}
	}

	/**
	 * 提交问题-保存问题说明
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping(value = "/questionCenter/saveQuesDescription/", method = RequestMethod.POST)
	public String saveQuesDescription(@ModelAttribute("form") QuestionVo questionVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			try {
				questionCenterService.updateQuestion(questionVo, user);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			return "redirect:/questionCenter/questiondetail/" + questionVo.getQuesID() + "/";
		} else {// 未登录
			return "redirect:/user/login/";
		}
	}

	/**
	 * 提交问题-保存问题回答 -问题详情页回答
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping(value = "/questionCenter/saveAnswer/", method = RequestMethod.POST)
	public String saveAnswer(@ModelAttribute("form") AnswerVo answerVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			int quesID = answerVo.getQuesID();
			try {
				questionCenterService.saveAnswerVo(answerVo, user);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			return "redirect:/questionCenter/questiondetail/" + quesID + "/";
		} else {// 未登录
			return "redirect:/user/login/";
		}
	}

	/**
	 * 提交问题-保存问题回答 -老师中心
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping("/questionCenter/saveTeacherAnswer.ajax")
	public void saveTeacherAnswer(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {

		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		String quesID = RequestUtils.getQueryParam(request, "quesID");// 问题ID
		String quesAddID = RequestUtils.getQueryParam(request, "quesAddID");// 问题ID
		String answContent = RequestUtils.getQueryParam(request, "answContent");// 回答内容
		AnswerVo answerVo = new AnswerVo();
		answerVo.setQuesID(Integer.parseInt(quesID));
		answerVo.setQuesAddID(Integer.parseInt(quesAddID));
		answerVo.setAnswContent(answContent);
		// TeacherVo teacherVo=new TeacherVo();
		int unsolvedQuesNum = 0;
		int quesAddCout = 0;
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			try {
				questionCenterService.saveAnswerVo(answerVo, user);

				// teacherVo =
				// teacherService.getTeacherByUserId(user.getUserId());
				// if (teacherVo!=null) {
				QuestionUserVo questionUser = new QuestionUserVo();
				questionUser.setStatus(0);
				// questionUser.setTeacherID(teacherVo.getId());
				questionUser.setTeacherID(user.getUserId());
				unsolvedQuesNum = teacherService.countQuestionPageModel(questionUser, 5);// 除1,2以外全部表示查询全部未解答.

				// 追问数量
				quesAddCout = teacherService.quesAddCout(questionUser);
				unsolvedQuesNum = unsolvedQuesNum + quesAddCout;
				// }

			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}

			JSONObject json = new JSONObject();
			json.put("flag", 0);
			json.put("unsolvedQuesNum", unsolvedQuesNum);
			ResponseUtils.renderJson(response, json.toString());
		} else {
			JSONObject json = new JSONObject();
			json.put("flag", 1);
			ResponseUtils.renderJson(response, json.toString());
		}
	}

	/**
	 * 保存邀请回答老师
	 * 
	 * @author 刘祚家
	 * @param request
	 * @param response
	 * @return String
	 * @throws LoginException
	 */
	@RequestMapping(value = "/questionCenter/saveQuestionInviteTeacher/", method = RequestMethod.POST)
	public String saveQuestionInviteTeacher(@ModelAttribute("form") QuestionVo questionVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		// 登录用户
		UserVo user = SiteUtils.getUser(request);
		if (user != null && user.getUserId() != 0) {// 如果用户已登录
			try {
				questionCenterService.saveQuestionInviteTeacher(questionVo, user);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			return "redirect:/questionCenter/";
		} else {// 未登录
			return "redirect:/user/login/";
		}

	}

	/**
	 * 
	 * 功能描述：取得行业二级的级联数据
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-17
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
	@RequestMapping("/questionCenter/createQuestion/getJobData.ajax")
	public void getJobData(HttpServletRequest request, HttpServletResponse response) throws BaseException {

		String proID = RequestUtils.getQueryParam(request, "proID");

		List<JobVo> jobGroupList = new ArrayList<JobVo>();
		try {
			if (!proID.equals("0")) {
				jobGroupList = jobService.getJobGroupByProid(Integer.parseInt(proID));
			}

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		JSONObject json = new JSONObject();
		json.put("jobGroupList", jobGroupList);
		ResponseUtils.renderJson(response, json.toString());
	}

	/**
	 * 
	 * 功能描述：取得岗位群下推荐老师的级联数据
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-17
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
	@RequestMapping("/questionCenter/createQuestion/getRecommendTeacherData.ajax")
	public void getRecommendTeacherData(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		List<UserVo> teacherList = new ArrayList<UserVo>();
		String jobGroupId = RequestUtils.getQueryParam(request, "jobGroupId");
		try {
			// 根据岗位群ID，取得相应的推荐老师
			if (!jobGroupId.equals("0")) {
				teacherList = questionCenterService.getRecommendTeacherList(Integer.parseInt(jobGroupId), 5);
			}

		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		JSONObject json = new JSONObject();
		json.put("teacherList", teacherList);
		ResponseUtils.renderJson(response, json.toString());
	}

	/**
	 * 
	 * 功能描述：问题帮助FAQ
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-21
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
	@RequestMapping("/questionCenter/faq/")
	public String faq(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws LoginException {

		return "qacenter/faq.htm";
	}

	/**
	 * 计算创建时间与当前时间段
	 * 
	 * @return
	 */
	private String compareTime(Date cTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 当前时间
		Date now = new Date();
		long l = now.getTime() - cTime.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		if (day != 0) {
			return String.valueOf(day) + "天前";
		} else if (hour != 0) {
			return String.valueOf(hour) + "小时前";
		} else if (min != 0) {
			return String.valueOf(min) + "分钟前";
		} else {
			return String.valueOf(s) + "秒前";
		}
	}

	/**
	 * 
	 * 功能描述：搜索
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-13
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
	@RequestMapping(value = "/questionCenter/search/", method = RequestMethod.POST)
	public String search(@ModelAttribute("form") SearchVo searchVo, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		List<ProfessionVo> professionList = new ArrayList<ProfessionVo>();
		PageModel pageModel = new PageModel();
		UserVo loginUserVo = new UserVo();

		String type = RequestUtils.getQueryParam(request, "type");
		if (type == null) {
			type = "0";
		} else {
			if (type.equals("")) {
				type = "0";
			}
		}

		String proId = RequestUtils.getQueryParam(request, "proId");
		if (proId == null) {
			proId = "0";
		} else {
			if (proId.equals("")) {
				proId = "0";
			}
		}
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		if (pageNo == null) {
			pageNo = "1";
		} else {
			if (pageNo.equals("")) {
				pageNo = "1";
			}
		}
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		if (pageSize == null) {
			pageSize = "5";
		} else {
			if (pageSize.equals("")) {
				pageSize = "5";
			}
		}

		try {
			loginUserVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		// 获取行业
		try {
			professionList = professionService.getProfessionList();

			// loginUserVo-登录用户,
			// searchVo-搜索关键字对象,
			// 0-全部问题,
			// 0-没有行业或岗位群ID,
			// 1-表示第一页,
			// 1-表示1条.
			pageModel = questionCenterService.getSearchList(loginUserVo, searchVo, Integer.valueOf(type), Integer.valueOf(proId),
					Integer.valueOf(pageNo), Integer.valueOf(pageSize));

		} catch (BaseException e) {
			logger.error("get QuestionList info exception.", e);
		}

		modelMap.put("pageModel", pageModel);
		modelMap.put("professionList", professionList);
		modelMap.put("searchVo", searchVo);

		modelMap.put("searchText", searchVo.getSearchtext());

		modelMap.put("typeInput", type);
		modelMap.put("idInput", proId);
		modelMap.put("pageNoInput", pageNo);
		modelMap.put("pageSizeInput", pageSize);

		return "qacenter/searchList.htm";

	}

	/**
	 * 
	 * 功能描述：搜索-查看更多
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-13
	 *         </p>
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws LoginException
	 */
	@RequestMapping("/questionCenter/moreSearch/")
	public void getMoreSearchList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws LoginException {
		String type = RequestUtils.getQueryParam(request, "type");
		String proId = RequestUtils.getQueryParam(request, "proId");
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		String searchtext = RequestUtils.getQueryParam(request, "searchtext");
		SearchVo searchVo = new SearchVo();
		searchVo.setSearchtext(searchtext);

		PageModel pageModel = new PageModel();
		String text = "";
		UserVo loginUserVo = new UserVo();
		try {
			loginUserVo = SiteUtils.getUser(request);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		if (loginUserVo == null) {

		} else {
			try {
				// loginUserVo-登录用户,
				// searchVo-搜索关键字对象,
				// 0-全部问题,
				// 0-没有行业或岗位群ID,
				// 1-表示第一页,
				// 1-表示1条.
				pageModel = questionCenterService.getSearchList(loginUserVo, searchVo, Integer.valueOf(type), Integer.valueOf(proId),
						Integer.valueOf(pageNo), Integer.valueOf(pageSize));

			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
			
				try {
					Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/webpage/qacenter/searchListPage.htm");
					ModelMap map = new ModelMap();
					map.put("pageModel", pageModel);
					map.put(RichFreeMarkerView.CONTEXT_CACHE, PathUtils.getCachePath(request));		
					map.put(RichFreeMarkerView.CONTEXT_PATH,PathUtils.getBasePath(request));
					map.put("searchText", searchVo.getSearchtext());
					
					text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
				} catch (IOException e) {
					logger.error(e); e.printStackTrace();
				} catch (TemplateException e) {
					logger.error(e); e.printStackTrace();
				}
			

			ResponseUtils.renderText(response, text);

		}
	}

	/**
	 * 功能描述：答疑搜索栏自动补全.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年2月27日 下午5:31:18
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
	@RequestMapping("/questionCenter/searchQues/autocomplete.ajax")
	public void autocompleteQuestion(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String term = RequestUtils.getQueryParam(request, "term");
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		if (!StringUtils.isBlank(term)) {
			try {
				list = questionCenterService.getQuestionListByKeyword(term, 20);
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		StringBuffer stringBuffer = new StringBuffer("[");
		for (QuestionVo questionVo : list) {
			stringBuffer.append("\"").append(questionVo.getQuesContent()).append("\",");
		}
		stringBuffer.append("]");
		String arrString = stringBuffer.toString().replace(",]", "]");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("arrString", arrString);
		ResponseUtils.renderJson(response, jsonObject.toString());

	}

	/**
	 * 功能描述：搜索老师自动补全.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年2月27日 下午5:31:18
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
	@RequestMapping("/questionCenter/searchTeacher/autocomplete.ajax")
	public void autocompleteTeacher(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String term = RequestUtils.getQueryParam(request, "term");
		List<UserVo> teacherList = new ArrayList<UserVo>();
		try {

			// 搜索老师身份的用户.
			teacherList = questionCenterService.getTeacherListByKeyword(term, 15);
			StringBuffer stringBuffer = new StringBuffer("[");
			if (teacherList.size() > 0) {
				for (UserVo userVo : teacherList) {
					stringBuffer.append("{id:\"" + userVo.getUserId() + "\",value:\"" + userVo.getUserName() + "\"},");
				}
			}
			stringBuffer.append("]");
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("arrString", stringBuffer.toString().replace(",]", "]"));
			ResponseUtils.renderJson(response, jsonObject.toString());
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
	}

}
