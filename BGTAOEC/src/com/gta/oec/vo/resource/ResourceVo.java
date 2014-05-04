/**
 * SourceVo.java	  V1.0   2014年1月9日 下午1:41:58
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.resource;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：资源
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class ResourceVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -180980981796573125L;
	/**资源ID**/
	private int reSourceID;
	/**资源URL**/
	private String dataUrl;
	/****资源名称*****/
	private String resourseName;
	/**下载标识**/
	private String dataIsdownload;
	/**资源类型: 1 主视频  2 辅助资源 **/
	private String sourceType;
	/**创建时间**/
	private Date reSourCtime;
	/**修改时间**/
	private Date reSourUtime;
	/**课程ID**/
	private int courseID;
	/**章节ID**/
	private int secID;
	/**课程资源关联ID**/
	private int courReID;
	/**课程资源创建时间**/
	private Date courReCtime;
	/**课程资源修改时间**/
	private Date courReUtime;
	
	public int getReSourceID() {
		return reSourceID;
	}
	public void setReSourceID(int reSourceID) {
		this.reSourceID = reSourceID;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getDataIsdownload() {
		return dataIsdownload;
	}
	public void setDataIsdownload(String dataIsdownload) {
		this.dataIsdownload = dataIsdownload;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public Date getReSourCtime() {
		return reSourCtime;
	}
	public void setReSourCtime(Date reSourCtime) {
		this.reSourCtime = reSourCtime;
	}
	public Date getReSourUtime() {
		return reSourUtime;
	}
	public void setReSourUtime(Date reSourUtime) {
		this.reSourUtime = reSourUtime;
	}
	public int getSecID() {
		return secID;
	}
	public void setSecID(int secID) {
		this.secID = secID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getCourReID() {
		return courReID;
	}
	public void setCourReID(int courReID) {
		this.courReID = courReID;
	}
	public Date getCourReCtime() {
		return courReCtime;
	}
	public void setCourReCtime(Date courReCtime) {
		this.courReCtime = courReCtime;
	}
	public Date getCourReUtime() {
		return courReUtime;
	}
	public void setCourReUtime(Date courReUtime) {
		this.courReUtime = courReUtime;
	}
	public String getResourseName() {
		return resourseName;
	}
	public void setResourseName(String resourseName) {
		this.resourseName = resourseName;
	}
	
}
