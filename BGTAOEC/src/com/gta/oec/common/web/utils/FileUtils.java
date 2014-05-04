/**
 * FileUtils.java	  V1.0   2014-1-16 上午8:48:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.utils;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gta.oec.vo.course.SectionVO;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.reflect.generics.tree.Tree;

public class FileUtils {
  private FileUtils() {}
  /**
   * 
   * 功能描述：校验文件
   *
   * @author  bingzhong.qin
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
	System.out.println(new ArrayList<SectionVO>().size());
   }
}
