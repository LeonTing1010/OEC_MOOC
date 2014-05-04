/**
 * QuestionAttentionVo.java	  V1.0   2014年3月24日 下午4:31:54
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.qacenter;

import com.gta.oec.cms.vo.BaseValObject;

/**
 * 功能描述：问题关注value object
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class QuestionAttentionVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3757274086416888063L;
	
	
	/**
	 * 关注问题的id主键
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
	 * status关注问题的状态:0-有未读回答，1-全部回答已读
	 */
	private int status;
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
	 * @param id the id to set
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
	 * @param userId the userId to set
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
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
