package com.dsmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;

@Controller

public class LCoachController {

	@Autowired private LCoachService lCoachServiceImpl;
	
	public String selectCoasBySchId(Model model,Integer schId) {
				
		List<TbCoach> coaList =  lCoachServiceImpl.selectCoas();
		
		model.addAttribute("coaList", coaList);
		
		return "back/school_coach";
		
		
	}
	
	@RequestMapping("/coachlogin.action")
	public String coachLogin() {
		System.out.println("教练登陆");	
		return "redirect:/jsp/bmain.jsp";
	}
	@RequestMapping("/belongtocoach.action")
	public @ResponseBody List<TbStudent> getStudent(){
		
		System.out.println("教练主页");
		List<TbStudent> studentlist= lCoachServiceImpl.belongtococh(1);
		System.out.println(studentlist.get(1).getStuName());
		for(TbStudent tbStudent:studentlist) {
			System.out.println(tbStudent.getStuName());
			System.out.println(tbStudent.getStuAccount());			
		}		
		return studentlist;
	}	
}
