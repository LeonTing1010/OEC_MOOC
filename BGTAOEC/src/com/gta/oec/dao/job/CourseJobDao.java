package com.gta.oec.dao.job;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.CourseJobVo;

public interface CourseJobDao {
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
	public List<CourseJobVo> getCourseJobList(int jobId);
	
	
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
	public List<CourseJobVo> getCourseJobRecommendList(int jobId);
	
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
	public List<CourseJobVo> getCourseJobAllList(int jobId);
	
	/**
	 * 
	 * 功能描述：增加课程岗位关联
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月16日 下午3:11:28</p>
	 *
	 * @param courseJobVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public CourseJobVo addCourseJobRe(CourseJobVo courseJobVo);
	/**
	 * 
	 * 功能描述：获取课程-岗位关联的列表
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-22 上午9:03:40</p>
	 *
	 * @param courseJobVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobList(CourseJobVo courseJobVo);
	/**s
	 * 
	 * 功能描述：删除与课程绑定的所有岗位
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-23 上午10:37:49</p>
	 *
	 * @param courseId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleCourseJobRe(CourseJobVo courseJobVo);
	
	/**
	 * 
	 * 功能描述：根据课程ID查找课程行业岗位信息
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月6日 上午9:55:17</p>
	 *
	 * @param courseId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourJobInfo(int courseId);

	/**
	 * 功能描述：该课程所属岗位下的  其他相关推荐课程
	 *
	 * @author  li.liu
	 * <p>创建日期 ：2014-4-24 下午2:00:20</p>
	 *
	 * @param jobID
	 * @param courseID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getRelativityCourseListByJobId(int jobID,
			int courseID);
}

