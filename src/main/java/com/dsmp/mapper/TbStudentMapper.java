package com.dsmp.mapper;


import com.dsmp.pojo.TbStudent;

public interface TbStudentMapper {
	
	//查询学员
	public TbStudent findStudentByAccountPwd(TbStudent tbStudent);
	//更新学员输错密码状态
	public void updateStudent(TbStudent tbStudent);
	//学员注册
	public void insertStudent(TbStudent tbStudent);
}