package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbManager;
import com.dsmp.pojo.TbSchool;

/**   
 * @ClassName:  MenuInterceptor   
 * @Description:获取菜单判断是否登录
 * @author: zdc
 * @date:   2019年6月18日 下午2:57:56   
 *   
 */ 
public class MenuInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String rolId = request.getParameter("role_id");
		if (rolId.equals("1")||rolId.equals("2")) {
			TbManager tbManager = (TbManager) request.getSession().getAttribute("manager");
			if (null!=tbManager) {
				return true;
			}else {
				//管理员的session过期
				request.getRequestDispatcher("/WEB-INF/jsp/client/adminlogin.jsp").forward(request, response);
				
			}
		}else if (rolId.equals("3")) {
			TbSchool tbSchool = (TbSchool) request.getSession().getAttribute("school");
			if (null!=tbSchool) {
				return true;
			}else {
				//管理员的session过期
				request.getRequestDispatcher("/WEB-INF/jsp/client/login.jsp").forward(request, response);
				
			}
		}else if (rolId.equals("4")) {
			TbCoach tbCoach  = (TbCoach) request.getSession().getAttribute("coach");
			System.out.println("教练端菜单拦截器");
			if (null!=tbCoach) {
				return true;
			}else {
				//管理员的session过期
				request.getRequestDispatcher("/WEB-INF/jsp/client/login.jsp").forward(request, response);
				
			}
		}
		return false;
	}
}
