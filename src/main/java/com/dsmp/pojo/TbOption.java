package com.dsmp.pojo;

public class TbOption {
    private Integer optId;

    private Integer topId;

    private String optOption;

    private String optStatus;
    
    private TbTopic tbTopic;

    public TbOption() {
		
	}
    
    public TbOption(Integer optId, String optOption, String optStatus) {
		super();
		this.optId = optId;
		this.optOption = optOption;
		this.optStatus = optStatus;
	}

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
	public TbTopic getTbTopic() {
		return tbTopic;
	}
	public void setTbTopic(TbTopic tbTopic) {
		this.tbTopic = tbTopic;
	}
	@Override
	public String toString() {
		return "TbOption [optId=" + optId + ", topId=" + topId + ", optOption=" + optOption + ", optStatus=" + optStatus
				+ ", tbTopic=" + tbTopic + "]";
	}
    
	
}