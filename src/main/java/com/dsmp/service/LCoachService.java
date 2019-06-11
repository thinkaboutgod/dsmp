package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;



public interface LCoachService {

	public List<TbCoach> selectCoas();
	public List<TbStudent> belongtococh(int stuid);//属于教练的学生
	public TbCoach selectcoach(int coaid);
	
	public List<TbStudent> searchSubjectStudent();//查询教练底下科目二三学员
	
	 public MyResult countTimeByStuIdAndSubject(String stuId,String subId);//查询某学员该阶段已学时长
}

