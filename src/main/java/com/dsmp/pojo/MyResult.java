package com.dsmp.pojo;

import org.springframework.stereotype.Component;

@Component
public class MyResult {
	private int code;
	private String myresult;
	private String data;
	private Double sum;

	public MyResult() {
		super();
	}

	public MyResult(String myresult) {
		super();
		this.myresult = myresult;
	}

	public MyResult(int code, String myresult, String data) {
		super();
		this.code = code;
		this.myresult = myresult;
		this.data = data;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getMyresult() {
		return myresult;
	}

	public void setMyresult(String myresult) {
		this.myresult = myresult;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
