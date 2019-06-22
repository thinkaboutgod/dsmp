package com.dsmp.pojo;

import java.util.Date;

public class TbNotice {
	private Integer notId;

	private String notTitle;

	private String notContent;

	private Date notTime;

	private String notPath;

	private Integer ntyId;

	private TbNoticeType tbNoticeType;

	public String getNotPath() {
		return notPath;
	}

	public void setNotPath(String notPath) {
		this.notPath = notPath;
	}

	public Integer getNotId() {
		return notId;
	}

	public void setNotId(Integer notId) {
		this.notId = notId;
	}

	public String getNotTitle() {
		return notTitle;
	}

	public void setNotTitle(String notTitle) {
		this.notTitle = notTitle == null ? null : notTitle.trim();
	}

	public String getNotContent() {
		return notContent;
	}

	public void setNotContent(String notContent) {
		this.notContent = notContent == null ? null : notContent.trim();
	}

	public Date getNotTime() {
		return notTime;
	}

	public void setNotTime(Date notTime) {
		this.notTime = notTime;
	}

	public Integer getNtyId() {
		return ntyId;
	}

	public void setNtyId(Integer ntyId) {
		this.ntyId = ntyId;
	}

	public TbNoticeType getTbNoticeType() {
		return tbNoticeType;
	}

	public void setTbNoticeType(TbNoticeType tbNoticeType) {
		this.tbNoticeType = tbNoticeType;
	}

}