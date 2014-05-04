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
 * 功能描述：问题选项实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ExamOptionVo extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1370300696550530530L;
	/**试题项ID**/
	private int optId;
	/**试题ID**/
	private int quesId;
    /**试题项编号**/
	private String optNum;
	/**试题项内容**/
	private String optContent;
	/**是否正确选项**/
	private int optIsRight;
	/**创建时间**/
	private Date optCtime;
	/**修改时间**/
	private Date optUtime;
	public int getOptId() {
		return optId;
	}
	public void setOptId(int optId) {
		this.optId = optId;
	}
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public String getOptNum() {
		return optNum;
	}
	public void setOptNum(String optNum) {
		this.optNum = optNum;
	}
	public String getOptContent() {
		return optContent;
	}
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}
	public int getOptIsRight() {
		return optIsRight;
	}
	public void setOptIsRight(int optIsRight) {
		this.optIsRight = optIsRight;
	}
	public Date getOptCtime() {
		return optCtime;
	}
	public void setOptCtime(Date optCtime) {
		this.optCtime = optCtime;
	}
	public Date getOptUtime() {
		return optUtime;
	}
	public void setOptUtime(Date optUtime) {
		this.optUtime = optUtime;
	}
	
}
