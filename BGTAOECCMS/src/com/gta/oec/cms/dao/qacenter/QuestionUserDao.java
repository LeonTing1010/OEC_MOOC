/**
 * QuestionUserDao.java	  V1.0   2014年4月17日 上午11:12:33
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
import com.gta.oec.cms.vo.qacenter.QuestionUserVo;

/**
 * 功能描述：问题邀请回答表.dao
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QuestionUserDao extends SqlMapper<QuestionUserVo> {

	/**
	 * 功能描述：通过主键id删除该表这条数据.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 上午11:13:57
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
	public int deleteQuestionUserById(@Param(value = "id") int id) throws DAOException;

	/**
	 * 功能描述： 通过主键id获取这条邀请表数据.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 上午11:27:22
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
	public QuestionUserVo getQuestionUserById(@Param(value = "id") int id) throws DAOException;

	/**
	 * 功能描述：删除一个问题的邀请表数据.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月18日 下午5:01:30</p>
	 *
	 * @param questionId问题id.
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteQuestionUserByQuestionId(@Param(value = "questionId") int questionId) throws DAOException;
	
	
	
}
