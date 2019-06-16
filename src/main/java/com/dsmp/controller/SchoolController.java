package com.dsmp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsmp.pojo.TbRating;
import com.dsmp.service.SchoolService;

@Controller
@RequestMapping("school")
public class SchoolController {

	@Autowired private SchoolService schoolService;	
	


}

