package com.dsmp.pojo;

import java.util.Date;

public class TbCapitalrecord {
    private Integer capId;

    private Integer schId;

    private Integer stuId;

    private Double capMoney;

    private Date capTime;

    private String capFeetype;

    public Integer getCapId() {
        return capId;
    }

    public void setCapId(Integer capId) {
        this.capId = capId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Double getCapMoney() {
        return capMoney;
    }

    public void setCapMoney(Double capMoney) {
        this.capMoney = capMoney;
    }

    public Date getCapTime() {
        return capTime;
    }

    public void setCapTime(Date capTime) {
        this.capTime = capTime;
    }

    public String getCapFeetype() {
        return capFeetype;
    }

    public void setCapFeetype(String capFeetype) {
        this.capFeetype = capFeetype == null ? null : capFeetype.trim();
    }
}