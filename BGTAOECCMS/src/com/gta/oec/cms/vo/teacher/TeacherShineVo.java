package com.gta.oec.cms.vo.teacher;

import com.gta.oec.cms.vo.BaseValObject;


public class TeacherShineVo extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7329063738524561732L;
	/**
	 * 教师管理行业岗位信息 
	 */
	private int id;
	private Integer userid;
	private Integer proid;
	private Integer jobid;
	private String remark;

	private String jobName;
	private String professionName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public Integer getJobid() {
		return jobid;
	}
	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
 
}