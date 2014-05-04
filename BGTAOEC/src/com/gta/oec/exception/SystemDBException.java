package com.gta.oec.exception;
/**
 * 
 * 功能描述：数据库错误
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class SystemDBException extends BaseException {
	

	private static final long serialVersionUID = 108797897L;
	private static final int  errorCode = 1010;
	
	public SystemDBException(String msg) {
		super(msg);
		this.setCode(errorCode);
	}

	public SystemDBException(Throwable ex) {		
		super(ex);
		this.setCode(errorCode);
	}
	
	public SystemDBException(String msg, Throwable ex) {
		super(msg,ex);
		this.setCode(errorCode);
	}

}
