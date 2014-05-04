/**
 * CourseDao.java	  V1.0   2014-3-18 下午1:19:44
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.coursemanage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.course.Course;

public interface CourseDao extends SqlMapper {
	
	/**
	 * 
	 * 功能描述：查询所有职业信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-28 下午1:34:15</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> getAllTrade();
	
	/**
	 * 
	 * 功能描述：查询所有岗位群信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-28 下午4:22:10</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> getAllPjob(@Param(value="proId") int proId);
	
	/**
	 * 
	 * 功能描述：查询所有岗位信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-28 下午4:47:36</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> getAllJob(@Param(value="pjobId") int  pjobId);
	
	/**
	 * 
	 * 功能描述：根据行业id、岗位群id，查询岗位信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-29 下午3:20:21</p>
	 *
	 * @param pId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> getJobByPid(@Param(value="course") Course course);
	
	/**
	 * 
	 * 功能描述：审核课程
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-1 上午11:04:40</p>
	 *
	 * @param courseId
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void checkCourse(@Param(value="course") Course course);
	
	/**
	 * 
	 * 功能描述：批量审核课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-1 下午5:18:54</p>
	 *
	 * @param courseId
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void passAudit(@Param(value="volumnCid") List courseId,@Param(value="status") int status);
	
	/**
	 * 
	 * 功能描述：查看课程简介，根据课程id和岗位id
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-2 下午2:52:47</p>
	 *
	 * @param cid
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> getCourseIntroduction(@Param(value="cid") int cid,@Param(value="jobId") int jobId);
	
	/**
	 * 
	 * 功能描述：根据课程id查询课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-3 上午10:10:43</p>
	 *
	 * @param cid
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Course getCourseById(@Param(value="cid") int cid);
	
	public List<Course> allCoursePageQuery(PageModel<Course> page);

	/**
	 * 
	 * 功能描述：查询课程信息列表(带条件查询)
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-11 下午3:46:25</p>
	 *
	 * @param pc
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> allCourseCtxPageQuery(PaginationContext<Course> pc);
	
	/**
	 * 
	 * 功能描述：根据课程id查询课程、学校及岗位信息(修改课程)
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-14 下午1:10:44</p>
	 *
	 * @param courseId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Course courseAmend(@Param(value="cid") int courseId);
	
	/**
	 * 
	 * 功能描述：根据课程对象获取课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 上午10:40:46</p>
	 *
	 * @param course
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Course> courseInfo(@Param(value="course") Course course);
	
	/**
	 * 
	 * 功能描述：插入课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午2:35:21</p>
	 *
	 * @param course
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Course insertCourse(@Param(value="course") Course course);
	
	/**
	 * 
	 * 功能描述：课程修改
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午3:10:50</p>
	 *
	 * @param course
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateCourse(@Param(value="course") Course course);
	
	/**
	 * 功能描述：根据课程审核状态统计课程数量.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月22日 下午2:30:57</p>
	 *
	 * @return
	 * @throws DAOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countCourseByCheckStatus(@Param(value="checkStatus")int checkStatus)throws DAOException;
	
}
