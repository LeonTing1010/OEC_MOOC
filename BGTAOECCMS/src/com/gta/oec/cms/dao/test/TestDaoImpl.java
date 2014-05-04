/**
 * TestDaoImpl.java	  V1.0   2014-4-25 上午11:02:38
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.test;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.BaseDao;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.test.TestVo;

@Repository
public class TestDaoImpl extends BaseDao<TestVo> implements ITestDao {

	@Override
	public int insert(TestVo tvo) throws DAOException {
		return super.insert(tvo);
	}

	@Override
	public int update(TestVo tvo) throws DAOException {
		return super.update(tvo);
	}

	@Override
	public int delete(TestVo tvo) throws DAOException {
		return super.delete(tvo);
	}

	@Override
	public int insertBatch(List<TestVo> list) throws DAOException {
		return super.insertBatch(this.generateStatement(MAPPER_INSERT), list);
	}

	@Override
	public int updateBatch(List<TestVo> list) throws DAOException {
		return super.updateBatch(generateStatement(MAPPER_UPDATE), list);
	}

	@Override
	public int deleteBatch(List<TestVo> list) throws DAOException {
		return super.deleteBatch(generateStatement(MAPPER_DELETE), list);
	}

	@Override
	public List<TestVo> findList() {
		return super.findList(generateStatement("select"));
	}

	@Override
	public List<TestVo> findListPageQuery(PaginationContext<TestVo> page) {
		return super.findList(generateStatement("selectByPageQuery"), page);
	}
	
}
