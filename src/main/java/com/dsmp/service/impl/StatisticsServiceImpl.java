package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsmp.mapper.StatisticsMapper;
import com.dsmp.service.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {

	@Autowired private StatisticsMapper statisticsMapper;
	
}
