package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.service.CarService;

@Controller
@RequestMapping(value="car")
public class CarController {

	@Autowired private CarService carService;
	@Autowired private MyResult myResult;
	
	@RequestMapping(value="toschool_car")
	public String toSchoolCoach(HttpSession session) {
		session.setAttribute("schId", 1);
		return "back/school_car";
	}
	
	
	@RequestMapping(value="selectCarsByCondition")
	public @ResponseBody Map<String,List<TbCar>> selectCarsByCondition(HttpServletRequest request) {
		Map<String,List<TbCar>> map = new HashMap<>();
		List<TbCar> carList =  carService.selectCarsByCondition(request);
		map.put("data", carList);
		return map;
	}
	
	@RequestMapping(value = "scrapCar.action")
	public @ResponseBody MyResult scrapCar(HttpServletRequest request) {
		myResult = carService.scrapCar(request, myResult);
		return myResult;
	}
	
	
}
