/**
 * StudentServiceImpl.java	  V1.0   2014-1-14 下午6:11:19
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.student;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.dao.student.StudentDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.student.StudentVO;
@Service
public class StudentServiceImpl implements StudentService{
	private static final Log logger = LogFactory.getLog(StudentServiceImpl.class);
	/**
	 * funciton:添加学生信息
	 * Add   By:缪佳 2014-01-14
	 */
	@Autowired
    private StudentDao studentDao;
	@Override
	@Transactional()
	public StudentVO saveStudent(StudentVO user)throws BaseException{
		 try {
			 return studentDao.addStudent(user);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
				throw new SystemDBException("DB getStuByUserId Error!");
			}
	}
	@Override
	public StudentVO getStuByUserId(int stuId) throws BaseException {
		try {
			return studentDao.getStuByUserId(stuId);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("DB getStuByUserId Error!");
		}
	}
	
	@Override
	public int updateStudent(StudentVO student)throws BaseException {
		try{
			return studentDao.updateStudent(student);
		}catch(Exception e){
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("DB getStuByUserId Error!");
		}
	}

}
