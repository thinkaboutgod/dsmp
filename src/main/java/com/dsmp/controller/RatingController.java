package com.dsmp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.MyResult;
import com.dsmp.pojo.TbRating;
import com.dsmp.service.RatingService;

@Controller
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired private RatingService ratingService;
	@Autowired private MyResult myResult;
	
	@RequestMapping(value="toschool_rating")
	public String toSchoolCoach(HttpSession session) {
		session.setAttribute("schId", 1);
		return "back/school_rating";
	}
	
	
	// 对教练的评价信息
	@RequestMapping(value = "/selectSchoolRating.action", method = RequestMethod.POST)
	public @ResponseBody List<TbRating> getStudentRatingMsg(HttpServletRequest request) {
		String choose = request.getParameter("chooserating");
//		String schId = request.getParameter("schId");
		System.out.println(choose);
//		Integer.parseInt(schId)
		List<TbRating> ratinglist = ratingService.selectSchoolratingmsg(1, choose);
		return ratinglist;
	}
	
}
