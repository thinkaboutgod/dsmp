package com.dsmp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.TbStudent;

import com.dsmp.pojo.MyResult;

public interface StudentService {
	//学员登录验证
	public MyResult studentLogin(HttpSession session,String account,String password,String role); 
	//教练登录验证
	public MyResult coachLogin(HttpSession session,String account,String password,String role); 
	//驾校登录验证
	public MyResult schoolLogin(HttpSession session,String account,String password,String role); 
	//学员注册
	public MyResult studentRegister(HttpServletRequest request,String stuAccount,String stuPassword,String verifyCode);
	
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
