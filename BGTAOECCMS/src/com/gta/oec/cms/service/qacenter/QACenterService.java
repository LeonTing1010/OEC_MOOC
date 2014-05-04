/**
 * QACenter.java	  V1.0   2014年3月26日 下午2:18:29
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.qacenter;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.vo.qacenter.QuestionVo;

/**
 * 功能描述：答疑中心后台管理服务层接口.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface QACenterService {
	PaginationContext<QuestionVo> findQuestionsPagination(PaginationContext<QuestionVo> pc)
			throws ServiceException;

	/**
	 * 功能描述：通过问题id得到一个问题.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午2:40:17
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo getQuestionByQuestionId(int questionId) throws ServiceException;

	/**
	 * 功能描述：修改问题数据.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午2:41:15
	 *         </p>
	 * 
	 * @param questionVo
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int updateQuestion(QuestionVo questionVo) throws ServiceException;

	/**
	 * 功能描述：通过问题id得到一个问题的详细信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月1日 下午8:36:12
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo getQuestionDetailByQuestionId(int questionId) throws ServiceException;

	/**
	 * 功能描述：批量修改问题是否屏蔽.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月2日 上午10:13:29
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int batchUpdateVisibleQuestionOrNot(int[] questionIds, int updateType)
			throws ServiceException;

	/**
	 * 功能描述：修改某个问题是否屏蔽.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月11日 下午3:05:45
	 *         </p>
	 * 
	 * @param questionId
	 * @param updateType
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int updateVisibleQuestionOrNot(int questionId) throws ServiceException;

	/**
	 * 功能描述：修改问题是否推荐为精选.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月2日 上午10:18:50
	 *         </p>
	 * 
	 * @param questionId
	 * @param limit
	 *            推选精选问题的限制上限数量.
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int updateChosenQuestionOrNot(int questionId, int limit) throws ServiceException;

	/**
	 * 功能描述：删除一条问题,包括这个问题下面的所有数据.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月17日 下午3:01:10
	 *         </p>
	 * 
	 * @param questionId
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public void deleteQuestionWithAllData(int questionId) throws ServiceException;

}
