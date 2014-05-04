/**
 * RelationshipAnswerToQuestionAddVo.java	  V1.0   2014年4月18日 上午9:17:26
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.qacenter;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

/**
 * 功能描述：关系:描述回答和追问之间的关系.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class RelationshipAnswerToQuestionAddVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -417486737547880837L;
	private int id;
	private int answerId;
	private int questionAddId;
	private Date createTime;
	private Date updateTime;
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return this.answerId;
	}
	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return the questionAddId
	 */
	public int getQuestionAddId() {
		return this.questionAddId;
	}
	/**
	 * @param questionAddId the questionAddId to set
	 */
	public void setQuestionAddId(int questionAddId) {
		this.questionAddId = questionAddId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
