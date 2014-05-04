package com.gta.oec.service.profession;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.profession.ProfessionVo;

/**
 * 
 * 功能描述：岗位分类
 * 
 * @author yangyang.ou
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface ProfessionService {

	/**
	 * 
	 * 功能描述：热门职业推荐名查询
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-1-9 下午6:14:14
	 *         </p>
	 * 
	 * @return
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<ProfessionVo> getTopProName(Integer id) throws BaseException;

	/**
	 * 功能描述：答疑中心分类页面.获取行业分类.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014-1-16 下午2:17:14
	 *         </p>
	 * 
	 * @return List<ProfessionVo>
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<ProfessionVo> getProfessionList() throws BaseException;
	
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
	public List<ProfessionVo> getTopProfessionList(int num) throws BaseException;
	
	
	
	
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
	public ProfessionVo getProfessionByProId(int proId) throws BaseException;
	

}
