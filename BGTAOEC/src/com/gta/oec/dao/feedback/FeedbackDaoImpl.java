package com.gta.oec.dao.feedback;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.feedback.FeedbackVo;
@Repository
public class FeedbackDaoImpl extends BaseDao<FeedbackVo> implements FeedbackDao {

	@Override
	public void saveFeedback(FeedbackVo feedbackVo) {
		super.insert(generateStatement("insertFeedback"), feedbackVo);
	}

}
