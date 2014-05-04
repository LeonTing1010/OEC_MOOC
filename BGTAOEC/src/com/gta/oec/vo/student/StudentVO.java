/**
 * StudentVO.java	  V1.0   2014-1-10 上午10:27:17
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.vo.student;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：学生对象类
 *
 * @author  Miaoj
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class StudentVO extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3414802770364247442L;
	private int ID;
	
    public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	//	用户ID
	private int userID;
	//	学习目标
	private String studyGoal;
    //	学习学分
	private float studyCredit;
    //	教育程度
	private String education;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getStudyGoal() {
		return studyGoal;
	}
	public void setStudyGoal(String studyGoal) {
		this.studyGoal = studyGoal;
	}
	public float getStudyCredit() {
		return studyCredit;
	}
	public void setStudyCredit(float studyCredit) {
		this.studyCredit = studyCredit;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}
