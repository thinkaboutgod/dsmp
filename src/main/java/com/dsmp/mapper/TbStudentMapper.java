package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.Count;

public interface TbStudentMapper {

	public List<Count> countStudentBySchool(String schId);//根据驾校查询近半年报名人数
	public List<Count> searchDate();//查询近六个月日期
	public List<Count> countStudentByDate(String month);//查询某一个月所有驾校报名人数
	public List<Count> countStudentByMonth(String schId);//查询驾校近30天报名人数
}