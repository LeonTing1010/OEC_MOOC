package com.gta.oec.cms.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;

/**
 * @description Application Exception
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-24 11:38:00
 * 
 * */
public class AppExceiption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String code_Msg;
	
	private Throwable cause;

	public AppExceiption(String msg) {
		super(msg);
	}
	
	public AppExceiption(Throwable ex){
		this.cause = ex;
	}

	public AppExceiption(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}
	
	public AppExceiption(String code,String msg,Throwable ex){
		this(msg, ex);
		this.code = code;
	}
	
	public AppExceiption(String code,String codeMsg,String msg,Throwable ex){
		this(code,msg,ex);
		this.code_Msg = codeMsg;
	}

	public String getCode_Msg() {
		return code_Msg;
	}

	public void setCode_Msg(String code_Msg) {
		this.code_Msg = code_Msg;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Throwable getCause() {
		return (this.cause == null ? null : this.cause);
	}

	public String getAllMessage(){
		String message = this.getMessage();
		String code = getCode();
		Throwable cause = getCause();
		if (cause != null) {
			message = message + "; nested exception is is " + cause;
		}
		if(StringUtils.isNotEmpty(code)){
			message = message +"; nested exception is code is : " + code;
		}
		if(StringUtils.isNotEmpty(code_Msg)){
			message = message + "; nested exception is msg is : " + code_Msg;
		}
		return message;
	}

	public void printStackTrace(PrintStream ps) {
		if (getCause() == null) {
			super.printStackTrace(ps);

		} else {
			ps.println(this);
			getCause().printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		if (getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			getCause().printStackTrace(pw);
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

}
