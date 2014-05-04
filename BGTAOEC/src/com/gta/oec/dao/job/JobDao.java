package com.gta.oec.dao.job;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.job.JobVo;

public interface JobDao {
	
	public JobVo getJobDetail(Integer job);

	/**
	 * 
	 * 功能描述：根据行业ID查询岗位群
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-9 下午3:27:17</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getJobGroup(JobVo jobVo);
	/**
	 * 
	 * 功能描述：根据岗位群ID查询岗位群下的岗位信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-9 下午3:35:53</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getJobByJobGroupId(int jobGroupId);
	
	/**
	 * 
	 * 功能描述：显示所有岗位信息
	 * 根据岗位群查询岗位信息
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-9 下午5:30:08</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getAllJobDetail(JobVo jobVo,int page ,int pageSize);
	
	/**
	 * 
	 * 功能描述：岗位收藏数
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-21 上午10:52:47</p>
	 *
	 * @param jobVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getCountJobDetail(JobVo jobVo);
	
	/**
	 * 
	 * 功能描述：更新岗位收藏数
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-21 上午10:52:47</p>
	 *
	 * @param jobVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateMyCollectCount(int jobId);
	
	
	/**
	 * 
	 * 功能描述：根据老师ID,得到老师所属行业、岗位群;
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-21</p>
	 *
	 * @param teacherId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public JobVo getTeacherProfessionAddJob(int teacherId);
	
	/**
	 * 
	 * 功能描述：岗位收藏，用户收藏的岗位名称
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-13 上午9:50:31</p>
	 *
	 * @param list
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getJobNameList(List list,int pageNo,int pageSize);
	
	/**
	 * 
	 * 功能描述：岗位收藏，收藏岗位数
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-18 上午10:31:31</p>
	 *
	 * @param list
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getJobNameCount(List list);
	
	/**
	 * 
	 * 功能描述：根据行业id查询该行业下所有的岗位信息
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-20 下午7:39:42</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getJobByTradeId(int tradeId,int pageNo,int pageSize);
	public int getJobCountByTradeId(int tradeId);
	
	/**
	 * 
	 * 功能描述：根据岗位群id，查询该岗位群下的所有岗位
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-22 上午11:06:03</p>
	 *
	 * @param jobId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：bingzhogn.qin : 修改命名</p>
	 */
	public List<JobVo> getJobByJobGroupId(int jobGroupId,int pageNo,int pageSize);
	public int getJobCountByJobGroupId(int jobGroupId);
	/**
	 * 
	 * 功能描述：根据行业ID查询岗位群
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-9 下午3:27:17</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> getJobGroupByProId(int proId);
	
	/**
	 * 
	 * 功能描述：查询某岗位岗位群下其他相关岗位
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月20日 下午2:10:22</p>
	 *
	 * @param jobVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> selectRelateJob(JobVo jobVo);
	/**
	 * 
	 * 功能描述：获取岗位列表-分页
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-4-1 下午5:19:31</p>
	 *
	 * @param jobVo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<JobVo> queryJobList(JobVo jobVo,int pageNo ,int pageSize) throws BaseException;
	public int queryJobListCount(JobVo jobVo) throws BaseException;
}

