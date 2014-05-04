package com.gta.oec.cms.service.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.feedback.IFeedbackDao;
import com.gta.oec.cms.vo.feedback.Feedback;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
	@Autowired
	public IFeedbackDao fDao;
	@Override
	public List<Feedback> findFeedbackPageQuery(PaginationContext<Feedback> pc) {
		return fDao.findFeedbackPageQuery(pc);
	}

	@Override
	public List<Feedback> findSource() {
		return fDao.findSource();
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=java.lang.Throwable.class)  //用于取消事务控制
	@Override
	public void delFeedbackByID(Integer fID) {
		fDao.delFeedbackByID(fID);
	}

}
