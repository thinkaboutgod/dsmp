package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbMenu;
import com.dsmp.pojo.TbRating;
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
	
	private int stuId;
	@RequestMapping("/tostudentparticulars.action")
	public ModelAndView tostudentparticulars(HttpServletRequest request) {
		System.out.println("转换界面");
		stuId=Integer.valueOf(request.getParameter("stuId"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentparticulars");		
		return mav;
		
	}
	@RequestMapping("/studentparticulars.action")
	public @ResponseBody Map<String, List<BelongtoCoachStudentMsg>>  getStudentParticulars(){
		System.out.println("学生详情页");
		List<BelongtoCoachStudentMsg> studentmsg=lCoachServiceImpl.selectStudentParticulars(stuId);
		Map<String, List<BelongtoCoachStudentMsg>>  studentParticulars=new HashMap<>();
		studentParticulars.put("data", studentmsg);
		return studentParticulars;
	}
	@RequestMapping(value="/belongtocoach.action",method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getStudent(HttpServletRequest request){
		
		System.out.println("教练主页");
		List<TbStudent> studentlist= lCoachServiceImpl.belongtococh(1,request);
		Map<String,List<TbStudent>> studentlistmap=new HashMap<>();
		studentlistmap.put("data",studentlist);	
		return studentlistmap;
	}	
	//入口
	@RequestMapping(value = "/tocoachfindstudents.action")
	public ModelAndView toManageMain(String role_id) {
		System.out.println("执行教练主页");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/belongtocoachstudents");
		return mav;
	}
	//入口
	@RequestMapping(value = "/tocoachrating.action")
	public ModelAndView toCoachRating(String role_id) {
		System.out.println("学员评价教练统计页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentratingcoa");
		return mav;
	}
	@RequestMapping(value = "/studentratingmsg.action",method=RequestMethod.POST)
	public @ResponseBody List<TbRating> getStudentRatingMsg(HttpServletRequest request) {
		System.out.println("查询学生对老师的评价");
		String choose=request.getParameter("chooserating");
		System.out.println(choose);
		List<TbRating> ratinglist=lCoachServiceImpl.selectStudentratingmsg(1,choose);
		return ratinglist;
		
	}
}
