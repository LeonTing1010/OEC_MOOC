/**
 * AnswerDao.java	  V1.0   2014年4月1日 下午8:37:49
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.qacenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.qacenter.AnswerVo;

/**
 * 功能描述：回答的dao层
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface AnswerDao extends SqlMapper<AnswerVo> {

	/**
	 * 功能描述：通过回答id获取一个回答.以及每个回答人的教师信息,用户信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午8:39:53
	 *         </p>
	 * 
	 * @param answerId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public AnswerVo getAnswerByAnswerId(@Param(value = "answerId") int answerId)
			throws DAOException;

	/**
	 * 功能描述：通过问题id获得这个问题的所有回答.以及每个回答人的教师信息,用户信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午8:40:17
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<AnswerVo> getAnswerListByQuestionId(@Param(value = "questionId") int questionId)
			throws DAOException;

	/**
	 * 功能描述：通过问题id获得这个问题的所有回答(仅返回回答).
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 上午11:15:06
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<AnswerVo> getAnswersSimplyByQuestionId(@Param(value = "questionId") int questionId)
			throws DAOException;

	/**
	 * 功能描述：通过追问id获得这个追问的回答(仅返回回答).
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 上午11:15:06
	 *         </p>
	 * 
	 * @param questionAddId追问id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<AnswerVo> getAnswersSimplyByQuestionAddId(
			@Param(value = "questionAddId") int questionAddId) throws DAOException;

	/**
	 * 功能描述：删除一个答案.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月16日 上午10:30:39
	 *         </p>
	 * 
	 * @param answerId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteAnswerByAnswerId(@Param(value = "answerId") int answerId) throws DAOException;

	/**
	 * 功能描述：删除一个问题的所有答案.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 上午10:40:32
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteAnswersByQuestionId(@Param(value = "questionId") int questionId)
			throws DAOException;

	/**
	 * 功能描述：删除一个追问的答案.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 下午4:05:32
	 *         </p>
	 * 
	 * @param questionAddId
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteAnswersByQuestionAddId(@Param(value = "questionAddId") int questionAddId)
			throws DAOException;

	/*
	 * public int deleteAnswersTotallyByQuestionId(@Param(value = "questionId")
	 * int questionId) throws DAOException;
	 * 
	 * public int deleteAnswersTotallyByQuestionAddId(@Param(value =
	 * "questionAddId") int questionAddId) throws DAOException;
	 */

}
