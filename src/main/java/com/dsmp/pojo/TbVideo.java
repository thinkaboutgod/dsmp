package com.dsmp.pojo;

public class TbVideo {
    private Integer vidId;

    private Integer subId;

    private String vidPath;

    private String vidImgpath;

    private String vidTitle;

    public Integer getVidId() {
        return vidId;
    }

    public void setVidId(Integer vidId) {
        this.vidId = vidId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getVidPath() {
        return vidPath;
    }

    public void setVidPath(String vidPath) {
        this.vidPath = vidPath == null ? null : vidPath.trim();
    }

    public String getVidImgpath() {
        return vidImgpath;
    }

    public void setVidImgpath(String vidImgpath) {
        this.vidImgpath = vidImgpath == null ? null : vidImgpath.trim();
    }

    public String getVidTitle() {
        return vidTitle;
    }

    public void setVidTitle(String vidTitle) {
        this.vidTitle = vidTitle == null ? null : vidTitle.trim();
    }
}