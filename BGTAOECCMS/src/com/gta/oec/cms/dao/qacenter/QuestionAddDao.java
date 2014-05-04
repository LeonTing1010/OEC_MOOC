/**
 * QuestionAddDao.java	  V1.0   2014年4月3日 下午2:23:12
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
import com.gta.oec.cms.vo.qacenter.QuestionAddVo;

/**
 * 功能描述：追问dao层接口.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QuestionAddDao extends SqlMapper<QuestionAddVo> {

	/**
	 * 功能描述：通过回答id(是哪个回答的追问)获取追问list.带追问的回答.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月3日 下午2:24:44
	 *         </p>
	 * 
	 * @param answerId回答id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionAddVo> getQuestionAddListByAnswerId(@Param(value = "answerId") int answerId)
			throws DAOException;

	/**
	 * 功能描述：通过回答id(是哪个回答的追问)获取追问list.(仅追问数据)
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月3日 下午2:24:44
	 *         </p>
	 * 
	 * @param answerId回答id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionAddVo> getQuestionAddSimplyListByAnswerId(
			@Param(value = "answerId") int answerId) throws DAOException;

	/**
	 * 功能描述：通过追问id.删除追问.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 上午10:56:58
	 *         </p>
	 * 
	 * @param questionAddId追问问题id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteQuestionAddByQuestionAddId(@Param(value = "questionAddId") int questionAddId)
			throws DAOException;

	/**
	 * 功能描述：通过回答id(是哪个回答的追问)删除追问全部数据.包括(回答-追问关系表).不包括追问的回答.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月18日 下午1:33:52
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
	public int deleteQuestionAddTotallyByAnswerId(@Param(value = "answerId") int answerId)
			throws DAOException;

}
