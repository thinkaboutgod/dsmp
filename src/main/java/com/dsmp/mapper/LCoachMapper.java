package com.dsmp.mapper;

import java.util.Date;
import java.util.List;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.pojo.TbSubjectscore;

public interface LCoachMapper {

	public TbCoach selectCoach();
	
	public List<TbStudent> belongtocoach(int coaid,SearchBean searchBean);
	
	public List<BelongtoCoachStudentMsg> selectStudentMsg(int stuid);
	
	public List<TbRating> selectStudentRating(int coaid,String choose);
	
	public List<TbExamschedule> selectTheTestMsg(Date datenow,int schId,String subName);
	
	public List<TbStudent> selectTestappointment(Integer subid,int coaid);
	
	public List<TbStudent> selectHaveappointment(int coaid,Integer stuid);
	
	public Integer selectNumberofsubjects(int subid,int coaid);
	
	public Integer selecteasSeatNum(int exsid);
	
	public void insertBooking(int exsid,int stuid,int seatNum);//预约插入一
	
	public void updateBookingstate(int stuid);//预约记录插入二

}