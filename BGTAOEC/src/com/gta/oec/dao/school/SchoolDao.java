/**
 * student.java	  V1.0   2014-1-10 上午10:21:15
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.school;

import com.gta.oec.vo.school.SchoolVo;

public interface SchoolDao {
       
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
	public SchoolVo getSchoolByUserId(int userId);

}
