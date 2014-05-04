/**
 * FreeMarkUtils.java	  V1.0   2014-4-18 下午6:16:35
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.utils;

import javax.servlet.http.HttpServletRequest;

import com.gta.oec.common.web.springmvc.MessageResolver;

public class PathUtils {
  /**
   * 
   * 功能描述：获取相对路径
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：20142014-4-18 下午6:17:51</p>
   *
   * @param request
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static String getBasePath(HttpServletRequest request){
		if ("/ROOT".equals(request.getContextPath())) {
		   return "";
		} else {
		  return	 request.getContextPath();
		}
  }
  /**
   * 
   * 功能描述：
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：20142014-4-18 下午6:19:01</p>
   *
   * @param request
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static String getCachePath(HttpServletRequest request){
	    StringBuffer url = request.getRequestURL();  
	    String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
		return MessageResolver.getMessage(request, "cache", null)==null?tempContextUrl:MessageResolver.getMessage(request, "cache", null);
  }
}
