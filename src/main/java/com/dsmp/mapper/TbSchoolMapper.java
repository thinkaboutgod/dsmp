package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

public interface TbSchoolMapper {
	public List<TbSchool> selectAllSchool();
	
	public List<TbStudent> selectStus();
	
	public List<TbCoach> selectCoas();
	
	public List<TbCar> selectCars();
	
	//驾校登录验证
	public TbSchool getSchool(TbSchool tbSchool);
	public TbSchool findSchoolBySchId(Integer schId);
}