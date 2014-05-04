/**
 * PraiseDetailDao.java	  V1.0   2014年4月17日 下午2:28:23
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
import com.gta.oec.cms.vo.qacenter.PraiseDetailVo;

/**
 * 功能描述：回答的赞明细.dao层.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface PraiseDetailDao extends SqlMapper<PraiseDetailVo> {

	/**
	 * 功能描述：获取赞明细根据主键id.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月17日 下午2:30:20</p>
	 *
	 * @param praiseId赞id
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PraiseDetailVo getPraisedetailByPraiseId(@Param(value = "praiseId") int praiseId)
			throws DAOException;
	
	/**
	 * 功能描述：删除赞明细根据主键id
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月17日 下午2:41:05</p>
	 *
	 * @param praiseId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deletePraiseDetailByPraiseId(@Param(value = "praiseId") int praiseId)throws DAOException;
	
	/**
	 * 功能描述：根据回答的id,删除这个回答的赞明细.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月18日 下午3:31:22</p>
	 *
	 * @param answerId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deletePraiseDetailByAnswerId(@Param(value = "answerId") int answerId)throws DAOException;
	
	
	
}
