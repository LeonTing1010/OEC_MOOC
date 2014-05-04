package com.gta.oec.dao.qacenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;
import com.gta.oec.vo.qacenter.QuestionVo;

@Repository
public class PraiseDetailDaoImpl extends BaseDao<PraiseDetailVo> implements
		PraiseDetailDao {

	/**
	 * 
	 * 功能描述：保存赞明细
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-16
	 *         </p>
	 * 
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PraiseDetailVo savePraiseDetail(PraiseDetailVo praiseDetailVo) {
		if (praiseDetailVo != null) {
			super.insert(generateStatement("savePraiseDetail"), praiseDetailVo);
		}
		return praiseDetailVo;
	}
	
	/**
	 * 
	 * 功能描述：删除赞明细
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-1
	 *         </p>
	 * 
	 * @return 删除个数
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PraiseDetailVo deletePraiseDetail(PraiseDetailVo praiseDetailVo) {
		if (praiseDetailVo != null) {
			super.delete(generateStatement("deletePraiseDetail"), praiseDetailVo);
		}
		return praiseDetailVo;
	}
	

	@Override
	public List<PraiseDetailVo> getPraiseDetailByUserIdByAnsId(int userID,
			int answID) {
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("userID", userID);
		parameter.put("answID", answID);
		
		return findList(generateStatement("getPraiseDetailByUserIdByAnsId"), parameter);
	}

}
