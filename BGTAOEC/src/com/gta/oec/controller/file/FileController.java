/**
 * FileController.java	  V1.0   2014-3-6 上午8:52:50
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.util.logging.resources.logging;

import com.gta.oec.common.web.upload.FileRepository;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.controller.user.UserController;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   /**
    * 
    * 功能描述：获取文件上传进度
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-3-6 上午9:07:46</p>
    *
    * @param request
    * @param response
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   @RequestMapping("/file/fileUploadProgress.ajax")
   public void fileUploadProgress(HttpServletRequest request,HttpServletResponse response){
	   long totalSize = 1l;
	   long uploadSize = 1l;
	   boolean result = false;
	   if (request.getSession().getAttribute(FileRepository.TOTAL_SIZE)!=null) {
		   totalSize = (Long) request.getSession().getAttribute(FileRepository.TOTAL_SIZE);
	    }
	   if (request.getSession().getAttribute(FileRepository.UPLOAD_SIZE)!=null) {
		   uploadSize = (Long) request.getSession().getAttribute(FileRepository.UPLOAD_SIZE);
	    }
	   String uploadPercent = "0%";
	   if (totalSize>1&&uploadSize>1) {
		   uploadPercent = String.valueOf(uploadSize*100/totalSize).concat("%");
	   }
	   if (totalSize>1&&uploadSize>1&&(uploadSize>=totalSize)) {
		   result= true;
		   uploadPercent = "100%";
	    }
	   logger.info("fileUpload--totalSize:"+totalSize+"--uploadSize:"+uploadSize);
	   JSONObject jsonObject = new JSONObject();
	   jsonObject.put("result", result);
	   jsonObject.put("progress", uploadPercent);
	   ResponseUtils.renderJson(response, jsonObject.toString());
   }
   @RequestMapping("/file/fileUploadLocalProgress.ajax")
   public void fileUploadLocalProgress(HttpServletRequest request,HttpServletResponse response){

	   boolean result = false;
	   int percent = (Integer) request.getSession().getAttribute("percent");
	   if (percent>=100) {
		   result= true;
		   percent = 100;
	    }
	   JSONObject jsonObject = new JSONObject();
	   jsonObject.put("result", result);
	   jsonObject.put("progress", String.valueOf(percent).concat("%"));
	   ResponseUtils.renderJson(response, jsonObject.toString());
   }

}
