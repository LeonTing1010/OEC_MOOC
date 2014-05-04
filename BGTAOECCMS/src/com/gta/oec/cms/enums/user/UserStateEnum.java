package com.gta.oec.cms.enums.user;


/**
 *用户状态枚举类
 *
 *@author can.xie
 *@create date 2014.3.19
 */
public enum UserStateEnum {

	
	/**
	 * 是
	 */
	ABLE(1,"有效"),//有效
	DISABLED(0, "注销")//注销
 ;
	private String name;
	private Integer value;
	
	private UserStateEnum(Integer value,String  name) {
		this.name = name;
		this.value = value;
	}
	

	public static UserStateEnum userStateEnum(String value) {
		for (UserStateEnum se : values()) {
			if (se.value.equals(value)) {
				return se;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	
	
}
