package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;


public interface SchoolService {

	public List<TbStudent> selectStus();
	
	public List<TbCoach> selectCoas();
	
	public List<TbCar> selectCars();
	
	//驾校入驻信息录入
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName);
}
