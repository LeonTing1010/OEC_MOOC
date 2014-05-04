package com.gta.oec.common.web.staticpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.common.web.springmvc.RealPathResolver;



import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class StaticPageSvcImpl implements StaticPageSvc, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(StaticPageSvcImpl.class);

	private Configuration conf;
	
	private RealPathResolver realPathResolver;

	@Transactional(readOnly = true)
	public void index() throws IOException, TemplateException {
		Map<String, Object> data = new HashMap<String, Object>();
		String tpl = "";
		createPage(tpl, data,"index.html");
	}

	@Transactional(readOnly = true)
	public boolean createPage(String tpl, Map<String, Object> data,String file) throws IOException,
			TemplateException {
		String templatePath="/WEB-INF/webpage/"+tpl;
		long time = System.currentTimeMillis();
		String realPath=getFilePath(file);
		File f = new File(realPath);
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = null;
		boolean b=false;
		try {
			// FileWriter不能指定编码确实是个问题，只能用这个代替了。
			out = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			Template template = conf.getTemplate(templatePath);
			template.process(data, out);
			b=true;
		} catch(Exception e) {
			logger.error(e.getMessage());e.printStackTrace();
			b=false;
		}finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		
		time = System.currentTimeMillis() - time;
		logger.info("create index page, in {} ms", time);
		return b;
	}

	@Transactional(readOnly = true)
	public boolean deleteFile(String path) {
		File f = new File(getFilePath(path));
		return f.delete();
	}

	private String getFilePath(String indexPath) {
		
		return realPathResolver.get(indexPath);
		
	}


	public void afterPropertiesSet() throws Exception {
		Assert.notNull(conf, "freemarker configuration cannot be null!");
	}

	public void setFreeMarkerConfigurer(
			FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}
	
	@Autowired
	public void setRealPathResolver(RealPathResolver realPathResolver) {
		this.realPathResolver = realPathResolver;
	}


}
