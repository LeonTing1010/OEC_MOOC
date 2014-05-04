package com.gta.oec.cms.service.message;

import java.util.List;


import java.util.Map;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.vo.message.Message;

public interface MessageService {
	
	
	/**
	 * 批量插入消息
	 * 
	 * @param map里面 userid, 消息模板ID
	 * @throws Exception
	 */
	public void saveMessage(Message message)throws Exception;
	
	/**
	 * 分页查询发送消息列表
	 * 
	 */
	public List<Message> findMessagePageQuery(PaginationContext<Message> pc)throws Exception;
	
	/**
	 * 发送消息
	 * @author can.xie
	 * 
	 */
	public String sendMessage(Map<String, Object> in,Message message)throws Exception; 

}
