package com.dsmp.pojo;

import java.util.Date;

public class TbRating {
    private Integer ratId;

    private Integer coaId;

    private Integer schId;

    private Integer stuId;

    private String ratContent;

    private Date ratTime;

    private Integer ratStar;

    private String ratType;

    public Integer getRatId() {
        return ratId;
    }

    public void setRatId(Integer ratId) {
        this.ratId = ratId;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
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

    public String getRatContent() {
        return ratContent;
    }

    public void setRatContent(String ratContent) {
        this.ratContent = ratContent == null ? null : ratContent.trim();
    }

    public Date getRatTime() {
        return ratTime;
    }

    public void setRatTime(Date ratTime) {
        this.ratTime = ratTime;
    }

    public Integer getRatStar() {
        return ratStar;
    }

    public void setRatStar(Integer ratStar) {
        this.ratStar = ratStar;
    }

    public String getRatType() {
        return ratType;
    }

    public void setRatType(String ratType) {
        this.ratType = ratType == null ? null : ratType.trim();
    }
}