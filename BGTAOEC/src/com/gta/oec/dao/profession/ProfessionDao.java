package com.gta.oec.dao.profession;

import java.util.ArrayList;
import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.profession.ProfessionVo;

/**
 * 
 * 功能描述：岗位DAO层
 *
 * @author  yangyang.ou
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ProfessionDao {
	
	/**
	 * 
	 * 功能描述：热门职业推荐或职业名称查询
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-9 下午6:16:25</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ProfessionVo> getTopProName(Integer id);
	
	
	/**
	 * 功能描述：获取所有行业明细.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014-1-16 下午1:53:14</p>
	 *
	 * @return List<ProfessionVo>
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ProfessionVo> getAllProfessions();
	
	/**
	 * 功能描述：答疑中心获取前N个行业.
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-3-13
	 *         </p>
	 * 
	 * @return List<ProfessionVo>
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<ProfessionVo> getTopProfessionList(int num);
	
	
	/**
	 * 功能描述：根据行业ID取得行业
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-1-22 
	 *         </p>
	 * 
	 * @return List<ProfessionVo>
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public ProfessionVo getProfessionByProId(int proId);
	
}
