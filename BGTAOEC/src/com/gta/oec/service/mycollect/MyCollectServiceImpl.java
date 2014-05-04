/**
 * MyCollectServiceImpl.java	  V1.0   2014-1-21 下午1:32:54
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.mycollect;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.job.JobDao;
import com.gta.oec.dao.mycollect.MyCollectDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.service.course.CourseServiceImpl;
import com.gta.oec.vo.mycollect.MyCollectVo;

@Service
public class MyCollectServiceImpl implements MyCollectService{
	
	private static final Log logger = LogFactory.getLog(CourseServiceImpl.class); 
	@Override
	public List<MyCollectVo> getRefIdList(Integer userId,Integer collectType)throws BaseException {
		try {
		   return myCollectDao.getRefIdsList(userId,collectType);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}

	/**
	 * 
	 * 功能描述：根据用户id、岗位ID,收藏类型、查询收藏对象
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-21 </p>
	 *
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<MyCollectVo> getCollectListByUserId(Integer userId,int refId,int collectType)throws BaseException{
		try {
			   return myCollectDao.getCollectListByUserId(userId,refId,collectType);
			} catch (Exception e) {
				logger.error("DB Operate Error", e);
				throw new SystemDBException("DB Operate Error!");
			}
	}
	
	
	/**
	 * 功能描述：保存岗位收藏
	 * 
	 * @author 刘祚家
	 *         <p>
	 *         创建日期 ：2014-2-12 
	 *         </p>
	 * 
	 * @param MyCollectVo
	 * @return 保存个数
	 * @throws BaseException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public MyCollectVo saveMyCollect(MyCollectVo myCollectVo,int jobId) throws BaseException{
		try {
			myCollectVo=myCollectDao.saveMyCollect(myCollectVo);
			
			//更新收藏数量
			jobDao.updateMyCollectCount(jobId);
			
			return myCollectVo;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	/**
	 *   据用户id，岗位id,收藏类型(1、岗位收藏 2、课程收藏)，删除对应的收藏
	 */
	public void delMyCollect(Integer userId,Integer courId,Integer collType) throws BaseException{
		try{
			if(null==userId || userId.intValue()<=0 ||null==courId || courId.intValue()<=0 || null==collType || collType.intValue()<=0){
				throw new InterfaceFieldExcepiton("Interface parameter error!");
			}
			myCollectDao.delCourInfo(userId,courId,collType);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	
	 	@Autowired
	 	private MyCollectDao myCollectDao;
	 	@Autowired
	 	private JobDao jobDao;
	}


	

