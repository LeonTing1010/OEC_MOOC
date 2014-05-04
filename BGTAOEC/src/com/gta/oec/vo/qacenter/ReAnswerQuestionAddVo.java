
package com.gta.oec.vo.qacenter;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;

/**
 * 问答实体类
 * @author 刘祚家
 *
 */
public class ReAnswerQuestionAddVo extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;
	
	/**流水号ID**/
	private int id;
	/**追加回答ID**/
	private int answerId;
	/**追加问题ID**/
	private int quesAddId;
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
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getQuesAddId() {
		return quesAddId;
	}
	public void setQuesAddId(int quesAddId) {
		this.quesAddId = quesAddId;
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
