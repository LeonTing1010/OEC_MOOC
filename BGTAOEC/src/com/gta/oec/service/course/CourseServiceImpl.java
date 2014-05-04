/**
 * CourseServiceImpl.java	  V1.0   2014-1-9 上午9:10:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.exam.ExamDao;
import com.gta.oec.dao.exam.ExamStudentDao;
import com.gta.oec.dao.job.CourseJobDao;
import com.gta.oec.dao.learn.LearnDao;
import com.gta.oec.dao.note.NoteDao;
import com.gta.oec.dao.qacenter.AnswerDao;
import com.gta.oec.dao.qacenter.AttentionDao;
import com.gta.oec.dao.qacenter.PraiseDetailDao;
import com.gta.oec.dao.qacenter.QuestionCenterDao;
import com.gta.oec.dao.resource.ResourceDao;
import com.gta.oec.dao.section.SectionDao;
import com.gta.oec.dao.user.UserDao;
import com.gta.oec.dao.usercourse.UserCourseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.util.DateUtils;
import com.gta.oec.vo.common.ExamStuInfoVo;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.job.CourseJobVo;
import com.gta.oec.vo.learn.LearnVo;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.AttentionVo;
import com.gta.oec.vo.qacenter.PraiseDetailVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.resource.ResourceVo;
import com.gta.oec.vo.user.UserVo;
import com.gta.oec.vo.usercourse.UserCourseVo;

@Service
public class CourseServiceImpl implements CourseService { 
	private static final Log logger = LogFactory.getLog(CourseServiceImpl.class);
    @Autowired
	private CourseDao courseDao;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
   	private UserCourseDao userCourseDao;
    @Autowired
   	private UserDao userDao;
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ExamStudentDao examStudentDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private QuestionCenterDao questionCenterDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private LearnDao learnDao;
    @Autowired
    private CourseJobDao courseJobDao;
    @Autowired
	private PraiseDetailDao praiseDetailDao;
    @Autowired
	private AttentionDao attentionDao;
    @Autowired
    private ExamService examService;
	@Override
	public PageModel getCourseList(CourseVo coursevo, int page,
			int pageSize) throws BaseException {
		if (null==coursevo || page<=0 || pageSize<=0) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		PageModel pageModel = new PageModel();
		try {
			pageModel.setTotalItem(courseDao.getCourseListCount(coursevo));			
			pageModel.setList(courseDao.getCourseList(coursevo, page, pageSize));
			pageModel.setToPage(page);
			pageModel.setPageSize(pageSize);
			return pageModel;
		} catch (Exception e) {
		   logger.error("System DB operate error!",e);
		   throw new SystemDBException("System DB operate error!");
		}
	}
	
	/**
	 * 
	 * 功能描述：搜索课程
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @param coursevo
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getSearchCourseList(SearchVo searchVo,int page,int pageSize) throws BaseException{
		
		if (page<=0 || pageSize<=0) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		PageModel pageModel = new PageModel();
		
		try {
			pageModel.setTotalItem(courseDao.getSearchCourseListCount(searchVo));			
			pageModel.setList(courseDao.getSearchCourseList(searchVo,page, pageSize));
			pageModel.setToPage(page);
			pageModel.setPageSize(pageSize);
			return pageModel;
			
		} catch (Exception e) {
		   logger.error("System DB operate error!",e);
		   throw new SystemDBException("SystemDBException:System DB operate error!");
		}
	}
	

	@Override
	public PageModel getCourseListByJob(CourseVo coursevo,int jobID,int page,int pageSize) throws BaseException {
		try {
			PageModel pageModel = new PageModel();			
			pageModel.setTotalItem(courseDao.getCourseListCountByJob(coursevo, jobID));
			pageModel.setPageSize(pageSize);
			pageModel.setToPage(page);
			List<CourseVo> list = courseDao.getCourseListByJob(coursevo, jobID, page, pageSize);
			pageModel.setList(list);
			return pageModel;
		} catch (Exception e) {
			logger.error("System DB operate error!",e);
			throw new SystemDBException("System DB operate error!");
		}
	}
	
	/**
	 * 
	 * 功能描述：根据行业搜索课程列表
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14
	 *
	 * @param coursevo
	 * @param jobID
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getCourseListByProId(SearchVo searchVo,int proId,int page,int pageSize) throws BaseException{
		if (page<=0 || pageSize<=0) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		PageModel pageModel = new PageModel();
		
		try {
			pageModel.setTotalItem(courseDao.getCourseListByProIdCount(searchVo,proId));			
			pageModel.setList(courseDao.getCourseListByProId(searchVo, proId, page, pageSize));
			pageModel.setToPage(page);
			pageModel.setPageSize(pageSize);
			return pageModel;
			
		} catch (Exception e) {
			logger.error("System DB operate error!",e);
			throw new SystemDBException("System DB operate error!");
		}
	}
	

	@Override
	public CourseVo getCourseInfoById(Integer courseId) throws BaseException {
		if (courseId <= 0 ) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		try {
            CourseVo courseVo =  courseDao.getCourseById(courseId);		  
            return courseVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	
	}


	@Override
	public CourseVo getCourseById(int courseId) throws BaseException {
		if (courseId <= 0 ) {
			throw new InterfaceFieldExcepiton(
					"courseId or userId is not match.");
		}
		try {

              CourseVo courseVo =  courseDao.getCourseById(courseId);
  		      if (null==courseVo) {
				return null;
			  }
  			  SectionVO sectionVO = new SectionVO();
  			  sectionVO.setCourseId(courseId);
  			  sectionVO.setPid(courseId);
              List<SectionVO> list = sectionDao.getSectionList(sectionVO);
              for (SectionVO sect:list) {
            	  SectionVO querVo = new SectionVO();
            	  querVo.setCourseId(courseId);
            	  querVo.setPid(sect.getId());
				  sect.setList(sectionDao.getSectionList(querVo));
			   }
              courseVo.setList(list);
              return courseVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}
	
	@Override
	public CourseVo getUserCourseLearnDetail(int courseId, int userId)
			throws BaseException {
		if (courseId <= 0 || userId <= 0) {
			throw new InterfaceFieldExcepiton(
					"courseId or userId is not match.");
		}
		try {
			CourseVo courseVo = courseDao.getCourseById(courseId);
			if (null==courseVo) {
				return null;
			}
			SectionVO sectionVO = new SectionVO();
			sectionVO.setCourseId(courseId);
			sectionVO.setPid(courseId);
			//获取章信息
			List<SectionVO> list = sectionDao.getSectionList(sectionVO);
			//获取节信息
			for (SectionVO section : list) {
				List<SectionVO> sectionLearnList = sectionDao.getUserSectionList(section, userId);			
				
				for (SectionVO sVo:sectionLearnList) {
					//获取该节的学习状态
					sVo.setLearn(getSectionLearnDeatail(sVo,userId));
					//获取该节的辅助资源数
					sVo.setResourceCount(resourceDao.getSectionResourceCount(sVo.getId(),"2"));
					
					NoteVo noteVo = new NoteVo();
					noteVo.setCourseId(courseId);
					noteVo.setUserId(userId);
					noteVo.setSecId(sVo.getId());
					sVo.setNoteCount(noteDao.getUserNoteCount(noteVo));
				  }
				
				section.setList(sectionLearnList);
			}
			courseVo.setList(list);
			return courseVo;
		} catch (Exception e) {
			  logger.error("System DB operate error!",e);
			 throw new SystemDBException("System DB operate error!");
		}

	}
	public LearnVo getSectionLearnDeatail(SectionVO sectionVO,int userId){
		LearnVo queryVo = new LearnVo();
		queryVo.setCourseId(sectionVO.getCourseId());
		queryVo.setUserId(userId);
		queryVo.setSectionId(sectionVO.getId());
		return learnDao.getLearn(queryVo);
	}
	
	// 根据课程id查询用户id列表   zhangjin
	public List<UserVo> getCourseUser(Integer courseId,int pageNo,int pageSize)  throws BaseException {
		 
		 try {
			   return userDao.getCourseUser(courseId,pageNo,pageSize);
			} catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
		}

   
	//根据多个用户id查询用户  zhangjin
	@Override
	public List<UserVo> getUserListByIds(List idsList) throws BaseException{
		
		 try {
			 List<UserVo> list = null; 
			 if(idsList.size()>0){
				 list = userDao.getUserListByIds(idsList);
			 }
			   return list;
			} catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
		}


	//获取单个课程章节信息带笔记
	@Override
	public CourseVo getCourseInfo(int courseId) throws BaseException {
		if (courseId <= 0) {
			throw new InterfaceFieldExcepiton("courseId or userId is not match.");
		}
		try {
		 
			SectionVO sectionVO = new SectionVO();
			sectionVO.setCourseId(courseId);
			sectionVO.setPid(courseId);
			//获取章信息
			List<SectionVO> list = sectionDao.getSectionList(sectionVO);
			if (null==list || list.size()<=0) {
				return null;
			}
			//获取节信息
	        for (SectionVO sect:list) {
          	  SectionVO querVo = new SectionVO();
          	  querVo.setCourseId(courseId);
          	  querVo.setPid(sect.getId());
	        	List<SectionVO> childList = sectionDao.getSectionList(querVo);
	        	
	        	//获取节下面的笔记信息
	        	if (null!=childList&&childList.size()>0) {
					for (SectionVO secChlid: childList) {
						NoteVo noteVo = new NoteVo();
						noteVo.setSecId(secChlid.getId());
						noteVo.setCourseId(secChlid.getCourseId());
						List<NoteVo> noteList = noteDao.getNoteList(noteVo);
						secChlid.setNoteList(noteList);
					}
				}  
	        	
			   sect.setList(childList);
		   }
		  CourseVo courseVo = getCourseInfoById(courseId);
		  if(courseVo==null){
			  courseVo = new CourseVo();
		  }
		  courseVo.setList(list);
	     return courseVo;
		} catch (Exception e) {
			  logger.error("System DB operate error!",e);
			 throw new SystemDBException("System DB operate error!");
		}
	}
	
	//获取获取课程信息(带 练习/考试/作业 信息)
	@Override
	public CourseVo getCourseVoInfo(int courseId, ExamVo examVo)
			throws BaseException {
		if (courseId <= 0 || null == examVo || examVo.getExamType() <= 0) {
			throw new InterfaceFieldExcepiton(
					"courseId or examType is not match.");
		}
		CourseVo courseVo = null;
		CourseVo courseVo2=new CourseVo();
		ExamStudentVo examStudentVo=new ExamStudentVo();
		int count=0; //记录数
		// 判断考试类型  examType==2,练习，则需要带章节信息
		if (examVo.getExamType() == 2) {
			try {
				SectionVO sectionVO = new SectionVO();
				sectionVO.setCourseId(courseId);
				sectionVO.setPid(courseId);
				// 获取章信息
				List<SectionVO> list = sectionDao.getSectionList(sectionVO);
				//若章节不存在，直接返回课程信息
				if (null == list || list.size() <= 0) {    
					courseVo = getCourseInfoById(courseId);
					if (courseVo == null) {
						courseVo = new CourseVo();
					}
					return courseVo;
				}
				// 获取节信息
				for (SectionVO sect : list) {
					SectionVO querVo = new SectionVO();
					querVo.setCourseId(courseId);
					querVo.setPid(sect.getId());
					List<SectionVO> childList = sectionDao.getSectionList(querVo);

					// 获取节下面的练习信息
					if (null != childList && childList.size() > 0) {
						for (SectionVO secChlid : childList) {
							examVo.setCourId(courseId); // 设置考试的课程ID
							examVo.setSecId(secChlid.getId()); // 设置考试的章节ID
							List<ExamVo> examList = examDao.getExamList(examVo);
							if(null!=examList && examList.size()>0){
								for(ExamVo exam:examList){
									courseVo2.setCourseId(exam.getCourId());
									count=getCourStudyCount(courseVo2);  //得到学习该练习课程的人数
									exam.setLearnCount(count);
									
									examStudentVo.setExamId(exam.getExamId());
									count=examService.getTakeExamCount(examStudentVo);  //得到提交练习的人数
									exam.setExamCount(count);
								}
								secChlid.setExamList(examList);
							}
						}
					}
					sect.setList(childList);
				}
				courseVo = getCourseInfoById(courseId);
				if (courseVo == null) {
					courseVo = new CourseVo();
				}
				courseVo.setList(list);
			} catch (Exception e) {
				logger.error("System DB operate error!",e);
				throw new SystemDBException("System DB operate error!");
			}
		}
		//其他类型 （考试/作业）不需要带章节信息
		else {
			try {
				List<ExamVo> examList = examDao.getExamList(examVo);
				courseVo = getCourseInfoById(courseId);
				if(null!=examList && examList.size()>0) 
				{
					for(ExamVo exam:examList){
						courseVo2.setCourseId(exam.getCourId());
						count=getCourStudyCount(courseVo2);  //得到学习该考试课程的人数
						exam.setLearnCount(count);
						
						examStudentVo.setExamId(exam.getExamId());
						examStudentVo.setExamState(2);
						count=examService.getTakeExamCount(examStudentVo); //未批阅数
						exam.setNoCorrect(count);
						
						examStudentVo.setExamState(1);
						count=examService.getTakeExamCount(examStudentVo); //已批阅数
						exam.setHasCorrect(count);
						
						count=exam.getHasCorrect()+exam.getNoCorrect();  //得到总参加考试的人数
						exam.setExamCount(count);				
					}
					courseVo.setExamList(examList);
				}				
			} catch (Exception e) {
				logger.error("System DB operate error!",e);
				throw new SystemDBException("System DB operate error!");
			}
		}
		return courseVo;
	}
	
	//获取多个课程章节信息下的笔记  by zhangjin
	public PageModel getCourseInfoList(int userId,int pageNo,int pageSize) throws BaseException{
		
		PageModel pageModel = new PageModel();
		List<CourseVo> listInfo= new ArrayList<CourseVo>();
		//根据用户id查询带有笔记的课程id集合
		List<?> list =userCourseDao.getHasNoteCourseIdsListByUserId(userId, pageNo, pageSize);
		int totalCount=userCourseDao.getHasNoteCourseIdsListByUserId(userId, 0, 0).size();
		//该用户下课程的数量
		if (list!=null && list.size()>0) {
			
			for(int i=0;i<list.size();i++){
				int courseId = (Integer) list.get(i);
				CourseVo course = getUserCourseNotes(courseId,userId);
				if(course!=null){
					listInfo.add(course);
				}
			}
		}
		pageModel.setTotalItem(totalCount); //总记录数
		pageModel.setList(listInfo);
		pageModel.setToPage(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}
	
	
	
	

	//获取单个课程下问题和答案
	@Override
	public CourseVo getCourseQuesInfo(int userId,int courseId,int quesType) throws BaseException {
			if (courseId <= 0) {
				throw new InterfaceFieldExcepiton("courseId or userId is not match.");
			}
			try {
			 
				//获取问题信息quesType 0.答疑提问 1.课程提问
				List<QuestionVo> queslist =  questionCenterDao.getQuestionListByCourId(courseId,quesType);
				
				if (queslist.size() > 0) {
					for (QuestionVo questionVo : queslist) {
						AnswerVo answerVo = answerDao.getHotQuestionAnswer(questionVo.getQuesID());
						if (answerVo != null) {
							List<PraiseDetailVo> praiseDetailVos = null;
							try {

								praiseDetailVos = praiseDetailDao.getPraiseDetailByUserIdByAnsId(userId,answerVo.getAnswID());
							} catch (Exception e) {
								// TODO: handle exception
								logger.error(e); e.printStackTrace();
							}

							if (praiseDetailVos.size() == 0) {
								answerVo.setPraiseSign("赞");
							} else {
								answerVo.setPraiseSign("已赞");
							}
							questionVo.setAnswerVo(answerVo);
						} else {
							AnswerVo answer = new AnswerVo();
							answer.setPraiseSign("赞");
							questionVo.setAnswerVo(answer);
						}

						// 登录用户的问题是否已关注
						List<AttentionVo> attentionList = attentionDao
								.getAttentionQuestionByUserId(userId,questionVo.getQuesID());
						if (attentionList.size() == 0) {
							questionVo.setAttentionSign("+ 关注问题");
						} else {
							questionVo.setAttentionSign("已关注");
						}
					}
				}
			   
			  CourseVo courseVo = getCourseInfoById(courseId);
			  if(courseVo==null){
				  courseVo = new CourseVo();
			  }
			  courseVo.setQueslist(queslist);
		     return courseVo;
			
			} catch (Exception e) {
				  logger.error("System DB operate error!",e);
				 throw new SystemDBException("System DB operate error!");
			}

		}
	
	


	//获取多个课程下的问题和答案   by zhangjin
	public List<CourseVo> getCourseQuesInfoList(int userId,int quesType,int page,int pageSize) throws BaseException{
		
		List<CourseVo> listInfo= new ArrayList<CourseVo>();
		//该用户下课程id的集合
		List<?> list =userCourseDao.getCourseIdsListByUserIdAndExamType(userId,0,page,pageSize);
		if (list!=null && list.size()>0) {
			
			for(int i=0;i<list.size();i++){
				int courseId = (Integer) list.get(i);
				CourseVo course = getCourseQuesInfo(userId,courseId,quesType);
				if(course!=null){
					listInfo.add(course);
				}
			}
		}
		return listInfo;
	}
		
	

	
	/**
	 * 获取单个课程下和对应的考试类型（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
	 */
	@Override
	public CourseVo getCourseExamInfo(int userId,int courId,int examType) throws BaseException {
		if (courId <= 0) {
			throw new InterfaceFieldExcepiton("courseId or userId is not match.");
		}
		try {
		 
		  CourseVo courseVo = getCourseInfoById(courId);
		  //根据课程id获取课程下的试题（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
		  List<ExamVo> examlist = examDao.getExamListByCourId(courId,0,examType);
		  List<ExamStuInfoVo> examStuInfoList = new ArrayList<ExamStuInfoVo>();
		  
		  if(courseVo==null){
			  courseVo = new CourseVo();
		  }
          if(examlist!=null&&examlist.size()>0){
        	 
        	 for(ExamVo examVo:examlist){
        		 List list = new ArrayList();
        		 list.add(examVo.getExamId());
        		 List<ExamStudentVo> examstulist = examStudentDao.getExamStudentListByExamIds(userId,list);
        		
        		 if(examstulist!=null&&examstulist.size()>0){
        			 ExamStudentVo examStudentVo = (ExamStudentVo)examstulist.get(0);
        			 int status = examStudentVo.getExamState();
        			 
        			 ExamStuInfoVo examStuInfoVo = new ExamStuInfoVo();
        			 examStuInfoVo.setExamId(examVo.getExamId());
    				 examStuInfoVo.setExamStuId(examStudentVo.getId());
    				 examStuInfoVo.setExamName(examVo.getExamName());
    				 examStuInfoVo.setExamCTime(examVo.getExamCtime());
        			 if(status==2){    //未批阅
        				 examStuInfoVo.setExamStatus(2);
        			 }else if(status==1){   //已批阅
        				 examStuInfoVo.setExamScore(examStudentVo.getExamScore());
        				 examStuInfoVo.setExamStatus(1);
        			 }else{   //没有批阅状态，即考试类型为练习
        				 examStuInfoVo.setExamScore(examStudentVo.getExamScore());
        			 }
            		 examStuInfoList.add(examStuInfoVo);
            		 courseVo.setExamStuInfoList(examStuInfoList);
        		 }else{
        			 ExamStuInfoVo examStuInfoVo = new ExamStuInfoVo();
        			 examStuInfoVo.setExamId(examVo.getExamId());
        			 examStuInfoVo.setExamName(examVo.getExamName());
        			 examStuInfoVo.setExamCTime(examVo.getExamCtime());
            		 examStuInfoVo.setExamStatus(3);  //未提交
            		 examStuInfoList.add(examStuInfoVo);
            		 courseVo.setExamStuInfoList(examStuInfoList);
        		 }
        		 
             }
         }
	     return courseVo;
		} catch (Exception e) {
			  logger.error("System DB operate error!",e);
			 throw new SystemDBException("System DB operate error!");
		}

	}
	
	
	
	/**
	 *  【获取多个课程下和对应的课程作业（ 1：课程考试；2：课程练习；3：课程作业；4：认证考试）】  by zhangjin
	 */
	public PageModel getCourseExamInfoList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException{

		  if (null==pageNo || pageNo.intValue()<=0||pageSize==null||pageSize.intValue()<=0) {
				throw new InterfaceFieldExcepiton("Interface parameter error!");
			}
			PageModel pageModel = new PageModel();
			List<CourseVo> listInfo= new ArrayList<CourseVo>();
		
			//该用户下课程id的集合
			List<?> list =userCourseDao.getCourseIdsListByUserIdAndExamType(userId,examType,pageNo, pageSize);
				if (list!=null && list.size()>0) {
					
					for(int i=0;i<list.size();i++){
						int courseId = (Integer) list.get(i);
						CourseVo course = getCourseExamInfo(userId,courseId,examType);
						if(course!=null){
							listInfo.add(course);
						}
					}
				}
				pageModel.setTotalItem(list.size()); //总记录数
				pageModel.setList(listInfo);
				pageModel.setToPage(pageNo);
				pageModel.setPageSize(pageSize);
				
				return pageModel;
		}
	

	//增加课程笔记
	@Override
	public NoteVo insertNote(NoteVo noteVo) throws BaseException {
		
		if (null == noteVo || noteVo.getCourseId() <= 0 || noteVo.getSecId() <=0 || noteVo.getUserId() <=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error！");
		}
		try{ 
			return noteDao.addNotes(noteVo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}
	
	//课程收藏
	@Override
	public PageModel courseCollectList(int userId,int courseType,int pageNo,int pageSize)throws BaseException {
		try{
			List<CourseVo> courlist = courseDao.courseCollectList(userId,courseType,pageNo,pageSize);
			int totalItem = getCourseListCount(userId,courseType);
			PageModel pageModel = new PageModel();
			pageModel.setTotalItem(totalItem);
			pageModel.setList(courlist);
			pageModel.setPageSize(pageSize);
			pageModel.setToPage(pageNo);
			return pageModel;
		}catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public int getCourseListCount(int userId,int collType) throws BaseException{
		try{
			return courseDao.getCourseInfoCount(userId,collType);
		}catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	//获得资源列表
	@Override
	public List<ResourceVo> getResourceList(ResourceVo resourceVo) throws BaseException{
		
		if(null==resourceVo)
		{
			throw new InterfaceFieldExcepiton("Interface parameter error！");
		}
		try {
			return resourceDao.getResourceList(resourceVo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}

	@Override
	public List<CourseVo> getCourseListByName(CourseVo courseVo)throws BaseException {
		
		if(null== courseVo){
			return null;
		}
		try {
			List<CourseVo> coursList = courseDao.getCourseList(courseVo);
			return coursList;
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}

	//增加课程辅助资源
	@Override
	public ResourceVo addResource(ResourceVo resourceVo) throws BaseException {
		
		if(null==resourceVo || null==resourceVo.getDataIsdownload()
				|| null==resourceVo.getSourceType()|| null==resourceVo.getDataUrl())
		{
			throw new InterfaceFieldExcepiton("Interface parameter error！");
		}
		try {
			 resourceDao.getResourceList(resourceVo);
			 resourceDao.addResource(resourceVo);
			return resourceVo;
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}


	//增加辅助资源与的课程关联
	@Override
	public ResourceVo addBindCourRescource(ResourceVo resourceVo)throws BaseException {
		// 校验参数
		if (null == resourceVo || !(resourceVo.getCourseID() >0 || resourceVo.getSecID() >0)) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		// 判断课程是否存在
		if (resourceVo.getCourseID() > 0) {
			CourseVo courseVo =courseDao.getCourseById(resourceVo.getCourseID());	
			if (null == courseVo)
				throw new BaseException("Interface parameter error: Course not exists!");
		}
		//判断章节是否存在
		if (resourceVo.getSecID() > 0)
		{
			if(resourceVo.getCourseID()<0){
				throw new BaseException("Interface parameter error!");
			}
			SectionVO sectionVo = new SectionVO();
			sectionVo.setCourseId(resourceVo.getCourseID());
			sectionVo.setId(resourceVo.getSecID());
			List<SectionVO> secList = sectionDao.getSectionList(sectionVo);
			if (null == secList || secList.size() <= 0) {
				throw new BaseException("Interface parameter error: Course Section not exists!");
			}
			resourceVo.setCourseID(secList.get(0).getCourseId());
		}
		//判断资源是否存在
		ResourceVo resourceVo2 = resourceDao.getResourceById(resourceVo.getReSourceID());
		if (null == resourceVo2) {
			throw new BaseException("Interface parameter error: ReSource not exists！");
		}
		//判断关联是否重复
		List<ResourceVo> list = resourceDao.getReCourResourceList(resourceVo,0);
		if (null != list && list.size() >0) {
			throw new BaseException("Interface parameter error: Data already exists!");
		}
		
		try{
			resourceDao.addBindCourRescource(resourceVo);
			return resourceVo;
		}catch(Exception e){
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}



	//增加课程和岗位的关联
	@Override
	public CourseJobVo addBindCourseJob(CourseJobVo courseJobVo) throws BaseException {
		
		if(null==courseJobVo || (courseJobVo.getJobID()<=0 && courseJobVo.getProId()<=0))
		{
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			//校验数据的重复性
			List<CourseJobVo> list = courseJobDao.getCourseJobList(courseJobVo);
			if (null==list||list.size()==0) {
				courseJobDao.addCourseJobRe(courseJobVo);
			}
			return courseJobVo;
		}catch(Exception e){
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}
	public void betachUpdateSection(List<SectionVO> list) throws BaseException{
		for (int i = 0; i < list.size(); i++) {
			updateSection(list.get(i));
		}
	}
	
	//更新课程章节
	@Override
	public SectionVO updateSection(SectionVO sectionVO) throws BaseException {
		if(null==sectionVO || sectionVO.getId()<=0 )
		{
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		List<SectionVO> secList = sectionDao.getSectionList(sectionVO);
		if (null == secList || secList.size() <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error:Course Section not exists！");
		}
		try{
			sectionDao.update(sectionVO);
			sectionVO.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			return sectionVO;
		}catch(Exception e){
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}


	//删除课程和资源的关联(是否同时删除资源)
	@Override
	public ResourceVo deleteBindCourRescource(ResourceVo resourceVo,boolean flag) throws BaseException {
		
		if(null==resourceVo || resourceVo.getReSourceID()<0 || 
				!(resourceVo.getCourseID() >0 || resourceVo.getSecID() >0)){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			
			ResourceVo resourceVo2 = resourceDao.getResourceById(resourceVo.getReSourceID());
			
			//是否同时删除资源
			if(flag){
				List<ResourceVo> list=resourceDao.getReCourResourceList(null,resourceVo.getReSourceID());
				//如果关联大于一条，则只执行删除关联操作
				if(list.size()>1)
				{
					resourceDao.deleteBindCourRescource(resourceVo);
				}else{
					resourceDao.deleteBindCourRescource(resourceVo);
					resourceDao.deleteResource(resourceVo);
				}
			}else{
				resourceDao.deleteBindCourRescource(resourceVo);
			}
			if ("1".equals(resourceVo2.getSourceType())) {
				sectionDao.updateSecUrlNull(resourceVo2.getDataUrl());
			}
			return resourceVo;
		}catch(Exception e){
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
		
	}



	@Override
	public CourseVo createOrUpadteCourse(CourseVo courseVo,List<ResourceVo> resList,List<CourseJobVo> jobList,boolean flag) throws BaseException{
		if (null==courseVo || StringUtils.isBlank(courseVo.getCourseName())) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			
			courseVo.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));	
            
			if (flag) {
				CourseVo queryVo  = new CourseVo();
				queryVo.setCourseName(courseVo.getCourseName());
				List<CourseVo> list = getCourseList(queryVo);
				if (null!=list&&list.size()>0) {
				throw new BaseException("课程名重复");
				}
				courseVo.setCourseName(courseVo.getCourseName().trim());
				courseVo.setcTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				courseVo.setCourStartTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				courseVo = courseDao.insert(courseVo);
			}else if (courseVo.getCourseId()>0) {
				//查询课程名是否重复
				if (!StringUtils.isBlank(courseVo.getCourseName())) {
					
					CourseVo queryVo  = new CourseVo();
					queryVo.setCourseName(courseVo.getCourseName().trim());
					List<CourseVo> list = getCourseList(queryVo);
					
					CourseVo srcVo = getCourseInfoById(courseVo.getCourseId());
					if (null!=list&&list.size()>0) {
						if (!srcVo.getCourseName().equals(courseVo.getCourseName())&&courseVo.getCourseName().equals(list.get(0).getCourseName())) {
							throw new BaseException("课程名重复");
						}
					}
					courseVo.setCourseName(courseVo.getCourseName().trim());
				}
					
				courseDao.update(courseVo);
			}else {
				throw new BaseException("数据格式错误");
			}
			
				//绑定资源入库
				if (resList!=null&&resList.size()>0) {
					List<ResourceVo> resRtList = addBatchResource(resList);
					for (ResourceVo resource:resRtList) {
						resource.setCourseID(courseVo.getCourseId());
					}
					//批量绑定资源与课程
					addBatchReCourRescource(resRtList);
				 }
				//绑定课程与岗位
				if (null != jobList && jobList.size() > 0) {
					for (CourseJobVo jobVo:jobList) {
						jobVo.setCourseID(courseVo.getCourseId());
					}
					beatchBindCourseJob(jobList,true);
				}
			return courseVo;
		} catch (Exception e) {
			if (e instanceof BaseException) {
				throw new BaseException(e.getMessage());
			}
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
		
	}



	@Override
	public List<ResourceVo> addBatchResource(List<ResourceVo> list) throws BaseException {
		if (null==list || list.size()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}	
		List<ResourceVo> rtList = new ArrayList<ResourceVo>();
		for (ResourceVo resourceVo:list) {
			try {
				resourceVo = addResource(resourceVo);
				rtList.add(resourceVo);
			} catch (Exception e) {
				logger.error("SystemDBException:System DB operate error!", e);
				throw new SystemDBException("System DB operate error!!");
			}
		}
		return rtList;
	}



	@Override
	public void addBatchReCourRescource(List<ResourceVo> list)
			throws BaseException {
		if (null==list || list.size()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}	
		for (ResourceVo resourceVo:list) {
			try {
				resourceVo = addBindCourRescource(resourceVo);			
			} catch (Exception e) {
				logger.error("System DB operate error!", e);
				throw new SystemDBException("System DB operate error!!");
			}
		}
	
	}

	@Override
	public void beatchBindCourseJob(List<CourseJobVo> list,boolean delete)
			throws BaseException {
		if (null==list || list.size()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}	
		for (CourseJobVo vo:list) {
			try {
				if (delete) {
					CourseJobVo courseJobVo = new CourseJobVo();
					courseJobVo.setCourseID(vo.getCourseID());
					courseJobDao.deleCourseJobRe(courseJobVo);
					delete = false;
				}
				addBindCourseJob(vo);
			} catch (Exception e) {
				logger.error("System DB operate error!", e);
				throw new SystemDBException("System DB operate error!!");
			}
		}
	
	}



	@Override
	public void saveBatchSection(List<SectionVO> list) throws BaseException {
		if (null==list || list.size()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}	
		int courseId=0;
		for (SectionVO vo:list) {
			courseId = vo.getCourseId();
			try {
			  SectionVO rSectionVO = sectionDao.insert(vo);
			  //添加节
			  if (null!=vo.getList()&&vo.getList().size()>0) {
				  for (SectionVO sectionVO:vo.getList()) {
					  sectionVO.setPid(rSectionVO.getId());
					  sectionDao.insert(sectionVO);
			    	}
			   }
			} catch (Exception e) {
				logger.error("System DB operate error!", e);
				throw new SystemDBException("System DB operate error!!");
			}
		}
	   courseDao.updateCourseWeek(courseId);
	}



	@Override
	public List<CourseVo> getCourseList(CourseVo coursevo) throws BaseException {
		
		try {
			return courseDao.getCourseList(coursevo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}



	@Override
	public CourseVo updateCourse(CourseVo courseVo) throws BaseException {
		if (null==courseVo || courseVo.getCourseId()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
	    try {
	    	courseVo.setuTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
	    	if (courseVo.getLiveType()>0) {
				SectionVO sectionVO = new SectionVO();
				sectionVO.setCourseId(courseVo.getCourseId());
				sectionVO.setType(String.valueOf(courseVo.getLiveType()));
				sectionDao.updateSectionTypeByCourseId(sectionVO);
			}
			return courseDao.update(courseVo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}


	//获取课程信息(分页-不带章节信息)
	@Override
	public PageModel getCourseTab(CourseVo coursevo, Integer pageNo,
			Integer pageSize) throws BaseException {
		if (null==pageNo || pageNo.intValue()<=0||pageSize==null||pageSize.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		
		try {
			List<CourseVo> list = courseDao.getCourseList(coursevo, pageNo, pageSize);
			int totalItem = courseDao.getCourseListCount(coursevo);
			//查询课程的考试信息
			for(CourseVo cour:list){
				if(cour.getStatus()==2 || cour.getStatus()==5){
					//设置考试类型为1
					List<ExamVo> examList=examDao.getExamListByCourId(cour.getCourseId(),0, 1);
					if(null!=examList && examList.size()>0)
					{
						ExamStudentVo examStudentVo=new ExamStudentVo();
						int count=0;
						for(ExamVo exam:examList){
							examStudentVo.setExamId(exam.getExamId());
							examStudentVo.setExamState(1);   //设置为已批阅状态
							count=examService.getTakeExamCount(examStudentVo);
							exam.setHasCorrect(count);  //得到该课程考试的已批阅数
							
							examStudentVo.setExamState(2);   //设置为未批阅状态
							count=examService.getTakeExamCount(examStudentVo);
							exam.setNoCorrect(count);  //得到该课程考试的未批阅数
							
							count=exam.getHasCorrect()+exam.getNoCorrect();
							exam.setExamCount(count);  //得到总的参加考试人数
						}
						cour.setExamList(examList);
					}
					//设置考试类型为3  即作业
					List<ExamVo> opList=examDao.getExamListByCourId(cour.getCourseId(),0, 3);
					if(null!=opList && opList.size()>0)
					{
						ExamStudentVo examStudentVo=new ExamStudentVo();
						int count=0;
						for(ExamVo exam:opList){
							examStudentVo.setExamId(exam.getExamId());
							examStudentVo.setExamState(1);   //设置为已批阅状态
							count=examService.getTakeExamCount(examStudentVo);
							exam.setHasCorrect(count);  //得到该课程作业的已批阅数
							
							examStudentVo.setExamState(2);   //设置为未批阅状态
							count=examService.getTakeExamCount(examStudentVo);
							exam.setNoCorrect(count);  //得到该课程作业的未批阅数
							
							count=exam.getHasCorrect()+exam.getNoCorrect();
							exam.setExamCount(count);  //得到总的参加考试人数
						}
						cour.setOpList(opList);
					}
				}
			}		
			pageModel.setTotalItem(totalItem);
			pageModel.setList(list);
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);		
			return pageModel;
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}

	}

	//获取课程信息(分页-带章节  考试/作业/练习 信息)
	@Override
	public PageModel getCourseTab(CourseVo courseVo, Integer pageNo, Integer pageSize,Integer examType,Integer paperState) throws BaseException {
		if (null==pageNo || pageNo.intValue()<=0||pageSize==null||pageSize.intValue()<=0
				||examType==null||examType.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		List<CourseVo> courList=new ArrayList<CourseVo>();	
		try {
			//得到当前用户的课程
			List<CourseVo> list= getExamCourList(courseVo,examType,paperState, pageNo, pageSize);
			//总课程条数
			int totalItem = getExamCourListCount(courseVo, examType,paperState);
			//遍历课程，得到带章节和 考试/作业/练习 的课程对象
	    	for(CourseVo course:list){
	    		ExamVo examVo=new ExamVo();
	    		examVo.setCourId(course.getCourseId());
	    		examVo.setExamType(examType);  //设置考试类型
	    		examVo.setPaperState(paperState);  //设置批阅状态
	    		
	    		courseVo=getCourseVoInfo(course.getCourseId(),examVo);
	    		courList.add(courseVo);
	    	}
			pageModel.setTotalItem(totalItem);
			pageModel.setList(courList);
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);
			
			return pageModel;
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}
	
	@Override
	public List<CourseVo> getUserCourList(CourseVo courseVo)
			throws BaseException {
		if(null==courseVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			List<CourseVo> list = courseDao.getUserCourList(courseVo);
			return list;		
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}
	 public boolean checkUserCourse(Integer courseId,Integer userId) throws BaseException{
		if (null==courseId||userId==null||userId.intValue()<=0||courseId.intValue()<=0) {
				throw new InterfaceFieldExcepiton("Interface parameter error!");
			}
		try {
			LearnVo learnVo = new LearnVo();
			learnVo.setUserId(userId);
			learnVo.setCourseId(courseId);
			List<LearnVo> list = learnDao.getUserCourse(learnVo);
			if (null == list || list.size() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	 }

	@Override
	public void addUserCourse(Integer courseId, Integer userId)
			throws BaseException {
		if (null==courseId||userId==null||userId.intValue()<=0||courseId.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
		 
		   LearnVo learnVo = new LearnVo();
		   learnVo.setUserId(userId);
		   learnVo.setCourseId(courseId);
		   learnVo.setCreateTimeDate(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		   learnVo.setUpdateTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		   List<LearnVo> list = learnDao.getUserCourse(learnVo);
		   if (null==list||list.size()==0) {
			   learnDao.insertUserCourse(learnVo);
	     	}
		  
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}

	public void betachDeleteSectionById(List<Integer> list,Integer courseId) throws BaseException{
		if (list.size()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		for (int i = 0; i < list.size(); i++) {
			deleteSectionById(list.get(i),courseId);
		}
		
	}
	@Override
	public void deleteSectionById(Integer sectionId,Integer courseId) throws BaseException {
		if (sectionId<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			//删除关联的资源
			ResourceVo resourceVo = new ResourceVo();
			resourceVo.setSecID(sectionId);
			resourceVo.setCourseID(courseId);
			resourceDao.deleteBindCourRescource(resourceVo);			
			sectionDao.deleteSection(sectionId);
			courseDao.updateCourseWeek(courseId);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
		
	}

	@Override
	public SectionVO getSectionById(Integer sectionId) throws BaseException {
		if (sectionId <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			return sectionDao.getSectionById(sectionId);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}

	@Override
	public int getSectionNotCompleteCount(Integer courseId)
			throws BaseException {
		if (null==courseId||courseId <= 0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			return sectionDao.getSectionNotCompleteCount(courseId);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}


	@Override
	public int getCourStudyCount(CourseVo courseVo) throws BaseException {
		if(null==courseVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			return courseDao.selectCourStudyCount(courseVo);
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
	}

	
	@Override
	public void delCourInfo(Integer courseId)throws BaseException{
		if(null==courseId || courseId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		courseDao.delCourInfo(courseId);
	}
	
	@Override
	public void delUserCour(Integer courseId)throws BaseException{
		if(null==courseId || courseId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		courseDao.delUserCour(courseId);
	}
	
	@Override
	public void collectCourse(Integer courseId,Integer collType,Integer userId)throws BaseException{
		if(null==courseId || courseId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		courseDao.collectCour(courseId,collType,userId);
	}
	
	@Override
	public int checkCollCourse(Integer courseId,Integer collType,Integer userId)throws BaseException{
		if(null==courseId || courseId.intValue()<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			return courseDao.checkCollCourse(courseId,collType,userId);
		}catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 根据课程id查询章节列表 by zhangjin
	 * @param courseId
	 * @return
	 */
	@Override
	public List<SectionVO> getSectionList(int courseId,int secPid,int secId)throws BaseException{
		
		   try{
			   SectionVO sectionVO = new SectionVO();
			   sectionVO.setCourseId(courseId);
			   sectionVO.setPid(secPid);
			   sectionVO.setId(secId);
			   return sectionDao.getSectionList(sectionVO);
			}catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
		
	}

	@Override
	public CourseVo getUserCourseNotes(int courseId, int userId)
			throws BaseException {
		if (courseId <= 0||userId<=0) {
			throw new InterfaceFieldExcepiton("courseId or userId is not match.");
		}
		try {
			SectionVO sectionVO = new SectionVO();
			sectionVO.setCourseId(courseId);
			sectionVO.setPid(courseId);
			//获取章信息
			List<SectionVO> list = sectionDao.getSectionList(sectionVO);
			if (null!=list && list.size()>0) {
				//获取节信息
				Iterator<SectionVO> section=list.iterator();
				while (section.hasNext()) {
					boolean  flag=false;   //记录该章下面是否有笔记
					SectionVO sect=section.next();
					SectionVO querVo = new SectionVO();
					querVo.setCourseId(courseId);
					querVo.setPid(sect.getId());
					List<SectionVO> childList = sectionDao.getSectionList(querVo);   
	          	  
		        	//获取节下面的笔记信息
		        	if (null!=childList&&childList.size()>0) {
		        		Iterator<SectionVO> subSection=childList.iterator();
		        		while(subSection.hasNext()){
		        			SectionVO secChild=subSection.next();
		        			NoteVo noteVo = new NoteVo();
							noteVo.setSecId(secChild.getId());
							noteVo.setCourseId(secChild.getCourseId());
							noteVo.setUserId(userId);
							List<NoteVo> noteList = noteDao.getNoteList(noteVo);
							if(null!=noteList && noteList.size()>0){
								secChild.setNoteList(noteList);
								flag=true;
							}else{
								subSection.remove();
							}
		        		}
		        	}
		        	if(flag){
		        		sect.setList(childList);
		        	}else{
		        		section.remove();
		        	}
			   }
			}
		  CourseVo courseVo = getCourseInfoById(courseId);
		  if(courseVo==null){
			  courseVo = new CourseVo();
		  }
		  courseVo.setList(list);
	     return courseVo;
		} catch (Exception e) {
			  logger.error("System DB operate error!",e);
			 throw new SystemDBException("System DB operate error!");
		}
	}
	
	/**
	 * 根据用户id查询课程  by zhangjin
	 */
	public PageModel getUserCourseList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException{
		if (null==pageNo || pageNo.intValue()<=0||pageSize==null||pageSize.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		List<UserCourseVo> userCourselist = new ArrayList<UserCourseVo>();
		PageModel pageModel = new PageModel();
	
		List<?> list =userCourseDao.getCourseIdsListByUserIdAndExamType(userId,examType,pageNo,pageSize);
    
		if (list!=null && list.size()>0) {
			for(int i=0;i<list.size();i++){
				int courseId = (Integer) list.get(i);
				UserCourseVo userCourse = new UserCourseVo();
				CourseVo course = courseDao.getCourseById(courseId);
				List<ExamVo> examlist = examDao.getExamListByCourId(courseId, 0, examType);
			
				if(examlist!=null&&examlist.size()>0){
					ExamVo exam = examlist.get(0);
					ExamStudentVo examStudent = examStudentDao.getExamStudentByExamIdAndStuId(userId, exam.getExamId());
					
					userCourse.setExamId(exam.getExamId());
					if(examStudent!=null){
						userCourse.setExamStuId(examStudent.getId());
						if(examStudent.getExamState()==1){ //1.已批阅
							userCourse.setStatus(1);
							userCourse.setExamScore(examStudent.getExamScore());
						}else if(examStudent.getExamState()==2){ //2.未批阅
							userCourse.setStatus(2);
						}
						
					 }else{
						  userCourse.setStatus(3); //3.未提交考试
					  }
					userCourse.setExamCtime(exam.getExamCtime());
				}else{
					userCourse.setStatus(4); //4表示该课程暂无考试
				}
				
				userCourse.setCourseVo(course);
				userCourselist.add(userCourse);
			}
		}
		pageModel.setTotalItem(list.size()); //总记录数
		pageModel.setList(userCourselist);
		pageModel.setToPage(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}

	@Override
	public List<CourseVo> getCourseListByKeyword(String keyString, int num) throws BaseException {
		List<CourseVo> list=new ArrayList<CourseVo>();
		if (num<=0) {
			num=1;//最少查询一条.
		}
		if(!StringUtils.isBlank(keyString)){
			try {
				list=courseDao.getCourseListByKeyword(keyString, num);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
				throw new BaseException("system db exception.");
				
			}
		
		}
		return list;
	}

	@Override
	public List<CourseVo> getTradeCourse(int tradeId,int courseState,int isIndexPush,int pageNo,int pageSize) throws BaseException {
		if (tradeId<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			return courseDao.getTradeCourse(tradeId, courseState, isIndexPush,pageNo,pageSize);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new BaseException("system db exception.");
			
		}
		
	}

	@Override
	public PageModel getCoursePageModelByUserId(int userId, int pageSize, int pageNo) throws BaseException {
		
		if (pageNo<=0||pageSize<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		PageModel pageModel = new PageModel();
		
		try {
			List<CourseVo> list = courseDao.getCourseListByUserId(userId, pageNo, pageSize);
			int totalItem = courseDao.countCourseListByUserId(userId);
			pageModel.setTotalItem(totalItem);
			pageModel.setList(list);
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);		
		} catch (Exception e) {
			logger.error("System DB operate error!", e);
			throw new SystemDBException("System DB operate error!!");
		}
		return pageModel;
	}
	
	
	public SectionVO getSectionById(int sectionId) throws BaseException {
		
		  try{
			  return sectionDao.getSectionById(sectionId);
		  }catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
	}
	
	@Override
	public List<CourseVo> getCourseRecommendList()throws BaseException {
		try{
			  return courseDao.getCourseRecommendList();
		  }catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
		 }
	}
	
	@Override
	public List<CourseVo> getCourseListByPro(int proId, int page, int pageSize)throws BaseException{
		try{
			return courseDao.getCourseListByPro(proId, page, pageSize);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	public int getCourseExamCount(int userId,int examType)throws BaseException {
		 try{
			  return userCourseDao.getCourseExamCount(userId,examType);
		  }catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
		 }
	 }
	
	@Override
	public PageModel getCourListByJobGroupId(CourseVo courseVo,int jobPid,int pageNo,int pageSize) throws BaseException{
		try{
			PageModel pageModel = new PageModel();
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalItem(courseDao.getCourseListCountByJobGroupId(courseVo,jobPid));
			pageModel.setList(courseDao.getCourseListByJobGroupId(courseVo,jobPid,pageNo,pageSize));
			return pageModel;
		}catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	@Override
	public PageModel queryCourseListByProId(CourseVo courseVo,int proId,int pageNo,int pageSize)throws BaseException{
		try{
			
			PageModel pageModel = new PageModel();
			pageModel.setList(courseDao.queryCourseListByProId(courseVo,proId,pageNo,pageSize));
			pageModel.setTotalItem(courseDao.queryCourseListCountByProId(courseVo, proId));
			pageModel.setToPage(pageNo);
			pageModel.setPageSize(pageSize);
			return pageModel;
		}catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	/**
	 * 
	 * 功能描述：新收到的考试
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-3-12 上午10:38:26</p>
	 *
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List getExamNoticeList(int userId,int examType) throws BaseException{
		
		List<UserCourseVo> userCourselist = new ArrayList<UserCourseVo>();
		List<UserCourseVo> list = userCourseDao.getUserCourseList(userId, 0, 0);
		if(list!=null&&list.size()>0){
			for(UserCourseVo userCourse:list){
				CourseVo course = courseDao.getCourseById(userCourse.getCourID());
				if (null!=course) {
					List<ExamVo> examlist = examDao.getExamListByCourId(course.getCourseId(), 0, examType);
					if(examlist!=null&&examlist.size()>0){
						ExamVo exam = examlist.get(0);
						List examIds = new ArrayList();
						examIds.add(exam.getExamId());
					
						List<ExamStudentVo> examStudentlist = examStudentDao.getExamStudentListByExamIds(userId, examIds);
						userCourse.setExamId(exam.getExamId());
						userCourse.setTaskName(exam.getExamName());
						if(examStudentlist==null||examStudentlist.size()==0){
							 userCourse.setCourseVo(course);
							 userCourselist.add(userCourse);
						 }
						userCourse.setExamCtime(exam.getExamCtime());
					}
				}

				
			}
		}
		return userCourselist;
	}

	@Override
	public List<CourseVo> getExamCourList(CourseVo coursevo, int examType,int examState,
			int pageNo, int pageSize) throws BaseException {
		if (null==coursevo || pageNo<=0 || pageSize<=0) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		try {
			return courseDao.getExamCourseList(coursevo, examType,examState, pageNo, pageSize);
		} catch (Exception e) {
		   logger.error("System DB operate error!",e);
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public int getExamCourListCount(CourseVo coursevo, int examType,int examState) throws BaseException {
		if (null==coursevo) {
			throw new InterfaceFieldExcepiton("courseId is not match.");
		}
		try {
			return courseDao.getExamCourseListCount(coursevo, examType,examState);
		} catch (Exception e) {
		   logger.error("System DB operate error!",e);
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public void updateByBrowseTimes(Integer courId, long courseAttention) throws SystemDBException {
		try {
			courseAttention = courseAttention + 1;
			courseDao.updateByBrowseTimes(courId, courseAttention);
		} catch (Exception e) {
		   logger.error("System DB operate error!",e);
		   throw new SystemDBException("System DB operate error!");
		}
	}
}
