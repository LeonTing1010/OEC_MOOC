/**
 * QuestionAttentionDao.java	  V1.0   2014年4月17日 下午2:53:21
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.qacenter;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.qacenter.QuestionAttentionVo;

/**
 * 功能描述：关注问题.dao层
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QuestionAttentionDao extends SqlMapper<QuestionAttentionVo> {

	/**
	 * 功能描述：获取一条关注问题的信息.根据id.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 下午2:55:04
	 *         </p>
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionAttentionVo getQuestionAttentionById(@Param(value = "id") int id)
			throws DAOException;

	/**
	 * 功能描述：删除一条关注问题的信息.根据id.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 下午2:55:11
	 *         </p>
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteQuestionAttentionById(@Param(value = "id") int id) throws DAOException;

	/**
	 * 功能描述：根据问题id,删除这个问题的所有关注信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 上午10:30:10
	 *         </p>
	 * 
	 * @param questionId
	 * @return 删除条数.
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteQuestionAttentionByQuestionId(@Param(value = "questionId") int questionId)
			throws DAOException;

}
