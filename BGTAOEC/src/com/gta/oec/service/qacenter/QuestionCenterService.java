/**
 * JobService.java	  V1.0   2014-1-7 ����13:30:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.service.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.user.UserVo;

public interface QuestionCenterService {

	/**
	 * 功能描述：
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-17 下午6:23:07
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getQuestionListByJobGroupId(int jobGroupId,int quesId, int num)
			throws BaseException;

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
			throws BaseException;

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
	public List<QuestionVo> getHotQuestionList(int proId, UserVo userVo,
			int pageNo, int pageSize) throws BaseException;

	/**
	 * 功能描述：根据问题ID取得一个问题的所有答案.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-15 下午7:56:21
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<AnswerVo> getAllAnswer(int quesId) throws BaseException;

	/**
	 * 功能描述：通过用户id,回答id获取赞的明细列表.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-16 下午6:10:35
	 *         </p>
	 * 
	 * @param userID
	 * @param answID
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<PraiseDetailVo> getPraiseDetailByUserIdByAnsId(int userID,
			int answID) throws BaseException;

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
			throws BaseException;

	/**
	 * 功能描述：根据问题ID取得问题信息
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-16 上午9:31:33
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo getQuestionVo(int quesId) throws BaseException;

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
			PraiseDetailVo praiseDetailVo,boolean flag) throws BaseException;

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
			AttentionVo attentionVo,boolean flag) throws BaseException;

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
	 * 
	 * 功能描述：保存问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * @param QuestionVo对象
	 * @return QuestionVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestion(QuestionVo questionVo) throws BaseException;

	/**
	 * 
	 * 功能描述：根据用户-保存问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-18
	 *         </p>
	 * @param QuestionVo对象
	 * @return QuestionVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestionByUser(QuestionVo questionVo, UserVo user)
			throws BaseException;

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
	public QuestionVo updateQuestion(QuestionVo questionVo, UserVo user)
			throws BaseException;

	
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
	public QuestionVo saveQuestionInviteTeacher(QuestionVo questionVo, UserVo user)throws BaseException;
	
	
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
	public QuestionAddVo saveQuestionAddByUser(QuestionAddVo questionAddVo, UserVo user) throws BaseException;

	
	
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
			throws BaseException;

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
			throws BaseException;

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
			throws BaseException;

	/**
	 * 功能描述：获取答疑中心首页的2个精选问题
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-15 下午3:04:40
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getChosenQuestions(int limitCont)
			throws BaseException;

	/**
	 * 
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
	public List<UserVo> getTeacherAnswerActiveList() throws BaseException;

	/**
	 * 功能描述：保存问题回答
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16 上午10:57:22
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
	public AnswerVo saveAnswerVo(AnswerVo answerVo,UserVo user) throws BaseException;

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
			throws BaseException;

	/**
	 * 功能描述：问题详情的所有回答详情
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
	public PageModel getAllQuestionDetailAnswer(int quesId,UserVo loginUserVo,int page,int pageSize) throws BaseException;

	/**
	 * 功能描述：通过教师id获取所回答的问题分页列表.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-21 下午3:39:30
	 *         </p>
	 * 
	 * @param teacherId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PageModel getQuestionPageByTeacherId(int teacherId, int pageSize,
			int toPage) throws BaseException;

	/**
	 * 功能描述：选择最近一次,该问题该用户的答案.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-21 下午5:01:18
	 *         </p>
	 * 
	 * @param quesId
	 * @param userId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public AnswerVo getAnswerVoByQuesIdUserId(int quesId, int userId)
			throws BaseException;

	/**
	 * 功能描述：学生新收到的问题回答
	 * 
	 * @author zhangjin
	 *         <p>
	 *         创建日期 ：2014-1-21 下午5:01:18
	 *         </p>
	 * 
	 * @param quesId
	 * @param userId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getNewReceiveAnswer(int userId,List list,int pageNo,int pageSize)throws BaseException;

	/**
	 * 功能描述:获取分类问题分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-24 上午9:41:41
	 *         </p>
	 * @param loginUserVo        
	 * @param flag
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
	public PageModel getSortQuestion(UserVo loginUserVo,int flag,int type, int id, int pageNo, int pageSize)
			throws BaseException;

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
	public PageModel getSearchList(UserVo loginUserVo,SearchVo searchVo,int  type, int id, int pageNo, int pageSize) throws BaseException;
	
	
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
	public int updateAnswIsReadByquesId(List quesIds)throws BaseException;
	
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
	public List<QuestionUserVo> getAllQuestionUserListByQuesId(int quesId);
	
	
	public List<QuestionVo> getAttentionQuestionList(int proId, UserVo loginUserVo,
			int pageNo, int pageSize) throws BaseException;
	
	/**
	 * 
	 * 功能描述：学生中心，课程提问,问题id集合
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 上午10:36:32</p>
	 *
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List getQuesIdListByUserId(int userId,int quesType)throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：【-根据用户id查询用户关注的问题id集合-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-18 上午8:52:13</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List getAttentionQuesIdListByUserId(int userId);
	
	
   /**
    * 
    * 功能描述：【-根据问题id集合查询问题以及对应的答案-】
    *
    * @author  jin.zhang
    * <p>创建日期 ：2014-2-18 上午8:54:33</p>
    *
    * @param userId
    * @param quesIdlist
    * @param pageNo
    * @param pageSize
    * @param flag ,2表示所有的回答，0表示未读的回答标识
    * @return
    * @throws BaseException
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
	public List<QuestionVo> getQuestionListByIds(int userId,List quesIdlist,int pageNo, int pageSize,int flag) throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：【-：根据问题id集合查询有答案的问题id-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-18 下午2:46:36</p>
	 *
	 * @param quesList
	 * @param quesType
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<?> getHasAnswerQuestionIdsList(List quesList)throws BaseException;
	

	/**
	 * 
	 * 功能描述：【-：根据问题id集合查询关注的有答案的问题id-】
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-3 </p>
	 *
	 * @param quesList
	 * @param quesType
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<?> getAttentionHasAnswerQuestionIdsList(int userId,List quesList)throws BaseException;
	
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
	public int getAttentionHasAnswerQuestionIdsCount(int userId,List<?> queslist,int answIsRead) throws BaseException;
	
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
	public int updateAttentionAnswIsReadByquesId(int userId,List quesIds)throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：【-根据问题id集合查询答案的条数-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-18 下午3:48:54</p>
	 *
	 * @param queslist
	 * @param answIsRead
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getHasAnswerQuestionCount(List<?> queslist,int answIsRead) throws BaseException;
	
	/**
	 * 功能描述：关键字查询问题.
	 *
	 * @author dongs
	 * <p>创建日期 ：2014年2月27日 下午2:27:23</p>
	 *
	 * @param searchVo
	 * @param num
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getQuestionListByKeyword(String keyString ,int num)throws BaseException;

	/**
	 * 功能描述：关键字查询老师列表.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年2月28日 上午10:40:22</p>
	 *
	 * @param keyString
	 * @param num
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	List<UserVo> getTeacherListByKeyword(String keyString, int num) throws BaseException;
	
	/**
	 * 功能描述：保存课程提问,同时自动邀请该课程的任课老师回答此问题.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月5日 下午5:08:25</p>
	 *
	 * @param question
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveCourseQuestion(QuestionVo question) throws BaseException;
	
	public List<QuestionVo> getQestionList(QuestionVo questionVo)throws BaseException;
}
