package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbTopic;

public interface TbTopicMapper {
	public TbTopic findTopic(Integer topId);
	public List<TbTopic> findManyTopic();
	
}
