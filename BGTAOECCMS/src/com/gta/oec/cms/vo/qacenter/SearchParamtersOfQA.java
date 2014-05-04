/**
 * SearchParamtersOfQA.java	  V1.0   2014年3月27日 下午3:42:55
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
 * 功能描述：答疑中心,搜索参数类.
 * 
 * @author Administrator
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class SearchParamtersOfQA extends BaseValObject {

	public static final short UNCHOSEN_QUESTION = 0;
	public static final short CHOSEN_QUESTION = 1;
	public static final short VISIBLE_QUESTION = 0;
	public static final short INVISIBLE_QUESTION = 1;
	public static final int QUESTION_OF_QACENTER = 0;
	public static final int QUESTION_OF_COURSE = 1;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7823383775590455966L;

	private String courseName;
	private int professionId;
	private String professionName;
	private int jobGroupId;
	private String jobGroupName;
	private int chosenQuestionOrNot;
	private String questionContent;
	private int userId;
	private String userName;
	private int visibleQuestionOrNot;
	private Date timeFrom;
	private Date timeTo;
	private int questionType;

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return this.courseName;
	}

	/**
	 * @return the professionId
	 */
	public int getProfessionId() {
		return this.professionId;
	}

	/**
	 * @return the professionName
	 */
	public String getProfessionName() {
		return this.professionName;
	}

	/**
	 * @return the jobGroupId
	 */
	public int getJobGroupId() {
		return this.jobGroupId;
	}

	/**
	 * @return the jobGroupName
	 */
	public String getJobGroupName() {
		return this.jobGroupName;
	}

	/**
	 * @return the chosenQuestionOrNot
	 */
	public int getChosenQuestionOrNot() {
		return this.chosenQuestionOrNot;
	}

	/**
	 * @return the questionContent
	 */
	public String getQuestionContent() {
		return this.questionContent;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @return the visibleQuestionOrNot
	 */
	public int getVisibleQuestionOrNot() {
		return this.visibleQuestionOrNot;
	}

	/**
	 * @return the timeFrom
	 */
	public Date getTimeFrom() {
		return this.timeFrom;
	}

	/**
	 * @return the timeTo
	 */
	public Date getTimeTo() {
		return this.timeTo;
	}

	/**
	 * @return the questionType
	 */
	public int getQuestionType() {
		return this.questionType;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @param professionId
	 *            the professionId to set
	 */
	public void setProfessionId(int professionId) {
		this.professionId = professionId;
	}

	/**
	 * @param professionName
	 *            the professionName to set
	 */
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	/**
	 * @param jobGroupId
	 *            the jobGroupId to set
	 */
	public void setJobGroupId(int jobGroupId) {
		this.jobGroupId = jobGroupId;
	}

	/**
	 * @param jobGroupName
	 *            the jobGroupName to set
	 */
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	/**
	 * @param chosenQuestionOrNot
	 *            the chosenQuestionOrNot to set
	 */
	public void setChosenQuestionOrNot(int chosenQuestionOrNot) {
		this.chosenQuestionOrNot = chosenQuestionOrNot;
	}

	/**
	 * @param questionContent
	 *            the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param visibleQuestionOrNot
	 *            the visibleQuestionOrNot to set
	 */
	public void setVisibleQuestionOrNot(int visibleQuestionOrNot) {
		this.visibleQuestionOrNot = visibleQuestionOrNot;
	}

	/**
	 * @param timeFrom
	 *            the timeFrom to set
	 */
	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	/**
	 * @param timeTo
	 *            the timeTo to set
	 */
	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	/**
	 * @param questionType
	 *            the questionType to set
	 */
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

}
