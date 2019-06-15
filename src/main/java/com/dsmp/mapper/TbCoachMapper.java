package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;

public interface TbCoachMapper {

	//教练登陆
	public TbCoach getCoach(TbCoach coach);

	public List<TbCoach> selectCoasByCondition(SearchBean sBean); 
	
	public List<TbCoach> selseCoach(Integer schId);

	public Integer changeCoachState(Integer valueOf, String state);


}