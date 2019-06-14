package com.dsmp.mapper;

import java.util.List;

import com.dsmp.pojo.TbTopic;

public interface TbTopicMapper {
	public TbTopic findTopic(Integer topId);
	public List<TbTopic> findManyTopic(Integer subId);
	public List<TbTopic> findMistakeTopic(Integer stuId);
	
	
	public int updateByPrimaryKeySelective(TbTopic record);//更新题目
	
	public int insertSelective(TbTopic record);//增加题目
	
	public int selecttopId();//查询刚插入的题目的id，即序号
	
	public String selecttopImg (Integer topId);//查询需要修改题目的旧图片
	
	public int deleteByPrimaryKey(Integer topId);//删除题目
}
