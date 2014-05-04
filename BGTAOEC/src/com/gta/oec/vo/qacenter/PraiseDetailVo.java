
package com.gta.oec.vo.qacenter;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 赞明细实体类
 * @author 刘祚家
 *
 */
public class PraiseDetailVo extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358976271471277750L;
	
	/**赞的明细ID**/
	private int praiID;
	
	/**回答ID**/
	private int answID;
	/**用户ID**/
	private int userID;
	
	/**创建时间**/
	private Date praiCtime;
	/**修改时间**/
	private Date praiUtime;
	
	public int getPraiID() {
		return praiID;
	}
	public void setPraiID(int praiID) {
		this.praiID = praiID;
	}
	
	public int getAnswID() {
		return answID;
	}
	public void setAnswID(int answID) {
		this.answID = answID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getPraiCtime() {
		return praiCtime;
	}
	public void setPraiCtime(Date praiCtime) {
		this.praiCtime = praiCtime;
	}
	public Date getPraiUtime() {
		return praiUtime;
	}
	public void setPraiUtime(Date praiUtime) {
		this.praiUtime = praiUtime;
	}

	
}
