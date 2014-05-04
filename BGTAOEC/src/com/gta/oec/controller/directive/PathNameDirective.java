/**
 * PathNameDirective.java	  V1.0   2014-3-12 下午1:07:14
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gta.oec.common.web.frermarker.DirectiveUtils;
import com.gta.oec.common.web.utils.FileUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 * 功能描述：相对路径截取文件名标签
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class PathNameDirective implements TemplateDirectiveModel{
	
	public static final String FILE_PATH = "path";//字符   
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String filePath = DirectiveUtils.getString(FILE_PATH, params);

		if (!StringUtils.isBlank(filePath)) {
			String[] str = filePath.split("\\/");			
			Writer out = env.getOut();
			out.append(str[str.length-1]);
		}

	}
}
