/**
 * Course.java	  V1.0   2014-3-18 上午11:18:02
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.course;

import java.util.Date;
import java.util.List;

import com.gta.oec.cms.vo.BaseValObject;

public class Course extends BaseValObject {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 课程ID **/
	private int courseId;
	/** 课程名称 **/
	private String courseName;
	/** 课程编号 **/
	private String courNum;
	/** 老师ID **/
	private int userId;
	/** 授课老师名字 **/
	private String teacherName;
	/** 学校ID **/
	private int schoolId;
	/** 学校名字 **/
	private String schoolName;
	/** 开课时间 **/
	private Date courStartTime;
	/** 结课时间 **/
	private Date courEndTime;
	/** 课程周期 **/
	private int courWeek;
	/** 课程编号 **/
	private String courseNum;
	/** 课程简介 **/
	private String introduction;
	/** 课程目标 **/
	private String tag;
	/** 课程封面 **/
	private String titlePage;
	/** 公开标识 **/
	private String isPublic;
	/** 课程密码 **/
	private String password;
	/** 视频类型 : 1 -直播 2-点播 **/
	private int liveType;
	/** 点播标示 **/
	private String isDibbling;
	/** 课时 **/
	private int courseClass;
	/** 课程学分 **/
	private float credit;
	/** 已购买人数 **/
	private String buyCount;
	/** 已关注人数 **/
	private String attention;
	/** 课程价格 **/
	private float price;
	/** 记录创建时间 **/
	private Date cTime;
	/** 记录修改时间 **/
	private Date uTime;
	/** 审核状态 0 未发布 1 已发布/待审核 2 审核通过 **/
	private int status;
	/** 预览视频URL **/
	private String previewUrl;
	/** 评分方法 **/
	private String scoreMethod;
	/** 课程级别 **/
	private int courLevel;
	/** 章列表 **/
	private List<CourseDetail> sectionList;
	/** 用户名 **/
	private String userName;
	/**岗位id**/
	private int jobId;
	/** 岗位名称 **/
	private String jobName;
	/**岗位群id**/
	private int jobPid;
	/**职业id**/
	private int proId;
	/**职业名称**/
	private String proName;
	/**岗位推荐课程**/
	private int jobCourRecommend;

	public int getJobCourRecommend() {
		return jobCourRecommend;
	}

	public void setJobCourRecommend(int jobCourRecommend) {
		this.jobCourRecommend = jobCourRecommend;
	}

	public int getJobPid() {
		return jobPid;
	}

	public void setJobPid(int jobPid) {
		this.jobPid = jobPid;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<CourseDetail> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<CourseDetail> sectionList) {
		this.sectionList = sectionList;
	}

	public int getCourLevel() {
		return courLevel;
	}

	public void setCourLevel(int courLevel) {
		this.courLevel = courLevel;
	}

	public String getIsDibbling() {
		return isDibbling;
	}

	public void setIsDibbling(String isDibbling) {
		this.isDibbling = isDibbling;
	}

	public String getCourNum() {
		return courNum;
	}

	public void setCourNum(String courNum) {
		this.courNum = courNum;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Date getCourStartTime() {
		return courStartTime;
	}

	public void setCourStartTime(Date courStartTime) {
		this.courStartTime = courStartTime;
	}

	public Date getCourEndTime() {
		return courEndTime;
	}

	public void setCourEndTime(Date courEndTime) {
		this.courEndTime = courEndTime;
	}

	public int getCourWeek() {
		return courWeek;
	}

	public void setCourWeek(int courWeek) {
		this.courWeek = courWeek;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitlePage() {
		return titlePage;
	}

	public void setTitlePage(String titlePage) {
		this.titlePage = titlePage;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLiveType() {
		return liveType;
	}

	public void setLiveType(int liveType) {
		this.liveType = liveType;
	}

	public int getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(int courseClass) {
		this.courseClass = courseClass;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public String getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(String buyCount) {
		this.buyCount = buyCount;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getScoreMethod() {
		return scoreMethod;
	}

	public void setScoreMethod(String scoreMethod) {
		this.scoreMethod = scoreMethod;
	}

}
