
package com.gta.oec.vo.qacenter;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.user.UserVo;

/**
 * 问答实体类
 * @author 刘祚家
 *
 */
public class QuestionVo extends BaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920865612340125141L;
	/**问题ID**/
	private int quesID;
	/**课程ID**/
	private int courseID;
	/**用户ID**/
	private int userID;
	/**岗位ID**/
	private int jobID;
	/**提问内容**/
	private String quesContent;
	/**回答状态**/
	private int quesStatus;
	/**问题类别**/
	private int quesType;
	/**回答次数**/
	private int quesAnswerCount;
	/**关注人数**/
	private int quesAttentionCount;
	/**问题标签**/
	private String quesLabel;
	/**指定答疑人**/
	private String quesAnswerPerson;
	/**浏览权限**/
	private int quesLookLimit;
	/**回答权限**/
	private int quesAnswerLimit;
	/**价格设定**/
	private float quesPrice;
	/**创建时间**/
	private Date quesCtime;
	/**修改时间**/
	private Date quesUtime;
	/**章节ID**/
	private int secID;
	/**岗位群ID**/
	private int jobGroupID;
	/**问题说明**/
	private String quesDescription;
	/**是否精选**/
	private int quesIsChoiceness;
	
	/**问题关联图片**/
	private String quesPic;
	
	// 该问题是否可见.0是1否.
	private short visibleQuestionOrNot ;
	
	/**以下不是问题对象本身属性--------start-------**/
	/**行业ID**/
	private int proID;
	/**行业名称**/
	private String proName;
	/**创建时间与当前时间段**/
	private String compareTime;
	/**提问人的用户名.**/
	private String userName;
	
	private AnswerVo answerVo;
	
	private List<AnswerVo> answerList;
	
	private SectionVO sectionVO;
	
	
	/**选择推荐老师ID串,保存提问时使用**/
	private List<Integer> teacherIdList;
	
	/**用户是否关注标志**/
	private String attentionSign;
	
	/**以下不是问题对象本身属性--------end---------**/
	
	
	public int getQuesID() {
		return quesID;
	}
	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	public int getQuesStatus() {
		return quesStatus;
	}
	public void setQuesStatus(int quesStatus) {
		this.quesStatus = quesStatus;
	}
	public int getQuesType() {
		return quesType;
	}
	public void setQuesType(int quesType) {
		this.quesType = quesType;
	}
	public int getQuesAnswerCount() {
		return quesAnswerCount;
	}
	public void setQuesAnswerCount(int quesAnswerCount) {
		this.quesAnswerCount = quesAnswerCount;
	}
	public int getQuesAttentionCount() {
		return quesAttentionCount;
	}
	public void setQuesAttentionCount(int quesAttentionCount) {
		this.quesAttentionCount = quesAttentionCount;
	}
	public String getQuesLabel() {
		return quesLabel;
	}
	public void setQuesLabel(String quesLabel) {
		this.quesLabel = quesLabel;
	}
	public String getQuesAnswerPerson() {
		return quesAnswerPerson;
	}
	public void setQuesAnswerPerson(String quesAnswerPerson) {
		this.quesAnswerPerson = quesAnswerPerson;
	}
	
	public int getQuesLookLimit() {
		return quesLookLimit;
	}
	public void setQuesLookLimit(int quesLookLimit) {
		this.quesLookLimit = quesLookLimit;
	}
	public int getQuesAnswerLimit() {
		return quesAnswerLimit;
	}
	public void setQuesAnswerLimit(int quesAnswerLimit) {
		this.quesAnswerLimit = quesAnswerLimit;
	}
	public float getQuesPrice() {
		return quesPrice;
	}
	public void setQuesPrice(float quesPrice) {
		this.quesPrice = quesPrice;
	}
	public Date getQuesCtime() {
		return quesCtime;
	}
	public void setQuesCtime(Date quesCtime) {
		this.quesCtime = quesCtime;
	}
	public Date getQuesUtime() {
		return quesUtime;
	}
	public void setQuesUtime(Date quesUtime) {
		this.quesUtime = quesUtime;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public String getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
	}
	public AnswerVo getAnswerVo() {
		return answerVo;
	}
	public void setAnswerVo(AnswerVo answerVo) {
		this.answerVo = answerVo;
	}
	public int getSecID() {
		return secID;
	}
	public void setSecID(int secID) {
		this.secID = secID;
	}
	public int getJobGroupID() {
		return jobGroupID;
	}
	public void setJobGroupID(int jobGroupID) {
		this.jobGroupID = jobGroupID;
	}
	public String getQuesDescription() {
		return quesDescription;
	}
	public void setQuesDescription(String quesDescription) {
		this.quesDescription = quesDescription;
	}
	public List<AnswerVo> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}
	/**
	 * @return the quesIsChoiceness
	 */
	public int getQuesIsChoiceness() {
		return quesIsChoiceness;
	}
	/**
	 * @param quesIsChoiceness the quesIsChoiceness to set
	 */
	public void setQuesIsChoiceness(int quesIsChoiceness) {
		this.quesIsChoiceness = quesIsChoiceness;
	}
	public List<Integer> getTeacherIdList() {
		return teacherIdList;
	}
	public void setTeacherIdList(List<Integer> teacherIdList) {
		this.teacherIdList = teacherIdList;
	}
	public String getAttentionSign() {
		return attentionSign;
	}
	public void setAttentionSign(String attentionSign) {
		this.attentionSign = attentionSign;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public SectionVO getSectionVO() {
		return sectionVO;
	}
	public void setSectionVO(SectionVO sectionVO) {
		this.sectionVO = sectionVO;
	}
	public String getQuesPic() {
		return quesPic;
	}
	public void setQuesPic(String quesPic) {
		this.quesPic = quesPic;
	}
	public short getVisibleQuestionOrNot() {
		return visibleQuestionOrNot;
	}
	public void setVisibleQuestionOrNot(short visibleQuestionOrNot) {
		this.visibleQuestionOrNot = visibleQuestionOrNot;
	}
	
}
