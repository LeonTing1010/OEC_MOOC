/**
 * student.java	  V1.0   2014-1-10 上午10:21:15
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.student;

import com.gta.oec.vo.student.StudentVO;

public interface StudentDao {
        /**
         * 
         * 功能描述：
         *
         * @author  Miaoj
         * <p>创建日期 ：2014-1-10 上午11:10:00</p>
         *
         * @param user
         *
         * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
         */
		public StudentVO addStudent(StudentVO student);

		/**
		 * 
		 * 功能描述：根据用户id查询学生信息
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-14 上午11:14:18</p>
		 *
		 * @param stuId
		 * @return
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public StudentVO getStuByUserId(int stuId);
		
		/**
		 * 
		 * 功能描述：个人资料修改
		 *
		 * @author  yangyang.ou
		 * <p>创建日期 ：2014-1-21 上午8:55:54</p>
		 *
		 * @param student
		 *
		 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
		 */
		public int updateStudent(StudentVO student);
}
