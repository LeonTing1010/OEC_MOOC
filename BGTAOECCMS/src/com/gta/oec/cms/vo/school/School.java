/**
 * School.java	  V1.0   2014-3-21 上午11:04:51
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.school;

import com.gta.oec.cms.vo.BaseValObject;

public class School extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 学校id **/
	private int schId;
	/** 学校名字 **/
	private String schName;
	/** 学校简介 **/
	private String schDescription;
	/** 学校地址 **/
	private String schAddress;
	/** 学校网址 **/
	private String schWww;
	/** 学校logo **/
	private String schLogo;
	/** 生成时间 **/
	private String schCtime;
	/**
	 * @return the schId
	 */
	public int getSchId() {
		return schId;
	}
	/**
	 * @param schId the schId to set
	 */
	public void setSchId(int schId) {
		this.schId = schId;
	}
	/**
	 * @return the schName
	 */
	public String getSchName() {
		return schName;
	}
	/**
	 * @param schName the schName to set
	 */
	public void setSchName(String schName) {
		this.schName = schName;
	}
	/**
	 * @return the schDescription
	 */
	public String getSchDescription() {
		return schDescription;
	}
	/**
	 * @param schDescription the schDescription to set
	 */
	public void setSchDescription(String schDescription) {
		this.schDescription = schDescription;
	}
	/**
	 * @return the schAddress
	 */
	public String getSchAddress() {
		return schAddress;
	}
	/**
	 * @param schAddress the schAddress to set
	 */
	public void setSchAddress(String schAddress) {
		this.schAddress = schAddress;
	}
	/**
	 * @return the schWww
	 */
	public String getSchWww() {
		return schWww;
	}
	/**
	 * @param schWww the schWww to set
	 */
	public void setSchWww(String schWww) {
		this.schWww = schWww;
	}
	/**
	 * @return the schLogo
	 */
	public String getSchLogo() {
		return schLogo;
	}
	/**
	 * @param schLogo the schLogo to set
	 */
	public void setSchLogo(String schLogo) {
		this.schLogo = schLogo;
	}
	/**
	 * @return the schCtime
	 */
	public String getSchCtime() {
		return schCtime;
	}
	/**
	 * @param schCtime the schCtime to set
	 */
	public void setSchCtime(String schCtime) {
		this.schCtime = schCtime;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
