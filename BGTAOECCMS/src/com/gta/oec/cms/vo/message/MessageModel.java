package com.gta.oec.cms.vo.message;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

public class MessageModel extends BaseValObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2663257120996637116L;

	/**ID */
	private Integer messageModelid;

	/**标题 */
	private String title;
	
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 发送对象
	 */
	private Integer sendObject;
 
	
	
	 
	public Integer getMessageModelid() {
		return messageModelid;
	}
	public void setMessageModelid(Integer messageModelid) {
		this.messageModelid = messageModelid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
 
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getSendObject() {
		return sendObject;
	}
	public void setSendObject(Integer sendObject) {
		this.sendObject = sendObject;
	}
 
	
}
