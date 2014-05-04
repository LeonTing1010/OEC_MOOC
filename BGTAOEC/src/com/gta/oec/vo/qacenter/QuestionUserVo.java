
package com.gta.oec.vo.qacenter;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;

/**
 * 指定回答老师实体类
 * @author 刘祚家
 *
 */
public class QuestionUserVo extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;
	
	/**流水号**/
	private int id;
	/**问题ID**/
	private int quesID;
	/**用户ID**/
	private int teacherID;
	/**状态**/
	private int status;
	
	/**用户名称 **/
	private String userName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuesID() {
		return quesID;
	}
	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}
	
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
