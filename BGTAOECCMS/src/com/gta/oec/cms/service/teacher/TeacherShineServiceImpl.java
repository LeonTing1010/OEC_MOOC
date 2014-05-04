package com.gta.oec.cms.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.dao.teacher.TeacherShineDao;
import com.gta.oec.cms.vo.teacher.TeacherShineVo;

@Service
public class TeacherShineServiceImpl implements TeacherShineService{

	@Autowired
	private TeacherShineDao teacherShineDao; 
	@Override
	public void insertTeacherShine(TeacherShineVo teacherShineVo)
			throws Exception {
		 try{
			 teacherShineDao.insertTeacherShine(teacherShineVo);
		 }catch(Exception e){
			 e.printStackTrace();
			 throw e;
		 }
		
	}

}
