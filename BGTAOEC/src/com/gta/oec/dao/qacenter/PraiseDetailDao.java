package com.gta.oec.dao.qacenter;

import java.util.List;
import java.util.Map;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.qacenter.PraiseDetailVo;

public interface PraiseDetailDao {

	/**
	 * 
	 * 功能描述：保存赞明细
	 * 
	 * @author 刘祚家
	 * <p>
	 * 创建日期 ：2014-1-16
	 * </p>
	 * 
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 * <p>
	 *  修改历史 ：(修改人，修改时间，修改原因/内容)
	 * </p>
	 */
	public PraiseDetailVo savePraiseDetail(PraiseDetailVo praiseDetailVo);

	
	/**
	 * 
	 * 功能描述：删除赞明细
	 * 
	 * @author 刘祚家
	 * <p>
	 * 创建日期 ：2014-4-1
	 * </p>
	 * 
	 * @return 删除个数
	 * @throws BaseException
	 * 
	 * <p>
	 *  修改历史 ：(修改人，修改时间，修改原因/内容)
	 * </p>
	 */
	public PraiseDetailVo deletePraiseDetail(PraiseDetailVo praiseDetailVo);
	
	
	/**
	 * 功能描述：通过用户id,回答id获取赞的明细列表.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-16 下午5:34:40</p>
	 *
	 * @param userID
	 * @param answID
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<PraiseDetailVo> getPraiseDetailByUserIdByAnsId(int userID,int answID);
	
	
}
