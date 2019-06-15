package com.dsmp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbMenu;
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
		mav.setViewName("back/bimg");
		return mav;
	}

}
