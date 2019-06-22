package com.dsmp.pojo;

import java.util.Date;

public class TbAppeal {
    private Integer appId;

    private Integer schId;

    private String appContent;

    private Date appTime;
    
    private String appReply;
    
    private TbSchool tbSchool;

    public TbSchool getTbSchool() {
		return tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public String getAppReply() {
		return appReply;
	}

	public void setAppReply(String appReply) {
		this.appReply = appReply;
	}

	public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent == null ? null : appContent.trim();
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }
    
}