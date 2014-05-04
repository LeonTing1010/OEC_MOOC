/**
 * Job.java	  V1.0   2014-3-18 下午12:26:34
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.job;

import java.util.Date;
import java.util.List;

import com.gta.oec.cms.vo.BaseValObject;
import com.gta.oec.cms.vo.profession.Profession;

public class Job extends BaseValObject {
	
	public static final String CLOSED = "closed";
	public static final String OPEN = "open";
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 岗位ID **/
	private int jobID;
	/** 职业ID **/
	private int proID;
	/** 岗位名称 **/
	private String jobName;
	/** 岗位介绍 **/
	private String jobDescription;
	/** 父岗位ID **/
	private int jobPID;
	/** 岗位收藏数 **/
	private int jobCollectCount;
	/** 岗位详情 **/
	private String jobDetail;
	/** 岗位发展方向 **/
	private String jobDevelpping;
	/** 岗位职责 **/
	private String jobDuty;
	/** 岗位需求 **/
	private String jobDemand;
	/** 岗位级别 **/
	private int jobLevel;
	/** 薪资行情 **/
	private String jobWage;
	/** 创建时间 **/
	private Date jobCtime;
	/** 修改时间 **/
	private Date jobUtime;
	/** 岗位图片 **/
	private String jobImage;
	/** 岗位推荐 **/
	private int jobRecommend;
	/**是否隐藏*/
	private short jobIsVisible = 0;
	/** 子岗位群 */
	private List<Profession> children;
	/** 岗位状态 */
	private String state = CLOSED;
	/*real ProId*/
	private int realProID;
	/** 子岗位列表：岗位群有此项 **/
	private List<Job> list;
	
	public List<Job> getList() {
		return list;
	}

	public void setList(List<Job> list) {
		this.list = list;
	}

	public int getRealProID() {
		return realProID;
	}

	public void setRealProID(int realProID) {
		this.realProID = realProID;
	}

	public List<Profession> getChildren() {
		return children;
	}

	public void setChildren(List<Profession> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	 
	public int getProID() {
		return proID;
	}

	public void setProID(int proID) {
		this.proID = proID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getJobPID() {
		return jobPID;
	}

	public void setJobPID(int jobPID) {
		this.jobPID = jobPID;
	}

	public int getJobCollectCount() {
		return jobCollectCount;
	}

	public void setJobCollectCount(int jobCollectCount) {
		this.jobCollectCount = jobCollectCount;
	}

	public String getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(String jobDetail) {
		this.jobDetail = jobDetail;
	}

	public String getJobDevelpping() {
		return jobDevelpping;
	}

	public void setJobDevelpping(String jobDevelpping) {
		this.jobDevelpping = jobDevelpping;
	}

	public String getJobDuty() {
		return jobDuty;
	}

	public void setJobDuty(String jobDuty) {
		this.jobDuty = jobDuty;
	}

	public String getJobDemand() {
		return jobDemand;
	}

	public void setJobDemand(String jobDemand) {
		this.jobDemand = jobDemand;
	}

	public int getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getJobWage() {
		return jobWage;
	}

	public void setJobWage(String jobWage) {
		this.jobWage = jobWage;
	}

	public Date getJobCtime() {
		return jobCtime;
	}

	public void setJobCtime(Date jobCtime) {
		this.jobCtime = jobCtime;
	}

	public Date getJobUtime() {
		return jobUtime;
	}

	public void setJobUtime(Date jobUtime) {
		this.jobUtime = jobUtime;
	}

	public String getJobImage() {
		return jobImage;
	}

	public void setJobImage(String jobImage) {
		this.jobImage = jobImage;
	}

	public int getJobRecommend() {
		return jobRecommend;
	}

	public void setJobRecommend(int jobRecommend) {
		this.jobRecommend = jobRecommend;
	}

	public short getJobIsVisible() {
		return jobIsVisible;
	}

	public void setJobIsVisible(short jobIsVisible) {
		this.jobIsVisible = jobIsVisible;
	}

	
}
