/**
 * RegisterDao.java	  V1.0   2014年1月6日 上午11:37:38
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.user;

import java.util.ArrayList;
import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.user.UserVo;

public interface UserDao {
 /**
  * 
  * 功能描述：保存用户公用信息
  *
  * @author  Miaoj
  * <p>创建日期 ：2014-1-10 上午10:18:13</p>
  *
  * @param user
  *
  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  */
	public UserVo addUser(UserVo user);
    	
	public int updateUser(UserVo user);
	
	/**
	 * 根据用户id获取用户
	 */
	public UserVo getUserById(Integer id);
	
	/**
	 * 
	 * 功能描述：根据多个用户id获取用户
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-10 上午11:13:52</p>
	 *
	 * @param ids
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserListByIds(List list);
	
	/**
	 * 
	 * 功能描述：获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-9 下午5:26:51</p>
	 *
	 * @param user
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserInfo(UserVo user);
	/**
	 * 
	 * 功能描述：根据用户mode获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-14 下午2:00:19</p>
	 *
	 * @param user
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserByUserVo(UserVo user);
	/**
	 * 
	 * 功能描述：通过用户名称获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-10 下午6:27:56</p>
	 *
	 * @param strUserName
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserByName(String strUserName);
	
	/**
	 * 
	 * 功能描述：更新用户修改的验证码
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午4:03:29</p>
	 *
	 * @param strUserEmail
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUserPwdCode(String strCode,String strEmail);
	
	
	/**
	 * 
	 * 功能描述：更新用户修改的验证码
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午4:03:29</p>
	 *
	 * @param strUserEmail
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUserPwd(String strPwd,String strEmail);
	
	/**
	 * 
	 * 功能描述：取得推荐的老师列表
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-15 </p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getRecommendTeacherList(int limitNum);

	/**
	 * 
	 * 功能描述：根据岗位群ID，取得相应的推荐老师
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-15
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<UserVo> getRecommendTeacherList(int jobGroupId,int limitNum);
	
	
	/**
	 * 
	 * 功能描述：取得回答活跃老师列表
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-15 </p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getTeacherAnswerActiveList();
	
   /**
    * 
    * 功能描述：更新用户信息
    *
    * @author  Miaoj
    * <p>创建日期 ：2014-1-22 下午2:01:39</p>
    *
    * @param user
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
	public int updateUserMode(UserVo user);
	
	/**
	 * 
	 * 功能描述：更新用户的图片信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-23 下午4:28:28</p>
	 *
	 * @param strPath
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUser_HeadPic(UserVo user);

	/**
	 * 功能描述:老师名字关键字搜索老师身份的用户.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年2月27日 下午5:21:27</p>
	 *
	 * @param keyString
	 * @param type
	 * @param num
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getTeacherListByKeyword(String keyString,int num);
    /**
     * 
     * 功能描述：获取学习该门课程的用户列表
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-3-19 上午9:53:45</p>
     *
     * @param courId
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public List<UserVo> getCourseUser(Integer courseId,int pageNo,int pageSize);
}
