package com.gta.oec.dao.profession;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.profession.ProfessionVo;

@Repository
public class ProfessionDaoImpl extends BaseDao<ProfessionVo> implements ProfessionDao {

	@Override
	public List<ProfessionVo> getTopProName(Integer id){
		return super.findList(generateStatement("getProTopName"), 1);
	}

	@Override
	public List<ProfessionVo> getAllProfessions() {
		
		return findList(generateStatement("getAllProfessions"));
	}
	
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
	public List<ProfessionVo> getTopProfessionList(int n){
		return findList(generateStatement("getTopProfessionList"),n);
	}
	
	
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
	public ProfessionVo getProfessionByProId(int proId){
		return (ProfessionVo)super.selectOne(generateStatement("getProfessionByProId"), proId);
	}
	
}
