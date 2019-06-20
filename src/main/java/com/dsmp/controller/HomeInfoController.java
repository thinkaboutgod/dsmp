package com.dsmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;
import com.dsmp.service.HomeInfoService;

@Controller
@RequestMapping("/home")
public class HomeInfoController {
	@Autowired private HomeInfoService homeInfoService;
	
	//主页科目一跳转相应界面
	@RequestMapping("/keyi")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/keyi");
		return mav;
	}
	//广告数
	@RequestMapping("/main")
	public ModelAndView getHomeInfo() {
		ModelAndView mav = new ModelAndView();
		List<TbAdvertisement> advList = homeInfoService.getAdvertisement();
		List<TbNotice> notList = homeInfoService.getNotice();
		List<TbHotlink> hotList = homeInfoService.getHotlink();
		mav.addObject("advList", advList);
		mav.addObject("notList", notList);
		mav.addObject("hotList", hotList);
		System.out.println(advList.get(0).getTbSchool().getSchName());
		System.out.println(notList.get(0).getNotTitle());
		mav.setViewName("client/home");
		return mav;
	}	
}
