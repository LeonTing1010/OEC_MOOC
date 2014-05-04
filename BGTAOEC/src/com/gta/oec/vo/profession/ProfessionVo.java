package com.gta.oec.vo.profession;

import java.util.Date;
import java.util.List;

import com.gta.oec.vo.BaseVO;
import com.gta.oec.vo.job.JobVo;
import com.gta.oec.vo.job.TeacherShineItemVo;
import com.gta.oec.vo.qacenter.QuestionVo;

public class ProfessionVo extends BaseVO{
	/** serialVersionUID */
	private static final long serialVersionUID = 1225508529804513194L;
	/**职业ID**/
	private int proID;
	/**职业名称**/
	private String proName;
	/**职业描述**/
	private String proDescription;
	/**热门职业推荐**/
	private int proRecommend;
	/**创建时间**/
	private Date proCtime;
	/**修改时间**/
	private Date proUtime;
	
	
	/**----------- 非对象属性------- **/
	private List<JobVo> jobGroupList;
	/**行业下所有最新提问**/
	private List<QuestionVo> newQuestionList;
	/**行业下所有最热门提问**/
	private List<QuestionVo> hotQuestionList;
	
	private List<TeacherShineItemVo> teacherShineItemVos;
	public int getProRecommend() {
		return proRecommend;
	}
	public void setProRecommend(int proRecommend) {
		this.proRecommend = proRecommend;
	}
	public String getProName() {
		return proName;
	}
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDescription() {
		return proDescription;
	}
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	public List<JobVo> getJobGroupList() {
		return jobGroupList;
	}
	public void setJobGroupList(List<JobVo> jobGroupList) {
		this.jobGroupList = jobGroupList;
	}
	public Date getProCtime() {
		return proCtime;
	}
	public void setProCtime(Date proCtime) {
		this.proCtime = proCtime;
	}
	public Date getProUtime() {
		return proUtime;
	}
	public void setProUtime(Date proUtime) {
		this.proUtime = proUtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<QuestionVo> getNewQuestionList() {
		return newQuestionList;
	}
	public void setNewQuestionList(List<QuestionVo> newQuestionList) {
		this.newQuestionList = newQuestionList;
	}
	public List<QuestionVo> getHotQuestionList() {
		return hotQuestionList;
	}
	public void setHotQuestionList(List<QuestionVo> hotQuestionList) {
		this.hotQuestionList = hotQuestionList;
	}
	public List<TeacherShineItemVo> getTeacherShineItemVos() {
		return teacherShineItemVos;
	}
	public void setTeacherShineItemVos(List<TeacherShineItemVo> teacherShineItemVos) {
		this.teacherShineItemVos = teacherShineItemVos;
	}
	
}
