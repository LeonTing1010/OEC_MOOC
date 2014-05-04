/**
 * FileUploadProgressListener.java	  V1.0   2014-3-7 上午11:17:31
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.upload;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;
	public void setSession(HttpSession session){
		this.session=session;;
	}
	@Override
	public void update(long arg0, long arg1, int arg2) {
        int percent = (int) (((float)arg0 / (float)arg1) * 100);
        session.setAttribute("percent", percent);
	}


}
