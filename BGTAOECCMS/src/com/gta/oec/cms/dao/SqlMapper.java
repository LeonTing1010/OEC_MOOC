package com.gta.oec.cms.dao;

import java.util.List;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.exception.DAOException;

/**
 * @description : 所有的操作数据库的DAO;都必须继承SqlMapper这个父接口
 *   mybaits在启动加载的时候，会自动扫描加载DAO
 * 
 * @author jianhua.huang[Bill Huang]
 * @since 2014-03-13 18:01:00
 * @version 1.0
 * 
 * */
public interface SqlMapper<E> {
	
	/**
	 * @分页信息查询
	 * */
	public List<E> paginationContextPageQuery(PaginationContext<E> pc) throws DAOException;
	
	public List<E> pageModelPageQuery(PageModel<E> pm) throws DAOException;
	
}
