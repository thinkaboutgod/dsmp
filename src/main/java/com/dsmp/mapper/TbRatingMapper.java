package com.dsmp.mapper;

import com.dsmp.pojo.TbRating;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRatingMapper {
	public int addCoachRating(TbRating tbRating);
	public int addSchoolRating(TbRating tbRating);
}