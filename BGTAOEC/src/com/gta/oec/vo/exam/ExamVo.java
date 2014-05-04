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
import java.util.List;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：考试实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8244956418058815794L;
	/**主键ID**/
	private int examId;
	/**试卷ID**/
	private int paperId;
	/**考试名称、作业名称**/
	private String examName;
	/**考试类型 1：课程考试；2：课程练习；3：课程作业；4：认证考试**/
	private int examType;
	/**老师Id**/
	private int teacherId;
	/**课程ID**/
	private int courId;
	/**课程名称**/
	private String courseName;
	/**学习该课程的人数**/
	private int learnCount;
	/**章节ID**/
	private int secId;
	/**考试时长**/
	private float examDuration;
	/**考试地点**/
	private String examAddress;
	/**提交期限**/
	private Date examLastTime;
	/**考试评分方式**/
	private int examMarType;
	/**考试难度**/
	private int examLevel;
	/**已参加人数**/
	private int examCount;
	/**通过人数**/
	private int examPassCount;
	/**考试说明**/
	private String examInstruction;
	/**价格**/
	private float examPrice;
	/**创建时间**/
	private Date examCtime;
	/**修改时间**/
	private Date examUtime;
	/**考试时间**/
	private Date examTime;
	/**批阅状态**/
	private int paperState;
	/**未批阅数**/
	private int noCorrect;
	/**已批阅数**/
	private int hasCorrect;
	/**学生考试列表**/
	private List<ExamStudentVo> stuList;
	
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getCourId() {
		return courId;
	}
	public void setCourId(int courId) {
		this.courId = courId;
	}
	public int getSecId() {
		return secId;
	}
	public void setSecId(int secId) {
		this.secId = secId;
	}
	public float getExamDuration() {
		return examDuration;
	}
	public void setExamDuration(float examDuration) {
		this.examDuration = examDuration;
	}
	public String getExamAddress() {
		return examAddress;
	}
	public void setExamAddress(String examAddress) {
		this.examAddress = examAddress;
	}
	public Date getExamLastTime() {
		return examLastTime;
	}
	public void setExamLastTime(Date examLastTime) {
		this.examLastTime = examLastTime;
	}
	public int getExamMarType() {
		return examMarType;
	}
	public void setExamMarType(int examMarType) {
		this.examMarType = examMarType;
	}
	public int getExamLevel() {
		return examLevel;
	}
	public void setExamLevel(int examLevel) {
		this.examLevel = examLevel;
	}
	public int getExamCount() {
		return examCount;
	}
	public void setExamCount(int examCount) {
		this.examCount = examCount;
	}
	public int getExamPassCount() {
		return examPassCount;
	}
	public void setExamPassCount(int examPassCount) {
		this.examPassCount = examPassCount;
	}	
	public String getExamInstruction() {
		return examInstruction;
	}
	public void setExamInstruction(String examInstruction) {
		this.examInstruction = examInstruction;
	}
	public float getExamPrice() {
		return examPrice;
	}
	public void setExamPrice(float examPrice) {
		this.examPrice = examPrice;
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
	public Date getExamTime() {
		return examTime;
	}
	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}
	public int getPaperState() {
		return paperState;
	}
	public void setPaperState(int paperState) {
		this.paperState = paperState;
	}
	public List<ExamStudentVo> getStuList() {
		return stuList;
	}
	public void setStuList(List<ExamStudentVo> stuList) {
		this.stuList = stuList;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getNoCorrect() {
		return noCorrect;
	}
	public void setNoCorrect(int noCorrect) {
		this.noCorrect = noCorrect;
	}
	public int getHasCorrect() {
		return hasCorrect;
	}
	public void setHasCorrect(int hasCorrect) {
		this.hasCorrect = hasCorrect;
	}
	public int getLearnCount() {
		return learnCount;
	}
	public void setLearnCount(int learnCount) {
		this.learnCount = learnCount;
	}
}
