package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;

public interface TbCoachMapper {

	//教练登陆
	public TbCoach getCoach(TbCoach coach);

	public List<TbCoach> selectCoasByCondition(SearchBean sBean); 
	
	public List<TbCoach> selseCoach(Integer schId);

	public Integer changeCoachState(Integer valueOf, String state);
	//查询所有教练
	public List<TbCoach> selectAllCoach();
	/**
	 * 	添加教练
	 * @param coach
	 * @return
	 */
	public Integer addCoach(TbCoach coach);
	//教练排行
	public List<Count> getCoachRanking();


}