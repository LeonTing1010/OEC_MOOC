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

import java.util.Date;

import com.gta.oec.vo.BaseVO;

public class CourseJobVo extends BaseVO {

	private static final long serialVersionUID = -4848844141037374915L;
	
	/**主键ID**/
	private int id;
	/**岗位ID**/
	private int jobID;	
	/**行业ID**/
	private int proId;
	/**课程ID**/
	private int courseID;
	
	/**课程名称**/
	private String courseName;
	/**课程图片**/
	private String courseTitlePage;
	
	/**岗位推荐课程**/
	private int courseJobRecommend;
	
	/**授课老师ID**/
	private int userID;
	/**授课老师姓名**/
	private String userName;
	
	/**开课时间**/
	private Date courseStime;
	
	/**浏览次数**/
	private int courseAttention;
	
	/**行业名称**/
	private String proName;
	
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTitlePage() {
		return courseTitlePage;
	}
	public void setCourseTitlePage(String courseTitlePage) {
		this.courseTitlePage = courseTitlePage;
	}
	public int getCourseJobRecommend() {
		return courseJobRecommend;
	}
	public void setCourseJobRecommend(int courseJobRecommend) {
		this.courseJobRecommend = courseJobRecommend;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public Date getCourseStime() {
		return courseStime;
	}
	public void setCourseStime(Date courseStime) {
		this.courseStime = courseStime;
	}
	public int getCourseAttention() {
		return courseAttention;
	}
	public void setCourseAttention(int courseAttention) {
		this.courseAttention = courseAttention;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
}
