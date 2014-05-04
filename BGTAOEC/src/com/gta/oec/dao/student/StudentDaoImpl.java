/**
 * studentDalImpl.java	  V1.0   2014-1-10 上午10:22:28
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.student;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.student.StudentVO;
@Repository
public class StudentDaoImpl extends BaseDao<StudentVO> implements StudentDao{
    /**
     * 
     * 功能描述：添加学生信息到数据库
     *
     * @author  Miaoj
     * <p>创建日期 ：2014-1-10 上午11:10:00</p>
     *
     * @param user
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	@Override
	public StudentVO addStudent(StudentVO student)
	{
		super.insert(generateStatement("insert"),student);
		return student;
	}
	
	/**
	 * 根据用户id查询学生信息
	 */
	@Override
	public StudentVO getStuByUserId(int stuId) {
		return (StudentVO)super.selectOne(generateStatement("getStuByUserId"), stuId);
	}
	
	@Override
	public int updateStudent(StudentVO student) {
		Map< String,Object> map = new HashMap<String, Object>();
		if(student!=null&&!"".equals(student)){
			map.put("education", student.getEducation());
			map.put("studyGoal", student.getStudyGoal());
			map.put("userID", student.getUserID());
		}
		 return super.update(generateStatement("updateStuInfo"),map);
		
	}
}
