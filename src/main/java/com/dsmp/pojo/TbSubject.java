package com.dsmp.pojo;

public class TbSubject {
    private Integer subId;

    private String subName;

    private Double subTime;

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    public Double getSubTime() {
        return subTime;
    }

    public void setSubTime(Double subTime) {
        this.subTime = subTime;
    }
}