package com.gta.oec.cms.service.professionjob;

import java.util.List;
import java.util.Map;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

public interface ProfessionJobService {
	
	/**
	 * 功能描述：统计行业，岗位群，岗位包含课程数
	 * @author jianhua.huang
	 * @param int proId
	 * @param int jobPID
	 * @param int jobID  //int proId, int jobPID, int jobID
	 * <p>创建日期 ：2014-04-22 10:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getJobCourseNum(Map<String,Object> parameters);
	
	/**
	 * 功能描述：更新行业信息的是否隐藏
	 * @author jianhua.huang
	 * @param Profession profession
	 * <p>创建日期 ：2014-04-22 10:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateProfessionVisable(Profession profession) throws ServiceException;
	
	/**
	 * 功能描述：更新行业信息的是否隐藏
	 * @author jianhua.huang
	 * @param Map<String,Object> parameters
	 * <p>创建日期 ：2014-04-22 10:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateJobVisibleWithParameters(Map<String,Object> parameters) throws ServiceException;
	
	/**
	 * 功能描述：保存岗位群岗位信息
	 * @author jianhua.huang
	 * @param Job p
	 * <p>创建日期 ：2014-04-01 下午1:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveJob(Job job) throws ServiceException;
	
	/**
	 * 功能描述：更新保存岗位群岗位信息
	 * @author jianhua.huang
	 * @param Job p
	 * <p>创建日期 ：2014-04-01 下午1:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateJob(Job job) throws ServiceException;
	
	/**
	 * 功能描述：删除岗位群岗位信息
	 * @author jianhua.huang
	 * @param Job p
	 * <p>创建日期 ：2014-04-01 下午1:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void remove(List<Integer> jobIds) throws ServiceException;
	
	/**
	 * 功能描述：删除行业信息，以及行业下的岗位群和岗位信息
	 * @author jianhua.huang
	 * @param proId 
	 * @param jobIds
	 * <p>创建日期 ：2014-04-11 上午10:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void removeProfessionAndChildJob(int proId,List<Integer> jobIds) throws ServiceException;
	
	/**
	 * 功能描述：保存行业分类信息
	 * @author jianhua.huang
	 * @param Profession p
	 * <p>创建日期 ：2014-04-01 下午1:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveProfession(Profession p) throws ServiceException;
	
	/**
	 * 功能描述：更新行业分类信息
	 * @author jianhua.huang
	 * @param Profession p
	 * <p>创建日期 ：2014-04-03 下午1:16:03</p>
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateProfession(Profession p) throws ServiceException;
	
	/**
	 * 功能描述：分页查询行业分类
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-3-25 下午1:16:03</p>
	 * @return PaginationContext<Profession>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PaginationContext<Profession> findProfessionPageQuery(PaginationContext<Profession> pc);

	/**
	 * 功能描述：通过行业ID查询岗位
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-3-25 下午1:16:03</p>
	 * @return List<Job>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findJob(int proId);
	
	/**
	 * 功能描述：通过岗位群ID查询岗位群或者岗位
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-04-08 下午1:16:03</p>
	 * @return Job
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Job findJobByJobId(int jobId);

	/**
	 * 功能描述：通过行业ID查找子的岗位
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-3-25 下午1:16:03</p>
	 * @return List<Job>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findChildJobByProId(int proId);

	/**
	 * 功能描述：获得某个行业下的所有岗位群.如果id为0则获得全部岗位群.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月30日 上午11:05:38</p>
	 *
	 * @param proId行业id.
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findJobGroupListByProId(int proId);

	/**
	 * 功能描述：通过行业ID,父岗位ID查找子的岗位
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-3-25 下午1:16:03</p>
	 * @return List<Job>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findChildJob(int proId,int jobPID);
	
	/**
	 * 功能描述：通过父岗位ID查找子的岗位信息
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-04-09 下午2:16:03</p>
	 * @return List<Job>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findChildJobByJobPID(int jobPID);
	
	/**
	 * 功能描述：通过行业ID查找子的行业 
	 * @author jianhua.huang
	 * <p>创建日期 ：2014-3-25 下午1:16:03</p>
	 * @return List<Profession>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Profession> findChildPrfession(int proId);
}
