/**
 * CacheManagerImpl.java	  V1.0   2014-4-17 上午9:38:50
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 功能描述：本地缓存、临时用、后续可以与第三方缓存结合
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class OecCacheManager {
	private  final Log logger = LogFactory.getLog(getClass());
	private static final Map<String, Object> valueMap = new ConcurrentHashMap<String, Object>();
    private static final Map<String, Object> timeMap = new ConcurrentHashMap<String, Object>();
    private static OecCacheManager oecCacheManager = null;
    private OecCacheManager(){};
    public static OecCacheManager  getInstance() {
		if (null == oecCacheManager) {
			synchronized (OecCacheManager.class) {
				if (null==oecCacheManager) {
					oecCacheManager = new OecCacheManager();
				}
			}			
		}
		return oecCacheManager;
	}
	public  void set(String key, Object object) {
		valueMap.put(key, object);
		timeMap.put(key, System.currentTimeMillis());
		if (logger.isInfoEnabled()) {
			logger.info("OecCacheManager put data:key="+key+" value:"+object);
		}
	}

	public  Object get(String key) {
		timeMap.put(key, System.currentTimeMillis());
		if (logger.isInfoEnabled()) {
			logger.info("OecCacheManager get data:key="+key+" value:"+valueMap.get(key));
		}		
		return valueMap.get(key);
	}

	public  void delete(String key) {
		valueMap.remove(key);
		timeMap.remove(key);
		if (logger.isInfoEnabled()) {
			logger.info("OecCacheManager delete data:key="+key);
		}
	}
	
   public Map<String, Object> getTimeMap(){
	   return timeMap;
   }
}
