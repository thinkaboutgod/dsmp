package com.dsmp.pojo;

public class TbCar {
	
    private Integer carId;

    private Integer coaId;

    private Integer schId;

    private String carPlatenum;

    private String carStyle;

    private String carImg;

    private String carStatus;

    private String carColor;
    
    private String carStartTime;
    
    private String carUsedTime;
    
    private TbCoach tbCoach;
    
    private TbSchool tbSchool;
   
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public String getCarPlatenum() {
        return carPlatenum;
    }

    public void setCarPlatenum(String carPlatenum) {
        this.carPlatenum = carPlatenum == null ? null : carPlatenum.trim();
    }

    public String getCarStyle() {
        return carStyle;
    }

    public void setCarStyle(String carStyle) {
        this.carStyle = carStyle == null ? null : carStyle.trim();
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg == null ? null : carImg.trim();
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus == null ? null : carStatus.trim();
    }

    public String getCarColor() {
        return carColor;
    }
    
    public String getCarStartTime() {
		return carStartTime;
	}

	public void setCarStartTime(String carStartTime) {
		this.carStartTime = carStartTime;
	}

	public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

	public TbCoach getTbCoach() {
		return tbCoach;
	}

	public void setTbCoach(TbCoach tbCoach) {
		this.tbCoach = tbCoach;
	}

	public TbSchool getTbSchool() {
		return tbSchool;
	}

	public void setTbSchool(TbSchool tbSchool) {
		this.tbSchool = tbSchool;
	}

	public String getCarUsedTime() {
		return carUsedTime;
	}

	public void setCarUsedTime(String carUsedTime) {
		this.carUsedTime = carUsedTime;
	}

	

    
}