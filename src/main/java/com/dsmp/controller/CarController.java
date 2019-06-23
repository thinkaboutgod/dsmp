package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	/**
	 * 查询教练车
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectCarsByCondition")
	public @ResponseBody Map<String,List<TbCar>> selectCarsByCondition(HttpServletRequest request) {
		Map<String,List<TbCar>> map = new HashMap<>();
		List<TbCar> carList =  carService.selectCarsByCondition(request);
		map.put("data", carList);
		return map;
	}
	
	/**
	 * 报废车辆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "scrapCar")
	@Transactional
	public @ResponseBody MyResult scrapCar(HttpServletRequest request) {
		return carService.scrapCar(request, myResult);
	}
	
	/**
	 *	 新增教练车
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addCar")
	@Transactional
	public @ResponseBody String addCar(HttpServletRequest request,MultipartFile carImgNew) {
		return carService.addCar(request, carImgNew);
	}
	
	/**
	 *	 分配教练车
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "distributeCar")
	@Transactional
	public @ResponseBody MyResult distributeCar(HttpServletRequest request) {
		return carService.distributeCar(request, myResult);
	}
	
	
}
