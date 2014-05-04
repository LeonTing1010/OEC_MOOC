/**
 * TeacherShineItemVo.java	  V1.0   2014年3月7日 上午9:53:27
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.job;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.profession.ProfessionVo;

public class TeacherShineItemVo extends BaseVO {

	public static final int ON_LEFT = 0;
	public static final int ON_RIGHT = 1;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -371043726081721328L;
	private JobVo jobVo;
	private int position;
	private ProfessionVo professionVo;
	private int userId;

	public JobVo getJobVo() {
		return this.jobVo;
	}

	public void setJobVo(JobVo jobVo) {
		this.jobVo = jobVo;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ProfessionVo getProfessionVo() {
		return this.professionVo;
	}

	public void setProfessionVo(ProfessionVo professionVo) {
		this.professionVo = professionVo;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
