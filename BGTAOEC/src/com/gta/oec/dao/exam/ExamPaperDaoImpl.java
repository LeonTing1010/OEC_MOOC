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


import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.exam.ExamPaperVo;

@Repository
public class ExamPaperDaoImpl extends BaseDao<ExamPaperVo> implements ExamPaperDao{
	

	@Override
	public ExamPaperVo insert(ExamPaperVo examPaperVo) {
		super.insert(generateStatement("insert"), examPaperVo);
		return examPaperVo;
	}

}
