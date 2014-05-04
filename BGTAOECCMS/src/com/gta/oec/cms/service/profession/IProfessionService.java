/**
 * ProfessionService.java	  V1.0   2014-3-28 上午11:24:53
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.profession;

import java.util.List;
import java.util.Map;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

public interface IProfessionService {
	

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
	 * 功能描述：获取所有的行业.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年4月1日 上午11:22:38</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Profession> getAllProfessions();
	
	
	/**
	 * 查询行业信息
	 * @author can.xie
	 * @date 2014.4.9
	 * @param params
	 * @return
	 */
	public List<Profession> getProfessionByParams(Map<String, Object> params)throws Exception;
	
	/**
	 * 查询岗位信息
	 * @author can.xie
	 * @date 2014.4.9
	 * @param params
	 * @return
	 */
	public List<Job> getJobByParams(Map<String, Object> params)throws Exception;
	
	/**
	 * 
	 * 功能描述：查询所有行业和下面的岗位
	 *
	 * @author  xiecan
	 * <p>创建日期 ：2014-3-28 上午11:26:33</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void getProfessionAndJobs(Map<String, Object> params)throws Exception;
	
	
	
	
	
}
