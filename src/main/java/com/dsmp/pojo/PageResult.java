package com.dsmp.pojo;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	private int pageIndex;
	private int totalPage;
	private List<Object> list = new ArrayList<>();
	private String data;
	public PageResult() {
		super();
	}

	public PageResult(int pageIndex, int totalPage, List<Object> list) {
		super();
		this.pageIndex = pageIndex;
		this.totalPage = totalPage;
		this.list = list;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
