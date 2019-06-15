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
import com.dsmp.pojo.TbExamschedule;
import com.dsmp.pojo.TbRating;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.LCoachService;
import com.dsmp.utils.GsonUtils;
 

@Controller
@RequestMapping("/coach")
public class LCoachController {

	@Autowired private LCoachService lCoachServiceImpl;
	@Autowired
	private HttpSession session;
	
	private int stuId;
	private String subName=null;
	
	private String subname2=null;
	//学员预约的弹窗
	@RequestMapping("/coach/gotobookingbounced.action")	
	public ModelAndView gotoBookingbounced(HttpServletRequest request) {
		System.out.println("预约弹框跳转");
		stuId=Integer.valueOf(request.getParameter("stuId"));
		subName=request.getParameter("subName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/testappointment");
		return mav;		
	}	
	//学生查看的弹窗界面
	@RequestMapping("/coach/tostudentparticulars.action")
	public ModelAndView tostudentparticulars(HttpServletRequest request) {
		System.out.println("转换界面");
		if(request.getParameter("stuId")!=null) {
			stuId=Integer.valueOf(request.getParameter("stuId"));
		}		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentparticulars");		
		return mav;		
	}
	//查找学生的的科目考试情况信息
	@RequestMapping("/coach/studentparticulars.action")
	public @ResponseBody Map<String, List<BelongtoCoachStudentMsg>>  getStudentParticulars(){
		System.out.println("学生详情页");
		List<BelongtoCoachStudentMsg> studentmsg=lCoachServiceImpl.selectStudentParticulars(stuId);
		Map<String, List<BelongtoCoachStudentMsg>>  studentParticulars=new HashMap<>();
		studentParticulars.put("data", studentmsg);
		return studentParticulars;
	}
	//查找所属教练的学生信息
	@RequestMapping(value="/coach/belongtocoach.action",method=RequestMethod.POST)
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
	@RequestMapping(value = "/coach/studentratingmsg.action",method=RequestMethod.POST)
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
	//全部可预约场次
	@RequestMapping(value = "/coach/selectthetestmsg.action" ,method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbExamschedule>> getThetestmsg(){
		System.out.println("获得考试安排信息");
		Map<String, List<TbExamschedule>> thetestmsgmap=new HashMap<>();
		
		List<TbExamschedule> thetestmsg=lCoachServiceImpl.selectThetestmsg(1,null);//驾校id从session域中的教练信息中拿
		
		thetestmsgmap.put("data",thetestmsg);
		
		return thetestmsgmap;		
	}
	//单一个学生可预约场次
	@RequestMapping(value = "/coach/selectthestudenttestmsg.action" ,method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbExamschedule>> getStudentThetestmsg(){
		System.out.println("获得考试安排信息");
		Map<String, List<TbExamschedule>> thetestmsgmap=new HashMap<>();
		
		List<TbExamschedule> thetestmsg=lCoachServiceImpl.selectThetestmsg(1,subName);//驾校id从session域中的教练信息中拿
		
		thetestmsgmap.put("data",thetestmsg);
		
		return thetestmsgmap;		
	}
	//可预约学生
	@RequestMapping(value = "/coach/testappointment.action" ,method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getTestappointment(HttpServletRequest request){
		Map<String, List<TbStudent>> testappointmentmap=new HashMap<>();
		if(request.getParameter("thesubject")!=null) {
			subname2=request.getParameter("thesubject");
		}	
		List<TbStudent> testappointment=lCoachServiceImpl.findTestappointment(subname2, 1);
		testappointmentmap.put("data",testappointment);	
		return testappointmentmap;		
	}
	//已预约学生
	@RequestMapping(value = "/coach/haveappointment.action" ,method=RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getHaveappointmentstudent(){
		Map<String, List<TbStudent>> testappointmentmap=new HashMap<>();
		List<TbStudent> haveappointmentstudent=lCoachServiceImpl.findHaveappointment(1,null);
		testappointmentmap.put("data", haveappointmentstudent);		
		return testappointmentmap;		
	}
	//预约
	
	@RequestMapping(value = "/coach/testbooking.action" ,method=RequestMethod.POST)
	public @ResponseBody String insertBookingMsg(HttpServletRequest request) {
		int exsId=Integer.valueOf(request.getParameter("exsId"));
		String subName=request.getParameter("subName");
		String result=null;
		System.out.println(exsId);
		System.out.println(subName);
		//查找教练各科下的报名人数
		int count=lCoachServiceImpl.findNumberofsubjects(subName,1);
		//生成座位号
		
		int seatNum=0;
		if(lCoachServiceImpl.findSeatNum(exsId)!=null) {
			seatNum=lCoachServiceImpl.findSeatNum(exsId)+1;
		}else {
			seatNum=1;
		}
		if(count<5) {
			lCoachServiceImpl.insertBooking(exsId, stuId, seatNum);
			lCoachServiceImpl.updateBookingstate(stuId);
			result="success";
		}else{
			result="fail";
		}
		String results=GsonUtils.toJson(result);
		return results;
	}
}
