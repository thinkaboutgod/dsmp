package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.PlateformMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.PlateformService;

@Service
public class PlateformServiceImpl implements PlateformService {
	private String account;
	private String name;
	private String fwhere;
	private String beginTime;
	private String endTime;
	@Autowired
	private PlateformMapper plateformMapper;
	
	@Override
	public List<TbStudent> searchStudent(HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		fwhere = request.getParameter("school");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
		if (account.trim().equals("")) {
			account = null;
		}
		if (name.trim().equals("")) {
			name = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (fwhere.trim().equals("")) {
			fwhere = null;
		}

		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		SearchBean sBean = new SearchBean(account, name, fwhere, beginTime, endTime);
		List<TbStudent> list = new ArrayList<>();
		list = plateformMapper.searchAllstudent(sBean);

		return list;
	}

	@Override
	public List<TbStudent> searchStudent2(HttpServletRequest request) {
		account = request.getParameter("account");
		name = request.getParameter("name");
		fwhere = request.getParameter("school");
		beginTime = request.getParameter("beginTime");
		endTime = request.getParameter("endTime");
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
		SearchBean sBean = new SearchBean(account, name, fwhere, beginTime, endTime);
		List<TbStudent> list = new ArrayList<>();
		list = plateformMapper.searchAllstudent2(sBean);

		return list;
	}

	@Override
	public MyResult changeStudentState(HttpServletRequest request,MyResult myResult) {
		String state = request.getParameter("state");
		String stuId = request.getParameter("stuId");
		String preText = request.getParameter("preText");
		if (preText.equals("锁定")) {
			
		}else {
			if (state.equals("start")) {
				state="启用";
			}else if (state.equals("forbid")) {
				state="禁用";
			}
			int res = plateformMapper.changeStudentState(Integer.valueOf(stuId), state);
			if (res>0) {
				myResult.setMyresult("success");
			}else {
				myResult.setMyresult("failed");
			}
		}
		
		return myResult;
	}

}
