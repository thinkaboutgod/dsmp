package com.dsmp.service;

import com.dsmp.pojo.TbMistakeCollection;

public interface MistakeCollectionService {
	public TbMistakeCollection findMistakeTopicBySidAndTopId(Integer stuId,String topId);
	public boolean addMistakeTopic(Integer stuId,String topId);
	public boolean delMistakeTopic(Integer stuId,String topId);
}
