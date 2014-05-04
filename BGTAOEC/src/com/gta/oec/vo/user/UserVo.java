/**
 * UserVo.java	  V1.0   2013-12-30 ����9:19:03
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.user;

import java.util.Date;

import com.gta.oec.vo.BaseVO;
/**
 * 
 * 功能描述：用户管理的对象
 *
 * @author  Miaoj
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class UserVo extends BaseVO{
	public final int STATE_NORMAL = 1;
	public  final int STATE_DISABLE = 0;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6602861035585249006L;
	/**用户ID**/
	private Integer userId;
	/**角色ID**/
	private int roleID;
	/**用户姓名**/
	private String userName;
	/**用户手机号码**/
	private String userMobile;
	/**用户邮箱**/
	private String userEmail;
	/**用户密码**/
	private String password;
	/**用户类型**/
	private int userType;
	/**用户头像**/
	private String userHeadPic;
	/**是否老师**/
	private int isTeacher ;

	/** 创建时间*/
	private Date createDate;
	
	/**用户登录信息记录**/
	private String userLoginInfo;  
	/**跟新用户重设的密码Code**/
	private String userResetPwdCode;
	

	public String getUserResetPwdCode() {
		return userResetPwdCode;
	}
	public void setUserResetPwdCode(String userResetPwdCode) {
		this.userResetPwdCode = userResetPwdCode;
	}
		
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	//---------刘祚家---------//
	/**老师流水号**/
	private int id;
	/**老师专业**/
	private String major;
	/**老师等级职称**/
	private String teacherLevel;
	/**是否推荐答疑---0：否，1：是**/
	private int isRecommended;
	
	/**回答活跃老师回答数**/
	private int num;
	
	
	/**
	 * Add By liuli 
	 * 0 代表禁用
	 * 1 代表启用
	 * 
	 *  会员状态*/
	private Integer userState;

	public int getIsTeacher() {
		return isTeacher;
	}
	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}
	public String getUserLoginInfo() {
		return userLoginInfo;
	}
	public void setUserLoginInfo(String userLoginInfo) {
		this.userLoginInfo = userLoginInfo;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserHeadPic() {
		return userHeadPic;
	}
	public void setUserHeadPic(String userHeadPic) {
		this.userHeadPic = userHeadPic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTeacherLevel() {
		return teacherLevel;
	}
	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}
	public int getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(int isRecommended) {
		this.isRecommended = isRecommended;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}

}
