package com.gta.oec.dao.qacenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;

public interface QuestionUserDao {
	/**
	 * 
	 * 功能描述：保存提问指定老师列表.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-18 </p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveQuestionUser(List<QuestionUserVo> techerList);
	/**
	 * 功能描述：保存提问指定的老师.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月5日 下午4:57:16</p>
	 *
	 * @param questionUser
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveQuestionUser(QuestionUserVo questionUser);
	
	/**
	 * 
	 * 功能描述：删除没有选择的老师
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-20 </p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleteQuestionUser(List<QuestionUserVo> techerList);
	
	
	/**
	 * 
	 * 功能描述：根据问题ID,获取所有邀请老师列表
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-10
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionUserVo> getAllQuestionUserListByQuesId(int quesId);
	
	/**
	 * 功能描述：更改问题邀请老师回答状态
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateQuestionUserStatus(int quesId,int userId);
	
	
}

