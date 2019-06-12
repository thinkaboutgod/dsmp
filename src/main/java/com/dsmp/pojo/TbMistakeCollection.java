package com.dsmp.pojo;

/**
 * 错题集实体类
 *
 */
public class TbMistakeCollection {
	private Integer micId;//错题id
	private Integer stuId;//学员id
	private Integer topId;//题目id
	public TbMistakeCollection() {
		
	}
	public Integer getMicId() {
		return micId;
	}
	public void setMicId(Integer micId) {
		this.micId = micId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getTopId() {
		return topId;
	}
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	
}
