package com.gta.oec.dao.qacenter;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.ReAnswerQuestionAddVo;

@Repository
public class ReAnswerQuestionAddDaoImpl extends BaseDao<ReAnswerQuestionAddVo> implements ReAnswerQuestionAddDao {
	
	/**
	 * 
	 * 功能描述：保存回答与追加问题关系（中间表）
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-23
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public ReAnswerQuestionAddVo saveReAnswerQuestionAdd(ReAnswerQuestionAddVo reAnswerQuestionAddVo) {
		if (reAnswerQuestionAddVo != null) {
			super.insert(generateStatement("saveReAnswerQuestionAdd"), reAnswerQuestionAddVo);
		}
		return reAnswerQuestionAddVo;
	}
	
}
