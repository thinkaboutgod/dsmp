package com.dsmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.pojo.Count;
import com.dsmp.pojo.TbAdvertisement;
import com.dsmp.pojo.TbHotlink;
import com.dsmp.pojo.TbNotice;
import com.dsmp.service.HomeInfoService;

@Controller
@RequestMapping("/home")
public class HomeInfoController {
	@Autowired private HomeInfoService homeInfoService;
	@Autowired private TbSchoolMapper tbSchoolMapper;
	@Autowired private TbCoachMapper TbCoachMapper;
	//主页科目一跳转相应界面
	@RequestMapping("/keyi")
	public ModelAndView getKeYiPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/keyi");
		return mav;
	}	
	//主页科目二/三跳转相应界面
	@RequestMapping("/kemu")
	public ModelAndView getKeErPage(String KeMuNum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("KeMuNum",KeMuNum);
		mav.setViewName("client/videolist");
		return mav;
	}
	
	//广告数
	@RequestMapping("/main")
	public ModelAndView getHomeInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<TbAdvertisement> advList = homeInfoService.getAdvertisement();
		List<TbNotice> notList = homeInfoService.getNotice();
		List<TbHotlink> hotList = homeInfoService.getHotlink();
		request.getSession().setAttribute("hotList", hotList);
		mav.addObject("advList", advList);
		mav.addObject("notList", notList);
		mav.setViewName("client/home");
		return mav;
	}	
	
	//驾校排行
	@RequestMapping("/schoolRanking")
	public @ResponseBody List<Count> getSchoolRanking(){
		List<Count> ranList = tbSchoolMapper.getSchoolRanking();		
		for(int i = 0;i<ranList.size();i++) {
			double starAvg = ranList.get(i).getStarAvg();
			ranList.get(i).setStarAvg(Math.round(starAvg));
		}
		return ranList;
	}
	//教练排行
	@RequestMapping("/coachRanking")
	public @ResponseBody List<Count> getCoachRanking(){
		List<Count> coaList = TbCoachMapper.getCoachRanking();	
		for(int i = 0;i<coaList.size();i++) {
			double starAvg = coaList.get(i).getStarAvg();
			coaList.get(i).setStarAvg(Math.round(starAvg));
		}
		return coaList;
	}	
}
