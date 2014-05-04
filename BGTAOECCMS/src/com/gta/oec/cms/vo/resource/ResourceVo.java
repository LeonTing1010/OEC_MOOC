/**
 * ResourceVo.java	  V1.0   2014年4月8日 下午2:06:02
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.cms.vo.resource;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

/**
 * 功能描述：ResourceVo.资源类vo.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class ResourceVo extends BaseValObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5453451891596991379L;
	/**
	 * resourceId 资源ID
	 */
	private int resourceId;
	/**
	 * resourceURL资源URL
	 */
	private String resourceURL;
	/**
	 * resourceName资源名称
	 */
	private String resourceName;
	/**
	 * isDownload下载标识.
	 */
	private String isDownload;
	/**
	 * resourceType资源类型: 1 主视频 2 辅助资源
	 */
	private String resourceType;
	/**
	 * createTime资源创建时间.
	 */
	private Date createTime;
	/**
	 * updateTime资源更新时间.
	 */
	private Date updateTime;

	// //******以下不是本课程资源关系表的属性*******///
	/**
	 * courseId课程id
	 */
	private int courseId;
	/**
	 * sectionId章节id
	 */
	private int sectionId;

	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return this.resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return this.resourceName;
	}

	/**
	 * @param resourceName
	 *            the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the isDownload
	 */
	public String getIsDownload() {
		return this.isDownload;
	}

	/**
	 * @param isDownload
	 *            the isDownload to set
	 */
	public void setIsDownload(String isDownload) {
		this.isDownload = isDownload;
	}

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return this.resourceType;
	}

	/**
	 * @param resourceType
	 *            the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the sectionId
	 */
	public int getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId
	 *            the sectionId to set
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @return the resourceURL
	 */
	public String getResourceURL() {
		return resourceURL;
	}

	/**
	 * @param resourceURL
	 *            the resourceURL to set
	 */
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
}
