package com.dsmp.pojo;

/**   
 * @ClassName:  Count   
 * @Description:统计使用实体类  
 * @author: 张德承
 * @date:   2019年6月12日 上午12:16:47   
 *   
 */ 
public class Count {
	private Integer parameterId;
	private String name;
	private String data;
	private String phone;
	private double starAvg;
	
	
	
	public Integer getParameterId() {
		return parameterId;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public double getStarAvg() {
		return starAvg;
	}

	public void setStarAvg(double startAvg) {
		this.starAvg = startAvg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
