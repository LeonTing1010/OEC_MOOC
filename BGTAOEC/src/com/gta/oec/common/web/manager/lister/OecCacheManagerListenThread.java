/**
 * OecCacheManagerListenThread.java	  V1.0   2014-4-17 上午11:25:40
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.manager.lister;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gta.oec.common.web.manager.OecCacheManager;


/**
 * 
 * 功能描述：本地缓存监听线程、清除过期的数据 临时用
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class OecCacheManagerListenThread extends Thread{
	private  final Log logger = LogFactory.getLog(getClass());
    private Map<String, Object> timeMap;
    public void  destroy() {
    	Thread.currentThread().interrupt();
    	if (logger.isInfoEnabled()) {
    		logger.info("OecCacheManager Listen Thread is destroy.");
		}
	}
	public void run() {
		while (true) {
	    	if (logger.isInfoEnabled()) {
	    		logger.info("OecCacheManager Listen Thread is start handle.");
			}
	    	
			this.timeMap = OecCacheManager.getInstance().getTimeMap();
			if (null != timeMap) {
				try {					
					Iterator<Map.Entry<String, Object>> iterator = timeMap.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String, Object> entry = iterator.next();
						long time = (Long) entry.getValue();
						//如果超过20分钟 则清除缓存数据  时间具体与tomcat的超时时间相关
						if ((System.currentTimeMillis()-time)>(20*60*1000)) {
							OecCacheManager.getInstance().delete(entry.getKey());
							if (logger.isInfoEnabled()) {
								logger.info("OecCacheManager delete out of time data:key=".concat(entry.getKey()));
							}
							
						}
					}
				} catch (Exception e) { 
					logger.equals(e);
				}	
			}
			try {
				if (logger.isInfoEnabled()) {
					logger.info("OecCacheManager Listen Thread is  sleep.");
				}
				
				//每5分钟进行一次数据扫描
				this.sleep(5*60*1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
