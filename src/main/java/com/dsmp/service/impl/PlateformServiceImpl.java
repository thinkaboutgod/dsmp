package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.CoachMapper;
import com.dsmp.mapper.PlateformMapper;
import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
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
	@Autowired
	private TbSchoolMapper tbSchoolMapper;
	@Autowired
	private TbStudentMapper tbStudentMapper;
	@Override
	public List<TbStudent> searchStudent(HttpServletRequest request) {//查询已报名学员
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

		return plateformMapper.searchAllstudent(sBean);
	}

	@Override
	public List<TbStudent> searchStudent2(HttpServletRequest request) {//查询未报名学员
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

		return plateformMapper.searchAllstudent2(sBean);
	}

	@Override
	//修改学员状态
	public MyResult changeStudentState(HttpServletRequest request,MyResult myResult) {
		String state = request.getParameter("state");
		String stuId = request.getParameter("stuId");
		String preText = request.getParameter("preText");
		int res = 0;
		if (preText.equals("锁定")) {
			res = plateformMapper.changeStudentStateLock(Integer.valueOf(stuId), "启用");
		}else {
			if (state.equals("start")) {
				state="启用";
			}else if (state.equals("forbid")) {
				state="禁用";
			}
			res = plateformMapper.changeStudentState(Integer.valueOf(stuId), state);
			
		}
		if (res>0) {
			myResult.setMyresult("success");
		}else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	@Override
	public List<TbSchool> searchAllSchool() {//查询所有驾校
		return tbSchoolMapper.selectAllSchool();
	}

	@Override
	public List<Count> countStudent(String schId,String dateId) {//根据驾校统计近半年,或者近30天报名人数
		if (dateId.equals("1")) {
			return tbStudentMapper.countStudentBySchool(schId);
		}
		return tbStudentMapper.countStudentByMonth(schId);
	}

	@Override
	public List<Count> searchDate() {//获取近半年日期到月份
		return tbStudentMapper.searchDate();
	}

	@Override
	public List<Count> countStudentByDate(String month) {//统计某个月各驾校报名人数
		return tbStudentMapper.countStudentByDate(month);
	}

}
