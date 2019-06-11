package com.dsmp.pojo;

import java.util.Date;

public class TbStudyrecord {
    private Integer strId;

    private Integer stuId;

    private Integer subId;

    private Double strTime;

    private Date strBegintime;

    private Date strEndtime;

    private String strState;
    
    private TbSubject tbSubject;
    
    private TbSubjectscore tbsubjectScore;

    public Integer getStrId() {
        return strId;
    }

    public void setStrId(Integer strId) {
        this.strId = strId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Double getStrTime() {
        return strTime;
    }

    public void setStrTime(Double strTime) {
        this.strTime = strTime;
    }

    public Date getStrBegintime() {
        return strBegintime;
    }

    public void setStrBegintime(Date strBegintime) {
        this.strBegintime = strBegintime;
    }

    public Date getStrEndtime() {
        return strEndtime;
    }

    public void setStrEndtime(Date strEndtime) {
        this.strEndtime = strEndtime;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState == null ? null : strState.trim();
    }

    

	public TbSubject getTbSubject() {
		return tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}

	public TbSubjectscore getTbsubjectScore() {
		return tbsubjectScore;
	}

	public void setTbsubjectScore(TbSubjectscore tbsubjectScore) {
		this.tbsubjectScore = tbsubjectScore;
	}
    
}