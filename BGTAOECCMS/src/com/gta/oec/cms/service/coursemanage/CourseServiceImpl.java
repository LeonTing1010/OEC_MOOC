/**
 * CourseServiceImpl.java	  V1.0   2014-3-18 下午1:17:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.coursemanage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.coursedetail.CourseDetailDao;
import com.gta.oec.cms.dao.coursejob.CourseJobDao;
import com.gta.oec.cms.dao.coursemanage.CourseDao;
import com.gta.oec.cms.dao.resource.ResourceDao;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.exception.ServiceException;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.vo.course.Course;
import com.gta.oec.cms.vo.course.CourseDetail;
import com.gta.oec.cms.vo.course.CourseJob;
import com.gta.oec.cms.vo.resource.ResourceVo;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseDetailDao courDetaDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private CourseJobDao courseJobDao;

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public Course getParagraphicInfo(int courseId) {
		// 根据课程id查询对应的章、节信息
		try {
			Course courseVo = courseDao.getCourseById(courseId);
			if (null == courseVo) {
				return null;
			}
			CourseDetail section = new CourseDetail();
			section.setCourseId(courseId);
			section.setPid(courseId);
			List<CourseDetail> list = courDetaDao.getSectionByCid(section);
			for (CourseDetail sect : list) {
				CourseDetail querVo = new CourseDetail();
				querVo.setCourseId(courseId);
				querVo.setPid(sect.getId());
				sect.setList(courDetaDao.getSectionByCid(querVo));
				List<CourseDetail> listSum = sect.getList();
				for (int i = 0; i < listSum.size(); i++) {
					ResourceVo resource = new ResourceVo();
					int sectId = listSum.get(i).getId();
					resource.setSectionId(sectId);
					resource.setResourceType("2");
					// 参数中2表示课程辅助资源，此处功能为打开查看视频选项卡时，如果辅助资源url为空，查看辅助资源链接不显示
					int count = resourceDao.countCourseResourceListByResourceVo(resource);
					if (count > 0) {
						sect.getList().get(i).setResourceSum(count);
					}
				}
			}
			courseVo.setSectionList(list);
			return courseVo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<Course> getAllTrade() {
		return courseDao.getAllTrade();
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<Course> getAllPjob(int proId) {
		return courseDao.getAllPjob(proId);
	}

	@Override
	public List<Course> getAllJob(int pjobId) {
		return courseDao.getAllJob(pjobId);
	}

	@Override
	public List<Course> getJobByPid(Course course) {
		return courseDao.getJobByPid(course);
	}

	@Override
	public List<Course> allCoursePageQuery(PageModel<Course> pm) {
		return courseDao.allCoursePageQuery(pm);
	}

	@Override
	public List<Course> allCourseCtxPageQuery(PaginationContext<Course> pc) {
		return courseDao.allCourseCtxPageQuery(pc);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void checkCourse(Course course) {
		courseDao.checkCourse(course);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void courseRecommend(CourseJob course) {
		courseJobDao.courseRecommend(course);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void passAudit(List courseIdint, int status) {
		courseDao.passAudit(courseIdint, status);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<Course> getCourseIntroduction(int cid, int jobId) {
		return courseDao.getCourseIntroduction(cid, jobId);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void setChannelURL(CourseDetail courseDetail) {
		courDetaDao.setChannelURL(courseDetail);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public CourseDetail getCourseInfoByCid(int secId) {
		return courDetaDao.getSectionInfoById(secId);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<ResourceVo> getSectionCourseResourceList(int sectionId, int resourceType,
			int courseId) throws ServiceException {
		if (sectionId == 0) {
			throw new ServiceException("sectionId should  gt 0");
		}
		if (resourceType == 0) {
			throw new ServiceException("resourceType should  gt 0");
		}

		ResourceVo resource = new ResourceVo();
		resource.setSectionId(sectionId);
		resource.setResourceType(Integer.toString(resourceType));
		resource.setCourseId(courseId);
		List<ResourceVo> resourceList = null;
		try {
			resourceList = resourceDao.getCourseResourceListByResourceVo(resource);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return resourceList;
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public int countCourseResourceListByResourceVo(int sectionId, int resourceType)
			throws ServiceException {
		int count = 0;
		if (sectionId == 0) {
			throw new ServiceException("sectionId should  gt 0");
		}
		if (resourceType == 0) {
			throw new ServiceException("resourceType should  gt 0");
		}
		ResourceVo resource = new ResourceVo();
		resource.setSectionId(sectionId);
		resource.setResourceType(Integer.toString(resourceType));
		try {
			count = resourceDao.countCourseResourceListByResourceVo(resource);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public Course courseAmend(int courseId) {
		return courseDao.courseAmend(courseId);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<CourseJob> jobInfoByCid(int courseId) {
		CourseJob courseJob = new CourseJob();
		courseJob.setCourseID(courseId);
		return courseJobDao.jobInfoByCid(courseJob);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<Course> courseInfo(Course course) {
		return courseDao.courseInfo(course);
	}

	@Override
	public Course saveOrUpadteCourse(Course course, List<ResourceVo> resList,
			List<CourseJob> jobList, boolean flag) {
		try {
			course.setuTime(VeDate.getNowDate());
			if (flag) {
				Course queryVo = new Course();
				queryVo.setCourseName(course.getCourseName());
				List<Course> list = getCourseList(queryVo);
				if (null != list && list.size() > 0) {
					throw new Exception("课程名重复");
				}
				course.setCourseName(course.getCourseName().trim());
				course.setcTime(VeDate.getNowDate());
				course.setCourStartTime(VeDate.getNowDate());
				course = courseDao.insertCourse(course);
			} else if (course.getCourseId() > 0) {
				// 查询课程名是否重复
				if (!StringUtils.isBlank(course.getCourseName())) {

					Course queryVo = new Course();
					queryVo.setCourseName(course.getCourseName().trim());
					List<Course> list = getCourseList(queryVo);

					Course srcVo = getCourseInfoById(course.getCourseId());
					if (null != list && list.size() > 0) {
						if (!srcVo.getCourseName().equals(course.getCourseName())
								&& course.getCourseName().equals(list.get(0).getCourseName())) {
							throw new Exception("课程名重复");
						}
					}
					course.setCourseName(course.getCourseName().trim());
				}
				courseDao.updateCourse(course);
			} else {
				throw new Exception("数据格式错误");
			}

			// 绑定资源入库
			if (resList != null && resList.size() > 0) {
				List<ResourceVo> resRtList = addBatchResource(resList);
				for (ResourceVo resource : resRtList) {
					resource.setCourseId(course.getCourseId());
				}
				// 批量绑定资源与课程
				addBatchReCourRescource(resRtList);
			}
			// 绑定课程与岗位
			if (null != jobList && jobList.size() > 0) {
				for (CourseJob jobVo : jobList) {
					jobVo.setCourseID(course.getCourseId());
				}
				beatchBindCourseJob(jobList, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	// 根据课程对象获取课程信息
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<Course> getCourseList(Course coursevo) {
		List<Course> courseList = null;
		try {
			courseList = courseDao.courseInfo(coursevo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	// 功能描述：根据课程id获取课程信息(关联学校)
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public Course getCourseInfoById(Integer courseId) {
		Course courseVo = new Course();
		try {
			courseVo = courseDao.courseAmend(courseId);
			return courseVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseVo;
	}

	// 批量上传资源
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public List<ResourceVo> addBatchResource(List<ResourceVo> list) {
		List<ResourceVo> rtList = new ArrayList<ResourceVo>();
		if (null != list || list.size() > 0) {
			for (ResourceVo resourceVo : list) {
				try {
					resourceVo = addResource(resourceVo);
					rtList.add(resourceVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return rtList;
	}

	// 增加课程辅助资源
	@Override
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public ResourceVo addResource(ResourceVo resourceVo) {

		if (null != resourceVo || null != resourceVo.getIsDownload()
				|| null != resourceVo.getResourceType() || null == resourceVo.getResourceURL()) {
			try {
				resourceDao.getCourseResourceListByResourceVo(resourceVo);
				resourceDao.addResource(resourceVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resourceVo;
	}
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void addBatchReCourRescource(List<ResourceVo> list) {
		if (null != list || list.size() > 0) {
			for (ResourceVo resourceVo : list) {
				try {
					resourceVo = addBindCourRescource(resourceVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public ResourceVo addBindCourRescource(ResourceVo resourceVo) {
		// 判断课程是否存在
		if (resourceVo.getCourseId() > 0) {
			Course courseVo = courseDao.getCourseById(resourceVo.getCourseId());
		}
		// 判断章节是否存在
		if (resourceVo.getSectionId() > 0) {
			CourseDetail sectionVo = new CourseDetail();
			sectionVo.setCourseId(resourceVo.getCourseId());
			sectionVo.setId(resourceVo.getSectionId());
			List<CourseDetail> secList = courDetaDao.getSectionByCid(sectionVo);
			resourceVo.setCourseId(secList.get(0).getCourseId());
		}
		// 判断资源是否存在
		try {
			ResourceVo resourceVo2 = resourceDao
					.getResourceByResourceId(resourceVo.getResourceId());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		// 判断关联是否重复
		List<ResourceVo> list = resourceDao.getReCourResourceList(resourceVo, 0);
		try {
			resourceDao.addResource(resourceVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceVo;
	}

	// 批量绑定课程与岗位
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void beatchBindCourseJob(List<CourseJob> list, boolean delete) {
		if (null != list || list.size() > 0) {
			for (CourseJob vo : list) {
				try {
					if (delete) {
						CourseJob courseJobVo = new CourseJob();
						courseJobVo.setCourseID(vo.getCourseID());
						courseJobDao.deleteCourseJob(courseJobVo);
						delete = false;
					}
					addBindCourseJob(vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * @Override public ResourceVo addBindCourRescource(ResourceVo
		 * resourceVo){ // 判断课程是否存在 if (resourceVo.getCourseId() > 0) { Course
		 * courseVo =courseDao.getCourseById(resourceVo.getCourseId()); }
		 * //判断章节是否存在 if (resourceVo.getSectionId() > 0) { CourseDetail
		 * sectionVo = new CourseDetail();
		 * sectionVo.setCourseId(resourceVo.getCourseId());
		 * sectionVo.setId(resourceVo.getSectionId()); List<CourseDetail>
		 * secList = courDetaDao.getSectionByCid(sectionVo);
		 * resourceVo.setCourseId(secList.get(0).getCourseId()); } //判断资源是否存在
		 */
	}

	// 增加课程和岗位的关联
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public CourseJob addBindCourseJob(CourseJob courseJobVo) {
		if (null != courseJobVo || (courseJobVo.getJobID() > 0 && courseJobVo.getProId() > 0)) {

			try {
				// 校验数据的重复性
				List<CourseJob> list = courseJobDao.jobInfoByCid(courseJobVo);
				if (null == list || list.size() == 0) {
					courseJobDao.insertCourseJobRe(courseJobVo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return courseJobVo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.coursemanage.CourseService#getCourseUnauditedNum
	 * (int)
	 */
	@Override
	public int getCourseUnauditedNum(int checkStatus) throws ServiceException {
		int number = 0;
		if (checkStatus <= 0) {
			throw new ServiceException("参数错误.");
		}
		try {
			number = courseDao.countCourseByCheckStatus(checkStatus);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("countCourseByCheckStatus方法调用数据库出错.");
		}

		return number;
	}
	/* (non-Javadoc)
	 * @see com.gta.oec.cms.service.coursemanage.CourseService#updateSection(com.gta.oec.cms.vo.course.CourseDetail)
	 */
	@Transactional(rollbackFor=Throwable.class, propagation=Propagation.REQUIRED)
	public void updateSection(CourseDetail courseDetail) {
		courDetaDao.updateSection(courseDetail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.coursemanage.CourseService#batchDeleteSectionById
	 * (java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public void batchDeleteSectionById(List<Integer> sectionIds) throws ServiceException {
		if (sectionIds == null) {
			throw new ServiceException("参数错误.");
		}
		try {
			for (Integer secId : sectionIds) {
				courDetaDao.deleteSectionInfoById(secId);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("deleteSectionInfoById错误.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.coursemanage.CourseService#batchUpdateSection
	 * (java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public void batchUpdateSection(List<CourseDetail> sectionList) throws ServiceException {
		if (sectionList == null) {
			throw new ServiceException("参数错误.");
		}
		for (CourseDetail courseDetail : sectionList) {
			CourseDetail section = courDetaDao.getSectionInfoById(courseDetail.getId());
			if (section == null) {
				throw new ServiceException("参数错误.");
			}
		}
		for (CourseDetail courseDetail : sectionList) {
			try {
				courDetaDao.updateSection(courseDetail);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ServiceException("updateSection错误.");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.cms.service.coursemanage.CourseService#batchSaveSection(java
	 * .util.List)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public void batchSaveSection(List<CourseDetail> sectionList) throws ServiceException {
		if (sectionList == null) {
			throw new ServiceException("参数错误.");
		}

		for (CourseDetail courseDetail : sectionList) {
			try {
				courDetaDao.saveSectionInfo(courseDetail);
				// 添加节
				if (null != courseDetail.getList() && courseDetail.getList().size() > 0) {
					for (CourseDetail courd : courseDetail.getList()) {
						courd.setPid(courseDetail.getId());
						courDetaDao.saveSectionInfo(courd);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("updateSection错误.");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.cms.service.coursemanage.CourseService#
	 * updatePublishedCourseStep2_outlineService(int, java.util.List,
	 * java.util.List, java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public void updatePublishedCourseStep2_outlineService(int courseId,
			List<Integer> deleteCourseDetailIds, List<CourseDetail> updateCourseDetails,
			List<CourseDetail> saveCourseDetails) throws ServiceException {
		if (courseId <= 0 || deleteCourseDetailIds == null || updateCourseDetails == null
				|| saveCourseDetails == null) {
			throw new ServiceException("参数错误.");
		}
		// 要删除的章节
		if (deleteCourseDetailIds.size() > 0) {
			this.batchDeleteSectionById(deleteCourseDetailIds);
		}
		// 要修改的章节
		if (updateCourseDetails.size() > 0) {
			this.batchUpdateSection(updateCourseDetails);
		}
		// 要新增的章节
		if (saveCourseDetails.size() > 0) {
			this.batchSaveSection(saveCourseDetails);
		}

	}

	/* (non-Javadoc)
	 * @see com.gta.oec.cms.service.coursemanage.CourseService#hasResourceInPartOfSection(int, int)
	 */
	@Override
	public boolean hasResourceInPartOfSection(int courseId, int sectionId) throws ServiceException {
		if (courseId <=0 || sectionId <=0) {
			throw new ServiceException("参数错误");
		}
		CourseDetail courseDetail=courDetaDao.getSectionInfoById(sectionId);
		if (courseDetail==null) {
			return false;
		}
		//查看是否存在核心知识点.
		if (!StringUtils.isBlank(courseDetail.getCoreKnowledge())) {
			return true;
		}
		//查看是否存在主视频和辅助资源.
		ResourceVo resource=new ResourceVo();
		resource.setCourseId(courseId);
		resource.setSectionId(sectionId);
		int num=0;
		try {
			num=resourceDao.countCourseResourceListByResourceVo(resource);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("查看是否存在主视频和辅助资源错误");
		}
		if (num >0) {
			return true;
		}
		return false;
	}

}
