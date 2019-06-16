package com.dsmp.mapper;

import com.dsmp.pojo.TbRating;
import java.util.List;

public interface TbRatingMapper {

	List<TbRating> selectSchoolratingmsg(Integer schId, String choose);

	
}