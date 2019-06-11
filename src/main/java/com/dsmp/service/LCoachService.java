package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.MyResult;

import javax.servlet.http.HttpServletRequest;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;

public interface LCoachService {
	public List<TbStudent> belongtococh(int coaid, HttpServletRequest request);// 属于教练的学生

	public TbCoach selectcoach(int coaid);

	public List<TbStudent> searchSubjectStudent();// 查询教练底下科目二三学员

	public MyResult countTimeByStuIdAndSubject(String stuId, String subId);// 查询某学员该阶段已学时长

	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid);// 查询学生各科详情

	public List<TbRating> selectStudentratingmsg(int coaid, String choose);// 查找学生评价详情

	public List<TbExamschedule> selectThetestmsg(int schId);//根据驾校id查找本驾校的考试安排
	
}
