/**
 * LearnServiceImpl.java	  V1.0   2014-1-20 下午5:39:44
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.course;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.learn.LearnDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.util.DateUtils;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.learn.LearnVo;
@Service
public class LearnServiceImpl implements LearnService {
	private static final Log logger =  LogFactory.getLog(LearnServiceImpl.class);
	@Autowired
	LearnDao learnDao; 
	@Autowired
	CourseDao courseDao;
	

	@Override
	public LearnVo saveLearn(LearnVo learnVo) throws BaseException {
		if (null==learnVo||learnVo.getCourseId()<=0||learnVo.getSectionId()<=0||learnVo.getUserId()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		
		try {
		    LearnVo checkVo =	learnDao.getLearn(learnVo);
		    //校验数据是否重复
			if (null==checkVo) {
				try {
					learnVo.setCreateTimeDate(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					learnVo.setUpdateTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				} catch (Exception e) {
					logger.error(e); e.printStackTrace();
				}
				learnVo = learnDao.insert(learnVo);		
				updateLearnProgress(learnVo.getCourseId(),learnVo.getUserId());
				return learnVo;
			}
			return checkVo;
		 } catch (Exception e) {
				logger.error("System DB operate error!", e);
				throw new SystemDBException("System DB operate error!!");
		 }
	}


	@Override
	public void updateLearnProgress(Integer courseId, Integer userId) throws BaseException{
		if (courseId<=0||userId<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			LearnVo queryVo = new  LearnVo();
			queryVo.setCourseId(courseId);
			queryVo.setUserId(userId);
			//已学习课时
			int learnCount = learnDao.getUserCourseLearnCount(queryVo);
			if (learnCount>0) {
				CourseVo courseVo = courseDao.getCourseById(courseId);
				//总学时
				int period = courseVo.getCourWeek();
				if (period>0) {
					//计算学习进度
					float progress = learnCount*10/period;
					if (learnCount>=period) {
						progress = 10;
					}
					learnDao.updateUserCourseProgress(courseId, userId, progress);
				}
			}		
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");		}
	}


	@Override
	public Integer getUserCourseLearnCount(LearnVo learnVo)
			throws BaseException {
		if (null == learnVo || learnVo.getCourseId() <= 0
				|| learnVo.getUserId() <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
              return learnDao.getUserCourseLearnCount(learnVo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}
   
}
