/**
 * ExamService.java	  V1.0   2014年2月11日 下午6:48:51
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.exam;

import java.util.List;
import java.util.Map;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.gta.oec.vo.exam.ExamOptionVo;
import com.gta.oec.vo.exam.ExamPaperVo;
import com.gta.oec.vo.exam.ExamQuestionVo;
import com.gta.oec.vo.exam.ExamStudentVo;
import com.gta.oec.vo.exam.ExamVo;

/**
 * 
 * 功能描述：考试
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamService {


	/**
	 * 
	 * 功能描述：获取考试列表，和课程用户相关
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月11日 下午6:50:41</p>
	 *
	 * @param examVo
	 * @param courseVo
	 * @param n
	 * @param m
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getCourExamList(ExamVo examVo,CourseVo courseVo,int n,int m)throws BaseException;
	
	/**
	 * 
	 * 功能描述：查询参加某门考试的记录数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月12日 上午10:18:08</p>
	 *
	 * @param examStudentVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getTakeExamCount(ExamStudentVo examStudentVo)throws BaseException;
	
	
	/**
	 * 
	 * 功能描述： 插入考试
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-13 上午9:49:42</p>
	 *
	 * @param examVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamVo insertExam(ExamVo examVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：插入试卷
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-13 下午1:29:32</p>
	 *
	 * @param examPaperVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamPaperVo insertExamPaper(ExamPaperVo examPaperVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：插入试题
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-13 下午4:47:22</p>
	 *
	 * @param examQuestionVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamQuestionVo insertExamQuestion(ExamQuestionVo examQuestionVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据用户id查询所学课程考试信息 
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-14 上午11:18:32</p>
	 *
	 * @param userId
	 * @param examType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public PageModel getCourExamInfoList(int userId,int examType,Integer pageNo,Integer pageSize) throws BaseException;
	

	/**
	 * 
	 * 功能描述：获取考试列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月17日 上午11:02:37</p>
	 *
	 * @param examVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getExamList(ExamVo examVo)throws BaseException;
	

	
	/**
	 * 
	 * 功能描述：插入试题项
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-17 上午8:54:46</p>
	 *
	 * @param examOptionVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamOptionVo insertExamOption(ExamOptionVo examOptionVo)throws BaseException;
	
	
	public ExamQuestionVo selectExamQuestionById(int examQuesId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据课程id和试题类型查询对应的课程下发布的对应的试题 
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-19 下午5:51:19</p>
	 *
	 * @param courId
	 * @param secId
	 * @param examType
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getExamListByCourId(int courId,int secId,int examType)throws BaseException;

	/**
	 * 
	 * 功能描述：查询试卷试题(多条件查询)
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午1:55:25</p>
	 *
	 * @param examQuestionVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamQuestionVo> getExamQuesList(ExamQuestionVo examQuestionVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据考试Id查询考试
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午2:43:38</p>
	 *
	 * @param examId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamVo getExamByExamId(int examId) throws BaseException;
	
	/**
	 * 
	 * 功能描述：计算试卷试题分数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午4:19:24</p>
	 *
	 * @param examQuestionVo
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countExamQuesScore(ExamQuestionVo examQuestionVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据考试Id查询学生考试集合
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月21日 下午2:33:49</p>
	 *
	 * @param examId
	 * @param examState
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamStudentVo> getStuExamListByExamId(int examId,int examState,int pageNo,int pageSize)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据学生考试Id 和 试题Id 得到某学生某试题的作答详细
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午8:19:30</p>
	 *
	 * @param examStuId
	 * @param examQuesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamAnswerDetailVo getStuExamAnswer(int examStuId,int examQuesId)throws BaseException;
	/**
	 * 
	 * 功能描述：插入试题批阅分数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月20日 下午4:40:06</p>
	 *
	 * @param score
	 * @param examAnsId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void inputAnswerDetailScore(int score,int examAnsId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：【-根据试题id查询试题项-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-20 上午10:53:30</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamOptionVo> getExamOptionByQuesId(int quesId) throws BaseException;
	
	
	/**
	 * 
	 * 功能描述：【-根据课程id、试卷类型、试题类型查询对应的试题试题项 -】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-20 上午11:15:35</p>
	 *
	 * @param courseId
	 * @param examType 1-考试  2-练习 3-作业
	 * @param examQuesType 1-单选  2-多选 3-判断 4-问答
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamQuestionVo> getExamQuestionByCourseId(int courseId,int secId,int examType,int examQuesType)throws BaseException;
	/**
	 * 
	 * 功能描述：获取节考试 or 练习 or 作业
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-2-20 下午7:09:37</p>
	 *
	 * @param courseId
	 * @param secId
	 * @param examType 1-考试  2-练习 3-作业
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamPaperVo getSectionPaper(int courseId,int secId,int examType)throws BaseException;
	
	public int insertExamAnswerDetail(ExamAnswerDetailVo examAnswerDetailVo)throws BaseException;
	
	
	public ExamStudentVo insertExamStudent(ExamStudentVo examStudentVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据学生考试Id，批改学生考试分数和状态
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月21日 上午9:41:43</p>
	 *
	 * @param examState
	 * @param examScore
	 * @param examStuId
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void correctExamStu(int examState,double examScore,int examStuId)throws BaseException;
	
	
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
	public List<ExamStudentVo> getExamStudentListByExamIds(int userId,List examIds)throws BaseException;
	
	
	public ExamQuestionVo update(ExamQuestionVo examQuestionVo)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据学生考试Id得到考试总分
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月24日 下午2:07:25</p>
	 *
	 * @param examStuId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double  getExamSumScore(int examStuId)throws BaseException;
	
	
	public ExamStudentVo getExamStudentByExamIdAndStuId(int userId,int examId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据试题类型得到学生考试试题、学生试题得分和回答
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月3日 下午3:37:41</p>
	 *
	 * @param examId  考试Id
	 * @param examStuId  学生考试Id
	 * @param type 试题类型
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Map<String, Object> getStuExamQuesAnswer(int examId,int examStuId,int type)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据学生id和考试类型查询记录数  
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-3-3 上午11:12:23</p>
	 *
	 * @param userId
	 * @param examType
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getExamStudentListCount(int userId,int examType)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据考试id和试题类型查询考试问题
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-3-4 下午1:39:16</p>
	 *
	 * @param examId
	 * @param examQuesType
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamQuestionVo> getExamQuestionByExamId(int examId,int examQuesType)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据考试回答Id得到学生该题得分
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月6日 下午12:02:04</p>
	 *
	 * @param examAnswerId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double  getStuAnsdetailScore(int examAnswerId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：根据考试Id和批阅状态，查询学生考试记录数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月10日 下午4:36:26</p>
	 *
	 * @param examId
	 * @param examStatus
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getStuExamCount(int examId,int examStatus)throws BaseException;
	
}
