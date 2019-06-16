package com.dsmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbRatingMapper;
import com.dsmp.pojo.TbRating;
import com.dsmp.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired private TbRatingMapper tbRatingMapper;
	
	@Override
	public List<TbRating> selectSchoolratingmsg(Integer schId, String choose) {
		if (choose.equals("所有评价")) {
			choose = null;
		}
		return tbRatingMapper.selectSchoolratingmsg(schId,choose);
	}

}
