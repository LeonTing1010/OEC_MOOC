/**
 * QACenterServiceImpl.java	  V1.0   2014年3月26日 下午2:19:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.qacenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.qacenter.AnswerAttentionDao;
import com.gta.oec.cms.dao.qacenter.AnswerDao;
import com.gta.oec.cms.dao.qacenter.PraiseDetailDao;
import com.gta.oec.cms.dao.qacenter.QuestionAddDao;
import com.gta.oec.cms.dao.qacenter.QuestionAttentionDao;
import com.gta.oec.cms.dao.qacenter.QuestionDao;
import com.gta.oec.cms.dao.qacenter.QuestionUserDao;
import com.gta.oec.cms.dao.teacher.TeacherDao;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.vo.qacenter.AnswerVo;
import com.gta.oec.cms.vo.qacenter.QuestionAddVo;
import com.gta.oec.cms.vo.qacenter.QuestionVo;

@Service
/**
 * 功能描述：答疑中心后台管理服务层实现类.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class QACenterServiceImpl implements QACenterService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#findQuestionsPagination
	 * (com.gta.oec.cms.common.pagination.PaginationContext)
	 */
	@Override
	public PaginationContext<QuestionVo> findQuestionsPagination(PaginationContext<QuestionVo> pc) {
		List<QuestionVo> qustionList = null;
		try {
			qustionList = questionDao.questionsPageQuery(pc);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		pc.setResult(qustionList);
		return pc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#getQuestionByQuestionId
	 * (int)
	 */
	@Override
	public QuestionVo getQuestionByQuestionId(int questionId) throws ServiceException {
		if (questionId <= 0) {
			throw new ServiceException("参数错误");
		}
		try {
			return questionDao.getQuestionByQuestionId(questionId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("null result", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#updateQuestion(com.gta
	 * .oec.cms.vo.qacenter.QuestionVo)
	 */
	@Override
	public int updateQuestion(QuestionVo questionVo) throws ServiceException {
		int total = 0;
		if (questionVo == null) {
			throw new ServiceException("参数不能为空");
		} else {
			try {
				questionDao.getQuestionByQuestionId(questionVo.getQuestionId());
			} catch (DAOException e) {
				e.printStackTrace();
				new ServiceException("不是数据库的存在的问题.", e);
			}
		}
		try {
			total = questionDao.updateQuestion(questionVo);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.cms.service.qacenter.QACenterService#
	 * getQuestionDetailByQuestionId(int)
	 */
	@Override
	public QuestionVo getQuestionDetailByQuestionId(int questionId) throws ServiceException {
		if (questionId <= 0) {
			throw new ServiceException("参数错误");
		}
		QuestionVo questionVo = null;
		try {
			questionVo = questionDao.getQuestionByQuestionId(questionId);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (questionVo == null) {
			throw new ServiceException("没有这个id对应的问题");
		}
		List<AnswerVo> answerList = null;

		try {
			answerList = answerDao.getAnswerListByQuestionId(questionId);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (answerList != null && answerList.size() > 0) {
			for (AnswerVo answerVo : answerList) {
				List<QuestionAddVo> questionAddList = null;
				try {
					questionAddList = questionAddDao.getQuestionAddListByAnswerId(answerVo
							.getAnswerId());
				} catch (DAOException e) {
					e.printStackTrace();
				}
				answerVo.setQuestionAddList(questionAddList);
			}
		}
		questionVo.setAnswerList(answerList);
		return questionVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#updateVisibleQuestionOrNot
	 * (int)
	 */
	@Override
	public int updateVisibleQuestionOrNot(int questionId) throws ServiceException {
		int total = 0;
		QuestionVo questionVo = null;
		if (questionId <= 0) {
			throw new ServiceException("参数错误");
		}
		try {
			questionVo = questionDao.getQuestionByQuestionId(questionId);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (questionVo == null) {
			throw new ServiceException("没有这个id对应的问题");
		}

		if (questionVo.getVisibleQuestionOrNot() == QuestionVo.VISIBLE_QUESTION) {
			questionVo.setVisibleQuestionOrNot(QuestionVo.INVISIBLE_QUESTION);
		} else if (questionVo.getVisibleQuestionOrNot() == QuestionVo.INVISIBLE_QUESTION) {
			questionVo.setVisibleQuestionOrNot(QuestionVo.VISIBLE_QUESTION);
		}
		try {
			// 取消精选.
			questionVo.setChosenQuestionOrNot(QuestionVo.UNCHOSEN_QUESTION);
			// set updateTime.
			questionVo.setUpdateTime(VeDate.getNowDate());
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ServiceException("参数错误");
		}
		try {
			total = questionDao.updateQuestion(questionVo);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#updateChosenQuestionOrNot
	 * (int)
	 */
	@Override
	public int updateChosenQuestionOrNot(int questionId, int limit) throws ServiceException {
		/**
		 * 修改成功数.
		 */
		int total = 0;
		QuestionVo questionVo = null;
		if (questionId <= 0) {
			throw new ServiceException("参数错误");
		}
		if (limit <= 0) {
			throw new ServiceException("参数错误");
		}
		try {

			questionVo = questionDao.getQuestionByQuestionId(questionId);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (questionVo == null) {
			throw new ServiceException("没有这个id对应的问题");
		}
		if (questionVo.getQuestionType()==QuestionVo.QUESTION_OF_COURSE) {
			throw new ServiceException("这个id对应的问题不是一个答疑问题.");
		}
		/**
		 * 数据库中已存在的精选问题数量.
		 */
		int numberOfChosenQuestions = 0;
		try {
			numberOfChosenQuestions = questionDao.countChosenQuestions();
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		if (questionVo.getChosenQuestionOrNot() == QuestionVo.CHOSEN_QUESTION) {
			questionVo.setChosenQuestionOrNot(QuestionVo.UNCHOSEN_QUESTION);
		} else if (questionVo.getChosenQuestionOrNot() == QuestionVo.UNCHOSEN_QUESTION) {
			if (numberOfChosenQuestions < limit) {
				questionVo.setChosenQuestionOrNot(QuestionVo.CHOSEN_QUESTION);
			} else {
				throw new ServiceException("限制了精选问题数量为" + limit + "条!");
			}

		}
		try {
			// set updateTime.
			questionVo.setUpdateTime(VeDate.getNowDate());
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ServiceException("参数错误");
		}
		try {
			total = questionDao.updateQuestion(questionVo);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return total;
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public int batchUpdateVisibleQuestionOrNot(int[] questionIds, int updateType)
			throws ServiceException {
		int total = 0;
		QuestionVo questionVo = null;
		if (questionIds == null || questionIds.length <= 0) {
			throw new ServiceException("参数错误");
		}
		if (updateType <= 0) {
			throw new ServiceException("参数错误");
		}
		for (int questionId : questionIds) {
			try {
				questionVo = questionDao.getQuestionByQuestionId(questionId);
				if (questionVo == null) {
					throw new ServiceException("没有这个id对应的问题");
				}
			} catch (DAOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (updateType == 1) {
				questionVo.setVisibleQuestionOrNot(QuestionVo.VISIBLE_QUESTION);
			}
			if (updateType == 2) {
				questionVo.setVisibleQuestionOrNot(QuestionVo.INVISIBLE_QUESTION);
			}
			try {
				// 取消精选.
				questionVo.setChosenQuestionOrNot(QuestionVo.UNCHOSEN_QUESTION);
				// set updateTime.
				questionVo.setUpdateTime(VeDate.getNowDate());
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ServiceException("参数错误");
			}

			try {
				total += questionDao.updateQuestion(questionVo);
			} catch (DAOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.qacenter.QACenterService#deleteQuestionWithAllData
	 * (int)
	 */
	@Override
	public void deleteQuestionWithAllData(int questionId) throws ServiceException {
		// 数据校验
		if (questionId <= 0) {
			throw new ServiceException("参数错误");
		}
		int questionNum = 0;
		try {
			questionNum = questionDao.isExistQuestion(questionId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库错误.");
		}
		if (questionNum <= 0) {
			throw new ServiceException("库中没有这条记录.");
		}

		// 基本数据准备.
		// 问题的回答列表.
		List<AnswerVo> answerListOfQuestion = new ArrayList<AnswerVo>();
		try {
			answerListOfQuestion = answerDao.getAnswersSimplyByQuestionId(questionId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库错误.");
		}
		// 回答的追问列表.(遍历回答列表.将每个回答的追问列表都放入这个列表中).
		List<QuestionAddVo> questionAddListAll = new ArrayList<QuestionAddVo>();
		for (AnswerVo answerVo : answerListOfQuestion) {
			try {
				List<QuestionAddVo> questionAddList = null;
				questionAddList = questionAddDao.getQuestionAddSimplyListByAnswerId(answerVo
						.getAnswerId());
				if (questionAddList != null) {
					questionAddListAll.addAll(questionAddList);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("数据库错误.");
			}
		}
		// 追问的回答列表.(遍历追问列表.将每个追问的回答列表都放入这个列表中).
		List<AnswerVo> answerListOfQuestionAddAll = new ArrayList<AnswerVo>();
		for (QuestionAddVo questionAddVo : questionAddListAll) {
			try {
				List<AnswerVo> answerListOfQuestionAdd = answerDao
						.getAnswersSimplyByQuestionAddId(questionAddVo.getQuestionAddId());
				if (answerListOfQuestionAdd != null) {
					answerListOfQuestionAddAll.addAll(answerListOfQuestionAdd);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("数据库错误.");
			}
		}

		// 开始删除
		try {
			// 删除问题关注.
			int num = 0;
			num = questionAttentionDao.deleteQuestionAttentionByQuestionId(questionId);
			log.info("删除问题关注条数:" + num);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("删除问题关注出错.");
		}
		try {
			// 删除问题邀请表数据.
			int num = 0;
			num = questionUserDao.deleteQuestionUserByQuestionId(questionId);
			log.info("删除问题邀请条数:" + num);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("删除问题邀请出错.");
		}

		try {
			// 删除问题.
			int num = 0;
			num = questionDao.deleteQuestion(questionId);
			log.info("删除问题条数:" + num);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("删除问题出错.");
		}
		// 删除问题的回答以及回答的赞和关注.和回答的追问.
		for (AnswerVo answerVo : answerListOfQuestion) {

			int answerId = answerVo.getAnswerId();
			try {
				// 删除回答关注.
				int num = 0;
				num = answerAttentionDao.deleteAnswerAttentionByAnswerId(answerId);
				log.info("删除回答关注条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除回答关注.出错.");
			}
			try {
				// 删除回答赞明细.
				int num = 0;
				num = praiseDetailDao.deletePraiseDetailByAnswerId(answerId);
				log.info("删除回答赞明细条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除回答赞明细.出错.");
			}

			try {
				// 删除回答的追问.和回答与追问的关系.
				int num = 0;
				num = questionAddDao.deleteQuestionAddTotallyByAnswerId(answerId);
				log.info("删除回答的追问和回答与追问的关系.的条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除id为" + answerVo.getAnswerId() + "回答的追问.和回答与追问的关系出错.");
			}

			try {
				// 删除问题的回答.
				int num = 0;
				num = answerDao.deleteAnswerByAnswerId(answerId);
				log.info("删除问题回答的条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除问题回答出错.");
			}

		}
		// 删除追问的回答以及回答的赞和关注.
		for (AnswerVo answerVo : answerListOfQuestionAddAll) {
			int answerId = answerVo.getAnswerId();
			try {
				// 删除回答关注.
				int num = 0;
				num = answerAttentionDao.deleteAnswerAttentionByAnswerId(answerId);
				log.info("删除回答关注条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除回答关注.出错.");
			}
			try {
				// 删除回答赞明细.
				int num = 0;
				num = praiseDetailDao.deletePraiseDetailByAnswerId(answerId);
				log.info("删除回答赞明细条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除回答赞明细.出错.");
			}

			try {
				// 删除问题的回答.
				int num = 0;
				num = answerDao.deleteAnswerByAnswerId(answerId);
				log.info("删除问题回答的条数:" + num);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("删除问题回答出错.");
			}

		}
	}

	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private QuestionAddDao questionAddDao;
	@Autowired
	private QuestionAttentionDao questionAttentionDao;
	@Autowired
	private AnswerAttentionDao answerAttentionDao;
	@Autowired
	private PraiseDetailDao praiseDetailDao;
	@Autowired
	private QuestionUserDao questionUserDao;
	private Logger log = Logger.getLogger(QACenterServiceImpl.class);

}
