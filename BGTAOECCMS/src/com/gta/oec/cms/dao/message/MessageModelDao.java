package com.gta.oec.cms.dao.message;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.message.MessageModel;

public interface MessageModelDao extends SqlMapper<MessageModel>{
	
	
	/**
	 * 插入消息模板
	 * @author can.xie
	 * 
	 */
	public void inserMessageModel(@Param(value="messageModel") MessageModel messageModel) throws Exception;

}
