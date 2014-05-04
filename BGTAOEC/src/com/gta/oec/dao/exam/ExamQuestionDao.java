
package com.gta.oec.dao.exam;


import java.util.List;

import com.gta.oec.vo.exam.ExamQuestionVo;

/**
 * 
 * 功能描述：题目 DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamQuestionDao{

	/**
	 * 
	 * 功能描述：插入试卷试题
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-12 上午11:15:05</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamQuestionVo insert(ExamQuestionVo examQuestionVo);
	
	
	public ExamQuestionVo selectExamQuestionById(int examQuesId);
	
	/**
	 * 
	 * 功能描述：查询试卷试题
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午1:47:07</p>
	 *
	 * @param examQuestionVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamQuestionVo> selectExamQuesList(ExamQuestionVo examQuestionVo);
	
	/**
	 * 
	 * 功能描述：查询计算试卷试题分数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午4:16:48</p>
	 *
	 * @param examQuestionVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int selectExamQuesScore(ExamQuestionVo examQuestionVo);
	
	public ExamQuestionVo update(ExamQuestionVo examQuestionVo);
}

