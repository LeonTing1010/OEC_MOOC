/**
 * student.java	  V1.0   2014-1-10 上午10:21:15
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.teacher;

import java.util.List;

import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.user.UserVo;

public interface TeacherDao {
       
	/**
	 * 根据用户id查找对于的老师
	 * 功能描述：
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-13 下午12:01:25</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public TeacherVo getTeacherByUserId(int userId);
	/**
	 * 功能描述：根据行业查询分页.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-20 上午11:33:38</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TeacherVo> getTeacherListByProfessionId(int proId,int pageNo,int pageSize);
	
	/**
	 * 功能描述：行业类别的教师总数.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-23 下午2:13:30</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countTeacherByProfessionId(int proId);
	
	/**
	 * 功能描述：所有教师的总数.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-23 下午6:03:30</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countTeacher();
	
	
	/**
	 * 功能描述：根据岗位群id获取教师分页列表.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-20 下午12:00:29</p>
	 *
	 * @param jobGroupId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TeacherVo> getTeacherListByjobGroupId(int jobGroupId, int pageNo,int pageSize);
	
	/**
	 * 功能描述：岗位群类别的教师总数.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-23 下午2:14:39</p>
	 *
	 * @param jobGroupId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countTeacherListByjobGroupId(int jobGroupId);
	
	/**
	 * 功能描述：获取分页的老师列表.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-20 下午12:21:41</p>
	 *
	 * @param num
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TeacherVo> getTeacherList(int pageNo,int pageSize);
	
	
	/**
	 * 功能描述：根据教师id获取教师.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-21 上午10:15:46</p>
	 *
	 * @param teacherId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public TeacherVo getTeacherVoById(int teacherId);
	
	
	
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
	public int updateTeacherMode(TeacherVo teacher);
	
	
}
