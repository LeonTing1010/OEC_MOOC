package com.gta.oec.service.feedback;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.feedback.FeedbackVo;


/**
 * 功能描述:保存反馈信息
 *
 * @author xin.yi
 *
 * <p>2014-4-23 下午2:40:06<p>
 *
 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
public interface FeedbackService {
	
	public void saveFeedback(FeedbackVo feedbackVo) throws BaseException;

}
