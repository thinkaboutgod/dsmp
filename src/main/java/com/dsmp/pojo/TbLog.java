package com.dsmp.pojo;

import java.util.Date;

public class TbLog {
    private Integer logId;

    private Date logTime;

    private String logEvent;

    private String logOperatoraccount;

    private String logRole;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(String logEvent) {
        this.logEvent = logEvent == null ? null : logEvent.trim();
    }

    public String getLogOperatoraccount() {
        return logOperatoraccount;
    }

    public void setLogOperatoraccount(String logOperatoraccount) {
        this.logOperatoraccount = logOperatoraccount == null ? null : logOperatoraccount.trim();
    }

    public String getLogRole() {
        return logRole;
    }

    public void setLogRole(String logRole) {
        this.logRole = logRole == null ? null : logRole.trim();
    }
}