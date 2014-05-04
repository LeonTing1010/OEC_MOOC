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

import com.gta.oec.vo.BaseVO;

public class JobAuthenticationVo extends BaseVO {

	private static final long serialVersionUID = -4848844141037374915L;
	
	
	/**岗位ID**/
	private int jobID;	
	/**认证ID**/
	private int authenticationID;
	
	/**认证名称**/
	private String authenticationName;
	/**认证图片ID**/
	private int imageID;
	
	/**认证图片地址**/
	private String imageUrl;
	
	/**岗位推荐认证**/
	private int jobAuthRecommend;
	

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getAuthenticationID() {
		return authenticationID;
	}

	public void setAuthenticationID(int authenticationID) {
		this.authenticationID = authenticationID;
	}

	public String getAuthenticationName() {
		return authenticationName;
	}

	public void setAuthenticationName(String authenticationName) {
		this.authenticationName = authenticationName;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getJobAuthRecommend() {
		return jobAuthRecommend;
	}

	public void setJobAuthRecommend(int jobAuthRecommend) {
		this.jobAuthRecommend = jobAuthRecommend;
	}
	
}
