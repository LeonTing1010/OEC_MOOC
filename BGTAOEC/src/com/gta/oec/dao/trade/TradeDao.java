/**
 * TradeDao.java	  V1.0   2014-1-9 下午4:19:47
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.trade;

import java.util.List;

import com.gta.oec.vo.trade.TradeVo;

/**
 * 
 * 功能描述：行业DAO
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface TradeDao {
	/**
	 * 
	 * 功能描述：获取行业信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-9 下午4:25:12</p>
	 *
	 * @param tradeDao
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
   public List<TradeVo> getTradeInfo(TradeVo tradeVo,int page,int pageSize);
}
