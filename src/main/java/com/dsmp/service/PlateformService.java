package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudent;

public interface PlateformService {
	public List<TbStudent> searchStudent(HttpServletRequest request);
	public List<TbStudent> searchStudent2(HttpServletRequest request);
	public MyResult changeStudentState(HttpServletRequest request,MyResult myResult);
}
