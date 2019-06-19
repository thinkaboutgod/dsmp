package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.Video;
import com.dsmp.mapper.TbParameterMapper;
import com.dsmp.mapper.TbVideoMapper;
import com.dsmp.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	TbVideoMapper tbVideoMapper;

	@Override
	public List<Video> searchVideoBySubId(String subId) {
		return tbVideoMapper.searchVideoBySubId(subId);
	}

	
}
