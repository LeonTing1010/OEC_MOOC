
package com.gta.oec.vo.qacenter;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;

/**
 * 问答实体类
 * @author 刘祚家
 *
 */
public class QuestionAddVo extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;
	/**追加问题ID**/
	private int quesAddID;
	/**用户ID**/
	private int userID;
	/**岗位群ID**/
	private int jobGroupID;
	/**提问内容**/
	private String quesContent;
	/**追问状态**/
	private int quesStatus;
	/**问题类型**/
	private int quesType;
	/**回答次数**/
	private int quesAnswerCount;
	/**关注人数**/
	private int quesAttentionCount;

	/**追问回答老师ID**/
	private int quesAnswerUserID;
	/**浏览权限**/
	private int quesLookLimit;
	/**回答权限**/
	private int quesAnswerLimit;
	/**价格设定**/
	private float quesPrice;
	/**创建时间**/
	private Date quesCtime;
	/**修改时间**/
	private Date quesUtime;
	
	
	/**------------以下非对象本身属性---------------**/
	/**追加问题所有回答**/
	private List<AnswerVo> answerList;
	/**问题ID**/
	private int quesID;
	/**追加回答ID**/
	private int answerId;
	
	/**用户姓名**/
	private String userName;
	/**问题对象**/
	private QuestionVo questionVo;
	
	/**------------以下非对象本身属性---------------**/
	
	public int getQuesAddID() {
		return quesAddID;
	}
	public void setQuesAddID(int quesAddID) {
		this.quesAddID = quesAddID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getJobGroupID() {
		return jobGroupID;
	}
	public void setJobGroupID(int jobGroupID) {
		this.jobGroupID = jobGroupID;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	public int getQuesStatus() {
		return quesStatus;
	}
	public void setQuesStatus(int quesStatus) {
		this.quesStatus = quesStatus;
	}
	public int getQuesType() {
		return quesType;
	}
	public void setQuesType(int quesType) {
		this.quesType = quesType;
	}
	public int getQuesAnswerCount() {
		return quesAnswerCount;
	}
	public void setQuesAnswerCount(int quesAnswerCount) {
		this.quesAnswerCount = quesAnswerCount;
	}
	public int getQuesAttentionCount() {
		return quesAttentionCount;
	}
	public void setQuesAttentionCount(int quesAttentionCount) {
		this.quesAttentionCount = quesAttentionCount;
	}
	public int getQuesAnswerUserID() {
		return quesAnswerUserID;
	}
	public void setQuesAnswerUserID(int quesAnswerUserID) {
		this.quesAnswerUserID = quesAnswerUserID;
	}
	public int getQuesLookLimit() {
		return quesLookLimit;
	}
	public void setQuesLookLimit(int quesLookLimit) {
		this.quesLookLimit = quesLookLimit;
	}
	public int getQuesAnswerLimit() {
		return quesAnswerLimit;
	}
	public void setQuesAnswerLimit(int quesAnswerLimit) {
		this.quesAnswerLimit = quesAnswerLimit;
	}
	public float getQuesPrice() {
		return quesPrice;
	}
	public void setQuesPrice(float quesPrice) {
		this.quesPrice = quesPrice;
	}
	public Date getQuesCtime() {
		return quesCtime;
	}
	public void setQuesCtime(Date quesCtime) {
		this.quesCtime = quesCtime;
	}
	public Date getQuesUtime() {
		return quesUtime;
	}
	public void setQuesUtime(Date quesUtime) {
		this.quesUtime = quesUtime;
	}
	public List<AnswerVo> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getQuesID() {
		return quesID;
	}
	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public QuestionVo getQuestionVo() {
		return questionVo;
	}
	public void setQuestionVo(QuestionVo questionVo) {
		this.questionVo = questionVo;
	}

	
}
