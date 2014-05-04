/**
 * NoteVo.java	  V1.0   2014年1月13日 下午5:22:00
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.course;

import java.util.Date;

import com.gta.oec.dao.BaseDao;
import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述:  笔记
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class NoteVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4983938797043662992L;
	/**笔记ID**/
	private int noteId;
	/**章节编号**/
	private int secId;
	/**课程ID**/
	private int courseId;
	/**笔记名称**/
	private String noteName;
	/**提交时间**/
	private Date noteSubTime;
	/**笔记内容**/
	private String noteContent;
	/**用户id**/
	private int userId;
	/**视频时间**/
	private String videoTime;
	/**创建时间**/
	private Date noteCtime;
	/**修改时间**/
	private Date noteUtime;
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public int getSecId() {
		return secId;
	}
	public void setSecId(int secId) {
		this.secId = secId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public Date getNoteSubTime() {
		return noteSubTime;
	}
	public void setNoteSubTime(Date noteSubTime) {
		this.noteSubTime = noteSubTime;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	public Date getNoteCtime() {
		return noteCtime;
	}
	public void setNoteCtime(Date noteCtime) {
		this.noteCtime = noteCtime;
	}
	public Date getNoteUtime() {
		return noteUtime;
	}
	public void setNoteUtime(Date noteUtime) {
		this.noteUtime = noteUtime;
	}
	
}
