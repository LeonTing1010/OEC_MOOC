/**
 * SchoolServiceImpl.java	  V1.0   2014-3-21 上午11:24:44
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.school;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.school.SchoolDao;
import com.gta.oec.cms.vo.school.School;

@Service
public class SchoolServiceImpl implements SchoolService {

//	private static Logger log = Logger.getLogger(SchoolServiceImpl.class);
	@Override
	public List<School> allSchoolPageQuery(PageModel<School> pm) {
		return schDao.allSchoolPageQuery(pm);
	}

	@Override
	public List<School> schoolPageQuery(PaginationContext<School> pm) {
//		log.debug(new GsonBuilder().create().toJson(pm));
		return schDao.schoolPageQuery(pm);
	}

	@Override
	public void addSchool(School school) throws RuntimeException {
		schDao.addSchool(school);
	}

	@Override
	public void updateSchool(School school) throws RuntimeException {
		schDao.updateSchool(school);
	}

	@Override
	public void delSchool(int schoolId) throws RuntimeException {
		schDao.delSchool(schoolId);
	}
	
	@Autowired
	private SchoolDao schDao;
}
