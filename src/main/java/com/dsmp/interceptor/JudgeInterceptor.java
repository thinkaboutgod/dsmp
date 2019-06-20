package com.dsmp.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudent;
import com.dsmp.utils.GsonUtils;

class JudgeInterceptor implements HandlerInterceptor{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException 
	{
		MyResult result = new MyResult();
		TbStudent tbStudent = (TbStudent)request.getSession().getAttribute("student");
		if(tbStudent != null) {
			return true;
		}else {
			result.setMyresult("codeFaild");
			response.getWriter().println(GsonUtils.toJson(result));
			return false;
		}
	}
}
