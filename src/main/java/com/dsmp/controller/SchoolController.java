package com.dsmp.controller;


import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbCoach;
import com.dsmp.service.CoachService;
import com.dsmp.service.SchoolService;

@Controller
@RequestMapping("school")
public class SchoolController {

	@Autowired private SchoolService schoolService;		
	@Autowired private CoachService coachService;
	
	@RequestMapping("/selectCoach")
	public @ResponseBody List<TbCoach> selectCoach(Integer selectSchool){
		System.out.println(selectSchool);
		List<TbCoach> coaList = coachService.selectCoach(selectSchool);
		return coaList;
	}
}

