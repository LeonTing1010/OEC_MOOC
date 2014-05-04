/**
 * ProfessionDao.java	  V1.0   2014-3-28 上午11:29:27
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.profession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

public interface IProfessionDao extends SqlMapper<Profession>{

	/**
	 * 
	 * 功能描述：查询所有行业信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Profession> getAllProfession();
	
	
	/**
	 * 
	 * 功能描述：按照职业名称 职业描述 是否推荐 查询
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Profession> findProfessionsPageQuery(PaginationContext<Profession> pc);
	
	
	
	/**
	 * 
	 * 功能描述：修改行业图片
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateProfessions(Profession profession);
	
	
	
	
	
	
	/**
	 * 根据条件查询行业下面的岗位群信息
	 * 条件为空查询全部
	 * @author can.xie
	 * 2014.3.29
	 * 
	 */
	public List<Job> getJobByParams(@Param(value="params")Map<String, Object> params);
	

	/**
	 * 根据条件查询行业信息
	 * 条件为空查询全部
	 * @author can.xie
	 * 2014.3.29
	 * 
	 */
	public List<Profession> getProfessionByParams(@Param(value="params")Map<String, Object> params);
	
	
}
