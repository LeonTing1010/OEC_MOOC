/**
 * SchoolService.java	  V1.0   2014-3-21 上午11:22:53
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.service.school;

import java.util.List;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.vo.school.School;

public interface SchoolService {

	/**
	 * 
	 * 功能描述：查询所有学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 上午11:24:03
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
	 *         创建日期 ：2014-3-25 下午1:23:19
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
	 *         创建日期 ：2014-3-21 下午2:17:08
	 *         </p>
	 * 
	 * @param school
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void addSchool(School school);

	/**
	 * 
	 * 功能描述：修改学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 下午2:36:39
	 *         </p>
	 * 
	 * @param school
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void updateSchool(School school);

	/**
	 * 
	 * 功能描述：删除学校信息
	 * 
	 * @author yangyang.ou
	 *         <p>
	 *         创建日期 ：2014-3-21 下午2:59:06
	 *         </p>
	 * 
	 * @param schoolId
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void delSchool(int schoolId);
}
