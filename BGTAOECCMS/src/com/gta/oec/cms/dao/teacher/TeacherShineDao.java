package com.gta.oec.cms.dao.teacher;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.teacher.TeacherShineVo;
import com.gta.oec.cms.vo.user.User;

public interface TeacherShineDao extends SqlMapper<TeacherShineDao>{
	public void insertTeacherShine(@Param(value = "teacherShineVo") TeacherShineVo teacherShineVo);
	
	/**
	 *删除u_teacher_shine表
	 * 
	 */
	public void delTeacherShine(@Param(value="user")User user)throws Exception;
}
