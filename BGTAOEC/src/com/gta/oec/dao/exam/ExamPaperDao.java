
package com.gta.oec.dao.exam;


import com.gta.oec.vo.exam.ExamPaperVo;

/**
 * 
 * 功能描述：试卷DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamPaperDao{

	/**
	 * 
	 * 功能描述：插入试卷数据
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-12 上午11:15:05</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamPaperVo insert(ExamPaperVo examPaperVo);
}
