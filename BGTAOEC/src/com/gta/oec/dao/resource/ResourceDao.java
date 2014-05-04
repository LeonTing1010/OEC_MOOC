/**
 * Resource.java	  V1.0   2014年1月15日 上午10:20:13
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */
package com.gta.oec.dao.resource;

import java.util.HashMap;
import java.util.List;

import com.gta.oec.vo.resource.ResourceVo;

/**
 * 
 * 功能描述：课程辅助资源 DAO
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface ResourceDao {

	/**
	 * 
	 * 功能描述：获取课程辅助资源
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月15日 上午11:15:07</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ResourceVo> getResourceList(ResourceVo resourceVo);
	public ResourceVo getResourceById(Integer resourceId);
	
	/**
	 * 
	 * 功能描述：增加课程辅助资源
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月16日 上午9:36:27</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ResourceVo addResource(ResourceVo resourceVo);
	
	/**
	 * 
	 * 功能描述：增加辅助资源与课程的关联
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月16日 上午9:50:33</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ResourceVo addBindCourRescource(ResourceVo resourceVo);
	
	/**
	 * 
	 * 功能描述：获取课程资源关系关联的课程辅助资源(根据ResourceVo或者只根据id查找)
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月17日 下午6:53:39</p>
	 *
	 * @param resourceVo
	 * @param resourseVoId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ResourceVo> getReCourResourceList(ResourceVo resourceVo,int resourseVoId);
	
	/**
	 * 
	 * 功能描述：删除资源
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月17日 下午5:03:34</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ResourceVo deleteResource(ResourceVo resourceVo);
	
	/**
	 * 
	 * 功能描述：删除课程和资源的关联
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月17日 下午5:04:53</p>
	 *
	 * @param resourceVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public ResourceVo deleteBindCourRescource(ResourceVo resourceVo);
	/**
	 * 
	 * 功能描述：获取节的资源数量
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-24 上午9:59:46</p>
	 *
	 * @param sectionId
	 * @param sourceType 1 主视频资源  2 辅助资源
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Integer getSectionResourceCount(int sectionId,String sourceType);
}
