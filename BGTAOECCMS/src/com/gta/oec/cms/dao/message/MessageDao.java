package com.gta.oec.cms.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.message.Message;

/**
 * 发送消息持久类
 * @author can.xie
 *
 */

public interface MessageDao extends SqlMapper<Message>{
	
	/**
	 * 批量插入消息
	 * 
	 * @param map里面 userid, 消息模板ID
	 * @throws Exception
	 */
	public void saveMessage(@Param(value="message") Message message)throws Exception;
	
	/**
	 * 分页查询发送消息列表
	 * 
	 */
	public List<Message> findMessagePageQuery(PaginationContext<Message> pc)throws Exception;
	
	

}
