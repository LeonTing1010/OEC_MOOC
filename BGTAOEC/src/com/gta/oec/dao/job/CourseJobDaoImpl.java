package com.gta.oec.dao.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobAuthenticationVo;

@Repository
public class CourseJobDaoImpl extends BaseDao<CourseJobVo> implements CourseJobDao{
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-9 下午19:0</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobList(int jobId){
		
		return super.findList(generateStatement("getCourseJobList"), jobId);
	}
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位推荐课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobRecommendList(int jobId){
		return super.findList(generateStatement("getCourseJobRecommendList"), jobId);
	}

	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位所有课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobAllList(int jobId){
		return super.findList(generateStatement("getCourseJobAllList"), jobId);
	}

	@Override
	public CourseJobVo addCourseJobRe(CourseJobVo courseJobVo) {
		super.insert(generateStatement("insertCourseJobRe"), courseJobVo);
		return courseJobVo;
	}

	@Override
	public List<CourseJobVo> getCourseJobList(CourseJobVo courseJobVo) {
		return super.findList(generateStatement("selectCourseJobList"),courseJobVo);
	}

	@Override
	public void deleCourseJobRe(CourseJobVo courseJobVo) {
		if (null!=courseJobVo&&courseJobVo.getCourseID()>0) {
			this.getSqlSession().delete(generateStatement("deleCourseJobRe"),courseJobVo);	
		}
		
	}

	@Override
	public List<CourseJobVo> getCourJobInfo(int courseId) {
		return super.findList("selectCourJobInfo", courseId);
	}

	@Override
	public List<CourseJobVo> getRelativityCourseListByJobId(int jobID,
			int courseID) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("jobID", jobID);
		map.put("courseID",courseID);
		return super.findList(generateStatement("getRelativityCourseListByJobId"), map);
	}
	
}
