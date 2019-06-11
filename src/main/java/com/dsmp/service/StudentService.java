package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.TbStudent;

public interface StudentService {

	/**
	 * 	根据教练的id查询教练名下的学生信息
	 * @param coaId
	 * @return
	 */
	public List<TbStudent> selectStusByCoaId(Integer coaId);
	
	/**
	 * 	根据驾校的id查询驾校名下的学生信息
	 * @param schId
	 * @return
	 */
	public List<TbStudent> selectStusBySchId(Integer schId);

	public List<TbStudent> searchAllstudent(HttpServletRequest request);
	
	
	
}
