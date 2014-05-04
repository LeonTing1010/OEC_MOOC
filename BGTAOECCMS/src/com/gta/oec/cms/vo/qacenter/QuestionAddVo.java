/**
 * QuestionVo.java	  V1.0   2014年3月24日 下午4:31:54
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

/**
 * 功能描述：追问value object
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class QuestionAddVo extends BaseValObject {
	public static final int QUESTION_OF_QACENTER = 0;
	public static final int QUESTION_OF_COURSE = 1;
	public static final short ANSWERED_QUESTION = 1;
	public static final short UNANSWERED_QUESTION = 0;
	private static final long serialVersionUID = 7920865612340125141L;
	/** 追加问题ID **/
	private int questionAddId;
	/** 追问的用户ID **/
	private int userId;
	/** 提问内容 **/
	private String questionContent;
	/** 追问状态 .是否被回答. **/
	private int questionStatus = UNANSWERED_QUESTION;
	/** 浏览权限 **/
	private int questionLookLimit;
	/** 回答权限 **/
	private int questionAnswerLimit;
	/** 价格设定 **/
	private float questionPrice;
	/** 创建时间 **/
	private Date createTime;
	/** 修改时间 **/
	private Date updateTime;

	// 以下不是本类属性
	// 一个追问暂时只要一个回答.
	private List<AnswerVo> answerListOfQuestionAdd;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the questionAddId
	 */
	public int getQuestionAddId() {
		return this.questionAddId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * @return the questionContent
	 */
	public String getQuestionContent() {
		return this.questionContent;
	}

	/**
	 * @return the questionStatus
	 */
	public int getQuestionStatus() {
		return this.questionStatus;
	}

	/**
	 * @return the questionLookLimit
	 */
	public int getQuestionLookLimit() {
		return this.questionLookLimit;
	}

	/**
	 * @return the questionAnswerLimit
	 */
	public int getQuestionAnswerLimit() {
		return this.questionAnswerLimit;
	}

	/**
	 * @return the questionPrice
	 */
	public float getQuestionPrice() {
		return this.questionPrice;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @param questionAddId
	 *            the questionAddId to set
	 */
	public void setQuestionAddId(int questionAddId) {
		this.questionAddId = questionAddId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @param questionContent
	 *            the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	/**
	 * @param questionStatus
	 *            the questionStatus to set
	 */
	public void setQuestionStatus(int questionStatus) {
		this.questionStatus = questionStatus;
	}

	/**
	 * @param questionLookLimit
	 *            the questionLookLimit to set
	 */
	public void setQuestionLookLimit(int questionLookLimit) {
		this.questionLookLimit = questionLookLimit;
	}

	/**
	 * @param questionAnswerLimit
	 *            the questionAnswerLimit to set
	 */
	public void setQuestionAnswerLimit(int questionAnswerLimit) {
		this.questionAnswerLimit = questionAnswerLimit;
	}

	/**
	 * @param questionPrice
	 *            the questionPrice to set
	 */
	public void setQuestionPrice(float questionPrice) {
		this.questionPrice = questionPrice;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the answerListOfQuestionAdd
	 */
	public List<AnswerVo> getAnswerListOfQuestionAdd() {
		return answerListOfQuestionAdd;
	}

	/**
	 * @param answerListOfQuestionAdd
	 *            the answerListOfQuestionAdd to set
	 */
	public void setAnswerListOfQuestionAdd(List<AnswerVo> answerListOfQuestionAdd) {
		this.answerListOfQuestionAdd = answerListOfQuestionAdd;
	}

}
