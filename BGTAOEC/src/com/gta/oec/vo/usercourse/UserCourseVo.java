package com.gta.oec.vo.usercourse;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.course.CourseVo;


/**
 * 用户课程实体类
 * 功能描述：
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class UserCourseVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6778309242373819692L;
	
	/**主键ID**/
	private int id;
	/**课程ID**/
	private int userId;
	/**课程ID**/
	private int courID;
	/**创建时间**/
	private String ctime;
	/**修改时间**/
	private String utime;
	/**学习进度**/
	private int  progress;
	
	/***************课程对象******************/
	private CourseVo courseVo;
	//课程考试的状态
	private int status;
	//1.已批阅 2.未批阅 3.未提交考试 4表示该课程暂无考试
	private int examId;
	//考试创建时间
	private Date examCtime;
	//考试得分
	private float examScore;
	//作业名称
	private String taskName;
	private int examStuId;
	/**课程**/
	private List<CourseVo> courList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourID() {
		return courID;
	}
	public void setCourID(int courID) {
		this.courID = courID;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public List<CourseVo> getCourList() {
		return courList;
	}
	public void setCourList(List<CourseVo> courList) {
		this.courList = courList;
	}
	public CourseVo getCourseVo() {
		return courseVo;
	}
	public void setCourseVo(CourseVo courseVo) {
		this.courseVo = courseVo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getExamStuId() {
		return examStuId;
	}
	public void setExamStuId(int examStuId) {
		this.examStuId = examStuId;
	}
	public float getExamScore() {
		return examScore;
	}
	public void setExamScore(float examScore) {
		this.examScore = examScore;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getExamCtime() {
		return examCtime;
	}
	public void setExamCtime(Date examCtime) {
		this.examCtime = examCtime;
	}
}
