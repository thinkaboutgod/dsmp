package com.dsmp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbManager;
import com.dsmp.pojo.TbMenu;
import com.dsmp.pojo.TbSchool;
import com.dsmp.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/toManageMain.action")
	public ModelAndView toManageMain(String role_id) {

		System.out.println("执行");

		ModelAndView mav = new ModelAndView();
		Map<TbMenu, List<TbMenu>> menuMap = menuService.selectMen(Integer.valueOf(role_id));
		session.setAttribute("menuMap", menuMap);
		switch (role_id) {
		case "1":
			session.setAttribute("title", "平台管理端");
//			session.setAttribute("the_name", ((TbManager) session.getAttribute("manage")).getManName());
			session.setAttribute("rol_Id", 1);
			break;
		case "2":
			session.setAttribute("title", "运管门户");
			session.setAttribute("the_name", ((TbManager) session.getAttribute("manage")).getManName());
			session.setAttribute("rol_Id", 2);
			break;
		case "3":
			session.setAttribute("title", "驾校管理端");
			session.setAttribute("the_name", ((TbSchool) session.getAttribute("school")).getSchName());
			session.setAttribute("rol_Id", 3);
			break;
		case "4":
			session.setAttribute("title", "教练门户");
			session.setAttribute("the_name", ((TbCoach) session.getAttribute("caoch")).getCoaName());
			session.setAttribute("rol_Id", 4);
			break;
		}
		mav.setViewName("back/bimg");
		return mav;
	}
	//退出系统
	
	@RequestMapping(value = "/out.action")
	public ModelAndView out() {
		ModelAndView mav = new ModelAndView();
		int rol_Id = (int) session.getAttribute("rol_Id");
		switch (rol_Id) {
		case 1:
			session.removeAttribute("manage");
			mav.setViewName("client/adminlogin");
			break;
		case 2:
			session.removeAttribute("manage");
			mav.setViewName("client/adminlogin");
			break;
		case 3:
			session.removeAttribute("school");
			mav.setViewName("client/login");
			break;
		case 4:
			session.removeAttribute("coach");
			mav.setViewName("client/login");
			break;
		}
		return mav;
	}

}
