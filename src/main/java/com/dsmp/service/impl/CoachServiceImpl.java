package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {
	
	@Autowired private TbCoachMapper tbCoachMapper;

	@Override
	public List<TbCoach> selectCoasByCondition(HttpServletRequest request) {
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String schId = request.getParameter("schId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (schId.trim().equals("")) {
			schId = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		SearchBean sBean = new SearchBean();
		sBean.setAccount(account);
		sBean.setName(name);
		sBean.setSchId(schId);
		sBean.setBeginTime(beginTime);
		sBean.setEndTime(endTime);
		return tbCoachMapper.selectCoasByCondition(sBean);
	}

	@Override
	public MyResult changeCoachState(HttpServletRequest request, MyResult myResult) {
		String state = request.getParameter("state");
		String coaId = request.getParameter("coaId");
		int res = 0;
		if (state.equals("start")) {
			state = "启用";
		} else if (state.equals("forbid")) {
			state = "禁用";
		}
		
		res = tbCoachMapper.changeCoachState(Integer.valueOf(coaId), state);

		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}
	
	

	@Override
	public MyResult addCoach(HttpServletRequest request, MyResult myResult) {
		TbCoach coach = new TbCoach();
		
		Integer res = tbCoachMapper.addCoach(coach);
		if(res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}



	
	
}
