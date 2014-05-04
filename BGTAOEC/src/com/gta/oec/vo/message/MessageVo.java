
package com.gta.oec.vo.message;


import java.util.Date;

import com.gta.oec.vo.BaseVO;


/**
 * 消息通知实体类
 * 功能描述：
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class MessageVo extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * serialVersionUID
	 */
	/**消息ID**/
	private int msID;
	/**消息类型**/
	private String msType;
	/**消息主题**/
	private String msTitle;
	/**消息内容**/
	private String msContent;
	/**记录时间**/
	private Date msCtime;
	
	public int getMsID() {
		return msID;
	}
	public void setMsID(int msID) {
		this.msID = msID;
	}
	public String getMsType() {
		return msType;
	}
	public void setMsType(String msType) {
		this.msType = msType;
	}
	public String getMsTitle() {
		return msTitle;
	}
	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String msContent) {
		this.msContent = msContent;
	}
	public Date getMsCtime() {
		return msCtime;
	}
	public void setMsCtime(Date msCtime) {
		this.msCtime = msCtime;
	}
	
}
