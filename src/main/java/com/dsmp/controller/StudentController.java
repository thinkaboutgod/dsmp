package com.dsmp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="toschool_student")
	public String toSchoolCoach(HttpSession session) {
		session.setAttribute("schId", 1);
		return "back/school_student";
	}
	
	@RequestMapping(value = "searchAllStudent.action")
	public @ResponseBody Map<String, List<TbStudent>> searchAllstudent(HttpServletRequest request) {
		List<TbStudent> list = studentService.searchAllstudent(request);
		Map<String, List<TbStudent>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	
}
