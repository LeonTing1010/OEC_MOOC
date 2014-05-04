package com.gta.oec.cms.exception;

/**
 * @description Business Exception
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-24 11:38:00
 * 
 * */
public class BusinessException extends AppExceiption {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
		this.setCode(ExceptionConstants.CODE_0000);
		this.setCode_Msg(ExceptionConstants.MSG_0000);
	}
	
	public BusinessException(Throwable ex){
		super(ex);
		this.setCode(ExceptionConstants.CODE_0000);
		this.setCode_Msg(ExceptionConstants.MSG_0000);
	}

	public BusinessException(String msg, Throwable ex) {
		super(ExceptionConstants.CODE_0000,msg,ex);
		this.setCode_Msg(ExceptionConstants.MSG_0000);
	}
	
	public BusinessException(String code,String msg,Throwable ex){
		super(code,ExceptionConstants.MSG_0000 ,msg, ex);
	}

}
