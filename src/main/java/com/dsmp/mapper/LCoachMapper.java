package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;

public interface LCoachMapper {
	
	public List<TbCoach> selectCoas();
	
	public TbCoach selectCoach();
	
	public List<TbStudent> belongtocoach(int coaid);
}