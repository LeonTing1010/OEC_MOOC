/**
 * BaseException.java	  V1.0   2014-4-11 下午4:31:34
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.exception;

/**
 * @description BaseException
 * @author jianhua.huang[Bill Huang]
 * @since 2014-04-11 11:38:00
 * 
 * */
public class BaseException extends AppExceiption {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BaseException(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		if (super.getCause() == null) {
			return super.getMessage();
		} else {
			return super.getMessage() + "; nested exception is "
					+ super.getCause().getClass().getName() + ": "
					+ super.getCause().getMessage();
		}
	}

}
