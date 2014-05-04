/**
 * ResourceImpl.java	  V1.0   2014年1月15日 上午11:11:44
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.resource.ResourceVo;
import com.sun.org.apache.bcel.internal.generic.NEW;
@Repository
public class ResourceDaoImpl extends BaseDao<ResourceVo> implements ResourceDao{

	@Override
	public List<ResourceVo> getResourceList(ResourceVo resourceVo) {
		
		return super.findList(generateStatement("selectRescourceList"), resourceVo);
	}

	@Override
	public ResourceVo addResource(ResourceVo resourceVo) {
		
		super.insert(generateStatement("insertResource"), resourceVo);
		return resourceVo;
	}

	@Override
	public ResourceVo addBindCourRescource(ResourceVo resourceVo) {
		
		super.insert(generateStatement("insertReCourRescource"), resourceVo);
		return resourceVo;
	}

	@Override
	public List<ResourceVo> getReCourResourceList(ResourceVo resourceVo,int resourseVoId) {
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("resourceVo",resourceVo);
		map.put("id", resourseVoId);
		return super.findList(generateStatement("selectReCourResourceList"),map);
	}

	@Override
	public ResourceVo deleteResource(ResourceVo resourceVo) {
		super.getSqlSession().delete("deleteResource", resourceVo);
		return resourceVo;
	}

	@Override
	public ResourceVo deleteBindCourRescource(ResourceVo resourceVo) {
		super.getSqlSession().delete("deleteBindCourRescourse", resourceVo);
		return resourceVo;
	}

	@Override
	public Integer getSectionResourceCount(int sectionId,String sourceType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sectionId", sectionId);
		map.put("sourceType", sourceType);
		return (Integer) super.selectOne(generateStatement("getSectionResourceCount"), map);
	}

	@Override
	public ResourceVo getResourceById(Integer resourceId) {
		return (ResourceVo) super.selectOne(generateStatement("getResourceById"), resourceId);
	}
}
