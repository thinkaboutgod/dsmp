package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired private TbStudentMapper tbStudentMapper;

	
	
}
