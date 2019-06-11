package com.dsmp.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCarMapper;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCar;
import com.dsmp.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired private TbCarMapper tbCarMapper;
	
	@Override
	public List<TbCar> selectCarsBySchId(Integer schId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Test
	public List<TbCar> selectCarsByCondition(HttpServletRequest request) {
		String carPlateNum = request.getParameter("carPlateNum");
		String coachName = request.getParameter("coachName");
		String schId = request.getParameter("schId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (carPlateNum.trim().equals("")) {
			carPlateNum = null;
		}
		if (coachName.trim().equals("")) {
			coachName = null;
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
		
		sBean.setCarPlateNum(carPlateNum);
		sBean.setCoachName(coachName);
		sBean.setSchId(schId);
		sBean.setBeginTime(beginTime);
		sBean.setEndTime(endTime);
		
		List<TbCar> carList = tbCarMapper.selectCarsByCondition(sBean);
		
		for (TbCar tbCar : carList) {
			String fromDate = tbCar.getCarStartTime().split(" ")[0];
			String toDate = tbCarMapper.selectCurTime();
			System.out.println(fromDate);
			System.out.println(toDate);
	        Period period = Period.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
	        StringBuffer carUsedTime = new StringBuffer();
	        carUsedTime.append(period.getYears()).append("年")
	                .append(period.getMonths()).append("月")
	                .append(period.getDays()).append("天");
	        System.out.println(carUsedTime.toString());
	        tbCar.setCarStartTime(fromDate);
			tbCar.setCarUsedTime(carUsedTime.toString());
		}
		
		return carList;
		
	}

}
