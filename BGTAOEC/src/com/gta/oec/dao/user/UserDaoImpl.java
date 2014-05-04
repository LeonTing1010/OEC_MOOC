/**
 * RegisterDaoImpl.java	  V1.0   2014年1月6日 上午11:38:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.user.UserVo;
@Repository
public class UserDaoImpl extends BaseDao<UserVo> implements UserDao{

	 
	@Override
	public UserVo addUser(UserVo user)
	{
		super.insert(generateStatement("insertUser"), user);
		return user;
	}
	
	@Override
	public int updateUser(UserVo user)
	{
		return super.insert(generateStatement("updateUser"), user);
	}
	
	
	/**
	 * 根据用户id获取用户
	 */
	@Override
	public UserVo getUserById(Integer id) {
		return (UserVo) super.selectOne(generateStatement("getUserById"),id);
	}
	
	@Override
	public List<UserVo> getUserListByIds(List idsList) {
		return super.findList(generateStatement("getUserListByIds"),idsList);
	}
	
    /**
     * 根据用户用户名和密码获取用户列表
     */
	@Override
	public List<UserVo> getUserInfo(UserVo user) {
		return super.findList(generateStatement("getUserInfo"), user);
	}
    
    /**
     * 根据用户名称获取用户列表
     */
	@Override	
	public List<UserVo> getUserByName(String strUserName)
	{
		return super.findList(generateStatement("getUserlistByName"),strUserName);	
	}
	
	/**
	 * Function：根据用户对象获取用户信息
	 * Add   BY: 缪佳 2014-01-14
	 */
	@Override
	public List<UserVo> getUserByUserVo(UserVo user)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uservo", user);
		return super.findList(generateStatement("getUserlistByUserVo"), map);
	}
	/**
	 * Funciton:更新用户验证码
	 * Add   By:缪佳 2014-01-16
	 */
	@Override
	public int updateUserPwdCode(String strCode,String strEmail){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", strCode);
		map.put("email", strEmail);		
		return super.update(generateStatement("updateUserPwdCode"),map);
	}
	
	/**
	 * Funciton:更新用户密码
	 * Add   By:缪佳 2014-01-16
	 */
	@Override
	public int updateUserPwd(String strPwd,String strEmail){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pwd", strPwd);
		map.put("email", strEmail);		
		return super.update(generateStatement("updateUserPwd"),map);
	}
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
	public List<UserVo> getRecommendTeacherList(int limitNum){
		return super.findList(generateStatement("getRecommendTeacherList"),limitNum);
	}
	

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
	public List<UserVo> getRecommendTeacherList(int jobGroupId,int limitNum){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobGroupId", jobGroupId);
		map.put("limitNum",limitNum);
		return super.findList(generateStatement("getRecommendTeacherListByJobId"),map);
	}
	
	
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
	public List<UserVo> getTeacherAnswerActiveList(){
		return super.findList(generateStatement("getTeacherAnswerActiveList"));
	}
	
	/**
	 * 功能描述：更新用户信息
	 *
	 * @author  缪佳
	 * <p>创建日期 ：2014-1-22 </p>
	 */
	@Override
	public int updateUserMode(UserVo user)
	{
		return super.update(generateStatement("updateUser"), user);
	}
	
	/**
	 * 功能描述：更新用户信息
	 *
	 * @author  缪佳
	 * <p>创建日期 ：2014-1-22 </p>
	 */
	@Override
	public int updateUser_HeadPic(UserVo user)
	{
		return super.update(generateStatement("updateUser_HeadPic"), user);
	}

	/* (non-Javadoc)
	 * @see com.gta.oec.dao.user.UserDao#getTeacherListByKeyword(java.lang.String, int)
	 */
	@Override
	public List<UserVo> getTeacherListByKeyword(String keyString, int num) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", keyString);
		parameter.put("limit", num);
		return  findList(generateStatement("getUserOfTeacherListByKeyword"), parameter);
	}

	@Override
	public List<UserVo> getCourseUser(Integer courseId,int pageNo,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseId", courseId);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getCourseUser"),map);
	}
}
