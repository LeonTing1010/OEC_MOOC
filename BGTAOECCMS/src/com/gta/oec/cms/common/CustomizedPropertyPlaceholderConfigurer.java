/**
 * CustomizedPropertyPlaceholderConfigurer.java	  V1.0   2014-4-10 上午10:09:53
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/** 
 * @description 自定义的配置文件的configurer  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-04-10 11:25:34 
 * @file CustomizedPropertyPlaceholderConfigurer.java 
 * @package com.gta.oec.cms.common 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class CustomizedPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private Map<String,String> ctxPropertiesMap;
	
	/**
	 * @description ：处理资源文件的转换
	 * @author  jianhua.huang
	 * @since ：2014-04-10 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String,String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    
    /**
	 * @description ：获取context 属性
	 * @author  jianhua.huang
	 * @since ：2014-04-10 下午4:28:15</p>
	 * @param req
	 * @param resp
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
    public Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

	public Map<String, String> getCtxPropertiesMap() {
		return ctxPropertiesMap;
	}

	public void setCtxPropertiesMap(Map<String, String> ctxPropertiesMap) {
		this.ctxPropertiesMap = ctxPropertiesMap;
	}
    
}
