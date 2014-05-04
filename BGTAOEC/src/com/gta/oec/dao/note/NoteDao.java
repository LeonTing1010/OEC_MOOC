/**
 * NoteDao.java	  V1.0   2014年1月13日 下午6:15:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.note;

import java.util.List;

import com.gta.oec.vo.course.NoteVo;

/**
 * 
 * 功能描述：笔记DAO层
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface NoteDao {

	/**
	 * 
	 * 功能描述：获取笔记列表
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月13日 下午6:18:08</p>
	 *
	 * @param noteVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<NoteVo> getNoteList(NoteVo noteVo);
     /**
      * 
      * 功能描述：获取用户笔记数
      *
      * @author  bingzhong.qin
      * <p>创建日期 ：2014-2-20 下午1:12:15</p>
      *
      * @param noteVo
      * @return
      *
      * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
      */
	public int getUserNoteCount(NoteVo noteVo);
	
	/**
	 * 
	 * 功能描述：增加笔记
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年1月14日 下午4:05:29</p>
	 *
	 * @param noteVo
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public NoteVo addNotes(NoteVo noteVo);
}
