package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;

public interface LCoachMapper {

	public TbCoach selectCoach();
	
	public List<TbStudent> belongtocoach(int coaid,SearchBean searchBean);
	
	public List<BelongtoCoachStudentMsg> selectStudentMsg(int stuid);
	
	public List<TbRating> selectStudentRating(int coaid,String choose);
	
	public List<TbExamschedule> selectTheTestMsg(int schId);

}