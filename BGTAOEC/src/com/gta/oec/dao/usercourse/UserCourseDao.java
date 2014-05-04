/**
 * RegisterDao.java	  V1.0   2014年1月6日 上午11:37:38
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.usercourse;

import java.util.List;

import com.gta.oec.vo.usercourse.UserCourseVo;




public interface UserCourseDao {

	
	// 根据用户id查询课程
	public List<UserCourseVo> getUserCourseList(int userId,int page,int pageSize);
	

	// 根据课程id查询用户id集合
	public List<?> getUserIdsList(int courId);

	
	//根据用户id和考试类型id查询带有考试的课程id集合
	public List<?> getCourseIdsListByUserIdAndExamType(int userId,int examType,int page,int pageSize);
	
	//根据用户id查询带有笔记的课程id集合
	public List<?> getHasNoteCourseIdsListByUserId(int userId,int page,int pageSize);
	
	// 根据用户id查询课程数
	public int getCourseListCountByUserId(int userId);
	
	//根据用户id查询考试数
	public int getCourseExamCount(int userId,int examType);
}
