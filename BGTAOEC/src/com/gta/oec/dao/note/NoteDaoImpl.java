/**
 * NoteDaoImpl.java	  V1.0   2014年1月13日 下午6:19:10
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.note;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.course.NoteVo;
@Repository
public class NoteDaoImpl extends BaseDao<NoteVo> implements NoteDao{

	@Override
	public List<NoteVo> getNoteList(NoteVo noteVo) {
		
		return super.findList(generateStatement("getNoteList"), noteVo);
	}

	@Override
	public NoteVo addNotes(NoteVo noteVo) {
		
		 super.insert(generateStatement("addNote"), noteVo);
		 return noteVo;
	}

	@Override
	public int getUserNoteCount(NoteVo noteVo) {
		return (Integer) super.selectOne(generateStatement("getUserNoteCount"), noteVo);
	}

	
}
