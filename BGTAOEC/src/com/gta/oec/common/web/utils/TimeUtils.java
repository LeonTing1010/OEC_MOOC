/**
 * test.java	  V1.0   2014年2月27日 下午2:24:28
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 功能描述：时间工具类
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class TimeUtils {

	private static final Log logger = LogFactory.getLog(TimeUtils.class);
	/**
	 * 
	 * 功能描述：计算两个日期时间差(天、小时、分、秒)
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月27日 下午2:45:45</p>
	 *
	 * @param startTime
	 * @param endTime
	 * @param format
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Map<String, Long> dateDiff(String startTime, String endTime, String format) {
		//按照传入的格式生成一个SimpleDateFormat对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数
		long diff;
		Map<String, Long> map=new HashMap<String, Long>();
		//获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff/nd;//计算差多少天
			long hour = diff%nd/nh;//计算差多少小时
			long min = diff%nd%nh/nm;//计算差多少分钟
			long sec = diff%nd%nh%nm/ns;//计算差多少秒
			map.put("diffDays",day);
			map.put("diffHours", hour);
			map.put("diffMins", min);
			map.put("diffSecs", sec);
			map.put("diffTime", diff);
			//输出结果
			//System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String endTime="2014-03-22 17:17:00";
		String startTime=simpleDateFormat.format(date);
		
		System.out.println("结束时间"+endTime);
		System.out.println("开始时间"+simpleDateFormat.format(date));
		dateDiff(startTime, endTime,"yyyy-MM-dd HH:mm:ss");
	}
}
