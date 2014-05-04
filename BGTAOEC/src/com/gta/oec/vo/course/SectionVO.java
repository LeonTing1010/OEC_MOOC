/**
 * SectionVO.java	  V1.0   2014-1-6 下午5:59:33
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.vo.course;

import java.util.Date;
import java.util.List;

import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.learn.LearnVo;

/**
 * 
 * 功能描述：课程章节
 * 
 * @author bingzhong.qin
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class SectionVO extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5084918863713597944L;
	/**主键ID**/
	private int id;
	/**课程ID**/
	private int courseId;
	/**章节名称**/
	private String name;
	/**章节标题**/
	private String title;
	/**章节介绍**/
	private String introduction;
	/**章节开始时间**/
	private Date startTime;
	/**章节时长：分钟**/
	private int time;
	/**章节类型：直播or点播**/
	private String type;
	/**资源URL**/
	private String secUrl;
	/**章节编号**/
	private int num;
	/**父章节ID**/
	private int pid;
	/**章节顺序**/
	private int sequence;
	/**章节价格**/
	private float price;
	/**是否有笔记**/
	private int  isNoste;
	/**修改时间**/
	private Date cTime;
	/**创建时间**/
	private Date uTime;
	/**核心知识点**/
	private String coreKnowledge;
	/**节列表：只有章下面才有节列表**/
    private List<SectionVO> list;
    /***节笔记信息***/
    private List<NoteVo>  noteList;
    /**(考试、练习、作业)信息**/
    private List<ExamVo>  examList;
    /***节作业信息***/
    private TaskVo taskVo;
    /***节学习明细**/
    private LearnVo learn;
    /****节辅助资源数****/
    private int resourceCount;
    /****节笔记数****/
    private int noteCount;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSecUrl() {
		return secUrl;
	}
	public void setSecUrl(String secUrl) {
		this.secUrl = secUrl;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public LearnVo getLearn() {
		return learn;
	}
	public void setLearn(LearnVo learnVo) {
		this.learn = learnVo;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	public List<SectionVO> getList() {
		return list;
	}
	public void setList(List<SectionVO> list) {
		this.list = list;
	}
	public int getIsNoste() {
		return isNoste;
	}
	public void setIsNoste(int isNoste) {
		this.isNoste = isNoste;
	}
	public List<NoteVo> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<NoteVo> noteList) {
		this.noteList = noteList;
	}
	public String getCoreKnowledge() {
		return coreKnowledge;
	}
	public void setCoreKnowledge(String coreKnowledge) {
		this.coreKnowledge = coreKnowledge;
	}
	public TaskVo getTaskVo() {
		return taskVo;
	}
	public void setTaskVo(TaskVo taskVo) {
		this.taskVo = taskVo;
	}
	public List<ExamVo> getExamList() {
		return examList;
	}
	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}
	public int getResourceCount() {
		return resourceCount;
	}
	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
    
}
