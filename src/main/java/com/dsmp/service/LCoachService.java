package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;



public interface LCoachService {

	public List<TbCoach> selectCoas();
	public List<TbStudent> belongtococh(int stuid);//属于教练的学生
	public TbCoach selectcoach(int coaid);

}

