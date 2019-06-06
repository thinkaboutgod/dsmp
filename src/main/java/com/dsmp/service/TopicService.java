package com.dsmp.service;

import java.util.List;

import com.dsmp.pojo.TbTopic;

public interface TopicService {
	public TbTopic findTopic(Integer topId);
	/**
	 * @param topicAmount 出题数量
	 * @return 随机产生一份题目数量为topicAmount的试卷
	 */
	public List<TbTopic> findManyTopic(Integer topicAmount);
	
}
