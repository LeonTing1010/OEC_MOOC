package com.gta.oec.cms.exception;

/**
 * @description DAO Exception
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-24 11:38:00
 * 
 * */
public class DAOException extends AppExceiption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DAOException(String msg) {
		super(msg);
		this.setCode(ExceptionConstants.CODE_0200);
		this.setCode_Msg(ExceptionConstants.MSG_0200);
	}
	
	public DAOException(Throwable ex){
		super(ex);
		this.setCode(ExceptionConstants.CODE_0200);
		this.setCode_Msg(ExceptionConstants.MSG_0200);
	}

	public DAOException(String msg, Throwable ex) {
		super(ExceptionConstants.CODE_0200,msg, ex);
		this.setCode_Msg(ExceptionConstants.MSG_0200);
	}
	
	public DAOException(String code,String msg,Throwable ex){
		super(code,ExceptionConstants.MSG_0200, msg, ex);
	}

}
