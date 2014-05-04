/**
 * questiontionVo.java	  V1.0   2014年3月24日 下午4:31:54
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.qacenter;

import java.util.Date;
import java.util.List;

import com.gta.oec.cms.vo.BaseValObject;
import com.gta.oec.cms.vo.teacher.TeacherVo;

/**
 * 功能描述：回答value object
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class AnswerVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3959039986120482430L;
	/**
	 * 0.未读 UNREAD
	 */
	public static final int UNREAD = 0;
	/**
	 * 1.已读 READ
	 */
	public static final int READ = 1;

	/** 回答Id **/
	private int answerId;
	/** 问题Id **/
	private int questionId;
	/** 追加问题Id **/
	private int questionAddId;
	/** 回答人的用户Id **/
	private int userId;
	/** 回答内容 **/
	private String answerContent;
	/** 赞次数 **/
	private int answerPraiseCount;
	/** 浏览权限 **/
	private String answerLookLimit;
	/** 提问者对于该条答案是否已读 0.未读 1.已读 **/
	private int answerIsRead = UNREAD;
	/** 创建时间 **/
	private Date createTime;
	/** 修改时间 **/
	private Date updateTime;

	/**
	 * 以下不是answerVo的属性.
	 */
	private TeacherVo teacherUserOfAnswer;
	private List<QuestionAddVo> questionAddList;
	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return this.answerId;
	}

	/**
	 * @param answerId
	 *            the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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
	 * @return the questionAddId
	 */
	public int getQuestionAddId() {
		return this.questionAddId;
	}

	/**
	 * @param questionAddId
	 *            the questionAddId to set
	 */
	public void setQuestionAddId(int questionAddId) {
		this.questionAddId = questionAddId;
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
	 * @return the answerContent
	 */
	public String getAnswerContent() {
		return this.answerContent;
	}

	/**
	 * @param answerContent
	 *            the answerContent to set
	 */
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	/**
	 * @return the answerPraiseCount
	 */
	public int getAnswerPraiseCount() {
		return this.answerPraiseCount;
	}

	/**
	 * @param answerPraiseCount
	 *            the answerPraiseCount to set
	 */
	public void setAnswerPraiseCount(int answerPraiseCount) {
		this.answerPraiseCount = answerPraiseCount;
	}

	/**
	 * @return the answerLookLimit
	 */
	public String getAnswerLookLimit() {
		return this.answerLookLimit;
	}

	/**
	 * @param answerLookLimit
	 *            the answerLookLimit to set
	 */
	public void setAnswerLookLimit(String answerLookLimit) {
		this.answerLookLimit = answerLookLimit;
	}

	/**
	 * @return the answerIsRead
	 */
	public int getAnswerIsRead() {
		return this.answerIsRead;
	}

	/**
	 * @param answerIsRead
	 *            the answerIsRead to set
	 */
	public void setAnswerIsRead(int answerIsRead) {
		this.answerIsRead = answerIsRead;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
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
		return this.updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the teacherUserOfAnswer
	 */
	public TeacherVo getTeacherUserOfAnswer() {
		return this.teacherUserOfAnswer;
	}

	/**
	 * @param teacherUserOfAnswer
	 *            the teacherUserOfAnswer to set
	 */
	public void setTeacherUserOfAnswer(TeacherVo teacherUserOfAnswer) {
		this.teacherUserOfAnswer = teacherUserOfAnswer;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the questionAddList
	 */
	public List<QuestionAddVo> getQuestionAddList() {
		return questionAddList;
	}

	/**
	 * @param questionAddList the questionAddList to set
	 */
	public void setQuestionAddList(List<QuestionAddVo> questionAddList) {
		this.questionAddList = questionAddList;
	}
}
