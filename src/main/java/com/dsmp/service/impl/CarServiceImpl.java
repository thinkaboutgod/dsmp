package com.dsmp.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsmp.mapper.TbCarMapper;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.SearchBean;
import com.dsmp.pojo.TbCar;
import com.dsmp.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired private TbCarMapper tbCarMapper;
	@Autowired private TbParameterMapper tbParameterMapper;
	
	/**
	 * 	根据条件查询驾校名下车辆
	 */
	@Override
	public List<TbCar> selectCarsByCondition(HttpServletRequest request) {
		//获取查询条件
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
		
		//添加查询条件
		SearchBean sBean = new SearchBean();
		sBean.setCarPlateNum(carPlateNum);
		sBean.setCoachName(coachName);
		sBean.setSchId(schId);
		sBean.setBeginTime(beginTime);
		sBean.setEndTime(endTime);
		
		List<TbCar> carList = tbCarMapper.selectCarsByCondition(sBean);
		
		for (TbCar tbCar : carList) {
			//计算使用时间
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

	/**
	 * 报废车辆
	 */
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

	
	@Override
	@Transactional
	public String addCar(HttpServletRequest request,MultipartFile carImgNew ) {
		String result=null;
		TbCar car = new TbCar();
		
		if ("" == carImgNew.getOriginalFilename()) {// 没有更新图片
			car.setCarImg(null);
		} else {// 有更新图片
			String fileName = carImgNew.getOriginalFilename();
			car.setCarImg(fileName);
			String filePath = tbParameterMapper.selectParamter("系统文件存储路径");// 获取系统文件储存路径
			String path = filePath + "/images/car/";
//			String path = request.getServletContext().getRealPath("/images/car/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			
			try {
				carImgNew.transferTo(new File(path, fileName));// 插入图片
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		car.setSchId(Integer.parseInt(request.getParameter("schId")));
		car.setCarPlatenum(request.getParameter("carPlateNum"));
		car.setCarStyle(request.getParameter("carStyle"));
		car.setCarColor(request.getParameter("carColor"));
		
		int res = tbCarMapper.addCar(car);
		
		if(res>0) {
			result="success";
		}else {
			result="failed";
		}
		
		return result;
	}

	
	
}
