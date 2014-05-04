package com.gta.oec.cms.common.pagination;

import java.util.HashMap;
import java.util.Map;

/** 
 * @description 分页model和查询参数的上下文  
 * @author 黄建华(Bill Huang)  
 * @createDate 2014-03-18 19:58:34 
 * @file PaginationContext.java 
 * @package com.gta.oec.cms.common.pagination 
 * @email jianshaosky@126.com 
 * @version 1.0 
 */
public class PaginationContext<E> extends PageModel<E> {
	
	/*
	 * client query parameters
	 * */
	private Map<String,Object> parameters = new HashMap<String,Object>();
	
	/*
	 * client query parameter
	 * */
	private Object parameter;
	
	public PaginationContext() {}
	
	public PaginationContext(Map<String,Object> params,Object param){
		this.parameters = params;
		this.parameter = param;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void setParameters(Map<String, Object> parameters) {
		this.parameters.putAll(parameters);
	}
	
	public void addParameter(String key, Object value){
		this.parameters.put(key, value);
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
	
}
