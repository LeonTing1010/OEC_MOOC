package com.gta.oec.dao.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.job.JobVo;


@Repository
public class JobDaoImpl extends BaseDao<JobVo> implements JobDao{
	
	@Override
	public JobVo getJobDetail(Integer id){
		return (JobVo)super.selectOne(generateStatement("getJobDetailById"),id);
	}


	@Override
	public List<JobVo> getJobGroup(JobVo jobVo) {
		return super.findList(generateStatement("getJobGroup"), jobVo);
	}

	@Override
	public List<JobVo> getJobByJobGroupId(int proId) {
		return super.findList(generateStatement("getJobByJobGroupId"), proId);
	}

	
	@Override
	public List<JobVo> getAllJobDetail(JobVo jobVo,int page ,int pageSize){
		Map< String,Object> map = new HashMap<String, Object>();
		map.put("jobVo", jobVo);
		map.put("n", (page-1)*pageSize);
		map.put("m",pageSize);
		return super.findList(generateStatement("getAllJobDetail"), map);
	}
	
	@Override
	public int getCountJobDetail(JobVo jobVo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobVo", jobVo);
		return (Integer)super.selectOne(generateStatement("getCountJobDetail"), map);
	}
	
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
	public int updateMyCollectCount(int jobId){
		return super.update(generateStatement("updateMyCollectCount"),jobId);
	}
	
	
	/**
	 * 
	 * 功能描述：根据用户ID,得到老师所属行业、岗位群
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
	public JobVo getTeacherProfessionAddJob(int userId){
		return (JobVo)super.selectOne(generateStatement("getTeacherProfessionAddJob"), userId);
	}
	
	@Override
	public List<JobVo> getJobNameList(List list,int pageNo,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("joblist", list);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getJobNameList"), map);
	}
	
	@Override
	public int getJobNameCount(List list){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("joblist", list);
		return (Integer)super.selectOne(generateStatement("JobNameCount"), map);
	}
	
	@Override
	public List<JobVo> getJobByTradeId(int tradeId,int pageNo,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tradeId", tradeId);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m",pageSize);
		return super.findList(generateStatement("getJobByTradeId"), map);
	}
	public int getJobCountByTradeId(int tradeId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tradeId", tradeId);
		return (Integer) super.selectOne(generateStatement("getJobCountByTradeId"), map);
	}
	@Override
	public List<JobVo> getJobByJobGroupId(int jobGroupId,int pageNo,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobGroupId", jobGroupId);
		map.put("m", pageSize);
		map.put("n", (pageNo-1)*pageSize);
		return super.findList(generateStatement("queryJobByJobGroupId"),map);
	}
	public int getJobCountByJobGroupId(int jobGroupId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobGroupId", jobGroupId);
		return (Integer) super.selectOne(generateStatement("queryJobCountByJobGroupId"), map);
	}


	@Override
	public List<JobVo> getJobGroupByProId(int profId) {
		return super.findList(generateStatement("getJobGroupByProId"), profId);
	}


	@Override
	public List<JobVo> selectRelateJob(JobVo jobVo) {
		return super.findList(generateStatement("selectRelateJob"),jobVo);
	}


	@Override
	public List<JobVo> queryJobList(JobVo jobVo, int pageNo, int pageSize)
			throws BaseException {
		Map< String,Object> map = new HashMap<String, Object>();
		map.put("jobVo", jobVo);
		map.put("n", (pageNo-1)*pageSize);
		map.put("m",pageSize);
		return super.findList(generateStatement("queryJobList"),map);
	}


	@Override
	public int queryJobListCount(JobVo jobVo) throws BaseException {
		Map< String,Object> map = new HashMap<String, Object>();
		map.put("jobVo", jobVo);
		return (Integer) super.selectOne(generateStatement("queryJobListCount"), map);
	}
}
