package com.gta.oec.cms.util;

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

import com.gta.oec.cms.common.ApplicationPropertiesUtil;

@Component
public class FileRepository implements ServletContextAware {
	private Logger log = LoggerFactory.getLogger(FileRepository.class);
	public final static String TOTAL_SIZE = "TOTAL_SIZE";
	public final static String UPLOAD_SIZE = "UPLOAD_SIZE";

	public String storeByFileName(String filename, MultipartFile file)
			throws IOException {
		String path = ApplicationPropertiesUtil
				.getStringValue("ResourcesRootDir");
		String dir = path + filename;
		File dest = new File(dir);
		store(file, dest);
		return filename;
	}

	public void deleteRemoteFileByName(String ip, String name,
			String password, String filePath, String filename) throws Exception{
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(ip,
				name, password);
		try {
			SmbFile smbFile = new SmbFile(filePath + filename, auth);
			smbFile.delete();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			throw e;
		} catch (SmbException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			throw e;
		}
	}
	
	public String storeByRemoteFileName(String ip, String name,
			String password, String filePath, String filename,
			MultipartFile file) throws IOException {
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(ip,
				name, password);
		SmbFile smbFile;
		try {
			smbFile = new SmbFile(filePath, auth);
			if (!smbFile.exists()) {
				smbFile.mkdir();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (SmbException e) {
			e.printStackTrace();
			throw e;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			smbFile = new SmbFile(filePath + filename, auth);
			in = file.getInputStream();
			out = smbFile.getOutputStream();
			byte[] buffer = new byte[1024 * 10];
			int i = 0;
			if (null != ThreadLocalUtils.getRequest()) {
				ThreadLocalUtils.getRequest().getSession()
						.setAttribute(TOTAL_SIZE, file.getSize());
			}
			long uploadSize = 0;
			while ((i = in.read(buffer)) != -1) {// in对象的read方法返回-1为 文件以读取完毕
				out.write(buffer, 0, i);
				uploadSize = uploadSize + i;
				if (null != ThreadLocalUtils.getRequest()) {
					ThreadLocalUtils.getRequest().getSession()
							.setAttribute(UPLOAD_SIZE, uploadSize);
				}
				buffer = new byte[1024 * 10];
			}
			out.flush();
			in.close();
			out.close();
			resetUploadProgress();
		} catch (MalformedURLException e) {
			resetUploadProgress();
			e.printStackTrace();
		} catch (IOException e) {
			resetUploadProgress();
			if (null != in) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (null != out) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw e;
		}

		return filename;
	}
	
	public String storeByRmoeteFilename(String ip, String name,
			String password, String filePath, String filename,
			MultipartFile file) throws IOException {
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(ip,
				name, password);
		SmbFile smbFile;
		try {
			smbFile = new SmbFile(filePath, auth);
			if (!smbFile.exists()) {
				smbFile.mkdir();
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (SmbException e) {
			log.error(e.getMessage());
			throw e;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			smbFile = new SmbFile(filePath + filename, auth);
			in = file.getInputStream();
			out = smbFile.getOutputStream();
			byte[] buffer = new byte[1024 * 10];
			int i = 0;
			if (null != ThreadLocalUtils.getRequest()) {
				ThreadLocalUtils.getRequest().getSession()
						.setAttribute(TOTAL_SIZE, file.getSize());
			}
			long uploadSize = 0;
			while ((i = in.read(buffer)) != -1) {// in对象的read方法返回-1为 文件以读取完毕
				out.write(buffer, 0, i);
				uploadSize = uploadSize + i;
				if (null != ThreadLocalUtils.getRequest()) {
					ThreadLocalUtils.getRequest().getSession()
							.setAttribute(UPLOAD_SIZE, uploadSize);
				}
				buffer = new byte[1024 * 10];
			}
			out.flush();
			in.close();
			out.close();
			resetUploadProgress();
		} catch (MalformedURLException e) {
			resetUploadProgress();
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			resetUploadProgress();
			if (null != in) {
				try {
					in.close();
				} catch (IOException e1) {
					log.error(e.getMessage());
				}
			}
			if (null != out) {
				try {
					in.close();
				} catch (IOException e1) {
					log.error(e.getMessage());
				}
			}
			throw e;
		}

		return filename;
	}

	// 重置上传进度信息
	private void resetUploadProgress() {
		ThreadLocalUtils.getRequest().getSession().setAttribute(TOTAL_SIZE, 0l);
		ThreadLocalUtils.getRequest().getSession()
				.setAttribute(UPLOAD_SIZE, 0l);
	}

	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(ctxs.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	public String storeByFileName(String filename, File file)
			throws IOException {
		File dest = new File(ctxs.getRealPath(filename));
		store(file, dest);
		return filename;
	}

	private void store(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			file.transferTo(dest);
			resetUploadProgress();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	public File retrieve(String name) {
		return new File(ctxs.getRealPath(name));
	}

	private ServletContext ctxs;

	public void setServletContext(ServletContext servletContext) {
		this.ctxs = servletContext;
	}
}
