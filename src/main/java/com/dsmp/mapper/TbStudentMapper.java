package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.Count;

import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;

import com.dsmp.pojo.TbStudent;

public interface TbStudentMapper {

	public List<Count> countStudentBySchool(String schId);//根据驾校查询近半年报名人数
	public List<Count> searchDate();//查询近六个月日期
	public List<Count> countStudentByDate(String month);//查询某一个月所有驾校报名人数
	public List<Count> countStudentByMonth(String schId);//查询驾校近30天报名人数

	public List<TbStudent> selectStudentByCoachIdAndSubject(String coaId);//查询教练底下的科目二三学员
	
	//查询学员
	public TbStudent findStudentByAccountPwd(TbStudent tbStudent);
	//更新学员输错密码状态
	public void updateStudent(TbStudent tbStudent);
	//学员注册
	public void insertStudent(TbStudent tbStudent);
	
	/**
	 * 	查询驾校名下的所有学员
	 * @param sBean
	 * @return
	 */
	public List<TbStudent> searchAllstudent(SearchBean sBean);
	public int changeStudentState(int stuId,String state);//修改学员账号状态
	public int changeStudentStateLock(int stuId,String state);
	
	public String findStudentImgByStuId(int stuId);//根据id查学员照片路径
	
}