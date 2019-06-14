package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.pojo.TbSubjectscore;



public interface LCoachService {
	public List<TbStudent> belongtococh(int coaid,HttpServletRequest request);//属于教练的学生
	public TbCoach selectcoach(int coaid);
	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid);//查询学生各科详情
	
	public List<TbRating> selectStudentratingmsg(int coaid,String choose);//查找学生评价详情
	
	public List<TbExamschedule> selectThetestmsg(int schId,String subname);//根据驾校id查找本驾校的考试安排
	
	public List<TbStudent> findTestappointment(String subid,int coaid);//查看可安排考试学生
	
	public List<TbStudent> findHaveappointment(int coaid,Integer stuid);//查看已预约的学生
	
	public Integer findNumberofsubjects(String subname,int coaid);//统计各科的报名人数，满五名不能预约
	
	public Integer findSeatNum(int exsid);//考试座位号
	
	public void insertBooking(int exsid,int stuid,int seatNum);//预约数据插入
	
	public void updateBookingstate(int stuid);//更改学员预约状态
	
}

