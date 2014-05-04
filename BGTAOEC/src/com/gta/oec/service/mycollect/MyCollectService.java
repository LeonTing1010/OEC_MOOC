/**
 * MyCollectService.java	  V1.0   2014-1-21 下午1:32:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.mycollect;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.mycollect.MyCollectVo;

public interface MyCollectService {
	/**
	 * 
	 * 功能描述：根据用户id查询课程id集合
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-1-21 下午1:37:38</p>
	 *
	 * @param userId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<MyCollectVo> getRefIdList(Integer userId,Integer collectType)throws BaseException;
	
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
	public List<MyCollectVo> getCollectListByUserId(Integer userId,int refId,int collectType)throws BaseException;
	
	
	
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
	public MyCollectVo saveMyCollect(MyCollectVo myCollectVo,int jobId) throws BaseException;
	
	/**
	 * 
	 * 功能描述：删除收藏的课程/岗位
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-2-17 下午7:13:17</p>
	 *
	 * @param userId
	 * @param courId
	 * @param collType
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void delMyCollect(Integer userId,Integer courId,Integer collType) throws BaseException;
	
}
