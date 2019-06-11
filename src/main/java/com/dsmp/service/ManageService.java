package com.dsmp.service;

import javax.servlet.http.HttpSession;

import com.dsmp.pojo.MyResult;

public interface ManageService {
	//验证后台管理员登录
	public MyResult adminLogin(HttpSession session,String account,String password);
}
