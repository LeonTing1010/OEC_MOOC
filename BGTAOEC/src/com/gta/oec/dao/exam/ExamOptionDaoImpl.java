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
import com.gta.oec.vo.exam.ExamOptionVo;

@Repository
public class ExamOptionDaoImpl extends BaseDao<ExamOptionVo> implements ExamOptionDao{
	


	public ExamOptionVo insert(ExamOptionVo examOptionVo) {
		super.insert(generateStatement("insert"), examOptionVo);
		return examOptionVo;
	}
	
	/**
	 * 根据试题id查询试题项  by zhangjin
	 */
	public List<ExamOptionVo> getExamOptionByQuesId(int quesId){
		
		return super.findList(generateStatement("getExamOptionByQuesId"), quesId);
	}

}
