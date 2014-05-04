/**
 * ExamAnswerDetailDao.java	  V1.0   2014年2月19日 下午8:09:50
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.exam;

import java.util.List;

import com.gta.oec.vo.exam.ExamAnswerDetailVo;
import com.gta.oec.vo.exam.ExamOptionVo;

/**
 * 
 * 功能描述：学生考试回答Dao层
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamAnswerDetailDao {
	
	
    /**
     * 
     * 功能描述：插入学生的做题答案
     *
     * @author  jin.zhang
     * <p>创建日期 ：2014-2-20 下午3:40:15</p>
     *
     * @param examOptionVo
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public int insert(ExamAnswerDetailVo examAnswerDetailVo);
	/**
	 * 
	 * 功能描述：查询学生考试作答集合
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月19日 下午8:16:46</p>
	 *
	 * @param examAnswerDetailVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamAnswerDetailVo> selectExamAnswerList(ExamAnswerDetailVo examAnswerDetailVo);
	
	/**
	 * 
	 * 功能描述：根据作答明细ID保存批阅分数
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月20日 下午4:30:22</p>
	 *
	 * @param score
	 * @param examAnsId
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateStuExamScore(int score,int examAnsId);
	
	/**
	 * 
	 * 功能描述：根据学生考试Id得到考试总分
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年2月24日 下午2:05:22</p>
	 *
	 * @param examAnswerDetailVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double  selectSumCorrect(ExamAnswerDetailVo examAnswerDetailVo);

}
