package com.gta.oec.cms.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/** 
 * @description 自定义视图解析(通过配置实现多视图整合,如jsp,html,freemarker,pdf,excel...)  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-03-21 19:58:34 
 * @file CmsOecViewResolver.java 
 * @package com.gta.oec.cms.common 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class CmsOecViewResolver implements ViewResolver {

	private static Logger log = Logger.getLogger(CmsOecViewResolver.class);
	
	/**
	 * @description default viewResolver; if the view resolverMap is null, then resolveViewName will return the default viewResolver
	 * 
	 * */
	private ViewResolver defaultViewResolver = null;
	
	/**
	 * @description viewResolverMap;
	 * 
	 * */
	private Map<Set<String>,ViewResolver> viewResolverMap = new HashMap<Set<String>,ViewResolver>();
	
	public Map<Set<String>, ViewResolver> getViewResolverMap() {
        return viewResolverMap;
    }

    public void setViewResolverMap(Map<Set<String>, ViewResolver> viewResolverMap) {
        this.viewResolverMap = viewResolverMap;
    }

    public ViewResolver getDefaultViewResolver() {
        return defaultViewResolver;
    }

    public void setDefaultViewResolver(ViewResolver defaultViewResolver) {
        this.defaultViewResolver = defaultViewResolver;
    }
	
    /**
	 * @description return resolver with different suffix(eg: .jsp; .ftl; .html; .htm);
	 * @param viewName
	 * @param locale
	 * @return View
	 * 
	 * */
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		for (Map.Entry<Set<String>, ViewResolver> map : viewResolverMap
				.entrySet()) {
			Set<String> suffixs = map.getKey();
			for (String suffix : suffixs) {
				if (viewName.endsWith(suffix)) {
					ViewResolver viewResolver = map.getValue();
					if (null != viewResolver) {
						log.debug("found viewResolver '" + viewResolver
								+ "' for viewName '" + viewName + "'");
						return viewResolver.resolveViewName(viewName, locale);
					}
				}
			}
		}

		if (defaultViewResolver != null) {
			return defaultViewResolver.resolveViewName(viewName, locale);
		}
		// to allow for ViewResolver chaining
		return null;
	}

}