package com.gta.oec.cms.vo.user;

import java.util.Date;

import com.gta.oec.cms.vo.BaseValObject;

public class User extends BaseValObject {
	public static final int STUDENT_USER = 1;
	public static final int TEACHER_USER = 2;
	public static final int ADMIN_USER = 3;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/** 用户ID **/
	private Integer userId;
	/** 角色ID **/
	private int roleID;
	/** 用户姓名 **/
	private String userName;
	/** 用户手机号码 **/
	private String userMobile;
	/** 用户邮箱 **/
	private String userEmail;
	/** 用户密码 **/
	private String password;
	/** 用户类型 **/
	private Integer userType;
	/** 用户头像 **/
	private String userHeadPic;
	/** 用户登录信息记录 **/
	private String userLoginInfo;
	/** 跟新用户重设的密码Code **/
	private String userResetPwdCode;

	// add by can.xie createdate:2014.3.19 start .

	/** 确认密码 **/
	private String qcPassword;

	/**
	 * 会员状态，
	 */
	private Integer userState ;
	/**
	 * 创建时间
	 */
	private Date createDate;
	private Date endDate;

	/**
	 * 是否推荐答疑
	 */
	private Integer isRecommended;

	/**
	 * 学生学历
	 * 
	 */
	private String education;

	/**
	 * 老师 学校
	 * 
	 * @return
	 */
	private String schName;
	private String schId;
	/**
	 * 老师职称
	 * @return
	 */
	private String teacherLevel;
	/**
	 * 老师专业
	 * @return
	 */
	private String major;
	private String userImgUrl;
	
	/**
	 * 个人介绍
	 * 
	 */
	private String usIntroduce;
	// end
	/**
	 * 行业岗位
	 */
	private String jobGroupIdsInput;

	public String getJobGroupIdsInput() {
		return jobGroupIdsInput;
	}

	public void setJobGroupIdsInput(String jobGroupIdsInput) {
		this.jobGroupIdsInput = jobGroupIdsInput;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserImgUrl() {
		return userImgUrl;
	}

	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsIntroduce() {
		return usIntroduce;
	}

	public void setUsIntroduce(String usIntroduce) {
		this.usIntroduce = usIntroduce;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserHeadPic() {
		return userHeadPic;
	}

	public void setUserHeadPic(String userHeadPic) {
		this.userHeadPic = userHeadPic;
	}

	public String getUserLoginInfo() {
		return userLoginInfo;
	}

	public void setUserLoginInfo(String userLoginInfo) {
		this.userLoginInfo = userLoginInfo;
	}

	public String getUserResetPwdCode() {
		return userResetPwdCode;
	}

	public void setUserResetPwdCode(String userResetPwdCode) {
		this.userResetPwdCode = userResetPwdCode;
	}

	 

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getQcPassword() {
		return qcPassword;
	}

	public void setQcPassword(String qcPassword) {
		this.qcPassword = qcPassword;
	}


	public String getSchId() {
		return schId;
	}

	public void setSchId(String schId) {
		this.schId = schId;
	}

	public String getTeacherLevel() {
		return teacherLevel;
	}

	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
