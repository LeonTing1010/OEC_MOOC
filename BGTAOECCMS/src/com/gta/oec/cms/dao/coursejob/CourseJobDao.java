/**
 * CourseJobDao.java	  V1.0   2014-4-10 上午11:13:58
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.coursejob;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.course.CourseJob;

public interface CourseJobDao extends SqlMapper<CourseJob> {

	/**
	 * 
	 * 功能描述：岗位推荐课程
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-10 上午11:17:19</p>
	 *
	 * @param courseJob
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void courseRecommend(@Param(value="coursejob") CourseJob courseJob);
	
	/**
	 * 
	 * 功能描述：根据课程ID获取与课程关联的岗位信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-14 下午1:44:19</p>
	 *
	 * @param courseJob
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJob> jobInfoByCid(@Param(value="coursejob") CourseJob courseJob);
	
	/**
	 * 
	 * 功能描述：删除与课程绑定的岗位
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午4:42:58</p>
	 *
	 * @param courseJob
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleteCourseJob(CourseJob courseJob);
	
	/**
	 * 
	 * 功能描述：增加课程岗位关联
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午4:57:40</p>
	 *
	 * @param courseJob
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void insertCourseJobRe(CourseJob courseJob);
}
