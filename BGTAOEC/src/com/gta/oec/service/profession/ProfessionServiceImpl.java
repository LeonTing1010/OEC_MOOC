package com.gta.oec.service.profession;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.profession.ProfessionDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.profession.ProfessionVo;

@Service
public class ProfessionServiceImpl implements ProfessionService {
	private static final Log logger = LogFactory 
			.getLog(ProfessionServiceImpl.class);
	@Autowired
	private ProfessionDao professionDao;

	@Override
	public List<ProfessionVo> getTopProName(Integer id) throws BaseException {
		try {
			return professionDao.getTopProName(1);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	@Override
	public List<ProfessionVo> getProfessionList() throws BaseException {
		List<ProfessionVo> list = new ArrayList<ProfessionVo>();
		try {
			list = professionDao.getAllProfessions();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return list;
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
	public List<ProfessionVo> getTopProfessionList(int num) throws BaseException{
		List<ProfessionVo> list = new ArrayList<ProfessionVo>();
		try {
			list = professionDao.getTopProfessionList(num);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return list;
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
	public ProfessionVo getProfessionByProId(int proId) throws BaseException{
		ProfessionVo professionVo = new ProfessionVo();
		try {
			professionVo = professionDao.getProfessionByProId(proId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}

		return professionVo;
	}
	
}
