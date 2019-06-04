package com.dsmp.pojo;

public class TbHotlink {
    private Integer holId;

    private String holPath;

    private String holImgpath;

    private String holTitle;

    public Integer getHolId() {
        return holId;
    }

    public void setHolId(Integer holId) {
        this.holId = holId;
    }

    public String getHolPath() {
        return holPath;
    }

    public void setHolPath(String holPath) {
        this.holPath = holPath == null ? null : holPath.trim();
    }

    public String getHolImgpath() {
        return holImgpath;
    }

    public void setHolImgpath(String holImgpath) {
        this.holImgpath = holImgpath == null ? null : holImgpath.trim();
    }

    public String getHolTitle() {
        return holTitle;
    }

    public void setHolTitle(String holTitle) {
        this.holTitle = holTitle == null ? null : holTitle.trim();
    }
}