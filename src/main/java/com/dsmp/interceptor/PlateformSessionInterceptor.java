package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbManager;


/**   
 * @ClassName:  PlateformSessionInterceptor   
 * @Description:平台端是否登录拦截   
 * @author: 张德承
 * @date:   2019年6月18日 下午2:58:22   
 *   
 */ 
public class PlateformSessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TbManager tbManager = (TbManager) request.getSession().getAttribute("manager");
		if (null!=tbManager) {
			return true;
		}else {
			//管理员的session过期
			request.getRequestDispatcher("/WEB-INF/jsp/client/adminlogin.jsp").forward(request, response);

			return false;
		}
		
	}
}
