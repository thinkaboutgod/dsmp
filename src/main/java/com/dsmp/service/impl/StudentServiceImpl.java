package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbStudentMapper;
import com.dsmp.pojo.TbStudent;
import com.dsmp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired private TbStudentMapper tbStudentMapper;

	@Override
	public List<TbStudent> selectStusByCoaId(Integer coaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbStudent> selectStusBySchId(Integer schId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
