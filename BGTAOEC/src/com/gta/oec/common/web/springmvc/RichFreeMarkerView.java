package com.gta.oec.common.web.springmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.gta.oec.common.web.utils.PathUtils;



public class RichFreeMarkerView extends FreeMarkerView {

	public static final String CONTEXT_PATH = "base";
	public static final String CONTEXT_CACHE = "cache";

	@SuppressWarnings("unchecked")
	protected void exposeHelpers(Map model, HttpServletRequest request)
			throws Exception {
		 model.put(CONTEXT_CACHE, PathUtils.getCachePath(request));		
	     model.put(CONTEXT_PATH,PathUtils.getBasePath(request));
	
	}
}