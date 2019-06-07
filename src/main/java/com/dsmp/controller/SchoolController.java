//package com.dsmp.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.dsmp.pojo.TbCoach;
//import com.dsmp.service.SchoolService;
//
//@Controller
//@RequestMapping("school")
//public class SchoolController {
//
//	@Autowired private SchoolService schoolServiceImpl;
//	
//	public String selectCoas(Model model) {
//		
//		List<TbCoach> coaList =  schoolServiceImpl.selectCoas();
//		
//		model.addAttribute("coaList", coaList);
//		
//		return "back/school_coach";
//	}
//	
//	
//}
