/**
 * OecCacheManagerLister.java	  V1.0   2014-4-17 上午11:16:27
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.manager.lister;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 功能描述：本地缓存监听器启动缓存监听线程 临时用
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class OecCacheManagerListener implements ServletContextListener  {
	private  final Log logger = LogFactory.getLog(getClass());
    private OecCacheManagerListenThread oecCacheManagerListenThread;
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		oecCacheManagerListenThread = new OecCacheManagerListenThread();
		oecCacheManagerListenThread.start();
		if (logger.isInfoEnabled()) {
			logger.info("OecCacheManagerListener is start.");
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		oecCacheManagerListenThread.destroy();
		if (logger.isInfoEnabled()) {
			logger.info("OecCacheManagerListener is destroy.");
		}
	}
}
