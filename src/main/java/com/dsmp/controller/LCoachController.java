package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbMenu;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;
import com.dsmp.service.MenuService;

@Controller

public class LCoachController {

	@Autowired private LCoachService lCoachServiceImpl;
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private HttpSession session;
	
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
	public @ResponseBody Map<String, List<TbStudent>> getStudent(){
		
		System.out.println("教练主页");
		
		
		List<TbStudent> studentlist= lCoachServiceImpl.belongtococh(1);
		Map<String,List<TbStudent>> studentlistmap=new HashMap<>();
		studentlistmap.put("data",studentlist);
		System.out.println(studentlist.get(1).getStuName());
		for(TbStudent tbStudent:studentlist) {
			System.out.println(tbStudent.getStuName());
			System.out.println(tbStudent.getStuAccount());			
		}		
		return studentlistmap;
	}	
	@RequestMapping(value = "/tocoachmain.action")
	public ModelAndView toManageMain(String role_id) {
		System.out.println("执行教练主页");
		ModelAndView mav = new ModelAndView();
		Map<TbMenu, List<TbMenu>> menuMap = menuService.selectMen(Integer.valueOf(role_id));
		session.setAttribute("menuMap", menuMap);
		switch (role_id) {
		case "1":
			session.setAttribute("title","平台管理端");
			break;
		case "2":
			session.setAttribute("title","运管门户");
			break;
		case "3":
			session.setAttribute("title","驾校管理端");
			break;
		case "4":
			session.setAttribute("title","教练门户");
			break;
		}
		mav.setViewName("coach/belongtocoachstudents");
		return mav;
	}
}
