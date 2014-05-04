/**
 * CourseServiceImpl.java	  V1.0   2013-12-27 ����10:30:57
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.studentcenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.message.MessageDao;
import com.gta.oec.dao.user.UserDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.message.MessageVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.user.UserVo;

@Service
public class StudentcenterServiceImpl implements StudentcenterService{
	private static final Log logger = LogFactory.getLog(StudentcenterServiceImpl.class);
	@Autowired
    private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	@Autowired 
	private CourseDao courseDao;
	
	/**
	 * 根据消息id获取消息
	 */
	@Override
	public MessageVo getMessageById(Integer id) throws BaseException {
		try {
			return messageDao.get(id);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 根据用户id查询消息列表
	 */
	@Override
	public List<MessageVo> getMessageList(Integer userId) throws BaseException {
		try {
			return messageDao.getMessageList(userId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 根据用户id获取用户
	 */
	@Override
	public UserVo getUserById(Integer userId) throws BaseException {
		try {
			return userDao.getUserById(userId);
		} catch (Exception  e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 修改用户
	 */
	@Override
	public int updateUser(UserVo user) throws BaseException {
		try {
			return userDao.updateUser(user);
		} catch (Exception  e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	/**
	 * 查询课程列表
	 */
	@Override
	public List<CourseVo> getCourseList(int jobID) throws BaseException {
		try {
		    
			return courseDao.getCourseList(null, 0, 1);
		} catch (Exception  e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

}
