
package com.gta.oec.dao.task;

import java.util.List;

import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.course.TaskVo;

/**
 * 
 * 功能描述：作业 DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface TaskDao {

	/**
	 * 
	 * 功能描述：获取作业列表
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-16 下午6:44:48</p>
	 *
	 * @param noteVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TaskVo> getTaskList(TaskVo taskVo);
	
	/**
	 * 
	 * 功能描述：添加笔记
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-16 下午6:45:21</p>
	 *
	 * @param noteVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public TaskVo addTask(TaskVo taskVo);
}
