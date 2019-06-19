package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.TbLog;

public interface LogService {
	public List<TbLog> searchLog(HttpServletRequest request);
}
