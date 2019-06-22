package com.dsmp.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbSchool {
	
    private Integer schId;

    private Integer rolId;

    private String schName;

    private String schAccount;

    private String schPassword;

    private String schAddress;

    private String schIntroduction;

    private String schBossname;

    private String schRegistercapital;

    private String schCreditcode;

    private String schType;

    private String schSignupstatus;

    private String schHeadimg;

    private String schOperativestatus;

    private Double schCharge;
    
    private String schAudit;

    private TbRole tbRole;
    
    public String getSchRegistercapital() {
		return schRegistercapital;
	}

	public void setSchRegistercapital(String schRegistercapital) {
		this.schRegistercapital = schRegistercapital;
	}

	//驾校拥有的学员集合
    private List<TbStudent> stuList = new ArrayList<>();
    //驾校拥有的教练集合
    private List<TbCoach> coaList = new ArrayList<>();
    //车集合
    private List<TbCar> carList = new ArrayList<>();
    
    
    
    public String getSchAudit() {
		return schAudit;
	}

	public void setSchAudit(String schAudit) {
		this.schAudit = schAudit;
	}

	public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
    }

    public String getSchAccount() {
        return schAccount;
    }

    public void setSchAccount(String schAccount) {
        this.schAccount = schAccount == null ? null : schAccount.trim();
    }

    public String getSchPassword() {
        return schPassword;
    }

    public void setSchPassword(String schPassword) {
        this.schPassword = schPassword == null ? null : schPassword.trim();
    }

    public String getSchAddress() {
        return schAddress;
    }

    public void setSchAddress(String schAddress) {
        this.schAddress = schAddress == null ? null : schAddress.trim();
    }

    public String getSchIntroduction() {
        return schIntroduction;
    }

    public void setSchIntroduction(String schIntroduction) {
        this.schIntroduction = schIntroduction == null ? null : schIntroduction.trim();
    }

    public String getSchBossname() {
        return schBossname;
    }

    public void setSchBossname(String schBossname) {
        this.schBossname = schBossname == null ? null : schBossname.trim();
    }


    public String getSchCreditcode() {
        return schCreditcode;
    }

    public void setSchCreditcode(String schCreditcode) {
        this.schCreditcode = schCreditcode == null ? null : schCreditcode.trim();
    }

    public String getSchType() {
        return schType;
    }

    public void setSchType(String schType) {
        this.schType = schType == null ? null : schType.trim();
    }

    public String getSchSignupstatus() {
        return schSignupstatus;
    }

    public void setSchSignupstatus(String schSignupstatus) {
        this.schSignupstatus = schSignupstatus == null ? null : schSignupstatus.trim();
    }

    public String getSchHeadimg() {
        return schHeadimg;
    }

    public void setSchHeadimg(String schHeadimg) {
        this.schHeadimg = schHeadimg == null ? null : schHeadimg.trim();
    }

    public String getSchOperativestatus() {
        return schOperativestatus;
    }

    public void setSchOperativestatus(String schOperativestatus) {
        this.schOperativestatus = schOperativestatus == null ? null : schOperativestatus.trim();
    }

    public Double getSchCharge() {
        return schCharge;
    }

    public void setSchCharge(Double schCharge) {
        this.schCharge = schCharge;
    }

    
	public TbRole getTbRole() {
		return tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public List<TbStudent> getStuList() {
		return stuList;
	}

	public void setStuList(List<TbStudent> stuList) {
		this.stuList = stuList;
	}

	public List<TbCoach> getCoaList() {
		return coaList;
	}

	public void setCoaList(List<TbCoach> coaList) {
		this.coaList = coaList;
	}

	public List<TbCar> getCarList() {
		return carList;
	}

	public void setCarList(List<TbCar> carList) {
		this.carList = carList;
	}
    
	
    
}