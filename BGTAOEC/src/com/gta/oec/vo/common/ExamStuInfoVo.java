
package com.gta.oec.vo.common;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 学生考试作业列表信息实体类
 * @author zhangjin
 *
 */
public class ExamStuInfoVo extends BaseVO{
	private static final long serialVersionUID = 7920865612340125141L;
	
	/**作业名称**/
	private int examId;
	
	private int examStuId;
	
	private String examName;
	
	private float examScore;
	
	private int examStatus;
	
	private Date examCTime;

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public float getExamScore() {
		return examScore;
	}

	public void setExamScore(float examScore) {
		this.examScore = examScore;
	}

	public int getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(int examStatus) {
		this.examStatus = examStatus;
	}

	public Date getExamCTime() {
		return examCTime;
	}

	public void setExamCTime(Date examCTime) {
		this.examCTime = examCTime;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getExamStuId() {
		return examStuId;
	}

	public void setExamStuId(int examStuId) {
		this.examStuId = examStuId;
	}
}
