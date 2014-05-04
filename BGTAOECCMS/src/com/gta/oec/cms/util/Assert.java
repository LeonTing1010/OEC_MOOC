/**
 * Assert.java	  V1.0   2014-4-24 下午6:15:52
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.util;


public abstract class Assert extends org.springframework.util.Assert {

	/**
	 * 功能描述：检验对象不为空
	 * @author  Bill
	 * <p>创建日期 ：2014-04-28 下午03:28:42</p>
	 * @param object
	 * @param message
	 * @throws IllegalArgumentException
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 功能描述：检验字符串是否为空
	 * @author  Bill
	 * <p>创建日期 ：2014-04-28 下午03:28:42</p>
	 * @param str
	 * @param message
	 * @throws IllegalArgumentException
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static void notEmpty(String str, String message) {
		if (str == null || "".equals(str)) {
			throw new IllegalArgumentException(message);
		}
	}
	
}
