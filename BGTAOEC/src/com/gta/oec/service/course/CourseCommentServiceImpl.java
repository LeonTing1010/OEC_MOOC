/**
 * CourseService.java	  V1.0   2013-12-27 ����10:30:24
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.course;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseCommentDao;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseCommentVo;

@Service
public class CourseCommentServiceImpl implements CourseCommentService{
	private static final Log logger = LogFactory.getLog(CourseCommentServiceImpl.class);
	
	@Autowired
	private CourseCommentDao courseCommentDao;
	
	@Override
	public int deliverComment(CourseCommentVo courseComment,String comSource) throws SystemDBException {
		try {
			courseComment.setComCriTime(new Date());
			courseComment.setComSource(comSource);
			return courseCommentDao.saveCourseComment(courseComment);
		} catch (Exception e) {
			   logger.error("System DB operate error!",e);
			   throw new SystemDBException("System DB operate error!");
		}
		
	}

	@Override
	public CourseCommentVo findCourseCommentById(int comID) {
		return null;
	}

	@Override
	public PageModel findCourseCommentCoursePageListByCourseId(int courseID,int pageNo,
			int pageSize) {
		
		PageModel pageModel = new PageModel();
		//根据课程id查找该课程下的课程列表。
		List<CourseCommentVo> commentList = courseCommentDao.findCourseCommentCourseListBySize(courseID, pageNo, pageSize);
		int commentCount = courseCommentDao.findCourseCommentCountByCourseId(courseID);
		
		pageModel.setTotalItem(commentCount); //总记录数
		pageModel.setList(commentList);
		pageModel.setToPage(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}
	
}