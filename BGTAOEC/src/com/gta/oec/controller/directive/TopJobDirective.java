/**
 * TopJobDirective.java	  V1.0   2014-3-1 上午9:33:03
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gta.oec.common.web.frermarker.DirectiveUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.service.job.JobService;
import com.gta.oec.vo.job.JobVo;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * 功能描述：热门行业标签
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class TopJobDirective implements TemplateDirectiveModel{

	private static final Log logger = LogFactory.getLog(TopJobDirective.class);
	@Autowired
	JobService jobService;
	@Override
	public void execute(Environment env, Map map, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<JobVo> list = new ArrayList<JobVo>();
		
			try {
				JobVo jobVo = new JobVo();
				jobVo.setJobRecommend(1);
				list = jobService.getJobList(jobVo, 0, 0);
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>();
		paramWrap.put("list", DefaultObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, map, paramWrap);
		
	}
}
