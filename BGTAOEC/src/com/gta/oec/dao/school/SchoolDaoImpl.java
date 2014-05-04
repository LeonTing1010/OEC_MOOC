/**
 * studentDalImpl.java	  V1.0   2014-1-10 上午10:22:28
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.school;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.school.SchoolVo;
@Repository
public class SchoolDaoImpl extends BaseDao<SchoolVo> implements SchoolDao{


	@Override
	public SchoolVo getSchoolByUserId(int id) {
		return (SchoolVo)super.selectOne(generateStatement("getSchoolByUserId"),id);
	}
}
