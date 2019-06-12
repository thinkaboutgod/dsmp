package com.dsmp.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsmp.mapper.TbTopicMapper;
import com.dsmp.pojo.TbMistakeCollection;
import com.dsmp.pojo.TbTopic;
import com.dsmp.service.MistakeCollectionService;
import com.dsmp.service.TopicService;
@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TbTopicMapper tbTopicMapper;
	@Autowired
	private MistakeCollectionService mistakeCollectionService;
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
	/**
	 * @param studentId 学员id
	 * @param exResultMap 学员提交一份模拟卷时候的map集合，key-题目id，value-该题学员做对了(yes)还是错了(no)
	 * 错了就往错题集表里插入记录，对了就看错题集里面是否有这条记录，有则删除。
	 */
	@Override
	public void addOrDelMistakeCollection(Integer studentId, Map<String, String> exResultMap) {
		Set<String> keySet = exResultMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String topId = iterator.next();
			String optionRes = exResultMap.get(topId);
			System.out.println("topId:"+topId+",optionRes:"+optionRes);
			//先查看一下这条记录是否已经存在：
			TbMistakeCollection mistakeCollection = mistakeCollectionService.findMistakeTopicBySidAndTopId(studentId, topId);
			boolean topicExist = true;//错题集存在这题
			if(null==mistakeCollection) {
				topicExist = false;//错题集不存在这题
			}
			if("no".equals(optionRes)) {//如果这一题选错的话
				//记录插入到错题集表里：
				if(!topicExist) {//错题集不存在这题
					System.out.println("错题集不存在这题,可执行插入：");
					boolean addres = mistakeCollectionService.addMistakeTopic(studentId, topId);
					if(addres) {
						System.out.println("插入studentId&topId："+studentId+","+topId+"成功！");
					}
				}else {
					System.out.println("学员做错了，但错题集已经存在这题，无需插入！");
				}
				
			}else if("yes".equals(optionRes)) {//如果这一题选对的话
				//从错题集中删除这条记录：
				if(topicExist) {//错题集存在这题
					System.out.println("学员做对了，错题集存在这题,可执行删除：");
					boolean delres = mistakeCollectionService.delMistakeTopic(studentId, topId);
					if(delres) {
						System.out.println("删除studentId&topId："+studentId+","+topId+"成功！");
					}
				}
				
			}
			
		}
		
	}
	@Override
	public List<TbTopic> findMistakeTopic(Integer stuId) {
		
		
		return tbTopicMapper.findMistakeTopic(stuId);
	}
	@Override
	public List<TbTopic> findAllTopic() {
		List<TbTopic> topicList = tbTopicMapper.findManyTopic();//从数据库题目表查出所有试题
		
		return topicList;
	}

}
