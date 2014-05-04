/**
 * QuestionUserVo.java	  V1.0   2014年4月17日 上午11:17:12
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
 * 功能描述：问题邀请老师回答表vo.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class QuestionUserVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2353951778778667494L;
	/**
	 * id主键
	 */
	private int id;
	/**
	 * teacherId被邀请的老师的用户id
	 */
	private int userIdOfTeacher;
	/**
	 * questionId发出邀请的问题id
	 */
	private int questionId;
	/**
	 * status老师是否进行了回答.状态:0-未回答，1-已回答
	 */
	private int status;

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
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            the status to set
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

	/**
	 * @return the userIdOfTeacher
	 */
	public int getUserIdOfTeacher() {
		return userIdOfTeacher;
	}

	/**
	 * @param userIdOfTeacher
	 *            the userIdOfTeacher to set
	 */
	public void setUserIdOfTeacher(int userIdOfTeacher) {
		this.userIdOfTeacher = userIdOfTeacher;
	}

}
