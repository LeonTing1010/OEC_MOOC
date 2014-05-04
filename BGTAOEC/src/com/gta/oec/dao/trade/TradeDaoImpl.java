/**
 * TradeDaoImpl.java	  V1.0   2014-1-9 下午4:25:36
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.trade.TradeVo;
@Repository
public class TradeDaoImpl extends BaseDao<TradeVo> implements TradeDao {

	@Override
	public List<TradeVo> getTradeInfo(TradeVo tradeVo,int page,int pageSize) {
		
		Map< String,Object> map = new HashMap<String, Object>();
		map.put("tradeVo", tradeVo);
		map.put("n", (page-1)*pageSize);
		map.put("m",pageSize);
		return super.findList(generateStatement("getTradeInfo"), map);
	}

}
