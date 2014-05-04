
package com.gta.oec.dao.exam;

import java.util.List;

import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamStudentVo;

/**
 * 
 * 功能描述：作业 DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamStudentDao {

	
	
	public ExamStudentVo insert(ExamStudentVo examStudentVo);
	/**
	 * 根据考试id集合查询对应的考试学生集合
	 * 功能描述：
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-18 下午4:57:56</p>
	 *
	 * @param examIds
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamStudentVo> getExamStudentListByExamIds(int userId,List examIds);
	
	/**
	 * 
	 * 功能描述：根据学生id和考试类型查询记录数
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-20 下午6:40:09</p>
	 *
	 * @param userId
	 * @param examType
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getExamStudentListCount(int userId,int examType);
	
	/**
	 * 
	 * 功能描述：查询参加某门考试的记录数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月12日 上午10:06:46</p>
	 *
	 * @param examStudentVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int selectTakeExamCount(ExamStudentVo examStudentVo);
	
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
	public List getCourExamInfoList(int userId,int examType,int pageNo,int pageSize);
	
	/**
	 * 
	 * 功能描述：根据考试Id查询学生考试集合
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月21日 下午2:34:34</p>
	 *
	 * @param examId
	 * @param examState
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamStudentVo> selectStuExamListByExamId(int examId,int examState,int pageNo,int pageSize);
	
	/**
	 * 
	 * 功能描述：根据考试id学生id查询学生考试对象
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-26 上午10:48:06</p>
	 *
	 * @param userId
	 * @param examId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamStudentVo getExamStudentByExamIdAndStuId(int userId,int examId);
	
	/**
	 * 
	 * 功能描述：更新学生考试分数和批阅状态
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月21日 上午9:49:47</p>
	 *
	 * @param examState
	 * @param examScore
	 * @param examStuId
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void UpdateScoreAndState(int examState,double examScore,int examStuId);
	
	/**
	 * 
	 * 功能描述：查询学生考试记录数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月10日 下午4:34:27</p>
	 *
	 * @param examStudentVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int selectExamStuCount(ExamStudentVo examStudentVo);
}