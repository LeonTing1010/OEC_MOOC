/**
 * BeanUtils.java	  V1.0   2013-12-31 下午2:08:07
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gta.oec.exception.BaseException;

public class BeanUtils {
	private static final Log logger = LogFactory.getLog(BeanUtils.class);
	/**
	 * 
	 * 功能描述：实现map到object的转换
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:08:51
	 *         </p>
	 * 
	 * @param vo
	 * @param map
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public static void ConvertToMap(Object vo, Map map) {
		Class c = vo.getClass();
		Field[] fs = c.getDeclaredFields();
		Method method = null;
		// 遍历所有的属性(包括私有属性)
		for (int i = 0; i < fs.length; i++) {
			String key = fs[i].getName();
			// 组装get方法
			String m = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			try {
				method = c.getMethod(m);
			} catch (NoSuchMethodException e) {
				logger.error(e); e.printStackTrace();
				continue;
			}
			// 使用属性的get方法取值
			Object value = null;
			try {
				value = method.invoke(vo);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
				throw new RuntimeException("GetMehtod execute Exception");
			}
			map.put(key, value);
		}
	}

	/**
	 * 
	 * 功能描述：功能描述：实现Object到Map的转换,Date类型处理成String
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:10:25
	 *         </p>
	 * 
	 * @param vo
	 * @param map
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public static void ConvertToMapWithDate(Object vo, Map map) {
		Class c = vo.getClass();
		Field[] fs = c.getDeclaredFields();
		Method method = null;
		// 遍历所有的属性(包括私有属性)
		for (int i = 0; i < fs.length; i++) {
			String key = fs[i].getName();
			// 组装get方法
			String m = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			try {
				method = c.getMethod(m);
			} catch (NoSuchMethodException e) {
				logger.error(e); e.printStackTrace();
				continue;
			}
			// 使用属性的get方法取值
			Object value = null;
			try {
				value = method.invoke(vo);
				if (value != null
						&& value.getClass().getName().equals("java.util.Date")) {
					value = DateUtils.format((Date) value, null);
				}
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
				throw new RuntimeException("GetMehtod execute Exception", e);
			}
			map.put(key, value);
		}
	}

	/**
	 * 
	 * 功能描述：实现Map到Object的转换,只处理String类型,Map中的Key与Object属性严格匹配
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2013-12-31 下午2:39:28
	 *         </p>
	 * 
	 * @param o
	 * @param map
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public static void ConvertToObject(Object o, Map<String, String> map)
			throws BaseException {
		Iterator iter = map.entrySet().iterator();
		Class c = o.getClass();
		Method[] methods = c.getMethods();
		try {
			while (iter.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iter
						.next();
				for (int i = 0; i < methods.length; i++) {
					String methodName = methods[i].getName();
					if (methodName.toUpperCase().startsWith("SET")) {
						String m = "SET" + entry.getKey().toUpperCase();
						if (methodName.equalsIgnoreCase(m)) {
							methods[i].invoke(o, entry.getValue());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new BaseException("Convert Map to Bean Error!", e);
		}

	}
}
