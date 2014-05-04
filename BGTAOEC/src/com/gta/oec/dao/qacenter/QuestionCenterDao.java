package com.gta.oec.dao.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.qacenter.QuestionVo;

/**
 * 功能描述：
 * 
 * @author Administrator
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QuestionCenterDao {
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
	public List<QuestionVo> getNewQuestionList(int proId);

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
	public List<QuestionVo> getHotQuestionList(int proId,int userId,int pageNo,int pageSize);
	
	
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
	public QuestionVo saveQuestion(QuestionVo questionVo);
	
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
	public QuestionVo updateQuestion(QuestionVo questionVo);

	/**
	 * 功能描述：通过岗位群Id获取问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-17 下午4:29:15
	 *         </p>
	 * 
	 * @param jobGroupId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionListByJobGroupId(int jobGroupId,int quesId, int num);

	/**
	 * 
	 * 功能描述：根据课程id查询问题列表
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-15 下午2:23:31
	 *         </p>
	 * 
	 * @param courId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionListByCourId(int courId,int quesType);

	/**
	 * 
	 * 功能描述：根据用户id查询问题列表
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-16 下午1:52:55
	 *         </p>
	 * 
	 * @param courId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionListByUserId(int userId,int quesType);

	/**
	 * 功能描述：获取首页的两个精选问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-15 下午2:33:08
	 *         </p>
	 * 
	 * @return List<QuestionVo>
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getChosenQuestions(int limitCont);

	/**
	 * 功能描述：通过ID获取问题详情.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-16 上午9:11:01
	 *         </p>
	 * 
	 * @param quesId
	 * @return QuestionVo
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public QuestionVo getQuestion(int quesId);

	/**
	 * 功能描述:根据行业ID选取回答数量逆序的问题.
	 * 
	 * @author Administrator
	 *         <p>
	 *         创建日期 ：2014-1-16 下午3:48:24
	 *         </p>
	 * 
	 * @param proID
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getRelationQuestionListByProfessionID(int proID);

	/**
	 * 
	 * 功能描述：根据问题ID，更新关注数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateAttentionQuestionCount(int quesId);
	
	/**
	 * 
	 * 功能描述：根据问题ID，更新关注数量
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
	public int deleteAttentionQuestionCount(int quesId);
	
	
	/**
	 * 
	 * 功能描述：根据问题ID，更新回答数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateAnswerQuestionCount(int quesId);
	

	/**
	 * 功能描述：用户id获取问题分页信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-21 下午3:53:34
	 *         </p>
	 * 
	 * @param userId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionPageByAnswUserId(int userId,int pageSize,int toPage);
	
	/**
	 * 功能描述：用户id获取问题分页信息总条数.
	 *
	 * @author  Administrator
	 * <p>创建日期 ：2014-1-21 下午4:37:23</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int	getQuestionCountByAnswUserId(int userId);
	
	
	/**
	 * 
	 * 功能描述：根据用户id和问题id集合查询问题列表
	 * 
	 * @author zhangjin
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
	public List<QuestionVo> getQuestionListByIdsAndUserId(int userId,List list,int pageNo,int pageSize);

	
	
	
	/**
	 * 功能描述：获取分类问题的分页.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-23 下午6:36:25</p>
	 *       
	 * @param flag
	 * @param type .分类类型.0:全部.1:行业.2:岗位群.
	 * @param id	
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getSortQuestionPage(int flag,int type,int id,int pageNo,int pageSize);
	
	/**
	 * 功能描述：获取搜索问题
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-13 </p>
	 *
	 * @param type .分类类型.0:全部.1:行业.2:岗位群.
	 * @param id	行业ID或岗位群ID.
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getSearchListPage(SearchVo searchVo,int type,int id,int pageNo,int pageSize);
	
	
	
	/**
	 * 功能描述：通过问题类型(答疑,课程),每个老师已解答与否(已回答,未回答),获取问题
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-2-11 下午4:59:11</p>
	 *
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getQuestionPageListByQuestionUserByquesType(int userId,int status,int quesType,int pageNo,int pageSize);
	
	/**
	 * 功能描述：统计通过问题类型(答疑,课程),每个老师已解答与否(已回答,未回答)的问题总条数.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-2-12 上午9:56:09</p>
	 *
	 * @param teacherID
	 * @param status
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countQuestionByQuestionUserByquesType(int userId,int status,int quesType);
	
	/**
	 * 功能描述：统计分类问题总条数.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-24 上午9:14:15</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countSortQuestionPage(int type,int id);
	
	/**
	 * 功能描述：根据搜索关键字，统计搜索问题总条数.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-13</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countSearchListPage(SearchVo searchVo,int type,int id);
	


	/**
	 * 功能描述：更改问题回答状态
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateQuestionStatus(int quesId);
	

	/**
	 * 
	 * 功能描述：根据问题id集合查询问题   by zhangjin
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-14 下午5:35:24</p>
	 *
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getQuestionListByIds(List<?> quesIdlist,int pageNo,int pageSize);
	
	/**
	 * 
	 * 功能描述：根据问题id集合查询问题   by zhangjin
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-14 下午5:35:24</p>
	 *
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getQuestionListByIds(int userId,List quesIdlist,int pageNo,int pageSize);
	/**
	 * 
	 * 功能描述：学生中心，课程提问
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 上午10:40:02</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List getQuesIdListByUserId(int userId,int quesType);
	
	/**
	 * 功能描述:搜索问题.
	 *
	 * @author  Administrator
	 * <p>创建日期 ：2014年2月27日 下午5:21:27</p>
	 *
	 * @param keyString
	 * @param type
	 * @param num
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getQuestionListByKeyword(String keyString,int num);
	/**
	 * 
	 * 功能描述：获取问题列表，多条件查询
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-3-12 上午10:30:15</p>
	 *
	 * @param vo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public  List<QuestionVo>  getQestionList(QuestionVo vo);
	
}
