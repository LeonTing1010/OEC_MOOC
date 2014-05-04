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
 * 功能描述：问题实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamQuestionVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 666816845902238532L;
	/**主键ID**/
	private int examQuesId;
	/**试卷ID**/
	private int paperId;
	/**问题内容**/
	private String examQuesContent;
    /**试题类型的显示顺序**/
	private int examQuesNum;
	/**试题类型**/
	private int examQuesType;
	/**试题分数**/
	private Double examQuesScore;
	/**试题答案**/
	private String examQuesAnswer;
	/**创建时间**/
	private Date examQuesCtime;
	/**修改时间**/
	private Date examQuesUtime;
	/**学生的回答**/
	private ExamAnswerDetailVo examAnswerDetailVo;
	
	/**引入的试题选项对象**/
	private List<ExamOptionVo> examOptionlist;
	
	public int getExamQuesId() {
		return examQuesId;
	}
	public void setExamQuesId(int examQuesId) {
		this.examQuesId = examQuesId;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getExamQuesNum() {
		return examQuesNum;
	}
	public void setExamQuesNum(int examQuesNum) {
		this.examQuesNum = examQuesNum;
	}
	public int getExamQuesType() {
		return examQuesType;
	}
	public void setExamQuesType(int examQuesType) {
		this.examQuesType = examQuesType;
	}
	public Date getExamQuesCtime() {
		return examQuesCtime;
	}
	public void setExamQuesCtime(Date examQuesCtime) {
		this.examQuesCtime = examQuesCtime;
	}
	public Date getExamQuesUtime() {
		return examQuesUtime;
	}
	public void setExamQuesUtime(Date examQuesUtime) {
		this.examQuesUtime = examQuesUtime;
	}
	public String getExamQuesContent() {
		return examQuesContent;
	}
	public void setExamQuesContent(String examQuesContent) {
		this.examQuesContent = examQuesContent;
	}
	public String getExamQuesAnswer() {
		return examQuesAnswer;
	}
	public void setExamQuesAnswer(String examQuesAnswer) {
		this.examQuesAnswer = examQuesAnswer;
	}
	public ExamAnswerDetailVo getExamAnswerDetailVo() {
		return examAnswerDetailVo;
	}
	public void setExamAnswerDetailVo(ExamAnswerDetailVo examAnswerDetailVo) {
		this.examAnswerDetailVo = examAnswerDetailVo;
	}
	public List<ExamOptionVo> getExamOptionlist() {
		return examOptionlist;
	}
	public void setExamOptionlist(List<ExamOptionVo> examOptionlist) {
		this.examOptionlist = examOptionlist;
	}
	public Double getExamQuesScore() {
		return examQuesScore;
	}
	public void setExamQuesScore(Double examQuesScore) {
		this.examQuesScore = examQuesScore;
	}
	
}
