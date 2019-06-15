package com.dsmp.mapper;

import org.apache.ibatis.annotations.Param;

import com.dsmp.pojo.TbMistakeCollection;

public interface TbMistakeCollectionMapper {
	public TbMistakeCollection findMistakeTopicBySidAndTopId(@Param("stuId")Integer stuId, @Param("topId")String topId);
	public int addMistakeTopic(@Param("stuId")Integer stuId, @Param("topId")String topId);
	public int delMistakeTopic(@Param("stuId")Integer stuId, @Param("topId")String topId);

}
