/**
 * ProfessionServiceImpl.java	  V1.0   2014-3-28 上午11:27:11
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.profession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.profession.IProfessionDao;
import com.gta.oec.cms.vo.job.Job;
import com.gta.oec.cms.vo.profession.Profession;
import com.gta.oec.cms.vo.teacher.TeacherShineVo;

@Service
public class ProfessionServiceImpl implements IProfessionService {
	@Autowired
	private IProfessionDao proDao;

	@Override
	public void getProfessionAndJobs(Map<String, Object> params) throws Exception {
		 
		String jobGroupIdsInput=(String)params.get("jobGroupIdsInput");
		List<Profession> unselectedList = new ArrayList<Profession>();
		List<Profession> selectedList = new ArrayList<Profession>();
		//用户已选择的行业岗位信息
		List<TeacherShineVo> TeacherShineList=new ArrayList<TeacherShineVo>();
		try {
				//修改逻辑
				if(null !=jobGroupIdsInput && !"".equals(jobGroupIdsInput) &&  !"undefined".equals(jobGroupIdsInput) ){
					String []proJobsStr=jobGroupIdsInput.split(",");
					for(String ids:proJobsStr){
						String[] proJobId=ids.split("\\|");
						TeacherShineVo tsVo=new TeacherShineVo();
						tsVo.setProid(Integer.parseInt(proJobId[0]));
						tsVo.setJobid(Integer.parseInt(proJobId[1]));
						TeacherShineList.add(tsVo);
					}
					//得到所有的行业信息
					 unselectedList=matfchJobByPro(new HashMap<String, Object>());
					//已选中的个数
					 int count=0;
						for(Profession allPro:unselectedList)
						{		
							 		List<Job>selectJobs=new ArrayList<Job>();
							 		 Profession profession=new Profession() ;
									 profession.setProID(allPro.getProID());
									 profession.setProName(allPro.getProName());
									boolean flag=false;
									 for(TeacherShineVo teacherShineVo:TeacherShineList){
										 //如果已选择岗位 左边为空只显示行业
										 if(allPro.getProID()==teacherShineVo.getProid()){
											 flag=true;
											 List<Job> unJobs=allPro.getJobs();
											 for(Job selJob:unJobs){
													 //已选中 左边放空
													 if(selJob.getJobID()==teacherShineVo.getJobid()){
														 count++;
														 //添加到已选择的岗位中
														 selectJobs.add(selJob);
														 //去掉未选中 中的岗位
														 allPro.getJobs().remove(selJob);
														 flag=true;
														  break;
													 }
											 }
										 }
									 }
							//已选中 把岗位信息添加到行业中
							if(flag){
								profession.setJobs(selectJobs);
							} 
							selectedList.add(profession);
						}
						params.put("selectedNum",count);
				}
			//没有选中 初始化
				else{
					unselectedList=findSelectPro(unselectedList, selectedList, params);
					}
			
			} catch (Exception e) {
				e.printStackTrace();
		}
		params.put("unselected", unselectedList);
		params.put("selected", selectedList);
	}
	
	//封装新增逻辑的行业岗位
	public List<Profession> findSelectPro(List<Profession> unselectedList, 	List<Profession> selectedList,Map<String, Object > params)throws Exception{
		try {
			// 得到所有行业岗位
			 unselectedList=matfchJobByPro(new HashMap<String, Object>());
			for(Profession pro:unselectedList){
				// 只加载行业 显示，不加载岗位  
				Profession profession=new Profession() ;
				profession.setProID(pro.getProID());
				profession.setProName(pro.getProName());
				selectedList.add(profession);
			}
			params.put("selectedNum", 0);
			return unselectedList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//匹配行业岗位
	public List<Profession>  matfchJobByPro(Map<String, Object> params)throws Exception{
		List<Profession> proList =null;
		try {
			proList = proDao.getProfessionByParams(params);
			if (null != proList && proList.size() > 0) {
				// 得到所有岗位
				List<Job> jobList = proDao.getJobByParams(params);
				// 匹配行业下面的岗位群
				for (Profession pro : proList) {
					List<Job> proJobList = new ArrayList<Job>();
					for (Job job : jobList) {
						if (job.getProID() == pro.getProID()) {
							proJobList.add(job);
						}
					}
					pro.setJobs(proJobList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return proList;
	}

 

	@Override
	public List<Profession> getAllProfessions() {
		return proDao.getAllProfession();
	}

	@Override
	public List<Profession> findProfessionsPageQuery(
			PaginationContext<Profession> pc) {
		return proDao.findProfessionsPageQuery(pc);
	}

	@Override
	public void updateProfessions(Profession profession) {
		proDao.updateProfessions(profession);
	}



	@Override
	public List<Profession> getProfessionByParams(Map<String, Object> params)throws Exception {
			try {
				return proDao.getProfessionByParams(params);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
	
	}
	@Override
	public List<Job> getJobByParams(Map<String, Object> params)
			throws Exception {
		return proDao.getJobByParams(params);
	}
}
