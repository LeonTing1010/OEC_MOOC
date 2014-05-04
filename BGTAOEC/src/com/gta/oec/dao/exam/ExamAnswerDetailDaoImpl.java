/**
 * ExamAnswerDetailDaoImpl.java	  V1.0   2014年2月19日 下午8:12:09
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

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

@Repository
public class ExamAnswerDetailDaoImpl extends BaseDao<ExamAnswerDetailVo>implements ExamAnswerDetailDao {

	
	public int insert(ExamAnswerDetailVo examAnswerDetailVo) {
		
		return super.insert(generateStatement("insert"), examAnswerDetailVo);
	}
	
	public List<ExamAnswerDetailVo> selectExamAnswerList(ExamAnswerDetailVo examAnswerDetailVo) {
		return super.findList(generateStatement("selectExamAnswerList"), examAnswerDetailVo);
	}

	@Override
	public void updateStuExamScore(int score, int examAnsId) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("score", score);
		map.put("examAnsId", examAnsId);
		super.update("updateStuExamScore", map);
	}

	@Override
	public double selectSumCorrect(ExamAnswerDetailVo examAnswerDetailVo) {
		return (Double) super.selectOne(generateStatement("selectSumCorrect"), examAnswerDetailVo);
	}

}
