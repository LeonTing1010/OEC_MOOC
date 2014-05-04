/**
 * CourseDaoImpl.java	  V1.0   2013-12-27 ����10:40:01
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.message.MessageVo;
@Repository
public class MessageDaoImpl extends BaseDao<MessageVo> implements MessageDao{

	@Override
	public MessageVo get(Integer id) {
		Map map = new HashMap();
		map.put("id", 21);
		map.put("type", 1);
		return (MessageVo) super.selectOne(generateStatement("getMessageById"),id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageVo> getMessageList(Integer userId) {
		Map map = new HashMap();
		map.put("id", 21);
		map.put("type", 1);
		return (List<MessageVo>) super.findList(generateStatement("getMessageList"),userId);
	}

	@Override
	public MessageVo insert(MessageVo messageVo) {
		super.insert(messageVo);
		return messageVo;
	}

}
