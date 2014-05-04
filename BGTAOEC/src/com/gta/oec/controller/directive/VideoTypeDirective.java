/**
 * VideoTypeDirective.java	  V1.0   2014-3-10 上午11:28:05
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gta.oec.common.web.frermarker.DirectiveUtils;
import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.util.StrUtils;
import com.gta.oec.vo.course.CourseVo;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 * 功能描述：视频协议标签
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */

public class VideoTypeDirective implements TemplateDirectiveModel{
	
	public static final String DATA_URL = "dataUrl";//文件路径 
	public static final String VIDEO_PROTOCAOL = "videoProtocol";//播放协议 rtmp or http or ....
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String dataUrl = DirectiveUtils.getString(DATA_URL, params);
        String videoProtocol = DirectiveUtils.getString(VIDEO_PROTOCAOL, params);
		if (!StringUtils.isBlank(dataUrl)&&!StringUtils.isBlank(videoProtocol)) {
			String fileType = FileUtils.getFileType(dataUrl);
			if (!StringUtils.isBlank(fileType)) {
				if ("rtmp".equals(videoProtocol.toLowerCase())) {
					dataUrl = fileType.concat(":").concat(dataUrl);
				}
				if ("http".equals(videoProtocol.toLowerCase())) {
					dataUrl = fileType.concat(":").concat(dataUrl).concat("/manifest.f4m");
				}
			}
	
		}
		if (dataUrl != null) {
			Writer out = env.getOut();
			if (dataUrl != null) {
				out.append(dataUrl);
			} 
		}
	}
}
