/**
 * PraiseDetailVo.java	  V1.0   2014年4月17日 下午2:22:33
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
 * 功能描述：赞明细 vo
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class PraiseDetailVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4895313161890250604L;

	/**
	 * praiseId赞主键id/
	 */
	private int praiseId;
	/**
	 * answerId答案的id
	 */
	private int answerId;
	/**
	 * userId赞的用户的id
	 */
	private int userId;
	/**
	 * createTime创建时间
	 */
	private Date createTime;
	/**
	 * updateTime更新时间.
	 */
	private Date updateTime;

	/**
	 * @return the praiseId
	 */
	public int getPraiseId() {
		return this.praiseId;
	}

	/**
	 * @param praiseId
	 *            the praiseId to set
	 */
	public void setPraiseId(int praiseId) {
		this.praiseId = praiseId;
	}

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return this.answerId;
	}

	/**
	 * @param answerId
	 *            the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
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
	 * @param updateTime
	 *            the updateTime to set
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
