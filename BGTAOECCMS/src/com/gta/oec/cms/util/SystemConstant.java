package com.gta.oec.cms.util;

import java.util.Arrays;
import java.util.List;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;

/**
 * @description : 一些系统的常量
 * @author jianhua.huang[Bill huang]
 * @since 2014-03-24 13:20
 * 
 * */
public final class SystemConstant {
	/**分页常量--当前页数*/
	public static final String PAGEINDEX = "pageIndex";
	/**分页常量--每页大小*/
	public static final String PAGESIZE = "pageSize";
	/**分页常量--总记录数*/
	public static final String TOTAL = "total";
	/**分页常量--显示数据*/
	public static final String ROWS = "rows";
	
	public static final String USERPATH=ApplicationPropertiesUtil.getStringValue("imgRoot");
	
	public static final String schURLPATH="images/schoolmsg/";
	
	public static final String proJobPATH=ApplicationPropertiesUtil.getStringValue("imgProJob");
	
	public static final String proPATH =ApplicationPropertiesUtil.getStringValue("imgPro");
	/** 系统加载properties文件通过spring bean加载的bean id */
	public static final String PROPERTYCONFIGURERKEY = "propertyConfigurer";
	/** 系统加载properties文件后根据需要将一些变量放到application里面去的前缀 */
	public static final List<String> PUTAPPLICATIONVARIABLEPREFIX = Arrays.asList("Resources");
	/** 存放在session中登录用户的user对象的标记 */
	public static final String LOGINUSERMARKER = "LOGINUSERMARKER";
	/** 用来标记不需要保护的url在系统初始化的时候加载 */
	public static final String EXCLUDEDURLS = "excludedUrls";
	/** 用来标记不需要保护的目录在系统初始化的时候加载 */
	public static final String EXCLUDEDDIRS = "excludedDirs";
}
