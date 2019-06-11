package com.dsmp.mapper;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCar;
import java.util.List;

public interface TbCarMapper {
   
	List<TbCar> selectCarsByCondition(SearchBean sBean);

	String selectCurTime();
	
}