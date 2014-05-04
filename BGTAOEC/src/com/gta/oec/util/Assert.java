/**
 * Assert.java	  V1.0   2013-12-31 下午1:57:57
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.util;

public abstract class Assert {

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(String s, String message) {
		if (s == null || "".equals(s)) {
			throw new IllegalArgumentException(message);
		}
	}
}
