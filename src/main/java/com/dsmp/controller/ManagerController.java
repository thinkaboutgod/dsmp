package com.dsmp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.MyResult;
import com.dsmp.service.ManageService;

@Controller
@RequestMapping("/admin")
public class ManagerController {
	@Autowired private ManageService managerService;
	
	//主页跳到管理员登录页
	@RequestMapping("/admin")
	public ModelAndView getAdminPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/adminlogin");
		return mav;
	}	
	
	//管理员、运管部门登录
	@RequestMapping("/adminLogin")
	public @ResponseBody MyResult adminLogin(HttpSession session, String account, String password) {		
		MyResult result = managerService.adminLogin(session, account, password);		
		return result;
	}
	
}
