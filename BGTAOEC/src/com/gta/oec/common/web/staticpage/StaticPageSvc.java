package com.gta.oec.common.web.staticpage;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


import freemarker.template.TemplateException;

public interface StaticPageSvc {
	

	public void index() throws IOException, TemplateException;

	public boolean createPage(String tpl, Map<String, Object> data,String file)
			throws IOException, TemplateException;

	public boolean deleteFile(String path);
}
