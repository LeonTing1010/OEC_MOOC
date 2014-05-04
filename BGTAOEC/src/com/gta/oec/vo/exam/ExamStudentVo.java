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

/**
 * 
 * 功能描述：考试实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamStudentVo {

	/**主键ID**/
	private int id;
	/**考试ID**/
	private int examId;
	/**学生ID**/
	private int studentId;
	/**考试开始时间**/
	private Date examTime;
	/**考试结束时间**/
	private Date examEtime;
	/**考试得分**/
	private float examScore;
	/**数据创建时间**/
	private Date examCtime;
	/**数据修改时间**/
	private Date examUtime;
	/**批阅状态**/
	private int examState;
	/**学生姓名**/
	private String studentName;
	
	/**ExamVo对象**/  
	private ExamVo examVo;

	/******课程信息***********/ 
	/**课程名称**/
	private String courName;
	/**课程ID**/
	private int courId;
	/**考试分数**/
	private float courCredit;
	
	public int getCourId() {
		return courId;
	}
	public void setCourId(int courId) {
		this.courId = courId;
	}
	public ExamVo getExamVo() {
		return examVo;
	}
	public void setExamVo(ExamVo examVo) {
		this.examVo = examVo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getExamTime() {
		return examTime;
	}
	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}
	public Date getExamEtime() {
		return examEtime;
	}
	public void setExamEtime(Date examEtime) {
		this.examEtime = examEtime;
	}
	public float getExamScore() {
		return examScore;
	}
	public void setExamScore(float examScore) {
		this.examScore = examScore;
	}
	public Date getExamCtime() {
		return examCtime;
	}
	public void setExamCtime(Date examCtime) {
		this.examCtime = examCtime;
	}
	public Date getExamUtime() {
		return examUtime;
	}
	public void setExamUtime(Date examUtime) {
		this.examUtime = examUtime;
	}
	public String getCourName() {
		return courName;
	}
	public void setCourName(String courName) {
		this.courName = courName;
	}
	public float getCourCredit() {
		return courCredit;
	}
	public void setCourCredit(float courCredit) {
		this.courCredit = courCredit;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getExamState() {
		return examState;
	}
	public void setExamState(int examState) {
		this.examState = examState;
	}
	
}
