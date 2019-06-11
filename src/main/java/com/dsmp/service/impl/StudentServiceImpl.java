package com.dsmp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	

	@Autowired private TbStudentMapper tbStudentMapper;

	@Override
	public List<TbStudent> selectStusByCoaId(Integer coaId) {
		
		return null;
	}

	@Override
	public List<TbStudent> selectStusBySchId(Integer schId) {
		
		return null;
	}

	@Override
	public List<TbStudent> searchAllstudent(HttpServletRequest request) {
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
		return tbStudentMapper.searchAllstudent(sBean);
	}

	
}
