package com.dsmp.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.service.ExamScheduleService;
import com.dsmp.service.RatingService;

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
	ExamScheduleService examScheduleService;
	
	/**
	 * 返回到学员个人中心页面：教练评价，驾校评价，考试情况，科目学时查看
	 */
	@RequestMapping(value="/toStuPersonal.action")
	public ModelAndView toStuPersonal() {
		ModelAndView mav = new ModelAndView();
		
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
	public ModelAndView addCoachRating(Integer coa_id,Integer stu_id,Integer starNum,String ratingContent) {
		ModelAndView mav = new ModelAndView();
		if(starNum==null) {
			starNum=0;
		}
		System.out.println("coa_id:"+coa_id+",stu_id:"+stu_id+",starNum:"+starNum);
		System.out.println("ratingContent:"+ratingContent);
		if(null!=coa_id && null!=stu_id
				&& null!=ratingContent && !"".equals(ratingContent)) {
			ratingService.addCoachRating(coa_id,stu_id,starNum,ratingContent);
			
		}
		mav.setViewName("client/");
		return mav;
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
	public ModelAndView addSchoolRating(Integer sch_id,Integer stu_id,Integer starNum,String ratingContent) {
		ModelAndView mav = new ModelAndView();
		if(starNum==null) {
			starNum=0;
		}
		System.out.println("sch_id:"+sch_id+",stu_id:"+stu_id+",starNum:"+starNum);
		System.out.println("ratingContent:"+ratingContent);
		if(null!=sch_id && null!=stu_id && null!=starNum 
				&& null!=ratingContent && !"".equals(ratingContent)) {
			ratingService.addSchoolRating(sch_id,stu_id,starNum,ratingContent);
			
		}
		mav.setViewName("client/");
		return mav;
	}
	/**
	 * @return 返回到学员考试安排表（查看）
	 */
	@RequestMapping(value="/examSchedule.action")
	public ModelAndView examSchedule() {
		ModelAndView mav = new ModelAndView();
		examScheduleService.findExamScheduleByStuId(1);
		
		
		mav.addObject("sch_id", 1);
		mav.addObject("stu_id", 2);
		
		mav.setViewName("client/examSchedule");
		return mav;
	}

}
