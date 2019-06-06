package com.dsmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbStudent;
import com.dsmp.service.CoachService;

@Controller
public class CoachController {
	@Autowired private CoachService coachServiceImpl;
	
	@RequestMapping("/coachlogin.action")
	public String coachLogin() {
		System.out.println("教练登陆");	
		return "redirect:/jsp/bmain.jsp";
	}
	@RequestMapping("/belongtocoach.action")
	public @ResponseBody List<TbStudent> getStudent(String coaId ,String coaPassword){
		
		System.out.println(coaId);
		System.out.println(coaPassword);
		List<TbStudent> studentlist= coachServiceImpl.belongtococh(1);
		System.out.println(studentlist.get(1).getStuName());
		for(TbStudent tbStudent:studentlist) {
			System.out.println(tbStudent.getStuName());
			System.out.println(tbStudent.getStuAccount());			
		}		
		return studentlist;
	}

}
