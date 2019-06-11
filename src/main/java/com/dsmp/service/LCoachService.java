package com.dsmp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.pojo.TbSubjectscore;



public interface LCoachService {
	public List<TbStudent> belongtococh(int coaid,HttpServletRequest request);//属于教练的学生
	public TbCoach selectcoach(int coaid);
	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid);//查询学生各科详情
	
	public List<TbRating> selectStudentratingmsg(int coaid,String choose);//查找学生评价详情

}

