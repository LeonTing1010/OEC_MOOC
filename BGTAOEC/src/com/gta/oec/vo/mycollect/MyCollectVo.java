/**
 * myCollect.java	  V1.0   2014-1-21 下午1:23:01
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.mycollect;

/**
 * 
 * 功能描述：我的收藏
 *
 * @author  yangyang.ou
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class MyCollectVo {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5662788919785452367L;
	/**收藏id**/
	private int collectId;
	/**关联id**/
	private int refId;
	/**收藏类型**/
	private int collectType;
	/**用户id**/
	private int userId;

	public int getCollectId() {
		return collectId;
	}
	public void setCollectId(int collectId) {
		this.collectId = collectId;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public int getCollectType() {
		return collectType;
	}
	public void setCollectType(int collectType) {
		this.collectType = collectType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
