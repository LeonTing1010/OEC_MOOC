
package com.gta.oec.dao.exam;


import java.util.List;

import com.gta.oec.vo.exam.ExamOptionVo;

/**
 * 
 * 功能描述：题目项DAO层
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ExamOptionDao{

	/**
	 * 
	 * 功能描述：插入题目项
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-12 上午11:15:05</p>
	 *
	 * @param examVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ExamOptionVo insert(ExamOptionVo examOptionVo);
	
	/**
	 * 
	 * 功能描述：根据试题id查询试题项
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-20 上午10:52:51</p>
	 *
	 * @param quesId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ExamOptionVo> getExamOptionByQuesId(int quesId);
}
