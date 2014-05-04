/**
 * ThreadLocalUtils.java	  V1.0   2014-3-6 上午8:30:56
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.util;

import javax.servlet.http.HttpServletRequest;

public class ThreadLocalUtils {
    public static final ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal<HttpServletRequest>() ;
    public static HttpServletRequest getRequest(){
       return	REQUEST_THREAD_LOCAL.get();
    }
    public static void setReques(HttpServletRequest request) {
    	REQUEST_THREAD_LOCAL.set(request);
	}
}
