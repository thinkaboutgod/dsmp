package com.dsmp.pojo;

public class TbOption {
    private Integer optId;

    private Integer topId;

    private String optOption;

    private String optStatus;

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public String getOptOption() {
        return optOption;
    }

    public void setOptOption(String optOption) {
        this.optOption = optOption == null ? null : optOption.trim();
    }

    public String getOptStatus() {
        return optStatus;
    }

    public void setOptStatus(String optStatus) {
        this.optStatus = optStatus == null ? null : optStatus.trim();
    }
}