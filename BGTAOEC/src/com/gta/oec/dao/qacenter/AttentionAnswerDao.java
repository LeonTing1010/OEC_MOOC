package com.gta.oec.dao.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.qacenter.AttentionAnswerVo;

public interface AttentionAnswerDao {

	/**
	 * 
	 * 功能描述：批量保存关注该问题的用户与回答关系
	 * 
	 * @author 刘祚家
	 * <p>
	 * 创建日期 ：2014-2-10
	 * </p>
	 * 
	 * @throws BaseException
	 * 
	 * <p>
	 *  修改历史 ：(修改人，修改时间，修改原因/内容)
	 * </p>
	 */
	public int saveAttentionAnswerVoList(List<AttentionAnswerVo> attentionAnswerList);

	
	/**
	 * 
	 * 功能描述：根据问题ID，更新关注问题的答案为已读状态 
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-4-3</p>
	 *
	 * @param quesId
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateAttentionAnswIsReadByquesId(int userId,List quesIds);
	
}
