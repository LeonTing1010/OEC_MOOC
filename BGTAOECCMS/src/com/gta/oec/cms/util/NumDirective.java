/**
 * TimeBetweenDirective.java	  V1.0   2014-1-8 上午10:45:40
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class NumDirective implements TemplateDirectiveModel{
    private static final String s = "s";
	@Override
	public void execute(Environment arg0, Map arg1, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
	   	 String str = DirectiveUtils.getString(s, arg1);
     	 if (!StringUtils.isBlank(str)) {
     		 
			switch (Integer.parseInt(str)) {
			case 0:
				str = "零";
				break;
			case 1:
				str = "一";
				break;
			case 2:
				str = "二";
				break;
			case 3:
				str = "三";
				break;
			case 4:
				str = "四";
				break;
			case 5:
				str = "五";
				break;
			case 6:
				str = "六";
				break;
			case 7:
				str = "七";
				break;
			case 8:
				str = "八";
				break;
			case 9:
				str = "九";
				break;
			case 10:
				str = "十";
				break;
			default:
				break;
			}
     		 
     		 Writer writer = arg0.getOut();
     		 writer.append(str);
		}
    	
    	 
	}
	

}
