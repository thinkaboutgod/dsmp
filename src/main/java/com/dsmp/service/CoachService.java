package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;



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
	
}

