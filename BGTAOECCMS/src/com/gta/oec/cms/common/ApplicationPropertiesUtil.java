/**
 * SpringPropertiesUtil.java	  V1.0   2014-4-10 上午10:30:47
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.common;

import java.util.Map;

import org.apache.log4j.Logger;

/** 
 * @description Application-PropertiesUtil工具类 -获取属性值  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-04-10 11:25:34 
 * @file ApplicationPropertiesUtil.java 
 * @package com.gta.oec.cms.common 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class ApplicationPropertiesUtil {

	private static Logger log = Logger.getLogger(ApplicationPropertiesUtil.class);
	
	private static Map<String,String> properties;
	
	public static void setProperties(Map<String,String> properties){
		ApplicationPropertiesUtil.properties = properties;
	}
	
    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static String getStringValue(String keyName) {
    	String str = null;
    	try{
    		str = properties.get(keyName).toString();
    	}catch(NullPointerException e){
    		str = "";
    		log.error(e);
    		e.printStackTrace();
    	}
        return str;
    }
 
    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static int getIntValue(String keyName) {
    	int ret = 0;
    	try{
    		ret = Integer.parseInt(properties.get(keyName).toString());
    	}catch(Exception e){
    		ret = -1;
    		log.error(e);
    		e.printStackTrace();
    	}
        return ret;
    }
 
    /**
     * 获取配置文件中的内容
     *
     * @param keyName
     * @return
     */
    public static double getDoubleValue(String keyName) {
        double ret = 0.0d;
    	try{
    		ret = Double.parseDouble(properties.get(keyName).toString());
    	}catch(Exception e){
    		ret = -1d;
    		log.error(e);
    		e.printStackTrace();
    	}
        return ret;
    }

}
