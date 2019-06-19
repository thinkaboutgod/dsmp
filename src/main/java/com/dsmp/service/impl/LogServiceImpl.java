package com.dsmp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbLogMapper;
import com.dsmp.pojo.TbLog;
import com.dsmp.service.LogService;
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private TbLogMapper tbLogMapper;
	@Override
	public List<TbLog> searchLog(HttpServletRequest request) {
		String logOperatoraccount = request.getParameter("logOperatoraccount");
		String logRole = request.getParameter("logRole");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (logOperatoraccount.trim().equals("")) {
			logOperatoraccount = null;
		}
		if (logRole.trim().equals("")) {
			logRole = null;
		}
		if (beginTime.trim().equals("")) {
			beginTime = null;
		}
		if (endTime.trim().equals("")) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		return tbLogMapper.searchAllLog(logOperatoraccount, logRole, beginTime, endTime);
	}

}
