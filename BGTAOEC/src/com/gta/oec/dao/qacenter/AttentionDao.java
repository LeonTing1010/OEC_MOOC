package com.gta.oec.dao.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;

public interface AttentionDao {

	/**
	 * 
	 * 功能描述：保存关注明细
	 * 
	 * @author 刘祚家
	 * <p>
	 * 创建日期 ：2014-1-20
	 * </p>
	 * 
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 * <p>
	 *  修改历史 ：(修改人，修改时间，修改原因/内容)
	 * </p>
	 */
	public AttentionVo saveAttention(AttentionVo attentionVo);

	/**
	 * 
	 * 功能描述：删除关注明细
	 * 
	 * @author 刘祚家
	 * <p>
	 * 创建日期 ：2014-4-1
	 * </p>
	 * 
	 * @return 删除个数
	 * @throws BaseException
	 * 
	 * <p>
	 *  修改历史 ：(修改人，修改时间，修改原因/内容)
	 * </p>
	 */
	public AttentionVo deleteAttention(AttentionVo attentionVo);
	
	
	/**
	 * 功能描述：通过用户id,问题id获取关注问题明细列表.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-20</p>
	 *
	 * @param userID
	 * @param quesID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AttentionVo> getAttentionQuestionByUserId(int userID,int quesID);
	
	/**
	 * 功能描述：通过问题id,查找所有关注该问题的用户.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-10</p>
	 *
	 * @param userID
	 * @param quesID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<AttentionVo> getAttentionUserByQuesId(int quesID);
	
	/**
	 * 
	 * 功能描述：根据用户id查询关注的问题id集合
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-14 下午4:48:53</p>
	 *
	 * @param userID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List getAttentionQuesIdListByUserId(int userID);
}
