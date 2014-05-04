package com.gta.oec.dao.course;

import java.util.List;

import com.gta.oec.vo.course.CourseCommentVo;

public interface CourseCommentDao {

	/**
	 * 功能描述：对课程发表评论
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午1:17:35</p>
	 *
	 * @param courseComment
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws Exception 
	 */
	int saveCourseComment(CourseCommentVo courseComment) throws Exception;
	
	/**
	 * 功能描述：根据课程评论id和查询页数查找评论集合
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午2:04:09</p>
	 *
	 * @param comID
	 * @param pageSize
	 * @param pageSize 
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseCommentVo> findCourseCommentCourseListBySize(int comID,
			int page, int pageSize);

	/**
	 * 功能描述：根据课程Id查找该课程下评论总数
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午5:12:20</p>
	 *
	 * @param courseID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	Integer findCourseCommentCountByCourseId(int courseID);
}