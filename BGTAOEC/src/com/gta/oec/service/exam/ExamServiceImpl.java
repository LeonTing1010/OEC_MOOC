/**
 * ExamServiceImpl.java	  V1.0   2014年2月11日 下午6:51:37
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.exam.ExamAnswerDetailDao;
import com.gta.oec.dao.exam.ExamDao;
import com.gta.oec.dao.exam.ExamOptionDao;
import com.gta.oec.dao.exam.ExamPaperDao;
import com.gta.oec.dao.exam.ExamQuestionDao;
import com.gta.oec.dao.exam.ExamStudentDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.service.course.CourseServiceImpl;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.gta.oec.vo.exam.ExamOptionVo;
import com.gta.oec.vo.exam.ExamPaperVo;
import com.gta.oec.vo.exam.ExamQuestionVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.usercourse.UserCourseVo;

@Service
public class ExamServiceImpl implements ExamService{

	private static final Log logger = LogFactory.getLog(CourseServiceImpl.class);
    @Autowired
	private CourseDao courseDao; 
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ExamPaperDao examPaperDao;
    @Autowired
    private ExamQuestionDao examQuestionDao;
    @Autowired
    private ExamStudentDao examStudentDao;
    @Autowired
    private ExamOptionDao examOptionDao;
    @Autowired
    private ExamAnswerDetailDao examAnswerDetailDao;
	
    
	@Override
	public List<ExamVo> getCourExamList(ExamVo examVo, CourseVo courseVo,int n, int m)throws BaseException {
		if (null==courseVo || null==examVo || n<0|| m<0) {
			throw new InterfaceFieldExcepiton("parameter error!");
		}
		try {
			return examDao.getExamList(examVo, courseVo, n, m);
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public int getTakeExamCount(ExamStudentVo examStudentVo)
			throws BaseException {
		if(null==examStudentVo){
			throw new InterfaceFieldExcepiton("parameter error!");
		}
		try{
			return examStudentDao.selectTakeExamCount(examStudentVo);
		}catch(Exception e){
			 logger.error("System DB operate error!");
			 throw new SystemDBException("System DB operate error!");
		}
	}
	
	
	public PageModel getCourExamInfoList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException{
		if (null==pageNo || pageNo.intValue()<=0||pageSize==null||pageSize.intValue()<=0) {
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		List<UserCourseVo> list = new ArrayList<UserCourseVo>();
		PageModel pageModel = new PageModel();
		list = examStudentDao.getCourExamInfoList(userId, examType, pageNo, pageSize);
		
		pageModel.setTotalItem(list.size()); //总记录数
		pageModel.setList(list);
		pageModel.setToPage(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}
	
	
	/**
	 * 插入发布的试题  by zhangjin
	 */
	public ExamVo insertExam(ExamVo examVo) throws BaseException {
		if(null==examVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try {
			return examDao.insert(examVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}
	
	/**
	 * 插入试卷  by zhangjin
	 */
	public ExamPaperVo insertExamPaper(ExamPaperVo examPaperVo)throws BaseException{
		if(null==examPaperVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try {
			return examPaperDao.insert(examPaperVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}

	/**
	 * 插入问题  by zhangjin
	 */
	public ExamQuestionVo insertExamQuestion(ExamQuestionVo examQuestionVo) throws BaseException {
		
		try {
			return examQuestionDao.insert(examQuestionVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public List<ExamVo> getExamList(ExamVo examVo) throws BaseException {
		if(null==examVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		List<ExamVo> list=new ArrayList<ExamVo>();
		list=examDao.selectExamList(examVo);
		return list;
	}
		
	
	/**
	 * 插入试题项 by zhangjin
	 */
	public ExamOptionVo insertExamOption(ExamOptionVo examOptionVo) throws BaseException{
		if(null==examOptionVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try {
			return examOptionDao.insert(examOptionVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	

	/**
	 * 根据id查询对应的问题 by zhangjin
	 */
	public ExamQuestionVo selectExamQuestionById(int examQuesId)throws BaseException {
		
		try {
			return examQuestionDao.selectExamQuestionById(examQuesId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 * 根据课程id和试题类型查询对应的课程下发布的对应的试题   by zhangjin
	 */
	public List<ExamVo> getExamListByCourId(int courId,int secId,int examType)throws BaseException {
		
		try {
			return examDao.getExamListByCourId(courId,secId, examType);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}

	@Override
	public List<ExamQuestionVo> getExamQuesList(ExamQuestionVo examQuestionVo) throws BaseException{
		if(null==examQuestionVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try{
			return examQuestionDao.selectExamQuesList(examQuestionVo);
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public ExamVo getExamByExamId(int examId) throws BaseException {
		if(examId<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try{
			return examDao.getExamById(examId);
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public int countExamQuesScore(ExamQuestionVo examQuestionVo) throws BaseException {
		if(null==examQuestionVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try{
			return examQuestionDao.selectExamQuesScore(examQuestionVo);
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public List<ExamStudentVo> getStuExamListByExamId(int examId,int examState,int pageNo,int pageSize)
			throws BaseException {
		if(examId<=0 || pageNo<0 || pageSize<0){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try{
			return examStudentDao.selectStuExamListByExamId(examId,examState,pageNo,pageSize);
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public ExamAnswerDetailVo getStuExamAnswer(int examStuId, int examQuesId) throws BaseException{
		if(examStuId<=0 || examQuesId<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			ExamAnswerDetailVo examAnswerDetailVo =new ExamAnswerDetailVo();
			examAnswerDetailVo.setExamStuId(examStuId);
			examAnswerDetailVo.setExamQuesId(examQuesId);
			List<ExamAnswerDetailVo> list=null;
			ExamAnswerDetailVo answerDetailVo=null;
			list=examAnswerDetailDao.selectExamAnswerList(examAnswerDetailVo);
			if(null!=list && list.size()>0){
				answerDetailVo=list.get(0);
			}
			return answerDetailVo;
		} catch (Exception e) {
		   logger.error("System DB operate error!");
		   throw new SystemDBException("System DB operate error!");
		}
	}
	
	
	/**
	 *  根据课程id、试卷类型、试题类型查询对应的试题试题项 by zhangjin
	 */
	public List<ExamQuestionVo> getExamQuestionByCourseId(int courseId,int secId,int examType,int examQuesType)throws BaseException {
		
		  try {
				 List<ExamQuestionVo> examQuestionlist = new ArrayList<ExamQuestionVo>();
				 
				  List<ExamVo> examlist = getExamListByCourId(courseId,secId, examType);
				
				  if(examlist!=null&&examlist.size()>0){
					  ExamVo exam = (ExamVo)examlist.get(0);
					  int paperId = exam.getPaperId(); //获得试卷id
					  
					  ExamQuestionVo examQuestionVo = new ExamQuestionVo();
					  examQuestionVo.setPaperId(paperId);
					  examQuestionVo.setExamQuesType(examQuesType);  //试题类型
					  List<ExamQuestionVo> selectQueslist = getExamQuesList(examQuestionVo);
						  
					  if(examQuesType==1||examQuesType==2){ //单选或多选
						  for(ExamQuestionVo examQuestion:selectQueslist){
							 List<ExamOptionVo> examOptionlist = getExamOptionByQuesId(examQuestion.getExamQuesId());
							 examQuestion.setExamOptionlist(examOptionlist);
							 examQuestionlist.add(examQuestion);
						  }
					  }else if(examQuesType==4){ //问答题
						  examQuestionlist = selectQueslist;
					  }
					 
				  }
				  return examQuestionlist;
				  
			} catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
	         
		
	}
	
	
	/**
	 * 
	 */
	public List<ExamOptionVo> getExamOptionByQuesId(int quesId) throws BaseException{
		
		try {
			return examOptionDao.getExamOptionByQuesId(quesId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	public int insertExamAnswerDetail(ExamAnswerDetailVo examAnswerDetailVo) throws BaseException{
		if(null==examAnswerDetailVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try {
			return examAnswerDetailDao.insert(examAnswerDetailVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}
	
	
	public ExamStudentVo insertExamStudent(ExamStudentVo examStudentVo) throws BaseException{
		if(null==examStudentVo){
			throw new InterfaceFieldExcepiton("Interface parameter error!"); 
		}
		try {
			return examStudentDao.insert(examStudentVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public void inputAnswerDetailScore(int score, int examAnsId)
			throws BaseException {
		if(examAnsId<=0 || score<0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			examAnswerDetailDao.updateStuExamScore(score, examAnsId);
		}catch(Exception e){
			 logger.error("System DB operate error!");
			 throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public void correctExamStu(int examState, double examScore, int examStuId) throws BaseException{
		if(examStuId<=0 || examScore<0 || examState<0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			examStudentDao.UpdateScoreAndState(examState, examScore, examStuId);
		}catch(Exception e){
			 logger.error("System DB operate error!");
			 throw new SystemDBException("System DB operate error!");
		}
	}

	@Override
	public ExamPaperVo getSectionPaper(int courseId, int secId, int examType) throws BaseException{
		if(courseId<=0 || secId<=0 || examType<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			ExamPaperVo examPaperVo = new ExamPaperVo();
			List<ExamVo> list = getExamListByCourId(courseId,secId,examType);
			
			if (null==list||list.size()<=0) {
				return examPaperVo;
			}
			examPaperVo.setExamId(list.get(0).getExamId());
			examPaperVo.setRadioList(getExamQuestionByCourseId(courseId, secId, examType, 1));
			examPaperVo.setMultipleChoiceList(getExamQuestionByCourseId(courseId, secId, examType, 2));
			examPaperVo.setDetermineList(getExamQuestionByCourseId(courseId, secId, examType, 3));
			examPaperVo.setSubjectiveList(getExamQuestionByCourseId(courseId, secId, examType, 4));
			return examPaperVo;
		} catch (Exception e) {
			 logger.error("System DB operate error!");
			 throw new SystemDBException("System DB operate error!");
		}
	}
	
	
	/**
	 * 
	 * 功能描述：根据考试id集合查询对应的考试学生集合  
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-22 上午10:21:53</p>
	 *
	 * @param userId
	 * @param examIds
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamStudentVo> getExamStudentListByExamIds(int userId,List examIds)throws BaseException{
		
		try {
			return examStudentDao.getExamStudentListByExamIds(userId, examIds);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	
	public ExamQuestionVo update(ExamQuestionVo examQuestionVo)throws BaseException{
		
		try {
			return examQuestionDao.update(examQuestionVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public double getExamSumScore(int examStuId) throws BaseException{
		if(examStuId<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try {
			ExamAnswerDetailVo examAnswerDetailVo =new ExamAnswerDetailVo();
			examAnswerDetailVo.setExamStuId(examStuId);
			return examAnswerDetailDao.selectSumCorrect(examAnswerDetailVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	public ExamStudentVo getExamStudentByExamIdAndStuId(int userId,int examId)throws BaseException{
		
		try {
			return examStudentDao.getExamStudentByExamIdAndStuId(userId, examId);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public Map<String, Object> getStuExamQuesAnswer(int examId,int examStuId,int type) throws BaseException {
		if(examId<=0 || examStuId<=0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		List<ExamQuestionVo> examList=null;   //试题
		double  examScore=0; //试题类型得分
		List<ExamOptionVo> optionList=null;  //选择题试题项
		ExamVo examVo=new ExamVo();
		ExamAnswerDetailVo examAnswerDetailVo =null;
		ExamQuestionVo examQuestionVo=new ExamQuestionVo();	
		if(examId>0){
			examVo.setExamId(examId);
			try {
				examVo = getExamByExamId(examId);
				examQuestionVo.setPaperId(examVo.getPaperId());
				/** 根据paperId 和 试题类型 得到试题和试题总分 **/
				examQuestionVo.setExamQuesType(type);
				examList = getExamQuesList(examQuestionVo);
				if (null != examList && examList.size() > 0) {
					for (ExamQuestionVo ques : examList) {
						if(type==1 || type==2){
							optionList = getExamOptionByQuesId(ques.getExamQuesId()); // 得到选择题试题项
							ques.setExamOptionlist(optionList);
						}						
						examAnswerDetailVo = getStuExamAnswer(examStuId,ques.getExamQuesId()); 
						if(type==2 && null!=examAnswerDetailVo){     //多选题
						//	String answer=examAnswerDetailVo.getExamAnswer();
							//char[] a=answer.toCharArray();
						}
						if(null!=examAnswerDetailVo){
							ques.setExamAnswerDetailVo(examAnswerDetailVo);  // 保存学生试题作答明细
							double score=getStuAnsdetailScore(examAnswerDetailVo.getExamAnswerId());    //学生该试题得分
							examScore+=score;   //得到学生试题类型题得分
						}	
					}
				}
				map.put("examList", examList);
				map.put("examScore", examScore);
				map.put("examType", examVo.getExamType());
				map.put("examName", examVo.getExamName());
			}catch(Exception e){
				logger.error(e); e.printStackTrace();
			}
		}
		return map;
	}
	
	
	public int getExamStudentListCount(int userId,int examType)throws BaseException{
		
		try {
			return examStudentDao.getExamStudentListCount(userId, examType);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	
	public List<ExamQuestionVo> getExamQuestionByExamId(int examId,int examQuesType)throws BaseException {
		
		  try {
				 List<ExamQuestionVo> examQuestionlist = new ArrayList<ExamQuestionVo>();
				 
				      ExamVo exam = getExamByExamId(examId);
					  int paperId = exam.getPaperId(); //获得试卷id
					  
					  ExamQuestionVo examQuestionVo = new ExamQuestionVo();
					  examQuestionVo.setPaperId(paperId);
					  examQuestionVo.setExamQuesType(examQuesType);  //试题类型
					  List<ExamQuestionVo> selectQueslist = getExamQuesList(examQuestionVo);
						  
					  if(examQuesType==1||examQuesType==2){ //单选或多选
						  for(ExamQuestionVo examQuestion:selectQueslist){
							 List<ExamOptionVo> examOptionlist = getExamOptionByQuesId(examQuestion.getExamQuesId());
							 examQuestion.setExamOptionlist(examOptionlist);
							 examQuestionlist.add(examQuestion);
						  }
					  }else if(examQuesType==4){ //问答题
						  examQuestionlist = selectQueslist;
					  }
					 
				  return examQuestionlist;
				  
			} catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
		
	}

	@Override
	public double  getStuAnsdetailScore(int examAnswerId) throws BaseException{
		ExamAnswerDetailVo examAnswerDetailVo=new ExamAnswerDetailVo();
		if(examAnswerId>0){
			examAnswerDetailVo.setExamAnswerId(examAnswerId);
		}
		try{
			return examAnswerDetailDao.selectSumCorrect(examAnswerDetailVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}

	@Override
	public int getStuExamCount(int examId, int examStatus) throws BaseException {
		if(examId<0 || examStatus<0){
			throw new InterfaceFieldExcepiton("Interface parameter error!");
		}
		try{
			ExamStudentVo examStudentVo =new ExamStudentVo();
			examStudentVo.setExamId(examId);
			examStudentVo.setExamState(examStatus);
			return examStudentDao.selectExamStuCount(examStudentVo);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
}
