package com.gta.oec.enums.user;


/**
 * 功能描述：用户状态枚举类
 *
 * @author  li.liu
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public enum UserStateEnum {

	
	/**
	 * 是
	 */
	ABLE("ABLE","有效"),//有效
	DISABLED("DISABLED", "注销")//注销
 ;
	private UserStateEnum(String value, String name) {
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
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
}
