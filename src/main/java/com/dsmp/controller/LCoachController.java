package com.dsmp.controller;

import java.util.ArrayList;
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
import com.dsmp.utils.GsonUtils;

@Controller
@RequestMapping("/coach")
public class LCoachController {

	@Autowired
	private LCoachService lCoachServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private MyResult myResult;
	private int stuId;
	private String subName = null;

	private String subname2 = null;
	
	private int subId;

	// 学员预约的弹窗
	@RequestMapping("/coach/gotobookingbounced.action")
	public ModelAndView gotoBookingbounced(HttpServletRequest request) {
		System.out.println("预约弹框跳转");
		stuId = Integer.valueOf(request.getParameter("stuId"));

		subName = request.getParameter("subName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/testappointment");
		return mav;
	}

	// 学生查看的弹窗界面
	@RequestMapping("/coach/tostudentparticulars.action")
	public ModelAndView tostudentparticulars(HttpServletRequest request) {
		System.out.println("转换界面");
		if (request.getParameter("stuId") != null) {
			stuId = Integer.valueOf(request.getParameter("stuId"));
			subId=Integer.valueOf(request.getParameter("subId"));
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentparticulars");
		return mav;
	}

	// 查找学生的的科目考试情况信息
	@RequestMapping("/coach/studentparticulars.action")
	public @ResponseBody Map<String, List<BelongtoCoachStudentMsg>> getStudentParticulars() {
		System.out.println("学生详情页");
		///////
		List<BelongtoCoachStudentMsg> allacademicrecord=new ArrayList<BelongtoCoachStudentMsg>();
		Double studytimenow=lCoachServiceImpl.findStudytime(subId ,stuId);
		BelongtoCoachStudentMsg elongtoCoachStudentMsg2=lCoachServiceImpl.findSubjectnow(subId ,stuId);
		elongtoCoachStudentMsg2.setStrTime(studytimenow);
		allacademicrecord.add(elongtoCoachStudentMsg2);
		
		if(subId>1) {
			for(int i=1;i<subId;i++) {
				
				Double studytime=lCoachServiceImpl.findStudytime(i ,stuId);//学生某科的打卡计时
	
				
				List<BelongtoCoachStudentMsg> academicrecord=lCoachServiceImpl.findAcademicrecord(i, stuId);
				for(int j=0;j<academicrecord.size();j++) {
					BelongtoCoachStudentMsg belongtoCoachStudentMsg=academicrecord.get(j);
					belongtoCoachStudentMsg.setStrTime(studytime);	
					allacademicrecord.add(belongtoCoachStudentMsg);
				}
			}
		}
		for(BelongtoCoachStudentMsg aaa:allacademicrecord) {
			System.out.println("科目--》"+aaa.getSubName());
			System.out.println("学时--》"+aaa.getStrTime());
			System.out.println("总学时--》"+aaa.getSubTime());
			System.out.println("得分--》"+aaa.getSusScore());
		}
		///////////
		Map<String, List<BelongtoCoachStudentMsg>> studentParticulars = new HashMap<>();
		studentParticulars.put("data", allacademicrecord);
		return studentParticulars;
	}

	// 查找所属教练的学生信息
	@RequestMapping(value = "/coach/belongtocoach.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getStudent(HttpServletRequest request) {
		System.out.println("教练主页");
		List<TbStudent> studentlist = lCoachServiceImpl.belongtococh(1, request);
		Map<String, List<TbStudent>> studentlistmap = new HashMap<>();
		studentlistmap.put("data", studentlist);
		return studentlistmap;
	}

	// 查看学员界面
	@RequestMapping(value = "/tocoachfindstudents.action")
	public ModelAndView toManageMain(String role_id) {
		System.out.println("执行教练主页");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/belongtocoachstudents");
		return mav;
	}

	// 查看评价统计界面
	@RequestMapping(value = "/tocoachrating.action")
	public ModelAndView toCoachRating(String role_id) {
		System.out.println("学员评价教练统计页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/studentratingcoa");
		return mav;
	}

	// 对教练的评价信息
	@RequestMapping(value = "/coach/studentratingmsg.action", method = RequestMethod.POST)
	public @ResponseBody List<TbRating> getStudentRatingMsg(HttpServletRequest request) {
		System.out.println("查询学生对老师的评价");
		String choose = request.getParameter("chooserating");
		System.out.println(choose);
		List<TbRating> ratinglist = lCoachServiceImpl.selectStudentratingmsg(1, choose);
		return ratinglist;
	}

	// 考试安排界面
	@RequestMapping(value = "/toexamarrangement.action")
	public ModelAndView toExamArrangement(String role_id) {
		System.out.println("考试安排页面");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coach/examarrangement");
		return mav;
	}

	// 全部可预约场次
	@RequestMapping(value = "/coach/selectthetestmsg.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TbExamschedule>> getThetestmsg() {
		System.out.println("获得考试安排信息");
		Map<String, List<TbExamschedule>> thetestmsgmap = new HashMap<>();

		List<TbExamschedule> thetestmsg = lCoachServiceImpl.selectThetestmsg(1, null);// 驾校id从session域中的教练信息中拿

		thetestmsgmap.put("data", thetestmsg);

		return thetestmsgmap;
	}

	// 单一个学生可预约场次
	@RequestMapping(value = "/coach/selectthestudenttestmsg.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TbExamschedule>> getStudentThetestmsg() {
		System.out.println("获得考试安排信息");
		Map<String, List<TbExamschedule>> thetestmsgmap = new HashMap<>();

		List<TbExamschedule> thetestmsg = lCoachServiceImpl.selectThetestmsg(1, subName);// 驾校id从session域中的教练信息中拿

		thetestmsgmap.put("data", thetestmsg);

		return thetestmsgmap;
	}

	// 可预约学生
	@RequestMapping(value = "/coach/testappointment.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getTestappointment(HttpServletRequest request) {

		Map<String, List<TbStudent>> testappointmentmap = new HashMap<>();
		if (request.getParameter("thesubject") != null) {
			subname2 = request.getParameter("thesubject");
		}
		List<TbStudent> testappointment = lCoachServiceImpl.findTestappointment(subname2, 1);
		testappointmentmap.put("data", testappointment);
		return testappointmentmap;
	}

	// 已预约学生
	@RequestMapping(value = "/coach/haveappointment.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, List<TbStudent>> getHaveappointmentstudent() {
		Map<String, List<TbStudent>> testappointmentmap = new HashMap<>();
		List<TbStudent> haveappointmentstudent = lCoachServiceImpl.findHaveappointment(1, null);
		testappointmentmap.put("data", haveappointmentstudent);
		return testappointmentmap;
	}
	// 预约

	@RequestMapping(value = "/coach/testbooking.action", method = RequestMethod.POST)
	public @ResponseBody String insertBookingMsg(HttpServletRequest request) {
		int exsId = Integer.valueOf(request.getParameter("exsId"));
		String subName = request.getParameter("subName");
		String result = null;
		System.out.println(exsId);

		// 查找教练各科下的报名人数
		int count = lCoachServiceImpl.findNumberofsubjects(subName, 1);
		// 查找每场考试已报名学员数量
		TbExamschedule tbExamschedule = lCoachServiceImpl.findBookingnumber(exsId);

		// 生成座位号
		int seatNum = 0;
		int exsSignupnum = 0;
		if (lCoachServiceImpl.findSeatNum(exsId) != null) {
			seatNum = lCoachServiceImpl.findSeatNum(exsId) + 1;
		} else {
			seatNum = 1;
		}
		if (tbExamschedule.getExsSignupnum() != null) {
			exsSignupnum = tbExamschedule.getExsSignupnum() + 1;

		} else {
			exsSignupnum = 1;
		}
		if (count < 5) {
			System.out.println("本场考试人员：" + stuId);
			lCoachServiceImpl.insertBooking(exsId, stuId, seatNum);
			lCoachServiceImpl.updateBookingstate(stuId);
			lCoachServiceImpl.updateMaxBookingnum(exsSignupnum, exsId);
			result = "success";
		} else {
			result = "fail";
		}
		String results = GsonUtils.toJson(result);
		return results;
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
	public @ResponseBody MyResult searchStudentTime(String stuId, String subId) {

		return lCoachServiceImpl.countTimeByStuIdAndSubject(stuId, subId);
	}

	// 查询学员本次能否打卡学习（科目二，三）
	@RequestMapping(value = "/beginStudyJud.action")
	public @ResponseBody MyResult beginStudyJud(String stuId, String subId) {

		return lCoachServiceImpl.beginStudyJud(stuId, subId);
	}

	// 学员人脸识别打卡（科目二，三）
	@RequestMapping(value = "/makeClock.action")
	public @ResponseBody MyResult makeClock(HttpServletRequest request, String base, String stuId, String subId) {

		return lCoachServiceImpl.makeClock(request, base, stuId, subId);
	}

	// 学员人脸识别打卡，插入打卡记录（科目二，三）
	@RequestMapping(value = "/studyRecord.action")
	public @ResponseBody MyResult insertStudyRecord(HttpServletRequest request, String base, String stuId,
			String subId) {

		return lCoachServiceImpl.insertStudyRecord(stuId, subId);
	}

	// 学员结束打卡学习判断
	@RequestMapping(value = "/endStudyJud.action")
	public @ResponseBody MyResult endStudyJud(String stuId, String subId) {

		return lCoachServiceImpl.endStudyJud(stuId, subId);
	}

	// 学员结束打卡学习插入记录
	@RequestMapping(value = "/endStudyRecord.action")

	public @ResponseBody MyResult endStudyRecord(String stuId, String subId) {

		return lCoachServiceImpl.endStudyRecord(stuId, subId);
	}
}
