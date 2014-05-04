package com.gta.oec.cms.util;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * @description ID生成器帮助类  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-03-21 17:22:34 
 * @file IDGeneratorUtil.java 
 * @package com.gta.oec.cms.util 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class IDGeneratorUtil {
	
	public static final Logger log = LoggerFactory.getLogger(IDGeneratorUtil.class);
	
	/**
     * 
     * @description 生成UUID
     * @author  Bill Huang
     * @createDate 2014-03-24 17:22:34
     * @param null
     * @return String 
     * 
     */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-", "");
	    return str.toUpperCase(); 
	}
	
	/**
     * 
     * @description 获得指定数量的UUID
     * @author  Bill Huang
     * @createDate 2014-03-24 17:22:34
     * @param number
     * @return String[] 
     * 
     */
    public static String[] getUUID(int number) {   
        if (number < 1) {   
            return null;   
        }   
        String[] sArr = new String[number];   
        for (int i = 0; i < number; i++) {   
        	sArr[i] = getUUID();   
        }   
        return sArr;   
    }
	
	public static void main(String[] args) {
		System.out.println(getUUID());

		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("ss[" + i + "]=====" + ss[i] + " ==== " + ss[i].length());
		}
	}
	
}
