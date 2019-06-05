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
	@Autowired private MenuService menuService;
	@Autowired HttpSession session;
	@RequestMapping(value="/toManageMain.action")
	public ModelAndView toManageMain(String role_id) {
		ModelAndView mav = new ModelAndView();
		Map<TbMenu, List<TbMenu>> menuMap = menuService.selectMen(Integer.valueOf(role_id));
//		mav.addObject(menuMap);
		session.setAttribute("menuMap", menuMap);
		mav.setViewName("");
		return mav;
	}

}
