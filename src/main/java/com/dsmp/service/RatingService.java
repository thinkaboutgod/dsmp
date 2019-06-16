package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbRating;

public interface RatingService {

	List<TbRating> selectSchoolratingmsg(Integer schId, String choose);

}
