package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.CoachService;

@Controller
@RequestMapping("coach")
public class CoachController {

	@Autowired private CoachService coachService;
	
	@RequestMapping(value="toSchool_Coach")
	public String toSchoolCoach(HttpSession session) {
		session.setAttribute("schId", 1);
		return "back/school_coach";
	}
	
	
	@RequestMapping(value="selectCoasByCondition")
	public @ResponseBody Map<String,List<TbCoach>> selectCoasByCondition(HttpServletRequest request) {
		
		Map<String,List<TbCoach>> map = new HashMap<>();
		List<TbCoach> coaList =  coachService.selectCoasByCondition(request);
		map.put("data", coaList);
		return map;
		
	}
	
	
	
//	@RequestMapping("/selectStusByCoaId.action")
//	public @ResponseBody List<TbStudent> selectStusByCoaId(String coaId ,String coaPassword){
//		
//		System.out.println(coaId);
//		System.out.println(coaPassword);
//		List<TbStudent> studentlist= coachService.selectStusByCoaId(1);
//		System.out.println(studentlist.get(1).getStuName());
//		for(TbStudent tbStudent:studentlist) {
//			System.out.println(tbStudent.getStuName());
//			System.out.println(tbStudent.getStuAccount());			
//		}	
//		
//		return studentlist;
//	}
	
}
