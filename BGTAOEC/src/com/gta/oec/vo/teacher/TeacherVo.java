/**
 * UserVo.java	  V1.0   2013-12-30 ����9:19:03
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.teacher;

import java.util.List;

import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：老师实体类
 * 
 * @author jin.zhang
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class TeacherVo extends UserVo {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5575160334226487650L;
	/**
	 * serialVersionUID
	 */
	/** 主键ID **/
	private int id;
	/** 学校 **/
	private String school;
	/** 岗位群id **/
	private int jobGroupId;
	/** 岗位id **/
	private int jobId;
	/** 主讲课程 **/
	private String course;
	/** 专业 **/
	private String major;
	/** 介绍 **/
	private String introduce;
	/** 教师学历 **/
	private String teacherLevel;

	/****************************** 以下非老师表属性 ******************/
	private List<CourseVo> courseList;
	private String teacherName;
	private String jobGroupName;
	private String teacherPic;
	private ProfessionVo professionVo;
	private TeacherShineVo teacherShineVo;
	/** 岗位 **/
	private String job;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getJobGroupId() {
		return jobGroupId;
	}

	public void setJobGroupId(int jobGroupId) {
		this.jobGroupId = jobGroupId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getTeacherPic() {
		return teacherPic;
	}

	public void setTeacherPic(String teacherPic) {
		this.teacherPic = teacherPic;
	}

	public ProfessionVo getProfessionVo() {
		return professionVo;
	}

	public void setProfessionVo(ProfessionVo professionVo) {
		this.professionVo = professionVo;
	}

	public List<CourseVo> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseVo> courseList) {
		this.courseList = courseList;
	}

	public TeacherShineVo getTeacherShineVo() {
		return teacherShineVo;
	}

	public void setTeacherShineVo(TeacherShineVo teacherShineVo) {
		this.teacherShineVo = teacherShineVo;
	}
}
