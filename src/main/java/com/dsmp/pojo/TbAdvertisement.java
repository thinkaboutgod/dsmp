package com.dsmp.pojo;

public class TbAdvertisement {
	
    private Integer advId;

    private Integer schId;

    private String advPath;

    private String advImgpath;

    private String advDescribe;
    
    private TbSchool tbSchool;
    
    public TbSchool getTbSchool() {
		return tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public String getAdvPath() {
        return advPath;
    }

    public void setAdvPath(String advPath) {
        this.advPath = advPath == null ? null : advPath.trim();
    }

    public String getAdvImgpath() {
        return advImgpath;
    }

    public void setAdvImgpath(String advImgpath) {
        this.advImgpath = advImgpath == null ? null : advImgpath.trim();
    }

    public String getAdvDescribe() {
        return advDescribe;
    }

    public void setAdvDescribe(String advDescribe) {
        this.advDescribe = advDescribe == null ? null : advDescribe.trim();
    }
}