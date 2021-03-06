package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsmp.mapper.TbCoachMapper;
import com.dsmp.mapper.TbSchoolMapper;
import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.service.CoachService;
import com.dsmp.service.SchoolService;

@Controller
@RequestMapping("tbcoach")
public class CoachController {

	@Autowired private CoachService coachService;
	@Autowired private TbSchoolMapper tbSchoolMapper;
	@Autowired private MyResult myResult;
	
	@Autowired private TbCoachMapper tbCoachMapper;
	@RequestMapping(value="toschool_coach")
	public String toSchoolCoach(HttpSession session) {
		return "back/school_coach";
	}
	
	/**
	 * 	查询驾校名下的教练
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectCoasByCondition")
	public @ResponseBody Map<String,List<TbCoach>> selectCoasByCondition(HttpServletRequest request) {
		Map<String,List<TbCoach>> map = new HashMap<>();
		List<TbCoach> coaList =  coachService.selectCoasByCondition(request);
		map.put("data", coaList);
		return map;
	}
	
	/**
	 * 	修改教练状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "changeCoachState")
	public @ResponseBody MyResult changeCoachState(HttpServletRequest request) {
		myResult = coachService.changeCoachState(request, myResult);
		return myResult;
	}
	
	/**
	 * 	添加教练
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addCoach")
	public @ResponseBody MyResult addCoach(HttpServletRequest request,MyResult myResult) {
		myResult = coachService.addCoach(request,myResult);
		return myResult;
	}
	
	//主页跳转教练页面
	@RequestMapping("/allCoachPage")
	public ModelAndView getAllCoachPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/allcoach");
		return mav;
	}		
	
	//获取教练集合
	@RequestMapping("/selectAllCoach")
	public @ResponseBody List<TbCoach> getSchoolByStauts(){
		List<TbCoach> coaList = tbCoachMapper.selectAllCoach();
		return coaList;
	}	

	
	//驾校信息页
		@RequestMapping("/coachInfo")
		public ModelAndView getCoachInfoPage(Integer coaId) {
			ModelAndView mav = new ModelAndView();			
			TbCoach tbCoach = coachService.selectCoachById(coaId);
			TbSchool tbSchool = tbSchoolMapper.findSchoolBySchId(tbCoach.getSchId());			
			mav.addObject("tbCoach",tbCoach);
			mav.addObject("tbSchool",tbSchool);
			mav.setViewName("client/coach_info");
			return mav;
		}	
}
