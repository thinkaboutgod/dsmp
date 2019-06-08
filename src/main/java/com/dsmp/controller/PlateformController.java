package com.dsmp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.Count;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbSchool;
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

	// 学员统计页面
	@RequestMapping(value = "toStudentCount.action")
	public ModelAndView toStudentCount() {
		ModelAndView mav = new ModelAndView();
		List<TbSchool> listSchool = plateformService.searchAllSchool();// 查询所有驾校
		List<Count> dateList = plateformService.searchDate();// 查询近六个月月份
		mav.addObject("listSchool", listSchool);
		mav.addObject("dateList", dateList);
		mav.setViewName("back/plateform_studentcount");
		return mav;
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

	// 修改学员状态
	@RequestMapping(value = "changeStudentState.action")
	public @ResponseBody MyResult changeStudentState(HttpServletRequest request) {
		myResult = plateformService.changeStudentState(request, myResult);
		return myResult;
	}

	// 按照驾校统计近半年报名学员人数
	@RequestMapping(value = "countStudentBySchool.action")
	public @ResponseBody List<Count> countStudentBySchool(String schId,String dateId) {
		List<Count> cList = plateformService.countStudent(schId,dateId);

		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getData() == null) {
				cList.get(i).setData("0");
			}
		}
		return cList;
	}

	// 按照驾校统计近半年报名学员人数
	@RequestMapping(value = "countStudentByDate.action")
	public @ResponseBody Map<String, List<Count>>  countStudentByDate(String month) {
		List<Count> cList = plateformService.countStudentByDate(month);
		Map<String, List<Count>> map = new HashMap<>();
		map.put("data", cList);
		return map;
	}
}
