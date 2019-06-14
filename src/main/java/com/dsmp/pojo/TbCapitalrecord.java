package com.dsmp.pojo;

import java.util.Date;

public class TbCapitalrecord {
    private Integer capId;

    private Integer schId;

    private Integer stuId;
    
    private String capOrderNumber;
    
    private Double capMoney;

    private Date capTime;

    private String capFeetype;
    
    private TbStudent tbStudent;
    
    private TbSchool tbSchool;
   

	public String getCapOrderNumber() {
		return capOrderNumber;
	}

	public void setCapOrderNumber(String capOrderNumber) {
		this.capOrderNumber = capOrderNumber;
	}

	public Integer getCapId() {
        return capId;
    }
    
    public TbStudent getTbStudent() {
		return tbStudent;
	}

	public void setTbStudent(TbStudent tbStudent) {
		this.tbStudent = tbStudent;
	}

	public TbSchool getTbSchool() {
		return tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
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