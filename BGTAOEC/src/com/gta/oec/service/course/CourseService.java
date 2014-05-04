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

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.resource.ResourceVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：课程
 * 
 * @author bingzhong.qin
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface CourseService
 {

	/**
	 * 
	 * 功能描述：根据岗位查询课程列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月12日 下午6:41:34</p>
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
	public PageModel getCourseListByJob(CourseVo coursevo,int jobID,int page,int pageSize)
			throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：根据行业搜索课程列表
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
	public PageModel getCourseListByProId(SearchVo searchVo,int proId,int page,int pageSize)
			throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：获取课程列表(分页，带用户学校信息，不带章节信息)
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-10 上午10:43:39</p>
	 *
	 * @param coursevo
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getCourseList(CourseVo coursevo,int page,int pageSize) throws BaseException;
	
	
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
	public PageModel getSearchCourseList(SearchVo searchVo,int page,int pageSize) throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：获取课程列表(只携带课程信息)
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-18 上午9:35:18</p>
	 *
	 * @param coursevo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseList(CourseVo coursevo) throws BaseException;
	
	/**
	 * 
	 * 功能描述：获取课程信息(分页-不带章节信息)
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-18 上午9:35:18</p>
	 *
	 * @param coursevo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getCourseTab(CourseVo coursevo,Integer pageNo,Integer pageSize) throws BaseException;

	/**
	 * 
	 * 功能描述：获取课程信息(分页-带章节  考试/作业/练习 信息)
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月24日 下午1:45:01</p>
	 *
	 * @param courseVo
	 * @param pageNo     页码
	 * @param pageSize   每页条数
	 * @param examType   考试类型
	 * @param paperState 批阅状态
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getCourseTab(CourseVo courseVo,Integer pageNo,Integer pageSize,Integer examType,Integer paperState) throws BaseException;
	
	/**
	 * 
	 * 功能描述：获取课程信息（带章节信息）
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-9 下午6:29:09</p>
	 *
	 * @param courseId
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public CourseVo getCourseById(int courseId) 
			throws BaseException;

	/**
	 * 
	 * 功能描述：获取用户课程信息（带章节详细学习信息）
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-9 下午2:22:32
	 *         </p>
	 * 
	 * @param courseId 课程ID
	 * @param userId   用户ID
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public CourseVo getUserCourseLearnDetail(int courseId, int userId)
			throws BaseException;
	
	
	
	/**
	 * 
	 * 功能描述：获取学习该门课程的用户
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-10 下午2:56:53</p>
	 *
	 * @param courId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getCourseUser(Integer courseId,int pageNo,int pageSize) throws BaseException;

	
	 /**
	   * 
	   * 功能描述：根据多个用户id获取用户
	   *
	   * @author  jin.zhang
	   * <p>创建日期 ：2014-1-10 上午11:18:12</p>
	   *
	   * @param ids
	   * @return
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public List<UserVo> getUserListByIds(List idsList) throws BaseException;
	  /**
	   * 
	   * 功能描述：获取课程信息（不包含章节信息）
	   *
	   * @author  bingzhong.qin
	   * <p>创建日期 ：2014-1-13 下午3:25:55</p>
	   *
	   * @param courseId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public CourseVo getCourseInfoById(Integer courseId) throws BaseException;
	  
	  
	 
	  /**
	   * 
	   * 功能描述：获取某个用户课程信息（带章节与笔记）
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月13日 下午6:37:47</p>
	   *
	   * @param courseId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public CourseVo getCourseInfo(int courseId)throws BaseException;
	  /**
	   * 
	   * 功能描述：获取用户课程的笔记信息
	   *
	   * @author  bingzhong.qin
	   * <p>创建日期 ：2014-2-20 上午10:07:05</p>
	   *
	   * @param courseId
	   * @param userId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public CourseVo getUserCourseNotes(int courseId,int userId)throws BaseException;
	  
	  /**
		  * 
		  * 功能描述：获取多个课程下的课程信息（带章节与笔记）
		  *
		  * @author  jin.zhang
		  * <p>创建日期 ：2014-1-15 上午11:00:15</p>
		  *
		  * @return
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
	  public PageModel getCourseInfoList(int userId,int page,int pageSize)throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：//获取课程信息（带用户ID与答案）
	   *
	   * @author  jin.zhang
	   * <p>创建日期 ：2014-1-15 下午1:39:41</p>
	   *
	   * @param courseId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public CourseVo getCourseQuesInfo(int userId,int courseId,int quesType) throws BaseException;
	  
	
	  /**
	   * 
	   * 功能描述：//获取多个课程下的问题和答案   by zhangjin
	   *
	   * @author  jin.zhang
	   * <p>创建日期 ：2014-1-15 下午7:59:08</p>
	   *
	   * @param userId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public List<CourseVo> getCourseQuesInfoList(int userId,int quesType,int page,int pageSize) throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：增加课程章节笔记
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月14日 下午4:40:14</p>
	   *
	   * @param courseVo
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public NoteVo insertNote(NoteVo noteVo) throws BaseException;  
	  
	  /**
	   * 
	   * 功能描述：课程收藏
	   *
	   * @author  yangyang.ou
	   * <p>创建日期 ：2014-1-15 下午6:45:05</p>
	   *
	   * @param userId
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public PageModel courseCollectList(int userId,int courType,int pageNo,int pageSize)throws BaseException;

	  
	  /**
	   * 
	   * 功能描述：根据课程章节ID获取辅助资源列表
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月15日 下午12:16:46</p>
	   *
	   * @param courseId
	   * @param secId
	   * @return
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public List<ResourceVo> getResourceList(ResourceVo resourceVo) throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：通过课程名称查找课程
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月15日 下午5:22:04</p>
	   *
	   * @param courseVo
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public List<CourseVo> getCourseListByName(CourseVo courseVo) throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：增加课程辅助资源
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月16日 上午9:56:17</p>
	   *
	   * @param resourceVo
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public ResourceVo addResource(ResourceVo resourceVo) throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：增加辅助资源与的课程关联
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月16日 上午9:59:45</p>
	   *
	   * @param resourceVo
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public ResourceVo addBindCourRescource(ResourceVo resourceVo) throws BaseException;
	  
	  /**
	   * 
	   * 功能描述：增加课程和岗位的关联
	   *
	   * @author  biyun.huang
	   * <p>创建日期 ：2014年1月16日 下午3:18:37</p>
	   *
	   * @param courseVo
	   * @return
	   * @throws BaseException
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
	  public CourseJobVo addBindCourseJob(CourseJobVo courseJobVo) throws BaseException;

	  
		/**
		 * 
		 * 功能描述：获取单个课程下和对应的考试类型（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-1-16 下午6:31:26</p>
		 *
		 * @param courseId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public CourseVo getCourseExamInfo(int userId,int courseId,int examType) throws BaseException;
		
		/**
		 * 
		 * 功能描述：获取多个课程下和对应的考试类型（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-1-16 下午7:14:15</p>
		 *
		 * @param userId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		 public PageModel getCourseExamInfoList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException;
		 
		 
		/**
		 * 
		 * 功能描述：更新课程章节
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年1月17日 上午10:46:43</p>
		 *
		 * @param sectionVO
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public SectionVO updateSection(SectionVO sectionVO) throws BaseException;
		public void betachUpdateSection(List<SectionVO> list) throws BaseException;
		/**
		 * 
		 * 功能描述：删除章/节
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-2-11 下午2:02:26</p>
		 *
		 * @param id
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public void deleteSectionById(Integer sectionId,Integer courseId) throws BaseException;
		public void betachDeleteSectionById(List<Integer> list,Integer courseId) throws BaseException;
		/**
		 * 
		 * 功能描述：删除课程和资源的关联(是否同时删除资源)
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年1月17日 下午5:15:46</p>
		 *
		 * @param resourceVo
		 * @param flag
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public ResourceVo deleteBindCourRescource(ResourceVo resourceVo,boolean flag)throws BaseException;

		/**
		 * 
		 * 功能描述：保存或修改课程信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-3-6 下午4:21:21</p>
		 *
		 * @param courseVo 课程基本信息
		 * @param resList  资源列表
		 * @param jobList  关联岗位列表
		 * @param flag     save:true update:false
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public CourseVo createOrUpadteCourse(CourseVo courseVo,List<ResourceVo> resList,List<CourseJobVo> jobList,boolean flag)throws BaseException;
		/**
		 * 
		 * 功能描述：批量添加资源信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-18 上午9:19:56</p>
		 *
		 * @param list
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<ResourceVo> addBatchResource(List<ResourceVo> list) throws BaseException;
		/**
		 * 
		 * 功能描述：批量绑定资源
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-18 上午9:29:00</p>
		 *
		 * @param list
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public void addBatchReCourRescource(List<ResourceVo> list)throws BaseException;
		/**
		 * 
		 * 功能描述：批量绑定课程与岗位
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-18 上午9:30:18</p>
		 *
		 * @param list
	     * @param delete  是否删除原来已绑定的岗位信息
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public void beatchBindCourseJob(List<CourseJobVo> list,boolean delete) throws BaseException;
		/**
		 * 
		 * 功能描述：批量保存章/节信息
		 *
		 * @author  bingzhong.qin
		 * <p>创建日期 ：2014-1-18 上午9:32:26</p>
		 *
		 * @param list
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public void saveBatchSection(List<SectionVO> list) throws BaseException;
		
		
		   /**
		    * 
		    * 功能描述：修改课程
		    *
		    * @author  bingzhong.qin
		    * <p>创建日期 ：2014-1-18 下午2:00:40</p>
		    *
		    * @param courseVo
		    * @return
		    *
		    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		    */
		   public  CourseVo updateCourse(CourseVo courseVo) throws BaseException;
		 
		 /**
		  * 
		  * 功能描述：查询用户课程信息(带学校信息)
		  *
		  * @author  biyun.huang
		  * <p>创建日期 ：2014年1月21日 上午11:26:36</p>
		  *
		  * @param courseVo
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public List<CourseVo> getUserCourList(CourseVo courseVo)throws BaseException;

		 /**
		  * 
		  * 功能描述：增加用户的课程（用户购买成功之后添加）
		  *
		  * @author  bingzhong.qin
		  * <p>创建日期 ：2014-1-23 下午4:56:43</p>
		  *
		  * @param courseId
		  * @param userId
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public void addUserCourse(Integer courseId,Integer userId) throws BaseException;
		 /**
		  * 
		  * 功能描述：校验用户是否购买该课程
		  *
		  * @author  bingzhong.qin
		  * <p>创建日期 ：2014-4-2 下午7:18:13</p>
		  *
		  * @param courseId
		  * @param userId
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public boolean checkUserCourse(Integer courseId,Integer userId) throws BaseException;
		 
		 /**
		  * 
		  * 功能描述：课程收藏，课程信息条数
		  *
		  * @author  yangyang.ou
		  * <p>创建日期 ：2014-1-22 下午1:34:15</p>
		  *
		  * @param list
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public int getCourseListCount(int userId,int collType) throws BaseException;
		 
		 /**
		  * 
		  * 功能描述：获取获取课程信息(带 练习/考试/作业 信息)
		  *
		  * @author  biyun.huang
		  * <p>创建日期 ：2014年1月22日 下午4:46:14</p>
		  *
		  * @param courseId
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public CourseVo getCourseVoInfo(int courseId,ExamVo examVo)throws BaseException;
		
		 /**
		  * 
		  * 功能描述：查询某门课程的学习人数
		  *
		  * @author  biyun.huang
		  * <p>创建日期 ：2014年2月12日 下午1:32:24</p>
		  *
		  * @param courseVo
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public int getCourStudyCount(CourseVo courseVo) throws BaseException;
		 /**
		  * 
		  * 功能描述：课程收藏，课程删除
		  *
		  * @author  yangyang.ou
		  * <p>创建日期 ：2014-2-11 下午7:18:31</p>
		  *
		  * @param courseId
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public void delCourInfo(Integer courseId)throws BaseException;
		 /**
		  * 
		  * 功能描述：获取章/节信息
		  *
		  * @author  bingzhong.qin
		  * <p>创建日期 ：2014-2-11 下午7:06:35</p>
		  *
		  * @param sectionId
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public SectionVO getSectionById(Integer sectionId)throws BaseException;
		 

		  /**
		    * 
		    * 功能描述：获取未编辑核心知识点或未上传视频的节的数量
		    *
		    * @author  bingzhong.qin
		    * <p>创建日期 ：2014-2-12 下午1:32:11</p>
		    *
		    * @return
		    *
		    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		    */
		   public int getSectionNotCompleteCount(Integer courseId)throws BaseException;


		 /**
		  * 
		  * 功能描述：课程收藏，用户课程信息删除
		  *
		  * @author  yangyang.ou
		  * <p>创建日期 ：2014-2-12 下午2:14:09</p>
		  *
		  * @param courseId
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public void delUserCour(Integer courseId)throws BaseException;
		 
		 /**
		  * 
		  * 功能描述：收藏本课程
		  *
		  * @author  yangyang.ou
		  * <p>创建日期 ：2014-2-13 下午7:20:06</p>
		  *
		  * @param courseId
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public void collectCourse(Integer courseId,Integer collType,Integer userId)throws BaseException;

		 /**
		  * 
		  * 功能描述：查询该课程是否收藏
		  *
		  * @author  yangyang.ou
		  * <p>创建日期 ：2014-2-14 上午10:11:20</p>
		  *
		  * @param courseId
		  * @param collType
		  * @param userId
		  * @return
		  * @throws BaseException
		  *
		  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		  */
		 public int checkCollCourse(Integer courseId,Integer collType,Integer userId)throws BaseException;
		 
		/**
		 * 
		 * 功能描述：根据课程id查询章节列表
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-2-17 上午8:51:45</p>
		 *
		 * @param courseId
		 * @param secPid
		 * @param secId
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		 public List<SectionVO> getSectionList(int courseId,int secPid,int secId)throws BaseException;
		 
		 
		/**
		 * 
		 * 功能描述：根据课程id用户id查询课程
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-1-9 下午8:16:17</p>
		 *
		 * @param courseId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		 public PageModel getUserCourseList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException;
		 
		 
		 
		 /**
		 * 功能描述：关键字搜索课程.
		 *
		 * @author  dongs
		 * <p>创建日期 ：2014年2月27日 下午5:36:03</p>
		 *
		 * @param keyString
		 * @param num
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseVo> getCourseListByKeyword(String keyString ,int num)throws BaseException;
        /**
         * 
         * 功能描述：获取行业下的课程
         *
         * @author  bingzhong.qin
         * <p>创建日期 ：2014-2-28 下午3:01:11</p>
         *
         * @param tradeId  行业id
         * @param courseState  课程状态
         * @param isIndexPush  是否推荐首页 ：0-不推荐 1-推荐
         * @return
         * @throws BaseException
         *
         * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
         */
		public List<CourseVo> getTradeCourse(int tradeId,int courseState,int isIndexPush,int pageNo,int pageSize)throws BaseException;

		/**
		 * 功能描述：获取某个用户的课程分页.
		 *
		 * @author  dongs
		 * <p>创建日期 ：2014年3月4日 上午10:36:38</p>
		 *
		 * @param userId
		 * @param pageSize
		 * @param pageNo
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel getCoursePageModelByUserId(int userId,int pageSize,int pageNo)throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-3-4 下午4:11:45</p>
		 *
		 * @param sectionId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public SectionVO getSectionById(int sectionId)throws BaseException;
		
		/**
		 * 
		 * 功能描述：我的课程，推荐课程
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-3-5 下午4:20:18</p>
		 *
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseVo> getCourseRecommendList()throws BaseException;
		
		/**
		 * 
		 * 功能描述：课程信息，根据行业id查询课程信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-3-10 下午5:28:43</p>
		 *
		 * @param proId
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseVo> getCourseListByPro(int proId, int page, int pageSize)throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据用户的课程id查询考试数
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-3-10 下午2:57:22</p>
		 *
		 * @param userId
		 * @param list
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int getCourseExamCount(int userId,int examType)throws BaseException;
		
		
		/**
		 * 
		 * 功能描述：新收到的考试
		 *
		 * @author  jin.zhang
		 * <p>创建日期 ：2014-3-12 上午10:45:39</p>
		 *
		 * @param userId
		 * @param pageNo
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List getExamNoticeList(int userId,int examType) throws BaseException;
 
		/**
		 * 
		 * 功能描述：根据考试类型，获取存在考试的相关课程
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年3月12日 下午3:50:09</p>
		 *
		 * @param coursevo
		 * @param examType
		 * @param examState
		 * @param pageNo
		 * @param pageSize
		 * @throws BaseException
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public List<CourseVo> getExamCourList(CourseVo coursevo, int examType,int examState,int pageNo, int pageSize)throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据考试类型和批阅状态，获取相关课程的数量
		 *
		 * @author  biyun.huang
		 * <p>创建日期 ：2014年3月12日 下午4:29:43</p>
		 *
		 * @param coursevo
		 * @param examType
		 * @param examState
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int getExamCourListCount(CourseVo coursevo, int examType,int examState)throws BaseException;
		/**
		 * 
		 * 功能描述：课程介绍页，根据岗位群id查询课程信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-3-11 下午2:39:31</p>
		 *
		 * @param jobId
		 * @param pageNo
		 * @param pageSize
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel getCourListByJobGroupId(CourseVo courseVo,int jobPid,int pageNo,int pageSize)throws BaseException;
		
		/**
		 * 
		 * 功能描述：根据行业id查询课程信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-3-11 下午6:23:44</p>
		 *
		 * @param courseVo
		 * @param proId
		 * @param pageNo
		 * @param pageSize
		 * @return
		 * @throws BaseException
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public PageModel queryCourseListByProId(CourseVo courseVo,int proId,int pageNo,int pageSize)throws BaseException;


		/**
		 * 功能描述：课程被浏览时   更改课程浏览次数
		 *
		 * @author  li.liu
		 * <p>创建日期 ：2014-4-24 下午2:43:36</p>
		 *
		 * @param courId
		 * @param courseAttention
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 * @throws SystemDBException 
		 */
		public void updateByBrowseTimes(Integer courId, long courseAttention) throws SystemDBException;
 }