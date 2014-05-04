package com.gta.oec.dao.qacenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.AnswerVo;
import com.gta.oec.vo.qacenter.QuestionAddVo;
import com.gta.oec.vo.qacenter.QuestionUserVo;
import com.gta.oec.vo.qacenter.QuestionVo;

@Repository
public class QuestionAddDaoImpl extends BaseDao<QuestionAddVo> implements QuestionAddDao {
	/**
	 * 
	 * 功能描述：根据回答ID,取得追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-22
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionAddVo> getAllQuestionAddListByAnswId(int answId){
		return super.findList(generateStatement("getAllQuestionAddListByAnswId"), answId);
	}
	
	/**
	 * 
	 * 功能描述：根据所有回答,取得所有追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-10
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionAddVo> getAllQuestionAddListByAnswList(List<AnswerVo> list){
		return super.findList(generateStatement("getAllQuestionAddListByAnswList"), list);
	}
	
	
	/**
	 * 
	 * 功能描述：保存追加问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-23
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionAddVo saveQuestionAddByUser(QuestionAddVo questionAddVo) {
		if (questionAddVo != null) {
			super.insert(generateStatement("saveQuestionAddByUser"), questionAddVo);
		}
		return questionAddVo;
	}
	
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
	public int quesAddCout(int userId){
		return (Integer)selectOne(generateStatement("quesAddCout"), userId);
	}
	
	/**
	 * 功能描述：更改问题回答状态
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-17</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateQuestionAddStatus(int quesAddId){
		return super.update(generateStatement("updateQuestionAddStatus"), quesAddId);
	}
	
	/**
	 * 功能描述：获取追问问题
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-17</p>
	 *
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionAddVo> getQuestionAddPageModel(int teacherUserID,int status,int pageNo,int pageSize){
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teacherId",teacherUserID);
		map.put("status", status);
		map.put("limit", pageSize);
		int offset=(pageNo-1)*pageSize;
		map.put("offset", offset);
		
		return super.findList(generateStatement("getQuestionAddPageModel"), map);
	}
	
	/**
	 * 功能描述：获取追问问题总数
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-17</p>
	 *
	 * @param questionUserVo
	 * @param quesType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int getQuestionAddPageModelCount(int teacherUserID,int status,int pageNo,int pageSize){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teacherId",teacherUserID);
		map.put("status", status);
		map.put("limit", pageSize);
		int offset=(pageNo-1)*pageSize;
		map.put("offset", offset);
		
		return (Integer)selectOne(generateStatement("getQuestionAddPageModelCount"), map);
	}
	
	/**
	 * 功能描述：通过ID获取追问问题详情.
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-17 
	 *         </p>
	 * 
	 * @param quesAddId
	 * @return QuestionAddVo
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public QuestionAddVo getQuestionAddVo(int quesAddId){
		return (QuestionAddVo) selectOne(generateStatement("getQuestionAddVo"),quesAddId);
	}
}
