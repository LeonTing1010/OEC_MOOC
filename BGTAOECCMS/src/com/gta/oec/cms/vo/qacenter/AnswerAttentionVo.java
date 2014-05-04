/**
 * AnswerAttentionVo.java	  V1.0   2014年3月24日 下午4:31:54
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.qacenter;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

/**
 * 功能描述：关注问题的回答value object
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class AnswerAttentionVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2501709264985048457L;
	/**
	 * id主键
	 */
	private int id;
	/**
	 * userId用户id
	 */
	private int userId;
	/**
	 * questionId被关注的问题id
	 */
	private int questionId;
	/**
	 * answerId被关注的问题的回答id
	 */
	private int answerId;
	/**
	 * isReadAnswer关注者对于该条回答是否已读 0.未读 1.已读
	 */
	private int isReadAnswer;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the userIdOfTeacher
	 */
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return this.questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the isReadAnswer
	 */
	public int getIsReadAnswer() {
		return isReadAnswer;
	}

	/**
	 * @param isReadAnswer
	 *            the isReadAnswer to set
	 */
	public void setIsReadAnswer(int isReadAnswer) {
		this.isReadAnswer = isReadAnswer;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
