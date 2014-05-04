/**
 * RegisterService.java	  V1.0   2014年1月6日 下午2:06:06
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.user;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.user.UserVo;

public interface UserService {

	
	/**
	 * 
	 * 功能描述：用户登录
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：20142014-4-22 上午9:32:09</p>
	 *
	 * @param userVo (账号密码为必填项)
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	 public UserVo  userLogin(UserVo userVo) throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据用户id查询用户
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-13 上午11:02:34</p>
	 *
	 * @param courseId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public UserVo getUserById(int userId) throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：注册用户
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月6日 下午2:13:16</p>
	 *
	 * @param user
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public UserVo registerUser(UserVo user)throws BaseException;
	/**
	 * 
	 * 功能描述：保存注册用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-10 上午10:08:45</p>
	 *
	 * @param user
	 * @param strEducation
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public UserVo savaUser(UserVo user)throws BaseException;
	
    /**
     * 
     * 功能描述：检查登录用户信息
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-9 下午4:35:12</p>
     *
     * @param user
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public UserVo getUserByVo(UserVo user)throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：通过用户名称获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-10 下午6:36:08</p>
	 *
	 * @param userName
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserByName(String userName)throws BaseException;
	

	
	/**
	 * 
	 * 功能描述：获取用户信息根据用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-14 下午2:21:35</p>
	 *
	 * @param userVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UserVo> getUserByUserVo(UserVo userVo)throws BaseException;	
	
	
	/**
	 * 
	 * 功能描述：更新用户的Code
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午4:13:36</p>
	 *
	 * @param strCode
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUserPwdCode(String strCode,String strEmail)throws BaseException;	
	
	/**
	 * 
	 * 功能描述：更新用户的密码
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午4:13:36</p>
	 *
	 * @param strCode
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUserPwd(String strPwd,String strEmail)throws BaseException;	
	
	/**
	 * 
	 * 功能描述：更新用户信息到数据库中
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-22 下午1:56:16</p>
	 *
	 * @param userVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUser(UserVo userVo)throws BaseException;	
	
	/**
	 * 
	 * 功能描述：更新教师图片信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-23 下午4:22:00</p>
	 *
	 * @param userVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateUser_HeadPic(UserVo userVo)throws BaseException;	
	/**
	 * 
	 * 功能描述：获取笔记列表
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-3-10 下午3:08:38</p>
	 *
	 * @param courseId
	 * @param sectionId
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<NoteVo> getNote(int courseId,int sectionId,int userId) throws BaseException;
	
}
