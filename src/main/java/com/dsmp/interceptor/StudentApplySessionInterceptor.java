package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbStudent;

public class StudentApplySessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TbStudent tbStudent = (TbStudent) request.getSession().getAttribute("student");
		if (null!=tbStudent) {
			return true;
		}else {
			//管理员的session过期
			request.getRequestDispatcher("/WEB-INF/jsp/client/login.jsp").forward(request, response);

			return false;
		}
		
	}
}
