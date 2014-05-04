package com.gta.oec.service.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.job.CourseJobDao;
import com.gta.oec.dao.job.JobAuthenticationDao;
import com.gta.oec.dao.job.JobDao;
import com.gta.oec.dao.mycollect.MyCollectDao;
import com.gta.oec.dao.trade.TradeDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.job.JobAuthenticationVo;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.mycollect.MyCollectVo;
import com.gta.oec.vo.trade.TradeVo;
import com.gta.oec.vo.user.UserVo;

@Service
public class jobServiceImpl implements JobService {
	private static final Log logger = LogFactory.getLog(jobServiceImpl.class);
	@Autowired
	private JobDao jobDao;
	@Autowired 
    private TradeDao tradeDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private JobAuthenticationDao jobAuthenticationDao;
	@Autowired
	private CourseJobDao courseJobDao;
	@Autowired
	private MyCollectDao myCollectDao;
	

	public List<JobVo> getJobGroupDetailByProid(int proId) throws BaseException {
		
		if (proId <= 0) {
			throw new InterfaceFieldExcepiton("proId is null!");
		}
		try {
			 List<JobVo> list = getJobGroupByProid(proId);
			 for (JobVo jobVo:list) {
				jobVo.setList(getJobByJobGroupId(jobVo.getJobID()));
			}
			 return list;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}


	@Override
	public List<JobVo> getJobGroupByProid(int proId) throws BaseException {
		if (proId <= 0) {
			throw new InterfaceFieldExcepiton("proId is null!");
		}
		try {
			JobVo jobVo = new JobVo();
			jobVo.setJobPID(proId);
			return jobDao.getJobGroup(jobVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

	}

	@Override
	public List<JobVo> getJobByJobGroupId(int jobGroupId) throws BaseException {
		if (jobGroupId <= 0) {
			throw new InterfaceFieldExcepiton("proId is null!");
		}
		try {
          return jobDao.getJobByJobGroupId(jobGroupId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

	}

	@Override
	public List<TradeVo> getTradeList(TradeVo tradeVo,int page,int pageSize) throws BaseException {
		try {
			return tradeDao.getTradeInfo(tradeVo,page,pageSize);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public List<JobVo> getJobList(JobVo jobVo,int page ,int pageSize) throws BaseException{
		try{
			return jobDao.getAllJobDetail(jobVo,page, pageSize);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}

	@Override
	public int getCountJobDetail(JobVo jobVo) throws BaseException{
		try{
			return jobDao.getCountJobDetail(jobVo);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	/**
	 * 
	 * 功能描述：根据岗位ID查询岗位信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-9 下午19:0</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@Override
	public JobVo getPositionDetail(UserVo userVo,int jobId) throws BaseException{
		try{
			JobVo jobVo = new JobVo();//岗位
			List<MyCollectVo> myCollectVos = new ArrayList<MyCollectVo>();
			jobVo =jobDao.getJobDetail(jobId);
			
			if(userVo!=null){
				//判断该岗位是否已收藏 
				myCollectVos=myCollectDao.getCollectListByUserId(userVo.getUserId(),jobId, 1);
				int collectCount = myCollectDao.jobHasColl(jobId, 0, 1);
				jobVo.setJobCollectCount(collectCount);
				if (myCollectVos.size() == 0) {
					jobVo.setCollectSign("立即收藏");
				} else {
					jobVo.setCollectSign("已收藏");
				}
			}else{
				jobVo.setCollectSign("立即收藏");
			}
			return jobVo;
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
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
	public List<JobAuthenticationVo> getJobAuthenticationList(int jobId) throws BaseException{
		try{
			return jobAuthenticationDao.getJobAuthenticationList(jobId);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
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
	public List<JobAuthenticationVo> getJobAuthRecommendList(int jobId) throws BaseException{
		try{
			return jobAuthenticationDao.getJobAuthRecommendList(jobId);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobList(int jobId) throws BaseException{
		try{
			return courseJobDao.getCourseJobList(jobId);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位推荐课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobRecommendList(int jobId) throws BaseException{
		try{
			return courseJobDao.getCourseJobRecommendList(jobId);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	/**
	 * 
	 * 功能描述：根据岗位ID得到岗位所有课程列表信息
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-1-10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<CourseJobVo> getCourseJobAllList(int jobId) throws BaseException{
		if(jobId<0){
			throw new InterfaceFieldExcepiton("jobId Not less than zero");
		}
		try{
			return courseJobDao.getCourseJobAllList(jobId);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	@Override
	public List<JobVo> getJobGroupDetailList(JobVo jobVo)
			throws BaseException {
		try{
		List<JobVo> list = jobDao.getJobGroup(null);
		if(list!=null){
			for (int i=0;i<list.size();i++) {
				List<JobVo> jobList = jobDao.getJobByJobGroupId(list.get(i).getJobID());		
				list.get(i).setList(jobList);
				
			}
		}
	    return list;
	  }catch(Exception e){
		logger.error("DB Operate Error", e);
		throw new SystemDBException("DB Operate Error!"); 
	  }
	}
	
	
	@Override
	public List<CourseVo> getCourseByJobId(int jobId,List list,int page,int pageSize)throws BaseException {
		if (jobId <= 0) {
			throw new InterfaceFieldExcepiton("jobId is null!");
		}
		try {
			 return courseDao.getCollectInfo(jobId,list,page,pageSize);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	@Override
	public int getCourseCount(List list)throws BaseException{
		try {
			 return courseDao.getCourseCount(list);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
//	@Override
//	public List<JobVo> getAllJobInfo(JobVo jobVo,int page,int pageSize) throws BaseException {
//		try{
//			int userId = 0;
//			List<JobVo> list = getAllJobDetail(jobVo,page,pageSize);
//			 for (JobVo jobListVo:list) {
//				 jobListVo.setJobName(jobListVo.getJobName());
//				 jobListVo.setCourlist(getCourseByJobId(jobListVo.getJobID(),userId,page,pageSize));
//			}
//			 return list;
//		  }catch(Exception e){
//			logger.error("DB Operate Error", e);
//			throw new SystemDBException("DB Operate Error!"); 
//		  }
//	}
	
	@Override
	public PageModel getAllJobInfoByUid(JobVo jobVo,List list,Integer pageNo,Integer pageSize) throws BaseException{
		if(null==pageNo || pageNo.intValue()<=0 || pageSize==null || pageSize.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		
		try{
			List<CourseVo> joblist = null;
			joblist = getCourseByJobId(1,list,pageNo,pageSize);
			int totalItem = getCourseCount(list);
			 pageModel.setTotalItem(totalItem);
			 pageModel.setList(joblist);
			 pageModel.setToPage(pageNo);
			 pageModel.setPageSize(pageSize);
			 return pageModel;
		  }catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		  }
	}



	@Override
	public List<CourseJobVo> getAssociatedJobByCourseId(Integer courseId)
			throws BaseException {
		if (null == courseId || courseId.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			CourseJobVo courseJobVo = new CourseJobVo();
			courseJobVo.setCourseID(courseId);
			return 	 courseJobDao.getCourseJobList(courseJobVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	
	/**
	 * 
	 * 功能描述：根据老师ID,得到老师所属行业、岗位群
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
	public JobVo getTeacherProfessionAddJob(int teacherId) throws BaseException{
		try{
			JobVo jobVo = new JobVo();
			jobVo = jobDao.getTeacherProfessionAddJob(teacherId);
			return jobVo;
		  }catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		  }
	}
	
	@Override
	public PageModel getCourNameList(List<JobVo> list,Integer page,Integer pageSize) throws BaseException{
		PageModel pageModel = new PageModel();
		try{
			List<CourseVo> courList = null,courNameList= null;
			int jobid = 0;
			
			if(list!=null){
				for(int i=0;i<list.size();i++){
					courList = getCourseByJobId(list.get(i).getJobID(),null,page,pageSize);
					list.get(i).setCourlist(courList);
				}
			}
			 pageModel.setList(list);
			 pageModel.setToPage(page);
			 pageModel.setPageSize(pageSize);
			 return pageModel;
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		  }
	}
	
	@Override
	public PageModel getJobNameList(List list,Integer page,Integer pageSize)throws BaseException {
		PageModel pageModel = new PageModel();
		try{
			List<JobVo> jobName = jobDao.getJobNameList(list,page,pageSize);
			List<CourseVo> courList = null;
			if(jobName!=null){
				for(int i=0;i<jobName.size();i++){
					courList = getCourseByJobId(jobName.get(i).getJobID(),null,0,0);
					jobName.get(i).setCourlist(courList);
					//岗位包含的课程数
					jobName.get(i).setCourCount(courList.size());
				}
			}
			int totalItem = jobDao.getJobNameCount(list);
			pageModel.setList(jobName);
			pageModel.setTotalItem(totalItem);
			pageModel.setToPage(page);
			pageModel.setPageSize(pageSize);
			return pageModel;
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		  }
	}
	
	@Override
	public int jobHasColl(Integer jobId,Integer userId,Integer collType) throws BaseException{
		if (null == jobId || jobId.intValue()<=0 || null==userId || userId.intValue()<=0 || null==collType || collType.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			return myCollectDao.jobHasColl(jobId,userId,collType);
		} catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	@Override
	public void collJob(Integer jobId,Integer userId,Integer collectype) throws BaseException{
		if (null == jobId || jobId.intValue()<=0 || null==userId || userId.intValue()<=0 || null==collectype || collectype.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			if (0==jobHasColl(jobId, userId, collectype)) {
				myCollectDao.collJob(jobId,userId,collectype);
			}
		} catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	@Override
	public PageModel getJobByTradeId(Integer tradeId,int pageNo,int pageSize) throws BaseException{
		if(null==tradeId || tradeId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			PageModel pageModel = new PageModel();
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(jobDao.getJobByTradeId(tradeId, pageNo, pageSize));
			pageModel.setTotalItem(jobDao.getJobCountByTradeId(tradeId));
			return pageModel;
		} catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}
	
	@Override
	public PageModel getJobByJobGroupId(Integer jobGroupId,int pageNo,int pageSize) throws BaseException{
		if(null==jobGroupId || jobGroupId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			PageModel pageModel = new PageModel();
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(jobDao.getJobByJobGroupId(jobGroupId));
			pageModel.setTotalItem(jobDao.getJobCountByJobGroupId(jobGroupId));
			return pageModel;
		} catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	@Override
	public List<CourseJobVo> getCourseJobBycourseId(int courseId) throws BaseException{
		if(courseId<0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			return courseJobDao.getCourJobInfo(courseId);
		} catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	@Override
	public List<JobVo> getRelateJob(UserVo userVo,JobVo jobVo) throws BaseException {
		if(null==jobVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			List<MyCollectVo> myCollectVos = new ArrayList<MyCollectVo>();
			List<JobVo> jobList=jobDao.selectRelateJob(jobVo);
			if(jobList.size()>0){
				for (JobVo jobVo2 : jobList) {
					if(userVo!=null){
						//判断该岗位是否已收藏 
						myCollectVos=myCollectDao.getCollectListByUserId(userVo.getUserId(),jobVo2.getJobID(), 1);
						int collectCount = myCollectDao.jobHasColl(jobVo2.getJobID(), 0, 1);
						jobVo2.setJobCollectCount(collectCount);
						if (myCollectVos.size() == 0) {
							jobVo2.setCollectSign("收藏");
						} else {
							jobVo2.setCollectSign("已收藏");
						}
					}else{
						jobVo2.setCollectSign("收藏");
					}
				}
				
			}
			
			return jobList;
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	@Override
	public PageModel queryJobList(JobVo jobVo, int pageNo, int pageSize)
			throws BaseException {
		
		try {
			PageModel pageModel = new PageModel();
			pageModel.setPageSize(pageSize);
			pageModel.setToPage(pageNo);
			pageModel.setList(jobDao.queryJobList(jobVo, pageNo, pageSize));
			pageModel.setTotalItem(jobDao.queryJobListCount(jobVo));
			return pageModel;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}


	@Override
	public List<CourseJobVo> getRelativityCourseListByJobId(int jobID,
			int courseID) throws SystemDBException {
		try{
			return courseJobDao.getRelativityCourseListByJobId(jobID,courseID);
		}catch(Exception e){
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!"); 
		}
	}

}
