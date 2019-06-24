package com.dsmp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dsmp.utils.CheckSensitiveWord;

/**
 * @author 给教练或驾校评价的时候，如果有敏感词汇就拦截掉
 *
 */
public class SensitiveWordsInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("你提交了评价，但是现在被拦截到拦截器来看一下是否评价中有敏感词！");
		String ratingContent = (String) request.getParameter("ratingContent");
//		System.out.println("ratingContent:"+ratingContent);
		if(null==ratingContent||"".equals(ratingContent)) {//如果评价为空
//			System.out.println("空评价empty");
			response.getWriter().print("empty");
			return false;//不放行
		}
//		System.out.println("konghoujixuzhixing");
		boolean rightful = CheckSensitiveWord.isRightful(ratingContent);
		if(rightful) {//true为合法，
//			System.out.println("评价合法");
			return true;//放行，让其评价
		}else {//false为敏感词汇
			response.getWriter().print("sensitiveWord");
//			System.out.println("评价非法，含敏感词汇：");
			return false;//不放行，返回提示是敏感信息
		}
		
		
	}
		
}
