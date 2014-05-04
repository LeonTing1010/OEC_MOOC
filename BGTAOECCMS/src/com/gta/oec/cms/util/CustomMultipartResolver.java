/**
 * CustomMultipartResolver.java	  V1.0   2014-3-7 上午11:13:30
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


public class CustomMultipartResolver extends CommonsMultipartResolver{
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

			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);

		}catch (FileUploadException ex) {

			throw new MultipartException("Could not parse multipart servlet request", ex);

		}

	}


}
