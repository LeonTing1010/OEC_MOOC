/**
 * CourseVo.java	  V1.0   2013-12-27 ����10:31:55
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.course;

import java.util.Date;
import java.util.List;

import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.common.ExamStuInfoVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.qacenter.QuestionVo;
/**
 * 
 * 功能描述：课程
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class CourseVo extends BaseVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4607274924024500146L;
	/**课程ID**/
	private int courseId;
	/**课程名称**/
	private String courseName;
	/**用户ID**/
	private int userId;
	/**授课老师名字**/
	private String teacherName;
	/**学校ID**/
	private int schoolID;
	/**学校名字**/
	private String schoolName;
	/**开课时间**/
	private Date courStartTime;
	/**结课时间**/
	private Date courEndTime;
	/**课时**/
	private int courWeek;
	/**课程编号**/
	private String courseNum;
	/**课程简介**/
	private String introduction;
	/**课程目标**/
	private String tag;
	/**课程封面**/
	private String titlePage;
	/**公开标识**/
	private String isPublic;
	/**课程密码**/
	private String password;
	/**视频类型 : 1 -直播 2-点播**/
	private int liveType;
	/**课程周期**/
	private int courseClass;
	/**课程学分**/
	private float credit;
	/**已购买人数**/
	private String buyCount;
	/**已关注人数**/
	private String attention;
	/**课程价格**/
	private float price;
	/**记录创建时间**/
	private Date cTime;
	/**记录修改时间**/
	private Date uTime;
	/**课程状态   1.未发布 2.已发布 3.审核中（待审核） 4.审核未通过 5.已结束**/	 
	private int status;
	/**预览视频URL**/
	private String previewUrl;
	/**评分方法**/
	private String scoreMethod;
	/**学习进度**/
	private int progress;
	/**章节列表**/
	private List<SectionVO> list;
	/**问题列表**/
	private List<QuestionVo> queslist;
	/**学生考试列表**/
	private List<ExamStudentVo> examStudentlist;
	/**(考试/作业)列表1**/
	private List<ExamVo> examList;
	/**岗位名称**/
	private String jobName;
	/**岗位id**/
	private int jobId;
	/**岗位列表**/
	private List<CourseVo> courlist;
	/**收藏id**/
	private int collectId;
	/**(考试/作业)列表2**/
	private List<ExamVo> opList;
	/**距离开课相差天数**/
	private int diffDay;
	/**距离开课相差小时数**/
	private int diffHour;
	/**距离开课相差分钟数**/
	private int diffMin;
	/**距离开课相差的毫秒数**/
	private long diffTime;
	
	/**被浏览次数**/
	private long courseAttention = 0;
	
	/******************学生考试作业列表信息实体类集合（引入的类）**/
	private List<ExamStuInfoVo> examStuInfoList;

	public List<ExamStudentVo> getExamStudentlist() {
		return examStudentlist;
	}
	public void setExamStudentlist(List<ExamStudentVo> examStudentlist) {
		this.examStudentlist = examStudentlist;
	}

	public List<SectionVO> getList() {
		return list;
	}
	public void setList(List<SectionVO> list) {
		this.list = list;
	}
	public List<QuestionVo> getQueslist() {
		return queslist;
	}
	public void setQueslist(List<QuestionVo> queslist) {
		this.queslist = queslist;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	public int getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
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
	public String getScoreMethod() {
		return scoreMethod;
	}
	public void setScoreMethod(String scoreMethod) {
		this.scoreMethod = scoreMethod;
	}

	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public List<ExamVo> getExamList() {
		return examList;
	}
	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}
	public List<CourseVo> getCourlist() {
		return courlist;
	}
	public void setCourlist(List<CourseVo> courlist) {
		this.courlist = courlist;
	}
	public int getCollectId() {
		return collectId;
	}
	public void setCollectId(int collectId) {
		this.collectId = collectId;
	}
	public List<ExamVo> getOpList() {
		return opList;
	}
	public void setOpList(List<ExamVo> opList) {
		this.opList = opList;
	}
	public List<ExamStuInfoVo> getExamStuInfoList() {
		return examStuInfoList;
	}
	public void setExamStuInfoList(List<ExamStuInfoVo> examStuInfoList) {
		this.examStuInfoList = examStuInfoList;
	}
	public int getDiffDay() {
		return diffDay;
	}
	public void setDiffDay(int diffDay) {
		this.diffDay = diffDay;
	}
	public int getDiffHour() {
		return diffHour;
	}
	public void setDiffHour(int diffHour) {
		this.diffHour = diffHour;
	}
	public int getDiffMin() {
		return diffMin;
	}
	public void setDiffMin(int diffMin) {
		this.diffMin = diffMin;
	}
	public long getDiffTime() {
		return diffTime;
	}
	public void setDiffTime(long diffTime) {
		this.diffTime = diffTime;
	}
	public long getCourseAttention() {
		return courseAttention;
	}
	public void setCourseAttention(long courseAttention) {
		this.courseAttention = courseAttention;
	}
}
