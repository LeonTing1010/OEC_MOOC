/**
 * TeacherShineVoImpl.java	  V1.0   2014-2-10 下午3:47:53
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.job.JobDao;
import com.gta.oec.dao.profession.ProfessionDao;
import com.gta.oec.dao.teacher.TeacherDao;
import com.gta.oec.dao.teacher.TeacherShineDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.job.TeacherShineItemVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.teacher.TeacherShineVo;

@Service
public class TeacherShineServiceImpl implements TeacherShineService {
	private static final Log logger = LogFactory.getLog(TeacherShineServiceImpl.class);
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TeacherShineDao teacherShineDao;
	@Autowired
	private ProfessionDao professionDao;
	@Autowired
	private JobDao jobDao;

	/**
	 * 
	 * 功能描述：得到教师擅长的信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 下午4:04:23
	 *         </p>
	 * 
	 * @param stuId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<TeacherShineVo> getTeacherShineByUserId(int userId) {
		List<TeacherShineVo> list = null;
		try {
			list = teacherShineDao.getTeacherShineByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e); e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 功能描述：删除教师擅长的信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 下午4:04:23
	 *         </p>
	 * 
	 * @param stuId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int deleteTeacherShineInfor(int userId) {
		return teacherShineDao.deleteTeacherShineInfor(userId);
	}

	/**
	 * 
	 * 功能描述：批量插入擅长数据到数据库中
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-2-10 下午4:04:23
	 *         </p>
	 * 
	 * @param stuId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int addTeacherShine(List<TeacherShineVo> teacherShineVo) {
		return teacherShineDao.addTeacherShine(teacherShineVo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.teacher.TeacherShineService#initDataOfTeacherShine()
	 */
	@Override
	public Map<String, List<ProfessionVo>> initDataOfTeacherShine(List<TeacherShineVo> teacherShines ) throws BaseException {
		if (teacherShines==null) {
			throw new BaseException("参数错误.");
		}
		Map<String, List<ProfessionVo>> map = new HashMap<String, List<ProfessionVo>>();
		List<ProfessionVo> unselectedProfessionList = new ArrayList<ProfessionVo>();
		List<ProfessionVo> selectedProfessionList = new ArrayList<ProfessionVo>();
		List<JobVo> jobGroupList = new ArrayList<JobVo>();

		try {
			// get the listof allprofessions
			List<ProfessionVo> professionList = professionDao.getAllProfessions();
			if (professionList != null && professionList.size() > 0) {
			
				for (ProfessionVo professionVo : professionList) {
					jobGroupList = jobDao.getJobGroupByProId(professionVo.getProID());//得到行业下的岗位群
					List<JobVo> jobGroupList_tmp = new ArrayList<JobVo>();
					boolean flag=false; //行业是否被选中标识
					if (teacherShines != null && teacherShines.size() > 0) {
						for (TeacherShineVo teacherShineVo : teacherShines) {
							for (JobVo jobVos : jobGroupList) {
								if (teacherShineVo.getJobid() == jobVos.getJobID()) {
									jobGroupList_tmp.add(jobVos);
									flag=true;
									break;
								}
							}
						}
					}
					if(flag){
						ProfessionVo selected = new ProfessionVo();
						selected.setProName(professionVo.getProName());
						selected.setProID(professionVo.getProID());
						selected.setJobGroupList(jobGroupList_tmp);
						selectedProfessionList.add(selected);
					}
					ProfessionVo unselected = new ProfessionVo();
					unselected.setProName(professionVo.getProName());
					unselected.setProID(professionVo.getProID());
					jobGroupList.removeAll(jobGroupList_tmp);
					unselected.setJobGroupList(jobGroupList);
					unselectedProfessionList.add(unselected);
				}
			}

		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		map.put("selected", selectedProfessionList);
		map.put("unselected", unselectedProfessionList);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.teacher.TeacherShineService#initDataOfTeacherShineItem
	 * (int)
	 */
	@Override
	public List<ProfessionVo>[] initDataOfTeacherShineItem(int userId) throws BaseException {

		// 已选
		List<TeacherShineVo> teacherShineList = new ArrayList<TeacherShineVo>();
		teacherShineList = teacherShineDao.getTeacherShineByUserId(userId);
		List<ProfessionVo> professionList = null;
		List<TeacherShineItemVo> teacherShineItemList = new ArrayList<TeacherShineItemVo>();
		List<JobVo> jobGroupList = new ArrayList<JobVo>();
		try {
			// get the list of all professions
			professionList = professionDao.getAllProfessions();
			if (professionList != null && professionList.size() > 0) {
				for (ProfessionVo professionVo : professionList) {
					jobGroupList = jobDao.getJobGroupByProId(professionVo.getProID());
					for (JobVo jobVo : jobGroupList) {
						TeacherShineItemVo teaShineItemVo = new TeacherShineItemVo();
						teaShineItemVo.setPosition(TeacherShineItemVo.ON_LEFT);
						teaShineItemVo.setProfessionVo(professionVo);
						teaShineItemVo.setUserId(userId);
						teaShineItemVo.setJobVo(jobVo);
						for (TeacherShineVo teacherShineVo : teacherShineList) {
							if (teacherShineVo.getJobid() == jobVo.getJobID()) {
								teaShineItemVo.setPosition(TeacherShineItemVo.ON_RIGHT);
								break;
							}
						}
						teacherShineItemList.add(teaShineItemVo);
					}

					professionVo.setTeacherShineItemVos(teacherShineItemList);
				}
			}

		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}

		return null;
	}
}
