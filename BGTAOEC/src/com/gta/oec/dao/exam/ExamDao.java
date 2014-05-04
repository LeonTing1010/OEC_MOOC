
package com.gta.oec.dao.exam;

import java.util.List;

import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.exam.ExamVo;

/**
 * 
 * 功能描述：作业 DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamDao{

	/**
	 * 
	 * 功能描述：插入考试数据
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-12 上午11:15:05</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamVo insert(ExamVo examVo);
	
	/**
	 * 
	 * 功能描述：根据id查询考试对象
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-24 上午10:21:37</p>
	 *
	 * @param examId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamVo getExamById(int examId);
	/**
	 * 
	 * 功能描述：根据课程id和考试类型查询对应的考试（考试类型 1：课程考试；2：课程练习；3：课程作业；4：认证考试）
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-16 下午6:44:48</p>
	 *
	 * @param courId
	 * @param secId 章节Id
	 * @param examType  考试类型
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getExamListByCourId(int courId,int secId,int examType);
	
	/**
	 * 
	 * 功能描述：查询(考试、作业、练习)列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月22日 上午9:32:36</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getExamList(ExamVo examVo);
	
	/**
	 * 
	 * 功能描述：获取考试列表（与课程、用户相关）
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月11日 下午6:27:56</p>
	 *
	 * @param examVo
	 * @param userId  用户id
	 * @param checkStatus 课程发布状态
	 * @param m  
	 * @param n
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> getExamList(ExamVo examVo,CourseVo courseVo,int n,int m);
	
	/**
	 * 
	 * 功能描述：获取考试列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月17日 上午10:58:04</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamVo> selectExamList(ExamVo examVo);
}
