/**
 * TradeVo.java	  V1.0   2014-1-9 下午4:21:14
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.trade;

import java.util.List;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.job.JobVo;

public class TradeVo extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/** 行业ID ***/
	private int proId;
	/** 行业名称 ***/
	private String Name;
	/** 行业描述 ***/
	private String description;
	/** 创建时间 ***/
	private String cTime;
	/** 修改时间 ***/
	private String uTime;
	/** 是否热门**/
	private int recommend;
	/**岗位群**/
    private List<JobVo> list;
	
	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getuTime() {
		return uTime;
	}

	public void setuTime(String uTime) {
		this.uTime = uTime;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public List<JobVo> getList() {
		return list;
	}

	public void setList(List<JobVo> list) {
		this.list = list;
	}

}
