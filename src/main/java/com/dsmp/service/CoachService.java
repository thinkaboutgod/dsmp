package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;

/**
 * 
* @Description: TODO(教练的业务接口)  
* @author 庄祯 
* @date 2019年6月7日 下午4:35:21
 */
public interface CoachService {

	/**
	 * 	根据条件查询驾校名下的教练
	 * @param request
	 * @return
	 */
	public List<TbCoach> selectCoasByCondition(HttpServletRequest request);
	
	/**
	 * 	修改教练的状态
	 * @param request
	 * @param myResult
	 * @return
	 */
	public MyResult changeCoachState(HttpServletRequest request, MyResult myResult);

	/**
	 * 	添加教练
	 * @param myResult 
	 * @param request
	 * @return
	 */
	public MyResult addCoach(HttpServletRequest request, MyResult myResult);
	
	
	
}

