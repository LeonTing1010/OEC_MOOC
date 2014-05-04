/**
 * NoteVo.java	  V1.0   2014年1月13日 下午5:22:00
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.course;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 作业 实体类
 * 功能描述：
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class TaskVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 634886892685678866L;
	/**作业ID**/
	private int taskId;
	/**课程编号**/
	private int courId;
	/**章节ID**/
	private int secId;
	/**作业名称**/
	private String taskName;
	/**作业内容**/
	private String taskContent;
	/**用户id**/
	private int userId;
	/**作业评价**/
	private String taskEvaluate;
	/**作业评分**/
	private String taskScore;
	/**提交时间**/
	private Date taskSubmitTime;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getCourId() {
		return courId;
	}
	public void setCourId(int courId) {
		this.courId = courId;
	}
	public int getSecId() {
		return secId;
	}
	public void setSecId(int secId) {
		this.secId = secId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTaskEvaluate() {
		return taskEvaluate;
	}
	public void setTaskEvaluate(String taskEvaluate) {
		this.taskEvaluate = taskEvaluate;
	}
	public String getTaskScore() {
		return taskScore;
	}
	public void setTaskScore(String taskScore) {
		this.taskScore = taskScore;
	}
	public Date getTaskSubmitTime() {
		return taskSubmitTime;
	}
	public void setTaskSubmitTime(Date taskSubmitTime) {
		this.taskSubmitTime = taskSubmitTime;
	}
}
