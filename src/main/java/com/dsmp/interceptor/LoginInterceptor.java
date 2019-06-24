package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.pojo.MyResult;
import com.dsmp.utils.GsonUtils;
import com.google.code.kaptcha.Constants;



public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MyResult result = new MyResult();
		String myyzm = request.getParameter("yzm");
		String code = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(myyzm.equalsIgnoreCase(code)) {
			return true;
		}else {
			result.setMyresult("codeFaild");
			response.getWriter().println(GsonUtils.toJson(result));
			return false;
		}
		
	}
}
