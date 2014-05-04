/**
 * LearnDaoImpl.java	  V1.0   2014-1-15 上午10:54:04
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.learn;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.util.DateUtils;
import com.gta.oec.vo.learn.LearnVo;
@Repository
public class LearnDaoImpl extends BaseDao<LearnVo> implements LearnDao{

	private static final Log logger = LogFactory.getLog(LearnDaoImpl.class);
	@Override
	public LearnVo getLearn(LearnVo learnVo) {
		return (LearnVo) super.selectOne(generateStatement("getLearn"), learnVo);
	}

	@Override
	public LearnVo insert(LearnVo learnVo) {
		super.insert(learnVo);
		return learnVo;
	}

	@Override
	public Integer getUserCourseLearnCount(LearnVo learnVo) {
		return (Integer) super.selectOne(generateStatement("getUserCourseLearnCount"), learnVo);
	}

	@Override
	public void updateUserCourseProgress(Integer courseId,Integer userId,float progress) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseId", courseId);
		map.put("userId", userId);
		map.put("progess", progress);
		try {
			map.put("updateTime", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		super.update(generateStatement("updateUserCourseProgress"), map);
	}

	@Override
	public List<LearnVo> getUserCourse(LearnVo learnVo) {
		return super.findList(generateStatement("getUserCourse"),learnVo);
	}

	@Override
	public void insertUserCourse(LearnVo learnVo) {
		super.insert(generateStatement("insertUserCourse"), learnVo);
	}
  
}
