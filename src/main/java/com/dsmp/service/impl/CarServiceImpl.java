package com.dsmp.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbCarMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCar;
import com.dsmp.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired private TbCarMapper tbCarMapper;
	
	@Override
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
	        Period period = Period.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
	        StringBuffer carUsedTime = new StringBuffer();
	        carUsedTime.append(period.getYears()).append("年")
	                .append(period.getMonths()).append("月")
	                .append(period.getDays()).append("天");
	        tbCar.setCarStartTime(fromDate);
			tbCar.setCarUsedTime(carUsedTime.toString());
		}
		return carList;
	}

	@Override
	public MyResult scrapCar(HttpServletRequest request, MyResult myResult) {
		String state = request.getParameter("state");
		String carId = request.getParameter("carId");
	
		int res = tbCarMapper.scrapCar(Integer.valueOf(carId), state);

		if (res > 0) {
			myResult.setMyresult("success");
		} else {
			myResult.setMyresult("failed");
		}
		return myResult;
	}

	
}
