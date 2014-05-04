package com.gta.oec.dao.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;

public interface AnswerDao {

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
	public AnswerVo getHotQuestionAnswer(int quesId);

	/**
	 * 功能描述：获取问题的所有答案.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-15 下午7:29:35</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAllAnswers(int quesId);

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
	public List<AnswerVo> getAllAnswers(int quesId,int page,int pageSize);
	public int getAllAnswersCount(int quesId);
	
	/**
	 * 
	 * 功能描述：根据问答id查询答案集合
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-15 下午6:45:12</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAnswerListByQuesId(int quesId);
	
	/**
	 * 
	 * 功能描述：根据回答ID，更新赞数量
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-16 </p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateAnswPraiseCountByAnswId(int answId);
	
	/**
	 * 
	 * 功能描述：根据回答ID，删除赞数量
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-1 </p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteAnswPraiseCountByAnswId(int answId);
	
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
	public int updateAnswIsReadByquesId(List<?> quesIds);
	
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
	public int updateAnswIsReadByAnswerList(List<AnswerVo> answerIds);
	
	
	
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
	public AnswerVo getAnswerVoByAnswId(int answId);
	
	/**
	 * 功能描述：保存问题回答
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-16 上午10:46:24</p>
	 *
	 * @param answerVo
	 * @return answerVo
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public AnswerVo saveAnswerVo(AnswerVo answerVo);
	
	/**
	 * 功能描述：选择最近一次,该问题该用户的答案.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-21 下午5:01:18</p>
	 *
	 * @param quesId
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public AnswerVo getAnswerVoByQuesIdUserId(int quesId,int userId);
	
	/**
	 * 功能描述：获取某个问题,一个用户的n条回答.按时间逆序排列.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年2月19日 下午7:51:11</p>
	 *
	 * @param quesId
	 * @param useId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AnswerVo> getAnswerListByQuesIdUserId(int quesId,int userId,int n);
	
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
	public List<AnswerVo> getAllAnswersByQuesAddId(int quesAddId);
	
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
	public List<AnswerVo> getAllAnswersByQuesAddList(List<QuestionAddVo> quesAddList);
	
	/**
	 * 
	 * 功能描述： 根据问答id查询未读答案的个数
	 *
	 * @author  zhangjin
	 * <p>创建日期 ：2014-1-22</p>
	 *
	 * @param quesAddId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getNotReadAnswerCountByQuesId(int quesId);
	
	
	/**
	 *  获得最新的答案（按照时间倒叙排列）by zhangjin
	 * @param quesId
	 * @return
	 */
	public AnswerVo getNewReceiveQuestionAnswer(int quesId);
	
	/**
	 * 
	 * 功能描述： 根据问题id集合查询有答案的问题id
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-22 下午6:29:50</p>
	 *
	 * @param userId
	 * @param isRead
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<?> getHasAnswerQuestionIdsList(List<?> queslist);
	
	/**
	 * 
	 * 功能描述： 根据问题id集合查询关注的有答案的问题id
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-22 下午6:29:50</p>
	 *
	 * @param userId
	 * @param isRead
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<?> getAttentionHasAnswerQuestionIdsList(int userId,List<?> queslist);
	
	
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
	public int getAttentionHasAnswerQuestionIdsCount(int userId,List<?> queslist,int answIsRead);
	
	
	
	
	/**
	 * 
	 * 功能描述：【-根据问题id集合查询答案的条数-】
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
	public int getHasAnswerQuestionCount(List<?> queslist,int answIsRead);
	
	
}
