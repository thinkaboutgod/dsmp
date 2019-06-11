package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.BelongtoCoachStudentMsg;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;
import com.dsmp.service.MenuService;

@Controller
public class LCoachController {

	@Autowired
	private LCoachService lCoachServiceImpl;
	@Autowired
	private MyResult myResult;
	@Autowired
	private MenuService menuService;
	@Autowired
	private HttpSession session;
	
	private int stuId;
	//学生查看的弹窗界面
	@RequestMapping("/tostudentparticulars.action")
	public ModelAndView tostudentparticulars(HttpServletRequest request) {
		System.out.println("转换界面");
		stuId=Integer.valueOf(request.getParameter("stuId"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentparticulars");		
		return mav;
		
	}
	//查找学生的的科目考试情况信息
	@RequestMapping("/studentparticulars.action")
	public @ResponseBody Map<String, List<BelongtoCoachStudentMsg>>  getStudentParticulars(){
		System.out.println("学生详情页");
		List<BelongtoCoachStudentMsg> studentmsg=lCoachServiceImpl.selectStudentParticulars(stuId);
		Map<String, List<BelongtoCoachStudentMsg>>  studentParticulars=new HashMap<>();
		studentParticulars.put("data", studentmsg);
		return studentParticulars;
	}
	//查找所属教练的学生信息
	@RequestMapping(value="/belongtocoach.action",method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getStudent(HttpServletRequest request){	
		System.out.println("教练主页");
		List<TbStudent> studentlist= lCoachServiceImpl.belongtococh(1,request);
		Map<String,List<TbStudent>> studentlistmap=new HashMap<>();
		studentlistmap.put("data",studentlist);	
		return studentlistmap;
	}	
	//查看学员界面
	@RequestMapping(value = "/tocoachfindstudents.action")
	public ModelAndView toManageMain(String role_id) {
		System.out.println("执行教练主页");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/belongtocoachstudents");
		return mav;
	}
	//查看评价统计界面
	@RequestMapping(value = "/tocoachrating.action")
	public ModelAndView toCoachRating(String role_id) {
		System.out.println("学员评价教练统计页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentratingcoa");
		return mav;
	}
	//对教练的评价信息
	@RequestMapping(value = "/studentratingmsg.action",method=RequestMethod.POST)
	public @ResponseBody List<TbRating> getStudentRatingMsg(HttpServletRequest request) {
		System.out.println("查询学生对老师的评价");
		String choose=request.getParameter("chooserating");
		System.out.println(choose);
		List<TbRating> ratinglist=lCoachServiceImpl.selectStudentratingmsg(1,choose);
		return ratinglist;	
	}
	//考试安排界面
	@RequestMapping(value = "/toexamarrangement.action")
	public ModelAndView toExamArrangement(String role_id) {
		System.out.println("考试安排页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/examarrangement");
		return mav;
	}
	//考试安排信息
	@RequestMapping(value = "/selectthetestmsg.action" ,method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbExamschedule>> getThetestmsg(){
		System.out.println("获得考试安排信息");
		Map<String, List<TbExamschedule>> thetestmsgmap=new HashMap<>();
		
		List<TbExamschedule> thetestmsg=lCoachServiceImpl.selectThetestmsg(1);//驾校id从session域中的教练信息中拿
		
		thetestmsgmap.put("data", thetestmsg);
		
		return thetestmsgmap;		
	}


	// 学员打卡界面
	@RequestMapping(value = "/searchSubjectStudent.action")
	public ModelAndView searchSubjectStudent() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("studenstList", lCoachServiceImpl.searchSubjectStudent());
		mav.setViewName("coach/student_clockin");
		return mav;
	}

	// 查询学员已学时长
	@RequestMapping(value = "/searchStudentStudyTime.action")
	public @ResponseBody MyResult searchStudentTime(String stuId,String subId) {
		
		return lCoachServiceImpl.countTimeByStuIdAndSubject(stuId,subId);
	}

}
