
package com.gta.oec.vo.qacenter;

import com.gta.oec.vo.BaseVO;

/**
 * 关注问题实体类
 * @author 刘祚家
 *
 */
public class AttentionVo extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;

	/**ID流水号**/
	private int id;
	/**问题ID**/
	private int quesID;
	/**用户ID**/
	private int userID;
	/**关注问题状态：0-未读，1-已读**/
	private int status;
	
	
	/**关注数**/
	private int attentionNum;
	/**标识变为：已关注、关注问题**/
	private int flag;
	
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

}
