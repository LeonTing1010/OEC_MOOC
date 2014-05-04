package com.gta.oec.cms.vo.feedback;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

public class Feedback extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 意见反馈ID **/
	private Integer fID;
	/** 意见反馈标题 **/
	private String fTitle;
	/** 意见反馈内容 **/
	private String fContent;
	/** 意见反馈邮箱 **/
	private String fEmail;
	/** 意见反馈时间 **/
	private Date fCtime;
	/** 意见反馈来源 **/
	private String fSource;
	public Integer getfID() {
		return fID;
	}
	public void setfID(Integer fID) {
		this.fID = fID;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfEmail() {
		return fEmail;
	}
	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}
	public Date getfCtime() {
		return fCtime;
	}
	public void setfCtime(Date fCtime) {
		this.fCtime = fCtime;
	}
	public String getfSource() {
		return fSource;
	}
	public void setfSource(String fSource) {
		this.fSource = fSource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
