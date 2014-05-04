/**
 * CourseDao.java	  V1.0   2013-12-27 ����10:38:05
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.message;

import java.util.List;

import com.gta.oec.vo.message.MessageVo;

/**
 * 
 * 功能描述：课程DAO层
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface MessageDao {
	/**
	 * 
	 * 功能描述：插入课程信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-6 下午4:28:38</p>
	 *
	 * @param courseVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
   public MessageVo insert(MessageVo messageVo);
   public MessageVo get(Integer id);
   public List<MessageVo> getMessageList(Integer userId);
}
