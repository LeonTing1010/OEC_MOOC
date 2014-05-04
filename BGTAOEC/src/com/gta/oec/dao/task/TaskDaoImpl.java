/**
 * NoteDaoImpl.java	  V1.0   2014年1月13日 下午6:19:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.course.TaskVo;

@Repository
public class TaskDaoImpl extends BaseDao<TaskVo> implements TaskDao{

	@Override
	public List<TaskVo> getTaskList(TaskVo taskVo){
		
		return super.findList(generateStatement("getTaskList"), taskVo);
	}

	@Override
	public TaskVo addTask(TaskVo taskVo){
		
		 super.insert(generateStatement("addTask"), taskVo);
		 return taskVo;
	}

	
}
