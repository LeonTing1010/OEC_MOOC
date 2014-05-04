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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamVo;

@Repository
public class ExamDaoImpl extends BaseDao<ExamVo> implements ExamDao{
	

	@Override
	public ExamVo insert(ExamVo examVo) {
		super.insert(generateStatement("insert"), examVo);
		return examVo;
	}
	
	
    public ExamVo getExamById(int examId){
		return (ExamVo)super.selectOne(generateStatement("getExamById"), examId);
	}

	@Override
	public List<ExamVo> getExamListByCourId(int courId,int secId,int examType){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courId", courId);
		map.put("secId", secId);
		map.put("examType", examType);
		return super.findList(generateStatement("getExamListByCourId"), map);
	}

	@Override
	public List<ExamVo> getExamList(ExamVo examVo) {
		List<ExamVo> examList=new ArrayList<ExamVo>();
		examList=super.findList(generateStatement("getExamList"),examVo);
		return examList;
	}
	@Override
	public List<ExamVo> getExamList(ExamVo examVo, CourseVo courseVo, int n,int m) {
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("examVo", examVo);
		map.put("courseVo", courseVo);
		map.put("n", n);
		map.put("m", m);
		
		return super.findList(generateStatement("getCourExamList"), map);
	}

	@Override
	public List<ExamVo> selectExamList(ExamVo examVo) {
		return super.findList(generateStatement("selectExamList"),examVo);
	}

	
}
