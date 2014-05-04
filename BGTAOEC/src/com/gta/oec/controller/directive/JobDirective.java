/**
 * JobDirective.java	  V1.0   2014-2-28 下午1:21:43
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gta.oec.common.web.frermarker.DirectiveUtils;
import com.gta.oec.controller.course.BaseCourseController;
import com.gta.oec.exception.BaseException;
import com.gta.oec.service.job.JobService;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.trade.TradeVo;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 * 功能描述：岗位标签-获取岗位信息列表
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class JobDirective implements TemplateDirectiveModel{
	private static final Log logger = LogFactory.getLog(TemplateDirectiveModel.class);
	
	/***行业id ***/
	private static final String TRADE_ID = "tradeId";
	/***岗位群id ***/
	private static final String JOB_GROUNP_ID = "jobGroupId";
	@Autowired
	JobService jobService;
	@Override
	public void execute(Environment env, Map map, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String tradeId = DirectiveUtils.getString(TRADE_ID, map);
		List<JobVo> list = new ArrayList<JobVo>();
		if (!StringUtils.isBlank(tradeId)) {
			try {
				list = jobService.getJobByTradeId(Integer.parseInt(tradeId),1,6).getList();
			} catch (NumberFormatException e) {
				logger.error(e); e.printStackTrace();
			} catch (BaseException e) {
				logger.error(e); e.printStackTrace();
			}
		}
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>();
		paramWrap.put("list", DefaultObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, map, paramWrap);
		
	}
}
