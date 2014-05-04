/**
 * CourseService.java	  V1.0   2013-12-27 ����10:30:24
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.teacher;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.teacher.TeacherVo;

/**
 * 
 * 功能描述：根据用户id查询对应的老师对象
 * 
 * @author jin.zhang
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface TeacherService {

	public TeacherVo getTeacherByUserId(Integer userId) throws BaseException;

	/**
	 * 
	 * 功能描述：根据用户id查询学生信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-1-14 上午11:02:59
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
	public StudentVO getStuByUserId(Integer stuId) throws BaseException;

	/**
	 * 功能描述：根据行业查询分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-20 上午11:33:38
	 *         </p>
	 * 
	 * @param proId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public PageModel getTeacherListByProfessionId(int proId, int pageNo,
			int pageSize) throws BaseException;

	/**
	 * 功能描述：未分类老师分页信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-23 下午5:56:56
	 *         </p>
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public PageModel getTeacherPage(int pageNo, int pageSize)
			throws BaseException;

	/**
	 * 功能描述：根据岗位群id获取教师分页.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-20 下午12:00:29
	 *         </p>
	 * 
	 * @param jobGroupId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public PageModel getTeacherListByjobGroupId(int jobGroupId, int pageNo,
			int pageSize) throws BaseException;

	/**
	 * 功能描述：获取一定数量的老师列表.
	 * 
	 * @author Administrator
	 *         <p>
	 *         创建日期 ：2014-1-20 下午12:21:41
	 *         </p>
	 * 
	 * @param num
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<TeacherVo> getTeacherList(int num) throws BaseException;

	/**
	 * 功能描述：通过教师Id获取教师.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-21 上午10:21:16
	 *         </p>
	 * 
	 * @param teacherId
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public TeacherVo getTeacherById(int teacherId) throws BaseException;

	/**
	 * 
	 * 功能描述：更新教师信息
	 * 
	 * @author Miaoj
	 *         <p>
	 *         创建日期 ：2014-1-22 下午2:25:38
	 *         </p>
	 * 
	 * @param teacher
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int updateTeacher(TeacherVo teacher) throws BaseException;

	/**
	 * 功能描述：获取某个老师已解答未解答问题.quesType是问题分类(课程,答疑)(分页)
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-2-11 下午7:04:06
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
	public PageModel getQuestionPageModel(QuestionUserVo questionUserVo,
			int quesType, int pageNo, int pageSize) throws BaseException;

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
			 int pageNo, int pageSize) throws BaseException;
	
	
	
	/**
	 * 功能描述：统计总条数某个老师已解答未解答问题.quesType是问题分类(课程,答疑)
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-2-12 下午2:36:18
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
	public int countQuestionPageModel(QuestionUserVo questionUserVo,
			int quesType) throws BaseException;
	
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
	public int quesAddCout(QuestionUserVo questionUserVo) throws BaseException;
	
	
	
	/**
	 * 
	 * 功能描述：获取某个老师已解答未解答问题.quesType是问题分类(课程,答疑)(不分页)
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月12日 下午5:06:31</p>
	 *
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getTeaQuestionList(QuestionUserVo questionUserVo,
			int quesType, int pageNo, int pageSize)throws BaseException;
	
	/**
	 * 功能描述：根据追问问题ID取得问题信息
	 * 
	 * @author 刘祚家
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
	public QuestionAddVo getQuestionAddVo(int quesAddId) throws BaseException;
}
