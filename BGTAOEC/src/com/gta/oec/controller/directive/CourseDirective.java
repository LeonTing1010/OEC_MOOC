/**
 * CourseDirective.java	  V1.0   2014-1-4 上午9:20:13
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gta.oec.common.web.frermarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class CourseDirective implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map map, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>();
		
		paramWrap.put("", DefaultObjectWrapper.DEFAULT_WRAPPER.wrap(""));
		DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, map, paramWrap);
		
	}

}
