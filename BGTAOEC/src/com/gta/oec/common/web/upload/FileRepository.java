package com.gta.oec.common.web.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.gta.oec.common.web.utils.ThreadLocalUtils;
@Component
public class FileRepository implements ServletContextAware {
	private Logger logger = LoggerFactory.getLogger(FileRepository.class);
    public final static String TOTAL_SIZE = "TOTAL_SIZE";
    public final static String UPLOAD_SIZE = "UPLOAD_SIZE";

	public String storeByFilename(String filename, MultipartFile file)
			throws IOException {
		File dest = new File(ctx.getRealPath(filename));
		store(file, dest);
		return filename;
	}
    public String storeByRmoeteFilename(String ip,String name,String password,String filePath,String filename, MultipartFile file) throws IOException
		 {
    	NtlmPasswordAuthentication  auth = new NtlmPasswordAuthentication(ip, name, password);
    	SmbFile smbFile;
		try {
			smbFile = new SmbFile(filePath,auth);
			if (!smbFile.exists()) {
				smbFile.mkdir();
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SmbException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		InputStream in = null;
		OutputStream out = null;
	   try {
		   smbFile = new SmbFile(filePath+filename,auth);
		   if(!smbFile.exists()){
			   smbFile.createNewFile();
		   }
		    in = file.getInputStream();
		    out = smbFile.getOutputStream();
	       byte[] buffer = new byte[1024*10];  
	       int i = 0;
	      if (null!=ThreadLocalUtils.getRequest()) { 
	    	  ThreadLocalUtils.getRequest().getSession().setAttribute(TOTAL_SIZE, file.getSize());
		    }
	      long uploadSize =0;
	       while((i=in.read(buffer))!=-1){//in对象的read方法返回-1为 文件以读取完毕  	    	 
	           out.write(buffer, 0, i);  
	           uploadSize = uploadSize+i;
	 	      if (null!=ThreadLocalUtils.getRequest()) {
		    	  ThreadLocalUtils.getRequest().getSession().setAttribute(UPLOAD_SIZE, uploadSize);
			    }
	           buffer = new byte[1024*10];  
	       }  
	        out.flush();
	        in.close();
	        out.close();
	        resetUploadProgress();
	   } catch (MalformedURLException e) {
		   resetUploadProgress();
		   logger.error(e.getMessage());
		   e.printStackTrace();
	   } catch (IOException e) {
		   logger.error(e.getMessage());
		   e.printStackTrace();
		   resetUploadProgress();
		if (null!=in) {
			try {
				in.close();
			} catch (IOException e1) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		if (null!=out) {
			try {
				in.close();
			} catch (IOException e1) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		throw e;
	}
		return filename;
	}
    //重置上传进度信息
    private void resetUploadProgress() {
    	 ThreadLocalUtils.getRequest().getSession().setAttribute(TOTAL_SIZE, 0l);
    	  ThreadLocalUtils.getRequest().getSession().setAttribute(UPLOAD_SIZE, 0l);
    }

	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(ctx.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	public String storeByFilename(String filename, File file)
			throws IOException {
		File dest = new File(ctx.getRealPath(filename));
		store(file, dest);
		return filename;
	}

	private void store(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());		
			file.transferTo(dest);
			resetUploadProgress();
		} catch (IOException e) {
			logger.error("Transfer file error when upload file", e);
			e.printStackTrace();
			throw e;
		}
	}

	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			logger.error("Transfer file error when upload file", e);
			e.printStackTrace();
			throw e;
		}
	}

	public File retrieve(String name) {
		return new File(ctx.getRealPath(name));
	}

	private ServletContext ctx;

	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}
}
