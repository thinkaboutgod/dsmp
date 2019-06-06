package com.dsmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dsmp.service.StudentService;

@Controller
public class StudentController {

	@Autowired private StudentService studentService;
	
}
