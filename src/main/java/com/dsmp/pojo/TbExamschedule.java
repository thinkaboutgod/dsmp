package com.dsmp.pojo;

import java.util.Date;

public class TbExamschedule {
    private Integer exsId;

    private Integer schId;

    private Integer subId;

    private Date exsTime;

    private String exsAddress;

    private Integer exsSignupnum;

    private Date exsEndtime;

    private Integer exsTotalnum;
    
    private TbSubject tbSubject;
    

    public Integer getExsId() {
        return exsId;
    }

    public void setExsId(Integer exsId) {
        this.exsId = exsId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Date getExsTime() {
        return exsTime;
    }

    public void setExsTime(Date exsTime) {
        this.exsTime = exsTime;
    }

    public String getExsAddress() {
        return exsAddress;
    }

    public void setExsAddress(String exsAddress) {
        this.exsAddress = exsAddress == null ? null : exsAddress.trim();
    }

    public Integer getExsSignupnum() {
        return exsSignupnum;
    }

    public void setExsSignupnum(Integer exsSignupnum) {
        this.exsSignupnum = exsSignupnum;
    }

    public Date getExsEndtime() {
        return exsEndtime;
    }

    public void setExsEndtime(Date exsEndtime) {
        this.exsEndtime = exsEndtime;
    }

    public Integer getExsTotalnum() {
        return exsTotalnum;
    }

    public void setExsTotalnum(Integer exsTotalnum) {
        this.exsTotalnum = exsTotalnum;
    }

	public TbSubject getTbSubject() {
		return tbSubject;
	}

	public void setTbSubject(TbSubject tbSubject) {
		this.tbSubject = tbSubject;
	}
    
}