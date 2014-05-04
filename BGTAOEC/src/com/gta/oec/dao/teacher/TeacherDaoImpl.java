/**
 * studentDalImpl.java	  V1.0   2014-1-10 上午10:22:28
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.teacher.TeacherVo;
@Repository
public class TeacherDaoImpl extends BaseDao<TeacherVo> implements TeacherDao{

	@Override
	public TeacherVo getTeacherByUserId(int userId) {
		return (TeacherVo)super.selectOne(generateStatement("getTeacherByUserId"),userId);
	}

	@Override
	public List<TeacherVo> getTeacherListByProfessionId(int proId,int pageNo,int pageSize) {
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("proId", proId);
		int limit=pageSize;
		int offset=(pageNo-1)*pageSize;
		parameter.put("limit", limit);
		parameter.put("offset", offset);
		//return findList(generateStatement("getTeacherListByProfessionId"), parameter);
		return findList(generateStatement("getTeacherListByShineProfId"), parameter);
	}

	@Override
	public List<TeacherVo> getTeacherListByjobGroupId(int jobGroupId,int pageNo,int pageSize) {
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("jobGroupId", jobGroupId);
		int limit=pageSize;
		int offset=(pageNo-1)*pageSize;
		parameter.put("limit", limit);
		parameter.put("offset", offset);
		//return findList(generateStatement("getTeacherListByjobGroupId"), parameter);
		return findList(generateStatement("getTeacherListByShineJobgId"), parameter);
	}

	@Override
	public List<TeacherVo> getTeacherList(int pageNo,int pageSize) {
		Map<String, Object> parameter=new HashMap<String, Object>();
		int limit=pageSize;
		int offset=(pageNo-1)*pageSize;
		parameter.put("limit", limit);
		parameter.put("offset", offset);
		//return findList(generateStatement("getTeacherList"), parameter);
		return findList(generateStatement("getTeacherListInShine"), parameter);
	}

	@Override
	public TeacherVo getTeacherVoById(int teacherId) {
		return (TeacherVo) selectOne(generateStatement("getTeacherById"), teacherId);
	}
	
	@Override
	public int updateTeacherMode(TeacherVo teacher)
	{
		return super.update(generateStatement("updateTeacher"), teacher);	
	}

	@Override
	public int countTeacherByProfessionId(int proId) {
//		return (Integer) selectOne(generateStatement("countTeacherListByProfessionId"), proId);
		return (Integer) selectOne(generateStatement("countTeacherListByShineProId"), proId);
	}

	@Override
	public int countTeacherListByjobGroupId(int jobGroupId) {
//		return (Integer) selectOne(generateStatement("countTeacherListByjobGroupId"), jobGroupId);
		return (Integer) selectOne(generateStatement("countTeacherListByShinejobgId"), jobGroupId);
	}

	@Override
	public int countTeacher() {
		//return (Integer) selectOne(generateStatement("countTeacher"),0);
		return (Integer) selectOne(generateStatement("countShineTeacher"),0);
	}

}
