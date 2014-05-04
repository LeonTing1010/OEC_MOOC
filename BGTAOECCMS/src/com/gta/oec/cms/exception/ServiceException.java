package com.gta.oec.cms.exception;

/**
 * @description service Exception
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-24 11:38:00
 * 
 * */
public class ServiceException extends AppExceiption {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
		super(msg);
		this.setCode(ExceptionConstants.CODE_0100);
		this.setCode_Msg(ExceptionConstants.MSG_0100);
	}

	public ServiceException(Throwable ex){
		super(ex);
		this.setCode(ExceptionConstants.CODE_0100);
		this.setCode_Msg(ExceptionConstants.MSG_0100);
	}

	public ServiceException(String msg, Throwable ex) {
		super(ExceptionConstants.CODE_0100,msg, ex);
		this.setCode_Msg(ExceptionConstants.MSG_0100);
	}
	
	public ServiceException(String code,String msg,Throwable ex){
		super(code,ExceptionConstants.MSG_0100, msg, ex);
	}
}
