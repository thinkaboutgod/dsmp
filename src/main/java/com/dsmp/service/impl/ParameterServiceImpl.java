package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	TbParameterMapper tbParameterMapper;

//根据名字查询参数
	@Override
	public String searchParameterByName(String name) {

		return tbParameterMapper.selectParamter(name);
	}

}
