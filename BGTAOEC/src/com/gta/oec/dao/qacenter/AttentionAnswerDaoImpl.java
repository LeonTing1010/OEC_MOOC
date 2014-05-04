package com.gta.oec.dao.qacenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AttentionAnswerVo;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;

@Repository
public class AttentionAnswerDaoImpl extends BaseDao<AttentionAnswerVo> implements AttentionAnswerDao {

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
	public int saveAttentionAnswerVoList(List<AttentionAnswerVo> attentionAnswerList) {

		return super.insertBatch(generateStatement("saveAttentionAnswerVoList"), attentionAnswerList);
	}
	
	
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
	public int updateAttentionAnswIsReadByquesId(int userId,List quesIds){
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("userId", userId);
		parameter.put("list", quesIds);
		
		return super.update(generateStatement("updateAttentionAnswIsReadByquesId"),parameter);
	}

}
