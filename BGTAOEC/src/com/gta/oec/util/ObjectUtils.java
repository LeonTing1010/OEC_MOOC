/**
 * ObjectUtils.java	  V1.0   2014-1-3 下午5:50:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ObjectUtils {
	private static final Log logger = LogFactory.getLog(ObjectUtils.class);
    /**
     * 
     * 功能描述：
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-3 下午5:51:48</p>
     *
     * @param object
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
   public static Object prse(Object object,Class<?> returnClass){
	   if (null == object) {
		   try {
			return returnClass.newInstance();
		} catch (InstantiationException e) {
			logger.error(e); e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error(e); e.printStackTrace();
		}
	     }
	   return object;
   }
}
