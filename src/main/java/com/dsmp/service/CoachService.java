package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbCoach;
<<<<<<< HEAD

public interface CoachService {

	List<TbCoach> selectCoas();

=======
import com.dsmp.pojo.TbStudent;

public interface CoachService {
	public List<TbStudent> belongtococh(int stuid);//属于教练的学生
	public TbCoach selectcoach(int coaid);
>>>>>>> branch 'master' of https://github.com/thinkaboutgod/dsmp
}
