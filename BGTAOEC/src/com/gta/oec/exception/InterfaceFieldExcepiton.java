package com.gta.oec.exception;
/**
 * 
 * 功能描述：接口参数错误
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class InterfaceFieldExcepiton extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1781166217698233848L;
	private static final int  errorCode = 1009;
	
	public InterfaceFieldExcepiton(String msg) {
		super(msg);
		this.setCode(errorCode);
	}

	public InterfaceFieldExcepiton(Throwable ex) {		
		super(ex);
		this.setCode(errorCode);
	}
	
	public InterfaceFieldExcepiton(String msg, Throwable ex) {
		super(msg,ex);
		this.setCode(errorCode);
	}

}
