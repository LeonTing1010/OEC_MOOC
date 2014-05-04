package com.gta.oec.cms.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.comment.ICommentDao;
import com.gta.oec.cms.vo.comment.Comment;

@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private ICommentDao cDao;
	@Override
	public List<Comment> findCommentPageQuery(PaginationContext<Comment> pc) {
		return cDao.findCommentPageQuery(pc);
	}

	@Override
	public List<Comment> findSource() {
		return cDao.findSource();
	}

	@Override
	public void updateCommentByID(Integer cID) {
		cDao.updateCommentByID(cID);
	}

}
