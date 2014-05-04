
package com.gta.oec.vo.qacenter;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 用户与回答关系实体类
 * @author 刘祚家
 *
 */
public class AttentionAnswerVo extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;

	/**流水ID**/
	private int id;
	
	/**回答ID**/
	private int answerID;
	
	/**问题ID**/
	private int questionID;
	/**用户ID**/
	private int userID;
	
	/**关注者对于该条回答是否已读 0.未读 1.已读**/
	private int answIsRead;
	
	/**创建时间**/
	private Date ctime;
	/**修改时间**/
	private Date utime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAnswIsRead() {
		return answIsRead;
	}
	public void setAnswIsRead(int answIsRead) {
		this.answIsRead = answIsRead;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getUtime() {
		return utime;
	}
	public void setUtime(Date utime) {
		this.utime = utime;
	}
	
}
