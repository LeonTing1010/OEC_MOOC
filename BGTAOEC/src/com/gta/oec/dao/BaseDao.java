/**
 * BaseDao.java	  V1.0   2013-12-27 ����10:44:46
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gta.oec.dao.course.CourseCommentDao;
import com.gta.oec.util.Assert;
import com.gta.oec.vo.common.PageModel;


public class BaseDao<E> extends SqlSessionDaoSupport{
	public static final String MAPPER_INSERT = "insert";
	public static final String MAPPER_DELETE = "delete";
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	private Class<E> entityClass;
	public BaseDao(){
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		this.entityClass = (Class<E>) trueType;
	}
    /**
     * 
     * 功能描述：查询list
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-3 上午10:45:33</p>
     *
     * @param statement
     * @param parameter
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public List<E> findList(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return (List<E>)this.getSqlSession().selectList(statement, parameter);
	}
	
    /**
     * 
     * 功能描述：查询list
     *
     * @author  刘祚家
     * <p>创建日期 ：2014-1-15 </p>
     *
     * @param statement
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public List<E> findList(String statement) {
		Assert.notEmpty(statement, "Property statement is required");
		return (List<E>)this.getSqlSession().selectList(statement);
	}
	
	/**
	 * 
	 * 功能描述：selectOne
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:45:47</p>
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Object selectOne(String statement, Object parameter){
		Assert.notEmpty(statement, "Property statement is required");
		return this.getSqlSession().selectOne(statement, parameter);
	}
	/**
	 * 
	 * 功能描述：批量删除
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:45:56</p>
	 *
	 * @param statement
	 * @param list
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.delete(statement, list.get(i));
		}
		return list.size();
	}
	
	/**
	 * 
	 * 功能描述：删除数据
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-2-11 下午3:44:25</p>
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int delete(String statement,Object  parameter)
	{
		Assert.notEmpty(statement, "Property statement is required");
		return this.getSqlSession().delete(statement, parameter);
	}
	/**
	 * 
	 * 功能描述：insert
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:46:11</p>
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int insert(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return this.getSqlSession().insert(statement, parameter);
	}
	/**
	 * 
	 * 功能描述： insert
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-6 下午1:20:48</p>
	 *
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int insert(Object parameter) {
		return this.getSqlSession().insert(generateStatement(MAPPER_INSERT), parameter);
	}
	/**
	 * 
	 * 功能描述：批量插入
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:46:27</p>
	 *
	 * @param statement
	 * @param list
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int insertBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.insert(statement, list.get(i));
		}
		return list.size();
	}
	/**
	 * 
	 * 功能描述：update
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:46:37</p>
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int update(String statement, Object parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		return this.getSqlSession().update(statement, parameter);
		
	}
	public int update(Object parameter) {
		return this.getSqlSession().update(generateStatement("update"), parameter);
		
	}
	/**
	 * 
	 * 功能描述：批量修改
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:46:48</p>
	 *
	 * @param statement
	 * @param list
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateBatch(String statement, List<?> list) {
		Assert.notEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.update(statement, list.get(i));
		}
		return list.size();
	}
	/**
	 * 
	 * 功能描述：查询分页
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-3 上午10:46:59</p>
	 *
	 * @param statement
	 * @param countStatement
	 * @param parameter
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel findPagedList(String statement,String countStatement,PageModel parameter) {
		Assert.notEmpty(statement, "Property statement is required");
		Assert.notEmpty(statement, "Property countStatement is required");
		
		if(parameter == null){
			return new PageModel();
		}
		//首次查询时返回总记录数，以后查询时传入totalItem，则不再查询。
		if(parameter.getTotalItem() <= 0){
			// 得到数据记录总数
			Integer totalItem = (Integer) this.getSqlSession().selectOne(countStatement, parameter);
			if(totalItem != null){
				parameter.setTotalItem(totalItem.intValue());
			}else{
				return new PageModel();
			}
		}
		List<E> list = null;
		list = (List<E>)this.getSqlSession().selectList(statement, parameter);
		parameter.setList(list);
		return parameter;
	}
    /**
     * 
     * 功能描述： mapperId:sqlmap配置文件sql语句id
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-3 上午10:47:16</p>
     *
     * @param mapperId
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public String generateStatement(String mapperId){
		return entityClass.getName() + "." + mapperId;		
	}
	
}
