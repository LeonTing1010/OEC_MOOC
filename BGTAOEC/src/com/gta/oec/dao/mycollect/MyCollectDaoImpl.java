/**
 * MyCollectDaoImpl.java	  V1.0   2014-1-21 下午1:34:07
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.mycollect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.mycollect.MyCollectVo;

@Repository
public class MyCollectDaoImpl extends BaseDao<MyCollectVo>  implements MyCollectDao {
	public List<MyCollectVo> getRefIdsList(Integer userId,Integer collectType){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("collectType", collectType);
		return super.findList(generateStatement("getMyCollectList"),map);
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
	public List<MyCollectVo> getCollectListByUserId(Integer userId,int refId,int collectType) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("refId", refId);
		map.put("collectType", collectType);
		return super.findList(generateStatement("getCollectVoByUserId"),map);
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
	public MyCollectVo saveMyCollect(MyCollectVo myCollectVo){
		if (myCollectVo != null) {
			super.insert(generateStatement("saveMyCollect"), myCollectVo);
		}
		return myCollectVo;
	}
	
	/**
	 * 功能描述：查询岗位是否已收藏
	 */
	public int jobHasColl(Integer jobId,Integer userId,Integer collType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobId", jobId);
		map.put("userId", userId);
		map.put("collType", collType);
		return (Integer)super.selectOne(generateStatement("jobHasColl"), map);
	}
	
	public void collJob(Integer jobId,Integer userId,Integer collType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobId", jobId);
		map.put("userId", userId);
		map.put("collType", collType);
		super.insert("collectJob", map);
	}
	
	public void delCourInfo(Integer userId,Integer courId,Integer collType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("courId", courId);
		map.put("collType", collType);
		super.getSqlSession().delete("courDels", map);
	}
}
