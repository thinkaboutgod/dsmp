package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;

/**   
 * @ClassName:  CoachSessionInterceptor   
 * @Description:教练端是否登录拦截   
 * @author: zdc
 * @date:   2019年6月18日 下午2:57:23   
 *   
 */ 
public class CoachSessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TbCoach tbCoach = (TbCoach) request.getSession().getAttribute("coach");
		if (null!=tbCoach) {
			return true;
		}else {
			//教练端的session过期
			request.getRequestDispatcher("/WEB-INF/jsp/client/login.jsp").forward(request, response);

			return false;
		}
		
	}
}