package com.gta.oec.dao.qacenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.QuestionUserVo;

@Repository
public class QuestionUserDaoImpl extends BaseDao<QuestionUserVo> implements QuestionUserDao{
	/**
	 * 
	 * 功能描述：保存提问指定老师
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-18 </p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveQuestionUser(List<QuestionUserVo> techerList){
		super.insertBatch(generateStatement("saveQuestionUser"), techerList);
	}
	
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
	public void deleteQuestionUser(List<QuestionUserVo> techerList){
		super.deleteBatch(generateStatement("deleteQuestionUser"), techerList);
	}
	
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
	public List<QuestionUserVo> getAllQuestionUserListByQuesId(int quesId){
		return super.findList(generateStatement("getAllQuestionUserListByQuesId"),quesId);
	}
	
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
	public int updateQuestionUserStatus(int quesId,int userId){
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("quesId",quesId);
		parameter.put("userId", userId);
		return super.update(generateStatement("updateQuestionUserStatus"), parameter);
	}

	@Override
	public void saveQuestionUser(QuestionUserVo questionUser) {
		super.insert(generateStatement("saveQuestionUser"), questionUser);
	}
}
