package com.gta.oec.dao.job;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.job.JobAuthenticationVo;

public interface JobAuthenticationDao {
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位认证列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-9 下午19:0</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobAuthenticationVo> getJobAuthenticationList(int jobId);
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位推荐认证列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobAuthenticationVo> getJobAuthRecommendList(int jobId);
	
}

