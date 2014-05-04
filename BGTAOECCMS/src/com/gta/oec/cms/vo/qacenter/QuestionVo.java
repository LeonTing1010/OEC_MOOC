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
import com.gta.oec.cms.vo.course.Course;
import com.gta.oec.cms.vo.course.CourseDetail;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;
import com.gta.oec.cms.vo.user.User;

/**
 * 功能描述：问题value object
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class QuestionVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1871827194573894168L;

	public static final int QUESTION_OF_QACENTER = 0;
	public static final int QUESTION_OF_COURSE = 1;
	public static final short ANSWERED_QUESTION = 1;
	public static final short UNANSWERED_QUESTION = 0;
	public static final short UNCHOSEN_QUESTION = 0;
	public static final short CHOSEN_QUESTION = 1;
	public static final short VISIBLE_QUESTION = 0;
	public static final short INVISIBLE_QUESTION = 1;

	// 问题id
	private int questionId;
	// 课程提问对应的课程id
	private int courseId;
	// 课程提问对应的章节信息id
	private int sectionId;
	// 提问人用户id
	private int userId;
	// 问题分类对应的岗位id
	private int jobId;
	// 问题内容
	private String questionContent;
	// 问题描述
	private String questionDescription;
	// 问题状态.是否回答.0未.1已
	private short questionStatus = UNANSWERED_QUESTION;
	// 问题种类.0:答疑.1:课程.
	private int questionType;
	// 该问题的回答数量.
	private int answerCountOfQuestion;
	// 该问题的关注数量.
	private int attentionCountOfQuestion;
	// 问题浏览权限
	private int questionLookLimit;
	// 问题回答权限
	private int questionAnswerLimit;
	// 提问价格.
	private float questionPrice;
	// 是否是精选问题.0否1是
	private short chosenQuestionOrNot = UNCHOSEN_QUESTION;
	// 问题相关图片链接
	private String questionPictrueUrl;
	// 该问题是否可见.0是1否.
	private short visibleQuestionOrNot = VISIBLE_QUESTION;

	private Date createTime;
	private Date updateTime;

	/**
	 * 以下不是question的属性.
	 */
	private Course courseOfQuestion;
	private CourseDetail sectionOfQuestion;
	private User userOfAskQuestion;
	private Profession professionOfQuestion;
	private Job jobGroupOfQuestion;
	
	
	private List<AnswerVo> answerList;
	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return this.questionId;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return this.courseId;
	}

	/**
	 * @return the sectionId
	 */
	public int getSectionId() {
		return this.sectionId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return this.jobId;
	}

	/**
	 * @return the questionContent
	 */
	public String getQuestionContent() {
		return this.questionContent;
	}

	/**
	 * @return the questionDescription
	 */
	public String getQuestionDescription() {
		return this.questionDescription;
	}

	/**
	 * @return the questionStatus
	 */
	public short getQuestionStatus() {
		return this.questionStatus;
	}

	/**
	 * @return the questionType
	 */
	public int getQuestionType() {
		return this.questionType;
	}

	/**
	 * @return the answerCountOfQuestion
	 */
	public int getAnswerCountOfQuestion() {
		return this.answerCountOfQuestion;
	}

	/**
	 * @return the attentionCountOfQuestion
	 */
	public int getAttentionCountOfQuestion() {
		return this.attentionCountOfQuestion;
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
	 * @return the chosenQuestionOrNot
	 */
	public short getChosenQuestionOrNot() {
		return this.chosenQuestionOrNot;
	}

	/**
	 * @return the questionPictrueUrl
	 */
	public String getQuestionPictrueUrl() {
		return this.questionPictrueUrl;
	}

	/**
	 * @return the visibleQuestionOrNot
	 */
	public short getVisibleQuestionOrNot() {
		return this.visibleQuestionOrNot;
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
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @param sectionId
	 *            the sectionId to set
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * @param questionContent
	 *            the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	/**
	 * @param questionDescription
	 *            the questionDescription to set
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	/**
	 * @param questionStatus
	 *            the questionStatus to set
	 */
	public void setQuestionStatus(short questionStatus) {
		this.questionStatus = questionStatus;
	}

	/**
	 * @param questionType
	 *            the questionType to set
	 */
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	/**
	 * @param answerCountOfQuestion
	 *            the answerCountOfQuestion to set
	 */
	public void setAnswerCountOfQuestion(int answerCountOfQuestion) {
		this.answerCountOfQuestion = answerCountOfQuestion;
	}

	/**
	 * @param attentionCountOfQuestion
	 *            the attentionCountOfQuestion to set
	 */
	public void setAttentionCountOfQuestion(int attentionCountOfQuestion) {
		this.attentionCountOfQuestion = attentionCountOfQuestion;
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
	 * @param chosenQuestionOrNot
	 *            the chosenQuestionOrNot to set
	 */
	public void setChosenQuestionOrNot(short chosenQuestionOrNot) {
		this.chosenQuestionOrNot = chosenQuestionOrNot;
	}

	/**
	 * @param questionPictrueUrl
	 *            the questionPictrueUrl to set
	 */
	public void setQuestionPictrueUrl(String questionPictrueUrl) {
		this.questionPictrueUrl = questionPictrueUrl;
	}

	/**
	 * @param visibleQuestionOrNot
	 *            the visibleQuestionOrNot to set
	 */
	public void setVisibleQuestionOrNot(short visibleQuestionOrNot) {
		this.visibleQuestionOrNot = visibleQuestionOrNot;
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
	 * @return the courseOfQuestion
	 */
	public Course getCourseOfQuestion() {
		return this.courseOfQuestion;
	}

	/**
	 * @return the sectionOfQuestion
	 */
	public CourseDetail getSectionOfQuestion() {
		return this.sectionOfQuestion;
	}

	/**
	 * @return the userOfAskQuestion
	 */
	public User getUserOfAskQuestion() {
		return this.userOfAskQuestion;
	}

	/**
	 * @return the professionOfQuestion
	 */
	public Profession getProfessionOfQuestion() {
		return this.professionOfQuestion;
	}

	/**
	 * @return the jobGroupOfQuestion
	 */
	public Job getJobGroupOfQuestion() {
		return this.jobGroupOfQuestion;
	}

	/**
	 * @param courseOfQuestion the courseOfQuestion to set
	 */
	public void setCourseOfQuestion(Course courseOfQuestion) {
		this.courseOfQuestion = courseOfQuestion;
	}

	/**
	 * @param sectionOfQuestion the sectionOfQuestion to set
	 */
	public void setSectionOfQuestion(CourseDetail sectionOfQuestion) {
		this.sectionOfQuestion = sectionOfQuestion;
	}

	/**
	 * @param userOfAskQuestion the userOfAskQuestion to set
	 */
	public void setUserOfAskQuestion(User userOfAskQuestion) {
		this.userOfAskQuestion = userOfAskQuestion;
	}

	/**
	 * @param professionOfQuestion the professionOfQuestion to set
	 */
	public void setProfessionOfQuestion(Profession professionOfQuestion) {
		this.professionOfQuestion = professionOfQuestion;
	}

	/**
	 * @param jobGroupOfQuestion the jobGroupOfQuestion to set
	 */
	public void setJobGroupOfQuestion(Job jobGroupOfQuestion) {
		this.jobGroupOfQuestion = jobGroupOfQuestion;
	}

	/**
	 * @return the answerList
	 */
	public List<AnswerVo> getAnswerList() {
		return answerList;
	}

	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}

}
