/**
 * JobVo.java	  V1.0   2014-1-7 ����13:20:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.job;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.course.CourseVo;

public class JobVo extends BaseVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1723076634005806321L;
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
	/** 薪资行情 **/
	private String jobWage;
	/** 创建时间 **/
	private Date jobCtime;
	/** 修改时间 **/
	private Date jobUtime;
	/** 子岗位列表：岗位群有此项 **/
	private List<JobVo> list;

	/** 岗位图片 **/
	private String jobImage;

	/** 岗位认证最新3条列表 **/
	private List<JobAuthenticationVo> jobAuthenticationList;

	/** 岗位推荐认证列表 **/
	private List<JobAuthenticationVo> jobAuthRecommendList;

	/** 岗位课程最新3条列表 **/
	private List<CourseJobVo> courseJobList;

	/** 岗位推荐课程列表 **/
	private List<CourseJobVo> courseJobRecommendList;

	/** 岗位所有课程列表 **/
	private List<CourseJobVo> courseJobAllList;

	/** 岗位推荐 **/
	private int jobRecommend;

	/** 岗位下收藏课程的数 **/
	private int courCount;

	/** 岗位收藏：岗位下对应课程 **/
	private List<CourseVo> courlist;

	/** 行业名称 **/
	private String proName;
	/** 岗位收藏标识，该岗位是未收藏 ，已收藏 **/
	private String collectSign;

	public List<JobVo> getList() {
		return list;
	}

	public void setList(List<JobVo> list) {
		this.list = list;
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

	public List<JobAuthenticationVo> getJobAuthenticationList() {
		return jobAuthenticationList;
	}

	public void setJobAuthenticationList(List<JobAuthenticationVo> jobAuthenticationList) {
		this.jobAuthenticationList = jobAuthenticationList;
	}

	public List<CourseJobVo> getCourseJobList() {
		return courseJobList;
	}

	public void setCourseJobList(List<CourseJobVo> courseJobList) {
		this.courseJobList = courseJobList;
	}

	public List<CourseJobVo> getCourseJobRecommendList() {
		return courseJobRecommendList;
	}

	public void setCourseJobRecommendList(List<CourseJobVo> courseJobRecommendList) {
		this.courseJobRecommendList = courseJobRecommendList;
	}

	public String getJobImage() {
		return jobImage;
	}

	public void setJobImage(String jobImage) {
		this.jobImage = jobImage;
	}

	public List<CourseJobVo> getCourseJobAllList() {
		return courseJobAllList;
	}

	public void setCourseJobAllList(List<CourseJobVo> courseJobAllList) {
		this.courseJobAllList = courseJobAllList;
	}

	public List<JobAuthenticationVo> getJobAuthRecommendList() {
		return jobAuthRecommendList;
	}

	public void setJobAuthRecommendList(List<JobAuthenticationVo> jobAuthRecommendList) {
		this.jobAuthRecommendList = jobAuthRecommendList;
	}

	public int getJobRecommend() {
		return jobRecommend;
	}

	public void setJobRecommend(int jobRecommend) {
		this.jobRecommend = jobRecommend;
	}

	public List<CourseVo> getCourlist() {
		return courlist;
	}

	public void setCourlist(List<CourseVo> courlist) {
		this.courlist = courlist;
	}

	public int getCourCount() {
		return courCount;
	}

	public void setCourCount(int courCount) {
		this.courCount = courCount;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCollectSign() {
		return collectSign;
	}

	public void setCollectSign(String collectSign) {
		this.collectSign = collectSign;
	}

}
