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

import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseCommentVo;

public interface CourseCommentService{

	/**
	 * 功能描述：发表课程评论
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午12:22:21</p>
	 *
	 * @param courseComment
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @return 
	 * @throws SystemDBException 
	 */
	int deliverComment(CourseCommentVo courseComment,String comSource) throws SystemDBException;
	
	/**
	 * 功能描述：根据课程评论id查找课程评论
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午1:58:58</p>
	 *
	 * @param comID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	CourseCommentVo findCourseCommentById(int comID);
	
	/**
	 * 功能描述：根据课程评论id和查询页数查找评论集合
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-22 下午1:59:24</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	PageModel findCourseCommentCoursePageListByCourseId(int courseID,int pageNo,int pageSize);
	
}