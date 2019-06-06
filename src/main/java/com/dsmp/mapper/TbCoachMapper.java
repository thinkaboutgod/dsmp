package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;

public interface TbCoachMapper {

	List<TbCoach> selectCoas();

	List<TbStudent> belongtocoach(int stuid);

	TbCoach selectCoach();
 
	
	
}