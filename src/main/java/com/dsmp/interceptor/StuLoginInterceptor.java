package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbStudent;

/**
 * 学员点击个人中心前进入这个过滤器
 *
 */
public class StuLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TbStudent student =  (TbStudent) request.getSession().getAttribute("student");//取到session里面存的登录后的session
		if(null!=student) {
			if(student.getStuId()!=null&&student.getCoaId()!=null&&student.getSchId()!=null&&student.getSubId()!=null) {
				
				return true;//放行
			}else {//进入个人中心需是已报名
				request.getRequestDispatcher("/WEB-INF/jsp/client/apply.jsp").forward(request, response);//使用转发可保持原来request作用域，一旦用重定向，以前的request中存放的变量全部失效，并进入一个新的request作用域。
				return false;//不放行
			}
		}else {//未登录
			
			request.getRequestDispatcher("/WEB-INF/jsp/client/login.jsp").forward(request, response);//使用转发可保持原来request作用域，一旦用重定向，以前的request中存放的变量全部失效，并进入一个新的request作用域。 
//			response.getWriter().print("unLogin");
			
			return false;//不放行
		}
		
	}
	
}
