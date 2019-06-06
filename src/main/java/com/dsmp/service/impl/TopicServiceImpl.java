package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.TopicService;
@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TbTopicMapper tbTopicMapper;
	@Override
	public TbTopic findTopic(Integer topId) {
		
		return tbTopicMapper.findTopic(topId);
	}
	/**
	 * @param topicAmount 出题数量
	 * @return 随机产生一份题目数量为topicAmount的试卷
	 */
	@Override
	public List<TbTopic> findManyTopic(Integer topicAmount) {
		List<TbTopic> topicList = tbTopicMapper.findManyTopic();//从数据库题目表查出所有试题
		topicList = randomTopic(topicList,topicAmount);
		
		return topicList;
	}
	/**从数据库题库里随机产生一张考卷，题目数量是(出一张卷子出几道题)topicAmount
	 * @param topicList 从数据库题目表查出所有的题目（包括题目下的选项）
	 * @param topicAmount 出题数量
	 * @return 产生的一套题量为topicAmount的模拟卷(如果出题量topicAmount小于等于题库题目数量则按出题量出题；如果出题量topicAmount大于题库题目数量，则按题库已有数量出题)
	 */
	public List<TbTopic> randomTopic(List<TbTopic> topicList,Integer topicAmount) {
		List<TbTopic> randomTopicList = new ArrayList<TbTopic>();
		Random ran = new Random();
		System.out.println("题库题目数量:"+topicList.size());
		if(topicList.size()>=topicAmount) {//如果题库题目数比出题数topicAmount大（或等于），则可以出题
			for(int i=0;i<topicAmount;i++) {//循环次数topicAmount，每次从题库拿到一道题放入到我的卷子中
				int ranIndex = ran.nextInt(topicList.size());
				TbTopic topic = topicList.get(ranIndex);
				randomTopicList.add(topic);
				//把已经插入的题目从集合移除掉
				topicList.remove(ranIndex);
				
			}
			
		}else {//如果出题数量大于题库题数，则把所有的题目都出掉
			randomTopicList=topicList;
		}
		return randomTopicList;
		
	}

}
