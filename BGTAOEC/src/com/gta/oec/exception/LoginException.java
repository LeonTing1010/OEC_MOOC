/**
 * NotLoginException.java	  V1.0   2013-12-30 ����8:55:32
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.exception;


/**
 * 
 * 功能描述：未登录
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LoginException extends BaseException{
   public static final int errorCode = 1000;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2168718814045335116L;
	   
	public LoginException(String msg) {
			super(msg);
			this.setCode(errorCode);
	}

	public LoginException(Throwable ex) {		
		super(ex);
		this.setCode(errorCode);
	}
	
	public LoginException(String msg, Throwable ex) {
		super(msg,ex);
		this.setCode(errorCode);
	}
}
