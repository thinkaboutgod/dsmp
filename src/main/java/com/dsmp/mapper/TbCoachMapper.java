package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;

public interface TbCoachMapper {

	public List<TbCoach> selectCoasBySchId(Integer schId);

	public TbCoach selectCoach(String coaAccount,String pwd);

	public List<TbCoach> selectCoasByCondition(SearchBean sBean);
	
}