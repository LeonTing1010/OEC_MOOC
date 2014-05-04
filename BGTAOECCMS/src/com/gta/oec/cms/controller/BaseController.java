package com.gta.oec.cms.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.common.DateEditor;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.WebUtils;

/**
 * @description 基础控制器,所有的controll都继承这个控制器,一些公共的方法集成到这里
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-14 15:01:00
 * @version 1.0
 * 
 * */
public abstract class BaseController<E>{
	
	private static Logger log = Logger.getLogger(BaseController.class);
	
	/**
	 * json util format
	 * */
	protected Gson gson = new GsonBuilder().create();
	
	/**
	 * collection of the query marker
	 * */
	protected List<String> queryMarker = Arrays.asList("lt","gt","lte","gte","gp","op");
	
	/**
	 * @Description binding the date converter to the spring request data binder,
	 *    because in the form convert the properties will be use it, if 
	 *    you remove this function, the form submit to controller ,will throws 
	 *    some exception return http server error ; 400 bad request
	 * @author jianhua.huang[Bill Huang]
	 * @since  2014-04-03 15:01:00
	 * */
	@InitBinder  
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {  
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * init the pagination info into the PaginationContext with the parameter is pageIndex,pageSize
	 * @param PaginationContext<E> pc
	 * @param HttpServletRequest req
	 * @return PaginationContext<E> pc
	 * */
	public PaginationContext<E> initPaginationInfo(PaginationContext<E> pc,HttpServletRequest req){
		String pageIndex = req.getParameter(SystemConstant.PAGEINDEX);
		int currentPage = pageIndex==null?1:Integer.parseInt(pageIndex);
		String page_Size = req.getParameter(SystemConstant.PAGESIZE);
		int pageSize = page_Size==null?10:Integer.parseInt(page_Size);
		
		pc.setPageIndex(currentPage);
		pc.setPageSize(pageSize);
		pc = this.buildParametersInContext(pc, req);
		return pc;
	}
	
	/**
	 * init the pagination info into the PageMode with the parameter is pageIndex,pageSize
	 * @param PageModel<E> pm
	 * @param HttpServletRequest req
	 * @return PageModel<E> pm
	 * */
	public PageModel<E> initPaginationInfo(PageModel<E> pm,HttpServletRequest req){
		String pageIndex = req.getParameter(SystemConstant.PAGEINDEX);
		int currentPage = pageIndex==null?1:Integer.parseInt(pageIndex);
		String page_Size = req.getParameter(SystemConstant.PAGESIZE);
		int pageSize = page_Size==null?10:Integer.parseInt(page_Size);
		
		pm.setPageIndex(currentPage);
		pm.setPageSize(pageSize);
		return pm;
	}
	
	/**
	 * build the total and rows to the Map will return the map to client use json
	 * @return PaginationContext<E> page
	 * */
	public Map<String, Object> buildRetPageInfo(PaginationContext<E> page){
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put(SystemConstant.TOTAL, page.getTotalSize());// total键 // 存放总记录数，必须的
		jsonMap.put(SystemConstant.ROWS, page.getResult());// rows键 存放每页记录 list
		log.debug(this.gson.toJson(jsonMap));
		return jsonMap;
	}
	
	/**
	 * building the parameters to the pagination content from the request
	 * with the parameter end with the query marker
	 * @param PaginationContext<E> pc
	 * @param HttpServletRequest hr
	 * @return PaginationContext<E> pc
	 * */
	public PaginationContext<E> buildParametersInContext(PaginationContext<E> pc,HttpServletRequest req){
		pc.setParameters(this.buildParametersToMap(pc.getParameters(), req));
		return pc;
	}
	
	/**
	 * building the parameters to the map
	 * with the parameter end with the query marker
	 * @param Map<String,Object> map
	 * @param HttpServletRequest req
	 * @return Map<String,Object> map
	 * */
	public Map<String,Object> buildParametersToMap(Map<String,Object> map, HttpServletRequest req){
		Enumeration<?> e =  req.getParameterNames();
		while(e.hasMoreElements()){
			String name = (String)e.nextElement();
			for(String str : queryMarker){
				if(name.toUpperCase().endsWith(str.toUpperCase())){
					String parameter = req.getParameter(name);
					if(StringUtils.isNotEmpty(parameter)){
						map.put(name.substring(0,name.lastIndexOf("_")), parameter.trim());	
					}
					break;
				}else{
					continue;
				}
			}
		}
		return map;
	}
	/**
	 * 配置的常量路径
	 * @author can.xie
	 * @param req
	 * @param configurePath
	 * @return
	 */
	public  String findCtxPath(HttpServletRequest req,String configurePath){
		return WebUtils.getApplicationPath(req,configurePath);
	}
}
