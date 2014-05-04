/**
 * CustomerDisableException.java	  V1.0   2014-4-22 上午9:53:17
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.exception;

public class CustomerDisableException extends BaseException{
	   /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4090337483376842992L;
	public static final int errorCode = 1040;
	 
		public CustomerDisableException(String msg) {
				super(msg);
				this.setCode(errorCode);
		}

		public CustomerDisableException(Throwable ex) {		
			super(ex);
			this.setCode(errorCode);
		}
		
		public CustomerDisableException(String msg, Throwable ex) {
			super(msg,ex);
			this.setCode(errorCode);
		}




}
