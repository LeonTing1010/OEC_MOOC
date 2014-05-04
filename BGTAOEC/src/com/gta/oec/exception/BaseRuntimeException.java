/**
 * BaseRuntimeException.java	  V1.0   2014-1-4 下午4:07:06
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseRuntimeException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5588403161479993319L;
	private Throwable cause;

	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(Throwable ex) {		
		this.cause = ex;
	}
	
	public BaseRuntimeException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}

	public Throwable getCause() {
		return cause;
	}

	public String getMessage() {
		if (this.cause == null) {
			return super.getMessage();
		} else {
			return super.getMessage() + "; nested exception is "
					+ this.cause.getClass().getName() + ": "
					+ this.cause.getMessage();
		}
	}

	public void printStackTrace(PrintStream ps) {
		if (this.cause == null) {
			super.printStackTrace(ps);
		} else {
			ps.println(this);
			this.cause.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		if (this.cause == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			this.cause.printStackTrace(pw);
		}
	}
}
