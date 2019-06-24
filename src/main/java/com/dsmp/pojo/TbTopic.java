package com.dsmp.pojo;

import java.util.List;

public class TbTopic {
    private Integer topId;

    private Integer subId;

    private String topTopic;

    private String topImg;

    private String topAnswer;
    
    private String topAnswerDetail;
    
    private List<TbOption> options;//一道题目里面有多个选项
    public TbTopic() {
		
	}
    
    
    public TbTopic(Integer topId, String topTopic, String topAnswerDetail) {
		super();
		this.topId = topId;
		this.topTopic = topTopic;
		this.topAnswerDetail = topAnswerDetail;
	}


	public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getTopTopic() {
        return topTopic;
    }

    public void setTopTopic(String topTopic) {
        this.topTopic = topTopic == null ? null : topTopic.trim();
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg == null ? null : topImg.trim();
    }

    public String getTopAnswer() {
        return topAnswer;
    }

    public void setTopAnswer(String topAnswer) {
        this.topAnswer = topAnswer == null ? null : topAnswer.trim();
    }

	public List<TbOption> getOptions() {
		return options;
	}

	public void setOptions(List<TbOption> options) {
		this.options = options;
	}

	public String getTopAnswerDetail() {
		return topAnswerDetail;
	}

	public void setTopAnswerDetail(String topAnswerDetail) {
		this.topAnswerDetail = topAnswerDetail;
	}
	@Override
	public String toString() {
		return "TbTopic [topId=" + topId + ", subId=" + subId + ", topTopic=" + topTopic + ", topImg=" + topImg
				+ ", topAnswer=" + topAnswer + ", topAnswerDetail=" + topAnswerDetail + ", options=" + options + "]";
	}
    
	
}