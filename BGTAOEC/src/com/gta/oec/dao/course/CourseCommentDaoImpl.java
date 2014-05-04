package com.gta.oec.dao.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.course.CourseCommentVo;
@Repository
public class CourseCommentDaoImpl extends BaseDao<CourseCommentVo> implements CourseCommentDao{

	@Override
	@Transactional
	public int saveCourseComment(CourseCommentVo courseComment) throws Exception {
		
		int status = 0;
		try {
			status = super.insert(generateStatement("saveCourseComment"), courseComment);
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (logger.isDebugEnabled()) {
				
				logger.debug(e.getMessage());
				throw e;
			}
		}
		return status;
		
	}

	@Override
	public List<CourseCommentVo> findCourseCommentCourseListBySize(int courseID,int page,
			int pageSize) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("courseID", courseID);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("findCourseCommentCourseListBySize"),map) ;
	}

	@Override
	public Integer findCourseCommentCountByCourseId(int courseID) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("courseID", courseID);
		return (Integer) super.selectOne(generateStatement("findCourseCommentCountByCourseId"),map) ;
	}

}
