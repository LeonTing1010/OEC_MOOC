package com.gta.oec.cms.service.feedback;

import java.util.List;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.vo.feedback.Feedback;

public interface IFeedbackService  {
	/**
	 * 
	 * 功能描述：查询所有意见反馈信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Feedback> findFeedbackPageQuery(PaginationContext<Feedback> pc);
	
	
	/**
	 * 
	 * 功能描述：查询所有点评来源
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Feedback> findSource();
	
	
	/**
	 * 
	 * 功能描述：删除意见反馈信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void delFeedbackByID(Integer fID);
}
