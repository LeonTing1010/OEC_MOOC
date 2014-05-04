/**
 * BaseDao.java	  V1.0   2014-4-24 下午6:14:21
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;


public class BaseDao<E> extends SqlSessionDaoSupport {
	
	public static final String MAPPER_INSERT = "insert";
	public static final String MAPPER_UPDATE = "update";
	public static final String MAPPER_DELETE = "delete";
	
	private Class<E> entityClass;
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public BaseDao(){
		Type[] types = getClass().getGenericInterfaces();
		this.entityClass = (Class<E>) types[0];
	}

	private void checkStatementIsEmpty(String statement,String message){
		if (StringUtils.isEmpty(statement)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public List<E> findList(String statement, Object parameter) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		return this.getSqlSession().selectList(statement, parameter);
	}
	
	public List<E> findList(String statement) {
		return this.getSqlSession().selectList(statement);
	}
	
	public Object selectOne(String statement, Object parameter){
		this.checkStatementIsEmpty(statement, "Property statement is required");
		return this.getSqlSession().selectOne(statement, parameter);
	}
	
	public int deleteBatch(String statement, List<?> list) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.delete(statement, list.get(i));
		}
		return list.size();
	}
	
	public int delete(String statement,Object  parameter){
		this.checkStatementIsEmpty(statement, "Property statement is required");
		return this.getSqlSession().delete(statement, parameter);
	}
	
	public int delete(Object parameter){
		return this.getSqlSession().delete(generateStatement(MAPPER_DELETE), parameter);
	}
	
	public int insert(String statement, Object parameter) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		return this.getSqlSession().insert(statement, parameter);
	}
	
	public int insert(Object parameter) {
		return this.getSqlSession().insert(generateStatement(MAPPER_INSERT), parameter);
	}
	
	public int insertBatch(String statement, List<?> list) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		int len = list.size();
		int j = 0;
		for (int i = 0; i < len; i++) {
			j += sqlSession.insert(statement, list.get(i));
		}
		return j;
	}
	
	public int update(String statement, Object parameter) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		return this.getSqlSession().update(statement, parameter);
		
	}
	
	public int update(Object parameter) {
		return this.getSqlSession().update(generateStatement(MAPPER_UPDATE), parameter);
	}
	
	public int updateBatch(String statement, List<?> list) {
		this.checkStatementIsEmpty(statement, "Property statement is required");
		if(list == null || list.isEmpty()){
			return 0;
		}
		SqlSession sqlSession = this.getSqlSession();
		for (int i = 0; i < list.size(); i++) {
			sqlSession.update(statement, list.get(i));
		}
		return list.size();
	}
	
	public String generateStatement(String mapperId){
		return entityClass.getName() + "." + mapperId;		
	}
}
