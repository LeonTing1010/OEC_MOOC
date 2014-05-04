/**
 * JobService.java	  V1.0   2014-1-7 ����13:30:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.service.job;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobAuthenticationVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.trade.TradeVo;
import com.gta.oec.vo.user.UserVo;
	
	public interface JobService {
	    /**
	     * 
	     * 功能描述：根据行业ID获取岗位群信息(里面包含岗位信息)
	     *
	     * @author  bingzhong.qin
	     * <p>创建日期 ：2014-1-9 下午3:41:11</p>
	     *
	     * @param proId 行业id
	     * @return
	     * @throws BaseException
	     *
	     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	     */
		public List<JobVo> getJobGroupDetailByProid(int proId) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据行业ID查询岗位群
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-9 下午3:27:17</p>
		 *
		 * @param proId
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobVo> getJobGroupByProid(int proId)throws BaseException;
		/**
		 * 
		 * 功能描述：根据岗位群ID查询岗位群下的岗位信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-9 下午3:35:53</p>
		 *
		 * @param proId
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobVo> getJobByJobGroupId(int proId)throws BaseException;
		/**
		 * 
		 * 功能描述：获取行业信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-9 下午4:31:26</p>
		 *
		 * @param tradeVo
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<TradeVo> getTradeList(TradeVo tradeVo,int page,int pageSize) throws BaseException;
		/**
		 * 
		 * 功能描述：获取岗位群及岗位群下的岗位
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-10 下午6:36:43</p>
		 *
		 * @param tradeVo
		 * @param page
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobVo> getJobGroupDetailList( JobVo jobVo) throws BaseException;
		
		/**
		 * 
		 * 功能描述：获取岗位列表
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-9 下午3:55:25</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobVo> getJobList(JobVo jobVo,int page ,int pageSize) throws BaseException;
		/**
		 * 
		 * 功能描述：获取岗位列表-分页
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-4-1 下午5:18:59</p>
		 *
		 * @param jobVo
		 * @param pageNo
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel queryJobList(JobVo jobVo,int pageNo ,int pageSize) throws BaseException;
		/**
		 * 
		 * 功能描述：根据岗位ID查询岗位信息
		 *
		 * @author  刘祚家
		 * <p>创建日期 ：2014-1-9 下午19:0</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public JobVo getPositionDetail(UserVo userVo,int jobId) throws BaseException;
		
		
		/**
		 * 
		 * 功能描述：根据岗位ID得到岗位认证列表信息
		 *
		 * @author  刘祚家
		 * <p>创建日期 ：2014-1-9 下午19:0</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobAuthenticationVo> getJobAuthenticationList(int jobId) throws BaseException;
		
		
		/**
		 * 
		 * 功能描述：根据岗位ID得到岗位推荐认证列表信息
		 *
		 * @author  刘祚家
		 * <p>创建日期 ：2014-1-10</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobAuthenticationVo> getJobAuthRecommendList(int jobId) throws BaseException;
		
		
		/**
		 * 
		 * 功能描述：根据岗位ID得到岗位课程列表-按照时间最新3条信息
		 *
		 * @author  刘祚家
		 * <p>创建日期 ：2014-1-10</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseJobVo> getCourseJobList(int jobId) throws BaseException;
		
		
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
		public List<CourseJobVo> getCourseJobRecommendList(int jobId) throws BaseException;
		
		
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
		public List<CourseJobVo> getCourseJobAllList(int jobId) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据岗位id查询课程信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-15 下午3:19:46</p>
		 *
		 * @param jobId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseVo> getCourseByJobId(int jobId,List list,int page,int pageSize)throws BaseException;
		
		/**
		 * 
		 * 功能描述：获得所有岗位信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-15 下午2:56:49</p>
		 *
		 * @param jobVo
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
//		public List<JobVo> getAllJobInfo(JobVo jobVo,int page,int pageSize) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据用户id查询岗位信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-21 上午9:44:23</p>
		 *
		 * @param jobVo
		 * @param userId
		 * @param page
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel getAllJobInfoByUid(JobVo jobVo,List list,Integer page,Integer pageSize) throws BaseException;
		
		/**
		 * 
		 * 功能描述：岗位收藏总数
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-21 上午10:50:59</p>
		 *
		 * @param jobVo
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int getCountJobDetail(JobVo jobVo) throws BaseException;
		/**
		 * 
		 * 功能描述：根据课程ID获取与课程关联的岗位信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-22 下午2:02:01</p>
		 *
		 * @param courseId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseJobVo> getAssociatedJobByCourseId(Integer courseId) throws BaseException;
		
		
		/**
		 * 
		 * 功能描述：根据老师ID,得到老师所属行业、岗位群
		 *
		 * @author  刘祚家
		 * <p>创建日期 ：2014-1-21</p>
		 *
		 * @param teacherId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public JobVo getTeacherProfessionAddJob(int teacherId) throws BaseException;
		
		/**
		 * 
		 * 功能描述：岗位收藏，岗位信息条数
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-22 下午12:17:17</p>
		 *
		 * @param list
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int getCourseCount(List list)throws BaseException;
		
		/**
		 * 
		 * 功能描述：岗位收藏，当前用户收藏的岗位
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-13 上午9:47:43</p>
		 *
		 * @param list
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel getJobNameList(List list,Integer page,Integer pageSize)throws BaseException;
		
		/**
		 * 
		 * 功能描述：岗位收藏，用户收藏的该岗位下的课程
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-13 上午10:19:00</p>
		 *
		 * @param list
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel getCourNameList(List<JobVo> list,Integer page,Integer pageSize) throws BaseException;
		
		/**
		 * 
		 * 功能描述：查询当前岗位是否已收藏
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-17 下午2:59:27</p>
		 *
		 * @param jobId
		 * @param userId
		 * @param collType
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int jobHasColl(Integer jobId,Integer userId,Integer collType) throws BaseException;
		
		/**
		 * 
		 * 功能描述：收藏当前岗位
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-17 下午3:37:05</p>
		 *
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public void collJob(Integer jobId,Integer userId,Integer collectype) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据行业id查询该行业下所有的岗位信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-20 下午7:37:00</p>
		 *
		 * @param proId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(bingzhong.qin 修改命名 返回参数)</p>
		 */
		public PageModel getJobByTradeId(Integer tradeId,int pageNo,int pageSize) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据岗位群id，查询该岗位群下的所有岗位
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-2-22 上午11:02:43</p>
		 *
		 * @param jobId
		 * @param pageNo
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(bingzhong.qin 修改命名，修改返回参数)</p>
		 */
		public PageModel getJobByJobGroupId(Integer jobGroupId,int pageNo,int pageSize) throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据课程ID查找课程行业岗位信息
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年3月6日 上午9:58:34</p>
		 *
		 * @param courseId
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseJobVo> getCourseJobBycourseId(int courseId)throws BaseException;
		
		/**
		 * 
		 * 功能描述：查询某岗位岗位群下其他相关岗位
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年3月20日 下午2:17:03</p>
		 *
		 * @param jobVo
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<JobVo> getRelateJob(UserVo userVo ,JobVo jobVo)throws BaseException;

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
		 * @throws SystemDBException 
		 */
		public List<CourseJobVo> getRelativityCourseListByJobId(int jobID,
				int courseID) throws SystemDBException;
	}

