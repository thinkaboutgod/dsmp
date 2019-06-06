package com.dsmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsmp.pojo.TbCoach;
import com.dsmp.service.CoachService;
import com.dsmp.service.SchoolService;

@Controller
@RequestMapping("coach")
public class CoachController {

	@Autowired private CoachService coachService;
	
	public String selectCoasBySchId(Model model,Integer schId) {
				
		List<TbCoach> coaList =  coachService.selectCoas();
		
		model.addAttribute("coaList", coaList);
		
		return "back/school_coach";
		
	}
	
	
}
