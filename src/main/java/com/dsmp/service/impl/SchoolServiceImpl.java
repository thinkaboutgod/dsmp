package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbSchoolMapper;

import com.dsmp.pojo.TbCar;
import com.dsmp.pojo.TbCoach;
import com.dsmp.pojo.TbSchool;
import com.dsmp.pojo.TbStudent;

import com.dsmp.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired private TbSchoolMapper tbSchoolMapper;

}
