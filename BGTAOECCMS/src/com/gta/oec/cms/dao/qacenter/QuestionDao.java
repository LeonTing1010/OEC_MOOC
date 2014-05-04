/**
 * QuestionDao.java	  V1.0   2014年3月25日 下午5:31:02
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

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.qacenter.QuestionVo;

/**
 * 功能描述：question dao 层.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QuestionDao extends SqlMapper<QuestionVo> {

	/**
	 * 功能描述：分页带搜索条件获取问题列表.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午2:21:25
	 *         </p>
	 * 
	 * @param pc
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> questionsPageQuery(PaginationContext<QuestionVo> pc)
			throws DAOException;

	/**
	 * 功能描述：根据问题id获取问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午2:22:11
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
	public QuestionVo getQuestionByQuestionId(@Param(value = "questionId") int questionId)
			throws DAOException;
	
	/**
	 * 功能描述：判断是否是一个库中存在的问题.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月18日 上午10:08:42</p>
	 *
	 * @param questionId
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int isExistQuestion(@Param(value="questionId") int questionId)throws DAOException;

	/**
	 * 功能描述：修改一个问题
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午3:03:44
	 *         </p>
	 * 
	 * @param questionVo
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int updateQuestion(QuestionVo questionVo) throws DAOException;

	/**
	 * 功能描述：统计库中一共有多少精选问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月11日 下午3:44:48
	 *         </p>
	 * 
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int countChosenQuestions() throws DAOException;

	/**
	 * 功能描述：删除一条问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月16日 上午9:01:20
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
	public int deleteQuestion(@Param(value = "questionId") int questionId) throws DAOException;

}