/**
 * CourseJob.java	  V1.0   2014-3-18 下午1:08:48
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.course;

import com.gta.oec.cms.vo.BaseValObject;

public class CourseJob extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 主键ID **/
	private int id;
	/** 岗位ID **/
	private int jobID;
	/** 行业ID **/
	private int proId;
	/** 课程ID **/
	private int courseID;
	/** 岗位推荐课程 **/
	private int courseJobRecommend;
	/** 是否推送首页：0 不推送 1 推送 **/
	private int isIndex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getCourseJobRecommend() {
		return courseJobRecommend;
	}

	public void setCourseJobRecommend(int courseJobRecommend) {
		this.courseJobRecommend = courseJobRecommend;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
}
