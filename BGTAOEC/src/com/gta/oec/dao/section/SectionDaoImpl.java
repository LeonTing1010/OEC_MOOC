/**
 * SectionDaoImpl.java	  V1.0   2014-1-7 上午8:45:26
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.section;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.course.SectionVO;
@Repository
public class SectionDaoImpl extends BaseDao<SectionVO> implements SectionDao {

	@Override
	public List<SectionVO> getSectionList(SectionVO sectionVO) {
		return super.findList(generateStatement("getSectionList"), sectionVO);
	}

	@Override
	public List<SectionVO> getUserSectionList(SectionVO sectionVO,int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sectionVO", sectionVO);
		map.put("userId", userId);
		return super.findList("getUserSectionList", map);
	}

	@Override
	public SectionVO insert(SectionVO sectionVO) {
		super.insert(sectionVO);
		return sectionVO;
	}

	@Override
	public SectionVO update(SectionVO sectionVO) {
		super.update(generateStatement("update"), sectionVO);
		return sectionVO;
	}


	@Override
	public void deleteSection(Integer sectionId) {
	  this.getSqlSession().delete(generateStatement("delete"), sectionId);
	}

	@Override
	public SectionVO getSectionById(Integer sectionId) {
		return (SectionVO) super.selectOne(generateStatement("getSectionById"), sectionId);
	}

	@Override
	public int getSectionNotCompleteCount(Integer courseId) {
		return (Integer) super.selectOne(generateStatement("getSectionNotCompleteCount"), courseId);
	}

	@Override
	public void updateSecUrlNull(String secUrl) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("secUrl", secUrl);
		 super.update(generateStatement("updateSecUrlNull"),map);
	}

	@Override
	public void updateSectionTypeByCourseId(SectionVO sectionVO) {
		super.update(generateStatement("updateSectionTypeByCourseId"),sectionVO);
	}

}
