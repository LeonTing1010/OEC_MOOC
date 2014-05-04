package com.gta.oec.dao.qacenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;

@Repository
public class AnswerDaoImpl extends BaseDao<AnswerVo> implements AnswerDao {

	/**
	 * 
	 * 功能描述：根据问题得到赞最多的回答信息
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
	public AnswerVo getHotQuestionAnswer(int quesId) {
		return (AnswerVo) super.selectOne(
				generateStatement("getHotQuestionAnswer"), quesId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.AnswerDao#getAllAnswers(int)
	 */
	@Override
	public List<AnswerVo> getAllAnswers(int quesId) {
		return findList(generateStatement("getAllAnswer"), quesId);
	}
	
	/**
	 * 功能描述：获取问题的所有答案-分页.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-23</p>
	 *
	 * @param quesId
	 * @param quesId
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAllAnswers(int quesId,int page,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quesId", quesId);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		return findList(generateStatement("getAllAnswerByPage"), map);
	}
	public int getAllAnswersCount(int quesId){
		return (Integer) super.selectOne(generateStatement("getAllAnswersCount"), quesId);
	}

	/**
	 * 
	 * 功能描述：根据问答id查询答案集合
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-15 下午6:44:42
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<AnswerVo> getAnswerListByQuesId(int quesId) {
		return super.findList(generateStatement("getAnswerListByQuesId"),
				quesId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.dao.qacenter.AnswerDao#saveAnswerVo(com.gta.oec.vo.qacenter
	 * .AnswerVo)
	 */
	@Override
	public AnswerVo saveAnswerVo(AnswerVo answerVo) {

		if (answerVo != null) {
			super.insert(generateStatement("saveAnswer"), answerVo);
		}
		return answerVo;
	}

	/**
	 * 
	 * 功能描述：根据回答ID，更新赞数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateAnswPraiseCountByAnswId(int answId) {
		return super.update(generateStatement("updateAnswPraiseCountByAnswId"),
				answId);
	}

	/**
	 * 
	 * 功能描述：根据回答ID，删除赞数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-1
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int deleteAnswPraiseCountByAnswId(int answId) {
		return super.update(generateStatement("deleteAnswPraiseCountByAnswId"),
				answId);
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
		return (AnswerVo) super.selectOne(
				generateStatement("getAnswerVoByAnswId"), answId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.AnswerDao#getAnswerVoByQuesIdUserId(int,
	 * int)
	 */
	@Override
	public AnswerVo getAnswerVoByQuesIdUserId(int quesId, int userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("quesId", quesId);
		parameter.put("userId", userId);
		parameter.put("limit", 1);
		return (AnswerVo) selectOne(
				generateStatement("getAnswerVoByQuesIdUserId"), parameter);
	}
	
	/**
	 * 
	 * 功能描述：根据追问id查询答案集合
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-22</p>
	 *
	 * @param quesAddId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAllAnswersByQuesAddId(int quesAddId){
		return super.findList(generateStatement("getAllAnswersByQuesAddId"),quesAddId);
	}
	
	/**
	 * 
	 * 功能描述：获取所有追加提问所有的回答
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-10</p>
	 *
	 * @param quesAddList
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAllAnswersByQuesAddList(List<QuestionAddVo> list){
		return super.findList(generateStatement("getAllAnswersByQuesAddList"),list);
	}
	
	
	
	/**
	 * 
	 * 功能描述：根据问答id查询未读答案的个数
	 *
	 * @author  zhangjin
	 * <p>创建日期 ：2014-1-22</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getNotReadAnswerCountByQuesId(int quesId){
		
		return (Integer) selectOne(generateStatement("getNotReadAnswerCountByQuesId"), quesId);
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
	public int updateAnswIsReadByquesId(List<?> queslist){
		return super.update(generateStatement("updateAnswIsReadByquesId"),queslist);
	}
	
	/**
	 * 
	 * 功能描述：根据回答List，更新答案为已读状态 
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-10</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateAnswIsReadByAnswerList(List<AnswerVo> answerList){
		return super.update(generateStatement("updateAnswIsReadByAnswerList"),answerList);
	}
	
	
	/**
	 * 获得最新的答案（按照时间倒叙排列）by zhangjin
	 * @param quesId
	 * @return
	 */
	public AnswerVo getNewReceiveQuestionAnswer(int quesId) {
		return (AnswerVo) super.selectOne(generateStatement("getNewReceiveQuestionAnswer"), quesId);
	}
	

	/**
	 *  根据问题id集合查询有答案的问题id by zhangjin
	 */
	public List<?> getHasAnswerQuestionIdsList(List<?> list) {

		return findList(generateStatement("getHasAnswerQuestionIdsList"), list);
	}
	
	/**
	 *  根据问题id集合查询关注的有答案的问题id by 刘祚家
	 */
	public List<?> getAttentionHasAnswerQuestionIdsList(int userId,List<?> list) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("userId", userId);
		return findList(generateStatement("getAttentionHasAnswerQuestionIdsList"), map);
	}
	
	/**
	 * 
	 * 功能描述：【-根据问题id集合查询关注的有答案的问题条数-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-18 下午3:46:51</p>
	 *
	 * @param queslist
	 * @param answIsRead
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getAttentionHasAnswerQuestionIdsCount(int userId,List<?> queslist,int answIsRead){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("answIsRead", answIsRead);
		map.put("list", queslist);
		map.put("userId", userId);
		return (Integer)super.selectOne(generateStatement("getAttentionHasAnswerQuestionIdsCount"),map);
	}
	
	
	/**
	 * 【-根据问题id集合查询答案的条数 by zhangjin-】
	 */
	public int getHasAnswerQuestionCount(List<?> queslist,int answIsRead) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("answIsRead", answIsRead);
		map.put("list", queslist);
		return (Integer)super.selectOne(generateStatement("getHasAnswerQuestionCount"),map);
		
	}

	/* (non-Javadoc)
	 * @see com.gta.oec.dao.qacenter.AnswerDao#getAnswerListByQuesIdUserId(int, int)
	 */
	@Override
	public List<AnswerVo> getAnswerListByQuesIdUserId(int quesId, int userId,int n) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("quesId", quesId);
		parameter.put("userId", userId);
		parameter.put("limit", n);
		return findList(generateStatement("getAnswerVoByQuesIdUserId"), parameter);
	}
}
