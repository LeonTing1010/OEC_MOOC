/**
 * CourseServiceImpl.java	  V1.0   2013-12-27 ����10:30:57
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.school;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gta.oec.dao.school.SchoolDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.school.SchoolVo;

@Service
public class SchoolServiceImpl implements SchoolService{
	private static final Log logger = LogFactory.getLog(SchoolServiceImpl.class);
	@Autowired
    private SchoolDao schoolDao; 

	//功能描述：根据老师id查学校
	@Override
	public SchoolVo getSchoolByUserId(Integer id) throws BaseException {
		try {
			return schoolDao.getSchoolByUserId(id);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}


}
