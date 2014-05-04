/**
 * RegisterDaoImpl.java	  V1.0   2014年1月6日 上午11:38:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.usercourse;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.usercourse.UserCourseVo;
@Repository
public class UserCourseDaoImpl extends BaseDao<UserCourseVo> implements UserCourseDao{


	/**
	 * 根据课程id查询个数
	 */
	@Override
	public List<UserCourseVo> getUserCourseList(int userId,int page,int pageSize) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getUserCourseList"),map);
         
	}
	

	/**
	 * 根据课程id查询用户id集合
	 */
	public List<?> getUserIdsList(int courId) {
		
		 return super.findList(generateStatement("getUserIdsList"),courId);
	}


	
	/**
	 * 根据用户id和考试类型id查询带有考试的课程id集合
	 */
	public List<?> getCourseIdsListByUserIdAndExamType(int userId,int examType,int page,int pageSize){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("examType", examType);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		
		return super.findList(generateStatement("getCourseIdsListByUserIdAndExamType"),map);
	}
	
	/**
	 * 根据用户id查询带有笔记的课程id集合
	 */
	public List<?> getHasNoteCourseIdsListByUserId(int userId,int page,int pageSize){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		
		return super.findList(generateStatement("getHasNoteCourseIdsListByUserId"),map);
	}
	
	/**
	 * 根据用户id查询改用户的课程数量
	 */
	public int getCourseListCountByUserId(int userId){
		
		return (Integer) super.selectOne(generateStatement("getCourseListCountByUserId"), userId);
	}
	
	/**
	 * 根据用户的课程id查询考试数
	 */
    public int getCourseExamCount(int userId,int examType){
		
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("examType", examType);
		return (Integer) super.selectOne(generateStatement("getCourseExamCount"), map);
	}
	

}
