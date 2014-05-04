package com.gta.oec.cms.vo.comment;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

public class Comment extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 点评ID **/
	private Integer cID;
	/** 课程ID **/
	private Integer cCourseID;
	/** 课程名称 **/
	private String cName;
	/** 点评内容 **/
	private String cContent;
	/** 点评时间 **/
	private Date cTime;
	/** 点评人ID **/
	private Integer cUserID;
	/** 点评人 **/
	private String uName;
	/** 点评来源 **/
	private String cSource;
	/**显示依据**/
	private Integer cDel;
	
	
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Integer getcDel() {
		return cDel;
	}
	public void setcDel(Integer cDel) {
		this.cDel = cDel;
	}
	public Integer getcID() {
		return cID;
	}
	public void setcID(Integer cID) {
		this.cID = cID;
	}
	public Integer getcCourseID() {
		return cCourseID;
	}
	public void setcCourseID(Integer cCourseID) {
		this.cCourseID = cCourseID;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public Integer getcUserID() {
		return cUserID;
	}
	public void setcUserID(Integer cUserID) {
		this.cUserID = cUserID;
	}
	public String getcSource() {
		return cSource;
	}
	public void setcSource(String cSource) {
		this.cSource = cSource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
