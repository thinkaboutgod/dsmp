package com.dsmp.pojo;

/**   
 * @ClassName:  SearchBean   
 * @Description:搜索用实体类   
 * @author: 张德承
 * @date:   2019年6月5日 下午9:32:01   
 *   
 */ 
public class SearchBean {
	private String account;
	private String name;
	private String fwhere;
	private String beginTime;
	private String endTime;
	
	private String carPlateNum;
	private String coachName;
	private String schId;
	
	private String belongSubject;

	public SearchBean() {
		super();
	}

	public SearchBean(String account, String name, String fwhere, String beginTime, String endTime) {
		super();
		this.account = account;
		this.name = name;
		this.fwhere = fwhere;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}	
	public SearchBean(String account, String name, String fwhere, String beginTime, String endTime,
			String belongSubject) {
		super();
		this.account = account;
		this.name = name;
		this.fwhere = fwhere;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.belongSubject = belongSubject;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFwhere() {
		return fwhere;
	}

	public void setFwhere(String fwhere) {
		this.fwhere = fwhere;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getCarPlateNum() {
		return carPlateNum;
	}

	public void setCarPlateNum(String carPlateNum) {
		this.carPlateNum = carPlateNum;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getSchId() {
		return schId;
	}

	public void setSchId(String schId) {
		this.schId = schId;
	}
	

	public String getBelongSubject() {
		return belongSubject;
	}

	public void setBelongSubject(String belongSubject) {
		this.belongSubject = belongSubject;
	}

	
}
