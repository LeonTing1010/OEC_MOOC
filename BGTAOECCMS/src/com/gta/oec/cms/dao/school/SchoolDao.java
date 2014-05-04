/**
 * SchoolDao.java	  V1.0   2014-3-21 上午11:29:47
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.dao.school;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.school.School;

public interface SchoolDao extends SqlMapper {

	/**
	 * 
	 * 功能描述：查询所有学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 上午11:30:54
	 *         </p>
	 * 
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<School> allSchoolPageQuery(PageModel<School> pm);

	/**
	 * 
	 * 功能描述：根据学校名称、学校地址，查询学校信息列表
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-25 下午1:25:07
	 *         </p>
	 * 
	 * @param sName
	 * @param sAddress
	 * @param pm
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public List<School> schoolPageQuery(PaginationContext<School> pc);

	/**
	 * 
	 * 功能描述：新增学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 下午2:18:42
	 *         </p>
	 * 
	 * @param school
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void addSchool(@Param(value = "school") School school);

	/**
	 * 
	 * 功能描述：修改学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 下午2:38:13
	 *         </p>
	 * 
	 * @param school
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void updateSchool(@Param(value = "school") School school);

	/**
	 * 
	 * 功能描述：删除学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 下午3:00:25
	 *         </p>
	 * 
	 * @param schoolId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void delSchool(@Param(value = "schoolId") int schoolId);
}
