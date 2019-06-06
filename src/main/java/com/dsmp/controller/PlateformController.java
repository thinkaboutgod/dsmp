package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.PlateformService;

@Controller
@RequestMapping("/plateform")
public class PlateformController {
	@Autowired
	private PlateformService plateformService;
	@Autowired 
	private MyResult myResult;
	// 学员查看页面
	@RequestMapping(value = "toStudentController.action")
	public String toStudentController() {

		return "back/plateform_student";
	}

	// 搜索学员,已报名的
	@RequestMapping(value = "searchAllStudent.action")
	public @ResponseBody Map<String, List<TbStudent>> searchStudent(HttpServletRequest request) {
		List<TbStudent> list = plateformService.searchStudent(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	// 搜索学员，未报名的
	@RequestMapping(value = "searchAllStudent2.action")
	public @ResponseBody Map<String, List<TbStudent>> searchStudent2(HttpServletRequest request) {
		List<TbStudent> list = plateformService.searchStudent2(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	//修改学员状态
	@RequestMapping(value = "changeStudentState.action")
	public @ResponseBody MyResult changeStudentState(HttpServletRequest request) {
		myResult = plateformService.changeStudentState(request, myResult);
		return myResult;
	}
}
