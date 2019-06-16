package com.dsmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsmp.mapper.TbMistakeCollectionMapper;
import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.pojo.TbMistakeCollection;
import com.dsmp.service.MistakeCollectionService;
@Service
public class MistakeCollectionServiceImpl implements MistakeCollectionService {
	@Autowired
	private TbMistakeCollectionMapper tbMistakeCollectionMapper;
	@Override
	public TbMistakeCollection findMistakeTopicBySidAndTopId(Integer stuId, String topId) {
		
		return tbMistakeCollectionMapper.findMistakeTopicBySidAndTopId(stuId,topId);
	}
	@Transactional
	@Override
	public boolean addMistakeTopic(Integer stuId, String topId) {
		boolean delRes = false;
		int res = tbMistakeCollectionMapper.addMistakeTopic(stuId,topId);
		if(1==res) {
			delRes=true;
//			System.out.println("插入错题集成功！");
		}
		return delRes;
	}
	@Transactional
	@Override
	public boolean delMistakeTopic(Integer stuId, String topId) {
		boolean delRes = false;
		int res = tbMistakeCollectionMapper.delMistakeTopic(stuId,topId);
		if(1==res) {
			delRes=true;
//			System.out.println("插入错题集成功！");
		}
		return delRes;
	}

}
