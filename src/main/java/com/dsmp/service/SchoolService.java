package com.dsmp.service;


import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;


public interface SchoolService {


	
	//驾校入驻信息录入
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName);

	//查询允许报名和运营的所有驾校
	public List<TbSchool> selectAllSchoolForAdvertise();
	
}
