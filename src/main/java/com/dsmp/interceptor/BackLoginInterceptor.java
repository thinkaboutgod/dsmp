package com.dsmp.interceptor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbManager;
import com.dsmp.pojo.TbSchool;


public class BackLoginInterceptor implements HandlerInterceptor{
//平台管理端，运管，驾校，教练，用同一个后台，拦截两个登录界面，有登陆过，直接跳转
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		TbManager tbManager = (TbManager)request.getSession().getAttribute("manager");
		TbSchool tbSchool = (TbSchool)request.getSession().getAttribute("school");
		TbCoach tbCoach = (TbCoach)request.getSession().getAttribute("coach");
		if(tbManager != null || tbSchool != null ||tbCoach != null ) {		
			request.getRequestDispatcher("/WEB-INF/jsp/back/bimg.jsp").forward(request, response);			
			return false;
			
		}else {
			return true;
			
		}
	}
}
