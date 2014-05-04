package com.gta.oec.vo.teacher;

import com.gta.oec.vo.BaseVO;

public class TeacherShineVo extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7329063738524561732L;
	/**
	 * 教师管理行业岗位信息 Add By:缪佳 Add Date:2014-01-22
	 */
	private int id;
	private int userid;
	private int proid;
	private int jobid;
	private String remark;

	private String jobName;
	private String professionName;

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProfessionName() {
		return this.professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProid() {
		return proid;
	}

	public void setProid(int proid) {
		this.proid = proid;
	}

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}