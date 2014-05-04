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


import java.util.List;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.exam.ExamQuestionVo;

@Repository
public class ExamQuestionDaoImpl extends BaseDao<ExamQuestionVo> implements ExamQuestionDao{
	


	@Override
	public ExamQuestionVo insert(ExamQuestionVo examQuestionVo) {
		super.insert(generateStatement("insert"), examQuestionVo);
		return examQuestionVo;
	}

	@Override
	public ExamQuestionVo selectExamQuestionById(int examQuesId) {
		return (ExamQuestionVo) super.selectOne(generateStatement("selectExamQuestionById"), examQuesId);
	}
	
	public int updateExamQuestion(int examQuesId) {
		return super.update(generateStatement("updateAnswPraiseCountByAnswId"),examQuesId);
	}

	@Override
	public List<ExamQuestionVo> selectExamQuesList(ExamQuestionVo examQuestionVo) {
		return super.findList(generateStatement("selectExamQuestionList"),examQuestionVo);
	}

	@Override
	public int selectExamQuesScore(ExamQuestionVo examQuestionVo) {
		Object obj = super.selectOne(generateStatement("selectExamQuesScore"), examQuestionVo);
		if(obj==null){
			return 0;
		}else{
			return (Integer)super.selectOne(generateStatement("selectExamQuesScore"), examQuestionVo);
		}
	}

	@Override
	public ExamQuestionVo update(ExamQuestionVo examQuestionVo) {
		super.update(generateStatement("update"), examQuestionVo);
		return examQuestionVo;
	}
}
