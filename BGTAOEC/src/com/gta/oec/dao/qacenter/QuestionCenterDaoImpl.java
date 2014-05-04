package com.gta.oec.dao.qacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.common.SearchVo;
import com.gta.oec.vo.qacenter.QuestionVo;

@Repository
public class QuestionCenterDaoImpl extends BaseDao<QuestionVo> implements
		QuestionCenterDao {

	/**
	 * 
	 * 功能描述：根据行业得到最新提问列表信息
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getNewQuestionList(int proId) {
		return super.findList(generateStatement("getNewQuestionList"), proId);
	}

	/**
	 * 
	 * 功能描述：根据行业得到最热门提问列表信息
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<QuestionVo> getHotQuestionList(int proId, int userId,
			int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proId", proId);
		map.put("userId", userId);
		map.put("n", (pageNo - 1) * pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getHotQuestionList"), map);
	}

	/**
	 * 
	 * 功能描述：根据用户id和问题id集合查询问题列表
	 * 
	 * @author zhangjin
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
	public List<QuestionVo> getQuestionListByIdsAndUserId(int userId,
			List quesIdslist, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("list", quesIdslist);
		map.put("n", (pageNo - 1) * pageSize);
		map.put("m", pageSize);
		return super.findList(
				generateStatement("getQuestionListByIdsAndUserId"), map);
	}

	/**
	 * 
	 * 功能描述：保存问题
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo saveQuestion(QuestionVo questionVo) {
		if (questionVo != null) {
			super.insert(generateStatement("saveQuestion"), questionVo);
		}
		return questionVo;
	}

	/**
	 * 
	 * 功能描述：根据用户-更新问题说明
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-10
	 *         </p>
	 * @param QuestionVo对象
	 * @return QuestionVo对象
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public QuestionVo updateQuestion(QuestionVo questionVo) {
		if (questionVo != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("quesID", questionVo.getQuesID());
			map.put("quesDescription", questionVo.getQuesDescription());
			super.update(generateStatement("updateQuestion"), map);
		}
		return questionVo;
	}

	/**
	 * 
	 * 功能描述：根据课程id查询问题列表
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-15 下午2:23:00
	 *         </p>
	 * 
	 * @param courId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionListByCourId(int courId, int quesType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courId", courId);
		map.put("quesType", quesType);
		return super.findList(generateStatement("getQuestionListByCourId"), map);
	}

	/**
	 * 
	 * 功能描述：根据用户id查询问题列表
	 * 
	 * @author jin.zhang
	 *         <p>
	 *         创建日期 ：2014-1-15 下午2:23:00
	 *         </p>
	 * 
	 * @param courId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<QuestionVo> getQuestionListByUserId(int userId,int quesType) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("quesType", quesType);
		super.update(generateStatement("updateQuestion"), map);
		return super.findList(generateStatement("getQuestionListByUserId"),map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.QuestionCenterDao#getTwoChosenQuestions()
	 */
	@Override
	public List<QuestionVo> getChosenQuestions(int limitCont) {
		return super.findList(generateStatement("getChosenQuestionList"),
				limitCont);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.QuestionCenterDao#getQuestion(int)
	 */
	@Override
	public QuestionVo getQuestion(int quesId) {
		return (QuestionVo) selectOne(generateStatement("getQuestionDital"),
				quesId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.QuestionCenterDao#
	 * getRelationQuestionListByProfessionID(int)
	 */
	@Override
	public List<QuestionVo> getRelationQuestionListByProfessionID(int proID) {

		return findList(generateStatement("getRelationQuestionByProfID"), proID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.dao.qacenter.QuestionCenterDao#getQuestionListByJobGroupId
	 * (int)
	 */
	@Override
	public List<QuestionVo> getQuestionListByJobGroupId(int jobGroupId,int quesId, int num) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("jobGroupId", jobGroupId);
		parameterMap.put("quesId", quesId);
		parameterMap.put("num", num);
		return findList(generateStatement("getQuestionListByJobGroupId"),
				parameterMap);
	}

	/**
	 * 
	 * 功能描述：根据问题ID，更新关注数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-20
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateAttentionQuestionCount(int quesId) {
		return super.update(generateStatement("updateAttentionQuestionCount"),
				quesId);
	}
	
	/**
	 * 
	 * 功能描述：根据问题ID，删除更新关注数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-4-1
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int deleteAttentionQuestionCount(int quesId) {
		return super.update(generateStatement("deleteAttentionQuestionCount"),
				quesId);
	}
	
	
	/**
	 * 
	 * 功能描述：根据问题ID，更新回答数量
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-17
	 *         </p>
	 * 
	 * @param quesId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int updateAnswerQuestionCount(int quesId) {
		return super.update(generateStatement("updateAnswerQuestionCount"),
				quesId);
	}
	

	@Override
	public List<QuestionVo> getQuestionPageByAnswUserId(int userId,
			int pageSize, int toPage) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		List<QuestionVo> list = null;
		parameter.put("userId", userId);
		parameter.put("pageSize", pageSize);
		int offset = (toPage - 1) * pageSize;
		parameter.put("offset", offset);
		list = findList(generateStatement("questionPageByAnswUserId"),
				parameter);
		return list;
	}

	@Override
	public int getQuestionCountByAnswUserId(int userId) {
		return (Integer) selectOne(
				generateStatement("questionCountByAnswUserId"), userId);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gta.oec.dao.qacenter.QuestionCenterDao#getSortQuestionPage(int,
	 * int, int, int)
	 */
	@Override
	public List<QuestionVo> getSortQuestionPage(int flag,int type, int id, int pageNo,
			int pageSize) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		List<QuestionVo> list = null;
		parameter.put("flag", flag);
		parameter.put("type", type);
		parameter.put("id", id);
		parameter.put("limit", pageSize);
		int offset = (pageNo - 1) * pageSize;
		parameter.put("offset", offset);
		list = findList(generateStatement("getSortQuestionPage"), parameter);
		return list;
	}
	
	/**
	 * 功能描述：获取搜索问题
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-13 </p>
	 *
	 * @param type .分类类型.0:全部.1:行业.2:岗位群.
	 * @param id	行业ID或岗位群ID.
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<QuestionVo> getSearchListPage(SearchVo searchVo,int type,int id,int pageNo,int pageSize){
		Map<String, Object> parameter = new HashMap<String, Object>();
		List<QuestionVo> list = new ArrayList<QuestionVo>();
		
		parameter.put("search", searchVo.getSearchtext());
		parameter.put("type", type);
		parameter.put("id", id);
		parameter.put("limit", pageSize);
		int offset = (pageNo - 1) * pageSize;
		parameter.put("offset", offset);
		list = findList(generateStatement("getSearchListPage"), parameter);
		return list;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.dao.qacenter.QuestionCenterDao#countSortQuestionPage(int,
	 * int)
	 */
	@Override
	public int countSortQuestionPage(int type, int id) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("type", type);
		parameter.put("id", id);
		return (Integer) selectOne(generateStatement("countSortQuestionPage"),
				parameter);
	}
	
	/**
	 * 功能描述：根据搜索关键字，统计搜索问题总条数.
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-13</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int countSearchListPage(SearchVo searchVo,int type,int id){
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", searchVo.getSearchtext());
		parameter.put("type", type);
		parameter.put("id", id);
		return (Integer) selectOne(generateStatement("countSearchListPage"),parameter);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gta.oec.dao.qacenter.QuestionCenterDao#getQuestionByTypeByStatus(int,
	 * int, int, int)
	 */
	@Override
	public List<QuestionVo> getQuestionPageListByQuestionUserByquesType(int userId,int status,int quesType,int pageNo,int pageSize){

		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("userId",userId);
		parameter.put("quesType", quesType);
		parameter.put("limit", pageSize);
		int offset=(pageNo-1)*pageSize;
		parameter.put("offset", offset);
		if (status==0) {
			parameter.put("status", status);
			return findList(generateStatement("getUnsolvedQuestionPageListByQuestionUserByquesType"), parameter);
		}else {
		return findList(generateStatement("getSolvedQuestionPageListByQuestionUserByquesType"), parameter);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gta.oec.dao.qacenter.QuestionCenterDao#countQuestionPageListByQuestionUserByquesType(int, int, int, int, int)
	 */
	@Override
	public int countQuestionByQuestionUserByquesType(int userId,
			int status, int quesType) {
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("userId",userId);
		parameter.put("quesType", quesType);
		if(status==0){
			parameter.put("status", status);
			return (Integer)selectOne(generateStatement("countUnsolvedQuestionPageListByQuestionUserByquesType"), parameter);
		}else {
			return (Integer)selectOne(generateStatement("countSolvedQuestionPageListByQuestionUserByquesType"), parameter);
			
		}
	}

	
	/**
	 * 功能描述：更改问题回答状态
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int updateQuestionStatus(int quesId){
		return super.update(generateStatement("updateQuestionStatus"), quesId);
	}

	
	/**
	 * 根据问题id集合查询问题   by zhangjin
	 */
	public List<QuestionVo> getQuestionListByIds(int userId,List quesIdlist,int pageNo,int pageSize){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("list", quesIdlist);
		map.put("n", (pageNo - 1) * pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getQuestionListByIds"), map);
	}

	@Override
	public List getQuesIdListByUserId(int userId,int quesType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("quesType",quesType);
		return super.findList(generateStatement("getQuesIdListByUserId"), map);
	}
	

	/**
	 * 根据问题id集合查询问题   by zhangjin
	 */
	public List<QuestionVo> getQuestionListByIds(List<?> quesIdlist,int pageNo,int pageSize){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", quesIdlist);
		map.put("n", (pageNo - 1) * pageSize);
		map.put("m", pageSize);
		return super.findList(generateStatement("getQuestionListByIds"), map);
	}

	@Override
	public List<QuestionVo> getQuestionListByKeyword(String keyString,int num) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", keyString);
		parameter.put("limit", num);
		return  findList(generateStatement("getQuestionListByKeyword"), parameter);
	}

	@Override
	public List<QuestionVo> getQestionList(QuestionVo vo) {
		return findList(generateStatement("getQestionList"),vo);
	}

}
