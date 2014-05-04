/**
 * CourseDao.java	  V1.0   2013-12-27 ����10:38:05
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.course;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.qacenter.QuestionVo;
/**
 * 
 * 功能描述：课程DAO层
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface CourseDao {
	/**
	 * 
	 * 功能描述：插入课程信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-6 下午4:28:38</p>
	 *
	 * @param courseVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
   public CourseVo insert(CourseVo courseVo);
   /**
    * 
    * 功能描述：获取课程列表(与岗位关联)
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-7 下午1:14:38</p>
    *
    * @param courseVo
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> getCourseListByJob(CourseVo coursevo,int jobID,int page,int pageSize);
   public int getCourseListCountByJob(CourseVo coursevo,int jobID);
   
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
	public List<CourseVo> getCourseListByProId(SearchVo searchVo,int proId,int page,int pageSize);
	
	
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
	public int getCourseListByProIdCount(SearchVo searchVo,int proId);
	
   /**
    * 
    * 功能描述：获取课程列表
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-10 上午10:41:01</p>
    *
    * @param coursevo
    * @param page
    * @param pageSize
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> getCourseList(CourseVo coursevo,int pageNo,int pageSize);
   

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
	public List<CourseVo> getSearchCourseList(SearchVo searchVo,int page,int pageSize);
  
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
	public int getSearchCourseListCount(SearchVo searchVo);
	
	
	
	/**
    * 
    * 功能描述：获取分页总条数
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-20 下午4:01:30</p>
    *
    * @param coursevo
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int getCourseListCount(CourseVo coursevo);
   
   /**
    * 
    * 功能描述： 获取课程信息(带学校用户信息)
    *
    * @author  biyun.huang
    * <p>创建日期 ：2014年1月20日 下午3:02:23</p>
    *
    * @param courseId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public CourseVo getCourseById(int courseId);

   
   /**
    * 
    * 功能描述：获取课程列表
    *
    * @author  biyun.huang
    * <p>创建日期 ：2014年1月15日 下午5:15:01</p>
    *
    * @param courseVo
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> getCourseList(CourseVo courseVo);


   
   /**
    * 
    * 功能描述：查询各岗位下的课程
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-1-15 下午3:23:37</p>
    *
    * @param jobId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> getCollectInfo(int jobId,List list,int page,int pageSize);

   /**
    * 
    * 功能描述：课程收藏
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-1-15 下午6:48:16</p>
    *
    * @param jobId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> courseCollectList(int userId,int collType,int pageNo,int pageSize);
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
   public  CourseVo update(CourseVo courseVo);
   
   /**
    * 
    * 功能描述：查询用户课程信息
    *
    * @author  biyun.huang
    * <p>创建日期 ：2014年1月21日 上午11:24:18</p>
    *
    * @param courseVo
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<CourseVo> getUserCourList(CourseVo courseVo);
   
   /**
    * 
    * 功能描述：岗位收藏，岗位信息条数
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-1-22 下午12:19:15</p>
    *
    * @param list
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int getCourseCount(List list);
   
   /**
    * 
    * 功能描述：课程收藏，课程列表条数
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-1-22 下午1:39:25</p>
    *
    * @param list
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int getCourseInfoCount(int userId,int collType);

   /**
    * 
    * 功能描述：更新课时
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-2-11 下午5:13:14</p>
    *
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void updateCourseWeek(Integer courseId);


   
   /**
    * 
    * 功能描述：查询某门课程学习人数
    *
    * @author  biyun.huang
    * <p>创建日期 ：2014年2月12日 下午1:36:00</p>
    *
    * @param courseVo
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int selectCourStudyCount(CourseVo courseVo);

   
   /**
    * 
    * 功能描述：课程收藏，课程信息删除
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-2-11 下午7:27:25</p>
    *
    * @param courseId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void delCourInfo(Integer courseId);
   
   /**
    * 
    * 功能描述：课程收藏，用户课程信息删除
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-2-12 下午2:16:24</p>
    *
    * @param courseId
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void delUserCour(Integer courseId);
   
   /**
    * 
    * 功能描述：收藏本课程
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-2-13 下午7:23:53</p>
    *
    * @param courseId
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void collectCour(Integer courseId,Integer collType,Integer userId);

   /**
    * 
    * 功能描述：查询该课程是否收藏
    *
    * @author  yangyang.ou
    * <p>创建日期 ：2014-2-14 上午10:18:21</p>
    *
    * @param courseId
    * @param collType
    * @param userId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int checkCollCourse(Integer courseId,Integer collType,Integer userId);
   
	/**
	 * 功能描述：搜索课程.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年2月27日 下午5:38:15</p>
	 *
	 * @param keyString
	 * @param num
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseListByKeyword(String keyString,int num);
	
	
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
	public List<CourseVo> getTradeCourse(int tradeId,int courseState,int isIndexPush,int pageNo,int pageSize);

	/**
	 * 功能描述：某个用户的所有课程.分页
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月4日 上午10:33:15</p>
	 *
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseListByUserId(int userId,int pageNo,int pageSize);
	/**
	 * 功能描述：统计总数.某个用户的所有课程.分页
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月4日 上午10:33:15</p>
	 *
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countCourseListByUserId(int userId);
	
	/**
	 * 
	 * 功能描述：我的课程，推荐课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-5 下午3:56:35</p>
	 *
	 * @param courseId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseRecommendList();
	
	/**
	 * 
	 * 功能描述：获取考试相关课程列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月12日 下午3:47:07</p>
	 *
	 * @param coursevo
	 * @param examType
	 * @param examState
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getExamCourseList(CourseVo coursevo,int ExamType,int examState,int pageNo,int pageSize);
	
	/**
	 * 
	 * 功能描述：获取考试相关课程列表的数量
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月12日 下午4:01:25</p>
	 *
	 * @param coursevo
	 * @param examType
	 * @param examState
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getExamCourseListCount(CourseVo coursevo,int examType,int examState);

	/**
	 * 
	 * 功能描述：课程信息，根据行业id查询课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-10 下午5:37:35</p>
	 *
	 * @param proId
	 * @param page
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseListByPro(int proId, int page, int pageSize);
	
	/**
	 * 
	 * 功能描述：课程介绍页，根据岗位群id获得课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-11 下午2:43:14</p>
	 *
	 * @param jobPid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> getCourseListByJobGroupId(CourseVo courseVo,int jobPid,int pageNo,int pageSize);
	public int getCourseListCountByJobGroupId(CourseVo courseVo,int jobPid);
	
	/**
	 * 
	 * 功能描述：功能描述：课程介绍页，根据行业id查询课程信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-3-11 下午6:25:57</p>
	 *
	 * @param courseVo
	 * @param proId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseVo> queryCourseListByProId(CourseVo courseVo,int proId,int pageNo,int pageSize);
	
	
	public int queryCourseListCountByProId(CourseVo courseVo,int proId);
	
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
	 */
	public Object updateByBrowseTimes(Integer courId, long courseAttention);
}
