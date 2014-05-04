/**
 * TradeTopQuesDirective.java	  V1.0   2014-3-1 上午11:05:19
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gta.oec.common.web.frermarker.DirectiveUtils;
import com.gta.oec.service.qacenter.QuestionCenterService;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.user.UserVo;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * 功能描述： 行业热门问题
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class TradeTopQuesDirective implements TemplateDirectiveModel{
	private static final Log logger = LogFactory.getLog(TradeTopQuesDirective.class);
	/***行业id ***/
	private static final String TRADE_ID = "tradeId";
	private static final String LEN = "len";
	@Autowired
	QuestionCenterService questionCenterService;
	public void execute(Environment env, Map map, TemplateModel[] model,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String tradeId = DirectiveUtils.getString(TRADE_ID, map);
		String len = DirectiveUtils.getString(LEN, map);
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		try {
			if (!StringUtils.isBlank(tradeId)&&!StringUtils.isBlank(len)) {
				list = questionCenterService.getHotQuestionList(Integer.parseInt(tradeId), new UserVo(), 1, Integer.parseInt(len));
			}
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>();
		paramWrap.put("list", DefaultObjectWrapper.DEFAULT_WRAPPER.wrap(list==null?new ArrayList<CourseVo>():list));
		DirectiveUtils.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, map, paramWrap);
		
	}

}
