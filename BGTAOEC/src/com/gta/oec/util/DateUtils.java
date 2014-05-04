package com.gta.oec.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
	private static final Log logger = LogFactory.getLog(DateUtils.class);
	public static String defaultDateTimeFormatString = "yyyy-MM-dd HH:mm:ss";
	public static String defaultDateFormatString = "yyyy-MM-dd";
	/**
	 * 
	 * 功能描述：获取当前系统时间
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-2 下午2:35:54</p>
	 *
	 * @param parse 时间格式
	 * @return
	 * @throws ParseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Date getCurrentDate(String parse) throws ParseException{
		
		if (StringUtils.isBlank(parse)) {
			parse= defaultDateTimeFormatString;
		}
		SimpleDateFormat df = new SimpleDateFormat(parse);	
		return parse(df.format(new Date()), parse);
	}
	/**
	 * 
	 * 功能描述：string 转换成date，默认模式
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:15:31
	 *         </p>
	 * 
	 * @param source
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public static Date parse(String source) {
		try {
			SimpleDateFormat format = new SimpleDateFormat();
			if (source.indexOf(":") > 0)
				format.applyPattern(defaultDateTimeFormatString);
			else {
				format.applyPattern(defaultDateFormatString);
			}
			return format.parse(source.trim());
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能描述：string 转换成date,自定义格式
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:14:30
	 *         </p>
	 * 
	 * @param source
	 * @param formatString
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public static Date parse(String source, String formatString) {
		try {
			SimpleDateFormat format = new SimpleDateFormat();
			format.applyPattern(formatString.trim());
			return format.parse(source.trim());
		} catch (ParseException e) {
			logger.error(e); e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能描述：date转换成string
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:11:42
	 *         </p>
	 * 
	 * @param date
	 * @param FormatString
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public static String format(Date date, String FormatString) {
		SimpleDateFormat format = null;
		if (StringUtils.isBlank(FormatString)) {
			format = new SimpleDateFormat(defaultDateTimeFormatString);
		}
		format = new SimpleDateFormat(FormatString);
		return format.format(date);
	}

	/***************************************************************************
	 * dateStrToLong
	 * 
	 * @see 2005-11-15
	 * @param dateStr
	 *            日期字符串yyyy-mm-dd
	 * @return long theDay
	 **************************************************************************/
	public static long dateStrToLong(String dateStr) {
		long theDate = System.currentTimeMillis();
		Date thisDate = stringToDate(dateStr);
		if (thisDate != null) {
			theDate = thisDate.getTime();
		}
		return theDate;
	}

	/***************************************************************************
	 * stringToDate 把字符型"yyyy-MM-dd"转换成日期型
	 * 
	 * @param s
	 *            String 需要转换的日期时间字符串
	 * @return theDate Date
	 **************************************************************************/
	public static Date stringToDate(String s) {
		Date theDate = null;
		try {
			if (s != null) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						"yyyy-MM-dd");
				theDate = dateFormatter.parse(s);
			} else {
				theDate = null;
			}
		} catch (ParseException pe) {
			// plogger.error(e); e.printStackTrace();
			theDate = null;
		}
		return theDate;
	}

	public static String currPK() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String d1 = sdf.format(d);

		try {
			Thread.sleep(50);/* 沉睡50毫秒 */
		} catch (InterruptedException e) {
			logger.error(e); e.printStackTrace();
		}

		return d1;

	}

}