/**
 * AnswerAttentionDao.java	  V1.0   2014年4月1日 下午8:37:49
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
import com.gta.oec.cms.vo.qacenter.AnswerAttentionVo;

/**
 * 功能描述：回答的关注dao层
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface AnswerAttentionDao extends SqlMapper<AnswerAttentionVo> {
	/**
	 * 功能描述：获取一个回答的关注信息.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月17日 下午2:45:56</p>
	 *
	 * @param id
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public AnswerAttentionVo getAnswerAttentionById(@Param(value = "id") int id)
			throws DAOException;

	/**
	 * 功能描述：根据主键id.删除一个回答的关注信息.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月17日 下午2:46:22</p>
	 *
	 * @param id
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteAnswerAttentionById(@Param(value = "id") int id)
			throws DAOException;

	
	/**
	 * 功能描述：根据回答的id.删除一个回答的关注信息
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月18日 下午4:02:13</p>
	 *
	 * @param answerId
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteAnswerAttentionByAnswerId(@Param(value = "answerId") int answerId)
			throws DAOException;
	
	
}
