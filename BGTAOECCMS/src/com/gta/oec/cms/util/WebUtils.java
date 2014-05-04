package com.gta.oec.cms.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;

public class WebUtils {

	private static Logger log = Logger.getLogger(WebUtils.class);
	
	//服务器IP
	private static String ip=ApplicationPropertiesUtil.getStringValue("ResourcesServerIp");
	//服务器用户名
	private static String  name=ApplicationPropertiesUtil.getStringValue("ResourcesServerName");
	//服务器密码
	private static String   password=ApplicationPropertiesUtil.getStringValue("ResourcesServerPassword");
	  
	
	/**
	 * 功能描述：分离字符串通过特殊字符
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014-04-16 下午3:28:15</p>
	 * @param str : 被分割的字符串
	 * @param separatorMark ： 分割符
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static List<String> separateStrByMark(String str,String separatorMark){
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(separatorMark)){
			return null;
		}
		List<String> list = null;
		if(str.indexOf(separatorMark)!=-1){
			String[] _ex = str.split(separatorMark);
			list = Arrays.asList(_ex);
		}else{
			list = Arrays.asList(str);
		}
		return list;
	}
 
	/**
	 * 图片上传服务器路径 
	 * 得到服务器根路径
	 * @author can.xie
	 * @createDate 201403.24
	 */
	public static String getApplicationPath(HttpServletRequest request ,String url){
		return request.getSession().getServletContext().getRealPath(url);
	}
	
	/**
	 * 删除图片
	 * 得到服务器根路径
	 * @author can.xie
	 * @createDate 201403.24
	 */
	public static String delUserImg(String userFileName,String path){
		String message="文件不存在";
		try {
			path = "smb://"+ip+path;
			NtlmPasswordAuthentication  auth = new NtlmPasswordAuthentication(ip, name, password);
			SmbFile smbFile = new SmbFile(path+userFileName,auth);
			if(null !=smbFile && smbFile.exists()){
				smbFile.delete();
				message= "删除成功";
			} 
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		log.debug("@@删除文件@@@@@"+message);
		return message;
	}
	/**
	 *  图片上传
	 * @param file
	 * @param patch
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object > uploadImg(MultipartFile file,String patch)throws Exception{
		Map<String, Object> result=new HashMap<String, Object>();
	 
		//得到配置的上传图片大小
		Integer configureImgSize=ApplicationPropertiesUtil.getIntValue("imgSize");
		//得到配置 允许上传的图片格式
		String configureImgFormat=ApplicationPropertiesUtil.getStringValue("imgFormat");
		patch = "smb://"+ip+patch;
		try{
			
			NtlmPasswordAuthentication  auth = new NtlmPasswordAuthentication(ip, name, password);
	    	SmbFile smbFile;
			
			//组装后的file name
			String userFileName=null;
			//页面传过来的file name
			String fileName=file.getOriginalFilename();
			int index = fileName.indexOf(".");
			
			//验证格式是否合法
			if(index ==-1){
				result.put("message", "请选择正确的图片格式");
				result.put("title", "上传失败");
				return result;
			}
			//得到用户上传图片格式
			String imgFormat=fileName.substring(index);
			String [] imgFormatSpit=configureImgFormat.split(",");
			Integer flag=0;
			for(String spitImgFormat: imgFormatSpit){
				if(spitImgFormat.equalsIgnoreCase(imgFormat)){
					flag=1;
					break;
				}
			}
			if(flag!=1){
				result.put("message", "请选择正确的图片格式");
				result.put("title", "上传失败");
				return result;
			}
			//验证大小是否合法
			if(file.getSize()>configureImgSize){
				result.put("message", "文件大小不能超过500K");
				result.put("title", "上传失败");
				return result;
			}
			
 
			
			smbFile = new SmbFile(patch,auth);
			if (!smbFile.exists()) {
				smbFile.mkdir();
			}
		 
			//组装后的图片名字
			userFileName =  VeDate.getTimeSS() + imgFormat;
			/*	//spring 上传图片
			File uploadFile = new File(resourcesRootDir+patch+ userFileName); 
			FileCopyUtils.copy(file.getBytes(), uploadFile); */
			
		
			
			  smbFile = new SmbFile(patch+userFileName,auth);
			   if(!smbFile.exists()){
				   smbFile.createNewFile();
			   }
				InputStream  in = file.getInputStream();
				OutputStream out = smbFile.getOutputStream();
		       byte[] buffer = new byte[1024*10];  
		       int i = 0;
		      long uploadSize =0;
		       while((i=in.read(buffer))!=-1){//in对象的read方法返回-1为 文件以读取完毕  	    	 
		           out.write(buffer, 0, i);  
		           uploadSize = uploadSize+i;
		       }  
		        out.flush();
		        in.close();
		        out.close();
			
			
			result.put("message", "上传成功");
			result.put("title", "上传成功");
			result.put("userFileName", userFileName);
			log.debug("@@@@@@@上传图片成功");
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
			result.put("message", "上传失败");
			result.put("title", "上传失败");
		}
		return result;
	}
 
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static final String randomString(int length) {
		 char[] numbersAndLetters;
		if (length < 1) {
			return null;		 
			}		      
			Random randGen = new Random();	
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +		 
			"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();	
			char [] randBuffer = new char[length];	
			for (int i=0; i<randBuffer.length; i++) {		             
				randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];		          
				}		         
			return new String(randBuffer);		
			}
	
	public static final String randomInt(int length) {
		 char[] numbersAndLetters;
			if (length < 1) {
				return null;		 
				}		      
				Random randGen = new Random();	
				numbersAndLetters = ("0123456789").toCharArray();	
				char [] randBuffer = new char[length];	
				for (int i=0; i<randBuffer.length; i++) {		             
					randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];		          
					}		         
				return new String(randBuffer);		
				}
}
