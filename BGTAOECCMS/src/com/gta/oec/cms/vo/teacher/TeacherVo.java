/**
 * teacherVo.java	  V1.0   2014年4月2日 下午8:52:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.vo.teacher;

import com.gta.oec.cms.vo.school.School;
import com.gta.oec.cms.vo.user.User;

/**
 * 功能描述：teacher vo.继承级uservo类.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class TeacherVo extends User {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9085222903754739062L;
	private int idOfTeacher;
	private String levelOfTeacher;
	private String introduceOfTeacher;
	private int isRecommendedTeacher;
	private int schoolIdOfTeacher;
	private String majorOfTeacher;

	private School schoolOfTeacher;

	/**
	 * @return the idOfTeacher
	 */
	public int getIdOfTeacher() {
		return this.idOfTeacher;
	}

	/**
	 * @param idOfTeacher the idOfTeacher to set
	 */
	public void setIdOfTeacher(int idOfTeacher) {
		this.idOfTeacher = idOfTeacher;
	}

	/**
	 * @return the levelOfTeacher
	 */
	public String getLevelOfTeacher() {
		return this.levelOfTeacher;
	}

	/**
	 * @param levelOfTeacher the levelOfTeacher to set
	 */
	public void setLevelOfTeacher(String levelOfTeacher) {
		this.levelOfTeacher = levelOfTeacher;
	}

	/**
	 * @return the introduceOfTeacher
	 */
	public String getIntroduceOfTeacher() {
		return this.introduceOfTeacher;
	}

	/**
	 * @param introduceOfTeacher the introduceOfTeacher to set
	 */
	public void setIntroduceOfTeacher(String introduceOfTeacher) {
		this.introduceOfTeacher = introduceOfTeacher;
	}

	/**
	 * @return the isRecommendedTeacher
	 */
	public int getIsRecommendedTeacher() {
		return this.isRecommendedTeacher;
	}

	/**
	 * @param isRecommendedTeacher the isRecommendedTeacher to set
	 */
	public void setIsRecommendedTeacher(int isRecommendedTeacher) {
		this.isRecommendedTeacher = isRecommendedTeacher;
	}

	/**
	 * @return the schoolIdOfTeacher
	 */
	public int getSchoolIdOfTeacher() {
		return this.schoolIdOfTeacher;
	}

	/**
	 * @param schoolIdOfTeacher the schoolIdOfTeacher to set
	 */
	public void setSchoolIdOfTeacher(int schoolIdOfTeacher) {
		this.schoolIdOfTeacher = schoolIdOfTeacher;
	}

	/**
	 * @return the majorOfTeacher
	 */
	public String getMajorOfTeacher() {
		return this.majorOfTeacher;
	}

	/**
	 * @param majorOfTeacher the majorOfTeacher to set
	 */
	public void setMajorOfTeacher(String majorOfTeacher) {
		this.majorOfTeacher = majorOfTeacher;
	}

	/**
	 * @return the schoolOfTeacher
	 */
	public School getSchoolOfTeacher() {
		return this.schoolOfTeacher;
	}

	/**
	 * @param schoolOfTeacher the schoolOfTeacher to set
	 */
	public void setSchoolOfTeacher(School schoolOfTeacher) {
		this.schoolOfTeacher = schoolOfTeacher;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
