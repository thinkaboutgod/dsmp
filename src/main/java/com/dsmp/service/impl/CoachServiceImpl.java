package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {

	private String account;
	private String name;
	private String fwhere;
	private String beginTime;
	private String endTime;
	
	@Autowired private TbCoachMapper tbCoachMapper;

	@Override
	public List<TbCoach> selectCoasByCondition(HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		fwhere = request.getParameter("schId");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (fwhere.trim().equals("")) {
			fwhere = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		SearchBean sBean = new SearchBean(account, name, fwhere, beginTime, endTime);
		
		return tbCoachMapper.selectCoasByCondition(sBean);
	}



	
	
}
