package com.dsmp.pojo;

public class TbTopic {
    private Integer topId;

    private Integer subId;

    private String topTopic;

    private String topImg;

    private String topAnswer;

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
}