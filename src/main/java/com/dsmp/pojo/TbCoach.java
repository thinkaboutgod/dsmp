package com.dsmp.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbCoach {
    private Integer coaId;

    private Integer rolId;

    private Integer schId;

    private String coaName;

    private String coaAccount;

    private String coaPassword;

    private String coaSex;

    private String coaBirthday;

    private String coaIdcard;

    private String coaHeadimg;

    private String coaAddress;

    private String coaIntroduction;

    private String coaStatus;

    private String coaLevel;

    //
    private TbSchool tbSchool;
    
    private List<TbStudent> stuList = new ArrayList<>();
   
    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName == null ? null : coaName.trim();
    }

    public String getCoaAccount() {
        return coaAccount;
    }

    public void setCoaAccount(String coaAccount) {
        this.coaAccount = coaAccount == null ? null : coaAccount.trim();
    }

    public String getCoaPassword() {
        return coaPassword;
    }

    public void setCoaPassword(String coaPassword) {
        this.coaPassword = coaPassword == null ? null : coaPassword.trim();
    }

    public String getCoaSex() {
        return coaSex;
    }

    public void setCoaSex(String coaSex) {
        this.coaSex = coaSex == null ? null : coaSex.trim();
    }

    public String getCoaBirthday() {
        return coaBirthday;
    }

    public void setCoaBirthday(String coaBirthday) {
        this.coaBirthday = coaBirthday == null ? null : coaBirthday.trim();
    }

    public String getCoaIdcard() {
        return coaIdcard;
    }

    public void setCoaIdcard(String coaIdcard) {
        this.coaIdcard = coaIdcard == null ? null : coaIdcard.trim();
    }

    public String getCoaHeadimg() {
        return coaHeadimg;
    }

    public void setCoaHeadimg(String coaHeadimg) {
        this.coaHeadimg = coaHeadimg == null ? null : coaHeadimg.trim();
    }

    public String getCoaAddress() {
        return coaAddress;
    }

    public void setCoaAddress(String coaAddress) {
        this.coaAddress = coaAddress == null ? null : coaAddress.trim();
    }

    public String getCoaIntroduction() {
        return coaIntroduction;
    }

    public void setCoaIntroduction(String coaIntroduction) {
        this.coaIntroduction = coaIntroduction == null ? null : coaIntroduction.trim();
    }

    public String getCoaStatus() {
        return coaStatus;
    }

    public void setCoaStatus(String coaStatus) {
        this.coaStatus = coaStatus == null ? null : coaStatus.trim();
    }

    public String getCoaLevel() {
        return coaLevel;
    }

    public void setCoaLevel(String coaLevel) {
        this.coaLevel = coaLevel == null ? null : coaLevel.trim();
    }
}