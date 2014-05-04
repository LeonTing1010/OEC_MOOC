package com.gta.oec.cms.service.professionjob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.professionjob.IProfessionJobDao;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;

@Service
public class ProfessionJobServiceImpl implements ProfessionJobService {

	@Autowired
	private IProfessionJobDao iprofessionJobDao;

	@Override
	public PaginationContext<Profession> findProfessionPageQuery(PaginationContext<Profession> pc) {
		List<Profession> ret = null; 
		try {
			ret = iprofessionJobDao.paginationContextPageQuery(pc);
			pc.setResult(ret);
			return pc;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return pc;
	}

	@Override
	public List<Job> findJob(int proId) {
		return iprofessionJobDao.findJob(proId);
	}

	@Override
	public List<Job> findChildJobByProId(int proId) {
		return iprofessionJobDao.findChildJobByProId(proId);
	};

	@Override
	public List<Profession> findChildPrfession(int proId) {
		return iprofessionJobDao.findChildPrfession(proId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.cms.service.professionjob.ProfessionJobService#
	 * findJobGroupListByProId(int)
	 */
	@Override
	public List<Job> findJobGroupListByProId(int proId) {
		List<Job> jobList = new ArrayList<Job>();
		if (proId >= 0) {
			try {
				jobList = iprofessionJobDao.findJobGroupListByProId(proId);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return jobList;
	}

	@Override
	public List<Job> findChildJob(int proId, int jobPID) {
		return iprofessionJobDao.findChildJob(proId, jobPID);
	}

	@Override
	public void saveProfession(Profession p) throws ServiceException {
		try {
			iprofessionJobDao.saveProfession(p);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateProfession(Profession p) throws ServiceException {
		try {
			iprofessionJobDao.updateProfession(p);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveJob(Job job) throws ServiceException {
		try {
			System.out.println(job.getJobDuty()+"########################");
			iprofessionJobDao.addJob(job);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public Job findJobByJobId(int jobId) {
		return iprofessionJobDao.findJobByJobId(jobId);
	}

	@Override
	public void updateJob(Job job) throws ServiceException {
		try {
			System.out.println(job.getJobDuty()+"update$%$$$$$$$$$$");
			iprofessionJobDao.updateJob(job);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Job> findChildJobByJobPID(int jobPID) {
		return iprofessionJobDao.selectChildJobByJobPID(jobPID);
	}

	@Override
	public void remove(List<Integer> jobIds) throws ServiceException {
		try {
			iprofessionJobDao.deleteJobByIds(jobIds);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=java.lang.Throwable.class)
	@Override
	public void removeProfessionAndChildJob(int proId, List<Integer> jobIds)
			throws ServiceException {
		try {
			//删除行业
			iprofessionJobDao.deleteProfessionByProId(proId);
			if(jobIds.size()>0){
				//删除行业下面的子岗位群
				iprofessionJobDao.deleteJobByIds(jobIds);
				//删除岗位群下面的子岗位
				iprofessionJobDao.deleteJobByPids(jobIds);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateProfessionVisable(Profession profession)
			throws ServiceException {
		try {
			iprofessionJobDao.updateProfession(profession);
			if(profession.getProIsVisible()==1){
				Map<String,Object> parameters = new HashMap<String,Object>();
				parameters.put("jobIsVisible", profession.getProIsVisible());
				parameters.put("proID", profession.getProID());
				iprofessionJobDao.updateJobVisibleByParameters(parameters);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public int getJobCourseNum(Map<String,Object> parameters) {
		return iprofessionJobDao.countJobCourseNum(parameters);
	}

	@Override
	public void updateJobVisibleWithParameters(Map<String, Object> parameters)
			throws ServiceException {
		try {
			iprofessionJobDao.updateJobVisibleByParameters(parameters);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

}
