package com.gta.oec.cms.dao.comment;

import java.util.List;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.comment.Comment;

public interface ICommentDao extends SqlMapper<Comment> {
	/**
	 * 
	 * 功能描述：查询所有点评信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Comment> findCommentPageQuery(PaginationContext<Comment> pc);
	
	
	/**
	 * 
	 * 功能描述：查询所有点评来源
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<Comment> findSource();
	
	
	/**
	 * 
	 * 功能描述：删除点评信息
	 *
	 * @author  mingkai.cao
	 * <p>创建日期 ：2014-3-29 上午9:00:14</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void updateCommentByID(Integer cID);
}
