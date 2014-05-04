package com.gta.oec.service.qacenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.qacenter.AnswerDao;
import com.gta.oec.dao.qacenter.AttentionAnswerDao;
import com.gta.oec.dao.qacenter.AttentionDao;
import com.gta.oec.dao.qacenter.PraiseDetailDao;
import com.gta.oec.dao.qacenter.QuestionAddDao;
import com.gta.oec.dao.qacenter.QuestionCenterDao;
import com.gta.oec.dao.qacenter.QuestionUserDao;
import com.gta.oec.dao.qacenter.ReAnswerQuestionAddDao;
import com.gta.oec.dao.teacher.TeacherDao;
import com.gta.oec.dao.user.UserDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.util.DateUtils;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.AttentionAnswerVo;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.qacenter.ReAnswerQuestionAddVo;
import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.user.UserVo;

@Service
public class QuestionCenterServiceImpl implements QuestionCenterService{
	private static final Log logger = LogFactory
			.getLog(QuestionCenterServiceImpl.class);
	@Autowired 
	private QuestionCenterDao questionCenterDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private QuestionUserDao questionUserDao;
	@Autowired
	private PraiseDetailDao praiseDetailDao;
	@Autowired
	private AttentionDao attentionDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private QuestionAddDao questionAddDao;
	
	@Autowired
	private ReAnswerQuestionAddDao reAnswerQuestionAddDao;
	
	@Autowired
	private AttentionAnswerDao attentionAnswerDao;
	
	/**
	 * 
	 * 功能描述：根据行业得到最新提问列表信息
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getNewQuestionList(int proId, UserVo loginUserVo)
			throws BaseException {
		try {
			List<QuestionVo> newQuestionList = new ArrayList<QuestionVo>();
			
			//根据行业获取最新10条问题
			newQuestionList = questionCenterDao.getNewQuestionList(proId);
//			if (newQuestionList.size() > 0) {
//				for (QuestionVo questionVo : newQuestionList) {
//					AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
//					if (answerVo != null) {
//						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
//						if(loginUserVo.getUserId()!=null){
//							try {
//								praiseDetailVos = praiseDetailDao
//										.getPraiseDetailByUserIdByAnsId(
//												loginUserVo.getUserId(),
//												answerVo.getAnswID());
//							} catch (Exception e) {
//								// TODO: handle exception
//								logger.error(e); e.printStackTrace();
//							}
//						}
//						
//						
//
//						if (praiseDetailVos.size() == 0) {
//							answerVo.setPraiseSign("赞");
//						} else {
//							answerVo.setPraiseSign("已赞");
//						}
//						questionVo.setAnswerVo(answerVo);
//					} else {
//						AnswerVo answer = new AnswerVo();
//						answer.setPraiseSign("赞");
//						questionVo.setAnswerVo(answer);
//					}
//
//				}
//			}

			return newQuestionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 
	 * 功能描述：根据行业得到最热门提问列表信息
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getHotQuestionList(int proId, UserVo loginUserVo,
			int pageNo, int pageSize) throws BaseException {
		try {
			List<QuestionVo> hotQuestionList = new ArrayList<QuestionVo>();
			if (proId == 0) { // 我的答疑提问列表
				hotQuestionList = questionCenterDao.getHotQuestionList(0,loginUserVo.getUserId(), pageNo, pageSize);
			} else { // 岗位下的问题列表
				hotQuestionList = questionCenterDao.getHotQuestionList(proId,0, pageNo, pageSize);
			}

			if (hotQuestionList.size() > 0) {
				
				for (QuestionVo questionVo : hotQuestionList) {
					AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						if(loginUserVo.getUserId()!=null){
							try {
								praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(loginUserVo.getUserId(),answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList =new ArrayList<AttentionVo>();
					if(loginUserVo.getUserId()!=null){
						attentionList= attentionDao.getAttentionQuestionByUserId(
								loginUserVo.getUserId(),
								questionVo.getQuesID());
					}
					
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}
			}
			return hotQuestionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}


	/**
	 * 功能描述：问题详情
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @param answerVo
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo getQuestionDetail(int quesId, UserVo loginUserVo)
			throws BaseException {
		QuestionVo questionVo = new QuestionVo();
		questionVo = questionCenterDao.getQuestion(quesId);

		if (questionVo != null) {
			if(loginUserVo!=null){
				// 登录用户的问题是否已关注
				List<AttentionVo> attentionList = attentionDao.getAttentionQuestionByUserId(loginUserVo.getUserId(),
								questionVo.getQuesID());
				if (attentionList.size() == 0) {
					questionVo.setAttentionSign("+ 关注问题");
				} else {
					questionVo.setAttentionSign("已关注");
				}
			}else{
				questionVo.setAttentionSign("+ 关注问题");
			}
			
		}

		return questionVo;
	}

	/**
	 * 功能描述：问题详情的所有回答详情-分页显示
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @param answerVo
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PageModel getAllQuestionDetailAnswer(int quesId,UserVo loginUserVo,int page,int pageSize) throws BaseException {
		List<AnswerVo> answerList = new ArrayList<AnswerVo>();
		PageModel pageModel = new PageModel();
		
		if(page<=0 || pageSize<=0){
			return pageModel;
		}else{
			answerList = answerDao.getAllAnswers(quesId,page,pageSize);
			pageModel.setTotalItem(answerDao.getAllAnswersCount(quesId));
		}
		pageModel.setList(answerList);
		pageModel.setToPage(page);
		pageModel.setPageSize(pageSize);
		if (answerList.size() > 0) {
			for (AnswerVo answerVo : answerList) {
				if (answerVo != null) {
					if(loginUserVo!=null){
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						try {

							praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(
											loginUserVo.getUserId(),
											answerVo.getAnswID());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e); e.printStackTrace();
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
					}else{
						answerVo.setPraiseSign("赞");
					}
					
				}
			}
		}

		return pageModel;
	}

	/**
	 * 
	 * 功能描述：保存问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestion(QuestionVo questionVo) throws BaseException {
		try {
			return questionCenterDao.saveQuestion(questionVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存问题出错!");
		}

	}

	/**
	 * 
	 * 功能描述：根据用户保存提问
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-18
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestionByUser(QuestionVo questionVo, UserVo user)
			throws BaseException {
		try {
			QuestionVo newQuestionVo = new QuestionVo();

			newQuestionVo.setUserID(user.getUserId());
			newQuestionVo.setJobID(questionVo.getJobID());
			newQuestionVo.setJobGroupID(questionVo.getJobID());// 可不要
			newQuestionVo.setQuesContent(questionVo.getQuesContent());
			if (!questionVo.getQuesDescription().equals("")) {
				newQuestionVo.setQuesDescription(questionVo.getQuesDescription());
			}
			//处理问题关联图片
			Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");//<img[^<>]*src=[\'\"]([0-9A-Za-z.\\/]*)[\'\"].(.*?)>");
			Matcher m = p.matcher(questionVo.getQuesDescription());
			while(m.find()){
				String picUrl =m.group(1);
				StringBuffer nemPicUrl =new StringBuffer("");
				if(picUrl.length()>0){
					if(picUrl.contains("http://")){
						newQuestionVo.setQuesPic(picUrl);
					}else{
						String[] picUrls=picUrl.split("\\.");
						if(picUrls.length>0){
							nemPicUrl.append(picUrls[0]).append("_1.").append(picUrls[1]);
							newQuestionVo.setQuesPic(nemPicUrl.toString());
						}
					}
				}
				break;
			}
			
			newQuestionVo.setQuesIsChoiceness(0);// 是否精选 0.普通 1.精选
			newQuestionVo.setQuesCtime(DateUtils
					.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			newQuestionVo.setQuesUtime(DateUtils
					.getCurrentDate("yyyy-MM-dd HH:mm:ss"));

			// 保存提问
			newQuestionVo = questionCenterDao.saveQuestion(newQuestionVo);

			// 保存提问的指定回答老师
			if (questionVo.getTeacherIdList() != null) {
				List<QuestionUserVo> techerList = new ArrayList<QuestionUserVo>();
				for (Integer teacherId : questionVo.getTeacherIdList()) {
					QuestionUserVo questionUserVo = new QuestionUserVo();
					questionUserVo.setQuesID(newQuestionVo.getQuesID());
					questionUserVo.setStatus(0);
					questionUserVo.setTeacherID(teacherId);
					techerList.add(questionUserVo);
				}
				questionUserDao.saveQuestionUser(techerList);
			}

			return newQuestionVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存提问出错!");
		}

	}
	
	/**
	 * 
	 * 功能描述：根据用户-更新问题说明
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-10
	 *         </p>
	 * @param QuestionVo对象
	 * @return QuestionVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo updateQuestion(QuestionVo questionVo, UserVo user) throws BaseException{
		// 保存提问
		questionVo = questionCenterDao.updateQuestion(questionVo);
		return questionVo;
	}
	
	/**
	 * 
	 * 功能描述：保存邀请回答老师 
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-10
	 *         </p>
	 * @param QuestionVo对象
	 * @return QuestionVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestionInviteTeacher(QuestionVo questionVo, UserVo user)throws BaseException{
		try {

			// 保存提问的指定回答老师
			if (questionVo.getTeacherIdList() != null) {
				//根据提问ID，获取所有被邀请老师列表
				List<QuestionUserVo> techerListed= questionUserDao.getAllQuestionUserListByQuesId(questionVo.getQuesID());
				List<Integer> teacherIdList=questionVo.getTeacherIdList();
				
				List<QuestionUserVo> techerList = new ArrayList<QuestionUserVo>();//新邀请老师
				List<QuestionUserVo> sameTecherList = new ArrayList<QuestionUserVo>();//相同的邀请老师对象
				List<Integer> sameTecherIdList = new ArrayList<Integer>();//相同的邀请老师ID
				//先找出相同的邀请老师
				for (Integer teacherId : teacherIdList) {
					for (QuestionUserVo questionUserVo : techerListed) {
						if(questionUserVo.getTeacherID()==teacherId){
							sameTecherList.add(questionUserVo);
							sameTecherIdList.add(teacherId);
						}
					}
				}
				//list中删除相同的邀请老师
				techerListed.removeAll(sameTecherList);
				teacherIdList.removeAll(sameTecherIdList);
				
				for (Integer teacherId :teacherIdList) {
					QuestionUserVo questionUserVo = new QuestionUserVo();
					questionUserVo.setQuesID(questionVo.getQuesID());
					questionUserVo.setStatus(0);
					questionUserVo.setTeacherID(teacherId);
					techerList.add(questionUserVo);
				}
				
				questionUserDao.deleteQuestionUser(techerListed);//删除没有选择的老师
				questionUserDao.saveQuestionUser(techerList);
			}

			return questionVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存邀请回答老师出错!");
		}
	}
	
	

	/**
	 * 
	 * 功能描述：根据用户-保存追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-23
	 *         </p>
	 * @param QuestionAddVo对象
	 * @return QuestionAddVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionAddVo saveQuestionAddByUser(QuestionAddVo questionAddVo, UserVo user) throws BaseException{
		try {
			
			
			//追加回答ID
			int answerId =questionAddVo.getAnswerId();
			
			questionAddVo.setUserID(user.getUserId());
			questionAddVo.setQuesCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			questionAddVo.setQuesUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			

			// 保存提问
			questionAddVo = questionAddDao.saveQuestionAddByUser(questionAddVo);

			// 保存回答与追加问题关系
			if (questionAddVo.getQuesAddID()!= 0) {
				ReAnswerQuestionAddVo reAnswerQuestionAddVo=new ReAnswerQuestionAddVo();
				reAnswerQuestionAddVo.setAnswerId(answerId);
				reAnswerQuestionAddVo.setQuesAddId(questionAddVo.getQuesAddID());
				reAnswerQuestionAddVo.setCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				reAnswerQuestionAddVo.setUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				
				reAnswerQuestionAddVo=reAnswerQuestionAddDao.saveReAnswerQuestionAdd(reAnswerQuestionAddVo);
			}

			return questionAddVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存追加提问出错!");
		}
	}
	
	
	/**
	 * 
	 * 功能描述：根据回答ID,取得追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-22
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionAddVo> getAllQuestionAddListByAnswId(int answId)
			throws BaseException {
		try {
			List<QuestionAddVo> questionAddList = new ArrayList<QuestionAddVo>();
			List<AnswerVo> answerList = new ArrayList<AnswerVo>();

			questionAddList = questionAddDao.getAllQuestionAddListByAnswId(answId);

			if (questionAddList.size() > 0) {
				for (QuestionAddVo questionAddVo : questionAddList) {
					// 取得所有的回答
					answerList = answerDao.getAllAnswersByQuesAddId(questionAddVo.getQuesAddID());
					questionAddVo.setAnswerList(answerList);
				}
			}
			return questionAddList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 
	 * 功能描述：取得推荐的老师列表
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-15
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<UserVo> getRecommendTeacherList(int limitNum)
			throws BaseException {
		try {
			List<UserVo> teacherList = new ArrayList<UserVo>();
			teacherList = userDao.getRecommendTeacherList(limitNum);
			return teacherList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 
	 * 功能描述：根据岗位群ID，取得相应的推荐老师
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-15
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<UserVo> getRecommendTeacherList(int jobGroupId, int limitNum)
			throws BaseException {
		try {
			List<UserVo> teacherList = new ArrayList<UserVo>();
			teacherList = userDao.getRecommendTeacherList(jobGroupId, limitNum);
			return teacherList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/* (non-Javadoc)
	 * @see com.gta.oec.service.qacenter.QuestionCenterService#getChosenQuestions(int)
	 */
	@Override
	public List<QuestionVo> getChosenQuestions(int limitCont)
			throws BaseException {
		List<QuestionVo> questionList = null;
		try {
			questionList = questionCenterDao.getChosenQuestions(limitCont);
			if (questionList != null) {
				if (questionList.size() > 0) {

					for (QuestionVo ques : questionList) {
						AnswerVo ans = answerDao.getHotQuestionAnswer(ques
								.getQuesID());
						if (ans != null) {
							ques.setAnswerVo(ans);
						}

					}
				}
			}
			return questionList;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 功能描述：取得回答活跃老师列表
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-15
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<UserVo> getTeacherAnswerActiveList() throws BaseException {
		try {
			List<UserVo> teacherAnswerActiveList = new ArrayList<UserVo>();
			teacherAnswerActiveList = userDao.getTeacherAnswerActiveList();
			return teacherAnswerActiveList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.service.qacenter.QuestionCenterService#getAllAnswer(int)
	 */
	@Override
	public List<AnswerVo> getAllAnswer(int quesId) throws BaseException {
		List<AnswerVo> answerList = null;
		try {
			answerList = answerDao.getAllAnswers(quesId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		return answerList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.qacenter.QuestionCenterService#getQuestionVo(int)
	 */
	@Override
	public QuestionVo getQuestionVo(int quesId) throws BaseException {
		QuestionVo question = null;
		try {
			question = questionCenterDao.getQuestion(quesId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return question;
	}

	/**
	 * 
	 * 功能描述：更新回答赞数量，同时插入赞明细
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public boolean updateAnswPraiseCount(String answID,
			PraiseDetailVo praiseDetailVo,boolean flag) throws BaseException {
		try {
			if(flag==true){
				answerDao.updateAnswPraiseCountByAnswId(Integer.parseInt(answID));
				praiseDetailDao.savePraiseDetail(praiseDetailVo);
			}else{//取消赞
				
				answerDao.deleteAnswPraiseCountByAnswId(Integer.parseInt(answID));
				praiseDetailDao.deletePraiseDetail(praiseDetailVo);
			}
			

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * 功能描述：更新关注数量，同时保存关注问题明细
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public AttentionVo updateAttentionQuestion(String quesID,
			AttentionVo attentionVo,boolean flag) throws BaseException {

		try {
			if(flag==true){
				questionCenterDao.updateAttentionQuestionCount(Integer
						.parseInt(quesID));// 更新关注数量
				attentionVo = attentionDao.saveAttention(attentionVo);// 保存关注明细
			}else{
				questionCenterDao.deleteAttentionQuestionCount(Integer
						.parseInt(quesID));// 删除关注数量
				attentionVo = attentionDao.deleteAttention(attentionVo);// 删除关注明细
			}


		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}
		return attentionVo;
	}

	/**
	 * 
	 * 功能描述：根据回答ID取得回答信息对象
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public AnswerVo getAnswerVoByAnswId(int answId) {
		return answerDao.getAnswerVoByAnswId(answId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.qacenter.QuestionCenterService#saveAnswerVo(com.gta
	 * .oec.vo.qacenter.AnswerVo)
	 */
	@Override
	public AnswerVo saveAnswerVo(AnswerVo answerVo,UserVo user) throws BaseException {
		if (answerVo != null) {
			if (!StringUtils.isBlank(answerVo.getAnswContent())) {
				try {
					answerVo.setUserID(user.getUserId());//回答用户ID
					answerVo.setAnswCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					answerVo.setAnswUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					
					int quesID=answerVo.getQuesID();
					
					//循环用户
					List<AttentionAnswerVo> attentionAnswerList=new ArrayList<AttentionAnswerVo>();
					
					//分析是追加问题的回答 还是直接问题的回答
					if(answerVo.getQuesAddID()!=0){
						answerVo.setQuesID(0);
						
						//更改追问问题回答状态
						questionAddDao.updateQuestionAddStatus(answerVo.getQuesAddID());
					}
					
					answerVo= answerDao.saveAnswerVo(answerVo);//保存回答
					
					if(answerVo.getQuesAddID()==0){
						//保存关注该问题的所有用户与回答的关系---q_attention_answer表
						//先找出有多少用户关注了该问题
						List<AttentionVo> attentionList=attentionDao.getAttentionUserByQuesId(answerVo.getQuesID());
						
						
						for (AttentionVo attentionVo : attentionList) {
							AttentionAnswerVo attentionAnswerVo =new AttentionAnswerVo();
							
							attentionAnswerVo.setAnswerID(answerVo.getAnswID());
							attentionAnswerVo.setQuestionID(answerVo.getQuesID());
							attentionAnswerVo.setUserID(attentionVo.getUserID());//关注用户ID
							attentionAnswerVo.setAnswIsRead(0);
							attentionAnswerVo.setCtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
							attentionAnswerVo.setUtime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
						
							attentionAnswerList.add(attentionAnswerVo);
						}
						
						//更改问题回答状态
						questionCenterDao.updateQuestionStatus(answerVo.getQuesID());
						//更改问题邀请老师回答状态
						questionUserDao.updateQuestionUserStatus(answerVo.getQuesID(),user.getUserId());
					}
					
					//保存关注该问题的所有用户与回答的关系
					attentionAnswerDao.saveAttentionAnswerVoList(attentionAnswerList);
					
					questionCenterDao.updateAnswerQuestionCount(quesID);// 更新回答数量
					
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e); e.printStackTrace();
					throw new SystemDBException("DB Operate Error!");
				}
			} else {
				throw new BaseException("未获取到问题ID,用户ID或者回答内容不能为空");
			}
		} else {
			throw new BaseException("为获取到需要保存的对象");
		}
		return answerVo;
	}

	@Override
	public List<PraiseDetailVo> getPraiseDetailByUserIdByAnsId(int userID,
			int answID) throws BaseException {
		List<PraiseDetailVo> praiseDetailList = null;
		try {
			praiseDetailList = praiseDetailDao.getPraiseDetailByUserIdByAnsId(
					userID, answID);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("DB Operate Error!");
		}

		return praiseDetailList;
	}

	/**
	 * 功能描述：通过用户id,问题id获取关注问题明细列表.
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @param userID
	 * @param quesID
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<AttentionVo> getAttentionQuestionByUserId(int userID, int quesID)
			throws BaseException {
		List<AttentionVo> attentionList = new ArrayList<AttentionVo>();
		try {
			attentionList = attentionDao.getAttentionQuestionByUserId(userID,
					quesID);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("DB Operate Error!");
		}

		return attentionList;
	}

	@Override
	public List<QuestionVo> getQuestionListByJobGroupId(int jobGroupId,int quesId, int num)
			throws BaseException {
		List<QuestionVo> list = null;
		try {
			list = questionCenterDao.getQuestionListByJobGroupId(jobGroupId,quesId,
					num);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("DB Operate Error!");
		}

		return list;
	}

	@Override
	public PageModel getQuestionPageByTeacherId(int teacherId, int pageSize,
			int toPage) throws BaseException {
		TeacherVo teacherVo = teacherDao.getTeacherVoById(teacherId);
		List<QuestionVo> questionList = null;
		PageModel pageModel = new PageModel();
		if (toPage <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		if (teacherVo != null && teacherVo.getUserId() != 0) {
			try {
				questionList = questionCenterDao.getQuestionPageByAnswUserId(
						teacherVo.getUserId(), pageSize, toPage);
				pageModel.setList(questionList);
				pageModel.setTotalItem(questionCenterDao
						.getQuestionCountByAnswUserId(teacherVo.getUserId()));
				pageModel.setPageSize(pageSize);
				pageModel.setToPage(toPage);
				return pageModel;
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("System DB operate error!", e);
				throw new SystemDBException("DB Operate Error!");
			}

		}
		return pageModel;
	}

	@Override
	public AnswerVo getAnswerVoByQuesIdUserId(int quesId, int userId)
			throws BaseException {
		AnswerVo answerVo = null;
		if (quesId > 0 && userId > 0) {
			try {
				answerVo = answerDao.getAnswerVoByQuesIdUserId(quesId, userId);

			} catch (Exception e) {
				// TODO: handle exception
				logger.error("System DB operate error!", e);
				throw new SystemDBException("DB Operate Error!");
			}
		}
		return answerVo;
	}

	/**
	 * 
	 * 功能描述：新收到的回答
	 * 
	 * @author zhangjin
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getNewReceiveAnswer(int proId, UserVo loginUserVo,int pageNo,int pageSize)
			throws BaseException {
		try {
			List<QuestionVo> hotQuestionList = new ArrayList<QuestionVo>();
		    
			hotQuestionList = questionCenterDao.getHotQuestionList(0,loginUserVo.getUserId(),pageNo,pageSize);
			
			if (hotQuestionList.size() > 0) {
				for (QuestionVo questionVo : hotQuestionList) {
					AnswerVo answerVo = answerDao
							.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = null;
						try {

							praiseDetailVos = praiseDetailDao
									.getPraiseDetailByUserIdByAnsId(
											loginUserVo.getUserId(),
											answerVo.getAnswID());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e); e.printStackTrace();
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList = attentionDao
							.getAttentionQuestionByUserId(
									loginUserVo.getUserId(),
									questionVo.getQuesID());
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}
			}
			return hotQuestionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 
	 * 功能描述：学生新收到的问题回答
	 * 
	 * @author zhangjin
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getNewReceiveAnswer(int userId,List quesIdslist,int pageNo,int pageSize) throws BaseException {
		try {
			List<QuestionVo> questionList = new ArrayList<QuestionVo>();
		    
			if(quesIdslist==null){
				return null;
			}
			questionList = questionCenterDao.getQuestionListByIdsAndUserId(userId, quesIdslist, pageNo, pageSize);
			
			if (questionList.size() > 0) {
				for (QuestionVo questionVo : questionList) {
					AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = null;
						try {
							praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(userId,answerVo.getAnswID());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e); e.printStackTrace();
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList = attentionDao
							.getAttentionQuestionByUserId(userId,questionVo.getQuesID());
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}
			}
			return questionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	

	@Override
	public PageModel getSortQuestion(UserVo loginUserVo,int flag, int type, int id,
			int pageNo, int pageSize) throws BaseException {
		List<QuestionVo> list = null;
		PageModel pageModel = new PageModel();
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			list = questionCenterDao.getSortQuestionPage(flag,type, id, pageNo,pageSize);

			if (list.size() > 0) {
				for (QuestionVo questionVo : list) {
					AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						
						if(loginUserVo.getUserId()!=null){
							try {

								praiseDetailVos = praiseDetailDao
										.getPraiseDetailByUserIdByAnsId(
												loginUserVo.getUserId(),
												answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}

						}
						
						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList =new ArrayList<AttentionVo>();
					if(loginUserVo.getUserId()!=null){
						attentionList= attentionDao
						.getAttentionQuestionByUserId(
								loginUserVo.getUserId(),
								questionVo.getQuesID());
					}
					
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}

				pageModel.setList(list);
				pageModel.setTotalItem(questionCenterDao.countSortQuestionPage(
						type, id));
				pageModel.setPageSize(pageSize);
				pageModel.setToPage(pageNo);

			}

			return pageModel;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	

	/**
	 * 功能描述:搜索
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-13 
	 *         </p>
	 * 
	 * @param type
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PageModel getSearchList(UserVo loginUserVo,SearchVo searchVo,int  type, int id, int pageNo, int pageSize) throws BaseException{
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		PageModel pageModel = new PageModel();
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			list = questionCenterDao.getSearchListPage(searchVo,type, id, pageNo,pageSize);

			if (list.size() > 0) {
				for (QuestionVo questionVo : list) {
					AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						
						if(loginUserVo.getUserId()!=null){
							try {
								praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(loginUserVo.getUserId(),answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}

						}
						
						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList =new ArrayList<AttentionVo>();
					if(loginUserVo.getUserId()!=null){
						attentionList= attentionDao.getAttentionQuestionByUserId(loginUserVo.getUserId(),questionVo.getQuesID());
					}
					
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}

				pageModel.setList(list);
				pageModel.setTotalItem(questionCenterDao.countSearchListPage(searchVo,type, id));
				pageModel.setPageSize(pageSize);
				pageModel.setToPage(pageNo);

			}

			return pageModel;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	

	/**
	 * 
	 * 功能描述：根据问题ID，更新答案为已读状态 
	 *
	 * @author  zhangjin
	 * <p>创建日期 ：2014-1-23</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateAnswIsReadByquesId(List quesIds)throws BaseException{
		
		try{
			List<AnswerVo> answerList =new ArrayList<AnswerVo>();
		    if (quesIds.size() > 0) {
				for (Object quesId : quesIds) {
					
					//获取问题所有回答
					answerList= answerDao.getAnswerListByQuesId((Integer)quesId);
					if (answerList != null && answerList.size()>0) {
						//获取所有回答的所有追加提问
						List<QuestionAddVo> addList = questionAddDao.getAllQuestionAddListByAnswList(answerList);
						
						//获取所有追加提问的回答()
						if (addList != null && addList.size()>0) {
							List<AnswerVo> answerAddList = answerDao.getAllAnswersByQuesAddList(addList);
							if (answerAddList != null && answerAddList.size()>0) {
								answerList.addAll(answerAddList);
							}
						}
					}
					
				}
			}
		    
		    
			return answerDao.updateAnswIsReadByAnswerList(answerList);//answerDao.updateAnswIsReadByquesId(quesIds);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	/**
	 * 
	 * 功能描述：根据问题ID,获取所有邀请老师列表
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-10
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionUserVo> getAllQuestionUserListByQuesId(int quesId){
		return questionUserDao.getAllQuestionUserListByQuesId(quesId);
	}
	
	
	/**
	 * 用户关注的问题  by zhangjin
	 */
	public List<QuestionVo> getAttentionQuestionList(int proId, UserVo user,
			int pageNo, int pageSize) throws BaseException {
		try {
			List<QuestionVo> questionList = new ArrayList<QuestionVo>();
			//用户关注问题id的集合
			List quesIdlist = attentionDao.getAttentionQuesIdListByUserId(user.getUserId());
			//根据用户关注id的集合查询对应的问题集合
		    questionList = questionCenterDao.getQuestionListByIds(user.getUserId(),quesIdlist, pageNo, pageSize);
			
		    if (questionList.size() > 0) {
				for (QuestionVo questionVo : questionList) {
					AnswerVo answerVo = answerDao
							.getHotQuestionAnswer(questionVo.getQuesID());
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						if(user.getUserId()!=null){
							try {
								praiseDetailVos = praiseDetailDao
										.getPraiseDetailByUserIdByAnsId(
												user.getUserId(),
												answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						questionVo.setAnswerVo(answerVo);
					} else {
						AnswerVo answer = new AnswerVo();
						answer.setPraiseSign("赞");
						questionVo.setAnswerVo(answer);
					}

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList =new ArrayList<AttentionVo>();
					if(user.getUserId()!=null){
						attentionList= attentionDao.getAttentionQuestionByUserId(
								user.getUserId(),
								questionVo.getQuesID());
					}
					
					if (attentionList.size() == 0) {
						questionVo.setAttentionSign("+ 关注问题");
					} else {
						questionVo.setAttentionSign("已关注");
					}
				}
			}
			return questionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	@Override
	public List getQuesIdListByUserId(int userId,int quesType)throws BaseException{
		try{
			return questionCenterDao.getQuesIdListByUserId(userId,quesType);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public int getHasAnswerQuestionCount(List queslist,int answIsRead) throws BaseException{
		try{
			List<AnswerVo> answerList =new ArrayList<AnswerVo>();
			int count =0;
		    if (queslist.size() > 0) {
				for (Object quesId : queslist) {
					//获取问题所有回答
					answerList= answerDao.getAnswerListByQuesId((Integer)quesId);
					
					if (answerList != null && answerList.size()>0) {
						//获取所有回答的所有追加提问
						List<QuestionAddVo> addList = questionAddDao.getAllQuestionAddListByAnswList(answerList);
						
						//获取所有追加提问的回答()
						if (addList != null && addList.size()>0) {
							List<AnswerVo> answerAddList = answerDao.getAllAnswersByQuesAddList(addList);
							if (answerAddList != null && answerAddList.size()>0) {
								answerList.addAll(answerAddList);
							}
						}
						
						for (AnswerVo answerVo : answerList) {
							if(answerVo.getAnswIsRead()==0){
								count=count+1;
							}
						}
					}
				}
			}
		    
		    return count;
		    
//			return answerDao.getHasAnswerQuestionCount(queslist,answIsRead);

		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	

	/**
	 * 根据用户id查询用户关注的问题id集合
	 * @param userId
	 * @return
	 */
	public List getAttentionQuesIdListByUserId(int userId){
		
		return attentionDao.getAttentionQuesIdListByUserId(userId);
	}
	
	/**
	 * 根据问题id集合查询对应的问题已经答案  by zhangjin
	 */
	public List<QuestionVo> getQuestionListByIds(int userId,List quesIdlist,int pageNo, int pageSize,int flag) throws BaseException {
		try {
			List<QuestionVo> questionList = new ArrayList<QuestionVo>();
			List<QuestionVo> delQuestionList = new ArrayList<QuestionVo>();
			//根据用户关注id的集合查询对应的问题集合
		    questionList = questionCenterDao.getQuestionListByIds(quesIdlist, pageNo, pageSize);
		
		    
		    if (questionList.size() > 0) {
				for (QuestionVo question : questionList) {
					AnswerVo answerVo =new AnswerVo();
					//获取问题所有回答
					List<AnswerVo> answerList= answerDao.getAnswerListByQuesId(question.getQuesID());
					if (answerList != null && answerList.size()>0) {
						//获取所有回答的所有追加提问
						List<QuestionAddVo> addList = questionAddDao.getAllQuestionAddListByAnswList(answerList);
						
						//获取所有追加提问的回答()
						if (addList != null && addList.size()>0) {
							List<AnswerVo> answerAddList = answerDao.getAllAnswersByQuesAddList(addList);
							if (answerAddList != null && answerAddList.size()>0) {
								answerList.addAll(answerAddList);
							}
						}
						
						//对回答list进行排序
						Collections.sort(answerList, new Comparator<AnswerVo>() {
				            public int compare(AnswerVo arg0, AnswerVo arg1) {
				                return arg0.getAnswCtime().compareTo(arg1.getAnswCtime());
				            }
				        });
					}
					
					if(flag==2){
						if (answerList != null && answerList.size()>0) {
							answerVo = answerList.get((answerList.size()-1));
						}
					}else if(flag==0){//取未读的回答
						if (answerList != null && answerList.size()>0) {
							AnswerVo answerVoTemp =answerList.get((answerList.size()-1));
							if(answerVoTemp.getAnswIsRead()==0){
								answerVo =answerVoTemp;
							}else{
								delQuestionList.add(question);
							}
						}
					}
//					AnswerVo answerVo =new AnswerVo();
//					answerVo = answerDao.getNewReceiveQuestionAnswer(question.getQuesID());
					
					if (answerVo != null) {
						List<PraiseDetailVo> praiseDetailVos = new ArrayList<PraiseDetailVo>();
						if(userId!=0){
							try {
								praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(userId,answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}
						}

						if (praiseDetailVos.size() == 0) {
							answerVo.setPraiseSign("赞");
						} else {
							answerVo.setPraiseSign("已赞");
						}
						question.setAnswerVo(answerVo);
					} 

					// 登录用户的问题是否已关注
					List<AttentionVo> attentionList =new ArrayList<AttentionVo>();
					if(userId!=0){
						attentionList= attentionDao.getAttentionQuestionByUserId(userId,question.getQuesID());
					}
					
					if (attentionList.size() == 0) {
						question.setAttentionSign("+ 关注问题");
					} else {
						question.setAttentionSign("已关注");
					}
				}
				
				questionList.removeAll(delQuestionList);
			}
			return questionList;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	/**
	 * 
	 * 功能描述：根据问题id集合查询有答案的问题id by zhangjin
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-22 下午6:32:56
	 *         </p>
	 * 
	 * @param userId
	 * @param isRead
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public List<?> getHasAnswerQuestionIdsList(List quesList)throws BaseException{
		try {
			return answerDao.getHasAnswerQuestionIdsList(quesList);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new BaseException("system db exception.");
		}
		
	}
	
	/**
	 * 
	 * 功能描述：根据问题id集合查询有答案的问题id by 刘祚家
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-3 
	 *         </p>
	 * 
	 * @param quesList
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public List<?> getAttentionHasAnswerQuestionIdsList(int userId,List quesList)throws BaseException{
		try {
			return answerDao.getAttentionHasAnswerQuestionIdsList(userId,quesList);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new BaseException("system db exception.");
		}
		
	}
	
	/**
	 * 
	 * 功能描述：【-根据问题id集合查询关注的有答案的条数-】
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-3 </p>
	 *
	 * @param queslist
	 * @param answIsRead
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getAttentionHasAnswerQuestionIdsCount(int userId,List<?> queslist,int answIsRead) throws BaseException{
		try{
			return answerDao.getAttentionHasAnswerQuestionIdsCount(userId,queslist,answIsRead);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 
	 * 功能描述：根据问题ID，更新关注问题的答案为已读状态 
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-3</p>
	 *
	 * @param quesId
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateAttentionAnswIsReadByquesId(int userId,List quesIds)throws BaseException{
		try{
			return attentionAnswerDao.updateAttentionAnswIsReadByquesId(userId,quesIds);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	

	/* (non-Javadoc)
	 * @see com.gta.oec.service.qacenter.QuestionCenterService#getQuestionListByKeyword(java.lang.String, int)
	 */
	@Override
	public List<QuestionVo> getQuestionListByKeyword(String keyString, int num) throws BaseException {
		List<QuestionVo> list=new ArrayList<QuestionVo>();
		if (num<=0) {
			num=1;//最少查询一条.
		}
		if(!StringUtils.isBlank(keyString)){
			try {
				list=questionCenterDao.getQuestionListByKeyword(keyString, num);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
				throw new BaseException("system db exception.");
				
			}
		
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see com.gta.oec.service.qacenter.QuestionCenterService#getTeacherListByKeyword(java.lang.String, int)
	 */
	@Override
	public List<UserVo> getTeacherListByKeyword(String keyString, int num)
			throws BaseException {
		List<UserVo> teacherList = new ArrayList<UserVo>();
		if (num<=0) {
			num=1;//最少查询一条.
		}
		if(!StringUtils.isBlank(keyString)){
		try {
			teacherList = userDao.getTeacherListByKeyword(keyString, num);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		}
		return teacherList;
	}


	@Override
	public void saveCourseQuestion(QuestionVo question) throws BaseException {
		QuestionVo savedQuestion=null;
        CourseVo courseVo=null;
        QuestionUserVo	questionUser=new QuestionUserVo();
		try {
			savedQuestion= questionCenterDao.saveQuestion(question);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存问题出错!");
		}
		
		if (savedQuestion==null) {
			throw new SystemDBException("保存问题出错!");
		}else {
			courseVo= courseDao.getCourseById(savedQuestion.getCourseID());
		}
		
		if (courseVo==null) {
			throw new SystemDBException("保存问题出错!");
		}else {
			questionUser.setQuesID(question.getQuesID());
			questionUser.setStatus(0);
			questionUser.setTeacherID(courseVo.getUserId());
			
		}
		
		try {
			questionUserDao.saveQuestionUser(questionUser);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("保存问题出错!");
		}
		
	}


	@Override
	public List<QuestionVo> getQestionList(QuestionVo questionVo)
			throws BaseException {
		try {
		return questionCenterDao.getQestionList(questionVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	
}
