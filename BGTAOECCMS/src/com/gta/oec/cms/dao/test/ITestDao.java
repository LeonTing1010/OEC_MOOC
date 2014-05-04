/**
 * TestDao.java	  V1.0   2014-4-25 上午10:43:44
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.test;

import java.util.List;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.test.TestVo;

public interface ITestDao {
	
	int insert(TestVo tvo) throws DAOException;
	int update(TestVo tvo) throws DAOException;
	int delete(TestVo tvo) throws DAOException;
	
	int insertBatch(List<TestVo> list) throws DAOException;
	int updateBatch(List<TestVo> list) throws DAOException;
	int deleteBatch(List<TestVo> list) throws DAOException;
	
	List<TestVo> findList();
	List<TestVo> findListPageQuery(PaginationContext<TestVo> page);
}
