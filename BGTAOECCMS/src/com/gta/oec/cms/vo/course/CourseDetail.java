/**
 * CourseDetail.java	  V1.0   2014-3-18 上午11:32:22
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.course;

import java.util.Date;
import java.util.List;

import com.gta.oec.cms.vo.BaseValObject;

public class CourseDetail extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2536587222811557496L;
	/** 主键ID **/
	private int id;
	/** 课程ID **/
	private int courseId;
	/** 章节名称 **/
	private String name;
	/** 章节标题 **/
	private String title;
	/** 章节介绍 **/
	private String introduction;
	/** 章节开始时间 **/
	private Date startTime;
	/** 章节时长：分钟 **/
	private int time;
	/** 章节类型：直播or点播 **/
	private String type;
	/** 视频URL **/
	private String secUrl;
	/** 章节编号 **/
	private int num;
	/** 父章节ID **/
	private int pid;
	/** 章节序号 **/
	private int sequence;
	/** 章节价格 **/
	private float price;
	/** 修改时间 **/
	private Date cTime;
	/** 创建时间 **/
	private Date uTime;
	/** 核心知识点 **/
	private String coreKnowledge;
	/**节列表：只有章下面才有节列表**/
    private List<CourseDetail> list;
    /**课程国际化信息**/
	private String videoProtocol;
	/**辅助资源是否存在**/
	private int resourceSum;
	
	/**
	 * @return the resourceSum
	 */
	public int getResourceSum() {
		return resourceSum;
	}
	/**
	 * @param resourceSum the resourceSum to set
	 */
	public void setResourceSum(int resourceSum) {
		this.resourceSum = resourceSum;
	}
	/**
	 * @return the videoProtocol
	 */
	public String getVideoProtocol() {
		return videoProtocol;
	}
	/**
	 * @param videoProtocol the videoProtocol to set
	 */
	public void setVideoProtocol(String videoProtocol) {
		this.videoProtocol = videoProtocol;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the secUrl
	 */
	public String getSecUrl() {
		return secUrl;
	}
	/**
	 * @param secUrl the secUrl to set
	 */
	public void setSecUrl(String secUrl) {
		this.secUrl = secUrl;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the cTime
	 */
	public Date getcTime() {
		return cTime;
	}
	/**
	 * @param cTime the cTime to set
	 */
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	/**
	 * @return the uTime
	 */
	public Date getuTime() {
		return uTime;
	}
	/**
	 * @param uTime the uTime to set
	 */
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	/**
	 * @return the coreKnowledge
	 */
	public String getCoreKnowledge() {
		return coreKnowledge;
	}
	/**
	 * @param coreKnowledge the coreKnowledge to set
	 */
	public void setCoreKnowledge(String coreKnowledge) {
		this.coreKnowledge = coreKnowledge;
	}
	/**
	 * @return the list
	 */
	public List<CourseDetail> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<CourseDetail> list) {
		this.list = list;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
