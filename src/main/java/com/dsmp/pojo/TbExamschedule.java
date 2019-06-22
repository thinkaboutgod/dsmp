package com.dsmp.pojo;

import java.util.Date;

public class TbExamschedule {
    private Integer exsId;

    private Integer schId;

    private Integer subId;

    private String exsTime;

    private String exsAddress;

    private Integer exsSignupnum;

    private String exsEndtime;

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

	public String getExsTime() {
		return exsTime;
	}

	public void setExsTime(String exsTime) {
		this.exsTime = exsTime;
	}

	public String getExsEndtime() {
		return exsEndtime;
	}

	public void setExsEndtime(String exsEndtime) {
		this.exsEndtime = exsEndtime;
	}
    
	
}