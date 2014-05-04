/**
 * ApplicationServletContextListener.java	  V1.0   2014-4-10 上午11:08:46
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.common.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.CustomizedPropertyPlaceholderConfigurer;
import com.gta.oec.cms.controller.professionjob.ProfessionJobController;
import com.gta.oec.cms.util.SystemConstant;

/** 
 * @description ApplicationServletContextListener系统启动listener,添加一些资源
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-04-10 11:25:34 
 * @file ApplicationServletContextListener.java 
 * @package com.gta.oec.cms.common 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class ApplicationServletContextListener implements
		ServletContextListener {

	private static Logger log = Logger.getLogger(ProfessionJobController.class);
	
	/**
	 * @description ：将一些需要放application里面的变量放进去
	 * @author  jianhua.huang
	 * @since ：2014-04-10 下午4:28:15</p>
	 * @param variables
	 * @param sc
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	protected void putVariableToApplicationScope(Map<String,String> variables,ServletContext sc){
		if(variables.isEmpty() || sc==null){
			return ;
		}
		//set ctx goabl variable to application scope
		sc.setAttribute("ctx", sc.getContextPath());
		//set the some variable to application scope with some prefix
		for (Map.Entry<String, String> entry : variables.entrySet()) {
		   String key = entry.getKey().toString();
		   for(String str : SystemConstant.PUTAPPLICATIONVARIABLEPREFIX){
				if(key.toUpperCase().startsWith(str.toUpperCase())){
					String value = entry.getValue().toString();
					if(StringUtils.isNotEmpty(value)){
						sc.setAttribute(key, value);
					}
					break;
				}else{
					continue;
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ApplicationPropertiesUtil.setProperties(null);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//系统启动初始化一些系统变量
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		CustomizedPropertyPlaceholderConfigurer propertyBean = (CustomizedPropertyPlaceholderConfigurer) wac
				.getBean(SystemConstant.PROPERTYCONFIGURERKEY);
		Map<String, String> maps = propertyBean.getCtxPropertiesMap();
		ApplicationPropertiesUtil.setProperties(maps);
		//将一些需要放到application作用域的变量放进去
		putVariableToApplicationScope(maps,event.getServletContext());
		log.debug("contextInitialized success ");
	}

}
