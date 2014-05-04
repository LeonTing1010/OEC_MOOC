/**
 * CourseStateEnum.java	  V1.0   2014-4-23 上午10:25:39
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.enums;


public enum CourseStateEnum {
	//1.未发布 2.已发布  3.审核中（待审核） 4.审核未通过 5.已结束
	UNPUBLISH(1,"未发布"),
	PUBLISHED(2,"已发布"),
	CHECKING(3,"审核中（待审核）"),
	UNPASS(4,"审核未通过"),
	FINISHED(5,"已结束");
	
	private Integer value;
	private String text;
	
	private CourseStateEnum(Integer value, String text){
		this.value = value;
		this.text = text;
	}
	
	public static String getText(Integer value) {
		for (CourseStateEnum cse : values()) {
			if (cse.value.equals(value)) {
				return cse.text;
			}
		}
		return null;
    }  

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
