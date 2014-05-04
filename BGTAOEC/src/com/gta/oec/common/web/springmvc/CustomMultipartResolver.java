/**
 * CustomMultipartResolver.java	  V1.0   2014-3-7 上午11:13:30
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gta.oec.common.web.upload.FileUploadProgressListener;

public class CustomMultipartResolver extends CommonsMultipartResolver{
	private static final Log logger = LogFactory.getLog(CustomMultipartResolver.class);
	
	@Autowired
	private FileUploadProgressListener progressListener;
	public void setFileUploadProgressListener(FileUploadProgressListener progressListener){
		this.progressListener=progressListener;
	}
	@Override
	@SuppressWarnings("unchecked")
	public MultipartParsingResult parseRequest(HttpServletRequest request)

			throws MultipartException {

		String encoding = determineEncoding(request);

		FileUpload fileUpload = prepareFileUpload(encoding);

		progressListener.setSession(request.getSession());

		fileUpload.setProgressListener(progressListener);
        
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(request);

			return parseFileItems(fileItems, encoding);

		}catch (FileUploadBase.SizeLimitExceededException ex) {
			logger.error(ex); ex.printStackTrace();;
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);

		}catch (FileUploadException ex) {
			logger.error(ex); ex.printStackTrace();;
			throw new MultipartException("Could not parse multipart servlet request", ex);

		}

	}


}
