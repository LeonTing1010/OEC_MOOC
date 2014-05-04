package com.gta.oec.service.feedback;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.config.InterfaceExtensionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.feedback.FeedbackDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.feedback.FeedbackVo;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	private static final Log logger = LogFactory.getLog(FeedbackServiceImpl.class);
	
	@Autowired
	private FeedbackDao feedbackDao;
	@Override
	public void saveFeedback(FeedbackVo feedbackVo) throws BaseException {	
		if(feedbackVo==null){
		throw new InterfaceFieldExcepiton("feedback is null");
		}
		try{
			feedbackDao.saveFeedback(feedbackVo);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

}
