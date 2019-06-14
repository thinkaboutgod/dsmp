package com.dsmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.pojo.TbSubjectscore;
import com.dsmp.service.LCoachService;

@Service
public class LCoachServiceImpl implements LCoachService {
	private String account;
	private String name;
	private String beginTime;
	private String endTime;
	private String belongSubject;
	private Date date;
	@Autowired private LCoachMapper lCoachMapper;
	
	//教练所属学生的普通信息
	@Override
	public List<TbStudent> belongtococh(int stuid,HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		belongSubject=request.getParameter("belongSubject");
		System.out.println("科目是："+belongSubject);
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		if(belongSubject!=null) {
		if (belongSubject.equals("查看所有学员")) {
			belongSubject = null;
		} else if(belongSubject.equals("科目一学员")){
			belongSubject="科目一";
		}else if(belongSubject.equals("科目二学员")){
			belongSubject="科目二";
		}else if(belongSubject.equals("科目三学员")){
			belongSubject="科目三";
		}else if(belongSubject.equals("科目四学员")){
			belongSubject="科目四";
		}else {
			belongSubject=null;
		}
		}
		SearchBean serchBean=new SearchBean();
		serchBean.setName(name);
		serchBean.setAccount(account);
		serchBean.setBeginTime(beginTime);
		serchBean.setEndTime(endTime);
		serchBean.setBelongSubject(belongSubject);
		List <TbStudent> stuentlist=lCoachMapper.belongtocoach(stuid,serchBean);
		return stuentlist;
	}
	
	@Override
	public TbCoach selectcoach(int coaid) {
		TbCoach bbCoach=lCoachMapper.selectCoach();
		return null;
	}
	//教练名下的学生科目考试信息
	@Override
	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid) {
		List<BelongtoCoachStudentMsg> studentmsg=lCoachMapper.selectStudentMsg(stuid);
		System.out.println("学生信息");		
		return studentmsg;
	}
	//查找学生对教练的评价
	@Override
	public List<TbRating> selectStudentratingmsg(int coaId,String choose) {
		if(choose.equals("所有评价")) {
			choose=null;
		}
		List<TbRating> ratinglist=lCoachMapper.selectStudentRating(coaId,choose);
		return ratinglist;
	}
	//查找考试安排信息
	@Override
	public List<TbExamschedule> selectThetestmsg(int schId,String subname) {
		date=new Date();
		List<TbExamschedule> thetestmsg=lCoachMapper.selectTheTestMsg(date,schId, subname);
		return thetestmsg;
	}
	//教练安排学生考试界面的表格信息
	@Override
	public List<TbStudent> findTestappointment(String subname, int coaid) {
		Integer subid=1;
		if(subname!=null&&subname.equals("科目一学员")) {
			subid=1;
		}
		if(subname!=null&&subname.equals("科目二学员")) {
			subid=2;
		}
		if(subname!=null&&subname.equals("科目三学员")) {
			subid=3;
		}
		if(subname!=null&&subname.equals("科目四学员")) {
			subid=4;
		}
		List<TbStudent> studenttestappointment=lCoachMapper.selectTestappointment(subid,coaid);
		return studenttestappointment;
	}

	@Override
	public List<TbStudent> findHaveappointment(int coaid,Integer stuid) {
		 List<TbStudent> haveappointmentstudent=lCoachMapper.selectHaveappointment(coaid,stuid);
		return haveappointmentstudent;
	}

	@Override
	public Integer findNumberofsubjects(String subname, int coaid) {
		int subid=0;
		if(subname!=null&&subname.equals("科目一")) {
			subid=1;
		}
		if(subname!=null&&subname.equals("科目二")) {
			subid=2;
		}
		if(subname!=null&&subname.equals("科目三")) {
			subid=3;
		}
		if(subname!=null&&subname.equals("科目四")) {
			subid=4;
		}
		
		Integer countNumberofsubjects=lCoachMapper.selectNumberofsubjects(subid,coaid);
		System.out.println("统计教练预约的各科人数："+countNumberofsubjects);
		
		return countNumberofsubjects;
	}

	@Override
	public Integer findSeatNum(int exsid) {
		Integer seatNum=lCoachMapper.selecteasSeatNum(exsid);
		return seatNum;
	}

	@Override
	public void insertBooking(int exsid, int stuid, int seatNum) {
		lCoachMapper.insertBooking(exsid, stuid, seatNum);
		
	}

	@Override
	public void updateBookingstate(int stuid) {
		lCoachMapper.updateBookingstate(stuid);	
	}
}
