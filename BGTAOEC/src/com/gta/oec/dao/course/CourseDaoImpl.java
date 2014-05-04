/**
 * CourseDaoImpl.java	  V1.0   2013-12-27 ����10:40:01
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
@Repository
public class CourseDaoImpl extends BaseDao<CourseVo> implements CourseDao{

	@Override
	public CourseVo insert(CourseVo courseVo) {
		super.insert(courseVo);
		return courseVo;
	}
	@Override
	public CourseVo update(CourseVo courseVo) {
		super.update(courseVo);
		return courseVo;
	}
	@Override
	public CourseVo getCourseById(int courseId) {
		
		return (CourseVo)super.selectOne(generateStatement("getCourseById"), courseId);
	}

	@Override
	public List<CourseVo> getCourseList(CourseVo coursevo, int pageNo,
			int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursevo", coursevo);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("selectCourseList"), map);
	}
	
	/**
	 * 
	 * 功能描述：搜索课程
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @param coursevo
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getSearchCourseList(SearchVo searchVo,int page,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", searchVo.getSearchtext());
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getSearchCourseList"), map);
	}
	
	
	/**
	 * 
	 * 功能描述：搜索课程总条数
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @param searchVo
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getSearchCourseListCount(SearchVo searchVo){
		return (Integer) super.selectOne(generateStatement("getSearchCourseListCount"), searchVo);
	}
	
	
	@Override
	public int getCourseListCount(CourseVo coursevo) {
		return (Integer) super.selectOne(generateStatement("selectCourseListCount"), coursevo);
	}

	@Override
	public List<CourseVo> getCourseListByJob(CourseVo coursevo,int jobID,int page,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursevo", coursevo);
		map.put("jobID", jobID);
		map.put("n", (page-1)*pageSize);                   
		map.put("m", pageSize);                                                                       
		return super.findList(generateStatement("selectCourseListByJob"), map);

	}
	public int getCourseListCountByJob(CourseVo coursevo,int jobID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursevo", coursevo);
		map.put("jobID", jobID);                                                                      
		return (Integer) super.selectOne(generateStatement("getCourseListCountByJob"), map);

	}
	/**
	 * 
	 * 功能描述：根据行业ID,搜索课程列表
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14
	 *
	 * @param coursevo
	 * @param jobID
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseListByProId(SearchVo searchVo,int proId,int page,int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", searchVo.getSearchtext());
		map.put("proId", proId);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		                                                          
		return super.findList(generateStatement("getCourseListByProId"), map);
	}
	
	/**
	 * 
	 * 功能描述：根据行业ID,搜索课程列表总条数
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @param searchVo
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getCourseListByProIdCount(SearchVo searchVo,int proId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", searchVo.getSearchtext());
		map.put("proId", proId);
		return (Integer) super.selectOne(generateStatement("getCourseListByProIdCount"), map);
	}
	


	@Override
	public List<CourseVo> getCourseList(CourseVo courseVo) {
		return super.findList(generateStatement("getCourseList"),courseVo);
	}
	
	@Override
	public List<CourseVo> getCollectInfo(int jobId,List list,int page,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("jobId", jobId);
		map.put("m", pageSize);
		map.put("n", (page-1)*pageSize);
		map.put("jobId", jobId);
		return super.findList(generateStatement("getCollect"), map);
	}
	
	@Override
	public List<CourseVo> courseCollectList(int userId,int collType,int pageNo,int pageSize) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("m", pageSize);
		map.put("n", (pageNo-1)*pageSize);
		map.put("userId", userId);
		map.put("collType", collType);
		return super.findList(generateStatement("getCourseCollect"),map);
	}
	@Override
	public List<CourseVo> getUserCourList(CourseVo courseVo) {
		return super.findList(generateStatement("selectUserCourList"),courseVo);
	}

	@Override
	public int getCourseCount(List list) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", list);
		return (Integer)super.selectOne(generateStatement("getCourseCount"), list);
	}
	
	@Override
	public int getCourseInfoCount(int userId,int collType) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("collType", collType);
		map.put("userId", userId);
		return (Integer)super.selectOne(generateStatement("getCourseInfoCount"), map);
	}

	@Override
	public void updateCourseWeek(Integer courseId) {
		super.update(generateStatement("updateCourseWeek"),courseId);
	}


	@Override
	public int selectCourStudyCount(CourseVo courseVo) {
		return (Integer)super.selectOne(generateStatement("selectCourStudyCount"), courseVo);
	}
	
	@Override
	public void delCourInfo(Integer courseId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseId", courseId);
		super.getSqlSession().delete("delcourInfo", map); 
	}
	
	@Override
	public void delUserCour(Integer courseId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseId", courseId);
		super.getSqlSession().delete("delUserCourInfo",map);
	}

	@Override
	public void collectCour(Integer courseId,Integer collType,Integer userId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseId", courseId);
		map.put("userId", userId);
		map.put("collType", collType);
		super.insert("courCollect", map);
	}

	@Override
	public int checkCollCourse(Integer courseId,Integer collType,Integer userId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseId", courseId);
		map.put("userId", userId);
		map.put("collType", collType);
		return (Integer)super.selectOne(generateStatement("checkCourCollect"), map);
	}
	@Override
	public List<CourseVo> getCourseListByKeyword(String keyString, int num) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", keyString);
		parameter.put("limit", num);
		return  findList(generateStatement("getCourseListByKeyword"), parameter);
	}
	@Override
	public List<CourseVo> getTradeCourse(int tradeId, int courseState,
			int isIndexPush,int pageNo,int pageSize) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("m", pageSize);
		parameter.put("n", (pageNo-1)*pageSize);
		parameter.put("tradeId", tradeId);
		parameter.put("courseState", courseState);
		parameter.put("isIndexPush", isIndexPush);
		return super.findList(generateStatement("getTradeCourse"), parameter);
	}
	/* (non-Javadoc)
	 * @see com.gta.oec.dao.course.CourseDao#getCourseListByUserId(int, int, int)
	 */
	@Override
	public List<CourseVo> getCourseListByUserId(int userId, int pageNo, int pageSize) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("limit", pageSize);
		parameter.put("offset", (pageNo-1)*pageSize);
		parameter.put("userId", userId);
		return super.findList(generateStatement("getCourseListByUserId"), parameter);
		
	}

	@Override
	public int countCourseListByUserId(int userId) {
		return (Integer) super.selectOne(generateStatement("countCourseListByUserId"), userId);
	}
	
	@Override
	public List<CourseVo> getCourseRecommendList() {
		return super.findList(generateStatement("countCourseRecommend"));
		
	}


	@Override
	public List<CourseVo> getExamCourseList(CourseVo coursevo, int examType,int examState,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursevo", coursevo);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m", pageSize);
		map.put("examType", examType);
		map.put("examState",examState);
		return super.findList(generateStatement("selectExamCourList"), map);
	}
	@Override
	public int getExamCourseListCount(CourseVo coursevo, int ExamType,int examState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursevo", coursevo);
		map.put("examType", ExamType);
		map.put("examState", examState);
		return (Integer)super.selectOne(generateStatement("selectExamCourCount"), map);
	}
	
	@Override
	public List<CourseVo> getCourseListByPro(int proId, int page, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("proId", proId);
		map.put("m", pageSize);
		map.put("n", (page-1)*pageSize);
		return super.findList(generateStatement("getCourseInfoByPro"),map);
	}
	
	@Override
	public List<CourseVo> getCourseListByJobGroupId(CourseVo courseVo,int jobPid,int pageNo,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobPid", jobPid);
		map.put("coursevo", courseVo);
		map.put("m", pageSize);
		map.put("n", (pageNo-1)*pageSize);
		return super.findList(generateStatement("getCourseListByJobGroupId"),map);
				
	}
	public int getCourseListCountByJobGroupId(CourseVo courseVo,int jobPid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobPid", jobPid);
		map.put("coursevo", courseVo);
		return (Integer) super.selectOne(generateStatement("getCourseListCountByJobGroupId"),map);
				
	}
	
	@Override
	public List<CourseVo> queryCourseListByProId(CourseVo courseVo,int proId,int pageNo,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseVo", courseVo);
		map.put("proId", proId);
		map.put("m", pageSize);
		map.put("n", (pageNo-1)*pageSize);
		List<CourseVo> list=super.findList(generateStatement("queryCourseListByProId"),map);
		return list;
	}
	@Override
	public int queryCourseListCountByProId(CourseVo courseVo,int proId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("courseVo", courseVo);
		map.put("proId", proId);
		return (Integer) super.selectOne(generateStatement("queryCourseListCountByProId"), map);
	}
	
	@Override
	public Object updateByBrowseTimes(Integer courId, long courseAttention) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courId", courId);
		map.put("courseAttention", courseAttention);
		return super.update(generateStatement("updateByBrowseTimes"),map);
	}
}
