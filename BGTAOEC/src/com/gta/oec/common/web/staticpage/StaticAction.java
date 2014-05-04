package com.gta.oec.common.web.staticpage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import freemarker.template.TemplateException;


@Controller
public class StaticAction {
	private static final Logger log = LoggerFactory.getLogger(StaticAction.class);

	/**
	 * 
	 * 功能描述：静态化电影频道首页
	 *
	 * @author  chenjie
	 * <p>创建日期 ：2012-6-12 下午06:44:59</p>
	 *
	 * @param request
	 * @param response
	 * @param model
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/static/v_movie.do")
	public void movieInput(HttpServletRequest request,HttpServletResponse response, ModelMap model) {}
	
	

}
