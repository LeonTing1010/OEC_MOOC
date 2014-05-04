/**
 * CourseDetail.java	  V1.0   2014-3-19 下午3:02:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.coursedetail;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.course.CourseDetail;

public interface CourseDetailDao extends SqlMapper<CourseDetail> {

	/**
	 * 
	 * 功能描述：根据课程id获取章、节信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-19 下午3:03:58
	 *         </p>
	 * 
	 * @param courseId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<CourseDetail> getSectionByCid(@Param(value = "courseDetail") CourseDetail courDetail);

	/**
	 * 
	 * 功能描述：设置直播通道
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-4 上午9:49:44
	 *         </p>
	 * 
	 * @param courseId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void setChannelURL(@Param(value = "courseDetail") CourseDetail courseDetail);

	/**
	 * 
	 * 功能描述：根据章节id获得章节信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-8 下午3:14:58
	 *         </p>
	 * 
	 * @param secId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public CourseDetail getSectionInfoById(@Param(value = "secId") int secId);

	/**
	 * 
	 * 功能描述：更新课程章节信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-4-22 上午8:53:47
	 *         </p>
	 * 
	 * @param courseDetail
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void updateSection(CourseDetail courseDetail);

	/**
	 * 功能描述：根据章节id删除章节.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月23日 下午5:32:17
	 *         </p>
	 * 
	 * @param secId
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public int deleteSectionInfoById(@Param(value = "secId") int secId) throws DAOException;

	/**
	 * 功能描述：保存一个章节信息.
	 * 
	 * @author dongs
	 *         <p>
	 *         创建日期 ：2014年4月24日 下午3:34:11
	 *         </p>
	 * 
	 * @param courseDetail
	 * @return
	 * @throws DAOException
	 * 
	 *             <p>
	 *             修改历史 ：(修改人，修改时间，修改原因/内容)
	 *             </p>
	 */
	public int saveSectionInfo(@Param(value = "courseDetail") CourseDetail courseDetail)
			throws DAOException;

}
