/**
 * NoteDaoImpl.java	  V1.0   2014年1月13日 下午6:19:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.exam.ExamStudentVo;

@Repository
public class ExamStudentDaoImpl extends BaseDao<ExamStudentVo> implements ExamStudentDao{

	

	public ExamStudentVo insert(ExamStudentVo examStudentVo) {
		super.insert(generateStatement("insert"), examStudentVo);
		return examStudentVo;
	}
	
	/**
	 * 根据考试id集合查询对应的考试学生集合
	 */
	@Override
	public List<ExamStudentVo> getExamStudentListByExamIds(int userId,List examIds){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("examIds", examIds);
		return super.findList(generateStatement("getExamStudentListByExamIds"), map);
	}

	@Override
	public int getExamStudentListCount(int userId,int examType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", userId);
		map.put("examType", examType);
		return (Integer) super.selectOne(generateStatement("getExamStudentListCount"), map);
	}

	@Override
	public int selectTakeExamCount(ExamStudentVo examStudentVo) {
		return (Integer) super.selectOne(generateStatement("selectExamCount"), examStudentVo);
	}

	/**
	 * 根据用户id查询所学课程考试信息
	 */
	public List getCourExamInfoList(int userId,int examType,int page,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("examType", examType);
		map.put("n", (page-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getCourExamInfoList"),map);
	}
	
	public ExamStudentVo getExamStudentByExamIdAndStuId(int userId,int examId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("examId", examId);
		return (ExamStudentVo)super.selectOne(generateStatement("getExamStudentByExamIdAndStuId"), map);
	}
	

	@Override
	public List<ExamStudentVo> selectStuExamListByExamId(int examId,int examState,int pageNo,int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("examId", examId);
		map.put("examState", examState);
		map.put("n",(pageNo-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("selectStuExamListByExamId"),map);
	}

	@Override
	public void UpdateScoreAndState(int examState,double examScore,int examStuId) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("examState", examState);
		map.put("examScore", examScore);
		map.put("examStuId", examStuId);
		super.update(generateStatement("updateScoreAndState"), map);
	}

	@Override
	public int selectExamStuCount(ExamStudentVo examStudentVo) {
		return (Integer)super.selectOne("selectStuExamCount", examStudentVo);
	}
}
