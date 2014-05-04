/**
 * FileUtils.java	  V1.0   2014-1-16 上午8:48:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.util;


import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;

public class FileUtils {
	
	private static Logger log = Logger.getLogger(FileUtils.class);
	
  private FileUtils() {}
  /**
   * 
   * 功能描述：校验文件
   *
   * @author  
   * <p>创建日期 ：2014-1-16 上午8:53:29</p>
   *
   * @param file 
   * @param type 校验的文件类型
   * @param size 校验的文件大小 KB
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static boolean checkFile(MultipartFile file ,String type,long size) {
	   if (null==file) {
		return false;
	   }
	   if (!StringUtils.isBlank(type)) {
		   String name = file.getOriginalFilename();
		   String[] str = name.split("\\.");
		   name = str[str.length-1];
		   if (!type.equals(name)) {
			 return false;
		   }
	   }
	   if (size>0) {
		 long fileSize = file.getSize();
		 if (fileSize>(size*1024)) {
		   return false;
		 }
	   }
	   return true;
     }
  
  	/**
  	* @description ：存储文件到远程的服务器目录上，且这个目录必须和应用服务器的机器在同一个局域网里面，且这个目录必须共享出来，才能访问
    *
    * @author  
    * <p>创建日期 ：2014-04-21 上午10:16:29</p>
    *
    * @param uploadFile 上传的文件， MultipartFile 
    * @param resourceChildDir 上传到目标服务器上面公共目录下面的子目录结构
    * @param fileRepository 用来做文件操作的工具
    * @param destFileName 目标文件名
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  	* */
	public static String storeRemoteFileByAuth(FileRepository fileRepository,String resourceChildDir, String destFileName,MultipartFile uploadFile) throws Exception {
		String resourcesServerIp = ApplicationPropertiesUtil.getStringValue("ResourcesServerIp");
		String resourcesServerName = ApplicationPropertiesUtil.getStringValue("ResourcesServerName");
		String resourcesServerPassword = ApplicationPropertiesUtil.getStringValue("ResourcesServerPassword");
		String resourcesRootDir = ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
		//Smb://username:password@ip/sharefolder（例如：smb://chb:123456@192.168.0.1/test）
		//String filePath = "smb://" + resourcesServerIp + "/" + resourcesRootDir	+ resourceDir;
		StringBuffer filePath = new StringBuffer("smb://" + resourcesServerIp + "/" + resourcesRootDir);
		if(StringUtils.isNotEmpty(resourceChildDir)){
			filePath.append(resourceChildDir);
		}
		String retFileName = null;
		try {
			retFileName = fileRepository.storeByRemoteFileName(
					resourcesServerIp, resourcesServerName,
					resourcesServerPassword, filePath.toString().trim(), destFileName,
					uploadFile);
			log.info("upload file success. the file Name is : "+retFileName);
			return retFileName;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("upload remote file failer !",e);
			throw e;
		}
	}
	
	/**
  	* @description ：删除存储在远程的服务器目录上文件，且这个目录必须和应用服务器的机器在同一个局域网里面，且这个目录必须共享出来，才能访问
    *
    * @author  
    * <p>创建日期 ：2014-04-21 上午10:16:29</p>
    *
    * @param fileRepository 用来做文件操作的工具
    * @param resourceChildDir 目标服务器上面公共目录下面的子目录结构
    * @param destFileName 目标文件名
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
  	* */
	public static void deleteRemoteFileByName(FileRepository fileRepository,String resourceChildDir,String destFileName) throws Exception{
		String resourcesServerIp = ApplicationPropertiesUtil.getStringValue("ResourcesServerIp");
		String resourcesServerName = ApplicationPropertiesUtil.getStringValue("ResourcesServerName");
		String resourcesServerPassword = ApplicationPropertiesUtil.getStringValue("ResourcesServerPassword");
		String resourcesRootDir = ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");
		//Smb://username:password@ip/sharefolder（例如：smb://chb:123456@192.168.0.1/test）
		//String filePath = "smb://" + resourcesServerIp + "/" + resourcesRootDir	+ resourceDir;
		StringBuffer filePath = new StringBuffer("smb://" + resourcesServerIp + "/" + resourcesRootDir);
		if(StringUtils.isNotEmpty(resourceChildDir)){
			filePath.append(resourceChildDir);
		}
		try {
			fileRepository.deleteRemoteFileByName(resourcesServerIp, resourcesServerName, resourcesServerPassword, filePath.toString().trim(), destFileName);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("delete remote file failer !",e);
			throw e;
		}
	} 
  
  public static String getFileType(String fileName){
	  
	  if (StringUtils.isBlank(fileName)) {
		 return null;
    	}
	  String[] str = fileName.split("\\.");
	  if (str.length>=2) {
		 return str[str.length-1];
	    }
	  return null;
  }
  public static void main(String[] args) {
	
   }
}
