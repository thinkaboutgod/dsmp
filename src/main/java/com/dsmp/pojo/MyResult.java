package com.dsmp.pojo;

import org.springframework.stereotype.Component;

@Component
public class MyResult {
	private int code;
	private int roleId;
	private String myresult;
	private String data;
	
	private int errCount;
	private Double sum;

	public MyResult() {
		super();
	}

	public MyResult(String myresult) {
		super();
		this.myresult = myresult;
	}

	public MyResult(int code,int roleId, String myresult, String data,int errCount) {
		super();
		this.code = code;
		this.myresult = myresult;
		this.data = data;
		this.errCount = errCount;
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getErrCount() {
		return errCount;
	}

	public void setErrCount(int errCount) {
		this.errCount = errCount;
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
