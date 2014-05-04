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
 * 功能描述：试卷实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamPaperVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6654132806992655532L;
	/**主键ID**/
	private int paperId;
	/**考试id**/
	private int examId;
	/**考试名称、作业名称**/
	private String paperName;

	private float paperTotalScore;
	/**老师Id**/
	private int userId;
	/**试卷类型 1：课程考试；2：课程练习；3：课程作业；4：认证考试**/
	private int paperType;
	/**创建时间**/
	private Date paperCtime;
	/**修改时间**/
	private Date paperUtime;
	/***单选题***/
	private List<ExamQuestionVo> radioList;
	/***多选题***/
	private List<ExamQuestionVo> multipleChoiceList;
	/***判断题***/
	private List<ExamQuestionVo> determineList;
	/***单选题***/
	private List<ExamQuestionVo> subjectiveList;
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public float getPaperTotalScore() {
		return paperTotalScore;
	}
	public void setPaperTotalScore(float paperTotalScore) {
		this.paperTotalScore = paperTotalScore;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPaperType() {
		return paperType;
	}
	public void setPaperType(int paperType) {
		this.paperType = paperType;
	}
	public Date getPaperCtime() {
		return paperCtime;
	}
	public void setPaperCtime(Date paperCtime) {
		this.paperCtime = paperCtime;
	}
	public Date getPaperUtime() {
		return paperUtime;
	}
	public void setPaperUtime(Date paperUtime) {
		this.paperUtime = paperUtime;
	}
	
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public List<ExamQuestionVo> getRadioList() {
		return radioList;
	}
	public void setRadioList(List<ExamQuestionVo> radioList) {
		this.radioList = radioList;
	}
	public List<ExamQuestionVo> getMultipleChoiceList() {
		return multipleChoiceList;
	}
	public void setMultipleChoiceList(List<ExamQuestionVo> multipleChoiceList) {
		this.multipleChoiceList = multipleChoiceList;
	}
	public List<ExamQuestionVo> getDetermineList() {
		return determineList;
	}
	public void setDetermineList(List<ExamQuestionVo> determineList) {
		this.determineList = determineList;
	}
	public List<ExamQuestionVo> getSubjectiveList() {
		return subjectiveList;
	}
	public void setSubjectiveList(List<ExamQuestionVo> subjectiveList) {
		this.subjectiveList = subjectiveList;
	}
	
}
