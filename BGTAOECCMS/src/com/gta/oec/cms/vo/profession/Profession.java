/**
 * Profession.java	  V1.0   2014-3-18 下午12:31:22
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gta.oec.cms.vo.BaseValObject;
import com.gta.oec.cms.vo.job.Job;

public class Profession extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 职业ID **/
	private Integer proID;
	/** 职业名称 **/
	private String proName;
	/** 职业描述 **/
	private String proDescription;
	/** 热门职业推荐 **/
	private int proRecommend;
	/** 创建时间 **/
	private Date proCtime;
	/** 修改时间 **/
	private Date proUtime;
	/**职业图片**/
	private String proImage;
	/**是否隐藏*/
	private short proIsVisible = 0;
	/**
	 * 行业下面的岗位信息
	 * @return
	 */
	private List<Job>  jobs=new ArrayList<Job>();
	/** 子岗位群 */
	private List<Profession> children;
	/** 岗位状态 */
	private String state = "closed";
	
	public short getProIsVisible() {
		return proIsVisible;
	}

	public void setProIsVisible(short proIsVisible) {
		this.proIsVisible = proIsVisible;
	}

	public String getProImage() {
		return proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}
	
	public String getState() {
		return state;
	}

	public Integer getProID() {
		return proID;
	}

	public void setProID(Integer proID) {
		this.proID = proID;
	}

	public List<Profession> getChildren() {
		return children;
	}

	public void setChildren(List<Profession> children) {
		this.children = children;
	}

	public void setState(String state) {
		this.state = state;
	}
 
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDescription() {
		return proDescription;
	}

	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}

	public int getProRecommend() {
		return proRecommend;
	}

	public void setProRecommend(int proRecommend) {
		this.proRecommend = proRecommend;
	}

	public Date getProCtime() {
		return proCtime;
	}

	public void setProCtime(Date proCtime) {
		this.proCtime = proCtime;
	}

	public Date getProUtime() {
		return proUtime;
	}

	public void setProUtime(Date proUtime) {
		this.proUtime = proUtime;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	
}
