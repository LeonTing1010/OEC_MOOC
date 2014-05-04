package com.gta.oec.cms.vo.message;

import java.util.Date;

import com.gta.oec.cms.vo.user.User;
public class Message extends MessageModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6327939937321994070L;

	private Integer messageId;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	 
	/**
	 * 创建时间
	 * @return
	 */
	private Date messageCreateDate;
	/**
	 * 结束时间 用户查询
	 */
	private Date endDate;
	/**
	 * 消息是否已读
	 * @return
	 */
	private Integer readFlag;
 
	
	/**
	 * 用户相关信息
	 */
	private User userVo;
	
	/**
	 * 系统操作管理员  邮箱
	 */
	private String adminUserEmail;
	
	/**
	 * 系统操作管理员id
	 * @return
	 */
	private Integer adminUserId;
	
	
	
	   
 
	public Integer getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getAdminUserEmail() {
		return adminUserEmail;
	}
	public void setAdminUserEmail(String adminUserEmail) {
		this.adminUserEmail = adminUserEmail;
	}
	public User getUserVo() {
		return userVo;
	}
	public void setUserVo(User userVo) {
		this.userVo = userVo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	 
	 
	public Date getMessageCreateDate() {
		return messageCreateDate;
	}
	public void setMessageCreateDate(Date messageCreateDate) {
		this.messageCreateDate = messageCreateDate;
	}
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
 
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	 
	
	
}
