package com.gta.oec.dao.feedback;

import com.gta.oec.vo.feedback.FeedbackVo;

/**
 * 功能描述:反馈信息
 *
 * @author xin.yi
 *
 * <p>2014-4-23 下午2:46:44<p>
 *
 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
public interface FeedbackDao {

	public void saveFeedback(FeedbackVo feedbackVo);

}
