package com.dsmp.pojo;

public class TbParameter {
    private Integer parId;

    private String parName;

    private String parType;

    private String parValue;

    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName == null ? null : parName.trim();
    }

    public String getParType() {
        return parType;
    }

    public void setParType(String parType) {
        this.parType = parType == null ? null : parType.trim();
    }

    public String getParValue() {
        return parValue;
    }

    public void setParValue(String parValue) {
        this.parValue = parValue == null ? null : parValue.trim();
    }
}