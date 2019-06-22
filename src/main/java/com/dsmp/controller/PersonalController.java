package com.dsmp.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.pojo.TbExamscheduleandstudent;
import com.dsmp.pojo.TbStudent;
import com.dsmp.pojo.TbStudyrecord;
import com.dsmp.pojo.TbSubjectscore;
import com.dsmp.service.ExamScheduleService;
import com.dsmp.service.RatingService;
import com.dsmp.service.StudentService;
import com.dsmp.service.StudyRecordService;
import com.dsmp.service.SubjectScoreService;

/**
 * 学员个人中心控制层
 *
 */
@Controller
@RequestMapping("/personal")
public class PersonalController {
	@Autowired
	private HttpSession session;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private ExamScheduleService examScheduleService;
	@Autowired
	private SubjectScoreService subjectScoreService;
	@Autowired
	private StudyRecordService studyRecordService;
	@Autowired
	private StudentService studentService;
	/**
	 * 返回到学员个人中心页面：教练评价，驾校评价，考试情况，科目学时查看
	 */
	@RequestMapping(value="/toStuPersonal.action")
	public ModelAndView toStuPersonal() {
		ModelAndView mav = new ModelAndView();
		String visitRes = "success";//可否访问个人中心（看是否登录，是否注册）
		TbStudent student =  (TbStudent) session.getAttribute("student");//取到session里面存的登录后的session
		if(null!=student) {
			if(student.getStuId()!=null&&student.getCoaId()!=null&&student.getSchId()!=null&&student.getSubId()!=null) {
				//1.评价相关：教练评价和驾校评价
				mav.addObject("coa_id", student.getCoaId());
				mav.addObject("sch_id", student.getSchId());
				mav.addObject("stu_id", student.getStuId());
				//2.该学员考试安排查看（只有在科目状态为"已预约"才有考试安排可以看，并且只能看到学员目前所处科目自己的安排(最新的那个安排表)）
				String examVisit = "seeExam";
				if("已预约".equals(student.getStuBookingstate())) {
					List<TbExamscheduleandstudent> examscheduleandstudentList = examScheduleService.findExamScheduleByStuIdAndSubId(student.getStuId(),student.getSubId());
					if(null!=examscheduleandstudentList&&examscheduleandstudentList.size()>0) {
						TbExamscheduleandstudent examscheduleandstudent = examscheduleandstudentList.get(0);
						System.out.println("考试安排表下的学员名称："+examscheduleandstudent.getTbStudent().getStuName()+",考试时间："+examscheduleandstudent.getTbExamschedule().getExsTime());
						mav.addObject("examscheduleandstudent", examscheduleandstudent);
					}
					
				}else {//不是已预约就看不到考试安排
					examVisit = "noExam";
				}
				mav.addObject("examVisit", examVisit);
				//3.查看学员各个科目成绩：
				List<TbSubjectscore> subjectscoreList = subjectScoreService.findSubjectScore(student.getStuId());
				mav.addObject("subjectscoreList", subjectscoreList);//把各个科目成绩的所有信息放入
				//4.科目二或科目三的学习记录查看
				List<TbStudyrecord> studyRecord2List = studyRecordService.findStudyRecord(student.getStuId(),"科目二");
				if(null!=studyRecord2List) {
//					System.out.println("studyRecord2List-subName:"+studyRecord2List.get(0).getTbSubject().getSubName());
					mav.addObject("studyRecord2List", studyRecord2List);
				}
				List<TbStudyrecord> studyRecord3List = studyRecordService.findStudyRecord(student.getStuId(),"科目三");
				if(null!=studyRecord3List) {
//					System.out.println("studyRecord3List-subName:"+studyRecord3List.get(0).getTbSubject().getSubName());
					mav.addObject("studyRecord3List", studyRecord3List);
				}
				//5.基本信息
				TbStudent stuDetail = studentService.findStuDetailById(student.getStuId());
				System.out.println("stuDetail-subName:"+stuDetail.getTbSubject().getSubName());
				System.out.println("stuDetail-bookingstate:"+stuDetail.getStuBookingstate());
				System.out.println("stuDetail-coaName:"+stuDetail.getTbCoach().getCoaName());
				
			}else {//进入个人中心需是已报名
				visitRes = "needRegister";
			}
		}else {//进入个人中心需要登录
			visitRes = "needLogin";
		}
		

		mav.addObject("visitRes", visitRes);//可否访问个人中心（看是否登录，是否注册）
		mav.setViewName("client/stu_personal");
		return mav;
	}
	/**去教练评价页面
	 * @return
	 */
	@RequestMapping(value="/coachRating.action")
	public ModelAndView coachRating() {//Integer coa_id,Integer sch_id,Integer stu_id
		ModelAndView mav = new ModelAndView();

		
		mav.addObject("coa_id", 1);
		mav.addObject("stu_id", 2);
		mav.setViewName("client/coachRating");
		return mav;
	}
	/**学员对教练的评价插入评价表中
	 * @param coa_id 教练id
	 * @param stu_id 学员id
	 * @param starNum 几颗星（1-5）
	 * @param ratingContent(评价内容)
	 * @return
	 */
	@RequestMapping(value="/addCoachRating.action")
	@ResponseBody
	public String addCoachRating(Integer coa_id,Integer stu_id,Integer starNum,String ratingContent) {
		String resInfo="failed";
		if(starNum==null) {
			starNum=0;
		}
		System.out.println("coa_id:"+coa_id+",stu_id:"+stu_id+",starNum:"+starNum);
		System.out.println("ratingContent:"+ratingContent);
		if(null!=coa_id && null!=stu_id
				&& null!=ratingContent && !"".equals(ratingContent)) {
			boolean res = ratingService.addCoachRating(coa_id,stu_id,starNum,ratingContent);
			System.out.println("res:"+res);
			if(res) {
				resInfo="success";
			}
		}
//		mav.setViewName("client/");
		return resInfo;
	}
	/**去驾校评价页面
	 * @return
	 */
	@RequestMapping(value="/schoolRating.action")
	public ModelAndView schoolRating() {
		ModelAndView mav = new ModelAndView();
//		System.out.println(sch_id:"+sch_id+",stu_id:"+stu_id);
		
		
		mav.addObject("sch_id", 1);
		mav.addObject("stu_id", 2);
		mav.setViewName("client/schoolRating");
		return mav;
	}
	/**学员对驾校的评价插入评价表中
	 * @param sch_id 教练id
	 * @param stu_id 学员id
	 * @param starNum 几颗星（1-5）
	 * @param ratingContent(评价内容)
	 * @return
	 */
	@RequestMapping(value="/addSchoolRating.action")
	@ResponseBody
	public String addSchoolRating(Integer sch_id,Integer stu_id,Integer starNum,String ratingContent) {
		String resInfo="failed";
		if(starNum==null) {
			starNum=0;
		}
		System.out.println("sch_id:"+sch_id+",stu_id:"+stu_id+",starNum:"+starNum);
		System.out.println("ratingContent:"+ratingContent);
		if(null!=sch_id && null!=stu_id && null!=starNum 
				&& null!=ratingContent && !"".equals(ratingContent)) {
			boolean res = ratingService.addSchoolRating(sch_id,stu_id,starNum,ratingContent);
			if(res) {
				resInfo="success";
			}
		}
		
		return resInfo;
	}


}
