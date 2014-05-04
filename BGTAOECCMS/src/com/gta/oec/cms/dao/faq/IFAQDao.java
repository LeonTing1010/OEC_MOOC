package com.gta.oec.cms.dao.faq;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.faq.FAQ;

public interface IFAQDao extends SqlMapper<FAQ> {
	/**
	 * 
	 * 功能描述：查询faq所有信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<FAQ> findFAQPageQuery(PaginationContext<FAQ> pc);
	
	
	/**
	 * 
	 * 功能描述：修改faq信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateFAQ(@Param(value="faq") FAQ faq);
	
	
	/**
	 * 
	 * 功能描述：删除faq信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void delFAQ(@Param(value="faqID") Integer faqID);
	
	
	
	/**
	 * 
	 * 功能描述：增加faq信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午11:30:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void addFAQ(@Param(value="faq") FAQ faq);
	
	
	/**
	 * 
	 * 功能描述：查询faq的标题
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-4-8 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<FAQ> findTitle();
	
	/**
	 * 
	 * 功能描述：根据ID查询faq的标题
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-4-8 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public FAQ findTitleByID(Integer id);
	
	/**
	 * 
	 * 功能描述：根据ID查询是否有子节点
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-4-8 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<FAQ> findPIDByID(Integer id);
}
