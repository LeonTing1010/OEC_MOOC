/**
 * BaseException.java	  V1.0   2013-12-30 ����8:53:15
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

/**
 * 
 * 功能描述：基础异常类
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = -802376082358572333L;

	private int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	///private Throwable cause;

	
	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(Throwable ex) {		
		super(ex);
		
	}
	
	public BaseException(String msg, Throwable ex) {
		super(msg,ex);
		//this.cause = ex;
	}

	public Throwable getCause() {
		return super.getCause();
	}

	public String getMessage() {
		if (super.getCause() == null) {
			return super.getMessage();
		} else {
			return super.getMessage() + "; nested exception is "
					+ super.getCause().getClass().getName() + ": "
					+ super.getCause().getMessage();
		}
	}

	public void printStackTrace(PrintStream ps) {
		if (super.getCause() == null) {
			super.printStackTrace(ps);
		} else {
			ps.println(this);
			super.getCause().printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		if (super.getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			super.getCause().printStackTrace(pw);
		}
	}
	
	
}
