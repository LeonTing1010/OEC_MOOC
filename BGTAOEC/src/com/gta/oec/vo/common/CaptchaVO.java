/**
 * CaptchaVO.java	  V1.0   2014-1-2 下午2:17:30
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.common;

import java.awt.image.BufferedImage;
import java.util.Date;


import com.gta.oec.vo.BaseVO;
/**
 *
 * 功能描述：验证码
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class CaptchaVO extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3139117323917769223L;
	/** 键 **/
	private String key;
	/** 验证码值 **/
	private String captchaValue;
	/** 验证码图片 **/
	private BufferedImage image;
    private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
