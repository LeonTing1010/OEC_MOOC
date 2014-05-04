package com.gta.oec.cms.dao.professionjob;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

public interface IProfessionJobDao extends SqlMapper<Profession>{
	
	public int countJobCourseNum(Map<String,Object> parameters);
	
	/**
	 * 功能描述：保存岗位群，岗位的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月08日 下午01:57:27</p>
	 * @param Job
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void addJob(@Param(value="Job") Job job) throws DAOException;
	
	/**
	 * 功能描述：删除行业的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月11日 上午10:57:27</p>
	 * @param proId
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleteProfessionByProId(@Param(value="proId") int proId) throws DAOException;
	
	/**
	 * 功能描述：根据JobID删除岗位群或者岗位的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月08日 下午03:40:27</p>
	 * @param List<Integer> jobIds
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleteJobByIds(@Param(value="jobIds") List<Integer> jobIds) throws DAOException;
	
	/**
	 * 功能描述：删除岗位群下面的子岗位的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月11日  上午10:40:27</p>
	 * @param List<Integer> jobIds
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void deleteJobByPids(@Param(value="jobIds") List<Integer> jobIds) throws DAOException;
	
	/**
	 * 功能描述：更新岗位群，岗位的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月08日 下午01:57:27</p>
	 * @param Job
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateJob(@Param(value="Job") Job job) throws DAOException;
	
	/**
	 * 功能描述：更新岗位群，岗位的是否隐藏.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月22日 下午03:57:27</p>
	 * @param parameters
	 * @return void
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * */
	public void updateJobVisibleByParameters(Map<String,Object> parameters) throws DAOException;
	
	/**
	 * 功能描述：保存行业的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月02日 上午10:57:27</p>
	 * @param Profession
	 * @return
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void saveProfession(@Param(value="profession") Profession profession) throws DAOException;
	
	/**
	 * 功能描述：更新行业的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月02日 上午10:57:27</p>
	 * @param Profession
	 * @return
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateProfession(@Param(value="profession") Profession profession) throws DAOException;
	
	/**
	 * 功能描述：通过JobID查询Job信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月08日 下午3:57:27</p>
	 * @param int
	 * @return Job
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	Job findJobByJobId(@Param(value="jobID") int jobID);
	
	public List<Profession> findProfessionPageQuery(PaginationContext<Profession> pc);
	
	public List<Job> findJob(@Param(value="proId") int proId);
	
	public List<Job> findChildJobByProId(@Param(value="proId") int proId);
	
	public List<Job> findChildJob(@Param(value="proId") int proId, @Param(value="jobPID") int jobPID);
	
	/**
	 * 功能描述：通过JobPID查询子Job的信息.
	 * @author  jianhua.huang
	 * <p>创建日期 ：2014年04月09日 下午2:13:27</p>
	 * @param int
	 * @return List<Job>
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> selectChildJobByJobPID(@Param(value="jobPID") int jobPID);
	
	public List<Profession> findChildPrfession(@Param(value="proId") int proId);
	
	/**
	 * 功能描述：查询一个行业的所有岗位群信息.id为0.查询所有岗位群.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月30日 上午10:57:27</p>
	 *
	 * @param proId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Job> findJobGroupListByProId(@Param(value="proId") int proId)throws DAOException;
}
