/**
 * CourseServiceImpl.java	  V1.0   2013-12-27 ����10:30:57
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.job.JobDao;
import com.gta.oec.dao.profession.ProfessionDao;
import com.gta.oec.dao.qacenter.QuestionAddDao;
import com.gta.oec.dao.qacenter.QuestionCenterDao;
import com.gta.oec.dao.section.SectionDao;
import com.gta.oec.dao.student.StudentDao;
import com.gta.oec.dao.teacher.TeacherDao;
import com.gta.oec.dao.user.UserDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.teacher.TeacherVo;
import com.gta.oec.vo.user.UserVo;

@Service
public class TeacherServiceImpl implements TeacherService {
	private static final Log logger = LogFactory 
			.getLog(TeacherServiceImpl.class);
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private ProfessionDao professionDao;
	@Autowired
	private QuestionCenterDao questionCenterDao;
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private QuestionAddDao questionAddDao;
	@Autowired
	private CourseDao courseDao;


	@Override
	public TeacherVo getTeacherByUserId(Integer userId) throws BaseException {

		try {
			return teacherDao.getTeacherByUserId(userId.intValue());
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	public StudentVO getStuByUserId(Integer stuId) throws BaseException {
		try {
			return studentDao.getStuByUserId(stuId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.teacher.TeacherService#getTeacherListByProfessionId
	 * (int, int)
	 */
	@Override
	public PageModel getTeacherListByProfessionId(int proId, int pageNo,
			int pageSize) throws BaseException {
		List<TeacherVo> list = null;
		UserVo userVo = null;
		PageModel pageModel = new PageModel();
		List<CourseVo> courseList=null;
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			list = teacherDao.getTeacherListByProfessionId(proId, pageNo,
					pageSize);
			if (list != null && list.size()>0) {
				for (TeacherVo teacherVo : list) {
					int userId = teacherVo.getUserId();
					userVo = userDao.getUserById(userId);
					courseList=new ArrayList<CourseVo>();
					courseList=courseDao.getCourseListByUserId(userId, 1, 3);
					if (userVo != null) {
						teacherVo.setTeacherName(userVo.getUserName());
						teacherVo.setTeacherPic(userVo.getUserHeadPic());
						teacherVo.setCourseList(courseList);
					}
				}
				int totalItem = teacherDao.countTeacherByProfessionId(proId);
				pageModel.setList(list);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				pageModel.setTotalItem(totalItem);

			}
		} catch (Exception e) {
			logger.error("select TeacherListByProfessionId Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return pageModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.service.teacher.TeacherService#getTeacherListByjobGroupId
	 * (int, int)
	 */
	@Override
	public PageModel getTeacherListByjobGroupId(int jobGroupId, int pageNo,
			int pageSize) throws BaseException {
		List<TeacherVo> list = null;
		List<CourseVo> courseList=null;
		UserVo userVo = null;
		PageModel pageModel = new PageModel();
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			list = teacherDao.getTeacherListByjobGroupId(jobGroupId, pageNo,
					pageSize);
			if (list != null && list.size()>0) {
				for (TeacherVo teacherVo : list) {
					int userId = teacherVo.getUserId();
					userVo = userDao.getUserById(userId);
					courseList=new ArrayList<CourseVo>();
					courseList=courseDao.getCourseListByUserId(userId, 1, 3);
					if (userVo != null) {
						teacherVo.setTeacherName(userVo.getUserName());
						teacherVo.setTeacherPic(userVo.getUserHeadPic());
						teacherVo.setCourseList(courseList);
					}
				}

				int totalItem = teacherDao
						.countTeacherListByjobGroupId(jobGroupId);
				pageModel.setList(list);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				pageModel.setTotalItem(totalItem);

			}
		} catch (Exception e) {
			logger.error("DB getTeacherListByjobGroupId Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return pageModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.service.teacher.TeacherService#getTeacherList(int)
	 */
	@Override
	public List<TeacherVo> getTeacherList(int num) throws BaseException {
		List<TeacherVo> list = null;
		UserVo userVo = null;
		JobVo jobGroupVo = null;
		ProfessionVo professionVo = null;
		try {
			list = teacherDao.getTeacherList(0, num);
			if (list != null && list.size()>0) {
				for (TeacherVo teacherVo : list) {
					int userId = teacherVo.getUserId();
					userVo = userDao.getUserById(userId);
					jobGroupVo = jobDao.getTeacherProfessionAddJob(userId);
					professionVo = professionDao
							.getProfessionByProId(jobGroupVo.getProID());
					if (userVo != null) {
						teacherVo.setTeacherName(userVo.getUserName());
						teacherVo.setTeacherPic(userVo.getUserHeadPic());
						teacherVo.setJobGroupName(jobGroupVo.getJobName());
						teacherVo.setProfessionVo(professionVo);
					}
				}
			}
		} catch (Exception e) {
			logger.error("DB getTeacherList Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.service.teacher.TeacherService#getTeacherById(int)
	 */
	@Override
	public TeacherVo getTeacherById(int teacherId) throws BaseException {
		TeacherVo teacher = null;
		UserVo user = null;
//		JobVo jobGroupVo = null;
//		ProfessionVo professionVo = null;
		try {

			teacher = teacherDao.getTeacherVoById(teacherId);
			if (teacher != null) {
				int userId = teacher.getUserId();
				user = userDao.getUserById(userId);
//				jobGroupVo = jobDao.getTeacherProfessionAddJob(userId);
//				if (null!=jobGroupVo) {
//					professionVo = professionDao.getProfessionByProId(jobGroupVo
//							.getProID());
//				}
			
//				if (user != null&&null!=jobGroupVo) {
				if (user != null) {	
					teacher.setTeacherName(user.getUserName());
					teacher.setTeacherPic(user.getUserHeadPic());
//					teacher.setJobGroupName(jobGroupVo.getJobName());
//					teacher.setProfessionVo(professionVo);
				}
			}

		} catch (Exception e) {
			logger.error("DB getTeacherById Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return teacher;
	}

	/**
	 * 
	 * 功能描述：更新教师信息
	 * 
	 * @author Miaoj
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateTeacher(TeacherVo teacher) throws BaseException {
		return teacherDao.updateTeacherMode(teacher);
	}

	@Override
	public PageModel getTeacherPage(int pageNo, int pageSize)
			throws BaseException {

		List<TeacherVo> list = null;
		UserVo userVo = null;
		List<CourseVo> courseList=null;
		PageModel pageModel = new PageModel();
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			list = teacherDao.getTeacherList(pageNo, pageSize);
			if (list != null && list.size()>0) {
				for (TeacherVo teacherVo : list) {
					int userId = teacherVo.getUserId();
					userVo = userDao.getUserById(userId);
					courseList=new ArrayList<CourseVo>();
					courseList=courseDao.getCourseListByUserId(userId, 1, 3);
					if (userVo != null) {
						teacherVo.setTeacherName(userVo.getUserName());
						teacherVo.setTeacherPic(userVo.getUserHeadPic());
						teacherVo.setCourseList(courseList);
					}
				}
				int totalItem = teacherDao.countTeacher();
				pageModel.setList(list);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				pageModel.setTotalItem(totalItem);

			}
		} catch (Exception e) {
			logger.error("DB getTeacherListByjobGroupId Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return pageModel;

	}

	@Override
	public PageModel getQuestionPageModel(QuestionUserVo questionUserVo,
			int quesType, int pageNo, int pageSize) throws BaseException {

		if (questionUserVo == null) {
			throw new InterfaceFieldExcepiton("questionUserVo参数不能为空");
		} else if (questionUserVo.getTeacherID() == 0) {
			throw new InterfaceFieldExcepiton("teacherId参数不能为空");
		}
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		UserVo userVo = null;
		SectionVO sectionVo = null;
		try {
			list = questionCenterDao.getQuestionPageListByQuestionUserByquesType(
							questionUserVo.getTeacherID(),
							questionUserVo.getStatus(), quesType, pageNo,
							pageSize);

			if (list.size()>0) {
				for (QuestionVo questionVo : list) {
					int userId = questionVo.getUserID();
					userVo = userDao.getUserById(userId);
					if (userVo != null) {
						questionVo.setUserName(userVo.getUserName());
					}
					int secId = questionVo.getSecID();
					sectionVo = sectionDao.getSectionById(secId);
					if (sectionVo != null) {
						questionVo.setSectionVO(sectionVo);
					}
				}
				int totalItem = questionCenterDao
						.countQuestionByQuestionUserByquesType(
								questionUserVo.getTeacherID(),
								questionUserVo.getStatus(), quesType);
				pageModel.setList(list);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				pageModel.setTotalItem(totalItem);

			}

		} catch (Exception e) {
			logger.error("DB getTeacherListByjobGroupId Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return pageModel;
	}
	
	/**
	 * 功能描述：获取某个老师被追问问题(分页)
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PageModel getQuestionAddPageModel(QuestionUserVo questionUserVo,
			int pageNo, int pageSize) throws BaseException{
		if (questionUserVo == null) {
			throw new InterfaceFieldExcepiton("questionUserVo参数不能为空");
		} else if (questionUserVo.getTeacherID() == 0) {
			throw new InterfaceFieldExcepiton("teacherId参数不能为空");
		}
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		List<QuestionAddVo> list = new ArrayList<QuestionAddVo>();
		QuestionVo questionVo = new QuestionVo();
		try {
			list = questionAddDao.getQuestionAddPageModel(
							questionUserVo.getTeacherID(),
							questionUserVo.getStatus(), 
							pageNo,
							pageSize);

			if (list != null && list.size()>0) {
				for (QuestionAddVo questionAddVo : list) {
					if(questionAddVo.getQuesID()!=0){
						questionVo=questionCenterDao.getQuestion(questionAddVo.getQuesID());
						questionAddVo.setQuestionVo(questionVo);
					}
				}
				int totalItem = questionAddDao.getQuestionAddPageModelCount(
						questionUserVo.getTeacherID(),
						questionUserVo.getStatus(), 
						pageNo,
						pageSize);

				pageModel.setList(list);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				pageModel.setTotalItem(totalItem);

			}

		} catch (Exception e) {
			logger.error("DB getTeacherListByjobGroupId Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return pageModel;
	}
	

	@Override
	public int countQuestionPageModel(QuestionUserVo questionUserVo,
			int quesType) throws BaseException {
		if (questionUserVo == null) {
			throw new InterfaceFieldExcepiton("questionUserVo参数不能为空");
		} else if (questionUserVo.getTeacherID() == 0) {
			throw new InterfaceFieldExcepiton("teacherId参数不能为空");
		}
		int totalItem;
		try {
		 totalItem = questionCenterDao
					.countQuestionByQuestionUserByquesType(
							questionUserVo.getTeacherID(),
							questionUserVo.getStatus(), quesType);
			


		} catch (Exception e) {
			logger.error("DB countQuestionPageModel Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return totalItem;
	}
	
	/**
	 * 功能描述：统计某个老师被追问问题总条数
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int quesAddCout(QuestionUserVo questionUserVo) throws BaseException{
		if (questionUserVo == null) {
			throw new InterfaceFieldExcepiton("questionUserVo参数不能为空");
		} else if (questionUserVo.getTeacherID() == 0) {
			throw new InterfaceFieldExcepiton("teacherId参数不能为空");
		}
		int totalItem;
		try {
		 totalItem = questionAddDao.quesAddCout(questionUserVo.getTeacherID());
			


		} catch (Exception e) {
			logger.error("DB countQuestionPageModel Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return totalItem;
	}

	@Override
	public List<QuestionVo> getTeaQuestionList(QuestionUserVo questionUserVo,
			int quesType, int pageNo, int pageSize) throws BaseException {

		if (questionUserVo == null) {
			throw new InterfaceFieldExcepiton("questionUserVo参数不能为空");
		} else if (questionUserVo.getTeacherID() == 0) {
			throw new InterfaceFieldExcepiton("teacherId参数不能为空");
		}
		if (pageNo <= 0 || pageSize <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		List<QuestionVo> list = null;
		try{
			list=questionCenterDao.getQuestionPageListByQuestionUserByquesType
					(questionUserVo.getTeacherID(), questionUserVo.getStatus(), quesType, pageNo, pageSize);
		}catch(Exception e){
			logger.error("DB countQuestionPageModel Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
		return list;
	}

	/**
	 * 功能描述：根据追问问题ID取得问题信息
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param quesAddId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionAddVo getQuestionAddVo(int quesAddId) throws BaseException{
		
		
		QuestionAddVo questionAdd = new QuestionAddVo();
		try {
			questionAdd=questionAddDao.getQuestionAddVo(quesAddId);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return questionAdd;
		
	}
}
