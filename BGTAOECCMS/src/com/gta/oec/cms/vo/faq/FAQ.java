package com.gta.oec.cms.vo.faq;

import com.gta.oec.cms.vo.BaseValObject;

public class FAQ extends BaseValObject{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	
	/**FAQ的ID **/
	private Integer faqID;
	/**标题 **/
	private String faqTitle;
	/**内容 **/
	private String faqContent;
	/**创建时间 **/
	private String faqCTime;
	/**修改时间 **/
	private String faqUTime;
	/** **/
	private Integer faqPID;
	
	
	
	
	public Integer getFaqID() {
		return faqID;
	}
	public void setFaqID(Integer faqID) {
		this.faqID = faqID;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public String getFaqCTime() {
		return faqCTime;
	}
	public void setFaqCTime(String faqCTime) {
		this.faqCTime = faqCTime;
	}
	public String getFaqUTime() {
		return faqUTime;
	}
	public void setFaqUTime(String faqUTime) {
		this.faqUTime = faqUTime;
	}
	public Integer getFaqPID() {
		return faqPID;
	}
	public void setFaqPID(Integer faqPID) {
		this.faqPID = faqPID;
	}
	
	
	
}
