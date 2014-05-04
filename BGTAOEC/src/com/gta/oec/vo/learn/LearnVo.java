/**
 * LearnVo.java	  V1.0   2014-1-15 上午10:46:00
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.learn;



import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：学习详细信息VO
 * 
 * @author bingzhong.qin
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class LearnVo extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6363452430318406672L;
	/** 学习id **/
	private int learnId;
	/** 课程id **/
	private int courseId;
	/** 节id **/
	private int sectionId;
	/** 用户id **/
	private int userId;
	/** 学习状态 **/
	private int learnState;
	/** 创建时间 **/
	private Date createTimeDate;
	/** 修改时间id **/
	private Date updateTime;

	public int getLearnId() {
		return learnId;
	}

	public void setLearnId(int learnId) {
		this.learnId = learnId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLearnState() {
		return learnState;
	}

	public void setLearnState(int learnState) {
		this.learnState = learnState;
	}

	public Date getCreateTimeDate() {
		return createTimeDate;
	}

	public void setCreateTimeDate(Date createTimeDate) {
		this.createTimeDate = createTimeDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
