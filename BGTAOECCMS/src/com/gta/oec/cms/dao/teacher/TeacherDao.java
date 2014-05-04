/**
 * TeacherDao.java	  V1.0   2014年4月2日 下午9:06:04
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.teacher;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.teacher.TeacherVo;

/**
 * 功能描述：教师teacher dao层.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface TeacherDao extends SqlMapper<TeacherVo> {

	/**
	 * 功能描述：通过用户id获取教师信息.包括用户信息,和学校信息.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月3日 下午12:27:44</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public TeacherVo getTeacherByUserId(@Param(value = "userId") int userId);

}
