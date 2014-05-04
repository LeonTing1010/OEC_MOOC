/**
 * StudentService.java	  V1.0   2014-1-14 下午6:07:18
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.student;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.student.StudentVO;
public interface StudentService {
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
	public StudentVO saveStudent(StudentVO user)throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：根据用户id查询学生信息
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-20 下午3:00:53</p>
	 *
	 * @param stuId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public StudentVO getStuByUserId(int stuId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：个人资料修改
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-21 上午8:53:29</p>
	 *
	 * @param student
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateStudent(StudentVO student)throws BaseException;
}
