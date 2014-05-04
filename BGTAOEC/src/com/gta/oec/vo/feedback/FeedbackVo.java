package com.gta.oec.vo.feedback;

import java.util.Date;

import com.gta.oec.vo.BaseVO;

/**
 * 功能描述:意见反馈
 *
 * @author xin.yi
 *
 * <p>2014-4-23 下午1:43:05<p>
 *
 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
public class FeedbackVo extends BaseVO{

	private static final long serialVersionUID = 6988332442321L;
	
	/**意见反馈id**/
	private int feeId;
	/**标题**/
	private String feeTitle;
	/**内容**/
	private String feeContent;
	/**邮箱**/
	private String feeEmail;
	/**反馈时间**/
	private Date feeCtime;
	/**反馈来源**/
	private String feeSource;
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public String getFeeTitle() {
		return feeTitle;
	}
	public void setFeeTitle(String feeTitle) {
		this.feeTitle = feeTitle;
	}
	public String getFeeContent() {
		return feeContent;
	}
	public void setFeeContent(String feeContent) {
		this.feeContent = feeContent;
	}
	public String getFeeEmail() {
		return feeEmail;
	}
	public void setFeeEmail(String feeEmail) {
		this.feeEmail = feeEmail;
	}
	public Date getFeeCtime() {
		return feeCtime;
	}
	public void setFeeCtime(Date feeCtime) {
		this.feeCtime = feeCtime;
	}
	public String getFeeSource() {
		return feeSource;
	}
	public void setFeeSource(String feeSource) {
		this.feeSource = feeSource;
	}

}
