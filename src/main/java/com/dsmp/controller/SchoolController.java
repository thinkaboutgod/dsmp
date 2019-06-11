package com.dsmp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsmp.service.SchoolService;

@Controller
@RequestMapping("school")
public class SchoolController {

	@Autowired private SchoolService schoolService;	
	
}

