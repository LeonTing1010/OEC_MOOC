/**
 * TeacherShineDao.java	  V1.0   2014-2-10 下午4:07:40
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.teacher;

import com.gta.oec.vo.teacher.TeacherShineVo;
import java.util.List;
public interface TeacherShineDao {
	/**
	 * 
	 * 功能描述：通过用户ID获取用户信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-2-10 下午4:45:20</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TeacherShineVo> getTeacherShineByUserId(int userId);
	
	/**
	 * 
	 * 功能描述：删除教师删除的信息
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-2-11 下午3:39:24</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteTeacherShineInfor(int userId);
	
	
	/**
	 * 
	 * 功能描述：批量添加教师的领域
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-1-16 下午6:45:21</p>
	 *
	 * @param noteVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int addTeacherShine(List<TeacherShineVo> teacherShineVo);
}
