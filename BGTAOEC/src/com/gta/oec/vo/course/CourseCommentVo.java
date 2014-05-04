package com.gta.oec.vo.course;

import java.util.Date;

public class CourseCommentVo {
	/**评论ID*/
    private Integer comId;
    /**评论人ID*/
    private Integer comUserId;
    /**被评论课程ID*/
    private Integer comCourseId;
    /**评论时间*/
    private Date comCriTime;
    /**评论来源(PC or App)*/
    private String comSource;
    /**是否删除*/
    private Byte comDel;
    /**评论内容*/
    private String comContent;
    
    /**便于查询，数据库中并不存在*/
    private String userName;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getComUserId() {
        return comUserId;
    }

    public void setComUserId(Integer comUserId) {
        this.comUserId = comUserId;
    }

    public Integer getComCourseId() {
        return comCourseId;
    }

    public void setComCourseId(Integer comCourseId) {
        this.comCourseId = comCourseId;
    }

    public Date getComCriTime() {
        return comCriTime;
    }

    public void setComCriTime(Date comCriTime) {
        this.comCriTime = comCriTime;
    }

    public String getComSource() {
        return comSource;
    }

    public void setComSource(String comSource) {
        this.comSource = comSource == null ? null : comSource.trim();
    }

    public Byte getComDel() {
        return comDel;
    }

    public void setComDel(Byte comDel) {
        this.comDel = comDel;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent == null ? null : comContent.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}