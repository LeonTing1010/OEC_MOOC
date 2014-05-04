
package com.gta.oec.vo.qacenter;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.gta.oec.vo.BaseVO;

/**
 * 回答实体类
 * @author 刘祚家
 *
 */
public class AnswerVo extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;
	/**回答ID**/
	private int answID;
	
	/**问题ID**/
	private int quesID;
	/**用户ID**/
	private int userID;
	
	/**回答内容**/
	private String answContent;
	
	/**赞次数**/
	private int answPraiseCount;

	/**浏览权限**/
	private String answLookLimit;
	
	/**创建时间**/
	private Date answCtime;
	/**修改时间**/
	private Date answUtime;
	
	/**追加问题ID**/
	private int quesAddID;
	
	/**提问者对于该条答案是否已读 0.未读 1.已读**/
	private int answIsRead;
	
	
	
	
	/**以下不是问题对象本身属性--------start-------**/
	private String userName;
	private String teacherLevel;
	private String praiseSign;
	/**用户头像**/
	private String userHeadPic;
	
	/**回答的追加问题**/
	private List<QuestionAddVo> quesAddLists;
	
	/**统计、检查对同一个老师所有的回答追问次数**/
	private int totalQuesAddNum;
	/**老师ID **/
	private int teacherId;
	/**标识：已赞、赞 **/
	private int flag;
	/**以下不是问题对象本身属性--------end---------**/


	public int getAnswID() {
		return answID;
	}


	public void setAnswID(int answID) {
		this.answID = answID;
	}


	public int getQuesID() {
		return quesID;
	}


	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getAnswContent() {
		return answContent;
	}


	public void setAnswContent(String answContent) {
		this.answContent = answContent;
	}


	public int getAnswPraiseCount() {
		return answPraiseCount;
	}


	public void setAnswPraiseCount(int answPraiseCount) {
		this.answPraiseCount = answPraiseCount;
	}


	public String getAnswLookLimit() {
		return answLookLimit;
	}


	public void setAnswLookLimit(String answLookLimit) {
		this.answLookLimit = answLookLimit;
	}


	public Date getAnswCtime() {
		return answCtime;
	}


	public void setAnswCtime(Date answCtime) {
		this.answCtime = answCtime;
	}


	public Date getAnswUtime() {
		return answUtime;
	}


	public void setAnswUtime(Date answUtime) {
		this.answUtime = answUtime;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getTeacherLevel() {
		return teacherLevel;
	}


	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}


	public String getPraiseSign() {
		return praiseSign;
	}


	public void setPraiseSign(String praiseSign) {
		this.praiseSign = praiseSign;
	}


	public String getUserHeadPic() {
		return userHeadPic;
	}


	public void setUserHeadPic(String userHeadPic) {
		this.userHeadPic = userHeadPic;
	}


	public List<QuestionAddVo> getQuesAddLists() {
		return quesAddLists;
	}


	public void setQuesAddLists(List<QuestionAddVo> quesAddLists) {
		this.quesAddLists = quesAddLists;
	}


	public int getQuesAddID() {
		return quesAddID;
	}


	public void setQuesAddID(int quesAddID) {
		this.quesAddID = quesAddID;
	}


	public int getAnswIsRead() {
		return answIsRead;
	}


	public void setAnswIsRead(int answIsRead) {
		this.answIsRead = answIsRead;
	}


	public int getTotalQuesAddNum() {
		return totalQuesAddNum;
	}


	public void setTotalQuesAddNum(int totalQuesAddNum) {
		this.totalQuesAddNum = totalQuesAddNum;
	}


	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}	
}
