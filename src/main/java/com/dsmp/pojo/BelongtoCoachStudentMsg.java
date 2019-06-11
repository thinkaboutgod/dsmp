package com.dsmp.pojo;
/*
 * 黄锟龙
 * 教练查看学生科目情况
 */
public class BelongtoCoachStudentMsg {
	private Integer stuId;
	private Integer subId;
	private Integer susScore;
	private String subName;
	private Integer subTime;
	private Integer strTime;
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getSusScore() {
		return susScore;
	}
	public void setSusScore(Integer susScore) {
		this.susScore = susScore;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Integer getSubTime() {
		return subTime;
	}
	public void setSubTime(Integer subTime) {
		this.subTime = subTime;
	}
	public Integer getStrTime() {
		return strTime;
	}
	public void setStrTime(Integer strTime) {
		this.strTime = strTime;
	}
	
}
