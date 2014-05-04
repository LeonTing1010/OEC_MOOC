/**
 * CourseService.java	  V1.0   2014-3-18 下午1:13:43
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.coursemanage;

import java.util.List;
import java.util.Map;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.vo.course.Course;
import com.gta.oec.cms.vo.course.CourseDetail;
import com.gta.oec.cms.vo.course.CourseJob;
import com.gta.oec.cms.vo.resource.ResourceVo;

public interface CourseService {

	/**
	 * 
	 * 功能描述：查询所有职业信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-28 下午1:31:48
	 *         </p>
	 * 
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> getAllTrade();

	/**
	 * 
	 * 功能描述：查询所有岗位群信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-28 下午4:20:39
	 *         </p>
	 * 
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> getAllPjob(int proId);

	/**
	 * 
	 * 功能描述：查询所有岗位信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-28 下午4:46:16
	 *         </p>
	 * 
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> getAllJob(int pjobId);

	/**
	 * 
	 * 功能描述：根据行业id、岗位id，查询岗位信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-29 下午3:17:39
	 *         </p>
	 * 
	 * @param pm
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> getJobByPid(Course course);

	/**
	 * 
	 * 功能描述：审核课程
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-1 上午11:01:50
	 *         </p>
	 * 
	 * @param courseId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void checkCourse(Course course);

	/**
	 * 
	 * 功能描述：岗位推荐课程
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-1 下午3:56:34
	 *         </p>
	 * 
	 * @param courseId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void courseRecommend(CourseJob course);

	/**
	 * 
	 * 功能描述：批量审核课程信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-1 下午5:17:09
	 *         </p>
	 * 
	 * @param courseId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void passAudit(List courseId, int status);

	/**
	 * 
	 * 功能描述：查看课程简介,根据课程id和岗位id
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-2 下午2:51:04
	 *         </p>
	 * 
	 * @param cid
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> getCourseIntroduction(int cid, int jobId);

	/**
	 * 
	 * 功能描述：查看视频，章节信息显示
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-3 上午10:02:34
	 *         </p>
	 * 
	 * @param courseId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public Course getParagraphicInfo(int courseId);

	/**
	 * 
	 * 功能描述：设置直播通道url
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-4 上午9:43:39
	 *         </p>
	 * 
	 * @param courseId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void setChannelURL(CourseDetail courseDetail);

	/**
	 * 
	 * 功能描述：根据课程id查询课程信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-8 上午11:09:50
	 *         </p>
	 * 
	 * @param courseId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public CourseDetail getCourseInfoByCid(int courseId);

	public List<Course> allCoursePageQuery(PageModel<Course> pm);

	/**
	 * 
	 * 功能描述：查询课程信息列表(带条件查询)
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-11 下午3:47:08
	 *         </p>
	 * 
	 * @param pc
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> allCourseCtxPageQuery(PaginationContext<Course> pc);

	/**
	 * 功能描述：获取课程章节的资源.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月8日 下午3:11:37
	 *         </p>
	 * 
	 * @param sectionId
	 *            (章节id) resourceType(资源类型)
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<ResourceVo> getSectionCourseResourceList(int sectionId, int resourceType,
			int courseId) throws ServiceException;

	/**
	 * 功能描述：统计课程章节资源数量.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月9日 下午5:58:38
	 *         </p>
	 * 
	 * @param sectionId
	 *            (章节id)
	 * @param resourceType
	 *            (资源类型)
	 * @return
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int countCourseResourceListByResourceVo(int sectionId, int resourceType)
			throws ServiceException;

	/**
	 * 
	 * 功能描述：根据课程id查询课程、学校及岗位信息(修改课程)
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-14 上午11:28:25
	 *         </p>
	 * 
	 * @param courseId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public Course courseAmend(int courseId);

	/**
	 * 
	 * 功能描述：根据课程ID获取与课程关联的岗位信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-14 下午1:37:22
	 *         </p>
	 * 
	 * @param courseId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<CourseJob> jobInfoByCid(int courseId);

	/**
	 * 
	 * 功能描述：根据课程对象查询课程基本信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-18 上午10:38:59
	 *         </p>
	 * 
	 * @param course
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<Course> courseInfo(Course course);

	/**
	 * 
	 * 功能描述：保存或修改课程信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-18 下午1:59:37
	 *         </p>
	 * 
	 * @param course
	 * @param resList
	 * @param jobList
	 * @param flag
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public Course saveOrUpadteCourse(Course course, List<ResourceVo> resList,
			List<CourseJob> jobList, boolean flag);

	/**
	 * 
	 * 功能描述： 更新课程章节信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-22 上午8:50:42
	 *         </p>
	 * 
	 * @param courseDetail
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void updateSection(CourseDetail courseDetail);

	/**
	 * 
	 * 功能描述：增加课件辅助资源
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-22 上午9:01:08
	 *         </p>
	 * 
	 * @param resourceVo
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public ResourceVo addResource(ResourceVo resourceVo);

	/**
	 * 
	 * 功能描述：批量增加课程资源
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-22 上午9:03:44
	 *         </p>
	 * 
	 * @param resourceVo
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public ResourceVo addBindCourRescource(ResourceVo resourceVo);

	/**
	 * 功能描述：获取未审核课程的数量.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月22日 下午2:29:20
	 *         </p>
	 * @param checkStatus课程的审核状态
	 * @return 课程数量.
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int getCourseUnauditedNum(int checkStatus) throws ServiceException;

	/**
	 * 功能描述：批量删除课程的章节.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月23日 下午3:28:55
	 *         </p>
	 * 
	 * @param sectionIds
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public void batchDeleteSectionById(List<Integer> sectionIds) throws ServiceException;

	/**
	 * 功能描述：批量更新章节内容.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月23日 下午5:46:39
	 *         </p>
	 * 
	 * @param sectionList
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public void batchUpdateSection(List<CourseDetail> sectionList) throws ServiceException;

	/**
	 * 功能描述：批量保存新的章节.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月24日 下午3:01:46</p>
	 *
	 * @param sectionList
	 * @throws ServiceException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void batchSaveSection(List<CourseDetail> sectionList) throws ServiceException;

	/**
	 * 功能描述：课程修改第二步.修改课程大纲.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月24日 上午10:03:48
	 *         </p>
	 * 
	 * @param paramters
	 * @throws ServiceException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public void updatePublishedCourseStep2_outlineService(int courseId,
			List<Integer> deleteCourseDetailIds, List<CourseDetail> updateCourseDetails,
			List<CourseDetail> saveCourseDetails) throws ServiceException;
	
	/**
	 * 功能描述：统计小节下面是否有内容(核心知识点,视频,辅助资源)
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月24日 下午5:41:53</p>
	 *
	 * @param courseId
	 * @param sectionId
	 * @return
	 * @throws ServiceException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public boolean hasResourceInPartOfSection(int courseId,int sectionId)throws ServiceException;

}
