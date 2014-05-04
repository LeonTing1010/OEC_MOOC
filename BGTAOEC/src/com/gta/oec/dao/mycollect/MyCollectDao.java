/**
 * MyCollectDao.java	  V1.0   2014-1-21 下午1:33:47
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.mycollect;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.mycollect.MyCollectVo;

public interface MyCollectDao {
	//根据用户id查询课程id集合
	public List<MyCollectVo> getRefIdsList(Integer courId,Integer Integer);
	
	
	/**
	 * 
	 * 功能描述：根据用户id、关联ID,收藏类型、查询收藏对象
	 *
	 * @author  刘祚家
	 * <p>创建日期 ：2014-2-21 </p>
	 *
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(bingzhogn.qin ： 修改接口名称、修改实现方式)</p>
	 */
	public List<MyCollectVo> getCollectListByUserId(Integer userId,int refId,int collectType);
	
	
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
	public MyCollectVo saveMyCollect(MyCollectVo myCollectVo);
	
	/**
	 * 
	 * 功能描述：查询岗位是否收藏
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 下午3:05:06</p>
	 *
	 * @param jobId
	 * @param userId
	 * @param collType
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int jobHasColl(Integer jobId,Integer userId,Integer collType);
	
	/**
	 * 
	 * 功能描述：收藏当前岗位
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 下午3:40:02</p>
	 *
	 * @param jobId
	 * @param userId
	 * @param collType
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void collJob(Integer jobId,Integer userId,Integer collType);
	
	/**
	 * 
	 * 功能描述：删除收藏的课程/岗位
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 下午7:16:50</p>
	 *
	 * @param userId
	 * @param courId
	 * @param collType
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void delCourInfo(Integer userId,Integer courId,Integer collType);
}
