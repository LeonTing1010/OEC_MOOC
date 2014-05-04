package com.gta.oec.cms.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * @description : 基础的VO对象的父类
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-13 18:26:00
 * 
 * */
public class BaseValObject implements Serializable, Cloneable, Comparable<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932029680477356236L;

	@Override
	public int compareTo(Object o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}

}
