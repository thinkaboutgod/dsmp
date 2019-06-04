package com.dsmp.pojo;

public class TbMenu {
    private Integer menId;

    private String menName;

    private Integer menFather;

    private String menUrl;

    private String menImg;

    public Integer getMenId() {
        return menId;
    }

    public void setMenId(Integer menId) {
        this.menId = menId;
    }

    public String getMenName() {
        return menName;
    }

    public void setMenName(String menName) {
        this.menName = menName == null ? null : menName.trim();
    }

    public Integer getMenFather() {
        return menFather;
    }

    public void setMenFather(Integer menFather) {
        this.menFather = menFather;
    }

    public String getMenUrl() {
        return menUrl;
    }

    public void setMenUrl(String menUrl) {
        this.menUrl = menUrl == null ? null : menUrl.trim();
    }

    public String getMenImg() {
        return menImg;
    }

    public void setMenImg(String menImg) {
        this.menImg = menImg == null ? null : menImg.trim();
    }
}