package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;

public interface TbCoachMapper {

	//教练登陆
	public TbCoach getCoach(TbCoach coach);

	/**
	 * 查询教练
	 * @param sBean
	 * @return
	 */
	public List<TbCoach> selectCoasByCondition(SearchBean sBean); 
	
	public List<TbCoach> selseCoach(Integer schId);

	/**
	 * 	修改教练状态
	 * @param valueOf
	 * @param state
	 * @return
	 */
	public Integer changeCoachState(Integer valueOf, String state);

	/**
	 * 	添加教练
	 * @param coach
	 * @return
	 */
	public Integer addCoach(TbCoach coach);


}