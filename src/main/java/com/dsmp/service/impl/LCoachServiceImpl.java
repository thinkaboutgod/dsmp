package com.dsmp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.LCoachMapper;
import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
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
	@Autowired private LCoachMapper lCoachMapper;
	
	
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

	@Override
	public List<BelongtoCoachStudentMsg> selectStudentParticulars(int stuid) {
		List<BelongtoCoachStudentMsg> studentmsg=lCoachMapper.selectStudentMsg(stuid);
		System.out.println("学生信息");		
		return studentmsg;
	}

	@Override
	public List<TbRating> selectStudentratingmsg(int coaId,String choose) {
		if(choose.equals("所有评价")) {
			choose=null;
		}
		List<TbRating> ratinglist=lCoachMapper.selectStudentRating(coaId,choose);
		return ratinglist;
	}
}
