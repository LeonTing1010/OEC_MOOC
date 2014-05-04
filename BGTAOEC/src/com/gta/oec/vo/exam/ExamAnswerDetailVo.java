/**
 * examstudentVo.java	  V1.0   2014-1-18 下午3:19:14
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.exam;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：考生考试作答实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamAnswerDetailVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1156580380318426527L;
	/**作答明细ID**/
	private int examAnswerId;
	/**学生考试Id**/
	private int examStuId;
	/**试题Id**/
	private int examQuesId;
	/**做题答案**/
	private String examAnswer;
	/**得分**/
	private double examAnswerScore;
	/**创建时间**/
	private Date examAnswerCtime;
	/**修改时间**/
	private Date examAnswerUtime;
	public int getExamAnswerId() {
		return examAnswerId;
	}
	public void setExamAnswerId(int examAnswerId) {
		this.examAnswerId = examAnswerId;
	}
	public int getExamStuId() {
		return examStuId;
	}
	public void setExamStuId(int examStuId) {
		this.examStuId = examStuId;
	}
	public int getExamQuesId() {
		return examQuesId;
	}
	public void setExamQuesId(int examQuesId) {
		this.examQuesId = examQuesId;
	}
	public String getExamAnswer() {
		return examAnswer;
	}
	public void setExamAnswer(String examAnswer) {
		this.examAnswer = examAnswer;
	}
	public double getExamAnswerScore() {
		return examAnswerScore;
	}
	public void setExamAnswerScore(double examAnswerScore) {
		this.examAnswerScore = examAnswerScore;
	}
	public Date getExamAnswerCtime() {
		return examAnswerCtime;
	}
	public void setExamAnswerCtime(Date examAnswerCtime) {
		this.examAnswerCtime = examAnswerCtime;
	}
	public Date getExamAnswerUtime() {
		return examAnswerUtime;
	}
	public void setExamAnswerUtime(Date examAnswerUtime) {
		this.examAnswerUtime = examAnswerUtime;
	}
}
