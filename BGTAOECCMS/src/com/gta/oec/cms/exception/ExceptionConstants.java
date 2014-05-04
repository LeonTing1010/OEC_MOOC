package com.gta.oec.cms.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ExceptionConstants {
	public static final String CODE_0000 = "0000";  
    public static final String MSG_0000 = "业务异常";  
  
    public static final String CODE_0100 = "0100";  
    public static final String MSG_0100 = "服务异常";  
  
    public static final String CODE_0200 = "0200";  
    public static final String MSG_0200 = "数据访问异常";  
  
    private static Map<String, String> returnCodeMap = new ConcurrentHashMap<String, String>();  
  
    public static Map<String, String> getReturnCodeMap() {  
        if (returnCodeMap.isEmpty()) {  
            returnCodeMap.put(CODE_0000, MSG_0000);  
            returnCodeMap.put(CODE_0100, MSG_0100);  
            returnCodeMap.put(CODE_0200, MSG_0200);  
        }  
        return returnCodeMap;  
    }
}
