package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

public interface PlateformService {
	public List<TbStudent> searchStudent(HttpServletRequest request);//查询已报名驾校学员
	public List<TbStudent> searchStudent2(HttpServletRequest request);//查询未报名驾校学员
	public MyResult changeStudentState(HttpServletRequest request,MyResult myResult);//修改学员账号状态
	public List<TbSchool> searchAllSchool();//查询所有驾校
	public List<Count> searchDate();//查询近六个月日期
	public List<Count> countStudent(String schId,String dateId);//查询各驾校近六个月报名人数
	public List<Count> countStudentByDate(String month);//查询各驾校某个月报名人数
}
