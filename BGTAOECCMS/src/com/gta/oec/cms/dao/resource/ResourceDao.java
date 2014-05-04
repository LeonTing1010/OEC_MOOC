/**
 * ResourceDao.java	  V1.0   2014年4月8日 下午1:55:25
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.resource;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.resource.ResourceVo;

/**
 * 功能描述：资源类数据持久化接口.
 * 
 * @author dongs
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface ResourceDao extends SqlMapper<ResourceVo> {

	/**
	 * 功能描述：根据资源id获取这条资源.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月8日 下午2:01:15
	 *         </p>
	 * 
	 * @param resourceId资源id
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public ResourceVo getResourceByResourceId(@Param(value = "resourceId") int resourceId)
			throws DAOException;

	/**
	 * 功能描述：根据资源对象设置的查询条件获取课程资源list.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月8日 下午2:33:11
	 *         </p>
	 * 
	 * @param ResourceVo
	 *            (资源vo)
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public List<ResourceVo> getCourseResourceListByResourceVo(ResourceVo resource)
			throws DAOException;

	/**
	 * 
	 * 功能描述：根据资源对象设置的查询条件统计课程资源数量.
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-9 下午5:52:23
	 *         </p>
	 * 
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int countCourseResourceListByResourceVo(ResourceVo resource) throws DAOException;
	
	/**
	 * 
	 * 功能描述：增加课程辅助资源
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午3:37:52</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void addResource(ResourceVo resourceVo);
	
	/**
	 * 
	 * 功能描述：获取课程资源关系关联的课程辅助资源(根据ResourceVo或者只根据id查找)
	 *
	 * @author  yangyang.ou
	 * <p>创建日期 ：2014-4-18 下午4:18:25</p>
	 *
	 * @param resourceVo
	 * @param resourseVoId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ResourceVo> getReCourResourceList(@Param(value = "resource") ResourceVo resourceVo,@Param(value = "id") int resourseVoId);

}
