package com.dsmp.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;


public interface SchoolService {


	
	//驾校入驻信息录入
	public MyResult insertSchoolInfo(String phone,String password,String sch_creditcode,String sch_name,
			String sch_type,String sch_address,String sch_bossname,String sch_registerCapital,
			String sch_introduce,Double sch_charge,String fileName);

	/**
	 * 	查看驾校的考场记录
	 * @param request
	 * @return
	 */
	public List<TbExamschedule> selectExamschedule(HttpServletRequest request);


	/**
	 *	 新增考场
	 * @param request
	 * @return
	 */
	public MyResult addExamschedule(HttpServletRequest request);

	/**
	 * 	录入学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	public MyResult addScore(HttpServletRequest request);

	/**
	 * 	修改学员科目二和科目三的成绩
	 * @param request
	 * @return
	 */
	public MyResult updateScore(HttpServletRequest request);

}
